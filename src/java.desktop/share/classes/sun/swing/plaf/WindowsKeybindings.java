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

package java.desktop.share.classes.sun.swing.plaf;


import javax.swing.JTextField;
import javax.swing.UIDefaults;
import javax.swing.text.DefaultEditorKit;















/**
 * WindowsKeybindings - The standard set of keymaps for the Windows Platform
 *
 */
public class WindowsKeybindings {

    /**
     * Install all Windows keybindings into the provided UIDefaults table
     *
     * @param table The UiDefaults table to install into
     */
    public static void installKeybindings(UIDefaults table) {
        // *** Text
        Object fieldInputMap = new UIDefaults.LazyInputMap(new Object[]{
                "control C", DefaultEditorKit.copyAction,
                "control V", DefaultEditorKit.pasteAction,
                "control X", DefaultEditorKit.cutAction,
                "COPY", DefaultEditorKit.copyAction,
                "PASTE", DefaultEditorKit.pasteAction,
                "CUT", DefaultEditorKit.cutAction,
                "control INSERT", DefaultEditorKit.copyAction,
                "shift INSERT", DefaultEditorKit.pasteAction,
                "shift DELETE", DefaultEditorKit.cutAction,
                "control A", DefaultEditorKit.selectAllAction,
                "control BACK_SLASH", "unselect"/*DefaultEditorKit.unselectAction*/,
                "shift LEFT", DefaultEditorKit.selectionBackwardAction,
                "shift RIGHT", DefaultEditorKit.selectionForwardAction,
                "control LEFT", DefaultEditorKit.previousWordAction,
                "control RIGHT", DefaultEditorKit.nextWordAction,
                "control shift LEFT", DefaultEditorKit.selectionPreviousWordAction,
                "control shift RIGHT", DefaultEditorKit.selectionNextWordAction,
                "HOME", DefaultEditorKit.beginLineAction,
                "END", DefaultEditorKit.endLineAction,
                "shift HOME", DefaultEditorKit.selectionBeginLineAction,
                "shift END", DefaultEditorKit.selectionEndLineAction,
                "BACK_SPACE", DefaultEditorKit.deletePrevCharAction,
                "shift BACK_SPACE", DefaultEditorKit.deletePrevCharAction,
                "ctrl H", DefaultEditorKit.deletePrevCharAction,
                "DELETE", DefaultEditorKit.deleteNextCharAction,
                "ctrl DELETE", DefaultEditorKit.deleteNextWordAction,
                "ctrl BACK_SPACE", DefaultEditorKit.deletePrevWordAction,
                "RIGHT", DefaultEditorKit.forwardAction,
                "LEFT", DefaultEditorKit.backwardAction,
                "KP_RIGHT", DefaultEditorKit.forwardAction,
                "KP_LEFT", DefaultEditorKit.backwardAction,
                "ENTER", JTextField.notifyAction,
                "control shift O", "toggle-componentOrientation"/*DefaultEditorKit.toggleComponentOrientation*/
        });
        Object passwordInputMap = new UIDefaults.LazyInputMap(new Object[]{
                "control C", DefaultEditorKit.copyAction,
                "control V", DefaultEditorKit.pasteAction,
                "control X", DefaultEditorKit.cutAction,
                "COPY", DefaultEditorKit.copyAction,
                "PASTE", DefaultEditorKit.pasteAction,
                "CUT", DefaultEditorKit.cutAction,
                "control INSERT", DefaultEditorKit.copyAction,
                "shift INSERT", DefaultEditorKit.pasteAction,
                "shift DELETE", DefaultEditorKit.cutAction,
                "control A", DefaultEditorKit.selectAllAction,
                "control BACK_SLASH", "unselect"/*DefaultEditorKit.unselectAction*/,
                "shift LEFT", DefaultEditorKit.selectionBackwardAction,
                "shift RIGHT", DefaultEditorKit.selectionForwardAction,
                "control LEFT", DefaultEditorKit.beginLineAction,
                "control RIGHT", DefaultEditorKit.endLineAction,
                "control shift LEFT", DefaultEditorKit.selectionBeginLineAction,
                "control shift RIGHT", DefaultEditorKit.selectionEndLineAction,
                "HOME", DefaultEditorKit.beginLineAction,
                "END", DefaultEditorKit.endLineAction,
                "shift HOME", DefaultEditorKit.selectionBeginLineAction,
                "shift END", DefaultEditorKit.selectionEndLineAction,
                "BACK_SPACE", DefaultEditorKit.deletePrevCharAction,
                "shift BACK_SPACE", DefaultEditorKit.deletePrevCharAction,
                "ctrl H", DefaultEditorKit.deletePrevCharAction,
                "DELETE", DefaultEditorKit.deleteNextCharAction,
                "RIGHT", DefaultEditorKit.forwardAction,
                "LEFT", DefaultEditorKit.backwardAction,
                "KP_RIGHT", DefaultEditorKit.forwardAction,
                "KP_LEFT", DefaultEditorKit.backwardAction,
                "ENTER", JTextField.notifyAction,
                "control shift O", "toggle-componentOrientation"/*DefaultEditorKit.toggleComponentOrientation*/
        });
        Object multilineInputMap = new UIDefaults.LazyInputMap(new Object[]{
                "control C", DefaultEditorKit.copyAction,
                "control V", DefaultEditorKit.pasteAction,
                "control X", DefaultEditorKit.cutAction,
                "COPY", DefaultEditorKit.copyAction,
                "PASTE", DefaultEditorKit.pasteAction,
                "CUT", DefaultEditorKit.cutAction,
                "control INSERT", DefaultEditorKit.copyAction,
                "shift INSERT", DefaultEditorKit.pasteAction,
                "shift DELETE", DefaultEditorKit.cutAction,
                "shift LEFT", DefaultEditorKit.selectionBackwardAction,
                "shift RIGHT", DefaultEditorKit.selectionForwardAction,
                "control LEFT", DefaultEditorKit.previousWordAction,
                "control RIGHT", DefaultEditorKit.nextWordAction,
                "control shift LEFT", DefaultEditorKit.selectionPreviousWordAction,
                "control shift RIGHT", DefaultEditorKit.selectionNextWordAction,
                "control A", DefaultEditorKit.selectAllAction,
                "control BACK_SLASH", "unselect"/*DefaultEditorKit.unselectAction*/,
                "HOME", DefaultEditorKit.beginLineAction,
                "END", DefaultEditorKit.endLineAction,
                "shift HOME", DefaultEditorKit.selectionBeginLineAction,
                "shift END", DefaultEditorKit.selectionEndLineAction,
                "control HOME", DefaultEditorKit.beginAction,
                "control END", DefaultEditorKit.endAction,
                "control shift HOME", DefaultEditorKit.selectionBeginAction,
                "control shift END", DefaultEditorKit.selectionEndAction,
                "UP", DefaultEditorKit.upAction,
                "DOWN", DefaultEditorKit.downAction,
                "BACK_SPACE", DefaultEditorKit.deletePrevCharAction,
                "shift BACK_SPACE", DefaultEditorKit.deletePrevCharAction,
                "ctrl H", DefaultEditorKit.deletePrevCharAction,
                "DELETE", DefaultEditorKit.deleteNextCharAction,
                "ctrl DELETE", DefaultEditorKit.deleteNextWordAction,
                "ctrl BACK_SPACE", DefaultEditorKit.deletePrevWordAction,
                "RIGHT", DefaultEditorKit.forwardAction,
                "LEFT", DefaultEditorKit.backwardAction,
                "KP_RIGHT", DefaultEditorKit.forwardAction,
                "KP_LEFT", DefaultEditorKit.backwardAction,
                "PAGE_UP", DefaultEditorKit.pageUpAction,
                "PAGE_DOWN", DefaultEditorKit.pageDownAction,
                "shift PAGE_UP", "selection-page-up",
                "shift PAGE_DOWN", "selection-page-down",
                "ctrl shift PAGE_UP", "selection-page-left",
                "ctrl shift PAGE_DOWN", "selection-page-right",
                "shift UP", DefaultEditorKit.selectionUpAction,
                "shift DOWN", DefaultEditorKit.selectionDownAction,
                "ENTER", DefaultEditorKit.insertBreakAction,
                "TAB", DefaultEditorKit.insertTabAction,
                "control T", "next-link-action",
                "control shift T", "previous-link-action",
                "control SPACE", "activate-link-action",
                "control shift O", "toggle-componentOrientation"/*DefaultEditorKit.toggleComponentOrientation*/
        });
        Object[] defaults = {
                "TextField.focusInputMap", fieldInputMap,
                "PasswordField.focusInputMap", passwordInputMap,
                "TextArea.focusInputMap", multilineInputMap,
                "TextPane.focusInputMap", multilineInputMap,
                "EditorPane.focusInputMap", multilineInputMap,
                "Button.focusInputMap",
                new UIDefaults.LazyInputMap(new Object[]{
                        "SPACE", "pressed",
                        "released SPACE", "released"
                }),
                "CheckBox.focusInputMap",
                new UIDefaults.LazyInputMap(new Object[]{
                        "SPACE", "pressed",
                        "released SPACE", "released"
                }),
                "ComboBox.ancestorInputMap", new UIDefaults.LazyInputMap(new Object[]{
                        "ESCAPE", "hidePopup",
                        "PAGE_UP", "pageUpPassThrough",
                        "PAGE_DOWN", "pageDownPassThrough",
                        "HOME", "homePassThrough",
                        "END", "endPassThrough",
                        "DOWN", "selectNext2",
                        "KP_DOWN", "selectNext2",
                        "UP", "selectPrevious2",
                        "KP_UP", "selectPrevious2",
                        "ENTER", "enterPressed",
                        "F4", "togglePopup",
                        "alt DOWN", "togglePopup",
                        "alt KP_DOWN", "togglePopup",
                        "alt UP", "togglePopup",
                        "alt KP_UP", "togglePopup"
                }),
                "Desktop.ancestorInputMap",
                new UIDefaults.LazyInputMap(new Object[]{
                        "ctrl F5", "restore",
                        "ctrl F4", "close",
                        "ctrl F7", "move",
                        "ctrl F8", "resize",
                        "RIGHT", "right",
                        "KP_RIGHT", "right",
                        "LEFT", "left",
                        "KP_LEFT", "left",
                        "UP", "up",
                        "KP_UP", "up",
                        "DOWN", "down",
                        "KP_DOWN", "down",
                        "ESCAPE", "escape",
                        "ctrl F9", "minimize",
                        "ctrl F10", "maximize",
                        "ctrl F6", "selectNextFrame",
                        "ctrl TAB", "selectNextFrame",
                        "ctrl alt F6", "selectNextFrame",
                        "shift ctrl alt F6", "selectPreviousFrame",
                        "ctrl F12", "navigateNext",
                        "shift ctrl F12", "navigatePrevious"
                }),
                "FileChooser.ancestorInputMap",
                new UIDefaults.LazyInputMap(new Object[]{
                        "ESCAPE", "cancelSelection",
                        "F2", "editFileName",
                        "F5", "refresh",
                        "BACK_SPACE", "Go Up",
                        "ENTER", "approveSelection",
                        "ctrl ENTER", "approveSelection"
                }),
                "InternalFrame.windowBindings", new Object[]{
                        "shift ESCAPE", "showSystemMenu",
                        "ctrl SPACE", "showSystemMenu",
                        "ESCAPE", "hideSystemMenu"
                },
                "List.focusInputMap",
                new UIDefaults.LazyInputMap(new Object[]{
                        "ctrl C", "copy",
                        "ctrl V", "paste",
                        "ctrl X", "cut",
                        "COPY", "copy",
                        "PASTE", "paste",
                        "CUT", "cut",
                        "control INSERT", "copy",
                        "shift INSERT", "paste",
                        "shift DELETE", "cut",
                        "UP", "selectPreviousRow",
                        "KP_UP", "selectPreviousRow",
                        "shift UP", "selectPreviousRowExtendSelection",
                        "shift KP_UP", "selectPreviousRowExtendSelection",
                        "ctrl shift UP", "selectPreviousRowExtendSelection",
                        "ctrl shift KP_UP", "selectPreviousRowExtendSelection",
                        "ctrl UP", "selectPreviousRowChangeLead",
                        "ctrl KP_UP", "selectPreviousRowChangeLead",
                        "DOWN", "selectNextRow",
                        "KP_DOWN", "selectNextRow",
                        "shift DOWN", "selectNextRowExtendSelection",
                        "shift KP_DOWN", "selectNextRowExtendSelection",
                        "ctrl shift DOWN", "selectNextRowExtendSelection",
                        "ctrl shift KP_DOWN", "selectNextRowExtendSelection",
                        "ctrl DOWN", "selectNextRowChangeLead",
                        "ctrl KP_DOWN", "selectNextRowChangeLead",
                        "LEFT", "selectPreviousColumn",
                        "KP_LEFT", "selectPreviousColumn",
                        "shift LEFT", "selectPreviousColumnExtendSelection",
                        "shift KP_LEFT", "selectPreviousColumnExtendSelection",
                        "ctrl shift LEFT", "selectPreviousColumnExtendSelection",
                        "ctrl shift KP_LEFT", "selectPreviousColumnExtendSelection",
                        "ctrl LEFT", "selectPreviousColumnChangeLead",
                        "ctrl KP_LEFT", "selectPreviousColumnChangeLead",
                        "RIGHT", "selectNextColumn",
                        "KP_RIGHT", "selectNextColumn",
                        "shift RIGHT", "selectNextColumnExtendSelection",
                        "shift KP_RIGHT", "selectNextColumnExtendSelection",
                        "ctrl shift RIGHT", "selectNextColumnExtendSelection",
                        "ctrl shift KP_RIGHT", "selectNextColumnExtendSelection",
                        "ctrl RIGHT", "selectNextColumnChangeLead",
                        "ctrl KP_RIGHT", "selectNextColumnChangeLead",
                        "HOME", "selectFirstRow",
                        "shift HOME", "selectFirstRowExtendSelection",
                        "ctrl shift HOME", "selectFirstRowExtendSelection",
                        "ctrl HOME", "selectFirstRowChangeLead",
                        "END", "selectLastRow",
                        "shift END", "selectLastRowExtendSelection",
                        "ctrl shift END", "selectLastRowExtendSelection",
                        "ctrl END", "selectLastRowChangeLead",
                        "PAGE_UP", "scrollUp",
                        "shift PAGE_UP", "scrollUpExtendSelection",
                        "ctrl shift PAGE_UP", "scrollUpExtendSelection",
                        "ctrl PAGE_UP", "scrollUpChangeLead",
                        "PAGE_DOWN", "scrollDown",
                        "shift PAGE_DOWN", "scrollDownExtendSelection",
                        "ctrl shift PAGE_DOWN", "scrollDownExtendSelection",
                        "ctrl PAGE_DOWN", "scrollDownChangeLead",
                        "ctrl A", "selectAll",
                        "ctrl SLASH", "selectAll",
                        "ctrl BACK_SLASH", "clearSelection",
                        "SPACE", "addToSelection",
                        "ctrl SPACE", "toggleAndAnchor",
                        "shift SPACE", "extendTo",
                        "ctrl shift SPACE", "moveSelectionTo"
                }),
                "MenuBar.windowBindings", new Object[]{
                        "F10", "takeFocus"
                },
                "RadioButton.focusInputMap",
                new UIDefaults.LazyInputMap(new Object[]{
                        "SPACE", "pressed",
                        "released SPACE", "released"
                }),
                "OptionPane.windowBindings", new Object[]{
                        "ESCAPE", "close"
                },
                "FormattedTextField.focusInputMap",
                new UIDefaults.LazyInputMap(new Object[]{
                        "ctrl C", DefaultEditorKit.copyAction,
                        "ctrl V", DefaultEditorKit.pasteAction,
                        "ctrl X", DefaultEditorKit.cutAction,
                        "COPY", DefaultEditorKit.copyAction,
                        "PASTE", DefaultEditorKit.pasteAction,
                        "CUT", DefaultEditorKit.cutAction,
                        "control INSERT", DefaultEditorKit.copyAction,
                        "shift INSERT", DefaultEditorKit.pasteAction,
                        "shift DELETE", DefaultEditorKit.cutAction,
                        "shift LEFT", DefaultEditorKit.selectionBackwardAction,
                        "shift KP_LEFT", DefaultEditorKit.selectionBackwardAction,
                        "shift RIGHT", DefaultEditorKit.selectionForwardAction,
                        "shift KP_RIGHT", DefaultEditorKit.selectionForwardAction,
                        "ctrl LEFT", DefaultEditorKit.previousWordAction,
                        "ctrl KP_LEFT", DefaultEditorKit.previousWordAction,
                        "ctrl RIGHT", DefaultEditorKit.nextWordAction,
                        "ctrl KP_RIGHT", DefaultEditorKit.nextWordAction,
                        "ctrl shift LEFT", DefaultEditorKit.selectionPreviousWordAction,
                        "ctrl shift KP_LEFT", DefaultEditorKit.selectionPreviousWordAction,
                        "ctrl shift RIGHT", DefaultEditorKit.selectionNextWordAction,
                        "ctrl shift KP_RIGHT", DefaultEditorKit.selectionNextWordAction,
                        "ctrl A", DefaultEditorKit.selectAllAction,
                        "HOME", DefaultEditorKit.beginLineAction,
                        "END", DefaultEditorKit.endLineAction,
                        "shift HOME", DefaultEditorKit.selectionBeginLineAction,
                        "shift END", DefaultEditorKit.selectionEndLineAction,
                        "BACK_SPACE", DefaultEditorKit.deletePrevCharAction,
                        "shift BACK_SPACE", DefaultEditorKit.deletePrevCharAction,
                        "ctrl H", DefaultEditorKit.deletePrevCharAction,
                        "DELETE", DefaultEditorKit.deleteNextCharAction,
                        "ctrl DELETE", DefaultEditorKit.deleteNextWordAction,
                        "ctrl BACK_SPACE", DefaultEditorKit.deletePrevWordAction,
                        "RIGHT", DefaultEditorKit.forwardAction,
                        "LEFT", DefaultEditorKit.backwardAction,
                        "KP_RIGHT", DefaultEditorKit.forwardAction,
                        "KP_LEFT", DefaultEditorKit.backwardAction,
                        "ENTER", JTextField.notifyAction,
                        "ctrl BACK_SLASH", "unselect",
                        "control shift O", "toggle-componentOrientation",
                        "ESCAPE", "reset-field-edit",
                        "UP", "increment",
                        "KP_UP", "increment",
                        "DOWN", "decrement",
                        "KP_DOWN", "decrement",
                }),
                "RootPane.ancestorInputMap",
                new UIDefaults.LazyInputMap(new Object[]{
                        "shift F10", "postPopup",
                        "CONTEXT_MENU", "postPopup"
                }),
                // These bindings are only enabled when there is a default
                // button set on the rootpane.
                "RootPane.defaultButtonWindowKeyBindings", new Object[]{
                        "ENTER", "press",
                        "released ENTER", "release",
                        "ctrl ENTER", "press",
                        "ctrl released ENTER", "release"
                },
                "ScrollBar.ancestorInputMap",
                new UIDefaults.LazyInputMap(new Object[]{
                        "RIGHT", "positiveUnitIncrement",
                        "KP_RIGHT", "positiveUnitIncrement",
                        "DOWN", "positiveUnitIncrement",
                        "KP_DOWN", "positiveUnitIncrement",
                        "PAGE_DOWN", "positiveBlockIncrement",
                        "ctrl PAGE_DOWN", "positiveBlockIncrement",
                        "LEFT", "negativeUnitIncrement",
                        "KP_LEFT", "negativeUnitIncrement",
                        "UP", "negativeUnitIncrement",
                        "KP_UP", "negativeUnitIncrement",
                        "PAGE_UP", "negativeBlockIncrement",
                        "ctrl PAGE_UP", "negativeBlockIncrement",
                        "HOME", "minScroll",
                        "END", "maxScroll"
                }),
                "ScrollPane.ancestorInputMap",
                new UIDefaults.LazyInputMap(new Object[]{
                        "RIGHT", "unitScrollRight",
                        "KP_RIGHT", "unitScrollRight",
                        "DOWN", "unitScrollDown",
                        "KP_DOWN", "unitScrollDown",
                        "LEFT", "unitScrollLeft",
                        "KP_LEFT", "unitScrollLeft",
                        "UP", "unitScrollUp",
                        "KP_UP", "unitScrollUp",
                        "PAGE_UP", "scrollUp",
                        "PAGE_DOWN", "scrollDown",
                        "ctrl PAGE_UP", "scrollLeft",
                        "ctrl PAGE_DOWN", "scrollRight",
                        "ctrl HOME", "scrollHome",
                        "ctrl END", "scrollEnd"
                }),
                "Slider.focusInputMap",
                new UIDefaults.LazyInputMap(new Object[]{
                        "RIGHT", "positiveUnitIncrement",
                        "KP_RIGHT", "positiveUnitIncrement",
                        "DOWN", "negativeUnitIncrement",
                        "KP_DOWN", "negativeUnitIncrement",
                        "PAGE_DOWN", "negativeBlockIncrement",
                        "LEFT", "negativeUnitIncrement",
                        "KP_LEFT", "negativeUnitIncrement",
                        "UP", "positiveUnitIncrement",
                        "KP_UP", "positiveUnitIncrement",
                        "PAGE_UP", "positiveBlockIncrement",
                        "HOME", "minScroll",
                        "END", "maxScroll"
                }),
                "Spinner.ancestorInputMap",
                new UIDefaults.LazyInputMap(new Object[]{
                        "UP", "increment",
                        "KP_UP", "increment",
                        "DOWN", "decrement",
                        "KP_DOWN", "decrement",
                }),
                "SplitPane.ancestorInputMap",
                new UIDefaults.LazyInputMap(new Object[]{
                        "UP", "negativeIncrement",
                        "DOWN", "positiveIncrement",
                        "LEFT", "negativeIncrement",
                        "RIGHT", "positiveIncrement",
                        "KP_UP", "negativeIncrement",
                        "KP_DOWN", "positiveIncrement",
                        "KP_LEFT", "negativeIncrement",
                        "KP_RIGHT", "positiveIncrement",
                        "HOME", "selectMin",
                        "END", "selectMax",
                        "F8", "startResize",
                        "F6", "toggleFocus",
                        "ctrl TAB", "focusOutForward",
                        "ctrl shift TAB", "focusOutBackward"
                }),
                "TabbedPane.focusInputMap",
                new UIDefaults.LazyInputMap(new Object[]{
                        "RIGHT", "navigateRight",
                        "KP_RIGHT", "navigateRight",
                        "LEFT", "navigateLeft",
                        "KP_LEFT", "navigateLeft",
                        "UP", "navigateUp",
                        "KP_UP", "navigateUp",
                        "DOWN", "navigateDown",
                        "KP_DOWN", "navigateDown",
                        "ctrl DOWN", "requestFocusForVisibleComponent",
                        "ctrl KP_DOWN", "requestFocusForVisibleComponent",
                }),
                "TabbedPane.ancestorInputMap",
                new UIDefaults.LazyInputMap(new Object[]{
                        "ctrl TAB", "navigateNext",
                        "ctrl shift TAB", "navigatePrevious",
                        "ctrl PAGE_DOWN", "navigatePageDown",
                        "ctrl PAGE_UP", "navigatePageUp",
                        "ctrl UP", "requestFocus",
                        "ctrl KP_UP", "requestFocus",
                }),
                "TableHeader.ancestorInputMap",
                   new UIDefaults.LazyInputMap(new Object[] {
                                    "SPACE", "toggleSortOrder",
                                     "LEFT", "selectColumnToLeft",
                                  "KP_LEFT", "selectColumnToLeft",
                                    "RIGHT", "selectColumnToRight",
                                 "KP_RIGHT", "selectColumnToRight",
                                 "alt LEFT", "moveColumnLeft",
                              "alt KP_LEFT", "moveColumnLeft",
                                "alt RIGHT", "moveColumnRight",
                             "alt KP_RIGHT", "moveColumnRight",
                           "alt shift LEFT", "resizeLeft",
                        "alt shift KP_LEFT", "resizeLeft",
                          "alt shift RIGHT", "resizeRight",
                       "alt shift KP_RIGHT", "resizeRight",
                                   "ESCAPE", "focusTable",
                   }),
                "Table.ancestorInputMap",
                new UIDefaults.LazyInputMap(new Object[]{
                        "ctrl C", "copy",
                        "ctrl V", "paste",
                        "ctrl X", "cut",
                        "COPY", "copy",
                        "PASTE", "paste",
                        "CUT", "cut",
                        "control INSERT", "copy",
                        "shift INSERT", "paste",
                        "shift DELETE", "cut",
                        "RIGHT", "selectNextColumn",
                        "KP_RIGHT", "selectNextColumn",
                        "shift RIGHT", "selectNextColumnExtendSelection",
                        "shift KP_RIGHT", "selectNextColumnExtendSelection",
                        "ctrl shift RIGHT", "selectNextColumnExtendSelection",
                        "ctrl shift KP_RIGHT", "selectNextColumnExtendSelection",
                        "ctrl RIGHT", "selectNextColumnChangeLead",
                        "ctrl KP_RIGHT", "selectNextColumnChangeLead",
                        "LEFT", "selectPreviousColumn",
                        "KP_LEFT", "selectPreviousColumn",
                        "shift LEFT", "selectPreviousColumnExtendSelection",
                        "shift KP_LEFT", "selectPreviousColumnExtendSelection",
                        "ctrl shift LEFT", "selectPreviousColumnExtendSelection",
                        "ctrl shift KP_LEFT", "selectPreviousColumnExtendSelection",
                        "ctrl LEFT", "selectPreviousColumnChangeLead",
                        "ctrl KP_LEFT", "selectPreviousColumnChangeLead",
                        "DOWN", "selectNextRow",
                        "KP_DOWN", "selectNextRow",
                        "shift DOWN", "selectNextRowExtendSelection",
                        "shift KP_DOWN", "selectNextRowExtendSelection",
                        "ctrl shift DOWN", "selectNextRowExtendSelection",
                        "ctrl shift KP_DOWN", "selectNextRowExtendSelection",
                        "ctrl DOWN", "selectNextRowChangeLead",
                        "ctrl KP_DOWN", "selectNextRowChangeLead",
                        "UP", "selectPreviousRow",
                        "KP_UP", "selectPreviousRow",
                        "shift UP", "selectPreviousRowExtendSelection",
                        "shift KP_UP", "selectPreviousRowExtendSelection",
                        "ctrl shift UP", "selectPreviousRowExtendSelection",
                        "ctrl shift KP_UP", "selectPreviousRowExtendSelection",
                        "ctrl UP", "selectPreviousRowChangeLead",
                        "ctrl KP_UP", "selectPreviousRowChangeLead",
                        "HOME", "selectFirstColumn",
                        "shift HOME", "selectFirstColumnExtendSelection",
                        "ctrl shift HOME", "selectFirstRowExtendSelection",
                        "ctrl HOME", "selectFirstRow",
                        "END", "selectLastColumn",
                        "shift END", "selectLastColumnExtendSelection",
                        "ctrl shift END", "selectLastRowExtendSelection",
                        "ctrl END", "selectLastRow",
                        "PAGE_UP", "scrollUpChangeSelection",
                        "shift PAGE_UP", "scrollUpExtendSelection",
                        "ctrl shift PAGE_UP", "scrollLeftExtendSelection",
                        "ctrl PAGE_UP", "scrollLeftChangeSelection",
                        "PAGE_DOWN", "scrollDownChangeSelection",
                        "shift PAGE_DOWN", "scrollDownExtendSelection",
                        "ctrl shift PAGE_DOWN", "scrollRightExtendSelection",
                        "ctrl PAGE_DOWN", "scrollRightChangeSelection",
                        "TAB", "selectNextColumnCell",
                        "shift TAB", "selectPreviousColumnCell",
                        "ENTER", "selectNextRowCell",
                        "shift ENTER", "selectPreviousRowCell",
                        "ctrl A", "selectAll",
                        "ctrl SLASH", "selectAll",
                        "ctrl BACK_SLASH", "clearSelection",
                        "ESCAPE", "cancel",
                        "F2", "startEditing",
                        "SPACE", "addToSelection",
                        "ctrl SPACE", "toggleAndAnchor",
                        "shift SPACE", "extendTo",
                        "ctrl shift SPACE", "moveSelectionTo",
                        "F8", "focusHeader"
                }),
                "ToggleButton.focusInputMap",
                new UIDefaults.LazyInputMap(new Object[]{
                        "SPACE", "pressed",
                        "released SPACE", "released"
                }),
                "ToolBar.ancestorInputMap",
                new UIDefaults.LazyInputMap(new Object[]{
                        "UP", "navigateUp",
                        "KP_UP", "navigateUp",
                        "DOWN", "navigateDown",
                        "KP_DOWN", "navigateDown",
                        "LEFT", "navigateLeft",
                        "KP_LEFT", "navigateLeft",
                        "RIGHT", "navigateRight",
                        "KP_RIGHT", "navigateRight"
                }),
                "Tree.focusInputMap",
                new UIDefaults.LazyInputMap(new Object[]{
                        "ADD", "expand",
                        "SUBTRACT", "collapse",
                        "ctrl C", "copy",
                        "ctrl V", "paste",
                        "ctrl X", "cut",
                        "COPY", "copy",
                        "PASTE", "paste",
                        "CUT", "cut",
                        "control INSERT", "copy",
                        "shift INSERT", "paste",
                        "shift DELETE", "cut",
                        "UP", "selectPrevious",
                        "KP_UP", "selectPrevious",
                        "shift UP", "selectPreviousExtendSelection",
                        "shift KP_UP", "selectPreviousExtendSelection",
                        "ctrl shift UP", "selectPreviousExtendSelection",
                        "ctrl shift KP_UP", "selectPreviousExtendSelection",
                        "ctrl UP", "selectPreviousChangeLead",
                        "ctrl KP_UP", "selectPreviousChangeLead",
                        "DOWN", "selectNext",
                        "KP_DOWN", "selectNext",
                        "shift DOWN", "selectNextExtendSelection",
                        "shift KP_DOWN", "selectNextExtendSelection",
                        "ctrl shift DOWN", "selectNextExtendSelection",
                        "ctrl shift KP_DOWN", "selectNextExtendSelection",
                        "ctrl DOWN", "selectNextChangeLead",
                        "ctrl KP_DOWN", "selectNextChangeLead",
                        "RIGHT", "selectChild",
                        "KP_RIGHT", "selectChild",
                        "LEFT", "selectParent",
                        "KP_LEFT", "selectParent",
                        "PAGE_UP", "scrollUpChangeSelection",
                        "shift PAGE_UP", "scrollUpExtendSelection",
                        "ctrl shift PAGE_UP", "scrollUpExtendSelection",
                        "ctrl PAGE_UP", "scrollUpChangeLead",
                        "PAGE_DOWN", "scrollDownChangeSelection",
                        "shift PAGE_DOWN", "scrollDownExtendSelection",
                        "ctrl shift PAGE_DOWN", "scrollDownExtendSelection",
                        "ctrl PAGE_DOWN", "scrollDownChangeLead",
                        "HOME", "selectFirst",
                        "shift HOME", "selectFirstExtendSelection",
                        "ctrl shift HOME", "selectFirstExtendSelection",
                        "ctrl HOME", "selectFirstChangeLead",
                        "END", "selectLast",
                        "shift END", "selectLastExtendSelection",
                        "ctrl shift END", "selectLastExtendSelection",
                        "ctrl END", "selectLastChangeLead",
                        "F2", "startEditing",
                        "ctrl A", "selectAll",
                        "ctrl SLASH", "selectAll",
                        "ctrl BACK_SLASH", "clearSelection",
                        "ctrl LEFT", "scrollLeft",
                        "ctrl KP_LEFT", "scrollLeft",
                        "ctrl RIGHT", "scrollRight",
                        "ctrl KP_RIGHT", "scrollRight",
                        "SPACE", "addToSelection",
                        "ctrl SPACE", "toggleAndAnchor",
                        "shift SPACE", "extendTo",
                        "ctrl shift SPACE", "moveSelectionTo"
                }),
                "Tree.ancestorInputMap",
                new UIDefaults.LazyInputMap(new Object[]{
                        "ESCAPE", "cancel"
                }),
        };
        table.putDefaults(defaults);
    }

}
