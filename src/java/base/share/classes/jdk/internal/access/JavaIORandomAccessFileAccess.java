/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package jdk.internal.access;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public interface JavaIORandomAccessFileAccess {
    public RandomAccessFile openAndDelete(File file, String mode)
        throws IOException;
}
