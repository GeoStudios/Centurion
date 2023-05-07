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
#include "splashscreen.h"
#include "jni.h"
extern void* SplashProcAddress(const char* name); /* in java_md.c */

/*
 * Prototypes of pointers to functions in splashscreen shared lib
 */
typedef int (*SplashLoadMemory_t)(void* pdata, int size);
typedef int (*SplashLoadFile_t)(const char* filename);
typedef int (*SplashInit_t)(void);
typedef void (*SplashClose_t)(void);
typedef void (*SplashSetFileJarName_t)(const char* fileName,
                                       const char* jarName);
typedef void (*SplashSetScaleFactor_t)(float scaleFactor);
typedef jboolean (*SplashGetScaledImageName_t)(const char* fileName,
                        const char* jarName, float* scaleFactor,
                        char *scaleImageName, const size_t scaleImageNameLength);
typedef int (*SplashGetScaledImgNameMaxPstfixLen_t)(const char* filename);

/*
 * This macro invokes a function from the shared lib.
 * it locates a function with SplashProcAddress on demand.
 * if SplashProcAddress fails, def value is returned.
 *
 * it is further wrapped with INVOKEV (works with functions which return
 * void and INVOKE (for all other functions). INVOKEV looks a bit ugly,
 * that's due being unable to return a value of type void in C. INVOKEV
 * works around this by using semicolon instead of return operator.
 */
#define _INVOKE(name,def,ret) \
    static void* proc = NULL; \
    if (!proc) { proc = SplashProcAddress(#name); } \
    if (!proc) { return def; } \
    ret ((name##_t)proc)

#define INVOKE(name,def) _INVOKE(name,def,return)
#define INVOKEV(name) _INVOKE(name, ,;)


int     DoSplashLoadMemory(void* pdata, int size) {
    INVOKE(SplashLoadMemory, 0)(pdata, size);
}

int     DoSplashLoadFile(const char* filename) {
    INVOKE(SplashLoadFile, 0)(filename);
}

int     DoSplashInit(void) {
    INVOKE(SplashInit, 0)();
}

void    DoSplashClose(void) {
    INVOKEV(SplashClose)();
}

void    DoSplashSetFileJarName(const char* fileName, const char* jarName) {
    INVOKEV(SplashSetFileJarName)(fileName, jarName);
}

void    DoSplashSetScaleFactor(float scaleFactor) {
    INVOKEV(SplashSetScaleFactor)(scaleFactor);
}

jboolean DoSplashGetScaledImageName(const char* fileName, const char* jarName,
           float* scaleFactor, char *scaledImageName, const size_t scaledImageNameLength) {
        INVOKE(SplashGetScaledImageName, 0)(fileName, jarName, scaleFactor,
                                            scaledImageName, scaledImageNameLength);
}

int     DoSplashGetScaledImgNameMaxPstfixLen(const char *fileName) {
    INVOKE(SplashGetScaledImgNameMaxPstfixLen, 0)(fileName);
}

