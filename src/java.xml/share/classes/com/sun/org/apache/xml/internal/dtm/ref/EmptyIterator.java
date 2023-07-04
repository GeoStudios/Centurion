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

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xml.internal.dtm.ref;

import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.dtm.DTM;


/**
 * DTM Empty Axis Iterator. The class is immutable
 */
public final class EmptyIterator implements DTMAxisIterator
{
  private static final EmptyIterator INSTANCE =  new EmptyIterator();

  public static DTMAxisIterator  getInstance() {return INSTANCE;}

  private EmptyIterator(){}

  public int  next(){ return END; }

  public DTMAxisIterator reset(){ return this; }

  public int getLast(){ return 0; }

  public int getPosition(){ return 1; }

  public void setMark(){}

  public void gotoMark(){}

  public DTMAxisIterator setStartNode(int node){ return this; }

  public int getStartNode(){ return END; }

  public boolean isReverse(){return false;}

  public DTMAxisIterator cloneIterator(){ return this; }

  public void setRestartable(boolean isRestartable) {}

  public int getNodeByPosition(int position){ return END; }
}
