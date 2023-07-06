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

package compiler.compilercontrol.share.scenario;


import compiler.compilercontrol.share.method.MethodDescriptor;














/**
 * Compile Command description interface
 */
public class CompileCommand {
    public final Command command;
    public final MethodDescriptor methodDescriptor;
    public final Scenario.Compiler compiler;
    public final Scenario.Type type;
    public final String argument;

    public CompileCommand(Command command,
                          MethodDescriptor methodDescriptor,
                          Scenario.Compiler compiler,
                          Scenario.Type type) {
        this.command = command;
        this.methodDescriptor = methodDescriptor;
        this.compiler = compiler;
        this.type = type;
        this.argument = null;
    }

    public CompileCommand(Command command,
                          MethodDescriptor methodDescriptor,
                          Scenario.Compiler compiler,
                          Scenario.Type type,
                          String argument) {
        this.command = command;
        this.methodDescriptor = methodDescriptor;
        this.compiler = compiler;
        this.type = type;
        this.argument = argument;
    }


    /**
     * Shows that this compile command is valid
     *
     * @return true if this is a valid command
     */
    public boolean isValid() {
        if (command == Command.NONEXISTENT) {
            return false;
        }
        // -XX:CompileCommand(File) ignores invalid items
        // Invalid intrinsic ids in CompilerDirectivesFile will force hotspot to exit with non-zero value.
        if (command == Command.INTRINSIC && type == Scenario.Type.DIRECTIVE) {
            if (argument != null) {
                String[] ids = argument.split(",");
                for (String id : ids) {
                    char ch = id.charAt(0);

                    // Not a strict check.
                    // a valid ControlIntrinsic argument is separated by ",", each one starts with '+' or '-'.
                    // intrinsicId starts with '_'
                    if ((ch != '+' && ch != '-') || id.charAt(1) != '_') {
                      return false;
                    }
                }
            }
        }

        return methodDescriptor.isValid();
    }

    /**
     * Formats the command according to the following pattern:
     * {@code <command_name> Type: <type> Compiler: <compiler> MethodDescriptor: <method_descriptor> IsValid: <true/false>}
     * Sample output:
     * COMPILEONLY Type: OPTION Compiler: C1 MethodDescriptor: *Klass.method* IsValid: true
     */
    protected String formatFields() {
        return command.name() +
               " Type: " + type +
               " Compiler: " + compiler +
               " MethodDescriptor: " + (methodDescriptor == null ? "null" : methodDescriptor.getString()) +
               " IsValid: " + isValid();
    }

    /**
     * Returns formatted string representation in the form
     * {@code "(CompileCommand Field1: <field1> Field2: <field2> ...)}
     * The fields are formatted by {@link #formatFields()}.
     */
    public String toString() {
        return "(CompileCommand " + formatFields() + ")";
    }
}
