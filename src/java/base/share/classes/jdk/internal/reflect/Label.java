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

package java.base.share.classes.jdk.internal.reflect;

import java.util.List;
import java.util.ArrayList;

/** Allows forward references in bytecode streams emitted by
    ClassFileAssembler. Assumes that the start of the method body is
    the first byte in the assembler's buffer. May be used at more than
    one branch site. */

class Label {
    static class PatchInfo {
        PatchInfo(ClassFileAssembler asm,
                  short instrBCI,
                  short patchBCI,
                  int stackDepth)
        {
            this.asm = asm;
            this.instrBCI   = instrBCI;
            this.patchBCI   = patchBCI;
            this.stackDepth = stackDepth;
        }
        // This won't work for more than one assembler anyway, so this is
        // unnecessary
        final ClassFileAssembler asm;
        final short instrBCI;
        final short patchBCI;
        final int   stackDepth;
    }
    private final List<PatchInfo> patches = new ArrayList<>();

    public Label() {
    }

    void add(ClassFileAssembler asm,
             short instrBCI,
             short patchBCI,
             int stackDepth)
    {
        patches.add(new PatchInfo(asm, instrBCI, patchBCI, stackDepth));
    }

    public void bind() {
        for (PatchInfo patch : patches){
            short curBCI = patch.asm.getLength();
            short offset = (short) (curBCI - patch.instrBCI);
            patch.asm.emitShort(patch.patchBCI, offset);
            patch.asm.setStack(patch.stackDepth);
        }
    }
}
