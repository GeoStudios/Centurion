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
 *
 *
 *
 * @test
 * @bug 4507434
 * @summary PolicyFile.getPermissions(ProtectionDomain) should return
 *           empty permissions when passed in null protection domain
 *
 */

import java.security.*;
import java.util.*;

public class nullDomain {
      public static void main(String[] args) {
      try {
          System.out.println(
            Policy.getPolicy().getPermissions((ProtectionDomain) null));
          PropertyPermission p = new PropertyPermission
                                        ("user.home","read");
          if (Policy.getPolicy().implies((ProtectionDomain)null, p))
                System.out.println ("implies succeeded");
       } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException ("Unexpected exception " +
                                             e.toString());

       }
     }
}
