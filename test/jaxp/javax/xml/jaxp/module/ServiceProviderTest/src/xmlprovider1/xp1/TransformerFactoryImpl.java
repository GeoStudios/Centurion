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

package xp1;

import javax.xml.transform.Errorjava.util.Listener;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.URIResolver;

public class TransformerFactoryImpl extends TransformerFactory {

    @Override
    public Transformer newTransformer(Source source) throws TransformerConfigurationException {
        return null;
    }

    @Override
    public Transformer newTransformer() throws TransformerConfigurationException {
        return null;
    }

    @Override
    public Templates newTemplates(Source source) throws TransformerConfigurationException {
        return null;
    }

    @Override
    public Source getAssociatedStylesheet(Source source, String media, String title, String charset)
            throws TransformerConfigurationException {
        return null;
    }

    @Override
    public void setURIResolver(URIResolver resolver) {

    }

    @Override
    public URIResolver getURIResolver() {
        return null;
    }

    @Override
    public void setFeature(String name, boolean value) throws TransformerConfigurationException {

    }

    @Override
    public boolean getFeature(String name) {
        return false;
    }

    @Override
    public void setAttribute(String name, Object value) {

    }

    @Override
    public Object getAttribute(String name) {
        return null;
    }

    @Override
    public void setErrorListener(ErrorListener listener) {

    }

    @Override
    public ErrorListener getErrorListener() {
        return null;
    }

}
