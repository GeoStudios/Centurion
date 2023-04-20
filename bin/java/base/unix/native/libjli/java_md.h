/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
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
