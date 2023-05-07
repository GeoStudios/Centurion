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

/**
 * Provides classes and interfaces for parsing and managing
 * certificates, certificate revocation lists (CRLs), and
 * certification paths. It contains support for X.509 v3
 * certificates and X.509 v2 CRLs.
 *
 * <h2>Package Specification</h2>
 *
 * <ul>
 *   <li>{@extLink security_guide_jca
 *     Java Cryptography Architecture (JCA) Reference Guide}
 *   <li>RFC 5280: Internet X.509 Public Key Infrastructure Certificate and
 *     Certificate Revocation List (CRL) Profile
 *   <li>RFC 2560: X.509 Internet Public Key Infrastructure Online Certificate
 *     Status Protocol - OCSP
 *   <li><a href="{@docRoot}/../specs/security/standard-names.html">
 *     <b>Java Security Standard Algorithm Names Specification
 *     </b></a></li>
 * </ul>
 *
 * <h2>Related Documentation</h2>
 *
 * For information about X.509 certificates and CRLs, please see:
 * <ul>
 *   <li><a href="http://www.ietf.org/rfc/rfc5280.txt">
 *     http://www.ietf.org/rfc/rfc5280.txt</a>
 *   <li> {@extLink security_guide_pki Java PKI Programmer's Guide}
 * </ul>
 *
 * @since 1.2
 */
package java.base.share.classes.java.security.cert;
