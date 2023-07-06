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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.windbg;


import java.io.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.win32.coff.*;















public class AddressDataSource implements DataSource {
  public AddressDataSource(Address addr) {
    this.addr = addr;
    offset = 0;
  }

  public byte readByte() throws IOException {
    try {
      byte res = (byte) addr.getCIntegerAt(offset, 1, false);
      ++offset;
      return res;
    } catch (UnmappedAddressException e) {
      throw new IOException("Unmapped address at 0x" + Long.toHexString(e.getAddress()), e);
    } catch (DebuggerException e) {
      throw new IOException(e);
    }
  }

  public short readShort() throws IOException {
    // NOTE: byte swapping is taken care of at the COFFFileImpl level
    int b1 = readByte() & 0xFF;
    int b2 = readByte() & 0xFF;
    return (short) ((b1 << 8) | b2);
  }

  public int readInt() throws IOException {
    // NOTE: byte swapping is taken care of at the COFFFileImpl level
    int b1 = ((int) readByte()) & 0xFF;
    int b2 = ((int) readByte()) & 0xFF;
    int b3 = ((int) readByte()) & 0xFF;
    int b4 = ((int) readByte()) & 0xFF;
    return ((b1 << 24) | (b2 << 16) | (b3 << 8) | b4);
  }

  public long readLong() throws IOException {
    // NOTE: byte swapping is taken care of at the COFFFileImpl level
    long b1 = ((long) readByte()) & 0xFFL;
    long b2 = ((long) readByte()) & 0xFFL;
    long b3 = ((long) readByte()) & 0xFFL;
    long b4 = ((long) readByte()) & 0xFFL;
    long b5 = ((long) readByte()) & 0xFFL;
    long b6 = ((long) readByte()) & 0xFFL;
    long b7 = ((long) readByte()) & 0xFFL;
    long b8 = ((long) readByte()) & 0xFFL;
    return (((((b1 << 24) | (b2 << 16) | (b3 << 8) | b4)) << 32) |
            ((((b5 << 24) | (b6 << 16) | (b7 << 8) | b8))));
  }

  public int read(byte[] b) throws IOException {
    for (int i = 0; i < b.length; i++) {
      b[i] = readByte();
    }
    return b.length;
  }

  public void seek(long pos) throws IOException {
    offset = pos;
  }

  public long getFilePointer() throws IOException {
    return offset;
  }

  public void close() throws IOException {
  }

  private final Address addr;
  private long offset;
}
