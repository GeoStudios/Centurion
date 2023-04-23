/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package jdk.internal.access;

public interface JavaAWTAccess {

    // Returns the AppContext used for applet logging isolation, or null if
    // no isolation is required.
    // If there's no applet, or if the caller is a stand alone application,
    // or running in the main app context, returns null.
    // Otherwise, returns the AppContext of the calling applet.
    public Object getAppletContext();
}
