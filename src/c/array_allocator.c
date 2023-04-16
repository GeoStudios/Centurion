/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
*/

//TODO: Need to add this sometime
//#include "shared_func.h"
#include "array_allocator.h"

/*
 * This file is used for the use of array_allocatoring which is very usful for arrays
 * 
 * @since Pre Development 1
 * @author Logan Abernathy
*/

int array_allocator_init_ex(ArrayAllocatorContext *ctx,
    const char *name_prefix, const int element_size,
    const int min_bits, const int max_bits,
    const bool need_lock)
    {
        const int obj_size = 0;
        const int reclaim_interval = 0;
        char name[32];
        struct fast_region_info regions[32];
        struct fast_region_info *region;
        int bits;
        int start;
        int end;
        int alloc_elements_once;
        int step;
    }