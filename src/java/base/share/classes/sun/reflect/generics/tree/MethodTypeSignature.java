/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.reflect.generics.tree;

import java.base.share.classes.sun.reflect.generics.visitor.Visitor;

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */

public class MethodTypeSignature implements Signature {
    private final FormalTypeParameter[] formalTypeParams;
    private final TypeSignature[] parameterTypes;
    private final ReturnType returnType;
    private final FieldTypeSignature[] exceptionTypes;

    private MethodTypeSignature(FormalTypeParameter[] ftps,
                                TypeSignature[] pts,
                                ReturnType rt,
                                FieldTypeSignature[] ets) {
        formalTypeParams = ftps;
        parameterTypes = pts;
        returnType = rt;
        exceptionTypes = ets;
    }

    public static MethodTypeSignature make(FormalTypeParameter[] ftps,
                                           TypeSignature[] pts,
                                           ReturnType rt,
                                           FieldTypeSignature[] ets) {
        return new MethodTypeSignature(ftps, pts, rt, ets);
    }

    public FormalTypeParameter[] getFormalTypeParameters(){
        return formalTypeParams;
    }
    public TypeSignature[] getParameterTypes(){return parameterTypes;}
    public ReturnType getReturnType(){return returnType;}
    public FieldTypeSignature[] getExceptionTypes(){return exceptionTypes;}

    public void accept(Visitor<?> v){v.visitMethodTypeSignature(this);}
}
