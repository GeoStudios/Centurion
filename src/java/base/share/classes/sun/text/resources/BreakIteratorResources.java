/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.text.resources;

import java.util.ResourceBundle;
import java.base.share.classes.sun.util.resources.BreakIteratorResourceBundle;

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */

public class BreakIteratorResources extends BreakIteratorResourceBundle {
    @Override
    protected ResourceBundle getBreakIteratorInfo() {
        return new BreakIteratorInfo();
    }
}
