/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

/**
 * An object that interrupts a thread blocked in an I/O operation.
 */

package sun.nio.ch;

public interface Interruptible {

    public void interrupt(Thread t);

}
