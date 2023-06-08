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
package java.base.share.classes.sun.net.ftp;

/**
 * Thrown to indicate that the FTP server reported an error.
 * For instance that the requested file doesn't exist or
 * that a command isn't supported.
 * <p>The specific error code can be retrieved with {@link #getReplyCode() }.</p>
 * @author      Jonathan Payne
 */
public class FtpProtocolException extends Exception {
    @java.io.Serial
    private static final long serialVersionUID = 5978077070276545054L;
    private final FtpReplyCode code;

    /**
     * Constructs a new {@code FtpProtocolException} from the
     * specified detail message. The reply code is set to unknown error.
     *
     * @param   detail   the detail message.
     */
    public FtpProtocolException(String detail) {
            super(detail);
            code = FtpReplyCode.UNKNOWN_ERROR;
    }

    /**
     * Constructs a new {@code FtpProtocolException} from the
     * specified response code and exception detail message
     *
     * @param   detail   the detail message.
     * @param   code The {@code FtpRelyCode} received from server.
     */
      public FtpProtocolException(String detail, FtpReplyCode code) {
        super(detail);
        this.code = code;
    }

    /**
     * Gets the reply code sent by the server that led to this exception
     * being thrown.
     *
     * @return The {@link FtpReplyCode} associated with that exception.
     */
    public FtpReplyCode getReplyCode() {
        return code;
    }
}
