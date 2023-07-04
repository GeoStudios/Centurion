<?xml version="1.0" encoding="UTF-8"?>
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

<xsl:stylesheet version="1.0"
      xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
      xmlns:xlink="http://www.w3.org/1999/xlink"
   >
      <xsl:output omit-xml-declaration = "yes" />
<xsl:template match="mo" >
   <xsl:choose>
      <xsl:when test="and * and" ></xsl:when>
      <xsl:when test="and and and" ></xsl:when>
      <xsl:when test="* and *" ></xsl:when>
      <xsl:when test="not(preceding-sibling::elem1 and following-sibling::elem2)"></xsl:when>
      <xsl:when test="not(preceding-sibling::* and following-sibling::*)"></xsl:when>
      <xsl:when test="or * or" ></xsl:when>
      <xsl:when test="and or or" ></xsl:when>
      <xsl:when test="* or *" ></xsl:when>
      <xsl:when test="not(preceding-sibling::elem1 or following-sibling::elem2)"></xsl:when>
      <xsl:when test="not(preceding-sibling::* or following-sibling::*)"></xsl:when>
      <xsl:when test="and | and" ></xsl:when>
      <xsl:when test="* | *" ></xsl:when>
      <xsl:when test="not(preceding-sibling::elem1 | following-sibling::elem2)"></xsl:when>
      <xsl:when test="not(preceding-sibling::* | following-sibling::*)"></xsl:when>
    </xsl:choose>
</xsl:template>
</xsl:stylesheet>

