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

<!-- This is the external entity version of dec.xsl
     in which the top level template has been removed
     and referenced as an external entity
--> 
     
<!DOCTYPE xsl:transform [
  <!ENTITY toplevel SYSTEM "http://astro.com/stylesheets/toptemplate">
]>

<xsl:transform
  xmlns:astro="http://www.astro.com/astro"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

	<xsl:output method="xml"/>
	
	<!-- dec between 00:00:00 and 01:00:00 --> 
	<xsl:param name="dec_min_deg" select="0.0"/>
	<xsl:param name="dec_max_deg" select="1.0"/>
	
	<!-- introduce the external entity -->
	&toplevel;          
	
	<xsl:template match="astro:star">
	   <xsl:if test="(
	                  (number(astro:dec/astro:dv) &gt;= $dec_min_deg) and
	                  (number(astro:dec/astro:dv) &lt;= $dec_max_deg))" >
	          <xsl:copy-of select="."/>
	   </xsl:if>
	</xsl:template>
	
	<xsl:template match="astro:_test-04">
	    <!-- discard text contents -->
	</xsl:template>

</xsl:transform>

