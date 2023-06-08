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

#ifndef LIBJIMAGE_INTTYPES_HPP
#define LIBJIMAGE_INTTYPES_HPP

typedef unsigned char      u1;
typedef          char      s1;
typedef unsigned short     u2;
typedef          short     s2;
typedef unsigned int       u4;
typedef          int       s4;
#ifdef LP64
typedef unsigned long      u8;
typedef          long      s8;
#else
typedef unsigned long long u8;
typedef          long long s8;
#endif

#endif // LIBJIMAGE_INTTYPES_HPP
