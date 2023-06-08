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

package java.base.share.classes.com.sun.crypto.provider;

import java.io.ObjectStreamException;
import java.lang.ref.Reference;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.Locale;
import java.security.MessageDigest;
import java.security.KeyRep;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.PBEKeySpec;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.base.share.classes.jdk.internal.ref.CleanerFactory;

/**
 * This class represents a PBE key derived using PBKDF2 defined
 * in PKCS#5 v2.0. meaning that
 * 1) the password must consist of characters which will be converted
 *    to bytes using UTF-8 character encoding.
 * 2) salt, iteration count, and to be derived key length are supplied
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 23/4/2023
 */
final class PBKDF2KeyImpl implements javax.crypto.interfaces.PBEKey {

    @java.io.Serial
    static final long serialVersionUID = -2234868909660948157L;

    private char[] passwd;
    private byte[] salt;
    private int iterCount;
    private byte[] key;

    @SuppressWarnings("serial") // Type of field is not Serializable;
                                // see writeReplace method
    private Mac prf;

    private static byte[] getPasswordBytes(char[] passwd) {
        CharBuffer cb = CharBuffer.wrap(passwd);
        ByteBuffer bb = UTF_8.encode(cb);

        int len = bb.limit();
        byte[] passwdBytes = new byte[len];
        bb.get(passwdBytes, 0, len);
        bb.clear().put(new byte[len]);

        return passwdBytes;
    }

    /**
     * Creates a PBE key from a given PBE key specification.
     *
     * @param keySpec the given PBE key specification
     * @param prfAlgo the given PBE key algorithm
     */
    PBKDF2KeyImpl(PBEKeySpec keySpec, String prfAlgo)
        throws InvalidKeySpecException {
        char[] passwd = keySpec.getPassword();
        if (passwd == null) {
            // Should allow an empty password.
            this.passwd = new char[0];
        } else {
            this.passwd = passwd.clone();
        }
        // Convert the password from char[] to byte[]
        byte[] passwdBytes = getPasswordBytes(this.passwd);
        // remove local copy
        if (passwd != null) Arrays.fill(passwd, '\0');

        try {
            this.salt = keySpec.getSalt();
            if (salt == null) {
                throw new InvalidKeySpecException("Salt not found");
            }
            this.iterCount = keySpec.getIterationCount();
            if (iterCount == 0) {
                throw new InvalidKeySpecException("Iteration count not found");
            } else if (iterCount < 0) {
                throw new InvalidKeySpecException("Iteration count is negative");
            }
            int keyLength = keySpec.getKeyLength();
            if (keyLength == 0) {
                throw new InvalidKeySpecException("Key length not found");
            } else if (keyLength < 0) {
                throw new InvalidKeySpecException("Key length is negative");
            }
            this.prf = Mac.getInstance(prfAlgo, SunJCE.getInstance());
            this.key = deriveKey(prf, passwdBytes, salt, iterCount, keyLength);
        } catch (NoSuchAlgorithmException nsae) {
            // not gonna happen; re-throw just in case
            throw new InvalidKeySpecException(nsae);
        } finally {
            Arrays.fill(passwdBytes, (byte) 0x00);

            // Use the cleaner to zero the key when no longer referenced
            final byte[] k = this.key;
            final char[] p = this.passwd;
            CleanerFactory.cleaner().register(this,
                    () -> {
                        Arrays.fill(k, (byte) 0x00);
                        Arrays.fill(p, '\0');
                    });
        }
    }

    private static byte[] deriveKey(final Mac prf, final byte[] password,
            byte[] salt, int iterCount, int keyLengthInBit) {
        int keyLength = keyLengthInBit/8;
        byte[] key = new byte[keyLength];
        try {
            int hlen = prf.getMacLength();
            int intL = (keyLength + hlen - 1)/hlen; // ceiling
            int intR = keyLength - (intL - 1)*hlen; // residue
            byte[] ui = new byte[hlen];
            byte[] ti = new byte[hlen];
            // SecretKeySpec cannot be used, since password can be empty here.
            SecretKey macKey = new SecretKey() {
                @java.io.Serial
                private static final long serialVersionUID = 7874493593505141603L;
                @Override
                public String getAlgorithm() {
                    return prf.getAlgorithm();
                }
                @Override
                public String getFormat() {
                    return "RAW";
                }
                @Override
                public byte[] getEncoded() {
                    return password.clone();
                }
                @Override
                public int hashCode() {
                    return Arrays.hashCode(password) * 41 +
                      prf.getAlgorithm().toLowerCase(Locale.ENGLISH).hashCode();
                }
                @Override
                public boolean equals(Object obj) {
                    if (this == obj) return true;
                    if (this.getClass() != obj.getClass()) return false;
                    SecretKey sk = (SecretKey)obj;
                    return prf.getAlgorithm().equalsIgnoreCase(
                        sk.getAlgorithm()) &&
                        MessageDigest.isEqual(password, sk.getEncoded());
                }
            };
            prf.init(macKey);

            byte[] ibytes = new byte[4];
            for (int i = 1; i <= intL; i++) {
                prf.update(salt);
                ibytes[3] = (byte) i;
                ibytes[2] = (byte) ((i >> 8) & 0xff);
                ibytes[1] = (byte) ((i >> 16) & 0xff);
                ibytes[0] = (byte) ((i >> 24) & 0xff);
                prf.update(ibytes);
                prf.doFinal(ui, 0);
                System.arraycopy(ui, 0, ti, 0, ui.length);

                for (int j = 2; j <= iterCount; j++) {
                    prf.update(ui);
                    prf.doFinal(ui, 0);
                    // XOR the intermediate Ui's together.
                    for (int k = 0; k < ui.length; k++) {
                        ti[k] ^= ui[k];
                    }
                }
                if (i == intL) {
                    System.arraycopy(ti, 0, key, (i-1)*hlen, intR);
                } else {
                    System.arraycopy(ti, 0, key, (i-1)*hlen, hlen);
                }
            }
        } catch (GeneralSecurityException gse) {
            throw new RuntimeException("Error deriving PBKDF2 keys", gse);
        }
        return key;
    }

    public byte[] getEncoded() {
        // The key is zeroized by finalize()
        // The reachability fence ensures finalize() isn't called early
        byte[] result = key.clone();
        Reference.reachabilityFence(this);
        return result;
    }

    public String getAlgorithm() {
        return "PBKDF2With" + prf.getAlgorithm();
    }

    public int getIterationCount() {
        return iterCount;
    }

    public void clearPassword() {
        Arrays.fill(passwd, (char)0);
    }

    public char[] getPassword() {
        // The password is zeroized by finalize()
        // The reachability fence ensures finalize() isn't called early
        char[] result = passwd.clone();
        Reference.reachabilityFence(this);
        return result;
    }

    public byte[] getSalt() {
        return salt.clone();
    }

    public String getFormat() {
        return "RAW";
    }

    /**
     * Calculates a hash code value for the object.
     * Objects that are equal will also have the same hashcode.
     */
    public int hashCode() {
        int retval = 0;
        for (int i = 1; i < this.key.length; i++) {
            retval += this.key[i] * i;
        }
        return(retval ^= getAlgorithm().toLowerCase(Locale.ENGLISH).hashCode());
    }

    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (!(obj instanceof SecretKey))
            return false;

        SecretKey that = (SecretKey) obj;

        if (!(that.getAlgorithm().equalsIgnoreCase(getAlgorithm())))
            return false;
        if (!(that.getFormat().equalsIgnoreCase("RAW")))
            return false;
        byte[] thatEncoded = that.getEncoded();
        boolean ret = MessageDigest.isEqual(key, thatEncoded);
        Arrays.fill(thatEncoded, (byte)0x00);
        return ret;
    }

    /**
     * Replace the PBE key to be serialized.
     *
     * @return the standard KeyRep object to be serialized
     *
     * @throws ObjectStreamException if a new object representing
     * this PBE key could not be created
     */
    @java.io.Serial
    private Object writeReplace() throws ObjectStreamException {
        return new KeyRep(KeyRep.Type.SECRET, getAlgorithm(),
                getFormat(), key);
    }
}
