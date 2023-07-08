/*
 * Copyright (c) 2023 Geo-Studios and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 only, as published
 * by the Free Software Foundation. Geo-Studios designates this particular
 * file as subject to the "Classpath" exception as provided
 * by Geo-Studio in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License version 2 for more details (a copy is
 * included in the LICENSE file that accompanied this code).
 *
 * You should have received a copy of the GNU General Public License
 * version 2 along with this work; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger;


import java.io.*;















/** An abstraction which represents a seekable data source.
    RandomAccessFile can be trivially mapped to this; in addition, we
    can support an adapter for addresses, so we can parse DLLs
    directly out of the remote process's address space.  This class is
    used by the Windows COFF and Posix ELF implementations. */

public interface DataSource {
  byte  readByte()       throws IOException;
  short readShort()      throws IOException;
  int   readInt()        throws IOException;
  long  readLong()       throws IOException;
  int   read(byte[] b)   throws IOException;
  void  seek(long pos)   throws IOException;
  long  getFilePointer() throws IOException;
  void  close()          throws IOException;
}
