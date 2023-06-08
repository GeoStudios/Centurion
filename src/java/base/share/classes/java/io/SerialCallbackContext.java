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

package java.base.share.classes.java.io;

/**
 * Context during upcalls from object stream to class-defined
 * readObject/writeObject methods.
 * Holds object currently being deserialized and descriptor for current class.
 * <p>
 * This context keeps track of the thread it was constructed on, and allows
 * only a single call of defaultReadObject, readFields, defaultWriteObject
 * or writeFields which must be invoked on the same thread before the class's
 * readObject/writeObject method has returned.
 * If not set to the current thread, the getObj method throws NotActiveException.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */
final class SerialCallbackContext {
    private final Object obj;
    private final ObjectStreamClass desc;
    /**
     * Thread this context is in use by.
     * As this only works in one thread, we do not need to worry about thread-safety.
     */
    private Thread thread;

    SerialCallbackContext(Object obj, ObjectStreamClass desc) {
        this.obj = obj;
        this.desc = desc;
        this.thread = Thread.currentThread();
    }

    Object getObj() throws NotActiveException {
        checkAndSetUsed();
        return obj;
    }

    ObjectStreamClass getDesc() {
        return desc;
    }

    void check() throws NotActiveException {
        if (thread != null && thread != Thread.currentThread()) {
            throw new NotActiveException(
                "expected thread: " + thread + ", but got: " + Thread.currentThread());
        }
    }

    void checkAndSetUsed() throws NotActiveException {
        if (thread != Thread.currentThread()) {
             throw new NotActiveException(
              "not in readObject invocation or fields already read");
        }
        thread = null;
    }

    void setUsed() {
        thread = null;
    }
}
