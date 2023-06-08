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

#include "fdlibm.h"

#ifdef __STDC__
        int finite(double x)
#else
        int finite(x)
        double x;
#endif
{
        int hx;
        hx = __HI(x);
        return  (unsigned)((hx&0x7fffffff)-0x7ff00000)>>31;
}
