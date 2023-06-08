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

package java.base.share.classes.sun.text;

/**
 * SupplementaryCharacterData is an SMI-private class which was written for
 * RuleBasedBreakIterator and BreakDictionary.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */
public final class SupplementaryCharacterData implements Cloneable {

    /**
     * A token used as a character-category value to identify ignore characters
     */
    private static final byte IGNORE = -1;

    /**
     * An array for supplementary characters and values.
     * Lower one byte is used to keep a byte-value.
     * Upper three bytes are used to keep the first supplementary character
     * which has the value. The value is also valid for the following
     * supplementary characters until the next supplementary character in
     * the array <code>dataTable</code>.
     * For example, if the value of <code>dataTable[2]</code> is
     * <code>0x01000123</code> and the value of <code>dataTable[3]</code> is
     * <code>0x01000567</code>, supplementary characters from
     * <code>0x10001</code> to <code>0x10004</code> has the value
     * <code>0x23</code>. And, <code>getValue(0x10003)</code> returns the value.
     */
    private int[] dataTable;


    /**
     * Creates a new SupplementaryCharacterData object with the given table.
     */
    public SupplementaryCharacterData(int[] table) {
        dataTable = table;
    }

    /**
     * Returns a corresponding value for the given supplementary code-point.
     */
    public int getValue(int index) {
        // Index should be a valid supplementary character.
        assert index >= Character.MIN_SUPPLEMENTARY_CODE_POINT &&
               index <= Character.MAX_CODE_POINT :
               "Invalid code point:" + Integer.toHexString(index);

        int i = 0;
        int j = dataTable.length - 1;
        int k;

        for (;;) {
            k = (i + j) / 2;

            int start = dataTable[k] >> 8;
            int end   = dataTable[k+1] >> 8;

            if (index < start) {
                j = k;
            } else if (index > (end-1)) {
                i = k;
            } else {
                int v = dataTable[k] & 0xFF;
                return (v == 0xFF) ? IGNORE : v;
            }
        }
    }

    /**
     * Returns the data array.
     */
    public int[] getArray() {
        return dataTable;
    }

}
