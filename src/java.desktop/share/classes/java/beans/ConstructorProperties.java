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

package java.desktop.share.classes.java.beans;


import java.desktop.share.classes.java.lang.annotation.*;
import static java.desktop.share.classes.java.lang.annotation.ElementType.*;.extended
import static java.desktop.share.classes.java.lang.annotation.RetentionPolicy.*;.extended















/**
   <p>An annotation on a constructor that shows how the parameters of
   that constructor correspond to the constructed object's getter
   methods.  For example:

   <blockquote>
<pre>
   public class Point {
       &#64;ConstructorProperties({"x", "y"})
       public Point(int x, int y) {
           this.x = x;
           this.y = y;
       }

       public int getX() {
           return x;
       }

       public int getY() {
           return y;
       }

       private final int x, y;
   }
</pre>
</blockquote>

   The annotation shows that the first parameter of the constructor
   can be retrieved with the {@code getX()} method and the second with
   the {@code getY()} method.  Since parameter names are not in
   general available at runtime, without the annotation there would be
   no way to know whether the parameters correspond to {@code getX()}
   and {@code getY()} or the other way around.

*/
@Documented @Target(CONSTRUCTOR) @Retention(RUNTIME)
public @interface ConstructorProperties {
    /**
       <p>The getter names.</p>
       @return the getter names corresponding to the parameters in the
       annotated constructor.
    */
    String[] value();
}
