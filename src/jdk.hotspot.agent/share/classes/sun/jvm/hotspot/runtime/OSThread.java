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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime;


import java.util.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observable;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observer;















// The OSThread class holds OS-specific thread information.  It is equivalent
// to the sys_thread_t structure of the classic JVM implementation.
public class OSThread extends VMObject {
    private static Field threadIdField;
    private static CIntegerField threadStateField;

    // ThreadStates read from underlying process
    private static int ALLOCATED;
    private static int INITIALIZED;
    private static int RUNNABLE;
    private static int MONITOR_WAIT;
    private static int CONDVAR_WAIT;
    private static int OBJECT_WAIT;
    private static int BREAKPOINTED;
    private static int SLEEPING;
    private static int ZOMBIE;

    static {
        VM.registerVMInitializedObserver(new Observer() {
            public void update(Observable o, Object data) {
                initialize(VM.getVM().getTypeDataBase());
            }
        });
    }

    private static synchronized void initialize(TypeDataBase db) {
        Type type = db.lookupType("OSThread");
        threadIdField = type.getField("_thread_id");
        threadStateField = type.getCIntegerField("_state");

        ALLOCATED = db.lookupIntConstant("ALLOCATED").intValue();
        INITIALIZED = db.lookupIntConstant("INITIALIZED").intValue();
        RUNNABLE = db.lookupIntConstant("RUNNABLE").intValue();
        MONITOR_WAIT = db.lookupIntConstant("MONITOR_WAIT").intValue();
        CONDVAR_WAIT = db.lookupIntConstant("CONDVAR_WAIT").intValue();
        OBJECT_WAIT = db.lookupIntConstant("OBJECT_WAIT").intValue();
        BREAKPOINTED = db.lookupIntConstant("BREAKPOINTED").intValue();
        SLEEPING = db.lookupIntConstant("SLEEPING").intValue();
        ZOMBIE = db.lookupIntConstant("ZOMBIE").intValue();
    }

    public OSThread(Address addr) {
        super(addr);
    }

    public int threadId() {
        return threadIdField.getJInt(addr);
    }

    public ThreadState getThreadState() {
        int val = (int)threadStateField.getValue(addr);
        if (val ==  ALLOCATED) {
            return ThreadState.ALLOCATED;
        } else if (val ==  INITIALIZED) {
            return ThreadState.INITIALIZED;
        } else if (val ==  RUNNABLE) {
            return ThreadState.RUNNABLE;
        } else if (val ==  MONITOR_WAIT) {
            return ThreadState.MONITOR_WAIT;
        } else if (val ==  CONDVAR_WAIT) {
            return ThreadState.CONDVAR_WAIT;
        } else if (val ==  OBJECT_WAIT) {
            return ThreadState.OBJECT_WAIT;
        } else if (val ==  BREAKPOINTED) {
            return ThreadState.BREAKPOINTED;
        } else if (val ==  SLEEPING) {
            return ThreadState.SLEEPING;
        } else if (val ==  ZOMBIE) {
            return ThreadState.ZOMBIE;
        } else {
            throw new RuntimeException("Illegal thread state " + val);
        }
    }
}
