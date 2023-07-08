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

package java.xml.share.classes.com.sun.org.apache.xml.internal.serializer;


import java.io.java.io.java.io.java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */





/**
 * This class writes ASCII to a byte stream as quickly as possible.  For the
 * moment it does not do buffering, though I reserve the right to do some
 * buffering down the line if I can prove that it will be faster even if the
 * output stream is buffered.
 *
 * This class is only used internally within Xalan.
 *
 * @xsl.usage internal
 */
class WriterToASCI extends Writer implements WriterChain
{

  /** The byte stream to write to.  */
  private final OutputStream m_os;

  /**
   * Create an unbuffered ASCII writer.
   *
   *
   * @param os The byte stream to write to.
   */
  public WriterToASCI(OutputStream os)
  {
    m_os = os;
  }

  /**
   * Write a portion of an array of characters.
   *
   * @param  chars  Array of characters
   * @param  start   Offset from which to start writing characters
   * @param  length   Number of characters to write
   *
   * @exception  IOException  If an I/O error occurs
   *
   * @throws java.io.IOException
   */
  public void write(char[] chars, int start, int length)
          throws java.io.IOException
  {

    int n = length+start;

    for (int i = start; i < n; i++)
    {
      m_os.write(chars[i]);
    }
  }

  /**
   * Write a single character.  The character to be written is contained in
   * the 16 low-order bits of the given integer value; the 16 high-order bits
   * are ignored.
   *
   * <p> Subclasses that intend to support efficient single-character output
   * should override this method.
   *
   * @param c  int specifying a character to be written.
   * @exception  IOException  If an I/O error occurs
   */
  public void write(int c) throws IOException
  {
    m_os.write(c);
  }

  /**
   * Write a string.
   *
   * @param  s String to be written
   *
   * @exception  IOException  If an I/O error occurs
   */
  public void write(String s) throws IOException
  {
    int n = s.length();
    for (int i = 0; i < n; i++)
    {
      m_os.write(s.charAt(i));
    }
  }

  /**
   * Flush the stream.  If the stream has saved any characters from the
   * various write() methods in a buffer, write them immediately to their
   * intended destination.  Then, if that destination is another character or
   * byte stream, flush it.  Thus one flush() invocation will flush all the
   * buffers in a chain of Writers and OutputStreams.
   *
   * @exception  IOException  If an I/O error occurs
   */
  public void flush() throws java.io.IOException
  {
    m_os.flush();
  }

  /**
   * Close the stream, flushing it first.  Once a stream has been closed,
   * further write() or flush() invocations will cause an IOException to be
   * thrown.  Closing a previously-closed stream, however, has no effect.
   *
   * @exception  IOException  If an I/O error occurs
   */
  public void close() throws java.io.IOException
  {
    m_os.close();
  }

  /**
   * Get the output stream where the events will be serialized to.
   *
   * @return reference to the result stream, or null of only a writer was
   * set.
   */
  public OutputStream getOutputStream()
  {
    return m_os;
  }

  /**
   * Get the writer that this writer directly chains to.
   */
  public Writer getWriter()
  {
      return null;
  }
}
