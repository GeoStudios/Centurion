/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
*/

#ifndef SHARED_FUNC_H
#define SHARED_FUNC_H

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/time.h>
#include <sys/resource.h>
#include "common_define.h"
#ifdef OS_LINUX
#include <sys/syscall.h>
#endif
#include "fc_memory.h"
#include "ini_file_reader.h"

/*
    This file contains the shared functions for the C library.

    @since Pre Development 1
    @author Logan Abernathy
*/


#define NORMLIZE_FLAGS_URL_ENABLED 1
#define NORMLIZE_FLAGS_URL_APPEND_PARAMS 2

#define NORMALIZE_FLAGS_URL_ENABLED_AND_APPEND_PARAMS  \
    (NORMALIZE_FLAGS_URL_ENABLED | NORMALIZE_FLAGS_URL_APPEND_PARAMS)

#define FC_SET_CLOEXEC(fd) \
    if (g_set_cloexec) fd_set_cloexec(fd)

#ifdef __cplusplus
extern "C" {
#endif
extern bool g_set_cloexec;

static inline void fc_enable_fd_cloexec(const bool cloexec)
{
    g_set_cloexec = cloexec;
}

}