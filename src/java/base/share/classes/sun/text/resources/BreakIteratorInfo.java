/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.text.resources;

import java.util.ListResourceBundle;

/**
 * @since Pre Java 1
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
