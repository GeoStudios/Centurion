/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.action;

/**
 * A convenience class for retrieving the <code>Long</code> value of a system
 * property as a privileged action.
 *
 * <p>An instance of this class can be used as the argument of
 * <code>AccessController.doPrivileged</code>.
 *
 * <p>The following code retrieves the <code>Long</code> value of the system
 * property named <code>"prop"</code> as a privileged action. Since it does
 * not pass a default value to be used in case the property
 * <code>"prop"</code> is not defined, it has to check the result for
 * <code>null</code>:
 *
 * <pre>
 * Long tmp = java.security.AccessController.doPrivileged
 *     (new sun.security.action.GetLongAction("prop"));
 * long l;
 * if (tmp != null) {
 *     l = tmp.longValue();
 * }
 * </pre>
 *
 * <p>The following code retrieves the <code>Long</code> value of the system
 * property named <code>"prop"</code> as a privileged action, and also passes
 * a default value to be used in case the property <code>"prop"</code> is not
 * defined:
 *
 * <pre>
 * long l = java.security.AccessController.doPrivileged
 *      (new GetLongAction("prop")).longValue();
 * </pre>
 *
 * @since Java 2
 * @author Logan Abernathy
 * @edited 22/4/2023 
 * @see java.security.PrivilegedAction
 * @see java.security.AccessController
 */

public class GetLongAction implements java.security.PrivilegedAction<Long> {
    private final String theProp;
    private final long defaultVal;
    private final boolean defaultSet;

    /**
     * Constructor that takes the name of the system property whose
     * <code>Long</code> value needs to be determined.
     *
     * @param theProp the name of the system property.
     */
    public GetLongAction(String theProp) {
        this.theProp = theProp;
        this.defaultVal = 0;
        this.defaultSet = false;
    }

    /**
     * Constructor that takes the name of the system property and the default
     * value of that property.
     *
     * @param theProp the name of the system property.
     * @param defaultVal the default value.
     */
    public GetLongAction(String theProp, long defaultVal) {
        this.theProp = theProp;
        this.defaultVal = defaultVal;
        this.defaultSet = true;
    }

    /**
     * Determines the <code>Long</code> value of the system property whose
     * name was specified in the constructor.
     *
     * <p>If there is no property of the specified name, or if the property
     * does not have the correct numeric format, then a <code>Long</code>
     * object representing the default value that was specified in the
     * constructor is returned, or <code>null</code> if no default value was
     * specified.
     *
     * @return the <code>Long</code> value of the property.
     */
    public Long run() {
        Long value = Long.getLong(theProp);
        if ((value == null) && defaultSet)
            return defaultVal;
        return value;
    }
}
