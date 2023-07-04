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

<xsl:stylesheet version="2.0" xmlns:Iteration="http://www.iterationsoftware.com"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xalan="http://xml.apache.org/xalan"
  xmlns:HTML="http://www.w3.org/Profiles/XHTML-transitional" xmlns:v="urn:schemas-microsoft-com:vml"
  xmlns:local="#local-functions">

  <xsl:output method="xml" encoding="UTF-8" cdata-section-elements="CalcExpression Value"/>

  <xsl:variable name="TabRowHeight">21</xsl:variable>

  <xsl:variable name="DataEditor">
    <xsl:call-template name="DataEditor"/>
  </xsl:variable>

  <xsl:variable name="view_type">
    <xsl:value-of select="//ViewEditor/@ViewType"/>
  </xsl:variable>

  <xsl:variable name="InitialTabEvent">
    <xsl:value-of select="//ViewEditor/@Page"/>
  </xsl:variable>

  <xsl:template match="Iteration">
    <id>
      <xsl:value-of
        select="xalan:nodeset($DataEditor)/DataEditor/View[ContentType=$view_type]/Page[Event=$InitialTabEvent]/@id"/>
    </id>
  </xsl:template>

  <xsl:template name="DataEditor">
    <DataEditor>
      <View>
        <ContentType>PieChart</ContentType>
        <ContentType>ThreeDPieChart</ContentType>
        <Page id="DATA_OBJECTS">
          <xsl:attribute name="label">
            <xsl:value-of select="//Translated/String[@name='DATA_OBJECTS']"/>
          </xsl:attribute>
          <Event>datasets</Event>
        </Page>
        <Page id="VIEWEDITOR_TAB_FIELDS">
          <xsl:attribute name="label">
            <xsl:value-of select="//Translated/String[@name='VIEWEDITOR_TAB_FIELDS']"/>
          </xsl:attribute>
          <Event>chartFields</Event>
        </Page>
      </View>
    </DataEditor>
  </xsl:template>
</xsl:stylesheet>
