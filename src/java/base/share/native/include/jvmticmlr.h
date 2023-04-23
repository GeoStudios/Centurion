/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#ifndef _JVMTI_CMLR_H_
#define _JVMTI_CMLR_H_

enum {
    JVMTI_CMLR_MAJOR_VERSION_1 = 0x00000001,
    JVMTI_CMLR_MINOR_VERSION_0 = 0x00000000,

    JVMTI_CMLR_MAJOR_VERSION   = 0x00000001,
    JVMTI_CMLR_MINOR_VERSION   = 0x00000000

    /*
     * This comment is for the "JDK import from HotSpot" sanity check:
     * version: 1.0.0
     */
};

typedef enum {
    JVMTI_CMLR_DUMMY       = 1,
    JVMTI_CMLR_INLINE_INFO = 2
} jvmtiCMLRKind;

/*
 * Record that represents arbitrary information passed through JVMTI
 * CompiledMethodLoadEvent void pointer.
 */
typedef struct _jvmtiCompiledMethodLoadRecordHeader {
  jvmtiCMLRKind kind;     /* id for the kind of info passed in the record */
  jint majorinfoversion;  /* major and minor info version values. Init'ed */
  jint minorinfoversion;  /* to current version value in jvmtiExport.cpp. */

  struct _jvmtiCompiledMethodLoadRecordHeader* next;
} jvmtiCompiledMethodLoadRecordHeader;

/*
 * Record that gives information about the methods on the compile-time
 * stack at a specific pc address of a compiled method. Each element in
 * the methods array maps to same element in the bcis array.
 */
typedef struct _PCStackInfo {
  void* pc;             /* the pc address for this compiled method */
  jint numstackframes;  /* number of methods on the stack */
  jmethodID* methods;   /* array of numstackframes method ids */
  jint* bcis;           /* array of numstackframes bytecode indices */
} PCStackInfo;

/*
 * Record that contains inlining information for each pc address of
 * an nmethod.
 */
typedef struct _jvmtiCompiledMethodLoadInlineRecord {
  jvmtiCompiledMethodLoadRecordHeader header;  /* common header for casting */
  jint numpcs;          /* number of pc descriptors in this nmethod */
  PCStackInfo* pcinfo;  /* array of numpcs pc descriptors */
} jvmtiCompiledMethodLoadInlineRecord;

/*
 * Dummy record used to test that we can pass records with different
 * information through the void pointer provided that they can be cast
 * to a jvmtiCompiledMethodLoadRecordHeader.
 */

typedef struct _jvmtiCompiledMethodLoadDummyRecord {
  jvmtiCompiledMethodLoadRecordHeader header;  /* common header for casting */
  char message[50];
} jvmtiCompiledMethodLoadDummyRecord;

#endif
