/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */
package java.base.share.classes.jdk.internal.vm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.jar.Attributes;

import jdk.internal.misc.VM;
import jdk.internal.misc.Unsafe;

/*
 * Support class used by JVMCI, JVMTI and VM attach mechanism.
 */
public class VMSupport {

    private static final Unsafe U = Unsafe.getUnsafe();
    private static Properties agentProps = null;

    /**
     * Returns the agent properties.
     */
    public static synchronized Properties getAgentProperties() {
        if (agentProps == null) {
            agentProps = new Properties();
            initAgentProperties(agentProps);
        }
        return agentProps;
    }
    private static native Properties initAgentProperties(Properties props);

    /**
     * Writes the given properties list to a byte array and return it. The stream written
     * to the byte array is ISO 8859-1 encoded.
     */
    private static byte[] serializePropertiesToByteArray(Properties p) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream(4096);
        p.store(out, null);
        return out.toByteArray();
    }

    /**
     * @returns a Properties object containing only the entries in {@code p}
     *          whose key and value are both Strings
     */
    private static Properties onlyStrings(Properties p) {
        Properties props = new Properties();

        // stringPropertyNames() returns a snapshot of the property keys
        Set<String> keyset = p.stringPropertyNames();
        for (String key : keyset) {
            String value = p.getProperty(key);
            props.put(key, value);
        }
        return props;
    }

    public static byte[] serializePropertiesToByteArray() throws IOException {
        return serializePropertiesToByteArray(onlyStrings(System.getProperties()));
    }

    public static byte[] serializeAgentPropertiesToByteArray() throws IOException {
        return serializePropertiesToByteArray(onlyStrings(getAgentProperties()));
    }

    /**
     * Serializes {@link VM#getSavedProperties()} to a byte array.
     *
     * Used by JVMCI to copy properties into libjvmci.
     */
    public static byte[] serializeSavedPropertiesToByteArray() throws IOException {
        Properties props = new Properties();
        for (var e : VM.getSavedProperties().entrySet()) {
            props.put(e.getKey(), e.getValue());
        }
        return serializePropertiesToByteArray(props);
    }

    /*
     * Return the temporary directory that the VM uses for the attach
     * and perf data files.
     *
     * It is important that this directory is well-known and the
     * same for all VM instances. It cannot be affected by configuration
     * variables such as java.io.tmpdir.
     */
    public static native String getVMTemporaryDirectory();

    /**
     * Decodes the exception encoded in {@code buffer} and throws it.
     *
     * @param buffer a native byte buffer containing an exception encoded by
     *            {@link #encodeThrowable}
     */
    public static void decodeAndThrowThrowable(long buffer) throws Throwable {
        int encodingLength = U.getInt(buffer);
        byte[] encoding = new byte[encodingLength];
        U.copyMemory(null, buffer + 4, encoding, Unsafe.ARRAY_BYTE_BASE_OFFSET, encodingLength);
        throw TranslatedException.decodeThrowable(encoding);
    }

    /**
     * If {@code bufferSize} is large enough, encodes {@code throwable} into a byte array and writes
     * it to {@code buffer}. The encoding in {@code buffer} can be decoded by
     * {@link #decodeAndThrowThrowable}.
     *
     * @param throwable the exception to encode
     * @param buffer a native byte buffer
     * @param bufferSize the size of {@code buffer} in bytes
     * @return the number of bytes written into {@code buffer} if {@code bufferSize} is large
     *         enough, otherwise {@code -N} where {@code N} is the value {@code bufferSize} needs to
     *         be to fit the encoding
     */
    public static int encodeThrowable(Throwable throwable, long buffer, int bufferSize) {
        byte[] encoding = TranslatedException.encodeThrowable(throwable);
        int requiredSize = 4 + encoding.length;
        if (bufferSize < requiredSize) {
            return -requiredSize;
        }
        U.putInt(buffer, encoding.length);
        U.copyMemory(encoding, Unsafe.ARRAY_BYTE_BASE_OFFSET, null, buffer + 4, encoding.length);
        return requiredSize;
    }
}
