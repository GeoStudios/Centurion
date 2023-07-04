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

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.management.DescriptorKey;

/**
 * That annotation is usable everywhere DescriptorKey is (and even more).
 * It is for use to test that you can retrieve the SqeDescriptorKey into the
 * appropriate Descriptor instances as built by the JMX runtime.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface SqeDescriptorKey {
    @DescriptorKey("sqeDescriptorKey")
    String value();

    // List descriptor fields that may be added or may be updated
    // when retrieving an MBeanInfo using a JMXWS connection compared to the
    // MBeanInfo returned by a local MBeanServer.
    // The annotation format is :
    //   <descriptorFieldName>=<descriptorFieldValue>
    // The values actually handled by the test suite are :
    //   openType=SimpleType.VOID
    @DescriptorKey("descriptorFields")
    String[] descriptorFields() default {};
}
