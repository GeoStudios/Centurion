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

#include <stdlib.h>
#include <string.h>
#if !defined(_ALLBSD_SOURCE)
#include <alloca.h>
#endif

#include "path_util.h"

/* Check the given name sequence to see if it can be further collapsed.
   Return zero if not, otherwise return the number of names in the sequence. */

static int
collapsible(char *names)
{
    char *p = names;
    int dots = 0, n = 0;

    while (*p) {
        if ((p[0] == '.') && ((p[1] == '\0')
                              || (p[1] == '/')
                              || ((p[1] == '.') && ((p[2] == '\0')
                                                    || (p[2] == '/'))))) {
            dots = 1;
        }
        n++;
        while (*p) {
            if (*p == '/') {
                p++;
                break;
            }
            p++;
        }
    }
    return (dots ? n : 0);
}


/* Split the names in the given name sequence,
   replacing slashes with nulls and filling in the given index array */

static void
splitNames(char *names, char **ix)
{
    char *p = names;
    int i = 0;

    while (*p) {
        ix[i++] = p++;
        while (*p) {
            if (*p == '/') {
                *p++ = '\0';
                break;
            }
            p++;
        }
    }
}


/* Join the names in the given name sequence, ignoring names whose index
   entries have been cleared and replacing nulls with slashes as needed */

static void
joinNames(char *names, int nc, char **ix)
{
    int i;
    char *p;

    for (i = 0, p = names; i < nc; i++) {
        if (!ix[i]) continue;
        if (i > 0) {
            p[-1] = '/';
        }
        if (p == ix[i]) {
            p += strlen(p) + 1;
        } else {
            char *q = ix[i];
            while ((*p++ = *q++));
        }
    }
    *p = '\0';
}

/* Collapse "." and ".." names in the given path wherever possible.
   A "." name may always be eliminated; a ".." name may be eliminated if it
   follows a name that is neither "." nor "..".  This is a syntactic operation
   that performs no filesystem queries, so it should only be used to cleanup
   after invoking the realpath() procedure. */

void
collapse(char *path)
{
    char *names = (path[0] == '/') ? path + 1 : path; /* Preserve first '/' */
    int nc;
    char **ix;
    int i, j;

    nc = collapsible(names);
    if (nc < 2) return;         /* Nothing to do */
    ix = (char **)alloca(nc * sizeof(char *));
    splitNames(names, ix);

    for (i = 0; i < nc; i++) {
        int dots = 0;

        /* Find next occurrence of "." or ".." */
        do {
            char *p = ix[i];
            if (p[0] == '.') {
                if (p[1] == '\0') {
                    dots = 1;
                    break;
                }
                if ((p[1] == '.') && (p[2] == '\0')) {
                    dots = 2;
                    break;
                }
            }
            i++;
        } while (i < nc);
        if (i >= nc) break;

        /* At this point i is the index of either a "." or a "..", so take the
           appropriate action and then continue the outer loop */
        if (dots == 1) {
            /* Remove this instance of "." */
            ix[i] = 0;
        }
        else {
            /* If there is a preceding name, remove both that name and this
               instance of ".."; otherwise, leave the ".." as is */
            for (j = i - 1; j >= 0; j--) {
                if (ix[j]) break;
            }
            if (j < 0) continue;
            ix[j] = 0;
            ix[i] = 0;
        }
        /* i will be incremented at the top of the loop */
    }

    joinNames(names, nc, ix);
}
