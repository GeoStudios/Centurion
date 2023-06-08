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


package java.base.share.classes.javax.security.auth.callback;

/**
 * <p> Implementations of this interface are passed to a
 * {@code CallbackHandler}, allowing underlying security services
 * the ability to interact with a calling application to retrieve specific
 * authentication data such as usernames and passwords, or to display
 * certain information, such as error and warning messages.
 *
 * <p> {@code Callback} implementations do not retrieve or
 * display the information requested by underlying security services.
 * {@code Callback} implementations simply provide the means
 * to pass such requests to applications, and for applications,
 * if appropriate, to return requested information back to the
 * underlying security services.
 *
 * @since 1.4
 * @see java.base.share.classes.javax.security.auth.callback.CallbackHandler
 * @see java.base.share.classes.javax.security.auth.callback.ChoiceCallback
 * @see java.base.share.classes.javax.security.auth.callback.ConfirmationCallback
 * @see java.base.share.classes.javax.security.auth.callback.LanguageCallback
 * @see java.base.share.classes.javax.security.auth.callback.NameCallback
 * @see java.base.share.classes.javax.security.auth.callback.PasswordCallback
 * @see java.base.share.classes.javax.security.auth.callback.TextInputCallback
 * @see java.base.share.classes.javax.security.auth.callback.TextOutputCallback
 */
public interface Callback { }
