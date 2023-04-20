/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "jdk_internal_io_JdkConsoleImpl.h"

#include <stdlib.h>
#include <unistd.h>
#include <termios.h>

JNIEXPORT jboolean JNICALL
Java_jdk_internal_io_JdkConsoleImpl_echo(JNIEnv *env,
                          jclass cls,
                          jboolean on)
{
    struct termios tio;
    jboolean old;
    int tty = fileno(stdin);
    if (tcgetattr(tty, &tio) == -1) {
        JNU_ThrowIOExceptionWithLastError(env, "tcgetattr failed");
        return !on;
    }
    old = (tio.c_lflag & ECHO) != 0;
    if (on) {
        tio.c_lflag |= ECHO;
    } else {
        tio.c_lflag &= ~ECHO;
    }
    if (tcsetattr(tty, TCSANOW, &tio) == -1) {
        JNU_ThrowIOExceptionWithLastError(env, "tcsetattr failed");
    }
    return old;
}
