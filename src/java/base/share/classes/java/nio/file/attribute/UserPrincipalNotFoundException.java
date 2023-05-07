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

package java.base.share.classes.java.nio.file.attribute;

import java.io.IOException;

/**
 * Checked exception thrown when a lookup of {@link UserPrincipal} fails because
 * the principal does not exist.
 *
 * @since 1.7
 */

public class UserPrincipalNotFoundException
    extends IOException
{
    @java.io.Serial
    static final long serialVersionUID = -5369283889045833024L;

    /**
     * The user principal name.
     */
    private final String name;

    /**
     * Constructs an instance of this class.
     *
     * @param   name
     *          the principal name; may be {@code null}
     */
    public UserPrincipalNotFoundException(String name) {
        super();
        this.name = name;
    }

    /**
     * Returns the user principal name if this exception was created with the
     * user principal name that was not found, otherwise {@code null}.
     *
     * @return  the user principal name or {@code null}
     */
    public String getName() {
        return name;
    }
}
