#!/bin/sh
#
# Copyright (c) 2023 Geo-Studios and/or its affiliates. All rights reserved.
# DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
#
# This code is free software; you can redistribute it and/or modify it under
# the terms of the GNU General Public License version 2 only, as published
# by the Free Software Foundation. Geo-Studios designates this particular
# file as subject to the "Classpath" exception as provided
# by Geo-Studio in the LICENSE file that accompanied this code.
#
# This code is distributed in the hope that it will be useful, but WITHOUT
# ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
# GNU General Public License version 2 for more details (a copy is
# included in the LICENSE file that accompanied this code).
#
# You should have received a copy of the GNU General Public License
# version 2 along with this work; if not, write to the Free Software
# Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
#

#
#   @test
#   @bug        8138817 8152971
#   @summary    Tests that there are no JNI warnings about local references.
#   @compile LoadFontsJNICheck.java
#   @run shell/timeout=300 LoadFontsJNICheck.sh
#
OS=`uname`

# pick up the compiled class files.
if [ -z "${TESTCLASSES}" ]; then
  CP="."
else
  CP="${TESTCLASSES}"
fi

if [ -z "${TESTJAVA}" ] ; then
   JAVACMD=java
else
   JAVACMD=$TESTJAVA/bin/java
fi

$JAVACMD ${TESTVMOPTS} \
    -cp "${CP}" -Xcheck:jni LoadFontsJNICheck | grep "local refs"  > "${CP}"/log.txt

# any messages logged may indicate a failure.
if [ -s "${CP}"/log.txt ]; then
    echo "Test failed"
    cat "${CP}"/log.txt
    exit 1
fi

echo "Test passed"
exit 0