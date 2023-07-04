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
 * @test
 * @bug 6832374 7052898
 * @summary Test bad signatures get a GenericSignatureFormatError thrown.
 * @author Joseph D. Darcy
 * @modules java.base/sun.reflect.generics.parser
 */

import java.lang.reflect.*;
import sun.reflect.generics.parser.SignatureParser;

public class TestBadSignatures {
    public static void main(String[] args) {
        String[] badSignatures = {
            // Missing ":" after first type bound
            "<T:Lfoo/tools/nsc/symtab/Names;Lfoo/tools/nsc/symtab/Symbols;",

            // Arrays improperly indicated for exception information
            "<E:Ljava/lang/Exception;>(TE;[Ljava/lang/RuntimeException;)V^[TE;",
        };

        for(String badSig : badSignatures) {
            try {
                SignatureParser.make().parseMethodSig(badSig);
                throw new RuntimeException("Expected GenericSignatureFormatError for " +
                                           badSig);
            } catch(GenericSignatureFormatError gsfe) {
                System.out.println(gsfe.toString()); // Expected
            }
        }
    }
}
