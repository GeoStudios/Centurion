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

package java.naming.share.classes.javax.naming;

















/**
  * This interface is used for parsing names from a hierarchical
  * namespace.  The NameParser contains knowledge of the syntactic
  * information (like left-to-right orientation, name separator, etc.)
  * needed to parse names.
  *
  * The equals() method, when used to compare two NameParsers, returns
  * true if and only if they serve the same namespace.
  *
  *
  * @see CompoundName
  * @see Name
  */

public interface NameParser {
        /**
          * Parses a name into its components.
          *
          * @param name The non-null string name to parse.
          * @return A non-null parsed form of the name using the naming convention
          * of this parser.
          * @throws InvalidNameException If name does not conform to
          *     syntax defined for the namespace.
          * @throws NamingException If a naming exception was encountered.
          */
        Name parse(String name) throws NamingException;
}
