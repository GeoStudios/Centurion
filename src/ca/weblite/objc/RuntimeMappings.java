/*
 * Geo Studios Protective License
 *
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 *
 * Whoever collects this software or tool may not distribute the copy that has been obtained.
 *
 * This software or tool may not be used to gain a commercial or monetary advantage.
 *
 * Copyright will be included in any software or tool using this license, no matter the size or type of software or tool.
 *
 * This software or tool is not under any patent, but the software or tool shall not be
 * sold or uploaded as some other product or without the original creators consent and
 * permission. If the following happens, consequences will occur due to following
 * instructions or not following the rules written in this document.
 */

package ca.weblite.objc;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;

/**
 * A JNA wrapper around the objective-c runtime.  This contains all of the functions
 * needed to interact with the runtime (e.g. send messages, etc..).
 *
 * <h2>Sample Usage</h2>
 * <script src="https://gist.github.com/3974488.js?file=SampleLowLevelAPI.java"></script>
 *
 * @see <a href="https://developer.apple.com/library/mac/#documentation/Cocoa/Reference/ObjCRuntimeRef/Reference/reference.html">Objective-C Runtime Reference</a>
 * @author Logan Abernathy
 * @since Alpha cdk-1.2
 */

public interface RuntimeMappings {

    public static interface Runtime0 extends Library {
        public static Runtime0 INSTANCE = (Runtime0) Native.loadLibrary("objc.A", Runtime0.class);

        public long objc_msgSend(Pointer theReceiver, Pointer theSelector,
                                 Object arg0
        );
        public double objc_msgSend_fpret(Pointer theReceiver, Pointer theSelector,
                                         Object arg0
        );
    }

    public static interface Runtime00 extends Library {
        public static Runtime00 INSTANCE = (Runtime00) Native.loadLibrary("objc.A", Runtime00.class);

        public long objc_msgSend(Pointer theReceiver, Pointer theSelector,
                                 Object arg0,
                                 Object arg1
        );
        public double objc_msgSend_fpret(Pointer theReceiver, Pointer theSelector,
                                         Object arg0,
                                         Object arg1
        );
    }

    public static interface Runtime000 extends Library {
        public static Runtime000 INSTANCE = (Runtime000) Native.loadLibrary("objc.A", Runtime000.class);

        public long objc_msgSend(Pointer theReceiver, Pointer theSelector,
                                 Object arg0,
                                 Object arg1,
                                 Object arg2
        );
        public double objc_msgSend_fpret(Pointer theReceiver, Pointer theSelector,
                                         Object arg0,
                                         Object arg1,
                                         Object arg2
        );
    }

    public static interface Runtime0000 extends Library {
        public static Runtime0000 INSTANCE = (Runtime0000) Native.loadLibrary("objc.A", Runtime0000.class);

        public long objc_msgSend(Pointer theReceiver, Pointer theSelector,
                                 Object arg0,
                                 Object arg1,
                                 Object arg2,
                                 Object arg3
        );
        public double objc_msgSend_fpret(Pointer theReceiver, Pointer theSelector,
                                         Object arg0,
                                         Object arg1,
                                         Object arg2,
                                         Object arg3
        );
    }

    public static interface Runtime0001 extends Library {
        public static Runtime0001 INSTANCE = (Runtime0001) Native.loadLibrary("objc.A", Runtime0001.class);

        public long objc_msgSend(Pointer theReceiver, Pointer theSelector,
                                 Object arg0,
                                 Object arg1,
                                 Object arg2,
                                 Structure.ByValue arg3
        );
        public double objc_msgSend_fpret(Pointer theReceiver, Pointer theSelector,
                                         Object arg0,
                                         Object arg1,
                                         Object arg2,
                                         Structure.ByValue arg3
        );
    }

    public static interface Runtime001 extends Library {
        public static Runtime001 INSTANCE = (Runtime001) Native.loadLibrary("objc.A", Runtime001.class);

        public long objc_msgSend(Pointer theReceiver, Pointer theSelector,
                                 Object arg0,
                                 Object arg1,
                                 Structure.ByValue arg2
        );
        public double objc_msgSend_fpret(Pointer theReceiver, Pointer theSelector,
                                         Object arg0,
                                         Object arg1,
                                         Structure.ByValue arg2
        );
    }

    public static interface Runtime0010 extends Library {
        public static Runtime0010 INSTANCE = (Runtime0010) Native.loadLibrary("objc.A", Runtime0010.class);

        public long objc_msgSend(Pointer theReceiver, Pointer theSelector,
                                 Object arg0,
                                 Object arg1,
                                 Structure.ByValue arg2,
                                 Object arg3
        );
        public double objc_msgSend_fpret(Pointer theReceiver, Pointer theSelector,
                                         Object arg0,
                                         Object arg1,
                                         Structure.ByValue arg2,
                                         Object arg3
        );
    }

    public static interface Runtime0011 extends Library {
        public static Runtime0011 INSTANCE = (Runtime0011) Native.loadLibrary("objc.A", Runtime0011.class);

        public long objc_msgSend(Pointer theReceiver, Pointer theSelector,
                                 Object arg0,
                                 Object arg1,
                                 Structure.ByValue arg2,
                                 Structure.ByValue arg3
        );
        public double objc_msgSend_fpret(Pointer theReceiver, Pointer theSelector,
                                         Object arg0,
                                         Object arg1,
                                         Structure.ByValue arg2,
                                         Structure.ByValue arg3
        );
    }

    public static interface Runtime01 extends Library {
        public static Runtime01 INSTANCE = (Runtime01) Native.loadLibrary("objc.A", Runtime01.class);

        public long objc_msgSend(Pointer theReceiver, Pointer theSelector,
                                 Object arg0,
                                 Structure.ByValue arg1
        );
        public double objc_msgSend_fpret(Pointer theReceiver, Pointer theSelector,
                                         Object arg0,
                                         Structure.ByValue arg1
        );
    }

    public static interface Runtime010 extends Library {
        public static Runtime010 INSTANCE = (Runtime010) Native.loadLibrary("objc.A", Runtime010.class);

        public long objc_msgSend(Pointer theReceiver, Pointer theSelector,
                                 Object arg0,
                                 Structure.ByValue arg1,
                                 Object arg2
        );
        public double objc_msgSend_fpret(Pointer theReceiver, Pointer theSelector,
                                         Object arg0,
                                         Structure.ByValue arg1,
                                         Object arg2
        );
    }

    public static interface Runtime0100 extends Library {
        public static Runtime0100 INSTANCE = (Runtime0100) Native.loadLibrary("objc.A", Runtime0100.class);

        public long objc_msgSend(Pointer theReceiver, Pointer theSelector,
                                 Object arg0,
                                 Structure.ByValue arg1,
                                 Object arg2,
                                 Object arg3
        );
        public double objc_msgSend_fpret(Pointer theReceiver, Pointer theSelector,
                                         Object arg0,
                                         Structure.ByValue arg1,
                                         Object arg2,
                                         Object arg3
        );
    }

    public static interface Runtime0101 extends Library {
        public static Runtime0101 INSTANCE = (Runtime0101) Native.loadLibrary("objc.A", Runtime0101.class);

        public long objc_msgSend(Pointer theReceiver, Pointer theSelector,
                                 Object arg0,
                                 Structure.ByValue arg1,
                                 Object arg2,
                                 Structure.ByValue arg3
        );
        public double objc_msgSend_fpret(Pointer theReceiver, Pointer theSelector,
                                         Object arg0,
                                         Structure.ByValue arg1,
                                         Object arg2,
                                         Structure.ByValue arg3
        );
    }

    public static interface Runtime011 extends Library {
        public static Runtime011 INSTANCE = (Runtime011) Native.loadLibrary("objc.A", Runtime011.class);

        public long objc_msgSend(Pointer theReceiver, Pointer theSelector,
                                 Object arg0,
                                 Structure.ByValue arg1,
                                 Structure.ByValue arg2
        );
        public double objc_msgSend_fpret(Pointer theReceiver, Pointer theSelector,
                                         Object arg0,
                                         Structure.ByValue arg1,
                                         Structure.ByValue arg2
        );
    }

    public static interface Runtime0110 extends Library {
        public static Runtime0110 INSTANCE = (Runtime0110) Native.loadLibrary("objc.A", Runtime0110.class);

        public long objc_msgSend(Pointer theReceiver, Pointer theSelector,
                                 Object arg0,
                                 Structure.ByValue arg1,
                                 Structure.ByValue arg2,
                                 Object arg3
        );
        public double objc_msgSend_fpret(Pointer theReceiver, Pointer theSelector,
                                         Object arg0,
                                         Structure.ByValue arg1,
                                         Structure.ByValue arg2,
                                         Object arg3
        );
    }

    public static interface Runtime0111 extends Library {
        public static Runtime0111 INSTANCE = (Runtime0111) Native.loadLibrary("objc.A", Runtime0111.class);

        public long objc_msgSend(Pointer theReceiver, Pointer theSelector,
                                 Object arg0,
                                 Structure.ByValue arg1,
                                 Structure.ByValue arg2,
                                 Structure.ByValue arg3
        );
        public double objc_msgSend_fpret(Pointer theReceiver, Pointer theSelector,
                                         Object arg0,
                                         Structure.ByValue arg1,
                                         Structure.ByValue arg2,
                                         Structure.ByValue arg3
        );
    }

    public static interface Runtime1 extends Library {
        public static Runtime1 INSTANCE = (Runtime1) Native.loadLibrary("objc.A", Runtime1.class);

        public long objc_msgSend(Pointer theReceiver, Pointer theSelector,
                                 Structure.ByValue arg0
        );
        public double objc_msgSend_fpret(Pointer theReceiver, Pointer theSelector,
                                         Structure.ByValue arg0
        );
    }

    public static interface Runtime10 extends Library {
        public static Runtime10 INSTANCE = (Runtime10) Native.loadLibrary("objc.A", Runtime10.class);

        public long objc_msgSend(Pointer theReceiver, Pointer theSelector,
                                 Structure.ByValue arg0,
                                 Object arg1
        );
        public double objc_msgSend_fpret(Pointer theReceiver, Pointer theSelector,
                                         Structure.ByValue arg0,
                                         Object arg1
        );
    }

    public static interface Runtime100 extends Library {
        public static Runtime100 INSTANCE = (Runtime100) Native.loadLibrary("objc.A", Runtime100.class);

        public long objc_msgSend(Pointer theReceiver, Pointer theSelector,
                                 Structure.ByValue arg0,
                                 Object arg1,
                                 Object arg2
        );
        public double objc_msgSend_fpret(Pointer theReceiver, Pointer theSelector,
                                         Structure.ByValue arg0,
                                         Object arg1,
                                         Object arg2
        );
    }

    public static interface Runtime1000 extends Library {
        public static Runtime1000 INSTANCE = (Runtime1000) Native.loadLibrary("objc.A", Runtime1000.class);

        public long objc_msgSend(Pointer theReceiver, Pointer theSelector,
                                 Structure.ByValue arg0,
                                 Object arg1,
                                 Object arg2,
                                 Object arg3
        );
        public double objc_msgSend_fpret(Pointer theReceiver, Pointer theSelector,
                                         Structure.ByValue arg0,
                                         Object arg1,
                                         Object arg2,
                                         Object arg3
        );
    }

    public static interface Runtime1001 extends Library {
        public static Runtime1001 INSTANCE = (Runtime1001) Native.loadLibrary("objc.A", Runtime1001.class);

        public long objc_msgSend(Pointer theReceiver, Pointer theSelector,
                                 Structure.ByValue arg0,
                                 Object arg1,
                                 Object arg2,
                                 Structure.ByValue arg3
        );
        public double objc_msgSend_fpret(Pointer theReceiver, Pointer theSelector,
                                         Structure.ByValue arg0,
                                         Object arg1,
                                         Object arg2,
                                         Structure.ByValue arg3
        );
    }

    public static interface Runtime101 extends Library {
        public static Runtime101 INSTANCE = (Runtime101) Native.loadLibrary("objc.A", Runtime101.class);

        public long objc_msgSend(Pointer theReceiver, Pointer theSelector,
                                 Structure.ByValue arg0,
                                 Object arg1,
                                 Structure.ByValue arg2
        );
        public double objc_msgSend_fpret(Pointer theReceiver, Pointer theSelector,
                                         Structure.ByValue arg0,
                                         Object arg1,
                                         Structure.ByValue arg2
        );
    }

    public static interface Runtime1010 extends Library {
        public static Runtime1010 INSTANCE = (Runtime1010) Native.loadLibrary("objc.A", Runtime1010.class);

        public long objc_msgSend(Pointer theReceiver, Pointer theSelector,
                                 Structure.ByValue arg0,
                                 Object arg1,
                                 Structure.ByValue arg2,
                                 Object arg3
        );
        public double objc_msgSend_fpret(Pointer theReceiver, Pointer theSelector,
                                         Structure.ByValue arg0,
                                         Object arg1,
                                         Structure.ByValue arg2,
                                         Object arg3
        );
    }

    public static interface Runtime1011 extends Library {
        public static Runtime1011 INSTANCE = (Runtime1011) Native.loadLibrary("objc.A", Runtime1011.class);

        public long objc_msgSend(Pointer theReceiver, Pointer theSelector,
                                 Structure.ByValue arg0,
                                 Object arg1,
                                 Structure.ByValue arg2,
                                 Structure.ByValue arg3
        );
        public double objc_msgSend_fpret(Pointer theReceiver, Pointer theSelector,
                                         Structure.ByValue arg0,
                                         Object arg1,
                                         Structure.ByValue arg2,
                                         Structure.ByValue arg3
        );
    }

    public static interface Runtime11 extends Library {
        public static Runtime11 INSTANCE = (Runtime11) Native.loadLibrary("objc.A", Runtime11.class);

        public long objc_msgSend(Pointer theReceiver, Pointer theSelector,
                                 Structure.ByValue arg0,
                                 Structure.ByValue arg1
        );
        public double objc_msgSend_fpret(Pointer theReceiver, Pointer theSelector,
                                         Structure.ByValue arg0,
                                         Structure.ByValue arg1
        );
    }

    public static interface Runtime110 extends Library {
        public static Runtime110 INSTANCE = (Runtime110) Native.loadLibrary("objc.A", Runtime110.class);

        public long objc_msgSend(Pointer theReceiver, Pointer theSelector,
                                 Structure.ByValue arg0,
                                 Structure.ByValue arg1,
                                 Object arg2
        );
        public double objc_msgSend_fpret(Pointer theReceiver, Pointer theSelector,
                                         Structure.ByValue arg0,
                                         Structure.ByValue arg1,
                                         Object arg2
        );
    }

    public static interface Runtime1100 extends Library {
        public static Runtime1100 INSTANCE = (Runtime1100) Native.loadLibrary("objc.A", Runtime1100.class);

        public long objc_msgSend(Pointer theReceiver, Pointer theSelector,
                                 Structure.ByValue arg0,
                                 Structure.ByValue arg1,
                                 Object arg2,
                                 Object arg3
        );
        public double objc_msgSend_fpret(Pointer theReceiver, Pointer theSelector,
                                         Structure.ByValue arg0,
                                         Structure.ByValue arg1,
                                         Object arg2,
                                         Object arg3
        );
    }

    public static interface Runtime1101 extends Library {
        public static Runtime1101 INSTANCE = (Runtime1101) Native.loadLibrary("objc.A", Runtime1101.class);

        public long objc_msgSend(Pointer theReceiver, Pointer theSelector,
                                 Structure.ByValue arg0,
                                 Structure.ByValue arg1,
                                 Object arg2,
                                 Structure.ByValue arg3
        );
        public double objc_msgSend_fpret(Pointer theReceiver, Pointer theSelector,
                                         Structure.ByValue arg0,
                                         Structure.ByValue arg1,
                                         Object arg2,
                                         Structure.ByValue arg3
        );
    }

    public static interface Runtime111 extends Library {
        public static Runtime111 INSTANCE = (Runtime111) Native.loadLibrary("objc.A", Runtime111.class);

        public long objc_msgSend(Pointer theReceiver, Pointer theSelector,
                                 Structure.ByValue arg0,
                                 Structure.ByValue arg1,
                                 Structure.ByValue arg2
        );
        public double objc_msgSend_fpret(Pointer theReceiver, Pointer theSelector,
                                         Structure.ByValue arg0,
                                         Structure.ByValue arg1,
                                         Structure.ByValue arg2
        );
    }

    public static interface Runtime1110 extends Library {
        public static Runtime1110 INSTANCE = (Runtime1110) Native.loadLibrary("objc.A", Runtime1110.class);

        public long objc_msgSend(Pointer theReceiver, Pointer theSelector,
                                 Structure.ByValue arg0,
                                 Structure.ByValue arg1,
                                 Structure.ByValue arg2,
                                 Object arg3
        );
        public double objc_msgSend_fpret(Pointer theReceiver, Pointer theSelector,
                                         Structure.ByValue arg0,
                                         Structure.ByValue arg1,
                                         Structure.ByValue arg2,
                                         Object arg3
        );
    }

    public static interface Runtime1111 extends Library {
        public static Runtime1111 INSTANCE = (Runtime1111) Native.loadLibrary("objc.A", Runtime1111.class);

        public long objc_msgSend(Pointer theReceiver, Pointer theSelector,
                                 Structure.ByValue arg0,
                                 Structure.ByValue arg1,
                                 Structure.ByValue arg2,
                                 Structure.ByValue arg3
        );
        public double objc_msgSend_fpret(Pointer theReceiver, Pointer theSelector,
                                         Structure.ByValue arg0,
                                         Structure.ByValue arg1,
                                         Structure.ByValue arg2,
                                         Structure.ByValue arg3
        );
    }
}