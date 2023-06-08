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

package java.base.share.classes.java.lang.reflect;

/**
 * The Permission class for reflective operations.
 * <P>
 * The following table
 * provides a summary description of what the permission allows,
 * and discusses the risks of granting code the permission.
 *
 * <table class="striped">
 * <caption style="display:none">Table shows permission target name, what the permission allows, and associated risks</caption>
 * <thead>
 * <tr>
 * <th scope="col">Permission Target Name</th>
 * <th scope="col">What the Permission Allows</th>
 * <th scope="col">Risks of Allowing this Permission</th>
 * </tr>
 * </thead>
 * <tbody>
 *
 * <tr>
 *   <th scope="row">suppressAccessChecks</th>
 *   <td>ability to suppress the standard Java language access checks
 *       on fields and methods in a class; allow access not only public members
 *       but also allow access to default (package) access, protected,
 *       and private members.</td>
 *   <td>This is dangerous in that information (possibly confidential) and
 *       methods normally unavailable would be accessible to malicious code.</td>
 * </tr>
 * <tr>
 *   <th scope="row">newProxyInPackage.{package name}</th>
 *   <td>ability to create a proxy instance in the specified package of which
 *       the non-public interface that the proxy class implements.</td>
 *   <td>This gives code access to classes in packages to which it normally
 *       does not have access and the dynamic proxy class is in the system
 *       protection domain. Malicious code may use these classes to
 *       help in its attempt to compromise security in the system.</td>
 * </tr>
 *
 * </tbody>
 * </table>
 *
 * @see java.security.Permission
 * @see java.security.BasicPermission
 * @see AccessibleObject
 * @see Field#get
 * @see Field#set
 * @see Method#invoke
 * @see Constructor#newInstance
 * @see Proxy#newProxyInstance
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 3/5/2023
 */
public final
class ReflectPermission extends java.security.BasicPermission {

    @java.io.Serial
    private static final long serialVersionUID = 7412737110241507485L;

    /**
     * Constructs a ReflectPermission with the specified name.
     *
     * @param name the name of the ReflectPermission
     *
     * @throws NullPointerException if {@code name} is {@code null}.
     * @throws IllegalArgumentException if {@code name} is empty.
     */
    public ReflectPermission(String name) {
        super(name);
    }

    /**
     * Constructs a ReflectPermission with the specified name and actions.
     * The actions should be null; they are ignored.
     *
     * @param name the name of the ReflectPermission
     *
     * @param actions should be null
     *
     * @throws NullPointerException if {@code name} is {@code null}.
     * @throws IllegalArgumentException if {@code name} is empty.
     */
    public ReflectPermission(String name, String actions) {
        super(name, actions);
    }

}
