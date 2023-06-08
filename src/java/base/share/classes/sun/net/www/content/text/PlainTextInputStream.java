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

package java.base.share.classes.sun.net.www.content.text;
import java.io.InputStream;
import java.io.FilterInputStream;

/**
 * PlainTextInputStream class extends the FilterInputStream class.
 * Currently all calls to the PlainTextInputStream object will call
 * the corresponding methods in the FilterInputStream class.  Hence
 * for now its use is more semantic.
 *
 * @author Sunita Mani
 */
public class PlainTextInputStream extends FilterInputStream {

    /**
     * Calls FilterInputStream's constructor.
     * @param is an InputStream
     */
    PlainTextInputStream(InputStream is) {
        super(is);
    }
}
