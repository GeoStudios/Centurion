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

#include <netdb.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>

#if defined(__linux__) || defined(_ALLBSD_SOURCE)
#include <netinet/in.h>
#endif

#include "jni.h"
#include "jni_util.h"
#include "jlong.h"
#include "net_util.h"
#include "nio.h"
#include "nio_util.h"

#include "sun_nio_ch_DatagramChannelImpl.h"

JNIEXPORT void JNICALL
Java_sun_nio_ch_DatagramChannelImpl_disconnect0(JNIEnv *env, jclass clazz,
                                                jobject fdo, jboolean isIPv6)
{
    jint fd = fdval(env, fdo);
    int rv;

#if defined(__APPLE__)
    // On macOS systems we use disconnectx
    rv = disconnectx(fd, SAE_ASSOCID_ANY, SAE_CONNID_ANY);
#else
    SOCKETADDRESS sa;
    memset(&sa, 0, sizeof(sa));
    #if defined(_ALLBSD_SOURCE)
        sa.sa.sa_family = isIPv6 ? AF_INET6 : AF_INET;
    #else
        sa.sa.sa_family = AF_UNSPEC;
    #endif
    socklen_t len = isIPv6 ? sizeof(struct sockaddr_in6) :
                             sizeof(struct sockaddr_in);
    rv = connect(fd, &sa.sa, len);
#endif

#if defined(_ALLBSD_SOURCE) && !defined(__APPLE__)
    // On _ALLBSD_SOURCE except __APPLE__ we consider EADDRNOTAVAIL
    // error to be OK and ignore it. __APPLE__ systems are excluded
    // in this check since for __APPLE__ systems, unlike other BSD systems,
    // we issue a "disconnectx" call (a few lines above),
    // which isn't expected to return this error code.
    if (rv < 0 && errno == EADDRNOTAVAIL)
        rv = errno = 0;
#elif defined(_AIX)
    /* See W. Richard Stevens, "UNIX Network Programming, Volume 1", p. 254:
     * 'Setting the address family to AF_UNSPEC might return EAFNOSUPPORT
     * but that is acceptable.
     */
    if (rv < 0 && errno == EAFNOSUPPORT)
        rv = errno = 0;
#endif // defined(_ALLBSD_SOURCE) || defined(_AIX)

    if (rv < 0)
        handleSocketError(env, errno);
}

JNIEXPORT jint JNICALL
Java_sun_nio_ch_DatagramChannelImpl_receive0(JNIEnv *env, jclass clazz,
                                             jobject fdo, jlong bufAddress,
                                             jint len, jlong senderAddress,
                                             jboolean connected)
{
    jint fd = fdval(env, fdo);
    void *buf = (void *)jlong_to_ptr(bufAddress);
    SOCKETADDRESS *sa = (SOCKETADDRESS *)jlong_to_ptr(senderAddress);
    socklen_t sa_len = sizeof(SOCKETADDRESS);
    jboolean retry = JNI_FALSE;
    jint n;

    if (len > MAX_PACKET_LEN) {
        len = MAX_PACKET_LEN;
    }

    do {
        retry = JNI_FALSE;
        n = recvfrom(fd, buf, len, 0, (struct sockaddr *)sa, &sa_len);
        if (n < 0) {
            if (errno == EAGAIN || errno == EWOULDBLOCK) {
                return IOS_UNAVAILABLE;
            }
            if (errno == EINTR) {
                return IOS_INTERRUPTED;
            }
            if (errno == ECONNREFUSED) {
                if (connected == JNI_FALSE) {
                    retry = JNI_TRUE;
                } else {
                    JNU_ThrowByName(env, JNU_JAVANETPKG "PortUnreachableException", 0);
                    return IOS_THROWN;
                }
            } else {
                return handleSocketError(env, errno);
            }
        }
    } while (retry == JNI_TRUE);

    return n;
}

JNIEXPORT jint JNICALL
Java_sun_nio_ch_DatagramChannelImpl_send0(JNIEnv *env, jclass clazz,
                                          jobject fdo, jlong bufAddress, jint len,
                                          jlong targetAddress, jint targetAddressLen)
{
    jint fd = fdval(env, fdo);
    void *buf = (void *)jlong_to_ptr(bufAddress);
    SOCKETADDRESS *sa = (SOCKETADDRESS *)jlong_to_ptr(targetAddress);
    socklen_t sa_len = (socklen_t) targetAddressLen;
    jint n;

    if (len > MAX_PACKET_LEN) {
        len = MAX_PACKET_LEN;
    }

    n = sendto(fd, buf, len, 0, (struct sockaddr *)sa, sa_len);
    if (n < 0) {
        if (errno == EAGAIN || errno == EWOULDBLOCK) {
            return IOS_UNAVAILABLE;
        }
        if (errno == EINTR) {
            return IOS_INTERRUPTED;
        }
        if (errno == ECONNREFUSED) {
            JNU_ThrowByName(env, JNU_JAVANETPKG "PortUnreachableException", 0);
            return IOS_THROWN;
        }
        return handleSocketError(env, errno);
    }
    return n;
}
