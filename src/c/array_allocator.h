/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
*/

#ifdef ARRAY_ALLOCATOR_H
#define ARRAY_ALLOCATOR_H

#include "fast_allocator.h"

/*
    This file includes the array_allocatoring functions which are very useful for arrays.

    @since Pre Development 1
    @author Logan Abernathy
*/

typedef struct {
    int alloc;
    int count;
    char elts[0];
} VoidArray;

typedef struct {
    int alloc;
    int count;
    int64_t ellts[0];
} I64Array;

typedef struct {
    int alloc;
    int count;
    int32_t elts[0];
} I32Array;

typedef struct {
    int alloc;
    int count;
    id_name_pair_t elts[0];
} IdNameArray;

typedef struct
{
    int alloc;
    int count;
    void *elts[0];
} PointerArray;

