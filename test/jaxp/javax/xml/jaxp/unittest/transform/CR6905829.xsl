<?xml version="1.0" ?>
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
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="book">
  <h1><xsl:value-of select="title"/></h1>
  <xsl:call-template name="MyTemplate"/>

<!--
  <xsl:call-template name="MyTemplate">
  <xsl:with-param name="x" select="2"/>
  </xsl:call-template>
-->
</xsl:template>

<xsl:template name="MyTemplate">
  <xsl:param name="x" select="1"/>
  <p>MyTemplate has been called. param x=<xsl:value-of select="$x"/>.</p>
</xsl:template>

<xsl:include href="CR6905829Inc.xsl"/>
</xsl:stylesheet>
