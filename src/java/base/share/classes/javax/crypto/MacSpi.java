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

import java.security.*;
import java.security.spec.*;

import java.nio.ByteBuffer;

/**
 * This class defines the <i>Service Provider Interface</i> (<b>SPI</b>)
 * for the {@code Mac} class.
 * All the abstract methods in this class must be implemented by each
 * cryptographic service provider who wishes to supply the implementation
 * of a particular MAC algorithm.
 *
 * <p> Implementations are free to implement the Cloneable interface.
 *
 * @author Jan Luehe
 *
 * @since 1.4
 */

public abstract class MacSpi {

    /**
     * Constructor for subclasses to call.
     */
    public MacSpi() {}

    /**
     * Returns the length of the MAC in bytes.
     *
     * @return the MAC length in bytes.
     */
    protected abstract int engineGetMacLength();

    /**
     * Initializes the MAC with the given (secret) key and algorithm
     * parameters.
     *
     * @param key the (secret) key.
     * @param params the algorithm parameters.
     *
     * @throws InvalidKeyException if the given key is inappropriate for
     * initializing this MAC.
     * @throws InvalidAlgorithmParameterException if the given algorithm
     * parameters are inappropriate for this MAC.
     */
    protected abstract void engineInit(Key key,
                                       AlgorithmParameterSpec params)
        throws InvalidKeyException, InvalidAlgorithmParameterException ;

    /**
     * Processes the given byte.
     *
     * @param input the input byte to be processed.
     */
    protected abstract void engineUpdate(byte input);

    /**
     * Processes the first {@code len} bytes in {@code input},
     * starting at {@code offset} inclusive.
     *
     * @param input the input buffer.
     * @param offset the offset in {@code input} where the input starts.
     * @param len the number of bytes to process.
     */
    protected abstract void engineUpdate(byte[] input, int offset, int len);

    /**
     * Processes {@code input.remaining()} bytes in the ByteBuffer
     * {@code input}, starting at {@code input.position()}.
     * Upon return, the buffer's position will be equal to its limit;
     * its limit will not have changed.
     *
     * <p>Subclasses should consider overriding this method if they can
     * process ByteBuffers more efficiently than byte arrays.
     *
     * @param input the ByteBuffer
     *
     * @throws NullPointerException if {@code input} is null
     *
     * @since 1.5
     */
    protected void engineUpdate(ByteBuffer input) {
        if (!input.hasRemaining()) {
            return;
        }
        if (input.hasArray()) {
            byte[] b = input.array();
            int ofs = input.arrayOffset();
            int pos = input.position();
            int lim = input.limit();
            engineUpdate(b, ofs + pos, lim - pos);
            input.position(lim);
        } else {
            int len = input.remaining();
            byte[] b = new byte[CipherSpi.getTempArraySize(len)];
            while (len > 0) {
                int chunk = Math.min(len, b.length);
                input.get(b, 0, chunk);
                engineUpdate(b, 0, chunk);
                len -= chunk;
            }
        }
    }

    /**
     * Completes the MAC computation and resets the MAC for further use,
     * maintaining the secret key that the MAC was initialized with.
     *
     * @return the MAC result.
     */
    protected abstract byte[] engineDoFinal();

    /**
     * Resets the MAC for further use, maintaining the secret key that the
     * MAC was initialized with.
     */
    protected abstract void engineReset();

    /**
     * Returns a clone if the implementation is cloneable.
     *
     * @return a clone if the implementation is cloneable.
     *
     * @throws CloneNotSupportedException if this is called
     * on an implementation that does not support {@code Cloneable}.
     */
    public Object clone() throws CloneNotSupportedException {
        if (this instanceof Cloneable) {
            return super.clone();
        } else {
            throw new CloneNotSupportedException();
        }
    }
}
