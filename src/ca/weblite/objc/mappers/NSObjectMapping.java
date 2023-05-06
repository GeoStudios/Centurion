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

package ca.weblite.objc.mappers;

import com.sun.jna.Pointer;

import ca.weblite.objc.Peerable;
import ca.weblite.objc.Proxy;
import ca.weblite.objc.Runtime;
import ca.weblite.objc.RuntimeUtils;
import ca.weblite.objc.TypeMapping;
import ca.weblite.objc.jna.PointerTool;

/**
 * <p>NSObjectMapping class.</p>
 *
 * @author Logan Abernathy
 * @since Alpha cdk-1.2
 */
public class NSObjectMapping implements TypeMapping {
    /**
     * Singleton instance.
     */
    public static final NSObjectMapping INSTANCE = new NSObjectMapping();

    private NSObjectMapping() { }

    @Override
    public Object cToJ(Object cVar, String signature, TypeMapping root) {
        //System.out.println("Mapping NSObject to Java "+cVar+" sig: "+signature);
        Pointer cObj = Pointer.NULL;
        if (cVar instanceof Pointer) {
            cObj = (Pointer)cVar;
        } else if (cVar instanceof Long) {
            cObj = new Pointer((Long) cVar);
        } else {
            return cVar;
        }
        if ( (Pointer.NULL == cObj) || (cVar == null) || (cObj == null) || (PointerTool.getPeer(cObj) == 0L ) ){
            //System.out.println("The java value will be null");
            return null;
        }
        String clsName = Runtime.INSTANCE.object_getClassName(cObj);
        boolean isString = "NSString".equals(clsName) || "NSTaggedPointerString".equals(clsName) || "NSMutableString".equals(clsName) || "__NSCFString".equals(clsName);


        ////System.out.println("Checking if object is a string "+isString+", "+clsName);
        if ( isString  ){
            return RuntimeUtils.str(cObj);
        }
        Object peer = RuntimeUtils.getJavaPeer(PointerTool.getPeer(cObj));
        if ( peer == null ){
            return Proxy.load(cObj);
        } else {
            return peer;
        }
    }

    @Override
    public Object jToC(Object jVar, String signature, TypeMapping root) {
        if ( jVar == null ){
            return Pointer.NULL;
        }
        if (jVar instanceof String) {
            return RuntimeUtils.str((String)jVar);
        }
        if (jVar instanceof Peerable) {
            return ((Peerable)jVar).getPeer();
        } else if (jVar instanceof Pointer) {
            return jVar;
        } else {
            throw new IllegalArgumentException("Unsupported value: " + jVar);
        }
    }
}