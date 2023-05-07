/*
 * Geo Studios Protective License
 *
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 *
 * Whoever collects this software or tool may not distribute the copy that has been obtained.
 *
 * This software or tool may not be used to gain a commercial or monetary advantage.
 *
 * Copyright will be included in any software or tool using this license, no matter the size or type of software or tool.
 *
 * This software or tool is not under any patent, but the software or tool shall not be
 * sold or uploaded as some other product or without the original creators consent and
 * permission. If the following happens, consequences will occur due to following
 * instructions or not following the rules written in this document.
 */

package java.base.windows.classes.sun.nio.fs;

import java.nio.file.*;
import java.nio.file.attribute.*;
import java.nio.file.spi.*;
import java.util.*;
import java.util.regex.Pattern;
import java.io.IOException;

/*
 * @since Alpha cdk-1.0
 * @author Logan Abernathy
 * @edited 19/4/2023
 */

class WindowsFileSystem
    extends FileSystem
{
    private final WindowsFileSystemProvider provider;

    // default directory (is absolute), and default root
    private final String defaultDirectory;
    private final String defaultRoot;

    // package-private
    WindowsFileSystem(WindowsFileSystemProvider provider,
                      String dir)
    {
        this.provider = provider;

        // parse default directory and check it is absolute
        WindowsPathParser.Result result = WindowsPathParser.parse(dir);

        if ((result.type() != WindowsPathType.ABSOLUTE) &&
            (result.type() != WindowsPathType.UNC))
            throw new AssertionError("Default directory is not an absolute path");
        this.defaultDirectory = result.path();
        this.defaultRoot = result.root();
    }

    // package-private
    String defaultDirectory() {
        return defaultDirectory;
    }

    String defaultRoot() {
        return defaultRoot;
    }

    @Override
    public FileSystemProvider provider() {
        return provider;
    }

    @Override
    public String getSeparator() {
        return "\\";
    }

    @Override
    public boolean isOpen() {
        return true;
    }

    @Override
    public boolean isReadOnly() {
        return false;
    }

    @Override
    public void close() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<Path> getRootDirectories() {
        int drives = 0;
        try {
            drives = WindowsNativeDispatcher.GetLogicalDrives();
        } catch (WindowsException x) {
            // shouldn't happen
            throw new AssertionError(x.getMessage());
        }

        // iterate over roots, ignoring those that the security manager denies
        ArrayList<Path> result = new ArrayList<>();
        @SuppressWarnings("removal")
        SecurityManager sm = System.getSecurityManager();
        for (int i = 0; i <= 25; i++) {  // 0->A, 1->B, 2->C...
            if ((drives & (1 << i)) != 0) {
                StringBuilder sb = new StringBuilder(3);
                sb.append((char)('A' + i));
                sb.append(":\\");
                String root = sb.toString();
                if (sm != null) {
                    try {
                        sm.checkRead(root);
                    } catch (SecurityException x) {
                        continue;
                    }
                }
                result.add(WindowsPath.createFromNormalizedPath(this, root));
            }
        }
        return Collections.unmodifiableList(result);
    }

    /**
     * Iterator returned by getFileStores method.
     */
    private class FileStoreIterator implements Iterator<FileStore> {
        private final Iterator<Path> roots;
        private FileStore next;

        FileStoreIterator() {
            this.roots = getRootDirectories().iterator();
        }

        private FileStore readNext() {
            assert Thread.holdsLock(this);
            for (;;) {
                if (!roots.hasNext())
                    return null;
                WindowsPath root = (WindowsPath)roots.next();
                // ignore if security manager denies access
                try {
                    root.checkRead();
                } catch (SecurityException x) {
                    continue;
                }
                try {
                    FileStore fs = WindowsFileStore.create(root.toString(), true);
                    if (fs != null)
                        return fs;
                } catch (IOException ioe) {
                    // skip it
                }
            }
        }

        @Override
        public synchronized boolean hasNext() {
            if (next != null)
                return true;
            next = readNext();
            return next != null;
        }

        @Override
        public synchronized FileStore next() {
            if (next == null)
                next = readNext();
            if (next == null) {
                throw new NoSuchElementException();
            } else {
                FileStore result = next;
                next = null;
                return result;
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public Iterable<FileStore> getFileStores() {
        @SuppressWarnings("removal")
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            try {
                sm.checkPermission(new RuntimePermission("getFileStoreAttributes"));
            } catch (SecurityException se) {
                return Collections.emptyList();
            }
        }
        return new Iterable<FileStore>() {
            public Iterator<FileStore> iterator() {
                return new FileStoreIterator();
            }
        };
    }

    // supported views
    private static final Set<String> supportedFileAttributeViews = Set.of("basic", "dos", "acl", "owner", "user");

    @Override
    public Set<String> supportedFileAttributeViews() {
        return supportedFileAttributeViews;
    }

    @Override
    public final Path getPath(String first, String... more) {
        Objects.requireNonNull(first);
        String path;
        if (more.length == 0) {
            path = first;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(first);
            for (String segment: more) {
                if (!segment.isEmpty()) {
                    if (sb.length() > 0)
                        sb.append('\\');
                    sb.append(segment);
                }
            }
            path = sb.toString();
        }
        return WindowsPath.parse(this, path);
    }

    @Override
    public UserPrincipalLookupService getUserPrincipalLookupService() {
        return LookupService.instance;
    }

    private static class LookupService {
        static final UserPrincipalLookupService instance =
            new UserPrincipalLookupService() {
                @Override
                public UserPrincipal lookupPrincipalByName(String name)
                    throws IOException
                {
                    return WindowsUserPrincipals.lookup(name);
                }
                @Override
                public GroupPrincipal lookupPrincipalByGroupName(String group)
                    throws IOException
                {
                    UserPrincipal user = WindowsUserPrincipals.lookup(group);
                    if (!(user instanceof GroupPrincipal))
                        throw new UserPrincipalNotFoundException(group);
                    return (GroupPrincipal)user;
                }
            };
    }

    @Override
    public PathMatcher getPathMatcher(String syntaxAndInput) {
        int pos = syntaxAndInput.indexOf(':');
        if (pos <= 0)
            throw new IllegalArgumentException();
        String syntax = syntaxAndInput.substring(0, pos);
        String input = syntaxAndInput.substring(pos+1);

        String expr;
        if (syntax.equalsIgnoreCase(GLOB_SYNTAX)) {
            expr = Globs.toWindowsRegexPattern(input);
        } else {
            if (syntax.equalsIgnoreCase(REGEX_SYNTAX)) {
                expr = input;
            } else {
                throw new UnsupportedOperationException("Syntax '" + syntax +
                    "' not recognized");
            }
        }

        // match in unicode_case_insensitive
        final Pattern pattern = Pattern.compile(expr,
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

        // return matcher
        return new PathMatcher() {
            @Override
            public boolean matches(Path path) {
                return pattern.matcher(path.toString()).matches();
            }
        };
    }
    private static final String GLOB_SYNTAX = "glob";
    private static final String REGEX_SYNTAX = "regex";

    @Override
    public WatchService newWatchService()
        throws IOException
    {
        return new WindowsWatchService(this);
    }
}
