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

package build.tools.dtdbuilder;

/**
 * A stream for reading HTML files. This stream takes care
 * of \r\n conversions and parameter entity expansion.
 *
 * @see DTD
 * @see DTDParser
 * @author Arthur van Hoff
 * @author Steven B. Byrne
 */
public final
class DTDInputStream extends FilterReader implements DTDConstants {
    public DTD dtd;
    public Stack<Object> stack = new Stack<>();
    public char str[] = new char[64];
    public int replace = 0;
    public int ln = 1;
    public int ch;

    /**
     * Create the stream.
     */
    public DTDInputStream(InputStream in, DTD dtd) throws IOException {
        super(new InputStreamReader(in));
        this.dtd = dtd;
        this.ch = in.read();
    }

    /**
     * Error
     */
    public void error(String msg) {
        System.out.println("line " + ln + ": dtd input error: " + msg);
    }

    /**
     * Push a single character
     */
    public void push(int ch) throws IOException {
        char data[] = {(char)ch};
        push(new CharArrayReader(data));
    }

    /**
     * Push an array of bytes.
     */
    public void push(char data[]) throws IOException {
        if (data.length > 0) {
            push(new CharArrayReader(data));
        }
    }

    /**
     * Push an entire input stream
     */
    void push(Reader in) throws IOException {
        stack.push(Integer.valueOf(ln));
        stack.push(Integer.valueOf(ch));
        stack.push(this.in);
        this.in = in;
        ch = in.read();
    }

    /**
     * Read a character from the input. Automatically pop
     * a stream of the stack if the EOF is reached. Also replaces
     * parameter entities.
     * [60] 350:22
     */
    @SuppressWarnings("fallthrough")
    public int read() throws IOException {
        switch (ch) {
          case '%': {
            ch = in.read();
            if (replace > 0) {
                return '%';
            }

            int pos = 0;
            while (((ch >= 'a') && (ch <= 'z')) || ((ch >= 'A') && (ch <= 'Z')) ||
                   ((ch >= '0') && (ch <= '9')) || (ch == '.') || (ch == '-')) {
                str[pos++] = (char)ch;
                ch = in.read();
            }
            if (pos == 0) {
                return '%';
            }

            String nm = new String(str, 0, pos);
            Entity ent = dtd.getEntity(nm);
            if (ent == null) {
                error("undefined entity reference: " + nm);
                return read();
            }

            // Skip ; or RE
            switch (ch) {
              case '\r':
                ln++;
                /* fall through */
              case ';':
                ch = in.read();
                break;
              case '\n':
                ln++;
                if ((ch = in.read()) == '\r') {
                    ch = in.read();
                }
                break;
            }

            // Push the entity.
            try {
                push(getEntityInputReader(ent));
            } catch (Exception e) {
                error("entity data not found: " + ent + ", " + ent.getString());
            }
            return read();
          }

          case '\n':
            ln++;
            if ((ch = in.read()) == '\r') {
                ch = in.read();
            }
            return '\n';

          case '\r':
            ln++;
            ch = in.read();
            return '\n';

          case -1:
            if (stack.size() > 0) {
                in = (Reader)stack.pop();
                ch = ((Integer)stack.pop()).intValue();
                ln = ((Integer)stack.pop()).intValue();
                return read();
            }
            return -1;

          default:
            int c = ch;
            ch = in.read();
            return c;
        }
    }

    /**
    * Return the data as a stream.
    */
    private Reader getEntityInputReader(Entity ent) throws IOException {
        if ((ent.type & Entity.PUBLIC) != 0) {
            // InputStream is = DTDBuilder.mapping.get(ent.getString()).openStream();
            // return new InputStreamReader(is);
            String path = DTDBuilder.mapping.get(ent.getString());

            return new InputStreamReader(new FileInputStream(path));
        }
        if ((ent.type & Entity.SYSTEM) != 0) {
            //InputStream is =  new URL(DTDBuilder.mapping.base, ent.getString()).openStream();
            String path = DTDBuilder.mapping.baseStr +  ent.getString();
            return new InputStreamReader(new FileInputStream(path));
        }
        return new CharArrayReader(ent.data);
    }

}
