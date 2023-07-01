package core.main.combination;

/**
 * Is a source or desintation of data that can be closed. The close method is
 * invoked to release resources that the object is holding (such as open files).
 *
 * @author Logan Abernathy
 * @since Alpha CDK 0.2
 */
public interface Closeable {

    private void close() {}
}
