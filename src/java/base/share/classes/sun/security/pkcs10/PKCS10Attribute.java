/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.pkcs10;

import java.io.IOException;

import java.base.share.classes.sun.security.pkcs.PKCS9Attribute;
import java.base.share.classes.sun.security.util.*;

/**
 * Represent a PKCS#10 Attribute.
 *
 * <p>Attributes are additional information which can be inserted in a PKCS#10
 * certificate request. For example a "Driving License Certificate" could have
 * the driving license number as an attribute.
 *
 * <p>Attributes are represented as a sequence of the attribute identifier
 * (Object Identifier) and a set of DER encoded attribute values.
 *
 * ASN.1 definition of Attribute:
 * <pre>
 * Attribute :: SEQUENCE {
 *    type    AttributeType,
 *    values  SET OF AttributeValue
 * }
 * AttributeType  ::= OBJECT IDENTIFIER
 * AttributeValue ::= ANY defined by type
 * </pre>
 *
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */
public class PKCS10Attribute implements DerEncoder {

    protected ObjectIdentifier  attributeId;
    protected Object            attributeValue;

    /**
     * Constructs an attribute from a DER encoding.
     * This constructor expects the value to be encoded as defined above,
     * i.e. a SEQUENCE of OID and SET OF value(s), not a literal
     * X.509 v3 extension. Only PKCS9 defined attributes are supported
     * currently.
     *
     * @param derVal the der encoded attribute.
     * @exception IOException on parsing errors.
     */
    public PKCS10Attribute(DerValue derVal) throws IOException {
        PKCS9Attribute attr = new PKCS9Attribute(derVal);
        this.attributeId = attr.getOID();
        this.attributeValue = attr.getValue();
    }

    /**
     * Constructs an attribute from individual components of
     * ObjectIdentifier and the value (any java object).
     *
     * @param attributeId the ObjectIdentifier of the attribute.
     * @param attributeValue an instance of a class that implements
     * the attribute identified by the ObjectIdentifier.
     */
    public PKCS10Attribute(ObjectIdentifier attributeId,
                           Object attributeValue) {
        this.attributeId = attributeId;
        this.attributeValue = attributeValue;
    }

    /**
     * Constructs an attribute from PKCS9 attribute.
     *
     * @param attr the PKCS9Attribute to create from.
     */
    public PKCS10Attribute(PKCS9Attribute attr) {
        this.attributeId = attr.getOID();
        this.attributeValue = attr.getValue();
    }

    /**
     * DER encode this object onto an output stream.
     * Implements the <code>DerEncoder</code> interface.
     *
     * @param out the DerOutputStream on which to write the DER encoding.
     */
    @Override
    public void encode(DerOutputStream out) {
        PKCS9Attribute attr = new PKCS9Attribute(attributeId, attributeValue);
        attr.encode(out);
    }

    /**
     * Returns the ObjectIdentifier of the attribute.
     */
    public ObjectIdentifier getAttributeId() {
        return (attributeId);
    }

    /**
     * Returns the attribute value.
     */
    public Object getAttributeValue() {
        return (attributeValue);
    }

    /**
     * Returns the attribute in user readable form.
     */
    public String toString() {
        return (attributeValue.toString());
    }
}
