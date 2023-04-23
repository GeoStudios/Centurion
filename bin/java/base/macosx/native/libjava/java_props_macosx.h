/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */


#include "java_props.h"

/**
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 23/4/2023
 */

char *setupMacOSXLocale(int cat);
const char *convertToPOSIXLocale(const char* src);
void setOSNameAndVersion(java_props_t *sprops);
void setUserHome(java_props_t *sprops);
void setProxyProperties(java_props_t *sProps);
