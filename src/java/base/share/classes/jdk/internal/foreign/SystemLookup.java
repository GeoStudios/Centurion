/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.jdk.internal.foreign;

import java.base.share.classes.java.lang.foreign.MemorySegment;
import java.base.share.classes.java.lang.foreign.SegmentScope;
import java.base.share.classes.java.lang.foreign.SymbolLookup;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.base.share.classes.jdk.internal.loader.NativeLibrary;
import java.base.share.classes.jdk.internal.loader.RawNativeLibraries;
import java.base.share.classes.sun.security.action.GetPropertyAction;

import static java.base.share.classes.java.lang.foreign.ValueLayout.ADDRESS;

public final class SystemLookup implements SymbolLookup {

    private SystemLookup() { }

    private static final SystemLookup INSTANCE = new SystemLookup();

    /* A fallback lookup, used when creation of system lookup fails. */
    private static final SymbolLookup FALLBACK_LOOKUP = name -> Optional.empty();

    /*
     * On POSIX systems, dlsym will allow us to lookup symbol in library dependencies; the same trick doesn't work
     * on Windows. For this reason, on Windows we do not generate any side-library, and load msvcrt.dll directly instead.
     */
    private static final SymbolLookup SYSTEM_LOOKUP = makeSystemLookup();

    private static SymbolLookup makeSystemLookup() {
        try {
            return switch (CABI.current()) {
                case SYS_V, LINUX_AARCH_64, MAC_OS_AARCH_64, LINUX_RISCV_64 -> libLookup(libs -> libs.load(jdkLibraryPath("syslookup")));
                case WIN_64 -> makeWindowsLookup(); // out of line to workaround javac crash
            };
        } catch (Throwable ex) {
            // This can happen in the event of a library loading failure - e.g. if one of the libraries the
            // system lookup depends on cannot be loaded for some reason. In such extreme cases, rather than
            // fail, return a dummy lookup.
            return FALLBACK_LOOKUP;
        }
    }

    private static SymbolLookup makeWindowsLookup() {
        Path system32 = Path.of(System.getenv("SystemRoot"), "System32");
        Path ucrtbase = system32.resolve("ucrtbase.dll");
        Path msvcrt = system32.resolve("msvcrt.dll");

        boolean useUCRT = Files.exists(ucrtbase);
        Path stdLib = useUCRT ? ucrtbase : msvcrt;
        SymbolLookup lookup = libLookup(libs -> libs.load(stdLib));

        if (useUCRT) {
            // use a fallback lookup to look up inline functions from fallback lib

            SymbolLookup fallbackLibLookup =
                    libLookup(libs -> libs.load(jdkLibraryPath("syslookup")));

            int numSymbols = WindowsFallbackSymbols.values().length;
            MemorySegment funcs = MemorySegment.ofAddress(fallbackLibLookup.find("funcs").orElseThrow().address(),
                ADDRESS.byteSize() * numSymbols, SegmentScope.global());

            Function<String, Optional<MemorySegment>> fallbackLookup = name -> Optional.ofNullable(WindowsFallbackSymbols.valueOfOrNull(name))
                .map(symbol -> MemorySegment.ofAddress(funcs.getAtIndex(ADDRESS, symbol.ordinal()).address(), 0L, SegmentScope.global()));

            final SymbolLookup finalLookup = lookup;
            lookup = name -> finalLookup.find(name).or(() -> fallbackLookup.apply(name));
        }

        return lookup;
    }

    private static SymbolLookup libLookup(Function<RawNativeLibraries, NativeLibrary> loader) {
        NativeLibrary lib = loader.apply(RawNativeLibraries.newInstance(MethodHandles.lookup()));
        return name -> {
            Objects.requireNonNull(name);
            try {
                long addr = lib.lookup(name);
                return addr == 0 ?
                        Optional.empty() :
                        Optional.of(MemorySegment.ofAddress(addr, 0, SegmentScope.global()));
            } catch (NoSuchMethodException e) {
                return Optional.empty();
            }
        };
    }

    /*
     * Returns the path of the given library name from JDK
     */
    private static Path jdkLibraryPath(String name) {
        Path javahome = Path.of(GetPropertyAction.privilegedGetProperty("java.home"));
        String lib = switch (CABI.current()) {
            case SYS_V, LINUX_AARCH_64, MAC_OS_AARCH_64, LINUX_RISCV_64 -> "lib";
            case WIN_64 -> "bin";
        };
        String libname = System.mapLibraryName(name);
        return javahome.resolve(lib).resolve(libname);
    }


    public static SystemLookup getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<MemorySegment> find(String name) {
        return SYSTEM_LOOKUP.find(name);
    }

    // fallback symbols missing from ucrtbase.dll
    // this list has to be kept in sync with the table in the companion native library
    private enum WindowsFallbackSymbols {
        // stdio
        fprintf,
        fprintf_s,
        fscanf,
        fscanf_s,
        fwprintf,
        fwprintf_s,
        fwscanf,
        fwscanf_s,
        printf,
        printf_s,
        scanf,
        scanf_s,
        snprintf,
        sprintf,
        sprintf_s,
        sscanf,
        sscanf_s,
        swprintf,
        swprintf_s,
        swscanf,
        swscanf_s,
        vfprintf,
        vfprintf_s,
        vfscanf,
        vfscanf_s,
        vfwprintf,
        vfwprintf_s,
        vfwscanf,
        vfwscanf_s,
        vprintf,
        vprintf_s,
        vscanf,
        vscanf_s,
        vsnprintf,
        vsnprintf_s,
        vsprintf,
        vsprintf_s,
        vsscanf,
        vsscanf_s,
        vswprintf,
        vswprintf_s,
        vswscanf,
        vswscanf_s,
        vwprintf,
        vwprintf_s,
        vwscanf,
        vwscanf_s,
        wprintf,
        wprintf_s,
        wscanf,
        wscanf_s,

        // time
        gmtime;

        static WindowsFallbackSymbols valueOfOrNull(String name) {
            try {
                return valueOf(name);
            } catch (IllegalArgumentException e) {
                return null;
            }
        }
    }
}
