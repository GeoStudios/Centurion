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
  <xsl:output method="text"/>

  <xsl:template match="/">
    <xsl:call-template name="recurse1">
      <xsl:with-param name="num">
        <xsl:value-of select="0"/>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:text>&#xa;</xsl:text>
  </xsl:template>

  <xsl:template name="recurse1">
    <xsl:param name="num"/>
    <xsl:call-template name="recurse2">
      <xsl:with-param name="num" select="0"/>
    </xsl:call-template>
    <xsl:if test="not($num = 19)">
      <xsl:variable name="tmpnumber"><xsl:value-of select="$num + 1"/></xsl:variable>
      <xsl:call-template name="recurse1">
        <xsl:with-param name="num">
          <xsl:value-of select="$tmpnumber"/>
        </xsl:with-param>
      </xsl:call-template>
    </xsl:if>
  </xsl:template>

  <xsl:template name="recurse2">
    <xsl:param name="num"/>
    <xsl:call-template name="recursefinal">
      <xsl:with-param name="num" select="0"/>
    </xsl:call-template>
    <xsl:if test="not($num = 19)">
      <xsl:variable name="tmpnumber"><xsl:value-of select="$num + 1"/></xsl:variable>
      <xsl:call-template name="recurse2">
        <xsl:with-param name="num" select="$tmpnumber"/>
      </xsl:call-template>
    </xsl:if>
  </xsl:template>

  <xsl:template name="recursefinal">
    <xsl:param name="num"/>
    <xsl:call-template name="dodot"/>
    <xsl:call-template name="dodot"/>
    <xsl:call-template name="dodot"/>
    <xsl:call-template name="dodot"/>
    <xsl:call-template name="dodot"/>
    <xsl:call-template name="dodot"/>
    <xsl:call-template name="dodot"/>
    <xsl:call-template name="dodot"/>
    <xsl:call-template name="dodot"/>
    <xsl:call-template name="dodot"/>
    <xsl:call-template name="dodot"/>
    <xsl:call-template name="dodot"/>
    <xsl:call-template name="dodot"/>
    <xsl:call-template name="dodot"/>
    <xsl:call-template name="dodot"/>
    <xsl:if test="not($num = 10)">
      <xsl:variable name="tmpnumber"><xsl:value-of select="$num + 1"/></xsl:variable>
      <xsl:call-template name="recursefinal">
        <xsl:with-param name="num" select="$tmpnumber"/>
      </xsl:call-template>
    </xsl:if>
  </xsl:template>

  <xsl:template name="dodot">
    <xsl:variable name="ElementTexts">
      <xsl:for-each select="element">
        <xsl:value-of select="text"/>
      </xsl:for-each>
    </xsl:variable>
    <xsl:value-of select="$ElementTexts"/>
  </xsl:template>
</xsl:stylesheet>
