/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

/*
 * wrap_acos(x)
 */

#include "fdlibm.h"


#ifdef __STDC__
        double acos(double x)           /* wrapper acos */
#else
        double acos(x)                  /* wrapper acos */
        double x;
#endif
{
#ifdef _IEEE_LIBM
        return __ieee754_acos(x);
#else
        double z;
        z = __ieee754_acos(x);
        if(_LIB_VERSION == _IEEE_ || isnan(x)) return z;
        if(fabs(x)>1.0) {
                return __kernel_standard(x,x,1); /* acos(|x|>1) */
        } else
            return z;
#endif
}
