/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include <sys/types.h>
#include <string.h>
#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "jlong.h"
#include "sun_nio_ch_NativeThread.h"
#include "nio_util.h"
#include <signal.h>
#include <pthread.h>

#ifdef __linux__
  /* Also defined in net/linux_close.c */
  #define INTERRUPT_SIGNAL (SIGRTMAX - 2)
#elif defined(_AIX)
  /* Also defined in net/aix_close.c */
  #define INTERRUPT_SIGNAL (SIGRTMAX - 1)
#elif defined(_ALLBSD_SOURCE)
  /* Also defined in net/bsd_close.c */
  #define INTERRUPT_SIGNAL SIGIO
#else
  #error "missing platform-specific definition here"
#endif

static void
nullHandler(int sig)
{
}

JNIEXPORT void JNICALL
Java_sun_nio_ch_NativeThread_init(JNIEnv *env, jclass cl)
{
    /* Install the null handler for INTERRUPT_SIGNAL.  This might overwrite the
     * handler previously installed by <platform>_close.c, but that's okay
     * since neither handler actually does anything.  We install our own
     * handler here simply out of paranoia; ultimately the two mechanisms
     * should somehow be unified, perhaps within the VM.
     */

    struct sigaction sa, osa;
    sa.sa_handler = nullHandler;
    sa.sa_flags = 0;
    sigemptyset(&sa.sa_mask);
    if (sigaction(INTERRUPT_SIGNAL, &sa, &osa) < 0)
        JNU_ThrowIOExceptionWithLastError(env, "sigaction");
}

JNIEXPORT jlong JNICALL
Java_sun_nio_ch_NativeThread_current0(JNIEnv *env, jclass cl)
{
    return (jlong)pthread_self();
}

JNIEXPORT void JNICALL
Java_sun_nio_ch_NativeThread_signal0(JNIEnv *env, jclass cl, jlong thread)
{
    int ret;
    ret = pthread_kill((pthread_t)thread, INTERRUPT_SIGNAL);
#ifdef MACOSX
    if (ret != 0 && ret != ESRCH)
#else
    if (ret != 0)
#endif
        JNU_ThrowIOExceptionWithLastError(env, "Thread signal failed");
}
