/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include "fdlibm.h"

#ifdef __STDC__
        int matherr(struct exception *x)
#else
        int matherr(x)
        struct exception *x;
#endif
{
        int n=0;
        if(x->arg1!=x->arg1) return 0;
        return n;
}
