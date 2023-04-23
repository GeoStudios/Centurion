/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include "jni.h"

int     DoSplashLoadMemory(void* pdata, int size); /* requires preloading the file */
int     DoSplashLoadFile(const char* filename);
int     DoSplashInit(void);
void    DoSplashClose(void);
void    DoSplashSetFileJarName(const char* fileName, const char* jarName);
void    DoSplashSetScaleFactor(float scaleFactor);
jboolean DoSplashGetScaledImageName(const char* jarName, const char* fileName,
         float* scaleFactor, char *scaleImageName, const size_t scaleImageNameLength);
int     DoSplashGetScaledImgNameMaxPstfixLen(const char *fileName);
