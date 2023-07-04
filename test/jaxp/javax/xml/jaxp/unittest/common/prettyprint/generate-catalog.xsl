<?xml version="1.0" encoding="utf-8" ?> 
 <!--
  ~ Copyright (c) 2023 Geo-Studios and/or its affiliates. All rights reserved.
  ~ DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
  ~
  ~ This code is free software; you can redistribute it and/or modify it under
  ~ the terms of the GNU General Public License version 2 only, as published
  ~ by the Free Software Foundation. Geo-Studios designates this particular
  ~ file as subject to the "Classpath" exception as provided
  ~ by Geo-Studio in the LICENSE file that accompanied this code.
  ~
  ~ This code is distributed in the hope that it will be useful, but WITHOUT
  ~ ANY WARRANTY; without even the implied warranty of
  ~ MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
  ~ GNU General Public License version 2 for more details (a copy is
  ~ included in the LICENSE file that accompanied this code).
  ~
  ~ You should have received a copy of the GNU General Public License
  ~ version 2 along with this work; if not, write to the Free Software
  ~ Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
  -->

 <!-- Stylesheet for generating the entity-resolver document in XCatalog format -->
 <xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"> 

       <xsl:output method="xml" indent="yes"/> 
       <xsl:param name="schemaBase"/> 
       <xsl:template match="entity-resolver-config"> 
          <catalog xmlns="xmlns:xml:catalog" 
                   prefer="system" 
                   xml:base="{$schemaBase}" > 
                    
                   <xsl:for-each select="entity"> 
                    
                          <!-- Generate System Id --> 
                          <xsl:text disable-output-escaping="yes">&lt;system systemId="</xsl:text> 
                          <xsl:value-of select="system-id/text()"/> 
                          <xsl:text>" uri="</xsl:text> 
                          <xsl:value-of select="location/text()"/> 
                          <xsl:text disable-output-escaping="yes">" /&gt;&#10;</xsl:text> 
                   </xsl:for-each> 
             </catalog> 
    </xsl:template> 
 </xsl:stylesheet>