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

package java.xml.share.classes.com.sun.org.apache.xml.internal.dtm.ref;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * The class ExtendedType represents an extended type object used by
 * ExpandedNameTable.
 */
public final class ExtendedType
{
    private int nodetype;
    private String namespace;
    private String localName;
    private int hash;

    /**
     * Create an ExtendedType object from node type, namespace and local name.
     * The hash code is calculated from the node type, namespace and local name.
     *
     * @param nodetype Type of the node
     * @param namespace Namespace of the node
     * @param localName Local name of the node
     */
    public ExtendedType (int nodetype, String namespace, String localName)
    {
      this.nodetype = nodetype;
      this.namespace = namespace;
      this.localName = localName;
      this.hash = nodetype + namespace.hashCode() + localName.hashCode();
    }

    /**
     * Create an ExtendedType object from node type, namespace, local name
     * and a given hash code.
     *
     * @param nodetype Type of the node
     * @param namespace Namespace of the node
     * @param localName Local name of the node
     * @param hash The given hash code
     */
    public ExtendedType (int nodetype, String namespace, String localName, int hash)
    {
      this.nodetype = nodetype;
      this.namespace = namespace;
      this.localName = localName;
      this.hash = hash;
    }

    /**
     * Redefine this ExtendedType object to represent a different extended type.
     * This is intended to be used ONLY on the hashET object. Using it elsewhere
     * will mess up existing hashtable entries!
     */
    protected void redefine(int nodetype, String namespace, String localName)
    {
      this.nodetype = nodetype;
      this.namespace = namespace;
      this.localName = localName;
      this.hash = nodetype + namespace.hashCode() + localName.hashCode();
    }

    /**
     * Redefine this ExtendedType object to represent a different extended type.
     * This is intended to be used ONLY on the hashET object. Using it elsewhere
     * will mess up existing hashtable entries!
     */
    protected void redefine(int nodetype, String namespace, String localName, int hash)
    {
      this.nodetype = nodetype;
      this.namespace = namespace;
      this.localName = localName;
      this.hash = hash;
    }

    /**
     * Override the hashCode() method in the Object class
     */
    public int hashCode()
    {
      return hash;
    }

    /**
     * Test if this ExtendedType object is equal to the given ExtendedType.
     *
     * @param other The other ExtendedType object to test for equality
     * @return true if the two ExtendedType objects are equal.
     */
    public boolean equals(ExtendedType other)
    {
      try
      {
        return other.nodetype == this.nodetype &&
                other.localName.equals(this.localName) &&
                other.namespace.equals(this.namespace);
      }
      catch(NullPointerException e)
      {
        return false;
      }
    }

    /**
     * Return the node type
     */
    public int getNodeType()
    {
      return nodetype;
    }

    /**
     * Return the local name
     */
    public String getLocalName()
    {
      return localName;
    }

    /**
     * Return the namespace
     */
    public String getNamespace()
    {
      return namespace;
    }

}