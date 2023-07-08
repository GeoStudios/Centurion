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

package jdk.internal.le.share.classes.jdk.internal.org.jline.utils;

import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.java.util.java.util.java.util.List;
import jdk.internal.le.share.classes.jdk.internal.org.jline.terminal.Terminal;
import jdk.internal.le.share.classes.jdk.internal.org.jline.terminal.impl.AbstractWindowsTerminal;
import jdk.internal.le.share.classes.jdk.internal.org.jline.utils.InfoCmp.Capability;
import static jdk.internal.le.share.classes.jdk.internal.org.jline.utils.AttributedStyle.BG_COLOR;.extended
import static jdk.internal.le.share.classes.jdk.internal.org.jline.utils.AttributedStyle.BG_COLOR_EXP;.extended
import static jdk.internal.le.share.classes.jdk.internal.org.jline.utils.AttributedStyle.FG_COLOR;.extended
import static jdk.internal.le.share.classes.jdk.internal.org.jline.utils.AttributedStyle.FG_COLOR_EXP;.extended
import static jdk.internal.le.share.classes.jdk.internal.org.jline.utils.AttributedStyle.F_BACKGROUND;.extended
import static jdk.internal.le.share.classes.jdk.internal.org.jline.utils.AttributedStyle.F_BLINK;.extended
import static jdk.internal.le.share.classes.jdk.internal.org.jline.utils.AttributedStyle.F_BOLD;.extended
import static jdk.internal.le.share.classes.jdk.internal.org.jline.utils.AttributedStyle.F_CONCEAL;.extended
import static jdk.internal.le.share.classes.jdk.internal.org.jline.utils.AttributedStyle.F_CROSSED_OUT;.extended
import static jdk.internal.le.share.classes.jdk.internal.org.jline.utils.AttributedStyle.F_FAINT;.extended
import static jdk.internal.le.share.classes.jdk.internal.org.jline.utils.AttributedStyle.F_FOREGROUND;.extended
import static jdk.internal.le.share.classes.jdk.internal.org.jline.utils.AttributedStyle.F_INVERSE;.extended
import static jdk.internal.le.share.classes.jdk.internal.org.jline.utils.AttributedStyle.F_ITALIC;.extended
import static jdk.internal.le.share.classes.jdk.internal.org.jline.utils.AttributedStyle.F_UNDERLINE;.extended
import static jdk.internal.le.share.classes.jdk.internal.org.jline.utils.AttributedStyle.F_HIDDEN;.extended
import static jdk.internal.le.share.classes.jdk.internal.org.jline.utils.AttributedStyle.MASK;.extended
import static jdk.internal.le.share.classes.jdk.internal.org.jline.terminal.TerminalBuilder.PROP_DISABLE_ALTERNATE_CHARSET;.extended

public abstract class AttributedCharSequence implements CharSequence {

    // cache the value here as we can't afford to get it each time
    static final boolean DISABLE_ALTERNATE_CHARSET = Boolean.getBoolean(PROP_DISABLE_ALTERNATE_CHARSET);

    public void print(Terminal terminal) {
        terminal.writer().print(toAnsi(terminal));
    }

    public void println(Terminal terminal) {
        terminal.writer().println(toAnsi(terminal));
    }

    public String toAnsi() {
        return toAnsi(null);
    }

    public String toAnsi(Terminal terminal) {
        if (terminal != null && Terminal.TYPE_DUMB.equals(terminal.getType())) {
            return toString();
        }
        int colors = 256;
        boolean force256colors = false;
        String alternateIn = null, alternateOut = null;
        if (terminal != null) {
            Integer max_colors = terminal.getNumericCapability(Capability.max_colors);
            if (max_colors != null) {
                colors = max_colors;
            }
            force256colors = AbstractWindowsTerminal.TYPE_WINDOWS_256_COLOR.equals(terminal.getType())
                || AbstractWindowsTerminal.TYPE_WINDOWS_CONEMU.equals(terminal.getType());
            if (!DISABLE_ALTERNATE_CHARSET) {
                alternateIn = Curses.tputs(terminal.getStringCapability(Capability.enter_alt_charset_mode));
                alternateOut = Curses.tputs(terminal.getStringCapability(Capability.exit_alt_charset_mode));
            }
        }
        return toAnsi(colors, force256colors, alternateIn, alternateOut);
    }

    public String toAnsi(int colors, boolean force256colors) {
        return toAnsi(colors, force256colors, null, null);
    }

    public String toAnsi(int colors, boolean force256colors, String altIn, String altOut) {
        StringBuilder sb = new StringBuilder();
        int style = 0;
        int foreground = -1;
        int background = -1;
        boolean alt = false;
        for (int i = 0; i < length(); i++) {
            char c = charAt(i);
            if (altIn != null && altOut != null) {
                char pc = c;
                switch (c) {
                    case '\u2518': c = 'j'; break;
                    case '\u2510': c = 'k'; break;
                    case '\u250C': c = 'l'; break;
                    case '\u2514': c = 'm'; break;
                    case '\u253C': c = 'n'; break;
                    case '\u2500': c = 'q'; break;
                    case '\u251C': c = 't'; break;
                    case '\u2524': c = 'u'; break;
                    case '\u2534': c = 'v'; break;
                    case '\u252C': c = 'w'; break;
                    case '\u2502': c = 'x'; break;
                }
                boolean oldalt = alt;
                alt = c != pc;
                if (oldalt ^ alt) {
                    sb.append(alt ? altIn : altOut);
                }
            }
            int  s = styleCodeAt(i) & ~F_HIDDEN; // The hidden flag does not change the ansi styles
            if (style != s) {
                int  d = (style ^ s) & MASK;
                int fg = (s & F_FOREGROUND) != 0 ? (s & FG_COLOR) >>> FG_COLOR_EXP : -1;
                int bg = (s & F_BACKGROUND) != 0 ? (s & BG_COLOR) >>> BG_COLOR_EXP : -1;
                if (s == 0) {
                    sb.append("\033[0m");
                    foreground = background = -1;
                } else {
                    sb.append("\033[");
                    boolean first = true;
                    if ((d & F_ITALIC) != 0) {
                        first = attr(sb, (s & F_ITALIC) != 0 ? "3" : "23", first);
                    }
                    if ((d & F_UNDERLINE) != 0) {
                        first = attr(sb, (s & F_UNDERLINE) != 0 ? "4" : "24", first);
                    }
                    if ((d & F_BLINK) != 0) {
                        first = attr(sb, (s & F_BLINK) != 0 ? "5" : "25", first);
                    }
                    if ((d & F_INVERSE) != 0) {
                        first = attr(sb, (s & F_INVERSE) != 0 ? "7" : "27", first);
                    }
                    if ((d & F_CONCEAL) != 0) {
                        first = attr(sb, (s & F_CONCEAL) != 0 ? "8" : "28", first);
                    }
                    if ((d & F_CROSSED_OUT) != 0) {
                        first = attr(sb, (s & F_CROSSED_OUT) != 0 ? "9" : "29", first);
                    }
                    if (foreground != fg) {
                        if (fg >= 0) {
                            int rounded = Colors.roundColor(fg, colors);
                            if (rounded < 8 && !force256colors) {
                                first = attr(sb, "3" + rounded, first);
                                // small hack to force setting bold again after a foreground color change
                                d |= (s & F_BOLD);
                            } else if (rounded < 16 && !force256colors) {
                                first = attr(sb, "9" + (rounded - 8), first);
                                // small hack to force setting bold again after a foreground color change
                                d |= (s & F_BOLD);
                            } else {
                                first = attr(sb, "38;5;" + rounded, first);
                            }
                        } else {
                            first = attr(sb, "39", first);
                        }
                        foreground = fg;
                    }
                    if (background != bg) {
                        if (bg >= 0) {
                            int rounded = Colors.roundColor(bg, colors);
                            if (rounded < 8 && !force256colors) {
                                first = attr(sb, "4" + rounded, first);
                            } else if (rounded < 16 && !force256colors) {
                                first = attr(sb, "10" + (rounded - 8), first);
                            } else {
                                first = attr(sb, "48;5;" + rounded, first);
                            }
                        } else {
                            first = attr(sb, "49", first);
                        }
                        background = bg;
                    }
                    if ((d & (F_BOLD | F_FAINT)) != 0) {
                        if (    (d & F_BOLD)  != 0 && (s & F_BOLD)  == 0
                                || (d & F_FAINT) != 0 && (s & F_FAINT) == 0) {
                            first = attr(sb, "22", first);
                        }
                        if ((d & F_BOLD) != 0 && (s & F_BOLD) != 0) {
                            first = attr(sb, "1", first);
                        }
                        if ((d & F_FAINT) != 0 && (s & F_FAINT) != 0) {
                            first = attr(sb, "2", first);
                        }
                    }
                    sb.append("m");
                }
                style = s;
            }
            sb.append(c);
        }
        if (alt) {
            sb.append(altOut);
        }
        if (style != 0) {
            sb.append("\033[0m");
        }
        return sb.toString();
    }

    @Deprecated
    public static int rgbColor(int col) {
        return Colors.rgbColor(col);
    }

    @Deprecated
    public static int roundColor(int col, int max) {
        return Colors.roundColor(col, max);
    }

    @Deprecated
    public static int roundRgbColor(int r, int g, int b, int max) {
        return Colors.roundRgbColor(r, g, b, max);
    }

    private static boolean attr(StringBuilder sb, String s, boolean first) {
        if (!first) {
            sb.append(";");
        }
        sb.append(s);
        return false;
    }

    public abstract AttributedStyle styleAt(int index);

    int styleCodeAt(int index) {
        return styleAt(index).getStyle();
    }

    public boolean isHidden(int index) {
        return (styleCodeAt(index) & F_HIDDEN) != 0;
    }

    public int runStart(int index) {
        AttributedStyle style = styleAt(index);
        while (index > 0 && styleAt(index - 1).equals(style)) {
            index--;
        }
        return index;
    }

    public int runLimit(int index) {
        AttributedStyle style = styleAt(index);
        while (index < length() - 1 && styleAt(index + 1).equals(style)) {
            index++;
        }
        return index + 1;
    }

    @Override
    public abstract AttributedString subSequence(int start, int end);

    public AttributedString substring(int start, int end) {
        return subSequence(start, end);
    }

    protected abstract char[] buffer();

    protected abstract int offset();

    @Override
    public char charAt(int index) {
        return buffer()[offset() + index];
    }

    public int codePointAt(int index) {
        return Character.codePointAt(buffer(), index + offset());
    }

    public boolean contains(char c) {
        for (int i = 0; i < length(); i++) {
            if (charAt(i) == c) {
                return true;
            }
        }
        return false;
    }

    public int codePointBefore(int index) {
        return Character.codePointBefore(buffer(), index + offset());
    }

    public int codePointCount(int index, int length) {
        return Character.codePointCount(buffer(), index + offset(), length);
    }

    public int columnLength() {
        int cols = 0;
        int len = length();
        for (int cur = 0; cur < len; ) {
            int cp = codePointAt(cur);
            if (!isHidden(cur))
                cols += WCWidth.wcwidth(cp);
            cur += Character.charCount(cp);
        }
        return cols;
    }

    public AttributedString columnSubSequence(int start, int stop) {
        int begin = 0;
        int col = 0;
        while (begin < this.length()) {
            int cp = codePointAt(begin);
            int w = isHidden(begin) ? 0 : WCWidth.wcwidth(cp);
            if (col + w > start) {
                break;
            }
            begin += Character.charCount(cp);
            col += w;
        }
        int end = begin;
        while (end < this.length()) {
            int cp = codePointAt(end);
            if (cp == '\n')
                break;
            int w = isHidden(end) ? 0 : WCWidth.wcwidth(cp);
            if (col + w > stop) {
                break;
            }
            end += Character.charCount(cp);
            col += w;
        }
        return subSequence(begin, end);
    }

    public List<AttributedString> columnSplitLength(int columns) {
        return columnSplitLength(columns, false, true);
    }

    public List<AttributedString> columnSplitLength(int columns, boolean includeNewlines, boolean delayLineWrap) {
        List<AttributedString> strings = new ArrayList<>();
        int cur = 0;
        int beg = cur;
        int col = 0;
        while (cur < length()) {
            int cp = codePointAt(cur);
            int w = isHidden(cur) ? 0 : WCWidth.wcwidth(cp);
            if (cp == '\n') {
                strings.add(subSequence(beg, includeNewlines ? cur+1 : cur));
                beg = cur + 1;
                col = 0;
            } else if ((col += w) > columns) {
                strings.add(subSequence(beg, cur));
                beg = cur;
                col = w;
            }
            cur += Character.charCount(cp);
        }
        strings.add(subSequence(beg, cur));
        return strings;
    }

    @Override
    public String toString() {
        return new String(buffer(), offset(), length());
    }

    public AttributedString toAttributedString() {
        return substring(0, length());
    }

}
