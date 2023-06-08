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

#include <stdio.h>
#include <string.h>
#include <sys/ldr.h>

#include "java_md_aix.h"

/**
 * @since Alpha cdk-1.0
 * @author Logan Abernathy
 * @edited 30/4/2023
*/

static unsigned char dladdr_buffer[0x8000];

static int fill_dll_info(void) {
    return loadquery(L_GETINFO, dladdr_buffer, sizeof(dladdr_buffer));
}

static int dladdr_dont_reload(void *addr, Dl_info *info) {
    const struct ld_info *p = (struct ld_info *)dladdr_buffer;
    memset((void *)info, 0, sizeof(Dl_info));
    for (;;) {
        if (addr >= p->ldinfo_textorg &&
            addr < p->ldinfo_textorg + p->ldinfo_textsize) {
            info->dli_fname = p->ldinfo_filename;
            return 1;
        }
        if (!p->ldinfo_next) {
            break;
        }
        p = (struct ld_info *)(((char *)p) + p->ldinfo_next);
    }
    return 0;
}

int dladdr(void *addr, Dl_info *info) {
    static int loaded = 0;
    int rc = 0;
    void *addr0;
    if (!addr) {
        return rc;
    }
    if (!loaded) {
        if (fill_dll_info() == -1)
            return rc;
        loaded = 1;
    }

    // first try with addr on cached data
    rc = dladdr_dont_reload(addr, info);

    // addr could be an AIX function descriptor, so try dereferenced version
    if (rc == 0) {
        addr0 = *((void **)addr);
        rc = dladdr_dont_reload(addr0, info);
    }

    // if we had no success until now, maybe loadquery info is outdated.
    // refresh and retry
    if (rc == 0) {
        if (fill_dll_info() == -1)
            return rc;
        rc = dladdr_dont_reload(addr, info);
        if (rc == 0) {
            rc = dladdr_dont_reload(addr0, info);
        }
    }
    return rc;
}
