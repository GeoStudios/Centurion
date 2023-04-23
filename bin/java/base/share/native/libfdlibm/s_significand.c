/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include "fdlibm.h"

#ifdef __STDC__
        double significand(double x)
#else
        double significand(x)
        double x;
#endif
{
        return __ieee754_scalb(x,(double) -ilogb(x));
}
