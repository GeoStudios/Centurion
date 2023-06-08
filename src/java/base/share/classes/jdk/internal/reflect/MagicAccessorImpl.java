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

package java.base.share.classes.jdk.internal.reflect;

/** <P> MagicAccessorImpl (named for parity with FieldAccessorImpl and
    others, not because it actually implements an interface) is a
    marker class in the hierarchy. All subclasses of this class are
    "magically" granted access by the VM to otherwise inaccessible
    fields and methods of other classes. It is used to hold the code
    for dynamically-generated FieldAccessorImpl and MethodAccessorImpl
    subclasses. (Use of the word "unsafe" was avoided in this class's
    name to avoid confusion with {@link jdk.internal.misc.Unsafe}.) </P>

    <P> The bug fix for 4486457 also necessitated disabling
    verification for this class and all subclasses, as opposed to just
    SerializationConstructorAccessorImpl and subclasses, to avoid
    having to indicate to the VM which of these dynamically-generated
    stub classes were known to be able to pass the verifier. </P>

    <P> Do not change the name of this class without also changing the
    VM's code. </P> */

class MagicAccessorImpl {
}
