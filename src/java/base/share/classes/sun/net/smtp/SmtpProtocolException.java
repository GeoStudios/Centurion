/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.net.smtp;

import java.io.IOException;

/**
 * This exception is thrown when unexpected results are returned during
 * an SMTP session.
 */
public class SmtpProtocolException extends IOException {
    @java.io.Serial
    private static final long serialVersionUID = -7547136771133814908L;

    SmtpProtocolException(String s) {
        super(s);
    }
}
