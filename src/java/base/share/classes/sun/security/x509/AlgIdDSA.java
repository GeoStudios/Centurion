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

package java.base.share.classes.sun.security.x509;

import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.DSAParams;

import sun.security.util.*;


/**
 * This class identifies DSS/DSA Algorithm variants, which are distinguished
 * by using different algorithm parameters <em>P, Q, G</em>.  It uses the
 * NIST/IETF standard DER encoding.  These are used to implement the Digital
 * Signature Standard (DSS), FIPS 186.
 *
 * <P><em><b>NOTE:</b></em>  DSS/DSA Algorithm IDs may be created without these
 * parameters.  Use of DSS/DSA in modes where parameters are
 * either implicit (e.g. a default applicable to a site or a larger scope),
 * or are derived from some Certificate Authority's DSS certificate, is
 * not supported directly.  The application is responsible for creating a key
 * containing the required parameters prior to using the key in cryptographic
 * operations.  The following is an example of how this may be done assuming
 * that we have a certificate called <code>currentCert</code> which doesn't
 * contain DSS/DSA parameters and we need to derive DSS/DSA parameters
 * from a CA's certificate called <code>caCert</code>.
 *
 * <pre>{@code
 * // key containing parameters to use
 * DSAPublicKey cAKey = (DSAPublicKey)(caCert.getPublicKey());
 * // key without parameters
 * DSAPublicKey nullParamsKey = (DSAPublicKey)(currentCert.getPublicKey());
 *
 * DSAParams cAKeyParams = cAKey.getParams();
 * KeyFactory kf = KeyFactory.getInstance("DSA");
 * DSAPublicKeySpec ks = new DSAPublicKeySpec(nullParamsKey.getY(),
 *                                            cAKeyParams.getP(),
 *                                            cAKeyParams.getQ(),
 *                                            cAKeyParams.getG());
 * DSAPublicKey usableKey = kf.generatePublic(ks);
 * }</pre>
 *
 * @see java.security.interfaces.DSAParams
 * @see java.security.interfaces.DSAPublicKey
 * @see java.security.KeyFactory
 * @see java.security.spec.DSAPublicKeySpec
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */
public final
class AlgIdDSA extends AlgorithmId implements DSAParams
{

    @java.io.Serial
    private static final long serialVersionUID = 3437177836797504046L;

    /*
     * The three unsigned integer parameters.
     */
    private BigInteger  p , q, g;

    /** Returns the DSS/DSA parameter "P" */
    public BigInteger   getP () { return p; }

    /** Returns the DSS/DSA parameter "Q" */
    public BigInteger   getQ () { return q; }

    /** Returns the DSS/DSA parameter "G" */
    public BigInteger   getG () { return g; }

    /**
     * Default constructor.  The OID and parameters must be
     * deserialized before this algorithm ID is used.
     */
    @Deprecated
    public AlgIdDSA () {}

    /**
     * Constructs a DSS/DSA Algorithm ID from numeric parameters.
     * If all three are null, then the parameters portion of the algorithm id
     * is set to null.  See note in header regarding use.
     *
     * @param p the DSS/DSA parameter "P"
     * @param q the DSS/DSA parameter "Q"
     * @param g the DSS/DSA parameter "G"
     */
    public AlgIdDSA (BigInteger p, BigInteger q, BigInteger g) {
        super (DSA_oid);

        if (p != null || q != null || g != null) {
            if (p == null || q == null || g == null)
                throw new ProviderException("Invalid parameters for DSS/DSA" +
                                            " Algorithm ID");
            try {
                this.p = p;
                this.q = q;
                this.g = g;
                initializeParams ();

            } catch (IOException e) {
                /* this should not happen */
                throw new ProviderException ("Construct DSS/DSA Algorithm ID");
            }
        }
    }

    /**
     * Returns "DSA", indicating the Digital Signature Algorithm (DSA) as
     * defined by the Digital Signature Standard (DSS), FIPS 186.
     */
    public String getName ()
        { return "DSA"; }


    /*
     * For algorithm IDs which haven't been created from a DER encoded
     * value, "params" must be created.
     */
    private void initializeParams () throws IOException {
        DerOutputStream out = new DerOutputStream();
        out.putInteger(p);
        out.putInteger(q);
        out.putInteger(g);
        DerOutputStream result = new DerOutputStream();
        result.write(DerValue.tag_Sequence, out);
        encodedParams = result.toByteArray();
    }

    /**
     * Parses algorithm parameters P, Q, and G.  They're found
     * in the "params" member, which never needs to be changed.
     */
    protected void decodeParams () throws IOException {
        if (encodedParams == null) {
            throw new IOException("DSA alg params are null");
        }

        DerValue params = new DerValue(encodedParams);
        if (params.tag != DerValue.tag_Sequence) {
            throw new IOException("DSA alg parsing error");
        }

        params.data.reset ();

        this.p = params.data.getBigInteger();
        this.q = params.data.getBigInteger();
        this.g = params.data.getBigInteger();

        if (params.data.available () != 0)
            throw new IOException ("AlgIdDSA params, extra="+
                                   params.data.available ());
    }


    /*
     * Returns a formatted string describing the parameters.
     */
    public String toString () {
        return paramsToString();
    }

    /*
     * Returns a string describing the parameters.
     */
    protected String paramsToString () {
        if (encodedParams == null) {
            return " null\n";
        } else {
            return "\n    p:\n" + Debug.toHexString(p) +
                    "\n    q:\n" + Debug.toHexString(q) +
                    "\n    g:\n" + Debug.toHexString(g) +
                    "\n";
        }
    }
}
