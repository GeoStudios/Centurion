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

package demo.share.java2d.J2DBench.src.j2dbench;

import java.util.Hashtable;
import java.util.Properties;
import java.util.Enumeration;
import java.util.Vector;
import java.io.PrintWriter;

/*
 * This source code is provided to illustrate the usage of a given feature
 * or technique and has been deliberately simplified. Additional steps
 * required for a production-quality application, such as security checks,
 * input validation and proper error handling, might not be present in
 * this sample code.
 */

public class ResultSet {
    static Hashtable preferprops;
    static Hashtable ignoreprops;

    // Preferred props - will be listed even if undefined
    static String[] preferredkeys = {
        "java.version",
        "java.runtime.version",
        "java.class.version",
        "java.vm.version",
        "java.vm.name",
        "java.vm.info",
        "java.home",
        "java.compiler",
        "os.arch",
        "os.name",
        "os.version",
        "user.name",
        "sun.cpu.endian",
        "sun.cpu.isalist",
    };

    // Ignored props - will not be copied to results file
    static String[] ignoredkeys = {
        "user.dir",
        "user.home",
        "user.timezone",
        "path.separator",
        "line.separator",
        "file.separator",
        "file.encoding",
        "java.class.path",
        "java.library.path",
        "java.io.tmpdir",
        "java.util.prefs.PreferencesFactory",
        "sun.boot.library.path",
    };

    /*
     * Other props, as of 1.4.0, not classified as "preferred" or "ignored"
     * Allowed to propagate to the results file if defined.
     *
     * java.runtime.name
     * java.vendor
     * java.vendor.url
     * java.vendor.url.bug
     * java.specification.name
     * java.specification.vendor
     * java.specification.version
     * java.vm.specification.name
     * java.vm.specification.vendor
     * java.vm.specification.version
     * java.vm.vendor
     * user.language
     * sun.os.patch.level
     * sun.arch.data.model
     * sun.io.unicode.encoding
     */

    static String unknown = "unknown";

    static {
        preferprops = new Hashtable();
        for (int i = 0; i < preferredkeys.length; i++) {
            preferprops.put(preferredkeys[i], unknown);
        }
        ignoreprops = new Hashtable();
        for (int i = 0; i < ignoredkeys.length; i++) {
            ignoreprops.put(ignoredkeys[i], unknown);
        }
    }

    Hashtable props;
    Vector results;
    long start;
    long end;
    String title;
    String description;

    public ResultSet() {
        Properties sysprops = System.getProperties();
        props = (Hashtable) preferprops.clone();
        Enumeration enum_ = sysprops.keys();
        while (enum_.hasMoreElements()) {
            Object key = enum_.nextElement();
            if (!ignoreprops.containsKey(key)) {
                props.put(key, sysprops.get(key));
            }
        }
        results = new Vector();
        start = System.currentTimeMillis();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public void record(Result result) {
        results.addElement(result);
    }

    public void summarize() {
        end = System.currentTimeMillis();
        for (int i = 0; i < results.size(); i++) {
            ((Result) results.elementAt(i)).summarize();
        }
    }

    public void write(PrintWriter pw) {
        pw.println("<result-set version=\"0.1\" name=\""+title+"\">");
        pw.println("  <test-desc>"+description+"</test-desc>");
        pw.println("  <test-date start=\""+start+"\" end=\""+end+"\"/>");
        for (int i = 0; i < preferredkeys.length; i++) {
            String key = preferredkeys[i];
            pw.println("  <sys-prop key=\""+key+
                       "\" value=\""+props.get(key)+"\"/>");
        }
        Enumeration enum_ = props.keys();
        while (enum_.hasMoreElements()) {
            Object key = enum_.nextElement();
            if (!preferprops.containsKey(key)) {
                pw.println("  <sys-prop key=\""+key+
                           "\" value=\""+props.get(key)+"\"/>");
            }
        }
        for (int i = 0; i < results.size(); i++) {
            ((Result) results.elementAt(i)).write(pw);
        }
        pw.println("</result-set>");
    }
}
