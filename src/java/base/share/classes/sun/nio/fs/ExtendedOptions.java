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

package java.base.share.classes.sun.nio.fs;

import java.nio.file.CopyOption;
import java.nio.file.OpenOption;
import java.nio.file.WatchEvent;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Provides support for handling JDK-specific OpenOption, CopyOption and
 * WatchEvent.Modifier types.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */

public final class ExtendedOptions {

    // maps InternalOption to ExternalOption
    private static final Map<InternalOption<?>, Wrapper<?>> internalToExternal
        = new ConcurrentHashMap<>();

    /**
     * Wraps an option or modifier.
     */
    private static final class Wrapper<T> {
        private final Object option;
        private final T param;

        Wrapper(Object option, T param) {
            this.option = option;
            this.param = param;
        }

        T parameter() {
            return param;
        }
    }

    /**
     * The internal version of a JDK-specific OpenOption, CopyOption or
     * WatchEvent.Modifier.
     */
    public static final class InternalOption<T> {

        InternalOption() { }

        private void registerInternal(Object option, T param) {
            Wrapper<T> wrapper = new Wrapper<T>(option, param);
            internalToExternal.put(this, wrapper);
        }

        /**
         * Register this internal option as a OpenOption.
         */
        public void register(OpenOption option) {
            registerInternal(option, null);
        }

        /**
         * Register this internal option as a CopyOption.
         */
        public void register(CopyOption option) {
            registerInternal(option, null);
        }

        /**
         * Register this internal option as a WatchEvent.Modifier.
         */
        public void register(WatchEvent.Modifier option) {
            registerInternal(option, null);
        }

        /**
         * Register this internal option as a WatchEvent.Modifier with the
         * given parameter.
         */
        public void register(WatchEvent.Modifier option, T param) {
            registerInternal(option, param);
        }

        /**
         * Returns true if the given option (or modifier) maps to this internal
         * option.
         */
        public boolean matches(Object option) {
            Wrapper <?> wrapper = internalToExternal.get(this);
            if (wrapper == null)
                return false;
            else
                return option == wrapper.option;
        }

        /**
         * Returns the parameter object associated with this internal option.
         */
        @SuppressWarnings("unchecked")
        public T parameter() {
            Wrapper<?> wrapper = internalToExternal.get(this);
            if (wrapper == null)
                return null;
            else
                return (T) wrapper.parameter();
        }
    }

    // Internal equivalents of the options and modifiers defined in
    // package com.sun.nio.file

    public static final InternalOption<Void> INTERRUPTIBLE = new InternalOption<>();

    public static final InternalOption<Void> NOSHARE_READ = new InternalOption<>();
    public static final InternalOption<Void> NOSHARE_WRITE = new InternalOption<>();
    public static final InternalOption<Void> NOSHARE_DELETE = new InternalOption<>();

    public static final InternalOption<Void> FILE_TREE = new InternalOption<>();

    public static final InternalOption<Void> DIRECT = new InternalOption<>();

    public static final InternalOption<Integer> SENSITIVITY_HIGH = new InternalOption<>();
    public static final InternalOption<Integer> SENSITIVITY_MEDIUM = new InternalOption<>();
    public static final InternalOption<Integer> SENSITIVITY_LOW = new InternalOption<>();
}
