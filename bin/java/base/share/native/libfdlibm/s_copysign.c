/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include "fdlibm.h"

#ifdef __STDC__
        double copysign(double x, double y)
#else
        double copysign(x,y)
        double x,y;
#endif
{
        __HI(x) = (__HI(x)&0x7fffffff)|(__HI(y)&0x80000000);
        return x;
}
