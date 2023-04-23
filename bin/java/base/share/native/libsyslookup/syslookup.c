/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

// Note: the include below is not strictly required, as dependencies will be pulled using linker flags.
// Adding at least one #include removes unwanted warnings on some platforms.
#include <stdlib.h>

// Simple dummy function so this library appears as a normal library to tooling.
char* syslookup() {
  return "syslookup";
}
