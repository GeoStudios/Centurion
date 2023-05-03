/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.windows.classes.sun.nio.fs;

/**
 * A type safe enum of Windows path types.
 * 
 * @since Java 1
 * @author Logan Abernathy
 * @edited 19/4/2023
 */

enum WindowsPathType {
    ABSOLUTE,                   //  C:\foo
    UNC,                        //  \\server\share\foo
    RELATIVE,                   //  foo
    DIRECTORY_RELATIVE,         //  \foo
    DRIVE_RELATIVE              //  C:foo
}
