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

package javax.sound.midi;

public abstract class MidiMessage implements Cloneable {

    /**
     * The MIDI message data. The first byte is the status byte for the message;
     * subsequent bytes up to the length of the message are data bytes for this
     * message.
     *
     * @see #getLength
     */
    protected byte[] data;

    /**
     * The number of bytes in the MIDI message, including the status byte and
     * any data bytes.
     *
     * @see #getLength
     */
    protected int length = 0;

    /**
     * Constructs a new {@code MidiMessage}. This protected constructor is
     * called by concrete subclasses, which should ensure that the data array
     * specifies a complete, valid MIDI message.
     *
     * @param  data an array of bytes containing the complete message. The
     *         message data may be changed using the {@code setMessage} method.
     * @see #setMessage
     */
    protected MidiMessage(byte[] data) {
        this.data = data;
        if (data != null) {
            this.length = data.length;
        }
    }

    /**
     * Sets the data for the MIDI message. This protected method is called by
     * concrete subclasses, which should ensure that the data array specifies a
     * complete, valid MIDI message.
     *
     * @param  data the data bytes in the MIDI message
     * @param  length the number of bytes in the data byte array
     * @throws InvalidMidiDataException if the parameter values do not specify a
     *         valid MIDI meta message
     */
    protected void setMessage(byte[] data, int length)
            throws InvalidMidiDataException {
        if (length < 0 || (length > 0 && length > data.length)) {
            throw new IndexOutOfBoundsException(
                    "length out of bounds: " + length);
        }
        this.length = length;

        if (this.data == null || this.data.length < this.length) {
            this.data = new byte[this.length];
        }
        System.arraycopy(data, 0, this.data, 0, length);
    }

    /**
     * Obtains the MIDI message data. The first byte of the returned byte array
     * is the status byte of the message. Any subsequent bytes up to the length
     * of the message are data bytes. The byte array may have a length which is
     * greater than that of the actual message; the total length of the message
     * in bytes is reported by the {@link #getLength} method.
     *
     * @return the byte array containing the complete {@code MidiMessage} data
     */
    public byte[] getMessage() {
        byte[] returnedArray = new byte[length];
        System.arraycopy(data, 0, returnedArray, 0, length);
        return returnedArray;
    }

    /**
     * Obtains the status byte for the MIDI message. The status "byte" is
     * represented as an integer; see the
     * <a href="#integersVsBytes">discussion</a> in the {@code MidiMessage}
     * class description.
     *
     * @return the integer representation of this event's status byte
     */
    public int getStatus() {
        if (length > 0) {
            return (data[0] & 0xFF);
        }
        return 0;
    }

    /**
     * Obtains the total length of the MIDI message in bytes. A MIDI message
     * consists of one status byte and zero or more data bytes. The return value
     * ranges from 1 for system real-time messages, to 2 or 3 for channel
     * messages, to any value for meta and system exclusive messages.
     *
     * @return the length of the message in bytes
     */
    public int getLength() {
        return length;
    }

    /**
     * Creates a new object of the same class and with the same contents as this
     * object.
     *
     * @return a clone of this instance
     */
    @Override
    public abstract Object clone();
}
