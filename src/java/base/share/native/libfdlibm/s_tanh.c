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
static const double one=1.0, two=2.0, tiny = 1.0e-300;
#else
static double one=1.0, two=2.0, tiny = 1.0e-300;
#endif

#ifdef __STDC__
        double tanh(double x)
#else
        double tanh(x)
        double x;
#endif
{
        double t,z;
        int jx,ix;

    /* High word of |x|. */
        jx = __HI(x);
        ix = jx&0x7fffffff;

    /* x is INF or NaN */
        if(ix>=0x7ff00000) {
            if (jx>=0) return one/x+one;    /* tanh(+-inf)=+-1 */
            else       return one/x-one;    /* tanh(NaN) = NaN */
        }

    /* |x| < 22 */
        if (ix < 0x40360000) {          /* |x|<22 */
            if (ix<0x3c800000)          /* |x|<2**-55 */
                return x*(one+x);       /* tanh(small) = small */
            if (ix>=0x3ff00000) {       /* |x|>=1  */
                t = expm1(two*fabs(x));
                z = one - two/(t+two);
            } else {
                t = expm1(-two*fabs(x));
                z= -t/(t+two);
            }
    /* |x| > 22, return +-1 */
        } else {
            z = one - tiny;             /* raised inexact flag */
        }
        return (jx>=0)? z: -z;
}
