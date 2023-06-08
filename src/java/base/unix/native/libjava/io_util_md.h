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

#include "jni_util.h"

/*
 * Macros to use the right data type for file descriptors
 */
#define FD jint

/*
 * Prototypes for functions in io_util_md.c called from io_util.c,
 * FileDescriptor.c, FileInputStream.c, FileOutputStream.c,
 * UnixFileSystem_md.c
 */
ssize_t handleWrite(FD fd, const void *buf, jint len);
ssize_t handleRead(FD fd, void *buf, jint len);
jint handleAvailable(FD fd, jlong *pbytes);
jint handleSetLength(FD fd, jlong length);
jlong handleGetLength(FD fd);
FD handleOpen(const char *path, int oflag, int mode);

/*
 * Functions to get fd from the java.io.FileDescriptor field
 * of an object.  These functions rely on having an appropriately
 * defined object with a FileDescriptor object at the fid offset.
 * If the FD object is null, return -1 to avoid crashing VM.
 */
FD getFD(JNIEnv *env, jobject cur, jfieldID fid);

/*
 * Macros to set/get fd when inside java.io.FileDescriptor
 */
#define THIS_FD(obj) (*env)->GetIntField(env, obj, IO_fd_fdID)

/*
 * Route the routines
 */
#define IO_Sync fsync
#define IO_Read handleRead
#define IO_Write handleWrite
#define IO_Append handleWrite
#define IO_Available handleAvailable
#define IO_SetLength handleSetLength
#define IO_GetLength handleGetLength

#ifdef _ALLBSD_SOURCE
#define open64 open
#define fstat64 fstat
#define stat64 stat
#define lseek64 lseek
#define ftruncate64 ftruncate
#define IO_Lseek lseek
#else
#define IO_Lseek lseek64
#endif

/*
 * On Solaris, the handle field is unused
 */
#define SET_HANDLE(fd) return (jlong)-1

/*
 * Retry the operation if it is interrupted
 */
#define RESTARTABLE(_cmd, _result)                \
    do {                                          \
        _result = _cmd;                           \
    } while ((_result == -1) && (errno == EINTR))

void fileDescriptorClose(JNIEnv *env, jobject this);

#ifdef MACOSX
jstring newStringPlatform(JNIEnv *env, const char* str);
#endif
