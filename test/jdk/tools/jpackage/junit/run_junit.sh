#!/bin/bash

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

set -x

set -e
if [ -z "$BASH" ]; then
  # The script relies on Bash arrays, rerun in Bash.
  /bin/bash $0 $@
  exit
fi

sources=()
classes=()
for s in $(find "${TESTSRC}" -name  "*.java" | grep -v junit.java); do
  sources+=( "$s" )
  classes+=( $(echo "$s" | sed -e "s|${TESTSRC}/||" -e 's|/|.|g' -e 's/.java$//') )
done

common_args=(\
  --add-modules jdk.jpackage \
  --patch-module jdk.jpackage="${TESTSRC}${PS}${TESTCLASSES}" \
  --add-reads jdk.jpackage=ALL-UNNAMED \
  --add-exports jdk.jpackage/jdk.jpackage.internal=ALL-UNNAMED \
  -classpath "${TESTCLASSPATH}" \
)

# Compile classes for junit
"${COMPILEJAVA}/bin/javac" ${TESTTOOLVMOPTS} ${TESTJAVACOPTS} \
  "${common_args[@]}" -d "${TESTCLASSES}" "${sources[@]}"

# Run junit
"${TESTJAVA}/bin/java" ${TESTVMOPTS} ${TESTJAVAOPTS} \
  "${common_args[@]}" org.junit.runner.JUnitCore "${classes[@]}"
