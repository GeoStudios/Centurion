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

/** <P> This interface abstracts raw access to operating system-level
    threads. In a debugging environment these methods map to, for
    example, thread_db calls on Solaris (see /usr/include/thread_db.h)
    or the Win32 debugging API calls. In a runtime environment these
    might map directly to pthread calls. </P>

    <P> Implementors of this interface must provide equals() and
    hashCode() methods which work properly regardless of how the
    ThreadProxy is obtained, in particular either through {@link
    sun.jvm.hotspot.debugger.ThreadAccess} or the thread list provided
    by {@link sun.jvm.hotspot.debugger.cdbg.CDebugger}. This allows
    matching up of the OS's notion of the thread list of the target
    process with any user-level lists that may be present (i.e., the
    JavaThread list in the HotSpot VM). </P>

    <P> Implementors of this interface should also provide a
    toString() which converts the ThreadProxy to a value easily
    recognizable in the platform's debugger. (For example, on Solaris,
    "t@&lt;id&gt;".) </P>

    <P> FIXME: had to be renamed from "Thread" to avoid numerous
    clashes with java.lang.Thread -- would be nice to pick a more
    consistent name with the rest of the system. </P> */

public interface ThreadProxy {
  /** Retrieves the context for the given thread. It is only valid to
      call this method if the thread is suspended (i.e., the process
      has not been resumed via ProcessControl); throws an
      IllegalThreadStateException if it is not. */
  ThreadContext getContext() throws IllegalThreadStateException;

  /** Indicates whether calls to setContext() are valid. */
  boolean canSetContext() throws DebuggerException;

  /** Sets the context for the given thread. The passed ThreadContext
      must be a modified version of one returned from a previous call
      to getContext(). It is only valid to call this method if the
      thread is suspended (i.e., the process has not been resumed via
      ProcessControl); throws an IllegalThreadStateException if it is
      not. Throws a DebuggerException if the target process can not be
      modified, for example because it is a core file. */
  void setContext(ThreadContext context)
    throws IllegalThreadStateException, DebuggerException;
}
