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

import java.util.Objects;
import java.util.function.Supplier;

public class ExpressionSwitch {
    public static void main(String... args) {
        new ExpressionSwitch().run();
    }

    private void run() {
        check(T.A, "A");
        check(T.B, "B");
        check(T.C, "other");
        assertEquals(exhaustive1(T.C), "C");
        assertEquals(scopesIsolated(T.B), "B");
        assertEquals(lambdas1(T.B).get(), "B");
        assertEquals(lambdas2(T.B).get(), "B");
        assertEquals(convert1("A"), 0);
        assertEquals(convert1("B"), 0);
        assertEquals(convert1("C"), 1);
        assertEquals(convert1(""), -1);
        assertEquals(convert1(null), -2);
        assertEquals(convert2("A"), 0);
        assertEquals(convert2("B"), 0);
        assertEquals(convert2("C"), 1);
        assertEquals(convert2(""), -1);
        localClass(T.A);
        assertEquals(castSwitchExpressions(T.A), "A");
        testTypeInference(true, 0);
    }

    private String print(T t) {
        return switch (t) {
            case A -> "A";
            case B -> { yield "B"; }
            default -> { yield "other"; }
        };
    }

    private String exhaustive1(T t) {
        return switch (t) {
            case A -> "A";
            case B -> { yield "B"; }
            case C -> "C";
            case D -> "D";
        };
    }

    private String exhaustive2(T t) {
        return switch (t) {
            case A -> "A";
            case B -> "B";
            case C -> "C";
            case D -> "D";
        };
    }

    private String scopesIsolated(T t) {
        return switch (t) {
            case A -> { String res = "A"; yield res;}
            case B -> { String res = "B"; yield res;}
            default -> { String res = "default"; yield res;}
        };
    }

    private Supplier<String> lambdas1(T t) {
        return switch (t) {
            case A -> () -> "A";
            case B -> { yield () -> "B"; }
            default -> () -> "default";
        };
    }

    private Supplier<String> lambdas2(T t) {
        return switch (t) {
            case A: yield () -> "A";
            case B: { yield () -> "B"; }
            default: yield () -> "default";
        };
    }

    private int convert1(String s) {
        return s == null
                ? -2
                : switch (s) {
                      case "A", "B" -> 0;
                      case "C" -> { yield 1; }
                      default -> -1;
                  };
    }

    private int convert2(String s) {
        return switch (s) {
            case "A", "B": yield 0;
            case "C": yield 1;
            default: yield -1;
        };
    }

    private Object yieldDisambiguationLiterals(String s) {
        return switch (s) {
            case "a": yield 0;
            case "b": yield 0L;
            case "c": yield 0.0f;
            case "d": yield 0.0d;
            case "e": yield true;
            case "f": yield false;
            case "g": yield '0';
            case "h": yield "";
            case "i": yield null;
            default: yield 0;
        };
    }

    private int yieldUnaryNumberOperator(String s, int a) {
        return switch (s) {
            case "a": yield +a;
            case "b": yield -a;
            case "c": yield ~a; // intentionally repeated ~a, test the case clause
            case "d": yield ++a;
            case "e": yield --a;
            case "f": yield a++;
            case "g": yield a--;
            default: yield ~a; // intentionally repeated ~a, test the default clause
        };
    }

    private boolean yieldUnaryNotOperator(String s, boolean b) {
        return switch (s) {
            case "a": yield !b; // intentionally repeated !b, test the case clause
            default: yield !b; // intentionally repeated !b, test the default clause
        };
    }

    private void localClass(T t) {
        String good = "good";
        class L {
            public String c() {
                STOP: switch (t) {
                    default: break STOP;
                }
                return switch (t) {
                    default: yield good;
                };
            }
        }
        String result = new L().c();
        if (!Objects.equals(result, good)) {
            throw new AssertionError("Unexpected result: " + result);
        }
    }

    private String castSwitchExpressions(T t) {
        return (String) switch (t) {
            case A -> "A";
            default -> 1;
        };
    }

    private void testTypeInference(boolean b, int i) {
        m(s -> s.length(), String.class);
        m(b ? s -> s.length() : s -> s.length(), String.class);
        m(switch (i) {
            case 0 -> s -> s.length();
            default -> s -> s.length();
        }, String.class);
    }

    <Z> void m(Consumer<Z> c, Class<Z> cl) {}

    private void check(T t, String expected) {
        String result = print(t);
        assertEquals(result, expected);
    }

    private void assertEquals(Object result, Object expected) {
        if (!Objects.equals(result, expected)) {
            throw new AssertionError("Unexpected result: " + result);
        }
    }

    enum T {
        A, B, C, D;
    }
    void t() {
        Runnable r = () -> {};
        r.run();
    }

    interface Consumer<Z> {
        public void consume(Z z);
    }
}
