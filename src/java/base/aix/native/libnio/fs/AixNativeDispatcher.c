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
#include <errno.h>
#include <sys/types.h>
#include <sys/mntctl.h>

#include "jni.h"
#include "jni_util.h"

#include "sun_nio_fs_AixNativeDispatcher.h"

static jfieldID entry_name;
static jfieldID entry_dir;
static jfieldID entry_fstype;
static jfieldID entry_options;

static jclass entry_cls;

/**
 * Call this to throw an internal UnixException when a system/library
 * call fails
 *
 * @since Alpha cdk-1.0
 * @author Logan Abernathy
 * @edited 30/4/2023
 */

static void throwUnixException(JNIEnv* env, int errnum) {
    jobject x = JNU_NewObjectByName(env, "sun/nio/fs/UnixException",
        "(I)V", errnum);
    if (x != NULL) {
        (*env)->Throw(env, x);
    }
}

/**
 * Initialization
 */
JNIEXPORT void JNICALL
Java_sun_nio_fs_AixNativeDispatcher_init(JNIEnv* env, jclass this)
{
    jclass clazz;

    clazz = (*env)->FindClass(env, "sun/nio/fs/UnixMountEntry");
    CHECK_NULL(clazz);
    entry_name = (*env)->GetFieldID(env, clazz, "name", "[B");
    CHECK_NULL(entry_name);
    entry_dir = (*env)->GetFieldID(env, clazz, "dir", "[B");
    CHECK_NULL(entry_dir);
    entry_fstype = (*env)->GetFieldID(env, clazz, "fstype", "[B");
    CHECK_NULL(entry_fstype);
    entry_options = (*env)->GetFieldID(env, clazz, "opts", "[B");
    CHECK_NULL(entry_options);
    entry_cls = (*env)->NewGlobalRef(env, clazz);
    if (entry_cls == NULL) {
        JNU_ThrowOutOfMemoryError(env, NULL);
        return;
    }
}

/**
 * Special implementation of getextmntent (see SolarisNativeDispatcher.c)
 * that returns all entries at once.
 */
JNIEXPORT jobjectArray JNICALL
Java_sun_nio_fs_AixNativeDispatcher_getmntctl(JNIEnv* env, jclass this)
{
    int must_free_buf = 0;
    char stack_buf[1024];
    char* buffer = stack_buf;
    size_t buffer_size = 1024;
    int num_entries;
    int i;
    jobjectArray ret;
    struct vmount * vm;

    for (i = 0; i < 5; i++) {
        num_entries = mntctl(MCTL_QUERY, buffer_size, buffer);
        if (num_entries != 0) {
            break;
        }
        if (must_free_buf) {
            free(buffer);
        }
        buffer_size *= 8;
        buffer = malloc(buffer_size);
        must_free_buf = 1;
    }
    /* Treat zero entries like errors. */
    if (num_entries <= 0) {
        if (must_free_buf) {
            free(buffer);
        }
        throwUnixException(env, errno);
        return NULL;
    }
    ret = (*env)->NewObjectArray(env, num_entries, entry_cls, NULL);
    if (ret == NULL) {
        if (must_free_buf) {
            free(buffer);
        }
        return NULL;
    }
    vm = (struct vmount*)buffer;
    for (i = 0; i < num_entries; i++) {
        jsize len;
        jbyteArray bytes;
        const char* fstype;
        /* We set all relevant attributes so there is no need to call constructor. */
        jobject entry = (*env)->AllocObject(env, entry_cls);
        if (entry == NULL) {
            if (must_free_buf) {
                free(buffer);
            }
            return NULL;
        }
        (*env)->SetObjectArrayElement(env, ret, i, entry);

        /* vm->vmt_data[...].vmt_size is 32 bit aligned and also includes NULL byte. */
        /* Since we only need the characters, it is necessary to check string size manually. */
        len = strlen((char*)vm + vm->vmt_data[VMT_OBJECT].vmt_off);
        bytes = (*env)->NewByteArray(env, len);
        if (bytes == NULL) {
            if (must_free_buf) {
                free(buffer);
            }
            return NULL;
        }
        (*env)->SetByteArrayRegion(env, bytes, 0, len, (jbyte*)((char *)vm + vm->vmt_data[VMT_OBJECT].vmt_off));
        (*env)->SetObjectField(env, entry, entry_name, bytes);

        len = strlen((char*)vm + vm->vmt_data[VMT_STUB].vmt_off);
        bytes = (*env)->NewByteArray(env, len);
        if (bytes == NULL) {
            if (must_free_buf) {
                free(buffer);
            }
            return NULL;
        }
        (*env)->SetByteArrayRegion(env, bytes, 0, len, (jbyte*)((char *)vm + vm->vmt_data[VMT_STUB].vmt_off));
        (*env)->SetObjectField(env, entry, entry_dir, bytes);

        switch (vm->vmt_gfstype) {
            case MNT_J2:
                fstype = "jfs2";
                break;
            case MNT_NAMEFS:
                fstype = "namefs";
                break;
            case MNT_NFS:
                fstype = "nfs";
                break;
            case MNT_JFS:
                fstype = "jfs";
                break;
            case MNT_CDROM:
                fstype = "cdrom";
                break;
            case MNT_PROCFS:
                fstype = "procfs";
                break;
            case MNT_NFS3:
                fstype = "nfs3";
                break;
            case MNT_AUTOFS:
                fstype = "autofs";
                break;
            case MNT_UDF:
                fstype = "udfs";
                break;
            case MNT_NFS4:
                fstype = "nfs4";
                break;
            case MNT_CIFS:
                fstype = "smbfs";
                break;
            default:
                fstype = "unknown";
        }
        len = strlen(fstype);
        bytes = (*env)->NewByteArray(env, len);
        if (bytes == NULL) {
            if (must_free_buf) {
                free(buffer);
            }
            return NULL;
        }
        (*env)->SetByteArrayRegion(env, bytes, 0, len, (jbyte*)fstype);
        (*env)->SetObjectField(env, entry, entry_fstype, bytes);

        len = strlen((char*)vm + vm->vmt_data[VMT_ARGS].vmt_off);
        bytes = (*env)->NewByteArray(env, len);
        if (bytes == NULL) {
            if (must_free_buf) {
                free(buffer);
            }
            return NULL;
        }
        (*env)->SetByteArrayRegion(env, bytes, 0, len, (jbyte*)((char *)vm + vm->vmt_data[VMT_ARGS].vmt_off));
        (*env)->SetObjectField(env, entry, entry_options, bytes);

        /* goto the next vmount structure: */
        vm = (struct vmount *)((char *)vm + vm->vmt_length);
    }

    if (must_free_buf) {
        free(buffer);
    }
    return ret;
}
