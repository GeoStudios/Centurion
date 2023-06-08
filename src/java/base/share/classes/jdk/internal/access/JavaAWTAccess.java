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

package java.base.share.classes.jdk.internal.access;

public interface JavaAWTAccess {

    // Returns the AppContext used for applet logging isolation, or null if
    // no isolation is required.
    // If there's no applet, or if the caller is a stand alone application,
    // or running in the main app context, returns null.
    // Otherwise, returns the AppContext of the calling applet.
    public Object getAppletContext();
}
