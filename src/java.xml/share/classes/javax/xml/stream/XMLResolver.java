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

package java.xml.share.classes.javax.xml.stream;

/**
 * This interface is used to resolve resources during an XML parse.  If an application wishes to
 * perform custom entity resolution it must register an instance of this interface with
 * the XMLInputFactory using the setXMLResolver method.
 *
 * @version 1.0
 */
public interface XMLResolver {

  /**
   * Retrieves a resource.  This resource can be of the following three return types:
   * (1) java.io.InputStream (2) javax.xml.stream.XMLStreamReader (3) java.xml.stream.XMLEventReader.
   * If this method returns null the processor will attempt to resolve the entity using its
   * default mechanism.
   *
   * @param publicID The public identifier of the external entity being referenced, or null if none was supplied.
   * @param systemID The system identifier of the external entity being referenced.
   * @param baseURI  Absolute base URI associated with systemId.
   * @param namespace The namespace of the entity to resolve.
   * @return The resource requested or null.
   * @throws XMLStreamException if there was a failure attempting to resolve the resource.
   */
  Object resolveEntity(String publicID,
                              String systemID,
                              String baseURI,
                              String namespace)
    throws XMLStreamException;
}
