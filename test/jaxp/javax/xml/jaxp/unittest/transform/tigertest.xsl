<?xml version='1.0' encoding="UTF-8"?>
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
		xmlns:office="http://openoffice.org/2000/office"
		xmlns:style="http://openoffice.org/2000/style">

	<xsl:output method="xml" encoding="UTF-8"/>

	<xsl:template match="/">
		<xsl:variable name="copyData">
			<xsl:call-template name="copy-by-template" />
		</xsl:variable>
		<test>
  		   <xsl:copy-of select="$copyData"/>
		</test>
	</xsl:template>

	<xsl:template name="copy-by-template">
		<xsl:copy-of select="/"/>
	</xsl:template>
</xsl:stylesheet>
