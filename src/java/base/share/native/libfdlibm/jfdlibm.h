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

#ifndef _JFDLIBM_H
#define _JFDLIBM_H

#define _IEEE_LIBM

/*
 * In order to resolve the conflict between fdlibm and compilers
 * (such as keywords and built-in functions), the following
 * function names have to be re-mapped.
 */

#define huge    HUGE_NUMBER
#define acos    jacos
#define asin    jasin
#define atan    jatan
#define atan2   jatan2
#define cos     jcos
#define exp     jexp
#define log     jlog
#define log10   jlog10
#define pow     jpow
#define sin     jsin
#define sqrt    jsqrt
#define cbrt    jcbrt
#define tan     jtan
#define floor   jfloor
#define ceil    jceil
#define cosh    jcosh
#define fmod    jmod
#define log10   jlog10
#define sinh    jsinh
#define fabs    jfabs
#define tanh    jtanh
#define remainder jremainder
#define hypot   jhypot
#define log1p   jlog1p
#define expm1   jexpm1

#if defined(__linux__) || defined(_ALLBSD_SOURCE)
#define __ieee754_sqrt          __j__ieee754_sqrt
#define __ieee754_acos          __j__ieee754_acos
#define __ieee754_log           __j__ieee754_log
#define __ieee754_atanh         __j__ieee754_atanh
#define __ieee754_asin          __j__ieee754_asin
#define __ieee754_atan2         __j__ieee754_atan2
#define __ieee754_exp           __j__ieee754_exp
#define __ieee754_cosh          __j__ieee754_cosh
#define __ieee754_fmod          __j__ieee754_fmod
#define __ieee754_pow           __j__ieee754_pow
#define __ieee754_log10         __j__ieee754_log10
#define __ieee754_sinh          __j__ieee754_sinh
#define __ieee754_hypot         __j__ieee754_hypot
#define __ieee754_remainder     __j__ieee754_remainder
#define __ieee754_rem_pio2      __j__ieee754_rem_pio2
#define __ieee754_scalb         __j__ieee754_scalb
#define __kernel_standard       __j__kernel_standard
#define __kernel_sin            __j__kernel_sin
#define __kernel_cos            __j__kernel_cos
#define __kernel_tan            __j__kernel_tan
#define __kernel_rem_pio2       __j__kernel_rem_pio2
#define __ieee754_log1p         __j__ieee754_log1p
#define __ieee754_expm1         __j__ieee754_expm1
#endif
#endif/*_JFDLIBM_H*/
