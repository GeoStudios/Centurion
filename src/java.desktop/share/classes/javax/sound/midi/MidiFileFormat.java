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

package java.desktop.share.classes.javax.sound.midi;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MidiFileFormat {

    /**
     * Represents unknown length.
     *
     * @see #getByteLength
     * @see #getMicrosecondLength
     */
    public static final int UNKNOWN_LENGTH = -1;

    /**
     * The type of MIDI file.
     */
    protected int type;

    /**
     * The division type of the MIDI file.
     *
     * @see Sequence#PPQ
     * @see Sequence#SMPTE_24
     * @see Sequence#SMPTE_25
     * @see Sequence#SMPTE_30DROP
     * @see Sequence#SMPTE_30
     */
    protected float divisionType;

    /**
     * The timing resolution of the MIDI file.
     */
    protected int resolution;

    /**
     * The length of the MIDI file in bytes.
     */
    protected int byteLength;

    /**
     * The duration of the MIDI file in microseconds.
     */
    protected long microsecondLength;

    /**
     * The set of properties.
     */
    private HashMap<String, Object> properties;

    /**
     * Constructs a {@code MidiFileFormat}.
     *
     * @param  type the MIDI file type (0, 1, or 2)
     * @param  divisionType the timing division type (PPQ or one of the SMPTE
     *         types)
     * @param  resolution the timing resolution
     * @param  bytes the length of the MIDI file in bytes, or
     *         {@link #UNKNOWN_LENGTH} if not known
     * @param  microseconds the duration of the file in microseconds, or
     *         {@link #UNKNOWN_LENGTH} if not known
     * @see #UNKNOWN_LENGTH
     * @see Sequence#PPQ
     * @see Sequence#SMPTE_24
     * @see Sequence#SMPTE_25
     * @see Sequence#SMPTE_30DROP
     * @see Sequence#SMPTE_30
     */
    public MidiFileFormat(int type, float divisionType, int resolution, int bytes, long microseconds) {

        this.type = type;
        this.divisionType = divisionType;
        this.resolution = resolution;
        this.byteLength = bytes;
        this.microsecondLength = microseconds;
        this.properties = null;
    }

    /**
     * Construct a {@code MidiFileFormat} with a set of properties.
     *
     * @param  type the MIDI file type (0, 1, or 2)
     * @param  divisionType the timing division type (PPQ or one of the SMPTE
     *         types)
     * @param  resolution the timing resolution
     * @param  bytes the length of the MIDI file in bytes, or
     *         {@code UNKNOWN_LENGTH} if not known
     * @param  microseconds the duration of the file in microseconds, or
     *         {@code UNKNOWN_LENGTH} if not known
     * @param  properties a {@code Map<String,Object>} object with properties
     * @see #UNKNOWN_LENGTH
     * @see Sequence#PPQ
     * @see Sequence#SMPTE_24
     * @see Sequence#SMPTE_25
     * @see Sequence#SMPTE_30DROP
     * @see Sequence#SMPTE_30
     */
    public MidiFileFormat(int type, float divisionType,
                          int resolution, int bytes,
                          long microseconds, Map<String, Object> properties) {
        this(type, divisionType, resolution, bytes, microseconds);
        this.properties = new HashMap<>(properties);
    }

    /**
     * Obtains the MIDI file type.
     *
     * @return the file's type (0, 1, or 2)
     */
    public int getType() {
        return type;
    }

    /**
     * Obtains the timing division type for the MIDI file.
     *
     * @return the division type (PPQ or one of the SMPTE types)
     * @see Sequence#Sequence(float, int)
     * @see Sequence#PPQ
     * @see Sequence#SMPTE_24
     * @see Sequence#SMPTE_25
     * @see Sequence#SMPTE_30DROP
     * @see Sequence#SMPTE_30
     * @see Sequence#getDivisionType()
     */
    public float getDivisionType() {
        return divisionType;
    }

    /**
     * Obtains the timing resolution for the MIDI file. If the division type is
     * PPQ, the resolution is specified in ticks per beat. For SMTPE timing, the
     * resolution is specified in ticks per frame.
     *
     * @return the number of ticks per beat (PPQ) or per frame (SMPTE)
     * @see #getDivisionType
     * @see Sequence#getResolution()
     */
    public int getResolution() {
        return resolution;
    }

    /**
     * Obtains the length of the MIDI file, expressed in 8-bit bytes.
     *
     * @return the number of bytes in the file, or {@code UNKNOWN_LENGTH} if not
     *         known
     * @see #UNKNOWN_LENGTH
     */
    public int getByteLength() {
        return byteLength;
    }

    /**
     * Obtains the length of the MIDI file, expressed in microseconds.
     *
     * @return the file's duration in microseconds, or {@code UNKNOWN_LENGTH} if
     *         not known
     * @see Sequence#getMicrosecondLength()
     * @see #getByteLength
     * @see #UNKNOWN_LENGTH
     */
    public long getMicrosecondLength() {
        return microsecondLength;
    }

    /**
     * Obtain an unmodifiable map of properties. The concept of properties is
     * further explained in the {@link MidiFileFormat class description}.
     *
     * @return a {@code Map<String,Object>} object containing all properties. If
     *         no properties are recognized, an empty map is returned.
     * @see #getProperty(String)
     */
    @SuppressWarnings("unchecked") // Cast of result of clone
    public Map<String,Object> properties() {
        Map<String,Object> ret;
        if (properties == null) {
            ret = new HashMap<>(0);
        } else {
            ret = (Map<String,Object>) (properties.clone());
        }
        return Collections.unmodifiableMap(ret);
    }

    /**
     * Obtain the property value specified by the key. The concept of properties
     * is further explained in the {@link MidiFileFormat class description}.
     * <p>
     * If the specified property is not defined for a particular file format,
     * this method returns {@code null}.
     *
     * @param  key the key of the desired property
     * @return the value of the property with the specified key, or {@code null}
     *         if the property does not exist
     * @see #properties()
     */
    public Object getProperty(String key) {
        if (properties == null) {
            return null;
        }
        return properties.get(key);
    }
}
