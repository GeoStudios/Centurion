/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include "fdlibm.h"
#include <errno.h>

#ifdef __STDC__
        double ldexp(double value, int exp)
#else
        double ldexp(value, exp)
        double value; int exp;
#endif
{
        if(!finite(value)||value==0.0) return value;
        value = scalbn(value,exp);
        if(!finite(value)||value==0.0) errno = ERANGE;
        return value;
}
