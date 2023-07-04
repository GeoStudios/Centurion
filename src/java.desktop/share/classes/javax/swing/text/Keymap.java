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
package javax.swing.text;

import javax.swing.Action;
import javax.swing.KeyStroke;

/**
 * A collection of bindings of KeyStrokes to actions.  The
 * bindings are basically name-value pairs that potentially
 * resolve in a hierarchy.
 *
 */
public interface Keymap {

    /**
     * Fetches the name of the set of key-bindings.
     *
     * @return the name
     */
    String getName();

    /**
     * Fetches the default action to fire if a
     * key is typed (i.e. a KEY_TYPED KeyEvent is received)
     * and there is no binding for it.  Typically this
     * would be some action that inserts text so that
     * the keymap doesn't require an action for each
     * possible key.
     *
     * @return the default action
     */
    Action getDefaultAction();

    /**
     * Set the default action to fire if a key is typed.
     *
     * @param a the action
     */
    void setDefaultAction(Action a);

    /**
     * Fetches the action appropriate for the given symbolic
     * event sequence.  This is used by JTextController to
     * determine how to interpret key sequences.  If the
     * binding is not resolved locally, an attempt is made
     * to resolve through the parent keymap, if one is set.
     *
     * @param key the key sequence
     * @return  the action associated with the key
     *  sequence if one is defined, otherwise <code>null</code>
     */
    Action getAction(KeyStroke key);

    /**
     * Fetches all of the keystrokes in this map that
     * are bound to some action.
     *
     * @return the list of keystrokes
     */
    KeyStroke[] getBoundKeyStrokes();

    /**
     * Fetches all of the actions defined in this keymap.
     *
     * @return the list of actions
     */
    Action[] getBoundActions();

    /**
     * Fetches the keystrokes that will result in
     * the given action.
     *
     * @param a the action
     * @return the list of keystrokes
     */
    KeyStroke[] getKeyStrokesForAction(Action a);

    /**
     * Determines if the given key sequence is locally defined.
     *
     * @param key the key sequence
     * @return true if the key sequence is locally defined else false
     */
    boolean isLocallyDefined(KeyStroke key);

    /**
     * Adds a binding to the keymap.
     *
     * @param key the key sequence
     * @param a the action
     */
    void addActionForKeyStroke(KeyStroke key, Action a);

    /**
     * Removes a binding from the keymap.
     *
     * @param keys the key sequence
     */
    void removeKeyStrokeBinding(KeyStroke keys);

    /**
     * Removes all bindings from the keymap.
     */
    void removeBindings();

    /**
     * Fetches the parent keymap used to resolve key-bindings.
     *
     * @return the keymap
     */
    Keymap getResolveParent();

    /**
     * Sets the parent keymap, which will be used to
     * resolve key-bindings.
     * The behavior is unspecified if a {@code Keymap} has itself
     * as one of its resolve parents.
     *
     * @param parent the parent keymap
     */
    void setResolveParent(Keymap parent);

}
