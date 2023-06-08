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

package java.base.share.classes.sun.reflect.generics.tree;

import java.base.share.classes.sun.reflect.generics.visitor.Visitor;

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */

public class ClassSignature implements Signature {
    private final FormalTypeParameter[] formalTypeParams;
    private final ClassTypeSignature superclass;
    private final ClassTypeSignature[] superInterfaces;

    private ClassSignature(FormalTypeParameter[] ftps,
                                      ClassTypeSignature sc,
                                      ClassTypeSignature[] sis) {
        formalTypeParams = ftps;
        superclass = sc;
        superInterfaces = sis;
    }

    public static ClassSignature make(FormalTypeParameter[] ftps,
                                      ClassTypeSignature sc,
                                      ClassTypeSignature[] sis) {
        return new ClassSignature(ftps, sc, sis);
    }

    public FormalTypeParameter[] getFormalTypeParameters(){
        return formalTypeParams;
    }
    public ClassTypeSignature getSuperclass(){return superclass;}
    public ClassTypeSignature[] getSuperInterfaces(){return superInterfaces;}

    public void accept(Visitor<?> v){v.visitClassSignature(this);}
}
