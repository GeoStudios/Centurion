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

package java.base.share.classes.javax.net.ssl;

/**
 * This class is the base interface for providing
 * algorithm-specific information to a KeyManagerFactory or
 * TrustManagerFactory.
 * <P>
 * In some cases, initialization parameters other than keystores
 * may be needed by a provider.  Users of that particular provider
 * are expected to pass an implementation of the appropriate
 * sub-interface of this class as defined by the
 * provider.  The provider can then call the specified methods in
 * the <CODE>ManagerFactoryParameters</CODE> implementation to obtain the
 * needed information.
 *
 * @author Brad R. Wetmore
 * @since 1.4
 */

public interface ManagerFactoryParameters {
}
