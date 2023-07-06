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

package utils;

/**
 *
 * Class includes reused constants across jstack's tests
 *
 */
public class Consts {

    public static final String UNKNOWN = "XXXXXX";
    public static final String JNI_GLOBAL_REF = "JNI global references: ";
    public static final String SCENARIO_NAME = "scenario";
    public static final String SEPARATOR = " ";

    public static String REENTRANT_LOCK_NONFAIR = "a java.util.concurrent.locks.ReentrantLock$NonfairSync";
    public static String REENTRANT_LOCK_FAIR = "a java.util.concurrent.locks.ReentrantLock$FairSync";
    public static final String FFORMAT_REENTRANT_LOCK_NONFAIR = "a java/util/concurrent/locks/ReentrantLock$NonfairSync";
    public static final String FFORMAT_REENTRANT_LOCK_FAIR = "a java/util/concurrent/locks/ReentrantLock$FairSync";

    public static String REENTRANT_RW_LOCK_NONFAIR = "a java.util.concurrent.locks.ReentrantReadWriteLock$NonfairSync";
    public static String REENTRANT_RW_LOCK_FAIR = "a java.util.concurrent.locks.ReentrantReadWriteLock$FairSync";
    public static final String FFORMAT_REENTRANT_RW_LOCK_NONFAIR = "a java/util/concurrent/locks/ReentrantReadWriteLock$NonfairSync";
    public static final String FFORMAT_REENTRANT_RW_LOCK_FAIR = "a java/util/concurrent/locks/ReentrantReadWriteLock$FairSync";

}