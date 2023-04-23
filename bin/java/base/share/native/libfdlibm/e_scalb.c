/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include "fdlibm.h"

#ifdef _SCALB_INT
#ifdef __STDC__
        double __ieee754_scalb(double x, int fn)
#else
        double __ieee754_scalb(x,fn)
        double x; int fn;
#endif
#else
#ifdef __STDC__
        double __ieee754_scalb(double x, double fn)
#else
        double __ieee754_scalb(x,fn)
        double x, fn;
#endif
#endif
{
#ifdef _SCALB_INT
        return scalbn(x,fn);
#else
        if (isnan(x)||isnan(fn)) return x*fn;
        if (!finite(fn)) {
            if(fn>0.0) return x*fn;
            else       return x/(-fn);
        }
        if (rint(fn)!=fn) return (fn-fn)/(fn-fn);
        if ( fn > 65000.0) return scalbn(x, 65000);
        if (-fn > 65000.0) return scalbn(x,-65000);
        return scalbn(x,(int)fn);
#endif
}
