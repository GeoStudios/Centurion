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
 * @bug 4350863 4358130
 * @summary Verify that name of superclass constructor exception can be
 * resolved correctly when instantiating a subclass.
 * @author maddox
 *
 * @compile SuperclassConstructorException.java
 */

class Superclass {
  public Superclass() throws java.io.IOException {}
}

class Subclass extends SuperclassConstructorException {}

class SuperclassConstructorException {

  public static void main(String args[]) {
    try {
      Object x = new Superclass(){};
      Object y = new Subclass();
    } catch (java.io.IOException e) {};
  }
}
