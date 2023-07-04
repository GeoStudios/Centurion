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
 */
package java.security.cert;
