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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.dtd;

import java.xml.share.classes.com.sun.org.apache.xerces.internal.util.SymbolTable;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.Augmentations;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XNIException;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * <p>A DTD grammar that produces balanced syntax trees.</p>
 *
 * @xerces.internal
 *
 */
final class BalancedDTDGrammar extends DTDGrammar {

    //
    // Data
    //

    /** Mixed. */
    private boolean fMixed;

    /** Stack depth */
    private int fDepth = 0;

    /** Children content model operation stack. */
    private short [] fOpStack = null;

    /** Holder for choice/sequence/leaf groups at each depth. */
    private int [][] fGroupIndexStack;

    /** Sizes of the allocated portions of each int[] in fGroupIndexStack. */
    private int [] fGroupIndexStackSizes;

    //
    // Constructors
    //

    /** Default constructor. */
    public BalancedDTDGrammar(SymbolTable symbolTable, XMLDTDDescription desc) {
        super(symbolTable, desc);
    } // BalancedDTDGrammar(SymbolTable,XMLDTDDescription)

    //
    // Public methods
    //

    /**
     * The start of a content model. Depending on the type of the content
     * model, specific methods may be called between the call to the
     * startContentModel method and the call to the endContentModel method.
     *
     * @param elementName The name of the element.
     * @param augs Additional information that may include infoset
     *                      augmentations.
     * @throws XNIException Thrown by handler to signal an error.
     */
    public void startContentModel(String elementName, Augmentations augs)
        throws XNIException {
        fDepth = 0;
        initializeContentModelStacks();
        super.startContentModel(elementName, augs);
    } // startContentModel(String)

    /**
     * A start of either a mixed or children content model. A mixed
     * content model will immediately be followed by a call to the
     * <code>pcdata()</code> method. A children content model will
     * contain additional groups and/or elements.
     *
     * @param augs Additional information that may include infoset
     *                      augmentations.
     * @throws XNIException Thrown by handler to signal an error.
     *
     * @see #any
     * @see #empty
     */
    public void startGroup(Augmentations augs) throws XNIException {
        ++fDepth;
        initializeContentModelStacks();
        fMixed = false;
    } // startGroup()

    /**
     * The appearance of "#PCDATA" within a group signifying a
     * mixed content model. This method will be the first called
     * following the content model's <code>startGroup()</code>.
     *
     *@param augs Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     *
     * @see #startGroup
     */
    public void pcdata(Augmentations augs) throws XNIException {
        fMixed = true;
    } // pcdata()

    /**
     * A referenced element in a mixed or children content model.
     *
     * @param elementName The name of the referenced element.
     * @param augs Additional information that may include infoset
     *                      augmentations.
     *
     * @throws XNIException Thrown by handler to signal an error.
     */
    public void element(String elementName, Augmentations augs) throws XNIException {
        addToCurrentGroup(addUniqueLeafNode(elementName));
    } // element(String)

    /**
     * The separator between choices or sequences of a mixed or children
     * content model.
     *
     * @param separator The type of children separator.
     * @param augs Additional information that may include infoset
     *                      augmentations.
     * @throws XNIException Thrown by handler to signal an error.
     *
     * @see org.apache.xerces.xni.XMLDTDContentModelHandler#SEPARATOR_CHOICE
     * @see org.apache.xerces.xni.XMLDTDContentModelHandler#SEPARATOR_SEQUENCE
     */
    public void separator(short separator, Augmentations augs) throws XNIException {
        if (separator == XMLDTDContentModelHandler.SEPARATOR_CHOICE) {
            fOpStack[fDepth] = XMLContentSpec.CONTENTSPECNODE_CHOICE;
        }
        else if (separator == XMLDTDContentModelHandler.SEPARATOR_SEQUENCE) {
            fOpStack[fDepth] = XMLContentSpec.CONTENTSPECNODE_SEQ;
        }
    } // separator(short)

    /**
     * The occurrence count for a child in a children content model or
     * for the mixed content model group.
     *
     * @param occurrence The occurrence count for the last element
     *                   or group.
     * @param augs Additional information that may include infoset
     *                      augmentations.
     * @throws XNIException Thrown by handler to signal an error.
     *
     * @see org.apache.xerces.xni.XMLDTDContentModelHandler#OCCURS_ZERO_OR_ONE
     * @see org.apache.xerces.xni.XMLDTDContentModelHandler#OCCURS_ZERO_OR_MORE
     * @see org.apache.xerces.xni.XMLDTDContentModelHandler#OCCURS_ONE_OR_MORE
     */
    public void occurrence(short occurrence, Augmentations augs) throws XNIException {
        if (!fMixed) {
            int currentIndex = fGroupIndexStackSizes[fDepth] - 1;
            if (occurrence == XMLDTDContentModelHandler.OCCURS_ZERO_OR_ONE) {
                fGroupIndexStack[fDepth][currentIndex] = addContentSpecNode(XMLContentSpec.CONTENTSPECNODE_ZERO_OR_ONE, fGroupIndexStack[fDepth][currentIndex], -1);
            }
            else if ( occurrence == XMLDTDContentModelHandler.OCCURS_ZERO_OR_MORE) {
                fGroupIndexStack[fDepth][currentIndex] = addContentSpecNode(XMLContentSpec.CONTENTSPECNODE_ZERO_OR_MORE, fGroupIndexStack[fDepth][currentIndex], -1);
            }
            else if ( occurrence == XMLDTDContentModelHandler.OCCURS_ONE_OR_MORE) {
                fGroupIndexStack[fDepth][currentIndex] = addContentSpecNode(XMLContentSpec.CONTENTSPECNODE_ONE_OR_MORE, fGroupIndexStack[fDepth][currentIndex], -1);
            }
        }
    } // occurrence(short)

    /**
     * The end of a group for mixed or children content models.
     *
     * @param augs Additional information that may include infoset
     *                      augmentations.
     * @throws XNIException Thrown by handler to signal an error.
     */
    public void endGroup(Augmentations augs) throws XNIException {
        final int length = fGroupIndexStackSizes[fDepth];
        final int group = length > 0 ? addContentSpecNodes(0, length - 1) : addUniqueLeafNode(null);
        --fDepth;
        addToCurrentGroup(group);
    } // endGroup()

    /**
     * The end of the DTD.
     *
     * @param augs Additional information that may include infoset
     *                      augmentations.
     * @throws XNIException Thrown by handler to signal an error.
     */
    public void endDTD(Augmentations augs) throws XNIException {
        super.endDTD(augs);
        fOpStack = null;
        fGroupIndexStack = null;
        fGroupIndexStackSizes = null;
    } // endDTD()

    //
    // Protected methods
    //

    /**
     * Adds the content spec to the given element declaration.
     */
    protected void addContentSpecToElement(XMLElementDecl elementDecl) {
        int contentSpec = fGroupIndexStackSizes[0] > 0 ? fGroupIndexStack[0][0] : -1;
        setContentSpecIndex(fCurrentElementIndex, contentSpec);
    }

    //
    // Private methods
    //

    /**
     * Creates a subtree from the leaf nodes at the current depth.
     */
    private int addContentSpecNodes(int begin, int end) {
        if (begin == end) {
            return fGroupIndexStack[fDepth][begin];
        }
        final int middle = (begin + end) >>> 1;
        return addContentSpecNode(fOpStack[fDepth],
                addContentSpecNodes(begin, middle),
                addContentSpecNodes(middle + 1, end));
    } // addContentSpecNodes(int,int)

    /**
     * Initialize the stacks which temporarily hold content models.
     */
    private void initializeContentModelStacks() {
        if (fOpStack == null) {
            fOpStack = new short[8];
            fGroupIndexStack = new int [8][];
            fGroupIndexStackSizes = new int [8];
        }
        else if (fDepth == fOpStack.length) {
            short [] newOpStack = new short[fDepth * 2];
            System.arraycopy(fOpStack, 0, newOpStack, 0, fDepth);
            fOpStack = newOpStack;
            int [][] newGroupIndexStack = new int[fDepth * 2][];
            System.arraycopy(fGroupIndexStack, 0, newGroupIndexStack, 0, fDepth);
            fGroupIndexStack = newGroupIndexStack;
            int [] newGroupIndexStackLengths = new int[fDepth * 2];
            System.arraycopy(fGroupIndexStackSizes, 0, newGroupIndexStackLengths, 0, fDepth);
            fGroupIndexStackSizes = newGroupIndexStackLengths;
        }
        fOpStack[fDepth] = -1;
        fGroupIndexStackSizes[fDepth] = 0;
    } // initializeContentModelStacks()

    /**
     * Add XMLContentSpec to the current group.
     *
     * @param contentSpec handle to the XMLContentSpec to add to the current group
     */
    private void addToCurrentGroup(int contentSpec) {
        int [] currentGroup = fGroupIndexStack[fDepth];
        int length = fGroupIndexStackSizes[fDepth]++;
        if (currentGroup == null) {
            currentGroup = new int[8];
            fGroupIndexStack[fDepth] = currentGroup;
        }
        else if (length == currentGroup.length) {
            int [] newGroup = new int[currentGroup.length * 2];
            System.arraycopy(currentGroup, 0, newGroup, 0, currentGroup.length);
            currentGroup = newGroup;
            fGroupIndexStack[fDepth] = currentGroup;
        }
        currentGroup[length] = contentSpec;
    } // addToCurrentGroup(int)

} // class BalancedDTDGrammar
