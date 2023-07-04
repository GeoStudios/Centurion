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

package com.sun.org.apache.xerces.internal.impl.dv.xs;

import com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeValueException;
import com.sun.org.apache.xerces.internal.impl.dv.ValidationContext;
import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;
import java.util.AbstractList;

/**
 * Represent the schema list types
 *
 * @xerces.internal
 *
 *
 * @LastModified: Oct 2017
 */
public class ListDV extends TypeValidator{

    public short getAllowedFacets(){
          return (XSSimpleTypeDecl.FACET_LENGTH | XSSimpleTypeDecl.FACET_MINLENGTH |
                  XSSimpleTypeDecl.FACET_MAXLENGTH | XSSimpleTypeDecl.FACET_PATTERN |
                  XSSimpleTypeDecl.FACET_ENUMERATION | XSSimpleTypeDecl.FACET_WHITESPACE );
    }

    // this method should never be called: XSSimpleTypeDecl is responsible for
    // calling the item type for the convertion
    public Object getActualValue(String content, ValidationContext context)
            throws InvalidDatatypeValueException{
        return content;
    }

    // length of a list type is the number of items in the list
    public int getDataLength(Object value) {
        return ((ListData)value).getLength();
    }

    final static class ListData extends AbstractList<Object> implements ObjectList {
        final Object[] data;
        private String canonical;
        public ListData(Object[] data) {
            this.data = data;
        }
        public synchronized String toString() {
            if (canonical == null) {
                int len = data.length;
                StringBuffer buf = new StringBuffer();
                if (len > 0) {
                    buf.append(data[0].toString());
                }
                for (int i = 1; i < len; i++) {
                    buf.append(' ');
                    buf.append(data[i].toString());
                }
                canonical = buf.toString();
            }
            return canonical;
        }
        public int getLength() {
            return data.length;
        }
        public boolean equals(Object obj) {
            if (!(obj instanceof ListData))
                return false;
            Object[] odata = ((ListData)obj).data;

            int count = data.length;
            if (count != odata.length)
                return false;

            for (int i = 0 ; i < count ; i++) {
                if (!data[i].equals(odata[i]))
                    return false;
            }//end of loop

            //everything went fine.
            return true;
        }

        public int hashCode() {
            int hash = 0;
            for (int i = 0; i < data.length; ++i) {
                hash ^= data[i].hashCode();
            }
            return hash;
        }

        public boolean contains(Object item) {
            for (int i = 0;i < data.length; i++) {
                if (item == data[i]) {
                    return true;
                }
            }
            return false;
        }

        public Object item(int index) {
            if (index < 0 || index >= data.length) {
                return null;
            }
            return data[index];
        }

        /*
         * List methods
         */

        public Object get(int index) {
            if (index >= 0 && index < data.length) {
                return data[index];
            }
            throw new IndexOutOfBoundsException("Index: " + index);
        }

        public int size() {
            return getLength();
        }
    }
} // class ListDV
