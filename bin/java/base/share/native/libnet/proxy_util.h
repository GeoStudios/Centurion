/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
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
