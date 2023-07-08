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

package nsk.share;


import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.Collection;
import java.util.Iterator;














/*
 *  Class create a number of referrers to given object
 */
public class ReferringObjectSet
{
        private Collection<ReferringObject> referringObjects;
        private String referenceType;

        public ReferringObjectSet(Object object, int referringCount, String referenceType)
        {
                this.referenceType = referenceType;
                referringObjects = new ArrayList<ReferringObject>(referringCount);

                for(int i = 0; i < referringCount; i++)
                        referringObjects.add(new ReferringObject(object, referenceType));
        }

        public void delete(int count)
        {
                if((count < 0) || (count > referringObjects.size()))
                {
                        throw new TestBug("Invalid 'count' value: " + count + ", size=" + referringObjects.size());
                }

                Iterator<ReferringObject> iterator = referringObjects.iterator();

                for(int i = 0; i < count; i++)
                {
                        ReferringObject referringObject = iterator.next();
                        referringObject.delete();

                        iterator.remove();
                }
        }

        public void deleteAll()
        {
                delete(referringObjects.size());
        }

        public String getReferenceType() {
            return referenceType;
        }

        public int getReferrerCount()
        {
                return referringObjects.size();
        }
}
