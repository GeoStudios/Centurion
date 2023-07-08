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

package jdk.jfr.share.classes.jdk.jfr;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Meta annotation for defining new types of event metadata.
 * <p>
 * In the following example, a transaction event is defined with two
 * user-defined annotations, {@code @Severity} and {@code @TransactionId}.
 *
 * <pre>{@literal
 * @MetadataDefinition
 * @Label("Severity")
 * @Description("Value between 0 and 100 that indicates severity. 100 is most severe.")
 * @Retention(RetentionPolicy.RUNTIME)
 * @Target({ElementType.TYPE})
 * public @interface Severity {
 *     int value() default 50;
 * }
 *
 * @MetadataDefinition
 * @Label("Transaction Id")
 * @Relational
 * @Retention(RetentionPolicy.RUNTIME)
 * @Target({ElementType.FIELD})
 * public @interface TransactionId {
 * }
 *
 * @Severity(80)
 * @Label("Transaction Blocked")
 * class TransactionBlocked extends Event {
 *     @TransactionId
 *     @Label("Transaction")
 *     long transactionId1;
 *
 *     @TransactionId
 *     @Label("Transaction Blocker")
 *     long transactionId2;
 * }
 * }</pre>
 *
 * Adding {@code @MetadataDefinition} to the declaration of {@code @Severity} and {@code @TransactionId}
 * ensures the information is saved by Flight Recorder.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface MetadataDefinition {
}
