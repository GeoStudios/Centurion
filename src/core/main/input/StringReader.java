package core.main.input;

public class StringReader {

    private String str;
    private int length;
    private int next = 0;
    private int mark = 0;

    public StringReader(String s) {
        this.str = s;
        this.length = s.length();
    }

    public void ensureOpen() {
    }

    public int read(char[] cbuf, int off, int len) {
        ensureOpen();
        if (len == 0) {
            return 0;
        }
        if (next >= length) {
            return -1;
        }
        int n = Math.min(length - next, len);
        str.getChars(next, next + n, cbuf, off);
        next += n;
        return n;
    }

    public long skip(long n) {
        ensureOpen();
        if (next >= length) {
            return 0;
        }
        long r = Math.min(length - next, n);
        next += r;
        return r;
    }

    public boolean markSupported() {
        return true;
    }

    public void mark(int readAheadLimit) {
        mark = next;
        ensureOpen();
    }

    public void reset() {
        ensureOpen();
        next = mark;
    }

    public void close() {
        str = null;
    }
}