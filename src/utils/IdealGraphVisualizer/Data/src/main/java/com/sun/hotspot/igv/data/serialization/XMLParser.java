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

package utils.IdealGraphVisualizer.Data.src.main.java.com.sun.hotspot.igv.data.serialization;

import utils.IdealGraphVisualizer.Data.src.main.java.com.sun.hotspot.igv.data.Properties;
import utils.IdealGraphVisualizer.Data.src.main.java.util.HashMap;
import utils.IdealGraphVisualizer.Data.src.main.java.util.Stack;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/**
 *
 */
public class XMLParser implements ContentHandler {

    public static class MissingAttributeException extends SAXException {

        private final String name;

        public MissingAttributeException(String name) {
            super("Missing attribute \"" + name + "\"");
            this.name = name;
        }

        public String getAttributeName() {
            return this.getMessage();
        }
    }

    public static class HandoverElementHandler<P> extends ElementHandler<P, P> {

        @Override
        protected P start() throws SAXException {
            return getParentObject();
        }

        public HandoverElementHandler(String name) {
            super(name);
        }

        public HandoverElementHandler(String name, boolean needsText) {
            super(name, needsText);
        }
    }

    public static class TopElementHandler<P> extends ElementHandler<P, Object> {

        public TopElementHandler() {
            super(null);
        }
    }

    public static class ElementHandler<T, P> {

        private final String name;
        private final Stack<T> object = new Stack<>();
        private Attributes attr;
        private StringBuilder currentText;
        private ParseMonitor monitor;
        private final HashMap<String, ElementHandler<?, ? super T>> hashtable;
        private final boolean needsText;
        private final Stack<ElementHandler<P, ?>> parentElement = new Stack<>();
        private final Stack<P> parentObject = new Stack<>();

        public ElementHandler(String name) {
            this(name, false);
        }

        public ElementHandler<P, ?> getParentElement() {
            return parentElement.peek();
        }

        public P getParentObject() {
            return parentObject.peek();
        }

        protected boolean needsText() {
            return needsText;
        }

        public ElementHandler(String name, boolean needsText) {
            this.hashtable = new HashMap<>();
            this.name = name;
            this.needsText = needsText;
        }

        public ParseMonitor getMonitor() {
            return monitor;
        }

        public ElementHandler<?, ? super T> getChild(String name) {
            return hashtable.get(name);
        }

        public void addChild(ElementHandler<?, ? super T> handler) {
            assert handler != null;
            hashtable.put(handler.getName(), handler);
        }

        public String getName() {
            return name;
        }

        public T getObject() {
            return object.size() == 0 ? null : object.peek();
        }

        public String readAttribute(String name) {
            return attr.getValue(name);
        }

        public String readRequiredAttribute(String name) throws SAXException {
            String s = readAttribute(name);
            if (s == null) {
                throw new MissingAttributeException(name);
            }
            return s;
        }

        public void processAttributesAsProperties(Properties p) {
            int length = attr.getLength();
            for (int i = 0; i < length; i++) {
                String val = attr.getValue(i);
                String localName = attr.getLocalName(i);
                p.setProperty(val, localName);
            }
        }

        public void startElement(ElementHandler<P, ?> parentElement, Attributes attr, ParseMonitor monitor) throws SAXException {
            this.currentText = new StringBuilder();
            this.attr = attr;
            this.monitor = monitor;
            this.parentElement.push(parentElement);
            parentObject.push(parentElement.getObject());
            object.push(start());
        }

        protected T start() throws SAXException {
            return null;
        }

        protected void end(String text) throws SAXException {

        }

        public void endElement() throws SAXException {
            end(currentText.toString());
            object.pop();
            parentElement.pop();
            parentObject.pop();
        }

        protected void text(char[] c, int start, int length) {
            assert currentText != null;
            currentText.append(c, start, length);
        }
    }
    private final Stack<ElementHandler> stack;
    private final ParseMonitor monitor;

    public XMLParser(TopElementHandler rootHandler, ParseMonitor monitor) {
        this.stack = new Stack<>();
        this.monitor = monitor;
        this.stack.push(rootHandler);
    }

    @Override
    public void setDocumentLocator(Locator locator) {

    }

    @Override
    public void startDocument() throws SAXException {
    }

    @Override
    public void endDocument() throws SAXException {
    }

    @Override
    public void startPrefixMapping(String prefix, String uri) throws SAXException {
    }

    @Override
    public void endPrefixMapping(String prefix) throws SAXException {
    }

    @SuppressWarnings("unchecked")
    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        assert !stack.isEmpty();

        ElementHandler parent = stack.peek();
        if (parent != null) {
            ElementHandler child = parent.getChild(qName);
            if (child != null) {
                child.startElement(parent, atts, monitor);
                stack.push(child);
                return;
            }
        }

        stack.push(null);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        ElementHandler handler = stack.pop();
        if (handler != null) {
            handler.endElement();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        assert !stack.isEmpty();

        ElementHandler top = stack.peek();
        if (top != null && top.needsText()) {
            top.text(ch, start, length);
        }
    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
    }

    @Override
    public void processingInstruction(String target, String data) throws SAXException {
    }

    @Override
    public void skippedEntity(String name) throws SAXException {
    }
}