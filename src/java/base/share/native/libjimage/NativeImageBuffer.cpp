/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
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
