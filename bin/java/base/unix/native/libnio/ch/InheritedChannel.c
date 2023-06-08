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

#include <stdlib.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <unistd.h>
#include <fcntl.h>

#include "jni.h"

#include "jni.h"
#include "jni_util.h"
#include "net_util.h"
#include "nio_util.h"

#include "sun_nio_ch_InheritedChannel.h"

JNIEXPORT void JNICALL
Java_sun_nio_ch_InheritedChannel_initIDs(JNIEnv *env, jclass cla)
{
    /* Initialize InetAddress IDs before later use of NET_XXX functions */
    initInetAddressIDs(env);
}

JNIEXPORT jobject JNICALL
Java_sun_nio_ch_InheritedChannel_inetPeerAddress0(JNIEnv *env, jclass cla, jint fd)
{
    SOCKETADDRESS sa;
    socklen_t len = sizeof(SOCKETADDRESS);
    jobject remote_ia = NULL;
    jint remote_port;

    if (getpeername(fd, &sa.sa, &len) == 0) {
        remote_ia = NET_SockaddrToInetAddress(env, &sa, (int *)&remote_port);
    }

    return remote_ia;
}

JNIEXPORT jbyteArray JNICALL
Java_sun_nio_ch_InheritedChannel_unixPeerAddress0(JNIEnv *env, jclass cla, jint fd)
{
    struct sockaddr_un sa;
    socklen_t len = sizeof(struct sockaddr_un);
    jobject remote_sa = NULL;

    if (getpeername(fd, (struct sockaddr *)&sa, &len) == 0) {
        if (sa.sun_family == AF_UNIX) {
            remote_sa = sockaddrToUnixAddressBytes(env, &sa, len);
        }
    }
    return remote_sa;
}

JNIEXPORT jint JNICALL
Java_sun_nio_ch_InheritedChannel_peerPort0(JNIEnv *env, jclass cla, jint fd)
{
    SOCKETADDRESS sa;
    socklen_t len = sizeof(SOCKETADDRESS);
    jint remote_port = -1;

    if (getpeername(fd, (struct sockaddr *)&sa.sa, &len) == 0) {
        NET_SockaddrToInetAddress(env, &sa, (int *)&remote_port);
    }

    return remote_port;
}

JNIEXPORT jint JNICALL
Java_sun_nio_ch_InheritedChannel_addressFamily(JNIEnv *env, jclass cla, jint fd)
{
    SOCKETADDRESS addr;
    socklen_t addrlen = sizeof(addr);

    if (getsockname(fd, (struct sockaddr *)&addr, &addrlen) < 0) {
        return sun_nio_ch_InheritedChannel_AF_UNKNOWN;
    }
    if (addr.sa.sa_family == AF_INET) {
        return sun_nio_ch_InheritedChannel_AF_INET;
    }
    if (addr.sa.sa_family == AF_INET6) {
        return sun_nio_ch_InheritedChannel_AF_INET6;
    }
    if (addr.sa.sa_family == AF_UNIX) {
        return sun_nio_ch_InheritedChannel_AF_UNIX;
    }
    return sun_nio_ch_InheritedChannel_AF_UNKNOWN;
}

JNIEXPORT jboolean JNICALL
Java_sun_nio_ch_InheritedChannel_isConnected(JNIEnv *env, jclass cla, jint fd)
{
    SOCKETADDRESS addr;
    socklen_t addrlen = sizeof(addr);

    if (getpeername(fd, (struct sockaddr *)&addr, &addrlen) < 0) {
        return JNI_FALSE;
    }
    return JNI_TRUE;
}

JNIEXPORT jint JNICALL
Java_sun_nio_ch_InheritedChannel_soType0(JNIEnv *env, jclass cla, jint fd)
{
    int sotype;
    socklen_t arglen = sizeof(sotype);
    if (getsockopt(fd, SOL_SOCKET, SO_TYPE, (void *)&sotype, &arglen) == 0) {
        if (sotype == SOCK_STREAM)
            return sun_nio_ch_InheritedChannel_SOCK_STREAM;
        if (sotype == SOCK_DGRAM)
            return sun_nio_ch_InheritedChannel_SOCK_DGRAM;
    }
    return sun_nio_ch_InheritedChannel_UNKNOWN;
}

JNIEXPORT jint JNICALL
Java_sun_nio_ch_InheritedChannel_dup(JNIEnv *env, jclass cla, jint fd)
{
   int newfd = dup(fd);
   if (newfd < 0) {
        JNU_ThrowIOExceptionWithLastError(env, "dup failed");
   }
   return (jint)newfd;
}

JNIEXPORT void JNICALL
Java_sun_nio_ch_InheritedChannel_dup2(JNIEnv *env, jclass cla, jint fd, jint fd2)
{
   if (dup2(fd, fd2) < 0) {
        JNU_ThrowIOExceptionWithLastError(env, "dup2 failed");
   }
}

JNIEXPORT jint JNICALL
Java_sun_nio_ch_InheritedChannel_open0(JNIEnv *env, jclass cla, jstring path, jint oflag)
{
    const char *str;
    int oflag_actual;

    /* convert to OS specific value */
    switch (oflag) {
        case sun_nio_ch_InheritedChannel_O_RDWR :
            oflag_actual = O_RDWR;
            break;
        case sun_nio_ch_InheritedChannel_O_RDONLY :
            oflag_actual = O_RDONLY;
            break;
        case sun_nio_ch_InheritedChannel_O_WRONLY :
            oflag_actual = O_WRONLY;
            break;
        default :
            JNU_ThrowInternalError(env, "Unrecognized file mode");
            return -1;
    }

    str = JNU_GetStringPlatformChars(env, path, NULL);
    if (str == NULL) {
        return (jint)-1;
    } else {
        int fd = open(str, oflag_actual);
        if (fd < 0) {
            JNU_ThrowIOExceptionWithLastError(env, str);
        }
        JNU_ReleaseStringPlatformChars(env, path, str);
        return (jint)fd;
    }
}

JNIEXPORT void JNICALL
Java_sun_nio_ch_InheritedChannel_close0(JNIEnv *env, jclass cla, jint fd)
{
    if (close(fd) < 0) {
        JNU_ThrowIOExceptionWithLastError(env, "close failed");
    }
}
