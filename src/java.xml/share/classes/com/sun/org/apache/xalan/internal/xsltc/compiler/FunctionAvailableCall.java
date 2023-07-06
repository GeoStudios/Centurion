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

package java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler;


import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.PUSH;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.utils.ObjectFactory;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.java.util.java.util.java.util.List;















/**
 * @LastModified: Nov 2017
 */
final class FunctionAvailableCall extends FunctionCall {

    private final Expression _arg;
    private String     _nameOfFunct = null;
    private String     _namespaceOfFunct = null;
    private boolean    _isFunctionAvailable = false;

    /**
     * Constructs a FunctionAvailableCall FunctionCall. Takes the
     * function name qname, for example, 'function-available', and
     * a list of arguments where the arguments must be instances of
     * LiteralExpression.
     */
    public FunctionAvailableCall(QName fname, List<Expression> arguments) {
        super(fname, arguments);
        _arg = arguments.get(0);
        _type = null;

        if (_arg instanceof LiteralExpr arg) {
            _namespaceOfFunct = arg.getNamespace();
            _nameOfFunct = arg.getValue();

            if (!isInternalNamespace()) {
              _isFunctionAvailable = hasMethods();
            }
        }
    }

    /**
     * Argument of function-available call must be literal, typecheck
     * returns the type of function-available to be boolean.
     */
    public Type typeCheck(SymbolTable stable) throws TypeCheckError {
        if (_type != null) {
           return _type;
        }
        if (_arg instanceof LiteralExpr) {
            return _type = Type.Boolean;
        }
        ErrorMsg err = new ErrorMsg(ErrorMsg.NEED_LITERAL_ERR,
                        "function-available", this);
        throw new TypeCheckError(err);
    }

    /**
     * Returns an object representing the compile-time evaluation
     * of an expression. We are only using this for function-available
     * and element-available at this time.
     */
    public Object evaluateAtCompileTime() {
        return getResult() ? Boolean.TRUE : Boolean.FALSE;
    }

    /**
     * for external java functions only: reports on whether or not
     * the specified method is found in the specifed class.
     */
    private boolean hasMethods() {

        // Get the class name from the namespace uri
        String className = getClassNameFromUri(_namespaceOfFunct);

        // Get the method name from the argument to function-available
        String methodName = null;
        int colonIndex = _nameOfFunct.indexOf(":");
        if (colonIndex > 0) {
          String functionName = _nameOfFunct.substring(colonIndex+1);
          int lastDotIndex = functionName.lastIndexOf('.');
          if (lastDotIndex > 0) {
            methodName = functionName.substring(lastDotIndex+1);
            if (className != null && className.length() != 0)
              className = className + "." + functionName.substring(0, lastDotIndex);
            else
              className = functionName.substring(0, lastDotIndex);
          }
          else
            methodName = functionName;
        }
        else
          methodName = _nameOfFunct;

        if (className == null || methodName == null) {
            return false;
        }

        // Replace the '-' characters in the method name
        if (methodName.indexOf('-') > 0)
          methodName = replaceDash(methodName);

        try {
            final Class<?> clazz = ObjectFactory.findProviderClass(className, true);

            if (clazz == null) {
                return false;
            }

            final Method[] methods = clazz.getMethods();

            for (int i = 0; i < methods.length; i++) {
                final int mods = methods[i].getModifiers();

                if (Modifier.isPublic(mods) && Modifier.isStatic(mods)
                        && methods[i].getName().equals(methodName))
                {
                    return true;
                }
            }
        }
        catch (ClassNotFoundException e) {
          return false;
        }
        return false;
    }

    /**
     * Reports on whether the function specified in the argument to
     * xslt function 'function-available' was found.
     */
    public boolean getResult() {
        if (_nameOfFunct == null) {
            return false;
        }

        if (isInternalNamespace()) {
            final Parser parser = getParser();
            _isFunctionAvailable =
                parser.functionSupported(Util.getLocalName(_nameOfFunct));
        }
        return _isFunctionAvailable;
    }

    /**
     * Return true if the namespace uri is null or it is the XSLTC translet uri.
     */
    private boolean isInternalNamespace() {
        return (_namespaceOfFunct == null ||
            _namespaceOfFunct.equals(EMPTYSTRING) ||
            _namespaceOfFunct.equals(TRANSLET_URI));
    }

    /**
     * Calls to 'function-available' are resolved at compile time since
     * the namespaces declared in the stylsheet are not available at run
     * time. Consequently, arguments to this function must be literals.
     */
    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        methodGen.getInstructionList().append(new PUSH(cpg, getResult()));
    }

}
