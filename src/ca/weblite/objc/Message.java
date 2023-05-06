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

package ca.weblite.objc;

import java.util.ArrayList;
import java.util.List;

import com.sun.jna.Pointer;

/**
 * A structure the encapsulates a message.  This is an optional alternative
 * way of sending messages to the Objective-C runtime.
 *
 * @see Client#send(Message...)
 * @see RuntimeUtils#msg(Message...)
 * @see Proxy#send(Message...)
 * @author Logan Abernathy
 * @since Alpha cdk-1.0
 */

public class Message {

    /**
     * Status identifier of a message to indicate that it has been skipped.
     *
     */
    public static final int STATUS_SKIPPED=1;

    /**
     * Status identifier of a message to indicate that is has been cancelled.
     */
    public static final int STATUS_CANCELLED=2;

    /**
     * Status identifier of a message to indicated that it has been completed.
     */
    public static final int STATUS_COMPLETED=3;

    /**
     * Status identifier of a message to indicate that it is ready to be sent.
     */
    public static final int STATUS_READY=0;

    /**
     * The target of the message.
     */
    public Pointer receiver;

    /**
     * The selector of the message.
     */
    public Pointer selector;

    /**
     * List of arguments to pass to the method invocation.
     */
    public List args = new ArrayList();

    /**
     * Placeholder for the result of the message. (i.e. return value).
     */
    public Object result;

    /**
     * If there was en error in the message handling, the error will be saved
     * here.
     */
    public Exception error;

    /**
     * The current status of the message.  Before running, its status should
     * be STATUS_READY, and after running, it should be STATUS_COMPLETED.  If,
     * for some reason it has been cancelled or skipped, then it could have
     * those statuses also.
     */
    public int status = 0;

    /**
     * Whether to coerce the input of the message.
     */
    public boolean coerceInput;
    /**
     * Whether to coerce the output of the message.
     */
    public boolean coerceOutput;


    /**
     * Whether the input was, in fact coerced. Set when the message
     * is run.
     */
    public boolean inputWasCoerced;
    /**
     * Whether the output was, in fact, coerced. Set when the message
     * is run.
     */
    public boolean outputWasCoerced;

    /**
     * Reference to the next message in the message chain.
     */
    public Message next;
    /**
     * Reference to the previous message in the message chain.
     */
    public Message previous;


    /**
     * Method that is called just before the message is sent.  This can be
     * overridden to change the parameters, skip the message, or cancel the message
     * chain altogether.
     */
    public void beforeRequest(){

    }

    /**
     * Method that is called just after the message is send and response received.
     * This can be overridden to do post processing, like changing the settings
     * of subsequent messages in the chain or doing processing based on the
     * output of the message.
     */
    public void afterResponse(){
    }
}