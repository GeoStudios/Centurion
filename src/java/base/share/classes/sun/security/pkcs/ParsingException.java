/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.pkcs;

import java.io.IOException;

/**
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */

public class ParsingException extends IOException {

    @java.io.Serial
    private static final long serialVersionUID = -6316569918966181883L;

    public ParsingException() {
        super();
    }

    public ParsingException(String s) {
        super(s);
    }
}
