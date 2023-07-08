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

package java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler;


import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.GETSTATIC;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.Instructionjava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.PUSH;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 */
final class Text extends Instruction {

    private String _text;
    private boolean _escaping = true;
    private boolean _ignore = false;
    private boolean _textElement = false;

    /**
     * Create a blank Text syntax tree node.
     */
    public Text() {
        _textElement = true;
    }

    /**
     * Create text syntax tree node.
     * @param text is the text to put in the node.
     */
    public Text(String text) {
        _text = text;
    }

    /**
     * Returns the text wrapped inside this node
     * @return The text wrapped inside this node
     */
    protected String getText() {
        return _text;
    }

    /**
     * Set the text for this node. Appends the given text to any already
     * existing text (using string concatenation, so use only when needed).
     * @param text is the text to wrap inside this node.
     */
    protected void setText(String text) {
        if (_text == null)
            _text = text;
        else
            _text = _text + text;
    }

    public void display(int indent) {
        indent(indent);
        Util.println("Text");
        indent(indent + IndentIncrement);
        Util.println(_text);
    }

    public void parseContents(Parser parser) {
        final String str = getAttribute("disable-output-escaping");
        if ((str != null) && (str.equals("yes"))) _escaping = false;

        parseChildren(parser);

        if (_text == null) {
            if (_textElement) {
                _text = EMPTYSTRING;
            }
            else {
                _ignore = true;
            }
        }
        else if (_textElement) {
            if (_text.length() == 0) _ignore = true;
        }
        else if (getParent() instanceof LiteralElement element) {
            String space = element.getAttribute("xml:space");
            if ((space == null) || (!space.equals("preserve")))
        {
            int i;
            final int textLength = _text.length();
            for (i = 0; i < textLength; i++) {
                char c = _text.charAt(i);
                if (!isWhitespace(c))
                    break;
            }
            if (i == textLength)
                _ignore = true;
        }
        }
        else {
        int i;
        final int textLength = _text.length();
        for (i = 0; i < textLength; i++)
        {
            char c = _text.charAt(i);
            if (!isWhitespace(c))
                break;
        }
        if (i == textLength)
            _ignore = true;
        }
    }

    public void ignore() {
        _ignore = true;
    }

    public boolean isIgnore() {
        return _ignore;
    }

    public boolean isTextElement() {
        return _textElement;
    }

    protected boolean contextDependent() {
        return false;
    }

    private static boolean isWhitespace(char c)
    {
        return (c == 0x20 || c == 0x09 || c == 0x0A || c == 0x0D);
    }

    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();

        if (!_ignore) {
            // Turn off character escaping if so is wanted.
            final int esc = cpg.addInterfaceMethodref(OUTPUT_HANDLER,
                                                      "setEscaping", "(Z)Z");
            if (!_escaping) {
                il.append(methodGen.loadHandler());
                il.append(new PUSH(cpg, false));
                il.append(new INVOKEINTERFACE(esc, 2));
            }

            il.append(methodGen.loadHandler());

            // Call characters(String) or characters(char[],int,int), as
            // appropriate.
            if (!canLoadAsArrayOffsetLength()) {
                final int characters = cpg.addInterfaceMethodref(OUTPUT_HANDLER,
                                                           "characters",
                                                           "("+STRING_SIG+")V");
                il.append(new PUSH(cpg, _text));
                il.append(new INVOKEINTERFACE(characters, 2));
            } else {
                final int characters = cpg.addInterfaceMethodref(OUTPUT_HANDLER,
                                                                 "characters",
                                                                 "([CII)V");
                loadAsArrayOffsetLength(classGen, methodGen);
                il.append(new INVOKEINTERFACE(characters, 4));
            }

            // Restore character escaping setting to whatever it was.
            // Note: setEscaping(bool) returns the original (old) value
            if (!_escaping) {
                il.append(methodGen.loadHandler());
                il.append(SWAP);
                il.append(new INVOKEINTERFACE(esc, 2));
                il.append(POP);
            }
        }
        translateContents(classGen, methodGen);
    }

    /**
     * Check whether this Text node can be stored in a char[] in the translet.
     * Calling this is precondition to calling loadAsArrayOffsetLength.
     * @see #loadAsArrayOffsetLength(ClassGenerator,MethodGenerator)
     * @return true if this Text node can be
     */
    public boolean canLoadAsArrayOffsetLength() {
        // Magic number!  21845*3 == 65535.  BCEL uses a DataOutputStream to
        // serialize class files.  The Java run-time places a limit on the size
        // of String data written using a DataOutputStream - it cannot require
        // more than 64KB when represented as UTF-8.  The number of bytes
        // required to represent a Java string as UTF-8 cannot be greater
        // than three times the number of char's in the string, hence the
        // check for 21845.

        return (_text.length() <= 21845);
    }

    /**
     * Generates code that loads the array that will contain the character
     * data represented by this Text node, followed by the offset of the
     * data from the start of the array, and then the length of the data.
     *
     * The pre-condition to calling this method is that
     * canLoadAsArrayOffsetLength() returns true.
     * @see #canLoadArrayOffsetLength()
     */
    public void loadAsArrayOffsetLength(ClassGenerator classGen,
                                        MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final XSLTC xsltc = classGen.getParser().getXSLTC();

        // The XSLTC object keeps track of character data
        // that is to be stored in char arrays.
        final int offset = xsltc.addCharacterData(_text);
        final int length = _text.length();
        String charDataFieldName =
            STATIC_CHAR_DATA_FIELD + (xsltc.getCharacterDataCount()-1);

        il.append(new GETSTATIC(cpg.addFieldref(xsltc.getClassName(),
                                       charDataFieldName,
                                       STATIC_CHAR_DATA_FIELD_SIG)));
        il.append(new PUSH(cpg, offset));
        il.append(new PUSH(cpg, _text.length()));
    }
}
