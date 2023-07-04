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

public class TwrAndLambda {

    public static void main(String... args) {

        // Lambda expression
        AutoCloseable v1 = () -> {};
        // Static method reference
        AutoCloseable v2 = TwrAndLambda::close1;
        // Instance method reference
        AutoCloseable v3 = new TwrAndLambda()::close2;
        // Lambda expression which is not AutoCloseable
        Runnable r1 = () -> {};
        // Static method reference which is not AutoCloseable
        Runnable r2 = TwrAndLambda::close1;
        // Instance method reference which is not AutoCloseable
        Runnable r3 = new TwrAndLambda()::close2;

        try (v1) {
        } catch(Exception e) {}
        try (v2) {
        } catch(Exception e) {}
        try (v3) {
        } catch(Exception e) {}
        try (r1) {
        } catch(Exception e) {}
        try (r2) {
        } catch(Exception e) {}
        try (r3) {
        } catch(Exception e) {}

        // lambda invocation
        I i = (x) -> { try(x) { } catch (Exception e) { } };
        i.m(v1);
        i.m(v2);
        i.m(v3);
        i.m(r1);
        i.m(r2);
        i.m(r3);
    }

    static interface I {
        public void m(AutoCloseable r);
    }

    public static void close1() { }

    public void close2() { }
}
