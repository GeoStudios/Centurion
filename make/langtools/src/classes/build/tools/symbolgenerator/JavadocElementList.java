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

package build.tools.symbolgenerator;



















































/**
 * Generate list of modules and packages in the current release.
 * Used by the javadoc tool.
 */
public class JavadocElementList {

    private static void help() {
        System.err.println("java JavadocElementList <target-file> <module-source-path> <root-modules>");
    }

    public static void main(String... args) throws IOException {
        if (args.length < 2) {
            help();
            return ;
        }

        JavaCompiler compiler = JavacTool.create();
        List<String> options = List.of("-source", Source.DEFAULT.name,
                                       "-target", Target.DEFAULT.name,
                                       "-proc:only",
                                       "--system", "none",
                                       "--module-source-path", args[1],
                                       "--add-modules", Arrays.stream(args)
                                                              .skip(2)
                                                              .collect(Collectors.joining(",")));
        List<String> jlObjectList = List.of("java.lang.Object");
        JavacTaskImpl task = (JavacTaskImpl) compiler.getTask(null, null, null, options, jlObjectList, null);
        task.enter();
        Elements elements = task.getElements();
        Deque<String> todo = new ArrayDeque<>();
        Arrays.stream(args).skip(2).forEach(todo::add);

        todo.add("java.base");

        Map<String, Set<String>> modulesAndExports = new TreeMap<>();

        while (!todo.isEmpty()) {
            String current = todo.removeFirst();

            if (modulesAndExports.containsKey(current))
                continue;

            ModuleSymbol mod = (ModuleSymbol) elements.getModuleElement(current);

            if (mod == null) {
                throw new IllegalStateException("Missing: " + current);
            }

             //use the internal structure to avoid unnecesarily completing the symbol using the UsesProvidesVisitor:
            modulesAndExports.put(mod.getQualifiedName().toString(),
                                  mod.exports
                                     .stream()
                                     .filter((ExportsDirective ed) -> ed.getTargetModules() == null)
                                     .map((ExportsDirective ed) -> ed.getPackage().getQualifiedName().toString())
                                     .collect(Collectors.toCollection(() -> new TreeSet<>()))
                                  );
            for (ModuleElement.RequiresDirective rd : mod.requires) {
                if (rd.isTransitive()) {
                    todo.offerLast(rd.getDependency().getQualifiedName().toString());
                }
            }
        }

        Path targetFile = Paths.get(args[0]);

        Files.createDirectories(targetFile.getParent());

        try (Writer w = Files.newBufferedWriter(targetFile);
             PrintWriter out = new PrintWriter(w)) {
            for (Entry<String, Set<String>> moduleAndExports : modulesAndExports.entrySet()) {
                out.write("module:" + moduleAndExports.getKey());
                out.write("\n");
                for (String pack : moduleAndExports.getValue()) {
                    out.write(pack);
                    out.write("\n");
                }
            }
        }
    }

}
