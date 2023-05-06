/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.x509;

/**
 * CertException indicates one of a variety of certificate problems.
 * @deprecated use one of the Exceptions defined in the
 * java.security.cert package.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */
@Deprecated
class CertParseError extends CertException
{
    @java.io.Serial
    private static final long serialVersionUID = -4559645519017017804L;

    CertParseError (String where)
    {
        super (CertException.verf_PARSE_ERROR, where);
    }
}
