/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
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
