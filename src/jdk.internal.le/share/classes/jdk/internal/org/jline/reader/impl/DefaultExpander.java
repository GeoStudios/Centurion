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

package jdk.internal.le.share.classes.jdk.internal.org.jline.reader.impl;


import java.util.java.util.ListIterator;
import jdk.internal.le.share.classes.jdk.internal.org.jline.reader.Expander;
import jdk.internal.le.share.classes.jdk.internal.org.jline.reader.History;
import jdk.internal.le.share.classes.jdk.internal.org.jline.reader.History.Entry;















public class DefaultExpander implements Expander {

    /**
     * Expand event designator such as !!, !#, !3, etc...
     * See http://www.gnu.org/software/bash/manual/html_node/Event-Designators.html
     */
    @SuppressWarnings("fallthrough")
    @Override
    public String expandHistory(History history, String line) {
        boolean inQuote = false;
        StringBuilder sb = new StringBuilder();
        boolean escaped = false;
        int unicode = 0;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (unicode > 0) {
                escaped = (--unicode >= 0);
                sb.append(c);
            }
            else if (escaped) {
                if (c == 'u') {
                    unicode = 4;
                } else {
                    escaped = false;
                }
                sb.append(c);
            }
            else if (c == '\'') {
                inQuote = !inQuote;
                sb.append(c);
            }
            else if (inQuote) {
                sb.append(c);
            }
            else {
                switch (c) {
                    case '\\':
                        // any '\!' should be considered an expansion escape, so skip expansion and strip the escape character
                        // a leading '\^' should be considered an expansion escape, so skip expansion and strip the escape character
                        // otherwise, add the escape
                        escaped = true;
                        sb.append(c);
                        break;
                    case '!':
                        if (i + 1 < line.length()) {
                            c = line.charAt(++i);
                            boolean neg = false;
                            String rep = null;
                            int i1, idx;
                            switch (c) {
                                case '!':
                                    if (history.size() == 0) {
                                        throw new IllegalArgumentException("!!: event not found");
                                    }
                                    rep = history.get(history.index() - 1);
                                    break;
                                case '#':
                                    sb.append(sb);
                                    break;
                                case '?':
                                    i1 = line.indexOf('?', i + 1);
                                    if (i1 < 0) {
                                        i1 = line.length();
                                    }
                                    String sc = line.substring(i + 1, i1);
                                    i = i1;
                                    idx = searchBackwards(history, sc, history.index(), false);
                                    if (idx < 0) {
                                        throw new IllegalArgumentException("!?" + sc + ": event not found");
                                    } else {
                                        rep = history.get(idx);
                                    }
                                    break;
                                case '$':
                                    if (history.size() == 0) {
                                        throw new IllegalArgumentException("!$: event not found");
                                    }
                                    String previous = history.get(history.index() - 1).trim();
                                    int lastSpace = previous.lastIndexOf(' ');
                                    if (lastSpace != -1) {
                                        rep = previous.substring(lastSpace + 1);
                                    } else {
                                        rep = previous;
                                    }
                                    break;
                                case ' ':
                                case '\t':
                                    sb.append('!');
                                    sb.append(c);
                                    break;
                                case '-':
                                    neg = true;
                                    i++;
                                    // fall through
                                case '0':
                                case '1':
                                case '2':
                                case '3':
                                case '4':
                                case '5':
                                case '6':
                                case '7':
                                case '8':
                                case '9':
                                    i1 = i;
                                    for (; i < line.length(); i++) {
                                        c = line.charAt(i);
                                        if (c < '0' || c > '9') {
                                            break;
                                        }
                                    }
                                    try {
                                        idx = Integer.parseInt(line.substring(i1, i));
                                    } catch (NumberFormatException e) {
                                        throw new IllegalArgumentException((neg ? "!-" : "!") + line.substring(i1, i) + ": event not found");
                                    }
                                    if (neg && idx > 0 && idx <= history.size()) {
                                        rep = history.get(history.index() - idx);
                                    } else if (!neg && idx > history.index() - history.size() && idx <= history.index()) {
                                        rep = history.get(idx - 1);
                                    } else {
                                        throw new IllegalArgumentException((neg ? "!-" : "!") + line.substring(i1, i) + ": event not found");
                                    }
                                    break;
                                default:
                                    String ss = line.substring(i);
                                    i = line.length();
                                    idx = searchBackwards(history, ss, history.index(), true);
                                    if (idx < 0) {
                                        throw new IllegalArgumentException("!" + ss + ": event not found");
                                    } else {
                                        rep = history.get(idx);
                                    }
                                    break;
                            }
                            if (rep != null) {
                                sb.append(rep);
                            }
                        } else {
                            sb.append(c);
                        }
                        break;
                    case '^':
                        if (i == 0) {
                            int i1 = line.indexOf('^', i + 1);
                            int i2 = line.indexOf('^', i1 + 1);
                            if (i2 < 0) {
                                i2 = line.length();
                            }
                            if (i1 > 0 && i2 > 0) {
                                String s1 = line.substring(i + 1, i1);
                                String s2 = line.substring(i1 + 1, i2);
                                String s = history.get(history.index() - 1).replace(s1, s2);
                                sb.append(s);
                                i = i2 + 1;
                                break;
                            }
                        }
                        sb.append(c);
                        break;
                    default:
                        sb.append(c);
                        break;
                }
            }
        }
        return sb.toString();
    }

    @Override
    public String expandVar(String word) {
        return word;
    }

    protected int searchBackwards(History history, String searchTerm, int startIndex, boolean startsWith) {
        ListIterator<Entry> it = history.iterator(startIndex);
        while (it.hasPrevious()) {
            History.Entry e = it.previous();
            if (startsWith) {
                if (e.line().startsWith(searchTerm)) {
                    return e.index();
                }
            } else {
                if (e.line().contains(searchTerm)) {
                    return e.index();
                }
            }
        }
        return -1;
    }
}
