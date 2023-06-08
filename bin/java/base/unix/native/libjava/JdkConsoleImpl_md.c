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
