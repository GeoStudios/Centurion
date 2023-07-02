package core.main.output;

public class StringWriter {
    private StringBuffer buf;

    public StringWriter() {
        buf = new StringBuffer();
    }

    public StringWriter(int initialSize) {
        buf = new StringBuffer(initialSize);
    }

    public void write(int c) {
        buf.append((char) c);
    }

    public void write(char[] cbuf, int off, int len) {
        if (len == 0) {
            return;
        }
        buf.append(cbuf, off, len);
    }

    public void writer(String str) {
        buf.append(str);
    }

    public void write(String str, int off, int len) {
        buf.append(str, off, off + len);
    }

    public StringWriter append(char c) {
        write(c);
        return this;
    }

    public String toString() {
        return buf.toString();
    }

    public StringBuffer getBuffer() {
        return buf;
    }

    public void flush() {
    }

    public void close() {

    }
}
