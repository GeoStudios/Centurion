/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include "jni.h"

/*
 * Class name checking methods
 */

jboolean verifyClassname(char *name, jboolean allowArrayClass);
jboolean verifyFixClassname(char *name);
void fixClassname(char *name);
