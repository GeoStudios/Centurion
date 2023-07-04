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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Handles the process output (either stdin or stdout)
 * passing the lines to a listener
 */
public class StreamHandler implements Runnable {

    public interface Listener {
        /**
         * Called when a line has been read from the process output stream
         * @param handler this StreamHandler
         * @param s the line
         */
        void onStringRead(StreamHandler handler, String s);
    }

    private final ExecutorService executor;
    private final InputStream is;
    private final Listener listener;

    /**
     * @param is input stream to read from
     * @param listener listener to pass the read lines to
     * @throws IOException
     */
    public StreamHandler(InputStream is, Listener listener) throws IOException {
        this.is = is;
        this.listener = listener;
        executor = Executors.newSingleThreadExecutor();
    }

    /**
     * Starts asynchronous reading
     */
    public void start() {
        executor.submit(this);
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) != null) {
                listener.onStringRead(this, line);
            }
        } catch (Exception x) {
            throw new RuntimeException(x);
        }
    }

}
