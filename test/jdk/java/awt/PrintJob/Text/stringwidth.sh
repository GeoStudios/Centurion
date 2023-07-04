#!/bin/ksh -p
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
OS=`uname`

status=1
checkstatus()
 {
  status=$?
  if [ $status -ne "0" ]; then
    exit "$status"
  fi
 }

# pick up the compiled class files.
if [ -z "${TESTCLASSES}" ]; then
  CP="."
else
  CP="${TESTCLASSES}"
fi


if [ $OS = Linux ]
then
    exit 0
fi
# Windows

if [ -z "${TESTJAVA}" ] ; then
   JAVA_HOME=../../../../../../build/windows-i586
else
   JAVA_HOME=$TESTJAVA
fi

    $JAVA_HOME/bin/java ${TESTVMOPTS} -cp "${CP}" StringWidth
    checkstatus

exit 0
