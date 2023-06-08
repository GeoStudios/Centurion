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

package java.base.unix.classes.java.lang;

import java.util.*;

import static java.base.unix.classes.java.lang.ProcessImpl.JNU_CHARSET;

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */
final class ProcessEnvironment
{
    private static final HashMap<Variable,Value> theEnvironment;
    private static final Map<String,String> theUnmodifiableEnvironment;
    static final int MIN_NAME_LENGTH = 0;

    static {
        // We cache the C environment.  This means that subsequent calls
        // to putenv/setenv from C will not be visible from Java code.
        byte[][] environ = environ();
        theEnvironment = new HashMap<>(environ.length/2 + 3);
        // Read environment variables back to front,
        // so that earlier variables override later ones.
        for (int i = environ.length-1; i > 0; i-=2)
            theEnvironment.put(Variable.valueOf(environ[i-1]),
                               Value.valueOf(environ[i]));

        theUnmodifiableEnvironment
            = Collections.unmodifiableMap
            (new StringEnvironment(theEnvironment));
    }

    /* Only for use by System.getenv(String) */
    static String getenv(String name) {
        return theUnmodifiableEnvironment.get(name);
    }

    /* Only for use by System.getenv() */
    static Map<String,String> getenv() {
        return theUnmodifiableEnvironment;
    }

    /* Only for use by ProcessBuilder.environment() */
    @SuppressWarnings("unchecked")
    static Map<String,String> environment() {
        return new StringEnvironment
            ((Map<Variable,Value>)(theEnvironment.clone()));
    }

    /* Only for use by Runtime.exec(...String[]envp...) */
    static Map<String,String> emptyEnvironment(int capacity) {
        return new StringEnvironment(new HashMap<>(capacity));
    }

    private static native byte[][] environ();

    // This class is not instantiable.
    private ProcessEnvironment() {}

    // Check that name is suitable for insertion into Environment map
    private static void validateVariable(String name) {
        if (name.indexOf('=')      != -1 ||
            name.indexOf('\u0000') != -1)
            throw new IllegalArgumentException
                ("Invalid environment variable name: \"" + name + "\"");
    }

    // Check that value is suitable for insertion into Environment map
    private static void validateValue(String value) {
        if (value.indexOf('\u0000') != -1)
            throw new IllegalArgumentException
                ("Invalid environment variable value: \"" + value + "\"");
    }

    // A class hiding the byteArray-String duality of
    // text data on Unixoid operating systems.
    private abstract static class ExternalData {
        protected final String str;
        protected final byte[] bytes;

        protected ExternalData(String str, byte[] bytes) {
            this.str = str;
            this.bytes = bytes;
        }

        public byte[] getBytes() {
            return bytes;
        }

        public String toString() {
            return str;
        }

        public boolean equals(Object o) {
            return o instanceof ExternalData
                && Arrays.equals(getBytes(), ((ExternalData) o).getBytes());
        }

        public int hashCode() {
            return Arrays.hashCode(getBytes());
        }
    }

    private static class Variable
        extends ExternalData implements Comparable<Variable>
    {
        protected Variable(String str, byte[] bytes) {
            super(str, bytes);
        }

        public static Variable valueOfQueryOnly(Object str) {
            return valueOfQueryOnly((String) str);
        }

        public static Variable valueOfQueryOnly(String str) {
            return new Variable(str, str.getBytes(JNU_CHARSET));
        }

        public static Variable valueOf(String str) {
            validateVariable(str);
            return valueOfQueryOnly(str);
        }

        public static Variable valueOf(byte[] bytes) {
            return new Variable(new String(bytes, JNU_CHARSET), bytes);
        }

        public int compareTo(Variable variable) {
            return Arrays.compare(getBytes(), variable.getBytes());
        }

        public boolean equals(Object o) {
            return o instanceof Variable && super.equals(o);
        }
    }

    private static class Value
        extends ExternalData implements Comparable<Value>
    {
        protected Value(String str, byte[] bytes) {
            super(str, bytes);
        }

        public static Value valueOfQueryOnly(Object str) {
            return valueOfQueryOnly((String) str);
        }

        public static Value valueOfQueryOnly(String str) {
            return new Value(str, str.getBytes(JNU_CHARSET));
        }

        public static Value valueOf(String str) {
            validateValue(str);
            return valueOfQueryOnly(str);
        }

        public static Value valueOf(byte[] bytes) {
            return new Value(new String(bytes, JNU_CHARSET), bytes);
        }

        public int compareTo(Value value) {
            return Arrays.compare(getBytes(), value.getBytes());
        }

        public boolean equals(Object o) {
            return o instanceof Value && super.equals(o);
        }
    }

    // This implements the String map view the user sees.
    private static class StringEnvironment
        extends AbstractMap<String,String>
    {
        private Map<Variable,Value> m;
        private static String toString(Value v) {
            return v == null ? null : v.toString();
        }
        public StringEnvironment(Map<Variable,Value> m) {this.m = m;}
        public int size()        {return m.size();}
        public boolean isEmpty() {return m.isEmpty();}
        public void clear()      {       m.clear();}
        public boolean containsKey(Object key) {
            return m.containsKey(Variable.valueOfQueryOnly(key));
        }
        public boolean containsValue(Object value) {
            return m.containsValue(Value.valueOfQueryOnly(value));
        }
        public String get(Object key) {
            return toString(m.get(Variable.valueOfQueryOnly(key)));
        }
        public String put(String key, String value) {
            return toString(m.put(Variable.valueOf(key),
                                  Value.valueOf(value)));
        }
        public String remove(Object key) {
            return toString(m.remove(Variable.valueOfQueryOnly(key)));
        }
        public Set<String> keySet() {
            return new StringKeySet(m.keySet());
        }
        public Set<Map.Entry<String,String>> entrySet() {
            return new StringEntrySet(m.entrySet());
        }
        public Collection<String> values() {
            return new StringValues(m.values());
        }

        // It is technically feasible to provide a byte-oriented view
        // as follows:
        //      public Map<byte[],byte[]> asByteArrayMap() {
        //          return new ByteArrayEnvironment(m);
        //      }


        // Convert to Unix style environ as a monolithic byte array
        // inspired by the Windows Environment Block, except we work
        // exclusively with bytes instead of chars, and we need only
        // one trailing NUL on Unix.
        // This keeps the JNI as simple and efficient as possible.
        public byte[] toEnvironmentBlock(int[]envc) {
            int count = m.size() * 2; // For added '=' and NUL
            for (Map.Entry<Variable,Value> entry : m.entrySet()) {
                count += entry.getKey().getBytes().length;
                count += entry.getValue().getBytes().length;
            }

            byte[] block = new byte[count];

            int i = 0;
            for (Map.Entry<Variable,Value> entry : m.entrySet()) {
                byte[] key   = entry.getKey  ().getBytes();
                byte[] value = entry.getValue().getBytes();
                System.arraycopy(key, 0, block, i, key.length);
                i+=key.length;
                block[i++] = (byte) '=';
                System.arraycopy(value, 0, block, i, value.length);
                i+=value.length + 1;
                // No need to write NUL byte explicitly
                //block[i++] = (byte) '\u0000';
            }
            envc[0] = m.size();
            return block;
        }
    }

    static byte[] toEnvironmentBlock(Map<String,String> map, int[]envc) {
        return map == null ? null :
            ((StringEnvironment)map).toEnvironmentBlock(envc);
    }


    private static class StringEntry
        implements Map.Entry<String,String>
    {
        private final Map.Entry<Variable,Value> e;
        public StringEntry(Map.Entry<Variable,Value> e) {this.e = e;}
        public String getKey()   {return e.getKey().toString();}
        public String getValue() {return e.getValue().toString();}
        public String setValue(String newValue) {
            return e.setValue(Value.valueOf(newValue)).toString();
        }
        public String toString() {return getKey() + "=" + getValue();}
        public boolean equals(Object o) {
            return o instanceof StringEntry
                && e.equals(((StringEntry)o).e);
        }
        public int hashCode()    {return e.hashCode();}
    }

    private static class StringEntrySet
        extends AbstractSet<Map.Entry<String,String>>
    {
        private final Set<Map.Entry<Variable,Value>> s;
        public StringEntrySet(Set<Map.Entry<Variable,Value>> s) {this.s = s;}
        public int size()        {return s.size();}
        public boolean isEmpty() {return s.isEmpty();}
        public void clear()      {       s.clear();}
        public Iterator<Map.Entry<String,String>> iterator() {
            return new Iterator<Map.Entry<String,String>>() {
                Iterator<Map.Entry<Variable,Value>> i = s.iterator();
                public boolean hasNext() {return i.hasNext();}
                public Map.Entry<String,String> next() {
                    return new StringEntry(i.next());
                }
                public void remove() {i.remove();}
            };
        }
        private static Map.Entry<Variable,Value> vvEntry(final Object o) {
            if (o instanceof StringEntry)
                return ((StringEntry)o).e;
            return new Map.Entry<Variable,Value>() {
                public Variable getKey() {
                    return Variable.valueOfQueryOnly(((Map.Entry)o).getKey());
                }
                public Value getValue() {
                    return Value.valueOfQueryOnly(((Map.Entry)o).getValue());
                }
                public Value setValue(Value value) {
                    throw new UnsupportedOperationException();
                }
            };
        }
        public boolean contains(Object o) { return s.contains(vvEntry(o)); }
        public boolean remove(Object o)   { return s.remove(vvEntry(o)); }
        public boolean equals(Object o) {
            return o instanceof StringEntrySet
                && s.equals(((StringEntrySet) o).s);
        }
        public int hashCode() {return s.hashCode();}
    }

    private static class StringValues
          extends AbstractCollection<String>
    {
        private final Collection<Value> c;
        public StringValues(Collection<Value> c) {this.c = c;}
        public int size()        {return c.size();}
        public boolean isEmpty() {return c.isEmpty();}
        public void clear()      {       c.clear();}
        public Iterator<String> iterator() {
            return new Iterator<String>() {
                Iterator<Value> i = c.iterator();
                public boolean hasNext() {return i.hasNext();}
                public String next()     {return i.next().toString();}
                public void remove()     {i.remove();}
            };
        }
        public boolean contains(Object o) {
            return c.contains(Value.valueOfQueryOnly(o));
        }
        public boolean remove(Object o) {
            return c.remove(Value.valueOfQueryOnly(o));
        }
        public boolean equals(Object o) {
            return o instanceof StringValues
                && c.equals(((StringValues)o).c);
        }
        public int hashCode() {return c.hashCode();}
    }

    private static class StringKeySet extends AbstractSet<String> {
        private final Set<Variable> s;
        public StringKeySet(Set<Variable> s) {this.s = s;}
        public int size()        {return s.size();}
        public boolean isEmpty() {return s.isEmpty();}
        public void clear()      {       s.clear();}
        public Iterator<String> iterator() {
            return new Iterator<String>() {
                Iterator<Variable> i = s.iterator();
                public boolean hasNext() {return i.hasNext();}
                public String next()     {return i.next().toString();}
                public void remove()     {       i.remove();}
            };
        }
        public boolean contains(Object o) {
            return s.contains(Variable.valueOfQueryOnly(o));
        }
        public boolean remove(Object o) {
            return s.remove(Variable.valueOfQueryOnly(o));
        }
    }

}
