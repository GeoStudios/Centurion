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
	xmlns:m="http://msqr.us/xsd/matte"
	xmlns:x="http://msqr.us/xsd/jaxb-web"
	version="1.0">

	<!-- standard data vars -->
	<xsl:variable name="aux" select="x:x-data/x:x-auxillary"/>
	<xsl:variable name="ctx" select="x:x-data/x:x-context"/>
	<xsl:variable name="err" select="x:x-data/x:x-errors/x:error[@field]"/>
	<xsl:variable name="req" select="x:x-data/x:x-request/x:param"/>
	<xsl:variable name="ses" select="x:x-data/x:x-session"/>
	
	<!-- helper vars -->
	<xsl:variable name="acting-user" select="x:x-data/x:x-session[1]/m:session[1]/m:acting-user[1]"/>
	<xsl:variable name="server-name" select="string($ctx/x:server-name)"/>
	<xsl:variable name="server-port" select="string($ctx/x:server-port)"/>
	<xsl:variable name="user-locale" select="string($ctx/x:user-locale)"/>
	<xsl:variable name="web-context" select="string($ctx/x:web-context)"/>
	<xsl:variable name="web-path" select="string($ctx/x:path)"/>
	
	<!-- application context defined as key for quick lookup -->
	<xsl:key name="appenv" match="x:x-data/x:x-auxillary/m:app-context/m:meta" use="@key"/>
	
	<!-- auxillaray params defined as key for quick lookup -->
	<xsl:key name="aux-param" match="x:x-data/x:x-auxillary/x:x-param" use="@key"/>
	
	<!-- message resource bundle defined as key for quick lookup -->
	<xsl:key name="i18n" match="x:x-data/x:x-msg/x:msg" use="@key"/>
	
	<!-- request params defined as key for quick lookup -->
	<xsl:key name="req-param" match="x:x-data/x:x-request/x:param" use="@key"/>
	
</xsl:stylesheet>
