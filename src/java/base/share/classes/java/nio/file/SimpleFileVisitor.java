/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.nio.file;

import java.base.share.classes.java.nio.file.attribute.BasicFileAttributes;
import java.io.IOException;
import java.util.Objects;

/**
 * A simple visitor of files with default behavior to visit all files and to
 * re-throw I/O errors.
 *
 * <p> Methods in this class may be overridden subject to their general contract.
 *
 * @param   <T>     The type of reference to the files
 *
 * @since 1.7
 */

public class SimpleFileVisitor<T> implements FileVisitor<T> {
    /**
     * Initializes a new instance of this class.
     */
    protected SimpleFileVisitor() {
    }

    /**
     * Invoked for a directory before entries in the directory are visited.
     *
     * <p> Unless overridden, this method returns {@link FileVisitResult#CONTINUE
     * CONTINUE}.
     */
    @Override
    public FileVisitResult preVisitDirectory(T dir, BasicFileAttributes attrs)
        throws IOException
    {
        Objects.requireNonNull(dir);
        Objects.requireNonNull(attrs);
        return FileVisitResult.CONTINUE;
    }

    /**
     * Invoked for a file in a directory.
     *
     * <p> Unless overridden, this method returns {@link FileVisitResult#CONTINUE
     * CONTINUE}.
     */
    @Override
    public FileVisitResult visitFile(T file, BasicFileAttributes attrs)
        throws IOException
    {
        Objects.requireNonNull(file);
        Objects.requireNonNull(attrs);
        return FileVisitResult.CONTINUE;
    }

    /**
     * Invoked for a file that could not be visited.
     *
     * <p> Unless overridden, this method re-throws the I/O exception that prevented
     * the file from being visited.
     */
    @Override
    public FileVisitResult visitFileFailed(T file, IOException exc)
        throws IOException
    {
        Objects.requireNonNull(file);
        throw exc;
    }

    /**
     * Invoked for a directory after entries in the directory, and all of their
     * descendants, have been visited.
     *
     * <p> Unless overridden, this method returns {@link FileVisitResult#CONTINUE
     * CONTINUE} if the directory iteration completes without an I/O exception;
     * otherwise this method re-throws the I/O exception that caused the iteration
     * of the directory to terminate prematurely.
     */
    @Override
    public FileVisitResult postVisitDirectory(T dir, IOException exc)
        throws IOException
    {
        Objects.requireNonNull(dir);
        if (exc != null)
            throw exc;
        return FileVisitResult.CONTINUE;
    }
}
