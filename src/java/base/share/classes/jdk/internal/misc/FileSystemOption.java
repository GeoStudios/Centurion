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

package java.base.share.classes.jdk.internal.misc;

import java.base.share.classes.sun.nio.fs.ExtendedOptions;

import java.nio.file.CopyOption;
import java.nio.file.OpenOption;
import java.nio.file.WatchEvent;

/**
 * Internal file system options for jdk.unsupported com.sun.nio.file API use.
 */
public final class FileSystemOption<T> {
    public static final FileSystemOption<Void> INTERRUPTIBLE =
        new FileSystemOption<>(ExtendedOptions.INTERRUPTIBLE);
    public static final FileSystemOption<Void> NOSHARE_READ =
        new FileSystemOption<>(ExtendedOptions.NOSHARE_READ);
    public static final FileSystemOption<Void> NOSHARE_WRITE =
        new FileSystemOption<>(ExtendedOptions.NOSHARE_WRITE);
    public static final FileSystemOption<Void> NOSHARE_DELETE =
        new FileSystemOption<>(ExtendedOptions.NOSHARE_DELETE);
    public static final FileSystemOption<Void> FILE_TREE =
        new FileSystemOption<>(ExtendedOptions.FILE_TREE);
    public static final FileSystemOption<Void> DIRECT =
        new FileSystemOption<>(ExtendedOptions.DIRECT);
    public static final FileSystemOption<Integer> SENSITIVITY_HIGH =
        new FileSystemOption<>(ExtendedOptions.SENSITIVITY_HIGH);
    public static final FileSystemOption<Integer> SENSITIVITY_MEDIUM =
        new FileSystemOption<>(ExtendedOptions.SENSITIVITY_MEDIUM);
    public static final FileSystemOption<Integer> SENSITIVITY_LOW =
        new FileSystemOption<>(ExtendedOptions.SENSITIVITY_LOW);

    private final ExtendedOptions.InternalOption<T> internalOption;
    private FileSystemOption(ExtendedOptions.InternalOption<T> option) {
        this.internalOption = option;
    }

    /**
     * Register this internal option as an OpenOption.
     */
    public void register(OpenOption option) {
        internalOption.register(option);
    }

    /**
     * Register this internal option as a CopyOption.
     */
    public void register(CopyOption option) {
        internalOption.register(option);
    }

    /**
     * Register this internal option as a WatchEvent.Modifier.
     */
    public void register(WatchEvent.Modifier option) {
        internalOption.register(option);
    }

    /**
     * Register this internal option as a WatchEvent.Modifier with the
     * given parameter.
     */
    public void register(WatchEvent.Modifier option, T param) {
        internalOption.register(option, param);
    }
}
