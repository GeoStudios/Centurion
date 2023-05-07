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
package java.base.share.classes.jdk.internal.vm;

import java.base.share.classes.jdk.internal.access.JavaLangAccess;
import java.base.share.classes.jdk.internal.access.SharedSecrets;
import java.base.share.classes.jdk.internal.vm.annotation.DontInline;
import java.base.share.classes.jdk.internal.vm.annotation.ReservedStackAccess;

/**
 * A stackable scope to support structured constructs. The push method is used to
 * push a StackableScope to the current thread's scope stack. The tryPop and
 * popForcefully methods are used to pop the StackableScope from the current thread's
 * scope stack.
 */
public class StackableScope {
    private static final JavaLangAccess JLA = SharedSecrets.getJavaLangAccess();

    private final Thread owner;
    private volatile StackableScope previous;

    /**
     * Creates a stackable scope.
     * @param shared true for a shared scope that cannot be pushed to the stack,
     * false for scope that is owned by the current thread
     */
    StackableScope(boolean shared) {
        if (shared) {
            this.owner = null;
        } else {
            this.owner = Thread.currentThread();
        }
    }

    /**
     * Creates a stackable scope owned by the current thread.
     */
    protected StackableScope() {
        this(false);
    }

    /**
     * Returns the scope owner or null if not owned.
     */
    public Thread owner() {
        return owner;
    }

    /**
     * Pushes this scope onto the current thread's scope stack.
     * @throws WrongThreadException it the current thread is not the owner
     */
    public StackableScope push() {
        if (Thread.currentThread() != owner)
            throw new WrongThreadException("Not owner");
        previous = head();
        setHead(this);
        return this;
    }

    /**
     * Pops this scope from the current thread's scope stack if the scope is
     * at the top of stack.
     * @return true if the pop succeeded, false if this scope is not the top of stack
     * @throws WrongThreadException it the current thread is not the owner
     */
    @DontInline @ReservedStackAccess
    public boolean tryPop() {
        if (Thread.currentThread() != owner)
            throw new WrongThreadException("Not owner");
        if (head() == this) {
            setHead(previous);
            previous = null;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Pops this scope from the current thread's scope stack.
     *
     * For well-behaved usages, this scope is at the top of the stack. It is popped
     * from the stack and the method returns {@code true}.
     *
     * If this scope is not at the top of the stack then this method attempts to
     * close each of the intermediate scopes by invoking their {@link #tryClose()}
     * method. If tryClose succeeds then the scope is removed from the stack. When
     * done, this scope is removed from the stack and {@code false} is returned.
     *
     * This method does nothing, and returns {@code false}, if this scope is not
     * on the current thread's scope stack.
     *
     * @return true if this scope was at the top of the stack, otherwise false
     * @throws WrongThreadException it the current thread is not the owner
     */
    @DontInline @ReservedStackAccess
    public boolean popForcefully() {
        if (Thread.currentThread() != owner)
            throw new WrongThreadException("Not owner");
        final StackableScope head = head();
        if (head == this) {
            setHead(previous);
            previous = null;
            return true;
        }

        // scope is not the top of stack
        if (contains(this)) {
            StackableScope current = head;
            while (current != this) {
                StackableScope previous = current.previous();
                // attempt to forcefully close the scope and remove from stack
                if (current.tryClose()) {
                    current.unlink();
                }
                current = previous;
            }
            unlink();
        }
        return false;
    }

    /**
     * Pops all scopes from the current thread's scope stack.
     */
    public static void popAll() {
        StackableScope head = head();
        if (head != null) {
            StackableScope current = head;
            while (current != null) {
                assert Thread.currentThread() == current.owner();
                current.tryClose();
                current = current.previous();
            }
            setHead(null);
        }
    }

    /**
     * Returns the scope that encloses this scope.
     */
    public StackableScope enclosingScope() {
        StackableScope previous = this.previous;
        if (previous != null)
            return previous;
        if (owner != null)
            return JLA.threadContainer(owner);
        return null;
    }

    /**
     * Returns the scope of the given type that encloses this scope.
     */
    public <T extends StackableScope> T enclosingScope(Class<T> type) {
        StackableScope current = enclosingScope();
        while (current != null) {
            if (type.isInstance(current)) {
                @SuppressWarnings("unchecked")
                T tmp = (T) current;
                return tmp;
            }
            current = current.enclosingScope();
        }
        return null;
    }

    /**
     * Returns the scope that directly encloses this scope, null if none.
     */
    StackableScope previous() {
        return previous;
    }

    /**
     * Returns the scope that this scope directly encloses, null if none.
     */
    private StackableScope next() {
        assert contains(this);
        StackableScope current = head();
        StackableScope next = null;
        while (current != this) {
            next = current;
            current = current.previous();
        }
        return next;
    }

    /**
     * Override this method to close this scope and release its resources.
     * This method should not pop the scope from the stack.
     * This method is guaranteed to execute on the owner thread.
     * @return true if this method closed the scope, false if it failed
     */
    protected boolean tryClose() {
        assert Thread.currentThread() == owner;
        return false;
    }

    /**
     * Removes this scope from the current thread's scope stack.
     */
    private void unlink() {
        assert contains(this);
        StackableScope next = next();
        if (next == null) {
            setHead(previous);
        } else {
            next.previous = previous;
        }
        previous = null;
    }

    /**
     * Returns true if the given scope is on the current thread's scope stack.
     */
    private static boolean contains(StackableScope scope) {
        assert scope != null;
        StackableScope current = head();
        while (current != null && current != scope) {
            current = current.previous();
        }
        return (current == scope);
    }

    /**
     * Returns the head of the current thread's scope stack.
     */
    static StackableScope head() {
        return JLA.headStackableScope(Thread.currentThread());
    }

    /**
     * Sets the head (top) of the current thread's scope stack.
     */
    private static void setHead(StackableScope scope) {
        JLA.setHeadStackableScope(scope);
    }
}
