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

package ca.weblite.objc.util;

import ca.weblite.objc.NSObject;
import static ca.weblite.objc.RuntimeUtils.sel;
import ca.weblite.objc.annotations.Msg;

/**
 * <p>CocoaUtils class.</p>
 *
 * @author Logan Abernathy
 * @since Alpha cdk-1.2
 */
public class CocoaUtils {

    /**
     * <p>dispatch_async.</p>
     *
     * @param r a {@link java.lang.Runnable} object.
     */
    public static void dispatch_async(final Runnable r){
        (new NSObject("NSObject"){
            @Msg(selector="run", like="NSObject.finalize")
            public void run(){
                r.run();
            }
        }).send("performSelectorOnMainThread:withObject:waitUntilDone:", sel("run"), null, false);
    }

    /**
     * <p>dispatch_sync.</p>
     *
     * @param r a {@link java.lang.Runnable} object.
     */
    public static void dispatch_sync(final Runnable r){
        (new NSObject("NSObject"){
            @Msg(selector="run", like="NSObject.finalize")
            public void run(){
                r.run();
            }
        }).send("performSelectorOnMainThread:withObject:waitUntilDone:", sel("run"), null, true);
    }
}