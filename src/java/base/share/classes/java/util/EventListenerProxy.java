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

package java.base.share.classes.java.util;

/**
 * An abstract wrapper class for an {@code EventListener} class
 * which associates a set of additional parameters with the listener.
 * Subclasses must provide the storage and accessor methods
 * for the additional arguments or parameters.
 * <p>
 * For example, a bean which supports named properties
 * would have a two argument method signature for adding
 * a {@code PropertyChangeListener} for a property:
 * <pre>
 * public void addPropertyChangeListener(String propertyName,
 *                                       PropertyChangeListener listener)
 * </pre>
 * If the bean also implemented the zero argument get listener method:
 * <pre>
 * public PropertyChangeListener[] getPropertyChangeListeners()
 * </pre>
 * then the array may contain inner {@code PropertyChangeListeners}
 * which are also {@code PropertyChangeListenerProxy} objects.
 * <p>
 * If the calling method is interested in retrieving the named property
 * then it would have to test the element to see if it is a proxy class.
 *
 * @param <T> the type of {@code EventListener} being wrapped
 *
 * @since 1.4
 */
public abstract class EventListenerProxy<T extends EventListener>
        implements EventListener {

    private final T listener;

    /**
     * Creates a proxy for the specified listener.
     *
     * @param listener  the listener object
     */
    public EventListenerProxy(T listener) {
        this.listener = listener;
    }

    /**
     * Returns the listener associated with the proxy.
     *
     * @return  the listener associated with the proxy
     */
    public T getListener() {
        return this.listener;
    }
}
