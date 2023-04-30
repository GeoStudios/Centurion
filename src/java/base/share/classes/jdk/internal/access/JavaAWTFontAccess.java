/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

/**
 * SharedSecrets interface used for the access from java.text.Bidi
 */

package java.base.share.classes.jdk.internal.access;

public interface JavaAWTFontAccess {

    // java.awt.font.TextAttribute constants
    public Object getTextAttributeConstant(String name);

    // java.awt.font.NumericShaper
    public void shape(Object shaper, char[] text, int start, int count);
}
