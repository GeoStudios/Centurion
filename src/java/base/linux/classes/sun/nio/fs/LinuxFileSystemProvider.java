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

package java.base.linux.classes.sun.nio.fs;

import java.nio.file.*;
import java.nio.file.attribute.*;
import java.nio.file.spi.FileTypeDetector;
import java.io.IOException;

import java.base.share.classes.jdk.internal.util.StaticProperty;

/**
 * Linux implementation of FileSystemProvider
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 23/4/2023
 */

class LinuxFileSystemProvider extends UnixFileSystemProvider {
    public LinuxFileSystemProvider() {
        super();
    }

    @Override
    LinuxFileSystem newFileSystem(String dir) {
        return new LinuxFileSystem(this, dir);
    }

    @Override
    LinuxFileStore getFileStore(UnixPath path) throws IOException {
        return new LinuxFileStore(path);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <V extends FileAttributeView> V getFileAttributeView(Path obj,
                                                                Class<V> type,
                                                                LinkOption... options)
    {
        if (type == DosFileAttributeView.class) {
            return (V) new LinuxDosFileAttributeView(UnixPath.toUnixPath(obj),
                                                     Util.followLinks(options));
        }
        if (type == UserDefinedFileAttributeView.class) {
            return (V) new LinuxUserDefinedFileAttributeView(UnixPath.toUnixPath(obj),
                                                             Util.followLinks(options));
        }
        return super.getFileAttributeView(obj, type, options);
    }

    @Override
    public DynamicFileAttributeView getFileAttributeView(Path obj,
                                                         String name,
                                                         LinkOption... options)
    {
        if (name.equals("dos")) {
            return new LinuxDosFileAttributeView(UnixPath.toUnixPath(obj),
                                                 Util.followLinks(options));
        }
        if (name.equals("user")) {
            return new LinuxUserDefinedFileAttributeView(UnixPath.toUnixPath(obj),
                                                         Util.followLinks(options));
        }
        return super.getFileAttributeView(obj, name, options);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <A extends BasicFileAttributes> A readAttributes(Path file,
                                                            Class<A> type,
                                                            LinkOption... options)
        throws IOException
    {
        if (type == DosFileAttributes.class) {
            DosFileAttributeView view =
                getFileAttributeView(file, DosFileAttributeView.class, options);
            return (A) view.readAttributes();
        } else {
            return super.readAttributes(file, type, options);
        }
    }

    @Override
    FileTypeDetector getFileTypeDetector() {
        String userHome = StaticProperty.userHome();
        Path userMimeTypes = Path.of(userHome, ".mime.types");
        Path etcMimeTypes = Path.of("/etc/mime.types");

        return chain(new MimeTypesFileTypeDetector(userMimeTypes),
                     new MimeTypesFileTypeDetector(etcMimeTypes));
    }
}
