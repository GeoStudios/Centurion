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
 * This class was used to produce a jcod file in which the
 * NestMembers attribute was modified to make it empty. Class
 * HostNoNestMember$Member has class HostNoNestMember as its
 * NestHost, which will trigger an error when resolving.
 *
 * When compiled, this generates a HostNoNestMember class and
 * a HostNoNestMember$Menber class.  The former class, HostNoNestMember,
 * gets overwritten when HostNoNestMember.jcod gets compiled.
 */
class HostNoNestMember {
  class Member {
    private int value;
  }

  public int test() {
    Member m = new Member();
    return m.value;
  }
}
