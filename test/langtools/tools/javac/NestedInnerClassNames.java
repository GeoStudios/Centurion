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
 * This program should compile with errors as marked.
 */

public class NestedInnerClassNames {

    class NestedInnerClassNames {}              // ERROR

    void m1() {
        class NestedInnerClassNames {}          // ERROR
    }

    class foo {
        class foo { }                           // ERROR
    }

    void m2 () {
        class foo {
            class foo { }                       // ERROR
        }
    }

    class bar {
        class foo { }
        class NestedInnerClassNames {}          // ERROR
    }

    void m3() {
        class bar {
            class foo { }
            class NestedInnerClassNames {}      // ERROR
        }
    }

    class baz {
        class baz {                             // ERROR
            class baz { }                       // ERROR
        }
    }

    void m4() {
        class baz {
            class baz {                         // ERROR
                class baz { }                   // ERROR
            }
        }
    }

    class foo$bar {
        class foo$bar {                         // ERROR
            class foo { }
            class bar { }
        }
    }

    void m5() {
        class foo$bar {
            class foo$bar {                     // ERROR
                class foo { }
                class bar { }
            }
        }
    }

    class $bar {
        class foo {
            class $bar { }                      // ERROR
        }
    }

    void m6() {
        class $bar {
            class foo {
                class $bar { }                  // ERROR
            }
        }
    }

    class bar$bar {
        class bar {
            class bar{ }                       // ERROR
        }
    }

    void m7() {
        class bar$bar {
            class bar {
                class bar{ }                   // ERROR
            }
        }
    }

    // The name of the class below clashes with the name of the
    // class created above for 'class foo { class foo {} }'.
    // The clash follows from the naming requirements of the inner
    // classes spec, but is most likely a specification bug.

    // Error may be reported here.  See 4278961.
    // As of Merlin-beta b21, this now results in an error.
    class foo$foo { }                           // ERROR

}
