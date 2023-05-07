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

package java.base.share.classes.java.security;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.base.share.classes.java.security.cert.CertPath;
import java.base.share.classes.java.security.cert.Certificate;
import java.util.Date;
import java.util.List;

/**
 * This class encapsulates information about a signed timestamp.
 * It is immutable.
 * It includes the timestamp's date and time as well as information about the
 * Timestamping Authority (TSA) which generated and signed the timestamp.
 *
 * @since 1.5
 * @author Vincent Ryan
 */

public final class Timestamp implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = -5502683707821851294L;

    /**
     * The timestamp's date and time
     *
     * @serial
     */
    private Date timestamp;

    /**
     * The TSA's certificate path.
     *
     * @serial
     */
    private final CertPath signerCertPath;

    /*
     * Hash code for this timestamp.
     */
    private transient int myhash = -1;

    /**
     * Constructs a {@code Timestamp}.
     *
     * @param timestamp is the timestamp's date and time. It must not be
     * {@code null}.
     * @param signerCertPath is the TSA's certificate path. It must not be
     * {@code null}.
     * @throws NullPointerException if timestamp or signerCertPath is
     * {@code null}.
     */
    public Timestamp(Date timestamp, CertPath signerCertPath) {
        if (timestamp == null || signerCertPath == null) {
            throw new NullPointerException();
        }
        this.timestamp = new Date(timestamp.getTime()); // clone
        this.signerCertPath = signerCertPath;
    }

    /**
     * Returns the date and time when the {@code Timestamp} was generated.
     *
     * @return The timestamp's date and time.
     */
    public Date getTimestamp() {
        return new Date(timestamp.getTime()); // clone
    }

    /**
     * Returns the certificate path for the Timestamping Authority.
     *
     * @return The TSA's certificate path.
     */
    public CertPath getSignerCertPath() {
        return signerCertPath;
    }

    /**
     * Returns the hash code value for this {@code Timestamp}.
     * The hash code is generated using the date and time of the
     * {@code Timestamp} and the TSA's certificate path.
     *
     * @return a hash code value for this {@code Timestamp}.
     */
    public int hashCode() {
        if (myhash == -1) {
            myhash = timestamp.hashCode() + signerCertPath.hashCode();
        }
        return myhash;
    }

    /**
     * Tests for equality between the specified object and this
     * {@code Timestamp}. Two timestamps are considered equal if the date and
     * time of their timestamp's and their signer's certificate paths are equal.
     *
     * @param obj the object to test for equality with this {@code Timestamp}.
     *
     * @return {@code true} if the timestamps are considered equal,
     * {@code false} otherwise.
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj instanceof Timestamp other
                && (timestamp.equals(other.getTimestamp()) &&
                signerCertPath.equals(other.getSignerCertPath()));
    }

    /**
     * Returns a string describing this {@code Timestamp}.
     *
     * @return A string comprising the date and time of the {@code Timestamp}
     *         and its signer's certificate.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append("timestamp: " + timestamp);
        List<? extends Certificate> certs = signerCertPath.getCertificates();
        if (!certs.isEmpty()) {
            sb.append("TSA: " + certs.get(0));
        } else {
            sb.append("TSA: <empty>");
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * Restores the state of this object from the stream, and explicitly
     * resets hash code value to -1.
     *
     * @param  ois the {@code ObjectInputStream} from which data is read
     * @throws IOException if an I/O error occurs
     * @throws ClassNotFoundException if a serialized class cannot be loaded
     */
    @java.io.Serial
    private void readObject(ObjectInputStream ois)
        throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        myhash = -1;
        timestamp = new Date(timestamp.getTime());
    }
}
