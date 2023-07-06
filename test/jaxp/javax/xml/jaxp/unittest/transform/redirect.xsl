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
    xmlns:redirect="http://xml.apache.org/xalan/redirect">
    
    <xsl:output method="xml" indent="no" encoding="UTF-8"/>
    
    <xsl:template match="/">
        <redirect:write file="redirect2.xml">
            <out>
                <xsl:text disable-output-escaping="yes">Euro 1: &#8364; </xsl:text>
            </out>
        </redirect:write>
        <out>
            <xsl:text disable-output-escaping="yes">Euro 1: &#8364; </xsl:text>
        </out>
    </xsl:template>
</xsl:stylesheet>