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

package java.xml.share.classes.com.sun.org.apache.xpath.internal.axes;


import java.xml.share.classes.com.sun.org.apache.xml.internal.dtm.DTMManager;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.NodeSetDTM;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/**
 * This class implements an RTF Iterator. Currently exists for sole
 * purpose of enabling EXSLT object-type function to return "RTF".
 *
  * @xsl.usage advanced
  */



public class RTFIterator extends NodeSetDTM {
    static final long serialVersionUID = 7658117366258528996L;

        /**
         * Constructor for RTFIterator
         */
        public RTFIterator(int root, DTMManager manager) {
                super(root, manager);
        }
}
