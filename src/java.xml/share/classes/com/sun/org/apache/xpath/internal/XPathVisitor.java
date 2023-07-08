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

package java.xml.share.classes.com.sun.org.apache.xpath.internal;


import java.xml.share.classes.com.sun.org.apache.xpath.internal.axes.LocPathIterator;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.axes.UnionPathIterator;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.functions.Function;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.objects.XNumber;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.objects.XString;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.operations.Operation;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.operations.UnaryOperation;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.operations.Variable;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.patterns.NodeTest;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.patterns.StepPattern;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.patterns.UnionPattern;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 * A derivation from this class can be passed to a class that implements
 * the XPathVisitable interface, to have the appropriate method called
 * for each component of the XPath.  Aside from possible other uses, the
 * main intention is to provide a reasonable means to perform expression
 * rewriting.
 *
 * <p>Each method has the form
 * <code>boolean visitComponentType(ExpressionOwner owner, ComponentType compType)</code>.
 * The ExpressionOwner argument is the owner of the component, and can
 * be used to reset the expression for rewriting.  If a method returns
 * false, the sub hierarchy will not be traversed.</p>
 *
 * <p>This class is meant to be a base class that will be derived by concrete classes,
 * and doesn't much except return true for each method.</p>
 */
public class XPathVisitor
{
        /**
         * Visit a LocationPath.
         * @param owner The owner of the expression, to which the expression can
         *              be reset if rewriting takes place.
         * @param path The LocationPath object.
         * @return true if the sub expressions should be traversed.
         */
        public boolean visitLocationPath(ExpressionOwner owner, LocPathIterator path)
        {
                return true;
        }

        /**
         * Visit a UnionPath.
         * @param owner The owner of the expression, to which the expression can
         *              be reset if rewriting takes place.
         * @param path The UnionPath object.
         * @return true if the sub expressions should be traversed.
         */
        public boolean visitUnionPath(ExpressionOwner owner, UnionPathIterator path)
        {
                return true;
        }

        /**
         * Visit a step within a location path.
         * @param owner The owner of the expression, to which the expression can
         *              be reset if rewriting takes place.
         * @param step The Step object.
         * @return true if the sub expressions should be traversed.
         */
        public boolean visitStep(ExpressionOwner owner, NodeTest step)
        {
                return true;
        }

        /**
         * Visit a predicate within a location path.  Note that there isn't a
         * proper unique component for predicates, and that the expression will
         * be called also for whatever type Expression is.
         *
         * @param owner The owner of the expression, to which the expression can
         *              be reset if rewriting takes place.
         * @param pred The predicate object.
         * @return true if the sub expressions should be traversed.
         */
        public boolean visitPredicate(ExpressionOwner owner, Expression pred)
        {
                return true;
        }

        /**
         * Visit a binary operation.
         * @param owner The owner of the expression, to which the expression can
         *              be reset if rewriting takes place.
         * @param op The operation object.
         * @return true if the sub expressions should be traversed.
         */
        public boolean visitBinaryOperation(ExpressionOwner owner, Operation op)
        {
                return true;
        }

        /**
         * Visit a unary operation.
         * @param owner The owner of the expression, to which the expression can
         *              be reset if rewriting takes place.
         * @param op The operation object.
         * @return true if the sub expressions should be traversed.
         */
        public boolean visitUnaryOperation(ExpressionOwner owner, UnaryOperation op)
        {
                return true;
        }

        /**
         * Visit a variable reference.
         * @param owner The owner of the expression, to which the expression can
         *              be reset if rewriting takes place.
         * @param var The variable reference object.
         * @return true if the sub expressions should be traversed.
         */
        public boolean visitVariableRef(ExpressionOwner owner, Variable var)
        {
                return true;
        }

        /**
         * Visit a function.
         * @param owner The owner of the expression, to which the expression can
         *              be reset if rewriting takes place.
         * @param func The function reference object.
         * @return true if the sub expressions should be traversed.
         */
        public boolean visitFunction(ExpressionOwner owner, Function func)
        {
                return true;
        }

        /**
         * Visit a match pattern.
         * @param owner The owner of the expression, to which the expression can
         *              be reset if rewriting takes place.
         * @param pattern The match pattern object.
         * @return true if the sub expressions should be traversed.
         */
        public boolean visitMatchPattern(ExpressionOwner owner, StepPattern pattern)
        {
                return true;
        }

        /**
         * Visit a union pattern.
         * @param owner The owner of the expression, to which the expression can
         *              be reset if rewriting takes place.
         * @param pattern The union pattern object.
         * @return true if the sub expressions should be traversed.
         */
        public boolean visitUnionPattern(ExpressionOwner owner, UnionPattern pattern)
        {
                return true;
        }

        /**
         * Visit a string literal.
         * @param owner The owner of the expression, to which the expression can
         *              be reset if rewriting takes place.
         * @param str The string literal object.
         * @return true if the sub expressions should be traversed.
         */
        public boolean visitStringLiteral(ExpressionOwner owner, XString str)
        {
                return true;
        }


        /**
         * Visit a number literal.
         * @param owner The owner of the expression, to which the expression can
         *              be reset if rewriting takes place.
         * @param num The number literal object.
         * @return true if the sub expressions should be traversed.
         */
        public boolean visitNumberLiteral(ExpressionOwner owner, XNumber num)
        {
                return true;
        }


}
