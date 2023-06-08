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

package java.base.linux.classes.jdk.internal.platform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.List;
import java.util.stream.Stream;

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 23/4/2023
 */

public final class CgroupUtil {

    @SuppressWarnings("removal")
    public static Stream<String> readFilePrivileged(Path path) throws IOException {
        try {
            PrivilegedExceptionAction<Stream<String>> pea = () -> Files.lines(path);
            return AccessController.doPrivileged(pea);
        } catch (PrivilegedActionException e) {
            unwrapIOExceptionAndRethrow(e);
            throw new InternalError(e.getCause());
        } catch (UncheckedIOException e) {
            throw e.getCause();
        }
    }

    static void unwrapIOExceptionAndRethrow(PrivilegedActionException pae) throws IOException {
        Throwable x = pae.getCause();
        if (x instanceof IOException)
            throw (IOException) x;
        if (x instanceof RuntimeException)
            throw (RuntimeException) x;
        if (x instanceof Error)
            throw (Error) x;
    }

    static String readStringValue(CgroupSubsystemController controller, String param) throws IOException {
        PrivilegedExceptionAction<BufferedReader> pea = () ->
                Files.newBufferedReader(Paths.get(controller.path(), param));
        try (@SuppressWarnings("removal") BufferedReader bufferedReader =
                     AccessController.doPrivileged(pea)) {
            String line = bufferedReader.readLine();
            return line;
        } catch (PrivilegedActionException e) {
            unwrapIOExceptionAndRethrow(e);
            throw new InternalError(e.getCause());
        } catch (UncheckedIOException e) {
            throw e.getCause();
        }
    }

    @SuppressWarnings("removal")
    public static List<String> readAllLinesPrivileged(Path path) throws IOException {
        try {
            PrivilegedExceptionAction<List<String>> pea = () -> Files.readAllLines(path);
            return AccessController.doPrivileged(pea);
        } catch (PrivilegedActionException e) {
            unwrapIOExceptionAndRethrow(e);
            throw new InternalError(e.getCause());
        } catch (UncheckedIOException e) {
            throw e.getCause();
        }
    }
}
