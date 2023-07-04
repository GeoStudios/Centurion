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

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.classfile.Attribute;
import com.sun.org.apache.bcel.internal.classfile.Code;
import com.sun.org.apache.bcel.internal.classfile.ConstantValue;
import com.sun.org.apache.bcel.internal.classfile.ExceptionTable;
import com.sun.org.apache.bcel.internal.classfile.Field;
import com.sun.org.apache.bcel.internal.classfile.Method;
import com.sun.org.apache.bcel.internal.classfile.Utility;

/**
 * Convert methods and fields into HTML file.
 *
 *
 */
final class MethodHTML {

    private final String className; // name of current class
    private final PrintWriter file; // file to write to
    private final ConstantHTML constantHtml;
    private final AttributeHTML attribute_html;


    MethodHTML(final String dir, final String class_name, final Method[] methods, final Field[] fields,
            final ConstantHTML constant_html, final AttributeHTML attribute_html) throws IOException {
        this.className = class_name;
        this.attribute_html = attribute_html;
        this.constantHtml = constant_html;
        file = new PrintWriter(new FileOutputStream(dir + class_name + "_methods.html"));
        file.println("<HTML><BODY BGCOLOR=\"#C0C0C0\"><TABLE BORDER=0>");
        file.println("<TR><TH ALIGN=LEFT>Access&nbsp;flags</TH><TH ALIGN=LEFT>Type</TH>"
                + "<TH ALIGN=LEFT>Field&nbsp;name</TH></TR>");
        for (final Field field : fields) {
            writeField(field);
        }
        file.println("</TABLE>");
        file.println("<TABLE BORDER=0><TR><TH ALIGN=LEFT>Access&nbsp;flags</TH>"
                + "<TH ALIGN=LEFT>Return&nbsp;type</TH><TH ALIGN=LEFT>Method&nbsp;name</TH>"
                + "<TH ALIGN=LEFT>Arguments</TH></TR>");
        for (int i = 0; i < methods.length; i++) {
            writeMethod(methods[i], i);
        }
        file.println("</TABLE></BODY></HTML>");
        file.close();
    }


    /**
     * Print field of class.
     *
     * @param field field to print
     * @throws java.io.IOException
     */
    private void writeField( final Field field ) throws IOException {
        final String type = Utility.signatureToString(field.getSignature());
        final String name = field.getName();
        String access = Utility.accessToString(field.getAccessFlags());
        Attribute[] attributes;
        access = Utility.replace(access, " ", "&nbsp;");
        file.print("<TR><TD><FONT COLOR=\"#FF0000\">" + access + "</FONT></TD>\n<TD>"
                + Class2HTML.referenceType(type) + "</TD><TD><A NAME=\"field" + name + "\">" + name
                + "</A></TD>");
        attributes = field.getAttributes();
        // Write them to the Attributes.html file with anchor "<name>[<i>]"
        for (int i = 0; i < attributes.length; i++) {
            attribute_html.writeAttribute(attributes[i], name + "@" + i);
        }
        for (int i = 0; i < attributes.length; i++) {
            if (attributes[i].getTag() == Const.ATTR_CONSTANT_VALUE) { // Default value
                final String str = ((ConstantValue) attributes[i]).toString();
                // Reference attribute in _attributes.html
                file.print("<TD>= <A HREF=\"" + className + "_attributes.html#" + name + "@" + i
                        + "\" TARGET=\"Attributes\">" + str + "</TD>\n");
                break;
            }
        }
        file.println("</TR>");
    }


    private void writeMethod( final Method method, final int method_number ) {
        // Get raw signature
        final String signature = method.getSignature();
        // Get array of strings containing the argument types
        final String[] args = Utility.methodSignatureArgumentTypes(signature, false);
        // Get return type string
        final String type = Utility.methodSignatureReturnType(signature, false);
        // Get method name
        final String name = method.getName();
        String html_name;
        // Get method's access flags
        String access = Utility.accessToString(method.getAccessFlags());
        // Get the method's attributes, the Code Attribute in particular
        final Attribute[] attributes = method.getAttributes();
        /* HTML doesn't like names like <clinit> and spaces are places to break
         * lines. Both we don't want...
         */
        access = Utility.replace(access, " ", "&nbsp;");
        html_name = Class2HTML.toHTML(name);
        file.print("<TR VALIGN=TOP><TD><FONT COLOR=\"#FF0000\"><A NAME=method" + method_number
                + ">" + access + "</A></FONT></TD>");
        file.print("<TD>" + Class2HTML.referenceType(type) + "</TD><TD>" + "<A HREF=" + className
                + "_code.html#method" + method_number + " TARGET=Code>" + html_name
                + "</A></TD>\n<TD>(");
        for (int i = 0; i < args.length; i++) {
            file.print(Class2HTML.referenceType(args[i]));
            if (i < args.length - 1) {
                file.print(", ");
            }
        }
        file.print(")</TD></TR>");
        // Check for thrown exceptions
        for (int i = 0; i < attributes.length; i++) {
            attribute_html.writeAttribute(attributes[i], "method" + method_number + "@" + i,
                    method_number);
            final byte tag = attributes[i].getTag();
            if (tag == Const.ATTR_EXCEPTIONS) {
                file.print("<TR VALIGN=TOP><TD COLSPAN=2></TD><TH ALIGN=LEFT>throws</TH><TD>");
                final int[] exceptions = ((ExceptionTable) attributes[i]).getExceptionIndexTable();
                for (int j = 0; j < exceptions.length; j++) {
                    file.print(constantHtml.referenceConstant(exceptions[j]));
                    if (j < exceptions.length - 1) {
                        file.print(", ");
                    }
                }
                file.println("</TD></TR>");
            } else if (tag == Const.ATTR_CODE) {
                final Attribute[] c_a = ((Code) attributes[i]).getAttributes();
                for (int j = 0; j < c_a.length; j++) {
                    attribute_html.writeAttribute(c_a[j], "method" + method_number + "@" + i + "@"
                            + j, method_number);
                }
            }
        }
    }
}
