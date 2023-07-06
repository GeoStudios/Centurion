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

package java.xml.share.classes.com.sun.org.apache.bcel.internal.generic;


import java.xml.share.classes.com.sun.org.apache.bcel.internal.Const;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.Repository;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.classfile.JavaClass;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 * Denotes reference such as java.lang.String.
 *
 */
public class ObjectType extends ReferenceType {

    private final String className; // Class name of type

    /**
     */
    public static ObjectType getInstance(final String className) {
        return new ObjectType(className);
    }

    /**
     * @param className fully qualified class name, e.g. java.lang.String
     */
    public ObjectType(final String className) {
        super(Const.T_REFERENCE, "L" + className.replace('.', '/') + ";");
        this.className = className.replace('/', '.');
    }


    /** @return name of referenced class
     */
    public String getClassName() {
        return className;
    }


    /** @return a hash code value for the object.
     */
    @Override
    public int hashCode() {
        return className.hashCode();
    }


    /** @return true if both type objects refer to the same class.
     */
    @Override
    public boolean equals( final Object type ) {
        return type instanceof ObjectType && ((ObjectType) type).className.equals(className);
    }


    /**
     * If "this" doesn't reference a class, it references an interface
     * or a non-existant entity.
     * @deprecated (since 6.0) this method returns an inaccurate result
     *   if the class or interface referenced cannot
     *   be found: use referencesClassExact() instead
     */
    @Deprecated
    public boolean referencesClass() {
        try {
            final JavaClass jc = Repository.lookupClass(className);
            return jc.isClass();
        } catch (final ClassNotFoundException e) {
            return false;
        }
    }


    /**
     * If "this" doesn't reference an interface, it references a class
     * or a non-existant entity.
     * @deprecated (since 6.0) this method returns an inaccurate result
     *   if the class or interface referenced cannot
     *   be found: use referencesInterfaceExact() instead
     */
    @Deprecated
    public boolean referencesInterface() {
        try {
            final JavaClass jc = Repository.lookupClass(className);
            return !jc.isClass();
        } catch (final ClassNotFoundException e) {
            return false;
        }
    }


    /**
     * Return true if this type references a class,
     * false if it references an interface.
     * @return true if the type references a class, false if
     *   it references an interface
     * @throws ClassNotFoundException if the class or interface
     *   referenced by this type can't be found
     */
    public boolean referencesClassExact() throws ClassNotFoundException {
        final JavaClass jc = Repository.lookupClass(className);
        return jc.isClass();
    }


    /**
     * Return true if this type references an interface,
     * false if it references a class.
     * @return true if the type references an interface, false if
     *   it references a class
     * @throws ClassNotFoundException if the class or interface
     *   referenced by this type can't be found
     */
    public boolean referencesInterfaceExact() throws ClassNotFoundException {
        final JavaClass jc = Repository.lookupClass(className);
        return !jc.isClass();
    }


    /**
     * Return true if this type is a subclass of given ObjectType.
     * @throws ClassNotFoundException if any of this class's superclasses
     *  can't be found
     */
    public boolean subclassOf( final ObjectType superclass ) throws ClassNotFoundException {
        if (this.referencesInterfaceExact() || superclass.referencesInterfaceExact()) {
            return false;
        }
        return Repository.instanceOf(this.className, superclass.className);
    }


    /**
     * Java Virtual Machine Specification edition 2,  5.4.4 Access Control
     * @throws ClassNotFoundException if the class referenced by this type
     *   can't be found
     */
    public boolean accessibleTo( final ObjectType accessor ) throws ClassNotFoundException {
        final JavaClass jc = Repository.lookupClass(className);
        if (jc.isPublic()) {
            return true;
        }
        final JavaClass acc = Repository.lookupClass(accessor.className);
        return acc.getPackageName().equals(jc.getPackageName());
    }
}
