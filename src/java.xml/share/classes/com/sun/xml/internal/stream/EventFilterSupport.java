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

package java.xml.share.classes.com.sun.xml.internal.stream;


import java.util.NoSuchElementException;
import javax.xml.stream.EventFilter;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.util.EventReaderDelegate;















/**
 *
 *
 */
public class EventFilterSupport extends EventReaderDelegate {

    //maintain a reference to EventFilter
    EventFilter fEventFilter ;
    /** Creates a new instance of EventFilterSupport */
    public EventFilterSupport(XMLEventReader eventReader, EventFilter eventFilter) {
        setParent(eventReader);
        fEventFilter = eventFilter;
    }

    public Object next(){
        try{
            return nextEvent();
        }catch(XMLStreamException ex){
            throw new NoSuchElementException();
        }
    }

    public boolean hasNext(){
        try{
            return peek() != null;
        }catch(XMLStreamException ex){
            return false;
        }
    }

    public XMLEvent nextEvent()throws XMLStreamException{
        while (super.hasNext()) {
            //get the next event by calling XMLEventReader
            XMLEvent event = super.nextEvent();

            //if this filter accepts this event then return this event
            if(fEventFilter.accept(event)){
                return event;
            }
        }
        throw new NoSuchElementException();
    }//nextEvent()

     public XMLEvent nextTag() throws XMLStreamException{
         while (super.hasNext()) {
             XMLEvent event = super.nextTag();
             //if the filter accepts this event return this event.
             if(fEventFilter.accept(event)){
                return event;
             }
         }
         throw new NoSuchElementException();
     }

     public XMLEvent peek() throws XMLStreamException{
         while (true) {
             XMLEvent event = super.peek();
             if(event == null)return null;
             //if the filter accepts this event return this event.
             if(fEventFilter.accept(event)){
                return event;
             }
             //call super.next(), and then peek again.
             super.next();
         }
     }

}//EventFilterSupport
