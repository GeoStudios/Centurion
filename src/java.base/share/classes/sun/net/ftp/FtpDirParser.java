/*
 * Copyright (c) 2023 Geo-Studios and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 only, as published
 * by the Free Software Foundation. Geo-Studios designates this particular
 * file as subject to the "Classpath" exception as provided
 * by Geo-Studio in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License version 2 for more details (a copy is
 * included in the LICENSE file that accompanied this code).
 *
 * You should have received a copy of the GNU General Public License
 * version 2 along with this work; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package java.base.share.classes.sun.net.ftp;

/**
 * This interface describes a parser for the FtpClient class. Such a parser is
 * used when listing a remote directory to transform text lines like:
 *      drwxr-xr-x      1 user01      ftp   512 Jan 29 23:32 prog
 * into FtpDirEntry instances.
 *
 * @see java.net.FtpClient#setFileParser(FtpDirParser)
 */
public interface FtpDirParser {

    /**
     * Takes one line from a directory listing and returns an FtpDirEntry instance
     * based on the information contained.
     *
     * @param line a <code>String</code>, a line sent by the FTP server as a
     *        result of the LST command.
     * @return an <code>FtpDirEntry</code> instance.
     * @see java.net.FtpDirEntry
     */
    FtpDirEntry parseLine(String line);
}