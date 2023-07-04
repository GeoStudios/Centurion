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

# @test
# @bug 8141690
# @summary MakeJavaSecurity.java functions

if [ "${TESTSRC}" = "" ] ; then
    TESTSRC="."
fi

if [ "${TESTJAVA}" = "" ] ; then
    JAVAC_CMD=`which javac`
    TESTJAVA=`dirname $JAVAC_CMD`/..
    COMPILEJAVA=${TESTJAVA}
fi

JAVAC="${COMPILEJAVA}/bin/javac ${TESTJAVACOPTS} ${TESTTOOLVMOPTS}"
JAVA="${TESTJAVA}/bin/java ${TESTVMOPTS}"
TOOLSRC="${TESTSRC}/../../../../make/src/classes/build/tools/makejavasecurity/MakeJavaSecurity.java"
TOOLNAME=build.tools.makejavasecurity.MakeJavaSecurity

if [ ! -f $TOOLSRC ]; then
    echo "Cannot find $TOOLSRC. Maybe not all code repos are available"
    exit 0
fi

$JAVAC -d . $TOOLSRC
$JAVA $TOOLNAME \
    $TESTSRC/raw_java_security \
    outfile \
    solaris \
    sparc \
    somepolicy \
    $TESTSRC/more_restricted

# On Windows, line end could be different. -b is a cross-platform option.
diff -b outfile $TESTSRC/final_java_security
