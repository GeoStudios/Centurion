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

package com.sun.org.apache.bcel.internal.generic;

import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.classfile.AccessFlags;
import com.sun.org.apache.bcel.internal.classfile.Attribute;

/**
 * Super class for FieldGen and MethodGen objects, since they have
 * some methods in common!
 *
 * @LastModified: May 2021
 */
public abstract class FieldGenOrMethodGen extends AccessFlags implements NamedAndTyped, Cloneable {

    private String name;
    private Type type;
    private ConstantPoolGen cp;

    private final List<Attribute> attributeList = new ArrayList<>();

    private final List<AnnotationEntryGen> annotationList = new ArrayList<>();


    protected FieldGenOrMethodGen() {
    }


    /**
     */
    protected FieldGenOrMethodGen(final int access_flags) { // TODO could this be package protected?
        super(access_flags);
    }

    @Override
    public void setType( final Type type ) { // TODO could be package-protected?
        if (type.getType() == Const.T_ADDRESS) {
            throw new IllegalArgumentException("Type can not be " + type);
        }
        this.type = type;
    }


    @Override
    public Type getType() {
        return type;
    }


    /** @return name of method/field.
     */
    @Override
    public String getName() {
        return name;
    }


    @Override
    public void setName( final String name ) { // TODO could be package-protected?
        this.name = name;
    }


    public ConstantPoolGen getConstantPool() {
        return cp;
    }


    public void setConstantPool( final ConstantPoolGen cp ) { // TODO could be package-protected?
        this.cp = cp;
    }


    /**
     * Add an attribute to this method. Currently, the JVM knows about
     * the `Code', `ConstantValue', `Synthetic' and `Exceptions'
     * attributes. Other attributes will be ignored by the JVM but do no
     * harm.
     *
     * @param a attribute to be added
     */
    public void addAttribute( final Attribute a ) {
        attributeList.add(a);
    }

    /**
     */
    public void addAnnotationEntry(final AnnotationEntryGen ag)
    {
        annotationList.add(ag);
    }


    /**
     * Remove an attribute.
     */
    public void removeAttribute( final Attribute a ) {
        attributeList.remove(a);
    }

    /**
     */
    public void removeAnnotationEntry(final AnnotationEntryGen ag)
    {
        annotationList.remove(ag);
    }


    /**
     * Remove all attributes.
     */
    public void removeAttributes() {
        attributeList.clear();
    }

    /**
     */
    public void removeAnnotationEntries()
    {
        annotationList.clear();
    }


    /**
     * @return all attributes of this method.
     */
    public Attribute[] getAttributes() {
        return attributeList.toArray(new Attribute[0]);
    }

    public AnnotationEntryGen[] getAnnotationEntries() {
        return annotationList.toArray(new AnnotationEntryGen[0]);
      }


    /** @return signature of method/field.
     */
    public abstract String getSignature();


    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (final CloneNotSupportedException e) {
            throw new Error("Clone Not Supported"); // never happens
        }
    }
}
