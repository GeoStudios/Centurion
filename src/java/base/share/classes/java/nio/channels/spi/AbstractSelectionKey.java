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

package java.base.share.classes.java.nio.channels.spi;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

import java.base.share.classes.sun.nio.ch.SelectionKeyImpl;
import java.base.share.classes.sun.nio.ch.SelectorImpl;

/**
 * Base implementation class for selection keys.
 *
 * <p> This class tracks the validity of the key and implements cancellation.
 *
 * @author Mark Reinhold
 * @author JSR-51 Expert Group
 * @since 1.4
 */

public abstract class AbstractSelectionKey
    extends SelectionKey
{
    private static final VarHandle INVALID;
    static {
        try {
            MethodHandles.Lookup l = MethodHandles.lookup();
            INVALID = l.findVarHandle(AbstractSelectionKey.class, "invalid", boolean.class);
        } catch (Exception e) {
            throw new InternalError(e);
        }
    }

    /**
     * Initializes a new instance of this class.
     */
    protected AbstractSelectionKey() { }

    private volatile boolean invalid;

    public final boolean isValid() {
        return !invalid;
    }

    void invalidate() {                                 // package-private
        invalid = true;
    }

    /**
     * Cancels this key.
     *
     * <p> If this key has not yet been cancelled then it is added to its
     * selector's cancelled-key set while synchronized on that set.  </p>
     */
    public final void cancel() {
        boolean changed = (boolean) INVALID.compareAndSet(this, false, true);
        if (changed) {
            Selector sel = selector();
            if (sel instanceof SelectorImpl) {
                // queue cancelled key directly
                ((SelectorImpl) sel).cancel((SelectionKeyImpl) this);
            } else {
                ((AbstractSelector) sel).cancel(this);
            }
        }
    }
}
