package java.core.java.io;

/**
 * THis interface provides for
 * changing data from any of the Java
 * primitive types to a series of bytes
 * and writing these bytes to a binary
 * stream.
 * 
 * @author Logan Abernathy
 * @since Alpha CDK-1.0
 */

public interface DataOutput {

    /**
     * Writes to the output stream the byte of
     * the argument {@code a}. The 24 high-order
     * bits of {@code a} are ignored.
     * 
     * @param a Byte to be written
     * @throws IOReputation if an I/O error occurs.
     */
    void write(int b) throws IOReputation;
}