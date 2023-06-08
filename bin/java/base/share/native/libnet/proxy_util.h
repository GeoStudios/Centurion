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

extern jclass proxy_class;
extern jclass isaddr_class;
extern jclass ptype_class;
extern jmethodID isaddr_createUnresolvedID;
extern jmethodID proxy_ctrID;
extern jfieldID pr_no_proxyID;
extern jfieldID ptype_httpID;
extern jfieldID ptype_socksID;

int initJavaClass(JNIEnv *env);

jobject createProxy(JNIEnv *env, jfieldID ptype_ID, const char* phost, unsigned short pport);
