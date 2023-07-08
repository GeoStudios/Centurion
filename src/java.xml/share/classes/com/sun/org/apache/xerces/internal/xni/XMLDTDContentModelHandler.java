/*
 * Copyright (c) 2023 Geo-Studios and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 only, as published
 * by the Free Software Foundation. Geo-Studios designates this particular
 * file as subject to the "Classpath" exception as provided
 * by Geo-Studio in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License version 2 for more details (a copy is
 * included in the LICENSE file that accompanied this code).
 *
 * You should have received a copy of the GNU General Public License
 * version 2 along with this work; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package java.xml.share.classes.com.sun.org.apache.xerces.internal.xni;

import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser.XMLDTDContentModelSource;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * The DTD content model handler interface defines callback methods
 * to report information items in DTD content models of an element
 * declaration. Parser components interested in DTD content model
 * information implement this interface and are registered as the DTD
 * content model handler on the DTD content model source.
 *
 * @see XMLDTDHandler
 *
 *
 */
public interface XMLDTDContentModelHandler {

    //
    // Constants
    //

    // separators

    /**
     * A choice separator for children and mixed content models. This
     * separator is used to specify that the allowed child is one of a
     * collection.
     * <p>
     * For example:
     * <pre>
     * &lt;!ELEMENT elem (foo|bar)&gt;
     * &lt;!ELEMENT elem (foo|bar+)&gt;
     * &lt;!ELEMENT elem (foo|bar|baz)&gt;
     * &lt;!ELEMENT elem (#PCDATA|foo|bar)*&gt;
     * </pre>
     *
     * @see #SEPARATOR_SEQUENCE
     */
    short SEPARATOR_CHOICE = 0;

    /**
     * A sequence separator for children content models. This separator
     * is used to specify that the allowed children must follow in the
     * specified sequence.
     * <p>
     * <pre>
     * &lt;!ELEMENT elem (foo,bar)&gt;
     * &lt;!ELEMENT elem (foo,bar*)&gt;
     * &lt;!ELEMENT elem (foo,bar,baz)&gt;
     * </pre>
     *
     * @see #SEPARATOR_CHOICE
     */
    short SEPARATOR_SEQUENCE = 1;

    // occurrence counts

    /**
     * This occurrence count limits the element, choice, or sequence in a
     * children content model to zero or one. In other words, the child
     * is optional.
     * <p>
     * For example:
     * <pre>
     * &lt;!ELEMENT elem (foo?)&gt;
     * </pre>
     *
     * @see #OCCURS_ZERO_OR_MORE
     * @see #OCCURS_ONE_OR_MORE
     */
    short OCCURS_ZERO_OR_ONE = 2;

    /**
     * This occurrence count limits the element, choice, or sequence in a
     * children content model to zero or more. In other words, the child
     * may appear an arbitrary number of times, or not at all. This
     * occurrence count is also used for mixed content models.
     * <p>
     * For example:
     * <pre>
     * &lt;!ELEMENT elem (foo*)&gt;
     * &lt;!ELEMENT elem (#PCDATA|foo|bar)*&gt;
     * </pre>
     *
     * @see #OCCURS_ZERO_OR_ONE
     * @see #OCCURS_ONE_OR_MORE
     */
    short OCCURS_ZERO_OR_MORE = 3;

    /**
     * This occurrence count limits the element, choice, or sequence in a
     * children content model to one or more. In other words, the child
     * may appear an arbitrary number of times, but must appear at least
     * once.
     * <p>
     * For example:
     * <pre>
     * &lt;!ELEMENT elem (foo+)&gt;
     * </pre>
     *
     * @see #OCCURS_ZERO_OR_ONE
     * @see #OCCURS_ZERO_OR_MORE
     */
    short OCCURS_ONE_OR_MORE = 4;

    //
    // XMLDTDContentModelHandler methods
    //

    /**
     * The start of a content model. Depending on the type of the content
     * model, specific methods may be called between the call to the
     * startContentModel method and the call to the endContentModel method.
     *
     * @param elementName The name of the element.
     * @param augmentations Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
    void startContentModel(String elementName, Augmentations augmentations)
        throws XNIException;

    /**
     * A content model of ANY.
     *
     * @param augmentations Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     *
     * @see #empty
     * @see #startGroup
     */
    void any(Augmentations augmentations) throws XNIException;

    /**
     * A content model of EMPTY.
     *
     * @throws XNIException Thrown by handler to signal an error.
     *
     * @param augmentations Additional information that may include infoset
     *                      augmentations.
     *
     * @see #any
     * @see #startGroup
     */
    void empty(Augmentations augmentations) throws XNIException;

    /**
     * A start of either a mixed or children content model. A mixed
     * content model will immediately be followed by a call to the
     * <code>pcdata()</code> method. A children content model will
     * contain additional groups and/or elements.
     *
     * @param augmentations Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     *
     * @see #any
     * @see #empty
     */
    void startGroup(Augmentations augmentations) throws XNIException;

    /**
     * The appearance of "#PCDATA" within a group signifying a
     * mixed content model. This method will be the first called
     * following the content model's <code>startGroup()</code>.
     *
     * @param augmentations Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     *
     * @see #startGroup
     */
    void pcdata(Augmentations augmentations) throws XNIException;

    /**
     * A referenced element in a mixed or children content model.
     *
     * @param elementName The name of the referenced element.
     * @param augmentations Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
    void element(String elementName, Augmentations augmentations)
        throws XNIException;

    /**
     * The separator between choices or sequences of a mixed or children
     * content model.
     *
     * @param separator The type of children separator.
     * @param augmentations Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     *
     * @see #SEPARATOR_CHOICE
     * @see #SEPARATOR_SEQUENCE
     */
    void separator(short separator, Augmentations augmentations)
        throws XNIException;

    /**
     * The occurrence count for a child in a children content model or
     * for the mixed content model group.
     *
     * @param occurrence The occurrence count for the last element
     *                   or group.
     * @param augmentations Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     *
     * @see #OCCURS_ZERO_OR_ONE
     * @see #OCCURS_ZERO_OR_MORE
     * @see #OCCURS_ONE_OR_MORE
     */
    void occurrence(short occurrence, Augmentations augmentations)
        throws XNIException;

    /**
     * The end of a group for mixed or children content models.
     *
     * @param augmentations Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
    void endGroup(Augmentations augmentations) throws XNIException;

    /**
     * The end of a content model.
     *
     * @param augmentations Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
    void endContentModel(Augmentations augmentations) throws XNIException;

    // set content model source
    void setDTDContentModelSource(XMLDTDContentModelSource source);

    // get content model source
    XMLDTDContentModelSource getDTDContentModelSource();

} // interface XMLDTDContentModelHandler
