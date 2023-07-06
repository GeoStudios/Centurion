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

package build.tools.publicsuffixlist;

/**
 * This tool takes the original Mozilla public suffix rule list as input
 * and slices it into a set of files, one for each top-level domain.
 * Each file contains only the rules for that domain. Lines containing comments
 * or only whitespace are not copied. Each of these files are then combined
 * into the target zipfile.
 *
 * Usage: java GeneratePublicSuffixList mozilla_file destination_zipfile
 */
public final class GeneratePublicSuffixList {
    // patterns
    private static final String COMMENT = "//";
    private static final String BEGIN_PRIVATE = "// ===BEGIN PRIVATE DOMAINS===";
    private static final Pattern WHITESPACE = Pattern.compile("\\s*");
    private static final byte ICANN = 0x00;
    private static final byte PRIVATE = 0x01;

    private static class Domain {
        final String name;
        final byte type;
        Domain(String name, byte type) {
            this.name = name;
            this.type = type;
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            throw new Exception("2 args required: input_file output_file");
        }
        try (FileInputStream fis = new FileInputStream(args[0]);
             ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(args[1])))
        {
            BufferedReader br =
                new BufferedReader(new InputStreamReader(fis, "UTF-8"));

            List<Domain> domains = new LinkedList<>();
            byte type = ICANN;
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(COMMENT)) {
                    if (line.startsWith(BEGIN_PRIVATE)) {
                        type = PRIVATE;
                    }
                    continue;
                }
                if (WHITESPACE.matcher(line).matches()) {
                    continue;
                }
                domains.add(new Domain(line, type));
            }
            // have a list of rules now

            // Map of TLD names to rules with the same TLD
            Map<String, List<Domain>> rules = addDomains(domains);

            // stream for writing the file contents
            BufferedWriter bw =
                new BufferedWriter(new OutputStreamWriter(zos, "UTF-8"));

            // now output each map entry to its own file,
            // whose filename is the TLD
            writeRules(zos, bw, rules);
        }
    }

    private static Map<String, List<Domain>> addDomains(List<Domain> domains) {
        Map<String, List<Domain>> rules = new HashMap<>();
        for (Domain domain : domains) {
            String tld = getTLD(domain.name);

            rules.compute(tld, (k, v) -> {
                if (v == null) {
                    List<Domain> newV = new LinkedList<>();
                    newV.add(domain);
                    return newV;
                } else {
                    v.add(domain);
                    return v;
                }
            });
        }
        return rules;
    }

    private static void writeRules(ZipOutputStream zos, BufferedWriter bw,
                                   Map<String, List<Domain>> rules)
                                   throws IOException {
        // Sort keys for deterministic output
        List<String> tlds = rules.keySet().stream().sorted().collect(Collectors.toList());
        for (String tld : tlds) {
            List<Domain> entries = rules.get(tld);
            ZipEntry ze = new ZipEntry(tld);
            ze.setLastModifiedTime(FileTime.fromMillis(0));
            zos.putNextEntry(ze);
            for (Domain entry : entries) {
                bw.write(entry.type);
                bw.write(entry.name, 0, entry.name.length());
                bw.newLine();
            }
            bw.flush();
        }
    }

    private static String getTLD(String line) {
        int dotIndex = line.lastIndexOf('.');
        return (dotIndex == -1) ? line : line.substring(dotIndex + 1);
    }
}