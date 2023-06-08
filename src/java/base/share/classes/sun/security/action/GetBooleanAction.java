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

package java.base.share.classes.sun.security.action;

import java.security.AccessController;

/**
 * A convenience class for retrieving the boolean value of a system property
 * as a privileged action.
 *
 * <p>An instance of this class can be used as the argument of
 * <code>AccessController.doPrivileged</code>.
 *
 * <p>The following code retrieves the boolean value of the system
 * property named <code>"prop"</code> as a privileged action:
 *
 * <pre>
 * boolean b = java.security.AccessController.doPrivileged
 *              (new GetBooleanAction("prop")).booleanValue();
 * </pre>
 *
 * @see java.security.PrivilegedAction
 * @see java.security.AccessController
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */

public class GetBooleanAction
        implements java.security.PrivilegedAction<Boolean> {
    private final String theProp;

    /**
     * Constructor that takes the name of the system property whose boolean
     * value needs to be determined.
     *
     * @param theProp the name of the system property.
     */
    public GetBooleanAction(String theProp) {
        this.theProp = theProp;
    }

    /**
     * Determines the boolean value of the system property whose name was
     * specified in the constructor.
     *
     * @return the <code>Boolean</code> value of the system property.
     */
    public Boolean run() {
        return Boolean.getBoolean(theProp);
    }

    /**
     * Convenience method to get a property without going through doPrivileged
     * if no security manager is present. This is unsafe for inclusion in a
     * public API but allowable here since this class is now encapsulated.
     *
     * Note that this method performs a privileged action using caller-provided
     * inputs. The caller of this method should take care to ensure that the
     * inputs are not tainted and the returned property is not made accessible
     * to untrusted code if it contains sensitive information.
     *
     * @param theProp the name of the system property.
     */
    @SuppressWarnings("removal")
    public static boolean privilegedGetProperty(String theProp) {
        if (System.getSecurityManager() == null) {
            return Boolean.getBoolean(theProp);
        } else {
            return AccessController.doPrivileged(
                    new GetBooleanAction(theProp));
        }
    }
}
