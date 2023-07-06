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

package org.example.person;


import java.util.Hashtable;
import javax.naming.*;
import javax.naming.directory.*;
import javax.naming.spi.*;














/*
 * State factory and object factory for the Person class.
 */



public class PersonFactory implements DirStateFactory, DirObjectFactory {

    public PersonFactory() {
    }

    @Override
    public Object getStateToBind(Object obj, Name name,
        Context nameCtx, Hashtable environment) throws NamingException {

        return null;
    }

    @Override
    public DirStateFactory.Result getStateToBind(Object obj, Name name,
        Context nameCtx, Hashtable environment, Attributes inAttrs)
            throws NamingException {

        if (obj instanceof Person) {
            return new DirStateFactory.Result(null,
                                              personToAttributes((Person)obj));
        } else {
            return null;
        }
    }

    @Override
    public Object getObjectInstance(Object obj, Name name, Context ctx,
        Hashtable environment) throws Exception {

        return null;
    }

    @Override
    public Object getObjectInstance(Object obj, Name name, Context ctx,
        Hashtable environment, Attributes attributes) throws Exception {

        if (obj instanceof DirContext) {
            ((DirContext)obj).close(); // cleanup
            return attributesToPerson(attributes);

        } else {
            return null;
        }
    }

    private Attributes personToAttributes(Person person) {

        // TBD: build attrs using methods in Person class instead of calling ...

        return person.getAttributes();
    }

    private Person attributesToPerson(Attributes attrset)
        throws NamingException {

        Attributes attrs = (Attributes)attrset;
        Person person = new Person((String)attrs.get("cn").get(),
                                   (String)attrs.get("sn").get());

        person.setMailAddress((String)attrs.get("mail").get());

        // TBD: extract any other attributes

        return person;
    }
}