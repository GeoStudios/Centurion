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

package java.base.share.classes.javax.crypto;

import jdk.internal.access.SharedSecrets;

import java.io.*;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.InvalidKeyException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Arrays;

/**
 * This class enables a programmer to create an object and protect its
 * confidentiality with a cryptographic algorithm.
 *
 * <p> Given any {@code Serializable} object, one can create a
 * {@code SealedObject} that encapsulates the original object, in serialized
 * format (i.e., a "deep copy"), and seals (encrypts) its serialized contents,
 * using a cryptographic algorithm such as AES, to protect its
 * confidentiality.  The encrypted content can later be decrypted (with
 * the corresponding algorithm using the correct decryption key) and
 * de-serialized, yielding the original object.
 *
 * <p> Note that the {@code Cipher} object must be fully initialized with
 * the correct algorithm, key, padding scheme, etc., before being applied
 * to a {@code SealedObject}.
 *
 * <p> The original object that was sealed can be recovered in two different
 * ways:
 *
 * <ul>
 *
 * <li>by using the {@link #getObject(java.base.share.classes.javax.crypto.Cipher) getObject}
 * method that takes a {@code Cipher} object.
 *
 * <p> This method requires a fully initialized {@code Cipher} object,
 * initialized with the
 * exact same algorithm, key, padding scheme, etc., that were used to seal the
 * object.
 *
 * <p> This approach has the advantage that the party who unseals the
 * sealed object does not require knowledge of the decryption key. For example,
 * after one party has initialized the cipher object with the required
 * decryption key, it could hand over the cipher object to
 * another party who then unseals the sealed object.
 *
 * <li>by using one of the
 * {@link #getObject(java.security.Key) getObject} methods
 * that take a {@code Key} object.
 *
 * <p> In this approach, the {@code getObject} method creates a cipher
 * object for the appropriate decryption algorithm and initializes it with the
 * given decryption key and the algorithm parameters (if any) that were stored
 * in the sealed object.
 *
 * <p> This approach has the advantage that the party who
 * unseals the object does not need to keep track of the parameters (e.g., an
 * IV) that were used to seal the object.
 *
 * </ul>
 *
 * @author Li Gong
 * @author Jan Luehe
 * @see Cipher
 * @since 1.4
 */

public class SealedObject implements Serializable {

    @java.io.Serial
    static final long serialVersionUID = 4482838265551344752L;

    /**
     * The serialized object contents in encrypted format.
     *
     * @serial
     */
    private byte[] encryptedContent = null;

    /**
     * The algorithm that was used to seal this object.
     *
     * @serial
     */
    private final String sealAlg;

    /**
     * The algorithm of the parameters used.
     *
     * @serial
     */
    private String paramsAlg = null;

    /**
     * The cryptographic parameters used by the sealing {@code Cipher} object,
     * encoded in the default format.
     * <p>
     * That is, {@code Cipher.getParameters().getEncoded()}.
     *
     * @serial
     */
    protected byte[] encodedParams = null;

    /**
     * Constructs a {@code SealedObject} from any {@code Serializable} object.
     *
     * <p>The given object is serialized, and its serialized contents are
     * encrypted using the given {@code Cipher} object, which must be fully
     * initialized.
     *
     * <p>Any algorithm parameters that may be used in the encryption
     * operation are stored inside the new {@code SealedObject}.
     *
     * @param object the object to be sealed; can be {@code null}.
     * @param c the cipher used to seal the object.
     *
     * @exception NullPointerException if the given cipher is {@code null}.
     * @exception IOException if an error occurs during serialization
     * @exception IllegalBlockSizeException if the given cipher is a block
     * cipher, no padding has been requested, and the total input length
     * (i.e., the length of the serialized object contents) is not a multiple
     * of the cipher's block size
     */
    public SealedObject(Serializable object, Cipher c) throws IOException,
        IllegalBlockSizeException
    {
        /*
         * Serialize the object
         */

        // creating a stream pipe-line, from a to b
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ObjectOutput a = new ObjectOutputStream(b);
        byte[] content;
        try {
            // write and flush the object content to byte array
            a.writeObject(object);
            a.flush();
            content = b.toByteArray();
        } finally {
            a.close();
        }

        /*
         * Seal the object
         */
        try {
            this.encryptedContent = c.doFinal(content);
        } catch (BadPaddingException ex) {
            // if sealing is encryption only
            // Should never happen??
        } finally {
            Arrays.fill(content, (byte)0);
        }

        // Save the parameters
        if (c.getParameters() != null) {
            this.encodedParams = c.getParameters().getEncoded();
            this.paramsAlg = c.getParameters().getAlgorithm();
        }

        // Save the encryption algorithm
        this.sealAlg = c.getAlgorithm();
    }

    /**
     * Constructs a {@code SealedObject} object from the passed-in
     * {@code SealedObject}.
     *
     * @param so a {@code SealedObject} object
     * @exception NullPointerException if the given sealed object
     * is {@code null}.
     */
    protected SealedObject(SealedObject so) {
        this.encryptedContent = so.encryptedContent.clone();
        this.sealAlg = so.sealAlg;
        this.paramsAlg = so.paramsAlg;
        if (so.encodedParams != null) {
            this.encodedParams = so.encodedParams.clone();
        } else {
            this.encodedParams = null;
        }
    }

    /**
     * Returns the algorithm that was used to seal this object.
     *
     * @return the algorithm that was used to seal this object.
     */
    public final String getAlgorithm() {
        return this.sealAlg;
    }

    /**
     * Retrieves the original (encapsulated) object.
     *
     * <p>This method creates a cipher for the algorithm that had been used in
     * the sealing operation.
     * If the default provider package provides an implementation of that
     * algorithm, a {@code Cipher} object containing that
     * implementation is used.
     * If the algorithm is not available in the default package, other
     * packages are searched.
     * The {@code Cipher} object is initialized for decryption,
     * using the given
     * {@code key} and the parameters (if any) that had been used in the
     * sealing operation.
     *
     * <p>The encapsulated object is unsealed and de-serialized, before it is
     * returned.
     *
     * @param key the key used to unseal the object.
     *
     * @return the original object.
     *
     * @exception IOException if an error occurs during de-serialization.
     * @exception ClassNotFoundException if an error occurs during
     * de-serialization.
     * @exception NoSuchAlgorithmException if the algorithm to unseal the
     * object is not available.
     * @exception InvalidKeyException if the given key cannot be used to unseal
     * the object (e.g., it has the wrong algorithm).
     * @exception NullPointerException if {@code key} is null.
     */
    public final Object getObject(Key key)
        throws IOException, ClassNotFoundException, NoSuchAlgorithmException,
            InvalidKeyException
    {
        if (key == null) {
            throw new NullPointerException("key is null");
        }

        try {
            return unseal(key, null);
        } catch (NoSuchProviderException nspe) {
            // we've already caught NoSuchProviderException's and converted
            // them into NoSuchAlgorithmException's with details about
            // the failing algorithm
            throw new NoSuchAlgorithmException("algorithm not found");
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new InvalidKeyException(e.getMessage());
        }
    }

    /**
     * Retrieves the original (encapsulated) object.
     *
     * <p>The encapsulated object is unsealed (using the given
     * {@code Cipher} object,
     * assuming that the {@code Cipher} object is already properly initialized)
     * and de-serialized, before it is returned.
     *
     * @param c the cipher used to unseal the object
     *
     * @return the original object.
     *
     * @exception NullPointerException if the given cipher is {@code null}.
     * @exception IOException if an error occurs during de-serialization
     * @exception ClassNotFoundException if an error occurs during
     * de-serialization
     * @exception IllegalBlockSizeException if the given cipher is a block
     * cipher, no padding has been requested, and the total input length is
     * not a multiple of the cipher's block size
     * @exception BadPaddingException if the given cipher has been
     * initialized for decryption, and padding has been specified, but
     * the input data does not have proper expected padding bytes
     */
    public final Object getObject(Cipher c)
        throws IOException, ClassNotFoundException, IllegalBlockSizeException,
            BadPaddingException
    {
        try (ObjectInput a = getExtObjectInputStream(c)) {
            return a.readObject();
        }
    }

    /**
     * Retrieves the original (encapsulated) object.
     *
     * <p>This method creates a cipher for the algorithm that had been used in
     * the sealing operation, using an implementation of that algorithm from
     * the given {@code provider}.
     * The {@code Cipher} object is initialized for decryption,
     * using the given
     * {@code key} and the parameters (if any) that had been used in the
     * sealing operation.
     *
     * <p>The encapsulated object is unsealed and de-serialized, before it is
     * returned.
     *
     * @param key the key used to unseal the object.
     * @param provider the name of the provider of the algorithm to unseal
     * the object.
     *
     * @return the original object.
     *
     * @exception IllegalArgumentException if the given provider is {@code null}
     * or empty.
     * @exception IOException if an error occurs during de-serialization.
     * @exception ClassNotFoundException if an error occurs during
     * de-serialization.
     * @exception NoSuchAlgorithmException if the algorithm to unseal the
     * object is not available.
     * @exception NoSuchProviderException if the given provider is not
     * configured.
     * @exception InvalidKeyException if the given key cannot be used to unseal
     * the object (e.g., it has the wrong algorithm).
     * @exception NullPointerException if {@code key} is null.
     */
    public final Object getObject(Key key, String provider)
        throws IOException, ClassNotFoundException, NoSuchAlgorithmException,
            NoSuchProviderException, InvalidKeyException
    {
        if (key == null) {
            throw new NullPointerException("key is null");
        }
        if (provider == null || provider.isEmpty()) {
            throw new IllegalArgumentException("missing provider");
        }

        try {
            return unseal(key, provider);
        } catch (IllegalBlockSizeException | BadPaddingException ex) {
            throw new InvalidKeyException(ex.getMessage());
        }
    }


    private Object unseal(Key key, String provider)
        throws IOException, ClassNotFoundException, NoSuchAlgorithmException,
            NoSuchProviderException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException
    {
        /*
         * Create the parameter object.
         */
        AlgorithmParameters params = null;
        if (this.encodedParams != null) {
            try {
                if (provider != null)
                    params = AlgorithmParameters.getInstance(this.paramsAlg,
                                                             provider);
                else
                    params = AlgorithmParameters.getInstance(this.paramsAlg);

            } catch (NoSuchProviderException nspe) {
                if (provider == null) {
                    throw new NoSuchAlgorithmException(this.paramsAlg
                                                       + " not found");
                } else {
                    throw new NoSuchProviderException(nspe.getMessage());
                }
            }
            params.init(this.encodedParams);
        }

        /*
         * Create and initialize the cipher.
         */
        Cipher c;
        try {
            if (provider != null)
                c = Cipher.getInstance(this.sealAlg, provider);
            else
                c = Cipher.getInstance(this.sealAlg);
        } catch (NoSuchPaddingException nspe) {
            throw new NoSuchAlgorithmException("Padding that was used in "
                                               + "sealing operation not "
                                               + "available");
        } catch (NoSuchProviderException nspe) {
            if (provider == null) {
                throw new NoSuchAlgorithmException(this.sealAlg+" not found");
            } else {
                throw new NoSuchProviderException(nspe.getMessage());
            }
        }

        try {
            if (params != null)
                c.init(Cipher.DECRYPT_MODE, key, params);
            else
                c.init(Cipher.DECRYPT_MODE, key);
        } catch (InvalidAlgorithmParameterException iape) {
            // this should never happen, because we use the exact same
            // parameters that were used in the sealing operation
            throw new RuntimeException(iape.getMessage());
        }

        try (ObjectInput a = getExtObjectInputStream(c)) {
            return a.readObject();
        }
    }

    /**
     * Restores the state of the {@code SealedObject} from a stream.
     *
     * @param s the object input stream.
     * @throws IOException if an I/O error occurs
     * @throws ClassNotFoundException if a serialized class cannot be loaded
     * @throws NullPointerException if s is {@code null}
     */
    @java.io.Serial
    private void readObject(java.io.ObjectInputStream s)
        throws java.io.IOException, ClassNotFoundException
    {
        s.defaultReadObject();
        if (encryptedContent != null)
            encryptedContent = encryptedContent.clone();
        if (encodedParams != null)
            encodedParams = encodedParams.clone();
    }

    // This method is also called inside SealedObjectForKeyProtector.java.
    private ObjectInputStream getExtObjectInputStream(Cipher c)
            throws BadPaddingException, IllegalBlockSizeException, IOException {

        byte[] content = c.doFinal(this.encryptedContent);
        ByteArrayInputStream b = new ByteArrayInputStream(content);
        return new extObjectInputStream(b);
    }

    static {
        SharedSecrets.setJavaxCryptoSealedObjectAccess(SealedObject::getExtObjectInputStream);
    }
}

final class extObjectInputStream extends ObjectInputStream {
    extObjectInputStream(InputStream in)
        throws IOException, StreamCorruptedException {
        super(in);
    }

    protected Class<?> resolveClass(ObjectStreamClass v)
        throws IOException, ClassNotFoundException
    {

        try {
            /*
             * Calling the super.resolveClass() first
             * will let us pick up bug fixes in the super
             * class (e.g., 4171142).
             */
            return super.resolveClass(v);
        } catch (ClassNotFoundException cnfe) {
            /*
             * This is a workaround for bug 4224921.
             */
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            if (loader == null) {
                loader = ClassLoader.getSystemClassLoader();
                if (loader == null) {
                    throw new ClassNotFoundException(v.getName());
                }
            }

            return Class.forName(v.getName(), false, loader);
        }
    }
}
