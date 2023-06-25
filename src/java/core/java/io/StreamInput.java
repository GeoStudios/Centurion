package java.core.java.io;

public abstract class StreamInput {

    // MAX_SKIP_BUFFER_SIZE is used to determine the maximum buffer size to
    // use when skipping.
    private static final int MAX_SKIP_BUFFER_SIZE = 2048;

    private static final int DEFAULT_BUFFER_SIZE = 8192;

    /**
     * Construnctor for subclasses to call.
     */
    public StreamInput() {}

    public static StreamInput nullImputStream() {
        return new StreamInput() {
            private volatile boolean closed;
        };
    }
}