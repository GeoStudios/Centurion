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
import java.nio.*;















/** Implementation of DataSource using MappedByteBuffer. This works
    around a performance problem in JDK 1.4 where ByteBuffer's
    operations always become virtual calls rather than being inlined.
    Need to fix this soon. */

public class MappedByteBufferDataSource implements DataSource {
  private MappedByteBuffer buf;

  public MappedByteBufferDataSource(MappedByteBuffer buf) {
    this.buf = buf;
  }

  public byte  readByte()       throws IOException { return buf.get();            }
  public short readShort()      throws IOException { return buf.getShort();       }
  public int   readInt()        throws IOException { return buf.getInt();         }
  public long  readLong()       throws IOException { return buf.getLong();        }
  public int   read(byte[] b)   throws IOException { buf.get(b); return b.length; }
  public void  seek(long pos)   throws IOException {
    try {
      buf.position((int) pos);
    } catch (IllegalArgumentException e) {
      System.err.println("Error seeking to file position 0x" + Long.toHexString(pos));
      throw(e);
    }
  }
  public long  getFilePointer() throws IOException { return buf.position();       }
  public void  close()          throws IOException { buf = null;                  }
}
