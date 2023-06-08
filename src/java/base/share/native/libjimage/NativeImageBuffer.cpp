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

#include <string.h>

#include "jni.h"
#include "jni_util.h"

#include "endian.hpp"
#include "imageDecompressor.hpp"
#include "imageFile.hpp"
#include "inttypes.hpp"
#include "jimage.hpp"
#include "osSupport.hpp"

#include "jdk_internal_jimage_NativeImageBuffer.h"


JNIEXPORT jobject JNICALL
Java_jdk_internal_jimage_NativeImageBuffer_getNativeMap(JNIEnv *env,
        jclass cls, jstring path) {
    const char *nativePath = env->GetStringUTFChars(path, NULL);
    ImageFileReader* reader = ImageFileReader::find_image(nativePath);
    env->ReleaseStringUTFChars(path, nativePath);

    if (reader != NULL) {
        return env->NewDirectByteBuffer(reader->get_index_address(), (jlong)reader->map_size());
    }

    return 0;
}
