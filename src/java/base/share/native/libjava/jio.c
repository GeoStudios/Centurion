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

#include <stdlib.h>

#include "jni.h"

#ifndef STATIC_BUILD

/* This is a temporary solution until we figure out how to let native
 * libraries use jio_* without linking with the VM.
 */

extern int
jio_vsnprintf(char *str, size_t count, const char *fmt, va_list args);

JNIEXPORT int
jio_snprintf(char *str, size_t count, const char *fmt, ...)
{
    int len;

    va_list args;
    va_start(args, fmt);
    len = jio_vsnprintf(str, count, fmt, args);
    va_end(args);

    return len;
}

extern int
jio_vfprintf(FILE *, const char *fmt, va_list args);

JNIEXPORT int
jio_fprintf(FILE *fp, const char *fmt, ...)
{
    int len;

    va_list args;
    va_start(args, fmt);
    len = jio_vfprintf(fp, fmt, args);
    va_end(args);

    return len;
}

#endif

