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

#ifndef JAVA_MD_H
#define JAVA_MD_H

/*
 * This file contains common defines and includes for unix.
 */
#include <limits.h>
#include <unistd.h>
#include <sys/param.h>
#include <dlfcn.h>
#include <pthread.h>
#include "manifest_info.h"
#include "jli_util.h"

#define PATH_SEPARATOR       ':'
#define FILESEP              "/"
#define FILE_SEPARATOR       '/'
#define IS_FILE_SEPARATOR(c) ((c) == '/')
#ifndef MAXNAMELEN
#define MAXNAMELEN           PATH_MAX
#endif

#ifdef _LP64
#define JLONG_FORMAT_SPECIFIER "%ld"
#else
#define JLONG_FORMAT_SPECIFIER "%lld"
#endif

int UnsetEnv(char *name);
char *FindExecName(char *program);
const char *SetExecname(char **argv);
const char *GetExecName();
static jboolean GetJVMPath(const char *jrepath, const char *jvmtype,
                           char *jvmpath, jint jvmpathsize);
static jboolean GetJREPath(char *path, jint pathsize, jboolean speculative);

#if defined(_AIX)
#include "java_md_aix.h"
#endif

#if defined(MACOSX)
#include <crt_externs.h>
#define environ (*_NSGetEnviron())
#else
extern char **environ;
#endif

#endif /* JAVA_MD_H */
