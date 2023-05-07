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

#ifndef NET_UTILS_MD_H
#define NET_UTILS_MD_H

#include <netdb.h>
#include <poll.h>
#include <sys/socket.h>

/************************************************************************
 * Macros and constants
 */

#define NET_NSEC_PER_MSEC 1000000
#define NET_NSEC_PER_SEC  1000000000
#define NET_NSEC_PER_USEC 1000

/* in case NI_MAXHOST is not defined in netdb.h */
#ifndef NI_MAXHOST
#define NI_MAXHOST 1025
#endif

/* Defines SO_REUSEPORT */
#ifndef SO_REUSEPORT
#ifdef __linux__
#define SO_REUSEPORT 15
#elif defined(AIX) || defined(MACOSX)
#define SO_REUSEPORT 0x0200
#else
#define SO_REUSEPORT 0
#endif
#endif

/*
 * On 64-bit JDKs we use a much larger stack and heap buffer.
 */
#ifdef _LP64
#define MAX_BUFFER_LEN 65536
#define MAX_HEAP_BUFFER_LEN 131072
#else
#define MAX_BUFFER_LEN 8192
#define MAX_HEAP_BUFFER_LEN 65536
#endif

typedef union {
    struct sockaddr     sa;
    struct sockaddr_in  sa4;
    struct sockaddr_in6 sa6;
} SOCKETADDRESS;

/************************************************************************
 * Functions
 */
void NET_ThrowUnknownHostExceptionWithGaiError(JNIEnv *env,
                                               const char* hostname,
                                               int gai_error);
void NET_ThrowByNameWithLastError(JNIEnv *env, const char *name,
                                  const char *defaultDetail);

#endif /* NET_UTILS_MD_H */
