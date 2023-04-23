/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package sun.net.www.protocol.jar;

import java.io.*;
import java.net.*;
import java.util.jar.*;


/*
 * This interface is used to call back to sun.plugin package.
 */
public interface URLJarFileCallBack
{
        public JarFile retrieve (URL url) throws IOException;
}
