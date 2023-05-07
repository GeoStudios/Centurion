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
 * This class represents the <code>ResourceBundle</code>
 * for javax.security.auth and sun.security.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */
public class Resources_pt_BR extends java.util.ListResourceBundle {

    private static final Object[][] contents = {

        // javax.security.auth.PrivateCredentialPermission
        {"invalid.null.input.s.", "entrada(s) nula(s) inv\u00E1lida(s)"},
        {"actions.can.only.be.read.", "as a\u00E7\u00F5es s\u00F3 podem ser 'lidas'"},
        {"permission.name.name.syntax.invalid.",
                "sintaxe inv\u00E1lida do nome da permiss\u00E3o [{0}]: "},
        {"Credential.Class.not.followed.by.a.Principal.Class.and.Name",
                "Classe da Credencial n\u00E3o seguida por um Nome e uma Classe do Principal"},
        {"Principal.Class.not.followed.by.a.Principal.Name",
                "Classe do Principal n\u00E3o seguida por um Nome do Principal"},
        {"Principal.Name.must.be.surrounded.by.quotes",
                "O Nome do Principal deve estar entre aspas"},
        {"Principal.Name.missing.end.quote",
                "Faltam as aspas finais no Nome do Principal"},
        {"PrivateCredentialPermission.Principal.Class.can.not.be.a.wildcard.value.if.Principal.Name.is.not.a.wildcard.value",
                "A Classe do Principal PrivateCredentialPermission n\u00E3o pode ser um valor curinga (*) se o Nome do Principal n\u00E3o for um valor curinga (*)"},
        {"CredOwner.Principal.Class.class.Principal.Name.name",
                "CredOwner:\n\tClasse do Principal = {0}\n\tNome do Principal = {1}"},

        // javax.security.auth.x500
        {"provided.null.name", "nome nulo fornecido"},
        {"provided.null.keyword.map", "mapa de palavra-chave nulo fornecido"},
        {"provided.null.OID.map", "mapa OID nulo fornecido"},

        // javax.security.auth.Subject
        {"NEWLINE", "\n"},
        {"invalid.null.AccessControlContext.provided",
                "AccessControlContext nulo inv\u00E1lido fornecido"},
        {"invalid.null.action.provided", "a\u00E7\u00E3o nula inv\u00E1lida fornecida"},
        {"invalid.null.Class.provided", "Classe nula inv\u00E1lida fornecida"},
        {"Subject.", "Assunto:\n"},
        {".Principal.", "\tPrincipal: "},
        {".Public.Credential.", "\tCredencial P\u00FAblica: "},
        {".Private.Credentials.inaccessible.",
                "\tCredenciais Privadas inacess\u00EDveis\n"},
        {".Private.Credential.", "\tCredencial Privada: "},
        {".Private.Credential.inaccessible.",
                "\tCredencial Privada inacess\u00EDvel\n"},
        {"Subject.is.read.only", "O Assunto \u00E9 somente para leitura"},
        {"attempting.to.add.an.object.which.is.not.an.instance.of.java.security.Principal.to.a.Subject.s.Principal.Set",
                "tentativa de adicionar um objeto que n\u00E3o \u00E9 uma inst\u00E2ncia de java.security.Principal a um conjunto de principais do Subject"},
        {"attempting.to.add.an.object.which.is.not.an.instance.of.class",
                "tentativa de adicionar um objeto que n\u00E3o \u00E9 uma inst\u00E2ncia de {0}"},

        // javax.security.auth.login.AppConfigurationEntry
        {"LoginModuleControlFlag.", "LoginModuleControlFlag: "},

        // javax.security.auth.login.LoginContext
        {"Invalid.null.input.name", "Entrada nula inv\u00E1lida: nome"},
        {"No.LoginModules.configured.for.name",
         "Nenhum LoginModule configurado para {0}"},
        {"invalid.null.Subject.provided", "Subject nulo inv\u00E1lido fornecido"},
        {"invalid.null.CallbackHandler.provided",
                "CallbackHandler nulo inv\u00E1lido fornecido"},
        {"null.subject.logout.called.before.login",
                "Subject nulo - log-out chamado antes do log-in"},
        {"unable.to.instantiate.LoginModule.module.because.it.does.not.provide.a.no.argument.constructor",
                "n\u00E3o \u00E9 poss\u00EDvel instanciar LoginModule, {0}, porque ele n\u00E3o fornece um construtor sem argumento"},
        {"unable.to.instantiate.LoginModule",
                "n\u00E3o \u00E9 poss\u00EDvel instanciar LoginModule"},
        {"unable.to.instantiate.LoginModule.",
                "n\u00E3o \u00E9 poss\u00EDvel instanciar LoginModule: "},
        {"unable.to.find.LoginModule.class.",
                "n\u00E3o \u00E9 poss\u00EDvel localizar a classe LoginModule: "},
        {"unable.to.access.LoginModule.",
                "n\u00E3o \u00E9 poss\u00EDvel acessar LoginModule: "},
        {"Login.Failure.all.modules.ignored",
                "Falha de Log-in: todos os m\u00F3dulos ignorados"},

        // sun.security.provider.PolicyFile

        {"java.security.policy.error.parsing.policy.message",
                "java.security.policy: erro durante o parsing de {0}:\n\t{1}"},
        {"java.security.policy.error.adding.Permission.perm.message",
                "java.security.policy: erro ao adicionar a permiss\u00E3o, {0}:\n\t{1}"},
        {"java.security.policy.error.adding.Entry.message",
                "java.security.policy: erro ao adicionar a Entrada:\n\t{0}"},
        {"alias.name.not.provided.pe.name.", "nome de alias n\u00E3o fornecido ({0})"},
        {"unable.to.perform.substitution.on.alias.suffix",
                "n\u00E3o \u00E9 poss\u00EDvel realizar a substitui\u00E7\u00E3o no alias, {0}"},
        {"substitution.value.prefix.unsupported",
                "valor da substitui\u00E7\u00E3o, {0}, n\u00E3o suportado"},
        {"SPACE", " "},
        {"LPARAM", "("},
        {"RPARAM", ")"},
        {"type.can.t.be.null","o tipo n\u00E3o pode ser nulo"},

        // sun.security.provider.PolicyParser
        {"keystorePasswordURL.can.not.be.specified.without.also.specifying.keystore",
                "keystorePasswordURL n\u00E3o pode ser especificado sem que a \u00E1rea de armazenamento de chaves tamb\u00E9m seja especificada"},
        {"expected.keystore.type", "tipo de armazenamento de chaves esperado"},
        {"expected.keystore.provider", "fornecedor da \u00E1rea de armazenamento de chaves esperado"},
        {"multiple.Codebase.expressions",
                "v\u00E1rias express\u00F5es CodeBase"},
        {"multiple.SignedBy.expressions","v\u00E1rias express\u00F5es SignedBy"},
        {"duplicate.keystore.domain.name","nome do dom\u00EDnio da \u00E1rea de armazenamento de teclas duplicado: {0}"},
        {"duplicate.keystore.name","nome da \u00E1rea de armazenamento de chaves duplicado: {0}"},
        {"SignedBy.has.empty.alias","SignedBy tem alias vazio"},
        {"can.not.specify.Principal.with.a.wildcard.class.without.a.wildcard.name",
                "n\u00E3o \u00E9 poss\u00EDvel especificar um principal com uma classe curinga sem um nome curinga"},
        {"expected.codeBase.or.SignedBy.or.Principal",
                "CodeBase ou SignedBy ou Principal esperado"},
        {"expected.permission.entry", "entrada de permiss\u00E3o esperada"},
        {"number.", "n\u00FAmero "},
        {"expected.expect.read.end.of.file.",
                "esperado [{0}], lido [fim do arquivo]"},
        {"expected.read.end.of.file.",
                "esperado [;], lido [fim do arquivo]"},
        {"line.number.msg", "linha {0}: {1}"},
        {"line.number.expected.expect.found.actual.",
                "linha {0}: esperada [{1}], encontrada [{2}]"},
        {"null.principalClass.or.principalName",
                "principalClass ou principalName nulo"},

        // sun.security.pkcs11.SunPKCS11
        {"PKCS11.Token.providerName.Password.",
                "Senha PKCS11 de Token [{0}]: "},

        /* --- DEPRECATED --- */
        // javax.security.auth.Policy
        {"unable.to.instantiate.Subject.based.policy",
                "n\u00E3o \u00E9 poss\u00EDvel instanciar a pol\u00EDtica com base em Subject"}
    };


    /**
     * Returns the contents of this <code>ResourceBundle</code>.
     *
     * @return the contents of this <code>ResourceBundle</code>.
     */
    @Override
    public Object[][] getContents() {
        return contents;
    }
}

