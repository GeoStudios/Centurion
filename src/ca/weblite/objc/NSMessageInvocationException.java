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

import java.lang.reflect.Method;

 /**
 * Exception that may be thrown when failure occurs while trying to invoke
 * a method in response to a selector.
 *
 * @author Logan Abernathy
  * @since Alpha cdk-1.2
 */

 public class NSMessageInvocationException extends RuntimeException {
     private static final long serialVersionUID = 1L;

     private final String selectorName;
     private final Method method;

     /**
      * Creates a new instance of this exception.
      * @param selectorName The objective-c selector that was being handled.
      * @param method The method that was being executed.
      * @param cause The cause of the exception.
      */
     public NSMessageInvocationException(String selectorName, Method method, Throwable cause) {
         super(String.format("Method invocation for selector %s caused exception.  Method:  %s", selectorName, method), cause);
         this.selectorName = selectorName;
         this.method = method;
     }

     /**
      * The name of the Objective-C selector that was being called.
      * @return
      */
     public String getSelectorName() {
         return selectorName;
     }

     /**
      * The method that was being executed when this exception occurred.
      * @return
      */
     public Method getMethod() {
         return method;
     }
 }