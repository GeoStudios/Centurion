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

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:str="http://exslt.org/strings"
                xmlns:xalan="http://xml.apache.org/xalan"
                version="1.0">
<xsl:template match="a">
   <xsl:apply-templates />
</xsl:template>
<xsl:template match="//a/c">
   <xsl:value-of select="." />
 -
      <xsl:value-of select="str:tokenize(string(.), ' ')" />
   <xsl:value-of select="str:tokenize(string(.), '')" />
   <xsl:for-each select="str:tokenize(string(.), ' ')">
      <xsl:value-of select="." />
   </xsl:for-each>
   <xsl:apply-templates select="*" />
</xsl:template>
<xsl:template match="//a/b">
   <xsl:value-of select="." />
 -
      <xsl:value-of select="xalan:tokenize(string(.), ' ')" />
   <xsl:value-of select="xalan:tokenize(string(.), '')" />
   <xsl:for-each select="xalan:tokenize(string(.), ' ')">
      <xsl:value-of select="." />
   </xsl:for-each>
   <xsl:apply-templates select="*" />
</xsl:template>

</xsl:stylesheet>