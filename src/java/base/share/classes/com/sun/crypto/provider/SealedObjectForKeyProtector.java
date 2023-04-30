/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.com.sun.crypto.provider;

import jdk.internal.access.SharedSecrets;

import java.io.*;
import java.security.*;
import javax.crypto.*;

/**
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 23/4/2023
 */

final class SealedObjectForKeyProtector extends SealedObject {

    @java.io.Serial
    static final long serialVersionUID = -3650226485480866989L;

    /**
     * The InputStreamFilter for a Key object inside this SealedObject. It can
     * be either provided as a {@link Security} property or a system property
     * (when provided as latter, it shadows the former). If the result of this
     * filter is {@link java.io.ObjectInputFilter.Status.UNDECIDED}, the system
     * level filter defined by jdk.serialFilter will be consulted. The value
     * of this property uses the same format of jdk.serialFilter.
     */
    private static final String KEY_SERIAL_FILTER = "jceks.key.serialFilter";

    SealedObjectForKeyProtector(Serializable object, Cipher c)
            throws IOException, IllegalBlockSizeException {
        super(object, c);
    }

    SealedObjectForKeyProtector(SealedObject so) {
        super(so);
    }

    AlgorithmParameters getParameters() {
        AlgorithmParameters params = null;
        if (super.encodedParams != null) {
            try {
                params = AlgorithmParameters.getInstance("PBE",
                    SunJCE.getInstance());
                params.init(super.encodedParams);
            } catch (NoSuchAlgorithmException nsae) {
                throw new RuntimeException(
                    "SunJCE provider is not configured properly");
            } catch (IOException io) {
                throw new RuntimeException("Parameter failure: "+
                    io.getMessage());
            }
        }
        return params;
    }

    @SuppressWarnings("removal")
    final Key getKey(Cipher c, int maxLength)
            throws IOException, ClassNotFoundException, IllegalBlockSizeException,
            BadPaddingException {

        try (ObjectInputStream ois = SharedSecrets.getJavaxCryptoSealedObjectAccess()
                .getExtObjectInputStream(this, c)) {
            AccessController.doPrivileged(
                    (PrivilegedAction<Void>) () -> {
                        ois.setObjectInputFilter(new DeserializationChecker(maxLength));
                        return null;
                    });
            try {
                @SuppressWarnings("unchecked")
                Key t = (Key) ois.readObject();
                return t;
            } catch (InvalidClassException ice) {
                String msg = ice.getMessage();
                if (msg.contains("REJECTED")) {
                    throw new IOException("Rejected by the"
                            + " jceks.key.serialFilter or jdk.serialFilter"
                            + " property", ice);
                } else {
                    throw ice;
                }
            }
        }
    }

    /**
     * The filter for the content of a SealedObjectForKeyProtector.
     *
     * First, the jceks.key.serialFilter will be consulted. If the result
     * is UNDECIDED, the system level jdk.serialFilter will be consulted.
     */
    private static class DeserializationChecker implements ObjectInputFilter {

        private static final ObjectInputFilter OWN_FILTER;

        static {
            @SuppressWarnings("removal")
            String prop = AccessController.doPrivileged(
                    (PrivilegedAction<String>) () -> {
                        String tmp = System.getProperty(KEY_SERIAL_FILTER);
                        if (tmp != null) {
                            return tmp;
                        } else {
                            return Security.getProperty(KEY_SERIAL_FILTER);
                        }
                    });
            OWN_FILTER = prop == null
                    ? null
                    : ObjectInputFilter.Config.createFilter(prop);
        }

        // Maximum possible length of anything inside
        private final int maxLength;

        private DeserializationChecker(int maxLength) {
            this.maxLength = maxLength;
        }

        @Override
        public ObjectInputFilter.Status checkInput(
                ObjectInputFilter.FilterInfo info) {

            if (info.arrayLength() > maxLength) {
                return Status.REJECTED;
            }

            if (info.serialClass() == Object.class) {
                return Status.UNDECIDED;
            }

            if (OWN_FILTER != null) {
                Status result = OWN_FILTER.checkInput(info);
                if (result != Status.UNDECIDED) {
                    return result;
                }
            }

            ObjectInputFilter defaultFilter =
                    ObjectInputFilter.Config.getSerialFilter();
            if (defaultFilter != null) {
                return defaultFilter.checkInput(info);
            }

            return Status.UNDECIDED;
        }
    }
}
