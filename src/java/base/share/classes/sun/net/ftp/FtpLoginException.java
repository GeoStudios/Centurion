/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package sun.net.ftp;

import java.io.IOException;

/**
 * This exception is thrown when an error is encountered during an
 * FTP login operation.
 *
 * @author      Jonathan Payne
 */
public class FtpLoginException extends IOException {
    @java.io.Serial
    private static final long serialVersionUID = 2218162403237941536L;

    public FtpLoginException(String s) {
        super(s);
    }
}
