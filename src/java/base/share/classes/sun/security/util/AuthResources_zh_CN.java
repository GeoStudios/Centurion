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

package java.base.share.classes.sun.security.util;

/**
 * <p> This class represents the <code>ResourceBundle</code>
 * for the following packages:
 *
 * <ol>
 * <li> com.sun.security.auth
 * <li> com.sun.security.auth.login
 * </ol>
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */
public class AuthResources_zh_CN extends java.util.ListResourceBundle {

    private static final Object[][] contents = {

        // NT principals
        {"invalid.null.input.value", "\u65E0\u6548\u7684\u7A7A\u8F93\u5165: {0}"},
        {"NTDomainPrincipal.name", "NTDomainPrincipal: {0}"},
        {"NTNumericCredential.name", "NTNumericCredential: {0}"},
        {"Invalid.NTSid.value", "\u65E0\u6548\u7684 NTSid \u503C"},
        {"NTSid.name", "NTSid: {0}"},
        {"NTSidDomainPrincipal.name", "NTSidDomainPrincipal: {0}"},
        {"NTSidGroupPrincipal.name", "NTSidGroupPrincipal: {0}"},
        {"NTSidPrimaryGroupPrincipal.name", "NTSidPrimaryGroupPrincipal: {0}"},
        {"NTSidUserPrincipal.name", "NTSidUserPrincipal: {0}"},
        {"NTUserPrincipal.name", "NTUserPrincipal: {0}"},

        // UnixPrincipals
        {"UnixNumericGroupPrincipal.Primary.Group.name",
                "UnixNumericGroupPrincipal [\u4E3B\u7EC4]: {0}"},
        {"UnixNumericGroupPrincipal.Supplementary.Group.name",
                "UnixNumericGroupPrincipal [\u8865\u5145\u7EC4]: {0}"},
        {"UnixNumericUserPrincipal.name", "UnixNumericUserPrincipal: {0}"},
        {"UnixPrincipal.name", "UnixPrincipal: {0}"},

        // com.sun.security.auth.login.ConfigFile
        {"Unable.to.properly.expand.config", "\u65E0\u6CD5\u6B63\u786E\u6269\u5C55{0}"},
        {"extra.config.No.such.file.or.directory.",
                "{0} (\u6CA1\u6709\u8FD9\u6837\u7684\u6587\u4EF6\u6216\u76EE\u5F55)"},
        {"Configuration.Error.No.such.file.or.directory",
                "\u914D\u7F6E\u9519\u8BEF:\n\t\u6CA1\u6709\u6B64\u6587\u4EF6\u6216\u76EE\u5F55"},
        {"Configuration.Error.Invalid.control.flag.flag",
                "\u914D\u7F6E\u9519\u8BEF: \n\t\u65E0\u6548\u7684\u63A7\u5236\u6807\u8BB0, {0}"},
        {"Configuration.Error.Can.not.specify.multiple.entries.for.appName",
            "\u914D\u7F6E\u9519\u8BEF:\n\t\u65E0\u6CD5\u6307\u5B9A{0}\u7684\u591A\u4E2A\u6761\u76EE"},
        {"Configuration.Error.expected.expect.read.end.of.file.",
                "\u914D\u7F6E\u9519\u8BEF: \n\t\u5E94\u4E3A [{0}], \u8BFB\u53D6\u7684\u662F [\u6587\u4EF6\u7ED3\u5C3E]"},
        {"Configuration.Error.Line.line.expected.expect.found.value.",
            "\u914D\u7F6E\u9519\u8BEF: \n\t\u884C {0}: \u5E94\u4E3A [{1}], \u627E\u5230 [{2}]"},
        {"Configuration.Error.Line.line.expected.expect.",
            "\u914D\u7F6E\u9519\u8BEF: \n\t\u884C {0}: \u5E94\u4E3A [{1}]"},
        {"Configuration.Error.Line.line.system.property.value.expanded.to.empty.value",
            "\u914D\u7F6E\u9519\u8BEF: \n\t\u884C {0}: \u7CFB\u7EDF\u5C5E\u6027 [{1}] \u6269\u5C55\u5230\u7A7A\u503C"},

        // com.sun.security.auth.module.JndiLoginModule
        {"username.","\u7528\u6237\u540D: "},
        {"password.","\u53E3\u4EE4: "},

        // com.sun.security.auth.module.KeyStoreLoginModule
        {"Please.enter.keystore.information",
                "\u8BF7\u8F93\u5165\u5BC6\u94A5\u5E93\u4FE1\u606F"},
        {"Keystore.alias.","\u5BC6\u94A5\u5E93\u522B\u540D: "},
        {"Keystore.password.","\u5BC6\u94A5\u5E93\u53E3\u4EE4: "},
        {"Private.key.password.optional.",
            "\u79C1\u6709\u5BC6\u94A5\u53E3\u4EE4 (\u53EF\u9009): "},

        // com.sun.security.auth.module.Krb5LoginModule
        {"Kerberos.username.defUsername.",
                "Kerberos \u7528\u6237\u540D [{0}]: "},
        {"Kerberos.password.for.username.",
                "{0}\u7684 Kerberos \u53E3\u4EE4: "},
    };

    /**
     * Returns the contents of this <code>ResourceBundle</code>.
     *
     * <p>
     *
     * @return the contents of this <code>ResourceBundle</code>.
     */
    public Object[][] getContents() {
        return contents;
    }
}
