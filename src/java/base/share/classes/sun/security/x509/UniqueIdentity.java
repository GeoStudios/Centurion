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

import sun.security.util.BitArray;
import sun.security.util.DerInputStream;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

/**
 * This class defines the UniqueIdentity class used by certificates.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */
public class UniqueIdentity {
    // Private data members
    private final BitArray    id;

    /**
     * The default constructor for this class.
     *
     * @param id the byte array containing the unique identifier.
     */
    public UniqueIdentity(BitArray id) {
        this.id = id;
    }

    /**
     * The default constructor for this class.
     *
     * @param id the byte array containing the unique identifier.
     */
    public UniqueIdentity(byte[] id) {
        this.id = new BitArray(id.length*8, id);
    }

    /**
     * Create the object, decoding the values from the passed DER stream.
     *
     * @param in the DerInputStream to read the UniqueIdentity from.
     * @exception IOException on decoding errors.
     */
    public UniqueIdentity(DerInputStream in) throws IOException {
        DerValue derVal = in.getDerValue();
        id = derVal.getUnalignedBitString(true);
    }

    /**
     * Create the object, decoding the values from the passed DER stream.
     *
     * @param derVal the DerValue decoded from the stream.
     * @exception IOException on decoding errors.
     */
    public UniqueIdentity(DerValue derVal) throws IOException {
        id = derVal.getUnalignedBitString(true);
    }

    /**
     * Return the UniqueIdentity as a printable string.
     */
    public String toString() {
        return ("UniqueIdentity:" + id.toString() + "\n");
    }

    /**
     * Encode the UniqueIdentity in DER form to the stream.
     *
     * @param out the DerOutputStream to marshal the contents to.
     * @param tag encode it under the following tag.
     */
    public void encode(DerOutputStream out, byte tag) {
        byte[] bytes = id.toByteArray();
        int excessBits = bytes.length*8 - id.length();

        out.write(tag);
        out.putLength(bytes.length + 1);

        out.write(excessBits);
        out.writeBytes(bytes);
    }

    /**
     * Return the unique id.
     */
    public boolean[] getId() {
        if (id == null) return null;

        return id.toBooleanArray();
    }
}
