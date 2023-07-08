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

package java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils;


import java.io.java.io.java.io.java.io.IOException;
import java.io.StringReader;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



public class RFC2253Parser {

    /**
     * Method rfc2253toXMLdsig
     *
     * @param dn
     * @return normalized string
     */
    public static String rfc2253toXMLdsig(String dn) {
        // Transform from RFC1779 to RFC2253
        String normalized = normalize(dn, true);

        return rfctoXML(normalized);
    }

    /**
     * Method xmldsigtoRFC2253
     *
     * @param dn
     * @return normalized string
     */
    public static String xmldsigtoRFC2253(String dn) {
        // Transform from RFC1779 to RFC2253
        String normalized = normalize(dn, false);

        return xmltoRFC(normalized);
    }

    /**
     * Method normalize
     *
     * @param dn
     * @return normalized string
     */
    public static String normalize(String dn) {
        return normalize(dn, true);
    }

    /**
     * Method normalize
     *
     * @param dn
     * @param toXml
     * @return normalized string
     */
    public static String normalize(String dn, boolean toXml) {
        //if empty string
        if (dn == null || dn.isEmpty()) {
            return "";
        }

        try {
            String DN = semicolonToComma(dn);
            StringBuilder sb = new StringBuilder();
            int i = 0;
            int l = 0;
            int k;

            //for name component
            for (int j = 0; (k = DN.indexOf(',', j)) >= 0; j = k + 1) {
                l += countQuotes(DN, j, k);

                if (k > 0 && DN.charAt(k - 1) != '\\' && (l % 2) == 0) {
                    sb.append(parseRDN(DN.substring(i, k).trim(), toXml)).append(',');

                    i = k + 1;
                    l = 0;
                }
            }

            sb.append(parseRDN(trim(DN.substring(i)), toXml));

            return sb.toString();
        } catch (IOException ex) {
            return dn;
        }
    }

    /**
     * Method parseRDN
     *
     * @param str
     * @param toXml
     * @return normalized string
     * @throws IOException
     */
    static String parseRDN(String str, boolean toXml) throws IOException {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int l = 0;
        int k;

        for (int j = 0; (k = str.indexOf('+', j)) >= 0; j = k + 1) {
            l += countQuotes(str, j, k);

            if (k > 0 && str.charAt(k - 1) != '\\' && (l % 2) == 0) {
                sb.append(parseATAV(trim(str.substring(i, k)), toXml)).append('+');

                i = k + 1;
                l = 0;
            }
        }

        sb.append(parseATAV(trim(str.substring(i)), toXml));

        return sb.toString();
    }

    /**
     * Method parseATAV
     *
     * @param str
     * @param toXml
     * @return normalized string
     * @throws IOException
     */
    static String parseATAV(String str, boolean toXml) throws IOException {
        int i = str.indexOf('=');

        if (i == -1 || i > 0 && str.charAt(i - 1) == '\\') {
            return str;
        }
        String attrType = normalizeAT(str.substring(0, i));
        // only normalize if value is a String
        String attrValue = null;
        if (attrType.charAt(0) >= '0' && attrType.charAt(0) <= '9') {
            attrValue = str.substring(i + 1);
        } else {
            attrValue = normalizeV(str.substring(i + 1), toXml);
        }

        return attrType + "=" + attrValue;

    }

    /**
     * Method normalizeAT
     *
     * @param str
     * @return normalized string
     */
    static String normalizeAT(String str) {

        String at = str.toUpperCase().trim();

        if (at.startsWith("OID")) {
            at = at.substring(3);
        }

        return at;
    }

    /**
     * Method normalizeV
     *
     * @param str
     * @param toXml
     * @return normalized string
     * @throws IOException
     */
    static String normalizeV(String str, boolean toXml) throws IOException {
        String value = trim(str);

        if (value.startsWith("\"")) {
            StringBuilder sb = new StringBuilder();
            StringReader sr = new StringReader(value.substring(1, value.length() - 1));
            int i = 0;
            char c;

            while ((i = sr.read()) > -1) {
                c = (char) i;

                //the following char is defined at 4.Relationship with RFC1779 and LDAPv2 inrfc2253
                if (c == ',' || c == '=' || c == '+' || c == '<'
                    || c == '>' || c == '#' || c == ';') {
                    sb.append('\\');
                }

                sb.append(c);
            }

            value = trim(sb.toString());
        }

        if (toXml) {
            if (value.length() > 0 && value.charAt(0) == '#') {
                value = '\\' + value;
            }
        } else {
            if (value.startsWith("\\#")) {
                value = value.substring(1);
            }
        }

        return value;
    }

    /**
     * Method rfctoXML
     *
     * @param string
     * @return normalized string
     */
    static String rfctoXML(String string) {
        try {
            String s = changeLess32toXML(string);

            return changeWStoXML(s);
        } catch (Exception e) {
            return string;
        }
    }

    /**
     * Method xmltoRFC
     *
     * @param string
     * @return normalized string
     */
    static String xmltoRFC(String string) {
        try {
            String s = changeLess32toRFC(string);

            return changeWStoRFC(s);
        } catch (Exception e) {
            return string;
        }
    }

    /**
     * Method changeLess32toRFC
     *
     * @param string
     * @return normalized string
     * @throws IOException
     */
    static String changeLess32toRFC(String string) throws IOException {
        StringBuilder sb = new StringBuilder();
        StringReader sr = new StringReader(string);
        int i = 0;
        char c;

        while ((i = sr.read()) > -1) {
            c = (char) i;

            if (c == '\\') {
                sb.append(c);

                char c1 = (char) sr.read();
                char c2 = (char) sr.read();

                //65 (A) 97 (a)
                if ((c1 >= 48 && c1 <= 57 || c1 >= 65 && c1 <= 70 || c1 >= 97 && c1 <= 102)
                    && (c2 >= 48 && c2 <= 57
                        || c2 >= 65 && c2 <= 70
                        || c2 >= 97 && c2 <= 102)) {
                    try {
                        char ch = (char) Byte.parseByte(String.valueOf(c1) + c2, 16);

                        sb.append(ch);
                    } catch (NumberFormatException ex) {
                        throw new IOException(ex);
                    }
                } else {
                    sb.append(c1);
                    sb.append(c2);
                }
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * Method changeLess32toXML
     *
     * @param string
     * @return normalized string
     * @throws IOException
     */
    static String changeLess32toXML(String string) throws IOException {
        StringBuilder sb = new StringBuilder();
        StringReader sr = new StringReader(string);
        int i = 0;

        while ((i = sr.read()) > -1) {
            if (i < 32) {
                sb.append('\\');
                sb.append(Integer.toHexString(i));
            } else {
                sb.append((char) i);
            }
        }

        return sb.toString();
    }

    /**
     * Method changeWStoXML
     *
     * @param string
     * @return normalized string
     * @throws IOException
     */
    static String changeWStoXML(String string) throws IOException {
        StringBuilder sb = new StringBuilder();
        StringReader sr = new StringReader(string);
        int i = 0;
        char c;

        while ((i = sr.read()) > -1) {
            c = (char) i;

            if (c == '\\') {
                char c1 = (char) sr.read();

                if (c1 == ' ') {
                    sb.append('\\');

                    String s = "20";

                    sb.append(s);
                } else {
                    sb.append('\\');
                    sb.append(c1);
                }
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * Method changeWStoRFC
     *
     * @param string
     * @return normalized string
     */
    static String changeWStoRFC(String string) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int k;

        for (int j = 0; (k = string.indexOf("\\20", j)) >= 0; j = k + 3) {
            sb.append(trim(string.substring(i, k))).append("\\ ");

            i = k + 3;
        }

        sb.append(string.substring(i));

        return sb.toString();
    }

    /**
     * Method semicolonToComma
     *
     * @param str
     * @return normalized string
     */
    static String semicolonToComma(String str) {
        return removeWSandReplace(str, ";", ",");
    }

    /**
     * Method removeWhiteSpace
     *
     * @param str
     * @param symbol
     * @return normalized string
     */
    static String removeWhiteSpace(String str, String symbol) {
        return removeWSandReplace(str, symbol, symbol);
    }

    /**
     * Method removeWSandReplace
     *
     * @param str
     * @param symbol
     * @param replace
     * @return normalized string
     */
    static String removeWSandReplace(String str, String symbol, String replace) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int l = 0;
        int k;

        for (int j = 0; (k = str.indexOf(symbol, j)) >= 0; j = k + 1) {
            l += countQuotes(str, j, k);

            if (k > 0 && str.charAt(k - 1) != '\\' && (l % 2) == 0) {
                sb.append(trim(str.substring(i, k))).append(replace);

                i = k + 1;
                l = 0;
            }
        }

        sb.append(trim(str.substring(i)));

        return sb.toString();
    }

    /**
     * Returns the number of Quotation from i to j
     *
     * @param s
     * @param i
     * @param j
     * @return number of quotes
     */
    private static int countQuotes(String s, int i, int j) {
        int k = 0;

        for (int l = i; l < j; l++) {
            if (s.charAt(l) == '"') {
                k++;
            }
        }

        return k;
    }

    //only for the end of a space character occurring at the end of the string from rfc2253

    /**
     * Method trim
     *
     * @param str
     * @return the string
     */
    static String trim(String str) {

        String trimed = str.trim();
        int i = str.indexOf(trimed) + trimed.length();

        if (str.length() > i && trimed.endsWith("\\")
            && !trimed.endsWith("\\\\") && str.charAt(i) == ' ') {
            trimed = trimed + " ";
        }

        return trimed;
    }

}
