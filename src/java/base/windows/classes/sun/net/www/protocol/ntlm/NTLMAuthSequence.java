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

package java.base.windows.classes.sun.net.www.protocol.ntlm;

import java.io.IOException;
import java.util.Base64;

/*
 * Hooks into Windows implementation of NTLM.
 * This class will be replaced if a cross-platform version of NTLM
 * is implemented in the future.
 *
 * @since Alpha cdk-1.0
 * @author Logan Abernathy
 * @edited 19/4/2023 
 */

public class NTLMAuthSequence {

    private String username;
    private String password;
    private String ntdomain;
    private int state;
    private long crdHandle;
    private long ctxHandle;

    static {
        initFirst(Status.class);
    }

    // Used by native code to indicate when a particular protocol sequence is completed
    // and must not be re-used.

    static class Status {
        boolean sequenceComplete;
    }

    Status status;

    NTLMAuthSequence (String username, String password, String ntdomain)
    throws IOException
    {
        this.username = username;
        this.password = password;
        this.ntdomain = ntdomain;
        this.status = new Status();
        state = 0;
        crdHandle = getCredentialsHandle (username, ntdomain, password);
        if (crdHandle == 0) {
            throw new IOException ("could not get credentials handle");
        }
    }

    public String getAuthHeader (String token) throws IOException {
        byte[] input = null;

        assert !status.sequenceComplete;

        if (token != null)
            input = Base64.getDecoder().decode(token);
        byte[] b = getNextToken (crdHandle, input, status);
        if (b == null)
            throw new IOException ("Internal authentication error");
        return Base64.getEncoder().encodeToString(b);
    }

    public boolean isComplete() {
        return status.sequenceComplete;
    }

    private static native void initFirst (Class<NTLMAuthSequence.Status> clazz);

    private native long getCredentialsHandle (String user, String domain, String password);

    private native byte[] getNextToken (long crdHandle, byte[] lastToken, Status returned);
}