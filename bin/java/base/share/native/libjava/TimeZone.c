/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include <stdlib.h>
#include <string.h>

#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "TimeZone_md.h"

#include "java_util_TimeZone.h"

/*
 * Gets the platform defined TimeZone ID
 */
JNIEXPORT jstring JNICALL
Java_java_util_TimeZone_getSystemTimeZoneID(JNIEnv *env, jclass ign,
                                            jstring java_home)
{
    const char *java_home_dir;
    char *javaTZ;
    jstring jstrJavaTZ = NULL;

    CHECK_NULL_RETURN(java_home, NULL);

    java_home_dir = JNU_GetStringPlatformChars(env, java_home, 0);
    CHECK_NULL_RETURN(java_home_dir, NULL);

    /*
     * Invoke platform dependent mapping function
     */
    javaTZ = findJavaTZ_md(java_home_dir);
    if (javaTZ != NULL) {
        jstrJavaTZ = JNU_NewStringPlatform(env, javaTZ);
        free((void *)javaTZ);
    }

    JNU_ReleaseStringPlatformChars(env, java_home, java_home_dir);
    return jstrJavaTZ;
}

/*
 * Gets a GMT offset-based time zone ID (e.g., "GMT-08:00")
 */
JNIEXPORT jstring JNICALL
Java_java_util_TimeZone_getSystemGMTOffsetID(JNIEnv *env, jclass ign)
{
    char *id = getGMTOffsetID();
    jstring jstrID = NULL;

    if (id != NULL) {
        jstrID = JNU_NewStringPlatform(env, id);
        free((void *)id);
    }
    return jstrID;
}
