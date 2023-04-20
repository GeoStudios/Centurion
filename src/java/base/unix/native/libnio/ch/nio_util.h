/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "jlong.h"
#include <errno.h>
#include <sys/types.h>
#include <sys/un.h>

#define RESTARTABLE(_cmd, _result) do { \
  do { \
    _result = _cmd; \
  } while((_result == -1) && (errno == EINTR)); \
} while(0)

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

/* 2 bytes to allow for null at end of string and null at start of string
 * for abstract name
 */
#define MAX_UNIX_DOMAIN_PATH_LEN \
        (int)(sizeof(((struct sockaddr_un *)0)->sun_path)-2)

/* NIO utility procedures */


/* Defined in IOUtil.c */

jint fdval(JNIEnv *env, jobject fdo);
void setfdval(JNIEnv *env, jobject fdo, jint value);

jint convertReturnVal(JNIEnv *env, jint n, jboolean reading);
jlong convertLongReturnVal(JNIEnv *env, jlong n, jboolean reading);


/* Defined in Net.c */

jint handleSocketError(JNIEnv *env, jint errorValue);

/* Defined in UnixDomainSockets.c */

jbyteArray sockaddrToUnixAddressBytes(JNIEnv *env,
                                      struct sockaddr_un *sa,
                                      socklen_t len);

jint unixSocketAddressToSockaddr(JNIEnv *env,
                                jbyteArray uaddr,
                                struct sockaddr_un *sa,
                                int *len);

