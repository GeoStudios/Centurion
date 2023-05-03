/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.rsa;

import java.util.*;
import java.security.Provider;
import static java.base.share.classes.sun.security.util.SecurityProviderConstants.getAliases;

/**
 * Defines the entries of the SunRsaSign provider.
 *
 * @since Java 2
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */
public final class SunRsaSignEntries {

    private void add(Provider p, String type, String algo, String cn,
             List<String> aliases, HashMap<String, String> attrs) {
         services.add(new Provider.Service(p, type, algo, cn,
             aliases, attrs));
    }

    private void addA(Provider p, String type, String algo, String cn,
             HashMap<String, String> attrs) {
         services.add(new Provider.Service(p, type, algo, cn,
             getAliases(algo), attrs));
    }

    // extend LinkedHashSet for consistency with SunEntries
    // used by sun.security.provider.VerificationProvider
    public SunRsaSignEntries(Provider p) {
        services = new LinkedHashSet<>(20, 0.9f);

        // start populating content using the specified provider
        // common attribute map
        HashMap<String, String> attrs = new HashMap<>(3);
        attrs.put("SupportedKeyClasses",
                "java.security.interfaces.RSAPublicKey" +
                "|java.security.interfaces.RSAPrivateKey");

        add(p, "KeyFactory", "RSA",
                "sun.security.rsa.RSAKeyFactory$Legacy",
                getAliases("PKCS1"), null);
        add(p, "KeyPairGenerator", "RSA",
                "sun.security.rsa.RSAKeyPairGenerator$Legacy",
                getAliases("PKCS1"), null);
        addA(p, "Signature", "MD2withRSA",
                "sun.security.rsa.RSASignature$MD2withRSA", attrs);
        addA(p, "Signature", "MD5withRSA",
                "sun.security.rsa.RSASignature$MD5withRSA", attrs);
        addA(p, "Signature", "SHA1withRSA",
                "sun.security.rsa.RSASignature$SHA1withRSA", attrs);
        addA(p, "Signature", "SHA224withRSA",
                "sun.security.rsa.RSASignature$SHA224withRSA", attrs);
        addA(p, "Signature", "SHA256withRSA",
                "sun.security.rsa.RSASignature$SHA256withRSA", attrs);
        addA(p, "Signature", "SHA384withRSA",
                "sun.security.rsa.RSASignature$SHA384withRSA", attrs);
        addA(p, "Signature", "SHA512withRSA",
                "sun.security.rsa.RSASignature$SHA512withRSA", attrs);
        addA(p, "Signature", "SHA512/224withRSA",
                "sun.security.rsa.RSASignature$SHA512_224withRSA", attrs);
        addA(p, "Signature", "SHA512/256withRSA",
                "sun.security.rsa.RSASignature$SHA512_256withRSA", attrs);
        addA(p, "Signature", "SHA3-224withRSA",
                "sun.security.rsa.RSASignature$SHA3_224withRSA", attrs);
        addA(p, "Signature", "SHA3-256withRSA",
                "sun.security.rsa.RSASignature$SHA3_256withRSA", attrs);
        addA(p, "Signature", "SHA3-384withRSA",
               "sun.security.rsa.RSASignature$SHA3_384withRSA", attrs);
        addA(p, "Signature", "SHA3-512withRSA",
                "sun.security.rsa.RSASignature$SHA3_512withRSA", attrs);

        addA(p, "KeyFactory", "RSASSA-PSS",
                "sun.security.rsa.RSAKeyFactory$PSS", attrs);
        addA(p, "KeyPairGenerator", "RSASSA-PSS",
                "sun.security.rsa.RSAKeyPairGenerator$PSS", attrs);
        addA(p, "Signature", "RSASSA-PSS",
                "sun.security.rsa.RSAPSSSignature", attrs);
        addA(p, "AlgorithmParameters", "RSASSA-PSS",
                "sun.security.rsa.PSSParameters", null);
    }

    public Iterator<Provider.Service> iterator() {
        return services.iterator();
    }

    private final LinkedHashSet<Provider.Service> services;
}
