/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.text.resources;

import sun.util.resources.ParallelListResourceBundle;

/**
 * @since Java 2
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */

public class FormatData_en_US extends ParallelListResourceBundle {
    /**
     * Overrides ParallelListResourceBundle
     */
    protected final Object[][] getContents() {
        return new Object[][] {
            { "NumberPatterns",
                new String[] {
                    "#,##0.###;-#,##0.###", // decimal pattern
                    "\u00a4#,##0.00;(\u00a4#,##0.00)", // currency pattern
                    "#,##0%" // percent pattern
                }
            },
        };
    }
}
