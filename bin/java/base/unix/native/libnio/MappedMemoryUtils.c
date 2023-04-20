/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "jlong.h"
#include "java_nio_MappedMemoryUtils.h"
#include <assert.h>
#include <sys/mman.h>
#include <stddef.h>
#include <stdlib.h>

#ifdef _AIX
#include <unistd.h>
#endif

/* Output type for mincore(2) */
#ifdef __linux__
typedef unsigned char mincore_vec_t;
#else
typedef char mincore_vec_t;
#endif

#ifdef _AIX
static long calculate_number_of_pages_in_range(void* address, size_t len, size_t pagesize) {
    uintptr_t address_unaligned = (uintptr_t) address;
    uintptr_t address_aligned = address_unaligned & (~(pagesize - 1));
    size_t len2 = len + (address_unaligned - address_aligned);
    long numPages = (len2 + pagesize - 1) / pagesize;
    return numPages;
}
#endif

JNIEXPORT jboolean JNICALL
Java_java_nio_MappedMemoryUtils_isLoaded0(JNIEnv *env, jobject obj, jlong address,
                                         jlong len, jlong numPages)
{
    jboolean loaded = JNI_TRUE;
    int result = 0;
    long i = 0;
    void *a = (void *) jlong_to_ptr(address);
    mincore_vec_t* vec = NULL;

#ifdef _AIX
    /* See JDK-8186665 */
    size_t pagesize = (size_t)sysconf(_SC_PAGESIZE);
    if ((long)pagesize == -1) {
        return JNI_FALSE;
    }
    numPages = (jlong) calculate_number_of_pages_in_range(a, len, pagesize);
#endif

    /* Include space for one sentinel byte at the end of the buffer
     * to catch overflows. */
    vec = (mincore_vec_t*) malloc(numPages + 1);

    if (vec == NULL) {
        JNU_ThrowOutOfMemoryError(env, NULL);
        return JNI_FALSE;
    }

    vec[numPages] = '\x7f'; /* Write sentinel. */
    result = mincore(a, (size_t)len, vec);
    assert(vec[numPages] == '\x7f'); /* Check sentinel. */

    if (result == -1) {
        JNU_ThrowIOExceptionWithLastError(env, "mincore failed");
        free(vec);
        return JNI_FALSE;
    }

    for (i=0; i<numPages; i++) {
        if (vec[i] == 0) {
            loaded = JNI_FALSE;
            break;
        }
    }
    free(vec);
    return loaded;
}


JNIEXPORT void JNICALL
Java_java_nio_MappedMemoryUtils_load0(JNIEnv *env, jobject obj, jlong address,
                                     jlong len)
{
    char *a = (char *)jlong_to_ptr(address);
    int result = madvise((caddr_t)a, (size_t)len, MADV_WILLNEED);
    if (result == -1) {
        JNU_ThrowIOExceptionWithLastError(env, "madvise failed");
    }
}

JNIEXPORT void JNICALL
Java_java_nio_MappedMemoryUtils_unload0(JNIEnv *env, jobject obj, jlong address,
                                     jlong len)
{
    char *a = (char *)jlong_to_ptr(address);
    int result = madvise((caddr_t)a, (size_t)len, MADV_DONTNEED);
    if (result == -1) {
        JNU_ThrowIOExceptionWithLastError(env, "madvise failed");
    }
}

JNIEXPORT void JNICALL
Java_java_nio_MappedMemoryUtils_force0(JNIEnv *env, jobject obj, jobject fdo,
                                      jlong address, jlong len)
{
    void* a = (void *)jlong_to_ptr(address);
    int result = msync(a, (size_t)len, MS_SYNC);
    if (result == -1) {
        JNU_ThrowIOExceptionWithLastError(env, "msync failed");
    }
}
