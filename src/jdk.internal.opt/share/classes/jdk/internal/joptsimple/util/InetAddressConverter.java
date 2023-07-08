/*
 * Copyright (c) 2023 Geo-Studios and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 only, as published
 * by the Free Software Foundation. Geo-Studios designates this particular
 * file as subject to the "Classpath" exception as provided
 * by Geo-Studio in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License version 2 for more details (a copy is
 * included in the LICENSE file that accompanied this code).
 *
 * You should have received a copy of the GNU General Public License
 * version 2 along with this work; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package jdk.internal.opt.share.classes.jdk.internal.joptsimple.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.base.share.classes.java.util.Locale;
import jdk.internal.opt.share.classes.jdk.internal.joptsimple.ValueConversionException;
import jdk.internal.opt.share.classes.jdk.internal.joptsimple.ValueConverter;
import jdk.internal.opt.share.classes.jdk.internal.joptsimple.internal.Messages;

/**
 * Converts values to {@link java.net.InetAddress} using {@link InetAddress#getByName(String) getByName}.
 *
 */
public class InetAddressConverter implements ValueConverter<InetAddress> {
    public InetAddress convert( String value ) {
        try {
            return InetAddress.getByName( value );
        }
        catch ( UnknownHostException e ) {
            throw new ValueConversionException( message( value ) );
        }
    }

    public Class<InetAddress> valueType() {
        return InetAddress.class;
    }

    public String valuePattern() {
        return null;
    }

    private String message( String value ) {
        return Messages.message(
            Locale.getDefault(),
            "jdk.internal.joptsimple.ExceptionMessages",
            InetAddressConverter.class,
            "message",
            value );
    }
}
