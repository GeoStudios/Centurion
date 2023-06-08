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

package java.base.share.classes.sun.text.resources;

import java.util.ListResourceBundle;

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */

public class BreakIteratorInfo extends ListResourceBundle {
    protected final Object[][] getContents() {
        return new Object[][] {
            // BreakIteratorClasses lists the class names to instantiate for each
            // built-in type of BreakIterator
            {"BreakIteratorClasses",
                new String[] {
                    "RuleBasedBreakIterator",  // word-break iterator class
                    "RuleBasedBreakIterator",  // line-break iterator class
                    "RuleBasedBreakIterator"   // sentence-break iterator class
                }
            },

            // Rules filename for each break-iterator
            {"WordData",      "WordBreakIteratorData"},
            {"LineData",      "LineBreakIteratorData"},
            {"SentenceData",  "SentenceBreakIteratorData"},
        };
    }
}
