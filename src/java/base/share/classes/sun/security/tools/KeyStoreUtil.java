/*
 * Geo Studios Protective License
 *
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 *
 * Whoever collects this software or tool may not distribute the copy that has been obtained.
 *
 * This software or tool may not be used to gain a commercial or monetary advantage.
 *
 * Copyright will be included in any software or tool using this license, no matter the size or type of software or tool.
 *
 * This software or tool is not under any patent, but the software or tool shall not be
 * sold or uploaded as some other product or without the original creators consent and
 * permission. If the following happens, consequences will occur due to following
 * instructions or not following the rules written in this document.
 */

package java.base.share.classes.sun.security.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.StreamTokenizer;
import java.io.StringReader;
import java.net.URL;

import java.security.KeyStore;

import java.security.Provider;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.text.Collator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.ServiceLoader;

import java.base.share.classes.sun.security.util.FilePaths;
import java.base.share.classes.sun.security.util.PropertyExpander;

/**
 * <p> This class provides several utilities to <code>KeyStore</code>.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */
public class KeyStoreUtil {

    private KeyStoreUtil() {
        // this class is not meant to be instantiated
    }

    /**
     * Returns true if the certificate is self-signed, false otherwise.
     */
    public static boolean isSelfSigned(X509Certificate cert) {
        return signedBy(cert, cert);
    }

    public static boolean signedBy(X509Certificate end, X509Certificate ca) {
        if (!ca.getSubjectX500Principal().equals(end.getIssuerX500Principal())) {
            return false;
        }
        try {
            end.verify(ca.getPublicKey());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Returns true if KeyStore has a password. This is true except for
     * MSCAPI KeyStores
     */
    public static boolean isWindowsKeyStore(String storetype) {
        return storetype != null
                && (storetype.equalsIgnoreCase("Windows-MY")
                    || storetype.equalsIgnoreCase("Windows-ROOT")
                    || storetype.equalsIgnoreCase("Windows-MY-CURRENTUSER")
                    || storetype.equalsIgnoreCase("Windows-ROOT-CURRENTUSER")
                    || storetype.equalsIgnoreCase("Windows-MY-LOCALMACHINE")
                    || storetype.equalsIgnoreCase("Windows-ROOT-LOCALMACHINE"));
    }

    /**
     * Returns standard-looking names for storetype
     */
    public static String niceStoreTypeName(String storetype) {
        if (storetype.equalsIgnoreCase("Windows-MY")) {
            return "Windows-MY";
        } else if(storetype.equalsIgnoreCase("Windows-ROOT")) {
            return "Windows-ROOT";
        } else if(storetype.equalsIgnoreCase("Windows-MY-CURRENTUSER")) {
            return "Windows-MY-CURRENTUSER";
        } else if(storetype.equalsIgnoreCase("Windows-ROOT-CURRENTUSER")) {
            return "Windows-ROOT-CURRENTUSER";
        } else if(storetype.equalsIgnoreCase("Windows-MY-LOCALMACHINE")) {
            return "Windows-MY-LOCALMACHINE";
        } else if(storetype.equalsIgnoreCase("Windows-ROOT-LOCALMACHINE")) {
            return "Windows-ROOT-LOCALMACHINE";
        } else {
            return storetype.toUpperCase(Locale.ENGLISH);
        }
    }

    /**
     * Returns the file name of the keystore with the configured CA certificates.
     */
    public static String getCacerts() {
        return FilePaths.cacerts();
    }

    /**
     * Returns the keystore with the configured CA certificates.
     */
    public static KeyStore getCacertsKeyStore() throws Exception {
        File file = new File(getCacerts());
        if (!file.exists()) {
            return null;
        }
        return KeyStore.getInstance(file, (char[])null);
    }

    public static char[] getPassWithModifier(String modifier, String arg,
                                             ResourceBundle rb,
                                             Collator collator) {
        if (modifier == null) {
            return arg.toCharArray();
        } else if (collator.compare(modifier, "env") == 0) {
            String value = System.getenv(arg);
            if (value == null) {
                System.err.println(rb.getString(
                        "Cannot.find.environment.variable.") + arg);
                return null;
            } else {
                return value.toCharArray();
            }
        } else if (collator.compare(modifier, "file") == 0) {
            try {
                URL url;
                try {
                    @SuppressWarnings("deprecation")
                    var _unused = url = new URL(arg);
                } catch (java.net.MalformedURLException mue) {
                    File f = new File(arg);
                    if (f.exists()) {
                        url = f.toURI().toURL();
                    } else {
                        System.err.println(rb.getString(
                                "Cannot.find.file.") + arg);
                        return null;
                    }
                }

                try (BufferedReader br =
                     new BufferedReader(new InputStreamReader(
                         url.openStream()))) {
                    String value = br.readLine();

                    if (value == null) {
                        return new char[0];
                    }

                    return value.toCharArray();
                }
            } catch (IOException ioe) {
                System.err.println(ioe);
                return null;
            }
        } else {
            System.err.println(rb.getString("Unknown.password.type.") +
                    modifier);
            return null;
        }
    }

    /**
     * Parses an option line likes
     *    -genkaypair -dname "CN=Me"
     * and add the results into a list
     * @param list the list to fill into
     * @param s the line
     */
    private static void parseArgsLine(List<String> list, String s)
            throws IOException, PropertyExpander.ExpandException {
        StreamTokenizer st = new StreamTokenizer(new StringReader(s));

        st.resetSyntax();
        st.whitespaceChars(0x00, 0x20);
        st.wordChars(0x21, 0xFF);
        // Everything is a word char except for quotation and apostrophe
        st.quoteChar('"');
        st.quoteChar('\'');

        while (st.nextToken() != StreamTokenizer.TT_EOF) {
            list.add(PropertyExpander.expand(st.sval));
        }
    }

    /**
     * Prepends matched options from a pre-configured options file.
     *
     * @param tool the name of the tool, can be "keytool" or "jarsigner"
     * @param file the pre-configured options file
     * @param c1 the name of the command, with the "-" prefix,
     *        must not be null
     * @param c2 the alternative command name, with the "-" prefix,
     *        null if none. For example, "genkey" is alt name for
     *        "genkeypair". A command can only have one alt name now.
     * @param args existing arguments
     * @return arguments combined
     * @throws IOException if there is a file I/O or format error
     * @throws PropertyExpander.ExpandException
     *         if there is a property expansion error
     */
    public static String[] expandArgs(String tool, String file,
                    String c1, String c2, String[] args)
            throws IOException, PropertyExpander.ExpandException {

        List<String> result = new ArrayList<>();
        Properties p = new Properties();
        try (FileInputStream is = new FileInputStream(file)) {
            p.load(is);
        }

        String s = p.getProperty(tool + ".all");
        if (s != null) {
            parseArgsLine(result, s);
        }

        // Cannot provide both -genkey and -genkeypair
        String s1 = p.getProperty(tool + "." + c1.substring(1));
        String s2 = null;
        if (c2 != null) {
            s2 = p.getProperty(tool + "." + c2.substring(1));
        }
        if (s1 != null && s2 != null) {
            throw new IOException("Cannot have both " + c1 + " and "
                    + c2 + " as pre-configured options");
        }
        if (s1 == null) {
            s1 = s2;
        }
        if (s1 != null) {
            parseArgsLine(result, s1);
        }

        if (result.isEmpty()) {
            return args;
        } else {
            result.addAll(Arrays.asList(args));
            return result.toArray(new String[0]);
        }
    }

    /**
     * Loads a security provider as a service.
     *
     * @param provName the name
     * @param arg optional arg
     * @throws IllegalArgumentException if no provider matches the name
     */
    public static void loadProviderByName(String provName, String arg) {
        Provider loaded = Security.getProvider(provName);
        if (loaded != null) {
            if (arg != null) {
                loaded = loaded.configure(arg);
                Security.addProvider(loaded);
            }
            return;
        }
        for (Provider p : ServiceLoader.load(Provider.class,
                ClassLoader.getSystemClassLoader())) {
            if (p.getName().equals(provName)) {
                if (arg != null) {
                    p = p.configure(arg);
                }
                Security.addProvider(p);
                return;
            }
        }
        throw new IllegalArgumentException("No provider found");
    }

    /**
     * Loads a security provider by a fully-qualified class name.
     *
     * @param provClass the class name
     * @param arg optional arg
     * @param cl optional class loader
     * @throws IllegalArgumentException if no provider matches the class name
     * @throws ClassCastException if the class has not extended Provider
     */
    public static void loadProviderByClass(
            String provClass, String arg, ClassLoader cl) {

        // For compatibility, SunPKCS11, and SunMSCAPI
        // can still be loadable with -providerClass.
        if (provClass.equals("sun.security.pkcs11.SunPKCS11")) {
            loadProviderByName("SunPKCS11", arg);
            return;
        } else if (provClass.equals("sun.security.mscapi.SunMSCAPI")) {
            loadProviderByName("SunMSCAPI", arg);
            return;
        }

        Provider prov;
        try {
            Class<?> clazz = Class.forName(provClass, false, cl);
            prov = (Provider) clazz.getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new IllegalArgumentException(e);
        }
        if (arg != null) {
            prov = prov.configure(arg);
        }
        Security.addProvider(prov);
    }
}
