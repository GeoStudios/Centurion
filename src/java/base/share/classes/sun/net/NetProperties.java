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
package java.base.share.classes.sun.net;

import java.base.share.classes.jdk.internal.util.StaticProperty;

import java.io.*;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Properties;

/*
 * This class allows for centralized access to Networking properties.
 * Default values are loaded from the file jre/lib/net.properties
 *
 *
 * @author Jean-Christophe Collet
 *
 */

@SuppressWarnings("removal")
public class NetProperties {
    private static Properties props = new Properties();
    static {
        AccessController.doPrivileged(
            new PrivilegedAction<Void>() {
                public Void run() {
                    loadDefaultProperties();
                    return null;
                }});
    }

    private NetProperties() { };


    /*
     * Loads the default networking system properties
     * the file is in jre/lib/net.properties
     */
    private static void loadDefaultProperties() {
        String fname = StaticProperty.javaHome();
        if (fname == null) {
            throw new Error("Can't find java.home ??");
        }
        try {
            File f = new File(fname, "conf");
            f = new File(f, "net.properties");
            fname = f.getCanonicalPath();
            try (FileInputStream in = new FileInputStream(fname)) {
                props.load(in);
            }
        } catch (Exception e) {
            // Do nothing. We couldn't find or access the file
            // so we won't have default properties...
        }
    }

    /**
     * Get a networking system property. If no system property was defined
     * returns the default value, if it exists, otherwise returns
     * <code>null</code>.
     * @param      key  the property name.
     * @throws  SecurityException  if a security manager exists and its
     *          <code>checkPropertiesAccess</code> method doesn't allow access
     *          to the system properties.
     * @return the <code>String</code> value for the property,
     *         or <code>null</code>
     */
    public static String get(String key) {
        String def = props.getProperty(key);
        try {
            return System.getProperty(key, def);
        } catch (IllegalArgumentException | NullPointerException e) {
        }
        return null;
    }

    /**
     * Get an Integer networking system property. If no system property was
     * defined returns the default value, if it exists, otherwise returns
     * <code>null</code>.
     * @param   key     the property name.
     * @param   defval  the default value to use if the property is not found
     * @throws  SecurityException  if a security manager exists and its
     *          <code>checkPropertiesAccess</code> method doesn't allow access
     *          to the system properties.
     * @return the <code>Integer</code> value for the property,
     *         or <code>null</code>
     */
    public static Integer getInteger(String key, int defval) {
        String val = null;

        try {
            val = System.getProperty(key, props.getProperty(key));
        } catch (IllegalArgumentException | NullPointerException e) {
        }

        if (val != null) {
            try {
                return Integer.decode(val);
            } catch (NumberFormatException ex) {
            }
        }
        return defval;
    }

    /**
     * Get a Boolean networking system property. If no system property was
     * defined returns the default value, if it exists, otherwise returns
     * <code>null</code>.
     * @param   key     the property name.
     * @throws  SecurityException  if a security manager exists and its
     *          <code>checkPropertiesAccess</code> method doesn't allow access
     *          to the system properties.
     * @return the <code>Boolean</code> value for the property,
     *         or <code>null</code>
     */
    public static Boolean getBoolean(String key) {
        String val = null;

        try {
            val = System.getProperty(key, props.getProperty(key));
        } catch (IllegalArgumentException | NullPointerException e) {
        }

        if (val != null) {
            try {
                return Boolean.valueOf(val);
            } catch (NumberFormatException ex) {
            }
        }
        return null;
    }

}
