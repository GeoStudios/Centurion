/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.jdk.internal.access;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Interface to specify methods for accessing {@code ObjectInputStream}.
 */
@FunctionalInterface
public interface JavaObjectInputStreamReadString {
    String readString(ObjectInputStream ois) throws IOException;
}

