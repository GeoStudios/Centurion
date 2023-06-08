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

package java.base.share.classes.sun.invoke.empty;

/**
 * An empty class in an empty package.
 * Used as a proxy for unprivileged code, since making access checks
 * against it will only succeed against public methods in public types.
 * <p>
 * This class also stands (internally to sun.invoke) for the type of a
 * value that cannot be produced, because the expression of this type
 * always returns abnormally.  (Cf. Nothing in the closures proposal.)
 * @author jrose
 */
public class Empty {
    private Empty() { throw new InternalError(); }
}
