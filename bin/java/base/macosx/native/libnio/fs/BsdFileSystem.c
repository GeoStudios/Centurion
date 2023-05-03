/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include "jni.h"
#include "jni_util.h"
#include "jlong.h"

#include "nio.h"

#include <stdlib.h>
#include <unistd.h>
#include <errno.h>

#include <copyfile.h>
#include "sun_nio_fs_BsdFileSystem.h"

#define RESTARTABLE(_cmd, _result) do { \
  do { \
    _result = _cmd; \
  } while((_result == -1) && (errno == EINTR)); \
} while(0)

/**
 * @since Java 2
 * @author Logan Abernathy
 * @edited 23/4/2023
 */

static void throwUnixException(JNIEnv* env, int errnum) {
    jobject x = JNU_NewObjectByName(env, "sun/nio/fs/UnixException",
        "(I)V", errnum);
    if (x != NULL) {
        (*env)->Throw(env, x);
    }
}

int fcopyfile_callback(int what, int stage, copyfile_state_t state,
    const char* src, const char* dst, void* cancel)
{
    if (what == COPYFILE_COPY_DATA) {
        if (stage == COPYFILE_ERR
                || (stage == COPYFILE_PROGRESS && *((int*)cancel) != 0)) {
            // errno will be set to ECANCELED if the operation is cancelled,
            // or to the appropriate error number if there is an error,
            // but in either case we need to quit.
            return COPYFILE_QUIT;
        }
    }
    return COPYFILE_CONTINUE;
}

// Copy all bytes from src to dst, within the kernel if possible (Linux),
// and return zero, otherwise return the appropriate status code.
//
// Return value
//   0 on success
//   IOS_UNAVAILABLE if the platform function would block
//   IOS_UNSUPPORTED_CASE if the call does not work with the given parameters
//   IOS_UNSUPPORTED if direct copying is not supported on this platform
//   IOS_THROWN if a Java exception is thrown
//
JNIEXPORT jint JNICALL
Java_sun_nio_fs_BsdFileSystem_directCopy0
    (JNIEnv* env, jclass this, jint dst, jint src, jlong cancelAddress)
{
    volatile jint* cancel = (jint*)jlong_to_ptr(cancelAddress);

    copyfile_state_t state;
    if (cancel != NULL) {
        state = copyfile_state_alloc();
        copyfile_state_set(state, COPYFILE_STATE_STATUS_CB, fcopyfile_callback);
        copyfile_state_set(state, COPYFILE_STATE_STATUS_CTX, (void*)cancel);
    } else {
        state = NULL;
    }
    if (fcopyfile(src, dst, state, COPYFILE_DATA) < 0) {
        int errno_fcopyfile = errno;
        if (state != NULL)
            copyfile_state_free(state);
        throwUnixException(env, errno_fcopyfile);
        return IOS_THROWN;
    }
    if (state != NULL)
        copyfile_state_free(state);

    return 0;
}
