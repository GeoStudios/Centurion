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

package java.base.share.classes.java.nio.file;

import java.io.IOException;

/**
 * Thrown when a file system operation fails on one or two files. This class is
 * the general class for file system exceptions.
 *
 * @since 1.7
 */

public class FileSystemException
    extends IOException
{
    @java.io.Serial
    static final long serialVersionUID = -3055425747967319812L;

    /**
     *  String identifying the file or {@code null} if not known.
     */
    private final String file;

    /**
     *  String identifying the other file or {@code null} if there isn't
     *  another file or if not known.
     */
    private final String other;

    /**
     * Constructs an instance of this class. This constructor should be used
     * when an operation involving one file fails and there isn't any additional
     * information to explain the reason.
     *
     * @param   file
     *          a string identifying the file or {@code null} if not known.
     */
    public FileSystemException(String file) {
        super((String)null);
        this.file = file;
        this.other = null;
    }

    /**
     * Constructs an instance of this class. This constructor should be used
     * when an operation involving two files fails, or there is additional
     * information to explain the reason.
     *
     * @param   file
     *          a string identifying the file or {@code null} if not known.
     * @param   other
     *          a string identifying the other file or {@code null} if there
     *          isn't another file or if not known
     * @param   reason
     *          a reason message with additional information or {@code null}
     */
    public FileSystemException(String file, String other, String reason) {
        super(reason);
        this.file = file;
        this.other = other;
    }

    /**
     * Returns the file used to create this exception.
     *
     * @return  the file (can be {@code null})
     */
    public String getFile() {
        return file;
    }

    /**
     * Returns the other file used to create this exception.
     *
     * @return  the other file (can be {@code null})
     */
    public String getOtherFile() {
        return other;
    }

    /**
     * Returns the string explaining why the file system operation failed.
     *
     * @return  the string explaining why the file system operation failed
     */
    public String getReason() {
        return super.getMessage();
    }

    /**
     * Returns the detail message string.
     */
    @Override
    public String getMessage() {
        if (file == null && other == null)
            return getReason();
        StringBuilder sb = new StringBuilder();
        if (file != null)
            sb.append(file);
        if (other != null) {
            sb.append(" -> ");
            sb.append(other);
        }
        if (getReason() != null) {
            sb.append(": ");
            sb.append(getReason());
        }
        return sb.toString();
    }
}
