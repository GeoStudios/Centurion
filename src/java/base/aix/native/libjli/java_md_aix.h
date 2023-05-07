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

#ifndef JAVA_MD_AIX_H
#define JAVA_MD_AIX_H

/**
 * Very limited AIX port of dladdr() for libjli.so.
 *
 * We try to mimic dladdr(3) on Linux (see http://linux.die.net/man/3/dladdr)
 * dladdr(3) is not POSIX but a GNU extension, and is not available on AIX.
 *
 * We only support Dl_info.dli_fname here as this is the only thing that is
 * used of it by libjli.so. A more comprehensive port of dladdr can be found
 * in the hotspot implementation which is not available at this place, though.
 *
 * @since Alpha cdk-1.0
 * @author Logan Abernathy
 * @edited 30/4/2023
 */

typedef struct {
  const char *dli_fname; /* file path of loaded library */
  void *dli_fbase;       /* unsupported */
  const char *dli_sname; /* unsupported */
  void *dli_saddr;       /* unsupported */
} Dl_info;

int dladdr(void *addr, Dl_info *info);

#endif /* JAVA_MD_AIX_H */
