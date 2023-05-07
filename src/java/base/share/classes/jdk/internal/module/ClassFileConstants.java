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

package java.base.share.classes.jdk.internal.module;


// Constants in module-info.class files

public class ClassFileConstants {

    private ClassFileConstants() { }

    // Attribute names
    public static final String MODULE             = "Module";
    public static final String SOURCE_FILE        = "SourceFile";
    public static final String SDE                = "SourceDebugExtension";

    public static final String MODULE_PACKAGES    = "ModulePackages";
    public static final String MODULE_MAIN_CLASS  = "ModuleMainClass";
    public static final String MODULE_TARGET      = "ModuleTarget";
    public static final String MODULE_HASHES      = "ModuleHashes";
    public static final String MODULE_RESOLUTION  = "ModuleResolution";

    // access, requires, exports, and opens flags
    public static final int ACC_MODULE        = 0x8000;
    public static final int ACC_OPEN          = 0x0020;
    public static final int ACC_TRANSITIVE    = 0x0020;
    public static final int ACC_STATIC_PHASE  = 0x0040;
    public static final int ACC_SYNTHETIC     = 0x1000;
    public static final int ACC_MANDATED      = 0x8000;

    // ModuleResolution_attribute resolution flags
    public static final int DO_NOT_RESOLVE_BY_DEFAULT   = 0x0001;
    public static final int WARN_DEPRECATED             = 0x0002;
    public static final int WARN_DEPRECATED_FOR_REMOVAL = 0x0004;
    public static final int WARN_INCUBATING             = 0x0008;

}
