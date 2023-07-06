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

package java.rmi.share.classes.sun.rmi.transport;

import java.io.java.io.java.io.java.io.IOException;

/**
 * Skeleton to dispatch DGC methods.
 * Originally generated by RMIC but frozen to match the stubs.
 */
@SuppressWarnings({"deprecation", "serial"})
public final class DGCImpl_Skel
        implements java.rmi.server.Skeleton {
    private static final java.rmi.server.Operation[] operations = {
            new java.rmi.server.Operation("void clean(java.rmi.server.ObjID[], long, java.rmi.dgc.VMID, boolean)"),
            new java.rmi.server.Operation("java.rmi.dgc.Lease dirty(java.rmi.server.ObjID[], long, java.rmi.dgc.Lease)")
    };

    private static final long interfaceHash = -669196253586618813L;

    public java.rmi.server.Operation[] getOperations() {
        return operations.clone();
    }

    public void dispatch(java.rmi.Remote obj, java.rmi.server.RemoteCall remoteCall, int opnum, long hash)
            throws java.lang.Exception {
        if (hash != interfaceHash)
            throw new java.rmi.server.SkeletonMismatchException("interface hash mismatch");

        sun.rmi.transport.DGCImpl server = (sun.rmi.transport.DGCImpl) obj;
        StreamRemoteCall call = (StreamRemoteCall) remoteCall;
        switch (opnum) {
            case 0: // clean(ObjID[], long, VMID, boolean)
            {
                java.rmi.server.ObjID[] $param_arrayOf_ObjID_1;
                long $param_long_2;
                java.rmi.dgc.VMID $param_VMID_3;
                boolean $param_boolean_4;
                try {
                    java.io.ObjectInput in = call.getInputStream();
                    $param_arrayOf_ObjID_1 = (java.rmi.server.ObjID[]) in.readObject();
                    $param_long_2 = in.readLong();
                    $param_VMID_3 = (java.rmi.dgc.VMID) in.readObject();
                    $param_boolean_4 = in.readBoolean();
                } catch (ClassCastException | IOException | ClassNotFoundException e) {
                    call.discardPendingRefs();
                    throw new java.rmi.UnmarshalException("error unmarshalling arguments", e);
                } finally {
                    call.releaseInputStream();
                }
                server.clean($param_arrayOf_ObjID_1, $param_long_2, $param_VMID_3, $param_boolean_4);
                try {
                    call.getResultStream(true);
                } catch (java.io.IOException e) {
                    throw new java.rmi.MarshalException("error marshalling return", e);
                }
                break;
            }

            case 1: // dirty(ObjID[], long, Lease)
            {
                java.rmi.server.ObjID[] $param_arrayOf_ObjID_1;
                long $param_long_2;
                java.rmi.dgc.Lease $param_Lease_3;
                try {
                    java.io.ObjectInput in = call.getInputStream();
                    $param_arrayOf_ObjID_1 = (java.rmi.server.ObjID[]) in.readObject();
                    $param_long_2 = in.readLong();
                    $param_Lease_3 = (java.rmi.dgc.Lease) in.readObject();
                } catch (ClassCastException | IOException | ClassNotFoundException e) {
                    call.discardPendingRefs();
                    throw new java.rmi.UnmarshalException("error unmarshalling arguments", e);
                } finally {
                    call.releaseInputStream();
                }
                java.rmi.dgc.Lease $result = server.dirty($param_arrayOf_ObjID_1, $param_long_2, $param_Lease_3);
                try {
                    java.io.ObjectOutput out = call.getResultStream(true);
                    out.writeObject($result);
                } catch (java.io.IOException e) {
                    throw new java.rmi.MarshalException("error marshalling return", e);
                }
                break;
            }

            default:
                throw new java.rmi.UnmarshalException("invalid method number");
        }
    }
}
