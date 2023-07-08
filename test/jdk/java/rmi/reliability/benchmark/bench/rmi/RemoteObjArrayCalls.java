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

package bench.rmi;

import bench.Benchmark;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/*
 *
 */

/**
 * Benchmark for testing speed of calls with remote object array parameters and
 * return values.
 */
public class RemoteObjArrayCalls implements Benchmark {

    static class RemoteObj extends UnicastRemoteObject implements Remote {
        RemoteObj() throws RemoteException {
        }
    }

    interface Server extends Remote {
        public Remote[] call(Remote[] a) throws RemoteException;
    }

    static class ServerImpl extends UnicastRemoteObject implements Server {
        public ServerImpl() throws RemoteException {
        }

        public Remote[] call(Remote[] a) throws RemoteException {
            return a;
        }
    }

    static class ServerFactory implements BenchServer.RemoteObjectFactory {
        public Remote create() throws RemoteException {
            return new ServerImpl();
        }
    }

    /**
     * Issue calls using arrays of remote objects as parameters/return values.
     * Arguments: <array size> <# calls>
     */
    public long run(String[] args) throws Exception {
        int size = Integer.parseInt(args[0]);
        int reps = Integer.parseInt(args[1]);
        BenchServer bsrv = Main.getBenchServer();
        Server stub = (Server) bsrv.create(new ServerFactory());
        Remote[] objs = new Remote[size];
        for (int i = 0; i < size; i++)
            objs[i] = new RemoteObj();

        long start = System.currentTimeMillis();
        for (int i = 0; i < reps; i++)
            stub.call(objs);
        long time = System.currentTimeMillis() - start;

        bsrv.unexport(stub, true);
        return time;
    }
}
