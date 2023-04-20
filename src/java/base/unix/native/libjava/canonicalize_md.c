/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

/*
 * Pathname canonicalization for Unix file systems
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/stat.h>
#include <errno.h>
#include <limits.h>

#include "jdk_util.h"
#include "path_util.h"

/* Note: The comments in this file use the terminology
         defined in the java.io.File class */

/* Convert a pathname to canonical form.  The input path is assumed to contain
   no duplicate slashes.  On Solaris we can use realpath() to do most of the
   work, though once that's done we still must collapse any remaining "." and
   ".." names by hand. */

JNIEXPORT int
JDK_Canonicalize(const char *orig, char *out, int len)
{
    if (len < PATH_MAX) {
        errno = EINVAL;
        return -1;
    }

    if (strlen(orig) > PATH_MAX) {
        errno = ENAMETOOLONG;
        return -1;
    }

    /* First try realpath() on the entire path */
    if (realpath(orig, out)) {
        /* That worked, so return it */
        collapse(out);
        return 0;
    } else {
        /* Something's bogus in the original path, so remove names from the end
           until either some subpath works or we run out of names */
        char *p, *end, *r = NULL;
        char path[PATH_MAX + 1];

        // strlen(orig) <= PATH_MAX, see above
        strncpy(path, orig, PATH_MAX);
        // append null for == case
        path[PATH_MAX] = '\0';
        end = path + strlen(path);

        for (p = end; p > path;) {

            /* Skip last element */
            while ((--p > path) && (*p != '/'));
            if (p == path) break;

            /* Try realpath() on this subpath */
            *p = '\0';
            r = realpath(path, out);
            *p = (p == end) ? '\0' : '/';

            if (r != NULL) {
                /* The subpath has a canonical path */
                break;
            } else if (errno == ENOENT || errno == ENOTDIR || errno == EACCES) {
                /* If the lookup of a particular subpath fails because the file
                   does not exist, because it is of the wrong type, or because
                   access is denied, then remove its last name and try again.
                   Other I/O problems cause an error return. */
                continue;
            } else {
                return -1;
            }
        }

        if (r != NULL) {
            /* Append unresolved subpath to resolved subpath */
            int rn = strlen(r);
            if (rn + (int)strlen(p) >= len) {
                /* Buffer overflow */
                errno = ENAMETOOLONG;
                return -1;
            }
            if ((rn > 0) && (r[rn - 1] == '/') && (*p == '/')) {
                /* Avoid duplicate slashes */
                p++;
            }
            strcpy(r + rn, p);
            collapse(r);
            return 0;
        } else {
            /* Nothing resolved, so just return the original path */
            strcpy(out, path);
            collapse(out);
            return 0;
        }
    }
}
