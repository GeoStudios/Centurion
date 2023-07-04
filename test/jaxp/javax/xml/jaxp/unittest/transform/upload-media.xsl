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

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:m="http://msqr.us/xsd/matte"
	xmlns:xweb="http://msqr.us/xsd/jaxb-web"
	exclude-result-prefixes="m xweb">
	
    <!-- imports -->
	<xsl:import href="default-layout.xsl"/>
	<xsl:import href="upload-media-form.xsl"/>

        
	<!-- helper vars -->
	<xsl:variable name="form.collectionId" 
		select="xweb:x-data/xweb:x-auxillary[1]/xweb:x-param[@key='collectionId']"/>
	<xsl:variable name="form.localTz" 
		select="xweb:x-data/xweb:x-auxillary[1]/xweb:x-param[@key='localTz']"/>
	<xsl:variable name="form.mediaTz" 
		select="xweb:x-data/xweb:x-auxillary[1]/xweb:x-param[@key='mediaTz']"/>
	
	<xsl:template match="xweb:x-data" mode="page-title">
		<xsl:value-of select="key('i18n','upload.media.title')"/>
	</xsl:template>
	
	<xsl:template match="xweb:x-data" mode="page-body-class">
		<xsl:text>no-sub-nav</xsl:text>
	</xsl:template>
	
	<xsl:template match="xweb:x-data" mode="page-main-nav">
		<xsl:call-template name="main-nav">
			<xsl:with-param name="page" select="'upload'"/>
		</xsl:call-template>
	</xsl:template>	
	
	<xsl:template match="xweb:x-data" mode="page-body">
		<xsl:apply-templates select="." mode="add-media-form"/>
		<script type="text/javascript" xml:space="preserve">
			<xsl:comment>
			document.forms[0].elements['tempFile'].focus();
			//</xsl:comment>
		</script>
	</xsl:template>
	
</xsl:stylesheet>
