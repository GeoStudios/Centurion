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

<!DOCTYPE stylesheet []>

<xsl:stylesheet xmlns:xsl='http://www.w3.org/1999/XSL/Transform'>

	<xsl:param name="config"/>
	<xsl:param name="mapsFile"/>

	<xsl:output method="text"/>

	<xsl:key name="key1" match="map1" use="@type"/>
	<xsl:key name="key2" match="map2" use="@type"/>
        
	<xsl:variable name="maps" select="document($mapsFile)"/>
	<xsl:variable name="type" select="document($config)/config/@type"/>

	<xsl:template match="map1">
		<xsl:for-each select="$maps">
			<xsl:value-of select="key('key1', $type)"/>
		</xsl:for-each>
	</xsl:template>

	<xsl:template match="map2">
		<xsl:for-each select="$maps">
			<xsl:value-of select="key('key2',$type)"/>
		</xsl:for-each>
	</xsl:template>
</xsl:stylesheet>
