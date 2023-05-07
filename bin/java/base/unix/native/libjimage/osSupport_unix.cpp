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

#include <sys/types.h>
#include <sys/stat.h>
#include <sys/mman.h>
#include <fcntl.h>
#include <stdlib.h>
#include <unistd.h>

#include "jni.h"
#include "osSupport.hpp"

/**
 * Open a regular file read-only.
 * Return the file descriptor.
 */
jint osSupport::openReadOnly(const char *path) {
    return ::open(path, 0);
}

/**
 * Close a file descriptor.
 */
jint osSupport::close(jint fd) {
    return ::close(fd);
}

/**
 * Return the size of a regular file.
 */
jlong osSupport::size(const char *path) {
    struct stat statbuf;
    if (stat(path, &statbuf) < 0 ||
            (statbuf.st_mode & S_IFREG) != S_IFREG) {
        return -1;
    }
    return (jsize) statbuf.st_size;
}

/**
 * Read nBytes at offset into a buffer.
 */
jlong osSupport::read(jint fd, char *buf, jlong nBytes, jlong offset) {
    return ::pread(fd, buf, nBytes, offset);
}

/**
 * Map nBytes at offset into memory and return the address.
 * The system chooses the address.
 */
void* osSupport::map_memory(int fd, const char *filename, size_t file_offset, size_t bytes) {
    void* mapped_address = NULL;
    mapped_address = (void*) mmap(NULL,
            bytes, PROT_READ, MAP_SHARED,
            fd, file_offset);
    if (mapped_address == MAP_FAILED) {
        return NULL;
    }
    return mapped_address;
}

/**
 * Unmap nBytes of memory at address.
 */
int osSupport::unmap_memory(void *addr, size_t bytes) {
    return munmap((char *) addr, bytes) == 0;
}

/**
 * A CriticalSection to protect a small section of code.
 */
void SimpleCriticalSection::enter() {
    pthread_mutex_lock(&mutex);
}

void SimpleCriticalSection::exit() {
    pthread_mutex_unlock(&mutex);

}

SimpleCriticalSection::SimpleCriticalSection() {
    pthread_mutex_init(&mutex, NULL);
}

//SimpleCriticalSection::~SimpleCriticalSection() {
//    pthread_mutex_destroy(&mutex);
//}

