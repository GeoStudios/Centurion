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

/* Written by Simon Montagu, Matitiahu Allouche
 * (ported from C code written by Markus W. Scherer)
 */

package java.base.share.classes.jdk.internal.icu.text;

/**
 * A BidiRun represents a sequence of characters at the same embedding level.
 * The Bidi algorithm decomposes a piece of text into sequences of characters
 * at the same embedding level, each such sequence is called a "run".
 *
 * <p>A BidiRun represents such a run by storing its essential properties,
 * but does not duplicate the characters which form the run.
 *
 * <p>The &quot;limit&quot; of the run is the position just after the
 * last character, i.e., one more than that position.
 *
 * <p>This class has no public constructor, and its members cannot be
 * modified by users.
 *
 * @see com.ibm.icu.text.Bidi
 */
class BidiRun {

    int start;              /* first logical position of the run */
    int limit;              /* last visual position of the run +1 */
    int insertRemove;       /* if >0, flags for inserting LRM/RLM before/after run,
                               if <0, count of bidi controls within run            */
    byte level;

    /*
     * Default constructor
     *
     * Note that members start and limit of a run instance have different
     * meanings depending whether the run is part of the runs array of a Bidi
     * object, or if it is a reference returned by getVisualRun() or
     * getLogicalRun().
     * For a member of the runs array of a Bidi object,
     *   - start is the first logical position of the run in the source text.
     *   - limit is one after the last visual position of the run.
     * For a reference returned by getLogicalRun() or getVisualRun(),
     *   - start is the first logical position of the run in the source text.
     *   - limit is one after the last logical position of the run.
     */
    BidiRun()
    {
        this(0, 0, (byte)0);
    }

    /*
     * Constructor
     */
    BidiRun(int start, int limit, byte embeddingLevel)
    {
        this.start = start;
        this.limit = limit;
        this.level = embeddingLevel;
    }

    /*
     * Copy the content of a BidiRun instance
     */
    void copyFrom(BidiRun run)
    {
        this.start = run.start;
        this.limit = run.limit;
        this.level = run.level;
        this.insertRemove = run.insertRemove;
    }

    /**
     * Get level of run
     */
    byte getEmbeddingLevel()
    {
        return level;
    }

    /**
     * Check if run level is even
     * @return true if the embedding level of this run is even, i.e. it is a
     *  left-to-right run.
     */
    boolean isEvenRun()
    {
        return (level & 1) == 0;
    }

}
