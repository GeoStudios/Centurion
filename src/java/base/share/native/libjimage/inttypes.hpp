/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
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
