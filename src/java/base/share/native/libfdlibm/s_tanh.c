/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
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
