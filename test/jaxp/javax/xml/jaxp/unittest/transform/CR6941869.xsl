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

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <!-- Xalan 2.6 contained in the Sun JDK 1.5 u12 and newer has issues with
       evaluating grouping nodes according the Muenchian Method (by Steve Muench),
       where nodes are stored in a key data structure for the group key, and later
       a group start is checked by checking for the first node retrieved for a key.
       The nodes are compared using a set union, where the size of the set should
       be 1 when the nodes are identical, and different to 1 when the nodes are
       different.
       The issue with Xalan 2.6 as part of the JDK is, that it does not evaluate the
       expression "count(.|key('props', subexpr)[1])" correctly. It always returns
       "1". If the argument expression of count() is stored in a variable first,
       and the variable is used as argument for count(), then everything works fine.
  -->



  <xsl:key name="props" match="c" use="d/e"/>
  <xsl:template match="a">

    Working (by replacing the count() argument with a variable):
    <xsl:for-each select="b">
      <xsl:for-each select="c">
        <xsl:variable name="tNodeSet" select=".|key('props', d/e)[1]"/>
        <xsl:for-each select="$tNodeSet">
        Node <xsl:value-of select="d/e/@attr"/>;
        </xsl:for-each>
        count = <xsl:value-of select="count($tNodeSet)"/>
      </xsl:for-each>
        ---------
    </xsl:for-each>
    Not working in the 2nd loop iteration (by using the union expression as count() argument):
    <xsl:for-each select="b">
      <xsl:for-each select="c">
        <!-- We replaced the variable "tNodeSet" by the related union expression.
             The for-each loop will work correctly, but the count() will
             always return "1" instead of first "1" and then "2".
        -->

        <xsl:for-each select=".|key('props', d/e)[1]">
        Node <xsl:value-of select="d/e/@attr"/>;
        </xsl:for-each>
        count = <xsl:value-of select="count(.|key('props', d/e)[1])"/>
      </xsl:for-each>
        ---------
    </xsl:for-each>

  </xsl:template>
</xsl:stylesheet>

