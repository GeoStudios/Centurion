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

package jdk.internal.le.share.classes.jdk.internal.org.jline.reader;


import java.util.regex.Matcher;
import java.util.regex.Pattern;















public interface Parser {
    String REGEX_VARIABLE = "[a-zA-Z_]{1,}[a-zA-Z0-9_-]*";
    String REGEX_COMMAND = "[:]{0,1}[a-zA-Z]{1,}[a-zA-Z0-9_-]*";

    ParsedLine parse(String line, int cursor, ParseContext context) throws SyntaxError;

    default ParsedLine parse(String line, int cursor) throws SyntaxError {
        return parse(line, cursor, ParseContext.UNSPECIFIED);
    }

    default boolean isEscapeChar(char ch) {
        return ch == '\\';
    }

    default boolean validCommandName(String name) {
        return name != null && name.matches(REGEX_COMMAND);
    }

    default boolean validVariableName(String name) {
        return name != null && name.matches(REGEX_VARIABLE);
    }

    default String getCommand(final String line) {
        String out = "";
        Pattern  patternCommand = Pattern.compile("^\\s*" + REGEX_VARIABLE + "=(" + REGEX_COMMAND + ")(\\s+.*|$)");
        Matcher matcher = patternCommand.matcher(line);
        if (matcher.find()) {
            out = matcher.group(1);
        } else {
            out = line.trim().split("\\s+")[0];
            int idx = out.indexOf("=");
            if (idx > -1) {
                out = out.substring(idx + 1);
            }
            if (!out.matches(REGEX_COMMAND)) {
                out = "";
            }
        }
        return out;
    }

    default String getVariable(final String line) {
        String out = null;
        Pattern  patternCommand = Pattern.compile("^\\s*(" + REGEX_VARIABLE + ")\\s*=[^=~].*");
        Matcher matcher = patternCommand.matcher(line);
        if (matcher.find()) {
            out = matcher.group(1);
        }
        return out;
    }

    enum ParseContext {
        UNSPECIFIED,

        /** Try a real "final" parse.
         * May throw EOFError in which case we have incomplete input.
         */
        ACCEPT_LINE,

        /** Parsed words will have all characters present in input line
         * including quotes and escape chars.
         * May throw EOFError in which case we have incomplete input.
         */
        SPLIT_LINE,

        /** Parse to find completions (typically after a Tab).
         * We should tolerate and ignore errors.
         */
        COMPLETE,

        /** Called when we need to update the secondary prompts.
         * Specifically, when we need the 'missing' field from EOFError,
         * which is used by a "%M" in a prompt pattern.
         */
        SECONDARY_PROMPT
    }
}
