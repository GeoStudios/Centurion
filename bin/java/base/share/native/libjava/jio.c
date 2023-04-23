/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
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

