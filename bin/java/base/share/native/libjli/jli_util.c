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

#include <stdio.h>
#include <string.h>
#include <stdarg.h>
#include "jni.h"
#include "jli_util.h"

/*
 * Returns a pointer to a block of at least 'size' bytes of memory.
 * Prints error message and exits if the memory could not be allocated.
 */
JNIEXPORT void * JNICALL
JLI_MemAlloc(size_t size)
{
    void *p = malloc(size);
    if (p == 0) {
        perror("malloc");
        exit(1);
    }
    return p;
}

/*
 * Equivalent to realloc(size).
 * Prints error message and exits if the memory could not be reallocated.
 */
void *
JLI_MemRealloc(void *ptr, size_t size)
{
    void *p = realloc(ptr, size);
    if (p == 0) {
        perror("realloc");
        exit(1);
    }
    return p;
}

/*
 * Wrapper over strdup(3C) which prints an error message and exits if memory
 * could not be allocated.
 */
JNIEXPORT char * JNICALL
JLI_StringDup(const char *s1)
{
    char *s = strdup(s1);
    if (s == NULL) {
        perror("strdup");
        exit(1);
    }
    return s;
}

/*
 * Very equivalent to free(ptr).
 * Here to maintain pairing with the above routines.
 */
JNIEXPORT void JNICALL
JLI_MemFree(void *ptr)
{
    free(ptr);
}

jboolean
JLI_HasSuffix(const char *s1, const char *s2)
{
    char *p = JLI_StrRChr(s1, '.');
    if (p == NULL || *p == '\0') {
        return JNI_FALSE;
    }
    return (JLI_StrCaseCmp(p, s2) == 0);
}

/*
 * debug helpers we use
 */
static jboolean _launcher_debug = JNI_FALSE;

void
JLI_TraceLauncher(const char* fmt, ...)
{
    va_list vl;
    if (_launcher_debug != JNI_TRUE) return;
    va_start(vl, fmt);
    vprintf(fmt,vl);
    va_end(vl);
    fflush(stdout);
}

JNIEXPORT void JNICALL
JLI_SetTraceLauncher()
{
   if (getenv(JLDEBUG_ENV_ENTRY) != 0) {
        _launcher_debug = JNI_TRUE;
        JLI_TraceLauncher("----%s----\n", JLDEBUG_ENV_ENTRY);
   }
}

jboolean
JLI_IsTraceLauncher()
{
   return _launcher_debug;
}

int
JLI_StrCCmp(const char *s1, const char* s2)
{
   return JLI_StrNCmp(s1, s2, JLI_StrLen(s2));
}

JNIEXPORT JLI_List JNICALL
JLI_List_new(size_t capacity)
{
    JLI_List l = (JLI_List) JLI_MemAlloc(sizeof(struct JLI_List_));
    l->capacity = capacity;
    l->elements = (char **) JLI_MemAlloc(capacity * sizeof(l->elements[0]));
    l->size = 0;
    return l;
}

void
JLI_List_free(JLI_List sl)
{
    if (sl) {
        if (sl->elements) {
            size_t i;
            for (i = 0; i < sl->size; i++)
                JLI_MemFree(sl->elements[i]);
            JLI_MemFree(sl->elements);
        }
        JLI_MemFree(sl);
    }
}

void
JLI_List_ensureCapacity(JLI_List sl, size_t capacity)
{
    if (sl->capacity < capacity) {
        while (sl->capacity < capacity)
            sl->capacity *= 2;
        sl->elements = JLI_MemRealloc(sl->elements,
            sl->capacity * sizeof(sl->elements[0]));
    }
}

JNIEXPORT void JNICALL
JLI_List_add(JLI_List sl, char *str)
{
    JLI_List_ensureCapacity(sl, sl->size+1);
    sl->elements[sl->size++] = str;
}

void
JLI_List_addSubstring(JLI_List sl, const char *beg, size_t len)
{
    char *str = (char *) JLI_MemAlloc(len+1);
    memcpy(str, beg, len);
    str[len] = '\0';
    JLI_List_ensureCapacity(sl, sl->size+1);
    sl->elements[sl->size++] = str;
}

char *
JLI_List_combine(JLI_List sl)
{
    size_t i;
    size_t size;
    char *str;
    char *p;
    for (i = 0, size = 1; i < sl->size; i++)
        size += JLI_StrLen(sl->elements[i]);

    str = JLI_MemAlloc(size);

    for (i = 0, p = str; i < sl->size; i++) {
        size_t len = JLI_StrLen(sl->elements[i]);
        memcpy(p, sl->elements[i], len);
        p += len;
    }
    *p = '\0';

    return str;
}

char *
JLI_List_join(JLI_List sl, char sep)
{
    size_t i;
    size_t size;
    char *str;
    char *p;
    for (i = 0, size = 1; i < sl->size; i++)
        size += JLI_StrLen(sl->elements[i]) + 1;

    str = JLI_MemAlloc(size);

    for (i = 0, p = str; i < sl->size; i++) {
        size_t len = JLI_StrLen(sl->elements[i]);
        if (i > 0) *p++ = sep;
        memcpy(p, sl->elements[i], len);
        p += len;
    }
    *p = '\0';

    return str;
}

JLI_List
JLI_List_split(const char *str, char sep)
{
    const char *p, *q;
    size_t len = JLI_StrLen(str);
    int count;
    JLI_List sl;
    for (count = 1, p = str; p < str + len; p++)
        count += (*p == sep);
    sl = JLI_List_new(count);
    for (p = str;;) {
        for (q = p; q <= str + len; q++) {
            if (*q == sep || *q == '\0') {
                JLI_List_addSubstring(sl, p, q - p);
                if (*q == '\0')
                    return sl;
                p = q + 1;
            }
        }
    }
}
