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
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:output method="xml" version="1.0" omit-xml-declaration="no" encoding="UTF-8" indent="yes" xml:space="preserve" />
  <!-- <xsl:output method="html" doctype-system="http://www.w3.org/TR/html4/strict.dtd" doctype-public="-//W3C//DTD HTML 
    4.01//EN" version="4.0" encoding="UTF-8" indent="yes" xml:lang="$lang" omit-xml-declaration="no"/> -->
  <xsl:param name="lang" />
  <xsl:template match="alphabet">
    <root>
      <p>lang: <xsl:value-of select="$lang" /></p>
      <ul>
        <xsl:apply-templates select="character">
          <xsl:sort select="." lang="{$lang}" order="ascending" />
        </xsl:apply-templates>
      </ul>
    </root>
  </xsl:template>
  <xsl:template match="character">
    <li>
      <xsl:value-of select="text()" />
    </li>
  </xsl:template>
</xsl:stylesheet>

