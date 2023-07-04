<?xml version="1.0"?>
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

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" omit-xml-declaration="yes"/>

<xsl:template match="/">
Coding Style Check Results
--------------------------
Total files checked: <xsl:number level="any" value="count(descendant::file)"/>
  Files with errors: <xsl:number level="any" value="count(descendant::file[error])"/>
       Total errors: <xsl:number level="any" value="count(descendant::error)"/>
    Errors per file: <xsl:number level="any" value="count(descendant::error) div count(descendant::file)"/>
<xsl:apply-templates/>
</xsl:template>

<xsl:template match="file[error]">
<xsl:apply-templates select="error"/>
</xsl:template>

<xsl:template match="error">
<xsl:value-of select="../@name"/>:<xsl:value-of select="@line"/><xsl:text>: </xsl:text><xsl:value-of select="@message"/><xsl:text>
</xsl:text>
</xsl:template>

</xsl:stylesheet>
