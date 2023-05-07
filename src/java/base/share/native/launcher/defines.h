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

#ifndef _DEFINES_H
#define _DEFINES_H

#include "java.h"

#define STR_HELPER(x) #x
#define STR(x) STR_HELPER(x)

/*
 * This file contains commonly defined constants used only by main.c
 * and should not be included by another file.
 */
#ifndef VERSION_STRING
/* make sure the compilation fails */
#error "VERSION_STRING must be defined"
#endif

/* Unused, but retained for JLI_Launch compatibility*/
#define DOT_VERSION "0.0"

#ifdef JAVA_ARGS
#ifdef PROGNAME
static const char* const_progname = PROGNAME;
#else
static char* const_progname = NULL;
#endif
static const char* const_jargs[] = JAVA_ARGS;
#ifdef EXTRA_JAVA_ARGS
static const char* const_extra_jargs[] = EXTRA_JAVA_ARGS;
#else
static const char** const_extra_jargs = NULL;
#endif
#else  /* !JAVA_ARGS */
#ifdef EXTRA_JAVA_ARGS
#error "EXTRA_JAVA_ARGS defined without JAVA_ARGS"
#endif
static const char* const_progname = "java";
static const char** const_jargs = NULL;
static const char** const_extra_jargs = NULL;
#endif /* JAVA_ARGS */

#ifdef LAUNCHER_NAME
static const char* const_launcher = LAUNCHER_NAME;
#else  /* LAUNCHER_NAME */
static char* const_launcher = NULL;
#endif /* LAUNCHER_NAME */

#ifdef EXPAND_CLASSPATH_WILDCARDS
static const jboolean const_cpwildcard = JNI_TRUE;
#else
static const jboolean const_cpwildcard = JNI_FALSE;
#endif /* EXPAND_CLASSPATH_WILDCARDS */

#ifdef ENABLE_ARG_FILES
static const jboolean const_disable_argfile = JNI_FALSE;
#else
static const jboolean const_disable_argfile = JNI_TRUE;
#endif
#endif /*_DEFINES_H */
