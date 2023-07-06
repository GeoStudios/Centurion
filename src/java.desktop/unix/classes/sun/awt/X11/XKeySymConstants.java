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

package java.desktop.unix.classes.sun.awt.X11;

public interface XKeySymConstants {
    long XK_VoidSymbol = 0xFFFFFF ; /* void symbol */

    /*
     * TTY Functions, cleverly chosen to map to ascii, for convenience of
     * programming, but could have been arbitrary (at the cost of lookup
     * tables in client code.
     */

    long XK_BackSpace = 0xFF08 ; /* back space, back char */
    long XK_Tab = 0xFF09 ;
    long XK_Linefeed = 0xFF0A ; /* Linefeed, LF */
    long XK_Clear = 0xFF0B ;
    long XK_Return = 0xFF0D ; /* Return, enter */
    long XK_Pause = 0xFF13 ; /* Pause, hold */
    long XK_Scroll_Lock = 0xFF14 ;
    long XK_Sys_Req = 0xFF15 ;
    long XK_Escape = 0xFF1B ;
    long XK_Delete = 0xFFFF ; /* Delete, rubout */

    /* International & multi-key character composition */

    long XK_Multi_key = 0xFF20 ; /* Multi-key character compose */
    long XK_Codeinput = 0xFF37 ;
    long XK_SingleCandidate = 0xFF3C ;
    long XK_MultipleCandidate = 0xFF3D ;
    long XK_PreviousCandidate = 0xFF3E ;

    /* Japanese keyboard support */

    long XK_Kanji = 0xFF21 ; /* Kanji, Kanji convert */
    long XK_Muhenkan = 0xFF22 ; /* Cancel Conversion */
    long XK_Henkan_Mode = 0xFF23 ; /* Start/Stop Conversion */
    long XK_Henkan = 0xFF23 ; /* Alias for Henkan_Mode */
    long XK_Romaji = 0xFF24 ; /* to Romaji */
    long XK_Hiragana = 0xFF25 ; /* to Hiragana */
    long XK_Katakana = 0xFF26 ; /* to Katakana */
    long XK_Hiragana_Katakana = 0xFF27 ; /* Hiragana/Katakana toggle */
    long XK_Zenkaku = 0xFF28 ; /* to Zenkaku */
    long XK_Hankaku = 0xFF29 ; /* to Hankaku */
    long XK_Zenkaku_Hankaku = 0xFF2A ; /* Zenkaku/Hankaku toggle */
    long XK_Touroku = 0xFF2B ; /* Add to Dictionary */
    long XK_Massyo = 0xFF2C ; /* Delete from Dictionary */
    long XK_Kana_Lock = 0xFF2D ; /* Kana Lock */
    long XK_Kana_Shift = 0xFF2E ; /* Kana Shift */
    long XK_Eisu_Shift = 0xFF2F ; /* Alphanumeric Shift */
    long XK_Eisu_toggle = 0xFF30 ; /* Alphanumeric toggle */
    long XK_Kanji_Bangou = 0xFF37 ; /* Codeinput */
    long XK_Zen_Koho = 0xFF3D ; /* Multiple/All Candidate(s) */
    long XK_Mae_Koho = 0xFF3E ; /* Previous Candidate */

    /* 0xFF31 thru 0xFF3F are under XK_KOREAN */

    /* Cursor control & motion */

    long XK_Home = 0xFF50 ;
    long XK_Left = 0xFF51 ; /* Move left, left arrow */
    long XK_Up = 0xFF52 ; /* Move up, up arrow */
    long XK_Right = 0xFF53 ; /* Move right, right arrow */
    long XK_Down = 0xFF54 ; /* Move down, down arrow */
    long XK_Prior = 0xFF55 ; /* Prior, previous */
    long XK_Page_Up = 0xFF55 ;
    long XK_Next = 0xFF56 ; /* Next */
    long XK_Page_Down = 0xFF56 ;
    long XK_End = 0xFF57 ; /* EOL */
    long XK_Begin = 0xFF58 ; /* BOL */

    /* Misc Functions */

    long XK_Select = 0xFF60 ; /* Select, mark */
    long XK_Print = 0xFF61 ;
    long XK_Execute = 0xFF62 ; /* Execute, run, do */
    long XK_Insert = 0xFF63 ; /* Insert, insert here */
    long XK_Undo = 0xFF65 ; /* Undo, oops */
    long XK_Redo = 0xFF66 ; /* redo, again */
    long XK_Menu = 0xFF67 ;
    long XK_Find = 0xFF68 ; /* Find, search */
    long XK_Cancel = 0xFF69 ; /* Cancel, stop, abort, exit */
    long XK_Help = 0xFF6A ; /* Help */
    long XK_Break = 0xFF6B ;
    long XK_Mode_switch = 0xFF7E ; /* Character set switch */
    long XK_script_switch = 0xFF7E ; /* Alias for mode_switch */
    long XK_Num_Lock = 0xFF7F ;

    /* Keypad Functions, keypad numbers cleverly chosen to map to ascii */

    long XK_KP_Space = 0xFF80 ; /* space */
    long XK_KP_Tab = 0xFF89 ;
    long XK_KP_Enter = 0xFF8D ; /* enter */
    long XK_KP_F1 = 0xFF91 ; /* PF1, KP_A, ... */
    long XK_KP_F2 = 0xFF92 ;
    long XK_KP_F3 = 0xFF93 ;
    long XK_KP_F4 = 0xFF94 ;
    long XK_KP_Home = 0xFF95 ;
    long XK_KP_Left = 0xFF96 ;
    long XK_KP_Up = 0xFF97 ;
    long XK_KP_Right = 0xFF98 ;
    long XK_KP_Down = 0xFF99 ;
    long XK_KP_Prior = 0xFF9A ;
    long XK_KP_Page_Up = 0xFF9A ;
    long XK_KP_Next = 0xFF9B ;
    long XK_KP_Page_Down = 0xFF9B ;
    long XK_KP_End = 0xFF9C ;
    long XK_KP_Begin = 0xFF9D ;
    long XK_KP_Insert = 0xFF9E ;
    long XK_KP_Delete = 0xFF9F ;
    long XK_KP_Equal = 0xFFBD ; /* equals */
    long XK_KP_Multiply = 0xFFAA ;
    long XK_KP_Add = 0xFFAB ;
    long XK_KP_Separator = 0xFFAC ; /* separator, often comma */
    long XK_KP_Subtract = 0xFFAD ;
    long XK_KP_Decimal = 0xFFAE ;
    long XK_KP_Divide = 0xFFAF ;

    long XK_KP_0 = 0xFFB0 ;
    long XK_KP_1 = 0xFFB1 ;
    long XK_KP_2 = 0xFFB2 ;
    long XK_KP_3 = 0xFFB3 ;
    long XK_KP_4 = 0xFFB4 ;
    long XK_KP_5 = 0xFFB5 ;
    long XK_KP_6 = 0xFFB6 ;
    long XK_KP_7 = 0xFFB7 ;
    long XK_KP_8 = 0xFFB8 ;
    long XK_KP_9 = 0xFFB9 ;

    /*
     * Auxilliary Functions; note the duplicate definitions for left and right
     * function keys;  Sun keyboards and a few other manufactures have such
     * function key groups on the left and/or right sides of the keyboard.
     * We've not found a keyboard with more than 35 function keys total.
     */

    long XK_F1 = 0xFFBE ;
    long XK_F2 = 0xFFBF ;
    long XK_F3 = 0xFFC0 ;
    long XK_F4 = 0xFFC1 ;
    long XK_F5 = 0xFFC2 ;
    long XK_F6 = 0xFFC3 ;
    long XK_F7 = 0xFFC4 ;
    long XK_F8 = 0xFFC5 ;
    long XK_F9 = 0xFFC6 ;
    long XK_F10 = 0xFFC7 ;
    long XK_F11 = 0xFFC8 ;
    long XK_L1 = 0xFFC8 ;
    long XK_F12 = 0xFFC9 ;
    long XK_L2 = 0xFFC9 ;
    long XK_F13 = 0xFFCA ;
    long XK_L3 = 0xFFCA ;
    long XK_F14 = 0xFFCB ;
    long XK_L4 = 0xFFCB ;
    long XK_F15 = 0xFFCC ;
    long XK_L5 = 0xFFCC ;
    long XK_F16 = 0xFFCD ;
    long XK_L6 = 0xFFCD ;
    long XK_F17 = 0xFFCE ;
    long XK_L7 = 0xFFCE ;
    long XK_F18 = 0xFFCF ;
    long XK_L8 = 0xFFCF ;
    long XK_F19 = 0xFFD0 ;
    long XK_L9 = 0xFFD0 ;
    long XK_F20 = 0xFFD1 ;
    long XK_L10 = 0xFFD1 ;
    long XK_F21 = 0xFFD2 ;
    long XK_R1 = 0xFFD2 ;
    long XK_F22 = 0xFFD3 ;
    long XK_R2 = 0xFFD3 ;
    long XK_F23 = 0xFFD4 ;
    long XK_R3 = 0xFFD4 ;
    long XK_F24 = 0xFFD5 ;
    long XK_R4 = 0xFFD5 ;
    long XK_F25 = 0xFFD6 ;
    long XK_R5 = 0xFFD6 ;
    long XK_F26 = 0xFFD7 ;
    long XK_R6 = 0xFFD7 ;
    long XK_F27 = 0xFFD8 ;
    long XK_R7 = 0xFFD8 ;
    long XK_F28 = 0xFFD9 ;
    long XK_R8 = 0xFFD9 ;
    long XK_F29 = 0xFFDA ;
    long XK_R9 = 0xFFDA ;
    long XK_F30 = 0xFFDB ;
    long XK_R10 = 0xFFDB ;
    long XK_F31 = 0xFFDC ;
    long XK_R11 = 0xFFDC ;
    long XK_F32 = 0xFFDD ;
    long XK_R12 = 0xFFDD ;
    long XK_F33 = 0xFFDE ;
    long XK_R13 = 0xFFDE ;
    long XK_F34 = 0xFFDF ;
    long XK_R14 = 0xFFDF ;
    long XK_F35 = 0xFFE0 ;
    long XK_R15 = 0xFFE0 ;

    /* Modifiers */

    long XK_Shift_L = 0xFFE1 ; /* Left shift */
    long XK_Shift_R = 0xFFE2 ; /* Right shift */
    long XK_Control_L = 0xFFE3 ; /* Left control */
    long XK_Control_R = 0xFFE4 ; /* Right control */
    long XK_Caps_Lock = 0xFFE5 ; /* Caps lock */
    long XK_Shift_Lock = 0xFFE6 ; /* Shift lock */

    long XK_Meta_L = 0xFFE7 ; /* Left meta */
    long XK_Meta_R = 0xFFE8 ; /* Right meta */
    long XK_Alt_L = 0xFFE9 ; /* Left alt */
    long XK_Alt_R = 0xFFEA ; /* Right alt */
    long XK_Super_L = 0xFFEB ; /* Left super */
    long XK_Super_R = 0xFFEC ; /* Right super */
    long XK_Hyper_L = 0xFFED ; /* Left hyper */
    long XK_Hyper_R = 0xFFEE ; /* Right hyper */

    /*
     * ISO 9995 Function and Modifier Keys
     * Byte 3 = 0xFE
     */

    long XK_ISO_Lock = 0xFE01 ;
    long XK_ISO_Level2_Latch = 0xFE02 ;
    long XK_ISO_Level3_Shift = 0xFE03 ;
    long XK_ISO_Level3_Latch = 0xFE04 ;
    long XK_ISO_Level3_Lock = 0xFE05 ;
    long XK_ISO_Group_Shift = 0xFF7E ; /* Alias for mode_switch */
    long XK_ISO_Group_Latch = 0xFE06 ;
    long XK_ISO_Group_Lock = 0xFE07 ;
    long XK_ISO_Next_Group = 0xFE08 ;
    long XK_ISO_Next_Group_Lock = 0xFE09 ;
    long XK_ISO_Prev_Group = 0xFE0A ;
    long XK_ISO_Prev_Group_Lock = 0xFE0B ;
    long XK_ISO_First_Group = 0xFE0C ;
    long XK_ISO_First_Group_Lock = 0xFE0D ;
    long XK_ISO_Last_Group = 0xFE0E ;
    long XK_ISO_Last_Group_Lock = 0xFE0F ;

    long XK_ISO_Left_Tab = 0xFE20 ;
    long XK_ISO_Move_Line_Up = 0xFE21 ;
    long XK_ISO_Move_Line_Down = 0xFE22 ;
    long XK_ISO_Partial_Line_Up = 0xFE23 ;
    long XK_ISO_Partial_Line_Down = 0xFE24 ;
    long XK_ISO_Partial_Space_Left = 0xFE25 ;
    long XK_ISO_Partial_Space_Right = 0xFE26 ;
    long XK_ISO_Set_Margin_Left = 0xFE27 ;
    long XK_ISO_Set_Margin_Right = 0xFE28 ;
    long XK_ISO_Release_Margin_Left = 0xFE29 ;
    long XK_ISO_Release_Margin_Right = 0xFE2A ;
    long XK_ISO_Release_Both_Margins = 0xFE2B ;
    long XK_ISO_Fast_Cursor_Left = 0xFE2C ;
    long XK_ISO_Fast_Cursor_Right = 0xFE2D ;
    long XK_ISO_Fast_Cursor_Up = 0xFE2E ;
    long XK_ISO_Fast_Cursor_Down = 0xFE2F ;
    long XK_ISO_Continuous_Underline = 0xFE30 ;
    long XK_ISO_Discontinuous_Underline = 0xFE31 ;
    long XK_ISO_Emphasize = 0xFE32 ;
    long XK_ISO_Center_Object = 0xFE33 ;
    long XK_ISO_Enter = 0xFE34 ;

    long XK_dead_grave = 0xFE50 ;
    long XK_dead_acute = 0xFE51 ;
    long XK_dead_circumflex = 0xFE52 ;
    long XK_dead_tilde = 0xFE53 ;
    long XK_dead_macron = 0xFE54 ;
    long XK_dead_breve = 0xFE55 ;
    long XK_dead_abovedot = 0xFE56 ;
    long XK_dead_diaeresis = 0xFE57 ;
    long XK_dead_abovering = 0xFE58 ;
    long XK_dead_doubleacute = 0xFE59 ;
    long XK_dead_caron = 0xFE5A ;
    long XK_dead_cedilla = 0xFE5B ;
    long XK_dead_ogonek = 0xFE5C ;
    long XK_dead_iota = 0xFE5D ;
    long XK_dead_voiced_sound = 0xFE5E ;
    long XK_dead_semivoiced_sound = 0xFE5F ;
    long XK_dead_belowdot = 0xFE60 ;

    long XK_First_Virtual_Screen = 0xFED0 ;
    long XK_Prev_Virtual_Screen = 0xFED1 ;
    long XK_Next_Virtual_Screen = 0xFED2 ;
    long XK_Last_Virtual_Screen = 0xFED4 ;
    long XK_Terminate_Server = 0xFED5 ;

    long XK_AccessX_Enable = 0xFE70 ;
    long XK_AccessX_Feedback_Enable = 0xFE71 ;
    long XK_RepeatKeys_Enable = 0xFE72 ;
    long XK_SlowKeys_Enable = 0xFE73 ;
    long XK_BounceKeys_Enable = 0xFE74 ;
    long XK_StickyKeys_Enable = 0xFE75 ;
    long XK_MouseKeys_Enable = 0xFE76 ;
    long XK_MouseKeys_Accel_Enable = 0xFE77 ;
    long XK_Overlay1_Enable = 0xFE78 ;
    long XK_Overlay2_Enable = 0xFE79 ;
    long XK_AudibleBell_Enable = 0xFE7A ;

    long XK_Pointer_Left = 0xFEE0 ;
    long XK_Pointer_Right = 0xFEE1 ;
    long XK_Pointer_Up = 0xFEE2 ;
    long XK_Pointer_Down = 0xFEE3 ;
    long XK_Pointer_UpLeft = 0xFEE4 ;
    long XK_Pointer_UpRight = 0xFEE5 ;
    long XK_Pointer_DownLeft = 0xFEE6 ;
    long XK_Pointer_DownRight = 0xFEE7 ;
    long XK_Pointer_Button_Dflt = 0xFEE8 ;
    long XK_Pointer_Button1 = 0xFEE9 ;
    long XK_Pointer_Button2 = 0xFEEA ;
    long XK_Pointer_Button3 = 0xFEEB ;
    long XK_Pointer_Button4 = 0xFEEC ;
    long XK_Pointer_Button5 = 0xFEED ;
    long XK_Pointer_DblClick_Dflt = 0xFEEE ;
    long XK_Pointer_DblClick1 = 0xFEEF ;
    long XK_Pointer_DblClick2 = 0xFEF0 ;
    long XK_Pointer_DblClick3 = 0xFEF1 ;
    long XK_Pointer_DblClick4 = 0xFEF2 ;
    long XK_Pointer_DblClick5 = 0xFEF3 ;
    long XK_Pointer_Drag_Dflt = 0xFEF4 ;
    long XK_Pointer_Drag1 = 0xFEF5 ;
    long XK_Pointer_Drag2 = 0xFEF6 ;
    long XK_Pointer_Drag3 = 0xFEF7 ;
    long XK_Pointer_Drag4 = 0xFEF8 ;
    long XK_Pointer_Drag5 = 0xFEFD ;

    long XK_Pointer_EnableKeys = 0xFEF9 ;
    long XK_Pointer_Accelerate = 0xFEFA ;
    long XK_Pointer_DfltBtnNext = 0xFEFB ;
    long XK_Pointer_DfltBtnPrev = 0xFEFC ;

    /*
     * 3270 Terminal Keys
     * Byte 3 = 0xFD
     */

    long XK_3270_Duplicate = 0xFD01 ;
    long XK_3270_FieldMark = 0xFD02 ;
    long XK_3270_Right2 = 0xFD03 ;
    long XK_3270_Left2 = 0xFD04 ;
    long XK_3270_BackTab = 0xFD05 ;
    long XK_3270_EraseEOF = 0xFD06 ;
    long XK_3270_EraseInput = 0xFD07 ;
    long XK_3270_Reset = 0xFD08 ;
    long XK_3270_Quit = 0xFD09 ;
    long XK_3270_PA1 = 0xFD0A ;
    long XK_3270_PA2 = 0xFD0B ;
    long XK_3270_PA3 = 0xFD0C ;
    long XK_3270_Test = 0xFD0D ;
    long XK_3270_Attn = 0xFD0E ;
    long XK_3270_CursorBlink = 0xFD0F ;
    long XK_3270_AltCursor = 0xFD10 ;
    long XK_3270_KeyClick = 0xFD11 ;
    long XK_3270_Jump = 0xFD12 ;
    long XK_3270_Ident = 0xFD13 ;
    long XK_3270_Rule = 0xFD14 ;
    long XK_3270_Copy = 0xFD15 ;
    long XK_3270_Play = 0xFD16 ;
    long XK_3270_Setup = 0xFD17 ;
    long XK_3270_Record = 0xFD18 ;
    long XK_3270_ChangeScreen = 0xFD19 ;
    long XK_3270_DeleteWord = 0xFD1A ;
    long XK_3270_ExSelect = 0xFD1B ;
    long XK_3270_CursorSelect = 0xFD1C ;
    long XK_3270_PrintScreen = 0xFD1D ;
    long XK_3270_Enter = 0xFD1E ;

    /*
     *  Latin 1
     *  Byte 3 = 0
     */
    long XK_space = 0x020 ;
    long XK_exclam = 0x021 ;
    long XK_quotedbl = 0x022 ;
    long XK_numbersign = 0x023 ;
    long XK_dollar = 0x024 ;
    long XK_percent = 0x025 ;
    long XK_ampersand = 0x026 ;
    long XK_apostrophe = 0x027 ;
    long XK_quoteright = 0x027 ; /* deprecated */
    long XK_parenleft = 0x028 ;
    long XK_parenright = 0x029 ;
    long XK_asterisk = 0x02a ;
    long XK_plus = 0x02b ;
    long XK_comma = 0x02c ;
    long XK_minus = 0x02d ;
    long XK_period = 0x02e ;
    long XK_slash = 0x02f ;
    long XK_0 = 0x030 ;
    long XK_1 = 0x031 ;
    long XK_2 = 0x032 ;
    long XK_3 = 0x033 ;
    long XK_4 = 0x034 ;
    long XK_5 = 0x035 ;
    long XK_6 = 0x036 ;
    long XK_7 = 0x037 ;
    long XK_8 = 0x038 ;
    long XK_9 = 0x039 ;
    long XK_colon = 0x03a ;
    long XK_semicolon = 0x03b ;
    long XK_less = 0x03c ;
    long XK_equal = 0x03d ;
    long XK_greater = 0x03e ;
    long XK_question = 0x03f ;
    long XK_at = 0x040 ;
    long XK_A = 0x041 ;
    long XK_B = 0x042 ;
    long XK_C = 0x043 ;
    long XK_D = 0x044 ;
    long XK_E = 0x045 ;
    long XK_F = 0x046 ;
    long XK_G = 0x047 ;
    long XK_H = 0x048 ;
    long XK_I = 0x049 ;
    long XK_J = 0x04a ;
    long XK_K = 0x04b ;
    long XK_L = 0x04c ;
    long XK_M = 0x04d ;
    long XK_N = 0x04e ;
    long XK_O = 0x04f ;
    long XK_P = 0x050 ;
    long XK_Q = 0x051 ;
    long XK_R = 0x052 ;
    long XK_S = 0x053 ;
    long XK_T = 0x054 ;
    long XK_U = 0x055 ;
    long XK_V = 0x056 ;
    long XK_W = 0x057 ;
    long XK_X = 0x058 ;
    long XK_Y = 0x059 ;
    long XK_Z = 0x05a ;
    long XK_bracketleft = 0x05b ;
    long XK_backslash = 0x05c ;
    long XK_bracketright = 0x05d ;
    long XK_asciicircum = 0x05e ;
    long XK_underscore = 0x05f ;
    long XK_grave = 0x060 ;
    long XK_quoteleft = 0x060 ; /* deprecated */
    long XK_a = 0x061 ;
    long XK_b = 0x062 ;
    long XK_c = 0x063 ;
    long XK_d = 0x064 ;
    long XK_e = 0x065 ;
    long XK_f = 0x066 ;
    long XK_g = 0x067 ;
    long XK_h = 0x068 ;
    long XK_i = 0x069 ;
    long XK_j = 0x06a ;
    long XK_k = 0x06b ;
    long XK_l = 0x06c ;
    long XK_m = 0x06d ;
    long XK_n = 0x06e ;
    long XK_o = 0x06f ;
    long XK_p = 0x070 ;
    long XK_q = 0x071 ;
    long XK_r = 0x072 ;
    long XK_s = 0x073 ;
    long XK_t = 0x074 ;
    long XK_u = 0x075 ;
    long XK_v = 0x076 ;
    long XK_w = 0x077 ;
    long XK_x = 0x078 ;
    long XK_y = 0x079 ;
    long XK_z = 0x07a ;
    long XK_braceleft = 0x07b ;
    long XK_bar = 0x07c ;
    long XK_braceright = 0x07d ;
    long XK_asciitilde = 0x07e ;

    long XK_nobreakspace = 0x0a0 ;
    long XK_exclamdown = 0x0a1 ;
    long XK_cent = 0x0a2 ;
    long XK_sterling = 0x0a3 ;
    long XK_currency = 0x0a4 ;
    long XK_yen = 0x0a5 ;
    long XK_brokenbar = 0x0a6 ;
    long XK_section = 0x0a7 ;
    long XK_diaeresis = 0x0a8 ;
    long XK_copyright = 0x0a9 ;
    long XK_ordfeminine = 0x0aa ;
    long XK_guillemotleft = 0x0ab ; /* left angle quotation mark */
    long XK_notsign = 0x0ac ;
    long XK_hyphen = 0x0ad ;
    long XK_registered = 0x0ae ;
    long XK_macron = 0x0af ;
    long XK_degree = 0x0b0 ;
    long XK_plusminus = 0x0b1 ;
    long XK_twosuperior = 0x0b2 ;
    long XK_threesuperior = 0x0b3 ;
    long XK_acute = 0x0b4 ;
    long XK_mu = 0x0b5 ;
    long XK_paragraph = 0x0b6 ;
    long XK_periodcentered = 0x0b7 ;
    long XK_cedilla = 0x0b8 ;
    long XK_onesuperior = 0x0b9 ;
    long XK_masculine = 0x0ba ;
    long XK_guillemotright = 0x0bb ; /* right angle quotation mark */
    long XK_onequarter = 0x0bc ;
    long XK_onehalf = 0x0bd ;
    long XK_threequarters = 0x0be ;
    long XK_questiondown = 0x0bf ;
    long XK_Agrave = 0x0c0 ;
    long XK_Aacute = 0x0c1 ;
    long XK_Acircumflex = 0x0c2 ;
    long XK_Atilde = 0x0c3 ;
    long XK_Adiaeresis = 0x0c4 ;
    long XK_Aring = 0x0c5 ;
    long XK_AE = 0x0c6 ;
    long XK_Ccedilla = 0x0c7 ;
    long XK_Egrave = 0x0c8 ;
    long XK_Eacute = 0x0c9 ;
    long XK_Ecircumflex = 0x0ca ;
    long XK_Ediaeresis = 0x0cb ;
    long XK_Igrave = 0x0cc ;
    long XK_Iacute = 0x0cd ;
    long XK_Icircumflex = 0x0ce ;
    long XK_Idiaeresis = 0x0cf ;
    long XK_ETH = 0x0d0 ;
    long XK_Eth = 0x0d0 ; /* deprecated */
    long XK_Ntilde = 0x0d1 ;
    long XK_Ograve = 0x0d2 ;
    long XK_Oacute = 0x0d3 ;
    long XK_Ocircumflex = 0x0d4 ;
    long XK_Otilde = 0x0d5 ;
    long XK_Odiaeresis = 0x0d6 ;
    long XK_multiply = 0x0d7 ;
    long XK_Ooblique = 0x0d8 ;
    long XK_Ugrave = 0x0d9 ;
    long XK_Uacute = 0x0da ;
    long XK_Ucircumflex = 0x0db ;
    long XK_Udiaeresis = 0x0dc ;
    long XK_Yacute = 0x0dd ;
    long XK_THORN = 0x0de ;
    long XK_Thorn = 0x0de ; /* deprecated */
    long XK_ssharp = 0x0df ;
    long XK_agrave = 0x0e0 ;
    long XK_aacute = 0x0e1 ;
    long XK_acircumflex = 0x0e2 ;
    long XK_atilde = 0x0e3 ;
    long XK_adiaeresis = 0x0e4 ;
    long XK_aring = 0x0e5 ;
    long XK_ae = 0x0e6 ;
    long XK_ccedilla = 0x0e7 ;
    long XK_egrave = 0x0e8 ;
    long XK_eacute = 0x0e9 ;
    long XK_ecircumflex = 0x0ea ;
    long XK_ediaeresis = 0x0eb ;
    long XK_igrave = 0x0ec ;
    long XK_iacute = 0x0ed ;
    long XK_icircumflex = 0x0ee ;
    long XK_idiaeresis = 0x0ef ;
    long XK_eth = 0x0f0 ;
    long XK_ntilde = 0x0f1 ;
    long XK_ograve = 0x0f2 ;
    long XK_oacute = 0x0f3 ;
    long XK_ocircumflex = 0x0f4 ;
    long XK_otilde = 0x0f5 ;
    long XK_odiaeresis = 0x0f6 ;
    long XK_division = 0x0f7 ;
    long XK_oslash = 0x0f8 ;
    long XK_ugrave = 0x0f9 ;
    long XK_uacute = 0x0fa ;
    long XK_ucircumflex = 0x0fb ;
    long XK_udiaeresis = 0x0fc ;
    long XK_yacute = 0x0fd ;
    long XK_thorn = 0x0fe ;
    long XK_ydiaeresis = 0x0ff ;

    /*
     *   Latin 2
     *   Byte 3 = 1
     */

    long XK_Aogonek = 0x1a1 ;
    long XK_breve = 0x1a2 ;
    long XK_Lstroke = 0x1a3 ;
    long XK_Lcaron = 0x1a5 ;
    long XK_Sacute = 0x1a6 ;
    long XK_Scaron = 0x1a9 ;
    long XK_Scedilla = 0x1aa ;
    long XK_Tcaron = 0x1ab ;
    long XK_Zacute = 0x1ac ;
    long XK_Zcaron = 0x1ae ;
    long XK_Zabovedot = 0x1af ;
    long XK_aogonek = 0x1b1 ;
    long XK_ogonek = 0x1b2 ;
    long XK_lstroke = 0x1b3 ;
    long XK_lcaron = 0x1b5 ;
    long XK_sacute = 0x1b6 ;
    long XK_caron = 0x1b7 ;
    long XK_scaron = 0x1b9 ;
    long XK_scedilla = 0x1ba ;
    long XK_tcaron = 0x1bb ;
    long XK_zacute = 0x1bc ;
    long XK_doubleacute = 0x1bd ;
    long XK_zcaron = 0x1be ;
    long XK_zabovedot = 0x1bf ;
    long XK_Racute = 0x1c0 ;
    long XK_Abreve = 0x1c3 ;
    long XK_Lacute = 0x1c5 ;
    long XK_Cacute = 0x1c6 ;
    long XK_Ccaron = 0x1c8 ;
    long XK_Eogonek = 0x1ca ;
    long XK_Ecaron = 0x1cc ;
    long XK_Dcaron = 0x1cf ;
    long XK_Dstroke = 0x1d0 ;
    long XK_Nacute = 0x1d1 ;
    long XK_Ncaron = 0x1d2 ;
    long XK_Odoubleacute = 0x1d5 ;
    long XK_Rcaron = 0x1d8 ;
    long XK_Uring = 0x1d9 ;
    long XK_Udoubleacute = 0x1db ;
    long XK_Tcedilla = 0x1de ;
    long XK_racute = 0x1e0 ;
    long XK_abreve = 0x1e3 ;
    long XK_lacute = 0x1e5 ;
    long XK_cacute = 0x1e6 ;
    long XK_ccaron = 0x1e8 ;
    long XK_eogonek = 0x1ea ;
    long XK_ecaron = 0x1ec ;
    long XK_dcaron = 0x1ef ;
    long XK_dstroke = 0x1f0 ;
    long XK_nacute = 0x1f1 ;
    long XK_ncaron = 0x1f2 ;
    long XK_odoubleacute = 0x1f5 ;
    long XK_udoubleacute = 0x1fb ;
    long XK_rcaron = 0x1f8 ;
    long XK_uring = 0x1f9 ;
    long XK_tcedilla = 0x1fe ;
    long XK_abovedot = 0x1ff ;

    /*
     *   Latin 3
     *   Byte 3 = 2
     */

    long XK_Hstroke = 0x2a1 ;
    long XK_Hcircumflex = 0x2a6 ;
    long XK_Iabovedot = 0x2a9 ;
    long XK_Gbreve = 0x2ab ;
    long XK_Jcircumflex = 0x2ac ;
    long XK_hstroke = 0x2b1 ;
    long XK_hcircumflex = 0x2b6 ;
    long XK_idotless = 0x2b9 ;
    long XK_gbreve = 0x2bb ;
    long XK_jcircumflex = 0x2bc ;
    long XK_Cabovedot = 0x2c5 ;
    long XK_Ccircumflex = 0x2c6 ;
    long XK_Gabovedot = 0x2d5 ;
    long XK_Gcircumflex = 0x2d8 ;
    long XK_Ubreve = 0x2dd ;
    long XK_Scircumflex = 0x2de ;
    long XK_cabovedot = 0x2e5 ;
    long XK_ccircumflex = 0x2e6 ;
    long XK_gabovedot = 0x2f5 ;
    long XK_gcircumflex = 0x2f8 ;
    long XK_ubreve = 0x2fd ;
    long XK_scircumflex = 0x2fe ;

    /*
     *   Latin 4
     *   Byte 3 = 3
     */

    long XK_kra = 0x3a2 ;
    long XK_kappa = 0x3a2 ; /* deprecated */
    long XK_Rcedilla = 0x3a3 ;
    long XK_Itilde = 0x3a5 ;
    long XK_Lcedilla = 0x3a6 ;
    long XK_Emacron = 0x3aa ;
    long XK_Gcedilla = 0x3ab ;
    long XK_Tslash = 0x3ac ;
    long XK_rcedilla = 0x3b3 ;
    long XK_itilde = 0x3b5 ;
    long XK_lcedilla = 0x3b6 ;
    long XK_emacron = 0x3ba ;
    long XK_gcedilla = 0x3bb ;
    long XK_tslash = 0x3bc ;
    long XK_ENG = 0x3bd ;
    long XK_eng = 0x3bf ;
    long XK_Amacron = 0x3c0 ;
    long XK_Iogonek = 0x3c7 ;
    long XK_Eabovedot = 0x3cc ;
    long XK_Imacron = 0x3cf ;
    long XK_Ncedilla = 0x3d1 ;
    long XK_Omacron = 0x3d2 ;
    long XK_Kcedilla = 0x3d3 ;
    long XK_Uogonek = 0x3d9 ;
    long XK_Utilde = 0x3dd ;
    long XK_Umacron = 0x3de ;
    long XK_amacron = 0x3e0 ;
    long XK_iogonek = 0x3e7 ;
    long XK_eabovedot = 0x3ec ;
    long XK_imacron = 0x3ef ;
    long XK_ncedilla = 0x3f1 ;
    long XK_omacron = 0x3f2 ;
    long XK_kcedilla = 0x3f3 ;
    long XK_uogonek = 0x3f9 ;
    long XK_utilde = 0x3fd ;
    long XK_umacron = 0x3fe ;

    /*
     * Latin-9 (a.k.a. Latin-0)
     * Byte 3 = 19
     */

    long XK_OE = 0x13bc ;
    long XK_oe = 0x13bd ;
    long XK_Ydiaeresis = 0x13be ;

    /*
     * Katakana
     * Byte 3 = 4
     */

    long XK_overline = 0x47e ;
    long XK_kana_fullstop = 0x4a1 ;
    long XK_kana_openingbracket = 0x4a2 ;
    long XK_kana_closingbracket = 0x4a3 ;
    long XK_kana_comma = 0x4a4 ;
    long XK_kana_conjunctive = 0x4a5 ;
    long XK_kana_middledot = 0x4a5 ; /* deprecated */
    long XK_kana_WO = 0x4a6 ;
    long XK_kana_a = 0x4a7 ;
    long XK_kana_i = 0x4a8 ;
    long XK_kana_u = 0x4a9 ;
    long XK_kana_e = 0x4aa ;
    long XK_kana_o = 0x4ab ;
    long XK_kana_ya = 0x4ac ;
    long XK_kana_yu = 0x4ad ;
    long XK_kana_yo = 0x4ae ;
    long XK_kana_tsu = 0x4af ;
    long XK_kana_tu = 0x4af ; /* deprecated */
    long XK_prolongedsound = 0x4b0 ;
    long XK_kana_A = 0x4b1 ;
    long XK_kana_I = 0x4b2 ;
    long XK_kana_U = 0x4b3 ;
    long XK_kana_E = 0x4b4 ;
    long XK_kana_O = 0x4b5 ;
    long XK_kana_KA = 0x4b6 ;
    long XK_kana_KI = 0x4b7 ;
    long XK_kana_KU = 0x4b8 ;
    long XK_kana_KE = 0x4b9 ;
    long XK_kana_KO = 0x4ba ;
    long XK_kana_SA = 0x4bb ;
    long XK_kana_SHI = 0x4bc ;
    long XK_kana_SU = 0x4bd ;
    long XK_kana_SE = 0x4be ;
    long XK_kana_SO = 0x4bf ;
    long XK_kana_TA = 0x4c0 ;
    long XK_kana_CHI = 0x4c1 ;
    long XK_kana_TI = 0x4c1 ; /* deprecated */
    long XK_kana_TSU = 0x4c2 ;
    long XK_kana_TU = 0x4c2 ; /* deprecated */
    long XK_kana_TE = 0x4c3 ;
    long XK_kana_TO = 0x4c4 ;
    long XK_kana_NA = 0x4c5 ;
    long XK_kana_NI = 0x4c6 ;
    long XK_kana_NU = 0x4c7 ;
    long XK_kana_NE = 0x4c8 ;
    long XK_kana_NO = 0x4c9 ;
    long XK_kana_HA = 0x4ca ;
    long XK_kana_HI = 0x4cb ;
    long XK_kana_FU = 0x4cc ;
    long XK_kana_HU = 0x4cc ; /* deprecated */
    long XK_kana_HE = 0x4cd ;
    long XK_kana_HO = 0x4ce ;
    long XK_kana_MA = 0x4cf ;
    long XK_kana_MI = 0x4d0 ;
    long XK_kana_MU = 0x4d1 ;
    long XK_kana_ME = 0x4d2 ;
    long XK_kana_MO = 0x4d3 ;
    long XK_kana_YA = 0x4d4 ;
    long XK_kana_YU = 0x4d5 ;
    long XK_kana_YO = 0x4d6 ;
    long XK_kana_RA = 0x4d7 ;
    long XK_kana_RI = 0x4d8 ;
    long XK_kana_RU = 0x4d9 ;
    long XK_kana_RE = 0x4da ;
    long XK_kana_RO = 0x4db ;
    long XK_kana_WA = 0x4dc ;
    long XK_kana_N = 0x4dd ;
    long XK_voicedsound = 0x4de ;
    long XK_semivoicedsound = 0x4df ;
    long XK_kana_switch = 0xFF7E ; /* Alias for mode_switch */

    /*
     *  Arabic
     *  Byte 3 = 5
     */

    long XK_Arabic_comma = 0x5ac ;
    long XK_Arabic_semicolon = 0x5bb ;
    long XK_Arabic_question_mark = 0x5bf ;
    long XK_Arabic_hamza = 0x5c1 ;
    long XK_Arabic_maddaonalef = 0x5c2 ;
    long XK_Arabic_hamzaonalef = 0x5c3 ;
    long XK_Arabic_hamzaonwaw = 0x5c4 ;
    long XK_Arabic_hamzaunderalef = 0x5c5 ;
    long XK_Arabic_hamzaonyeh = 0x5c6 ;
    long XK_Arabic_alef = 0x5c7 ;
    long XK_Arabic_beh = 0x5c8 ;
    long XK_Arabic_tehmarbuta = 0x5c9 ;
    long XK_Arabic_teh = 0x5ca ;
    long XK_Arabic_theh = 0x5cb ;
    long XK_Arabic_jeem = 0x5cc ;
    long XK_Arabic_hah = 0x5cd ;
    long XK_Arabic_khah = 0x5ce ;
    long XK_Arabic_dal = 0x5cf ;
    long XK_Arabic_thal = 0x5d0 ;
    long XK_Arabic_ra = 0x5d1 ;
    long XK_Arabic_zain = 0x5d2 ;
    long XK_Arabic_seen = 0x5d3 ;
    long XK_Arabic_sheen = 0x5d4 ;
    long XK_Arabic_sad = 0x5d5 ;
    long XK_Arabic_dad = 0x5d6 ;
    long XK_Arabic_tah = 0x5d7 ;
    long XK_Arabic_zah = 0x5d8 ;
    long XK_Arabic_ain = 0x5d9 ;
    long XK_Arabic_ghain = 0x5da ;
    long XK_Arabic_tatweel = 0x5e0 ;
    long XK_Arabic_feh = 0x5e1 ;
    long XK_Arabic_qaf = 0x5e2 ;
    long XK_Arabic_kaf = 0x5e3 ;
    long XK_Arabic_lam = 0x5e4 ;
    long XK_Arabic_meem = 0x5e5 ;
    long XK_Arabic_noon = 0x5e6 ;
    long XK_Arabic_ha = 0x5e7 ;
    long XK_Arabic_heh = 0x5e7 ; /* deprecated */
    long XK_Arabic_waw = 0x5e8 ;
    long XK_Arabic_alefmaksura = 0x5e9 ;
    long XK_Arabic_yeh = 0x5ea ;
    long XK_Arabic_fathatan = 0x5eb ;
    long XK_Arabic_dammatan = 0x5ec ;
    long XK_Arabic_kasratan = 0x5ed ;
    long XK_Arabic_fatha = 0x5ee ;
    long XK_Arabic_damma = 0x5ef ;
    long XK_Arabic_kasra = 0x5f0 ;
    long XK_Arabic_shadda = 0x5f1 ;
    long XK_Arabic_sukun = 0x5f2 ;
    long XK_Arabic_switch = 0xFF7E ; /* Alias for mode_switch */

    /*
     * Cyrillic
     * Byte 3 = 6
     */
    long XK_Serbian_dje = 0x6a1 ;
    long XK_Macedonia_gje = 0x6a2 ;
    long XK_Cyrillic_io = 0x6a3 ;
    long XK_Ukrainian_ie = 0x6a4 ;
    long XK_Ukranian_je = 0x6a4 ; /* deprecated */
    long XK_Macedonia_dse = 0x6a5 ;
    long XK_Ukrainian_i = 0x6a6 ;
    long XK_Ukranian_i = 0x6a6 ; /* deprecated */
    long XK_Ukrainian_yi = 0x6a7 ;
    long XK_Ukranian_yi = 0x6a7 ; /* deprecated */
    long XK_Cyrillic_je = 0x6a8 ;
    long XK_Serbian_je = 0x6a8 ; /* deprecated */
    long XK_Cyrillic_lje = 0x6a9 ;
    long XK_Serbian_lje = 0x6a9 ; /* deprecated */
    long XK_Cyrillic_nje = 0x6aa ;
    long XK_Serbian_nje = 0x6aa ; /* deprecated */
    long XK_Serbian_tshe = 0x6ab ;
    long XK_Macedonia_kje = 0x6ac ;
    long XK_Byelorussian_shortu = 0x6ae ;
    long XK_Cyrillic_dzhe = 0x6af ;
    long XK_Serbian_dze = 0x6af ; /* deprecated */
    long XK_numerosign = 0x6b0 ;
    long XK_Serbian_DJE = 0x6b1 ;
    long XK_Macedonia_GJE = 0x6b2 ;
    long XK_Cyrillic_IO = 0x6b3 ;
    long XK_Ukrainian_IE = 0x6b4 ;
    long XK_Ukranian_JE = 0x6b4 ; /* deprecated */
    long XK_Macedonia_DSE = 0x6b5 ;
    long XK_Ukrainian_I = 0x6b6 ;
    long XK_Ukranian_I = 0x6b6 ; /* deprecated */
    long XK_Ukrainian_YI = 0x6b7 ;
    long XK_Ukranian_YI = 0x6b7 ; /* deprecated */
    long XK_Cyrillic_JE = 0x6b8 ;
    long XK_Serbian_JE = 0x6b8 ; /* deprecated */
    long XK_Cyrillic_LJE = 0x6b9 ;
    long XK_Serbian_LJE = 0x6b9 ; /* deprecated */
    long XK_Cyrillic_NJE = 0x6ba ;
    long XK_Serbian_NJE = 0x6ba ; /* deprecated */
    long XK_Serbian_TSHE = 0x6bb ;
    long XK_Macedonia_KJE = 0x6bc ;
    long XK_Byelorussian_SHORTU = 0x6be ;
    long XK_Cyrillic_DZHE = 0x6bf ;
    long XK_Serbian_DZE = 0x6bf ; /* deprecated */
    long XK_Cyrillic_yu = 0x6c0 ;
    long XK_Cyrillic_a = 0x6c1 ;
    long XK_Cyrillic_be = 0x6c2 ;
    long XK_Cyrillic_tse = 0x6c3 ;
    long XK_Cyrillic_de = 0x6c4 ;
    long XK_Cyrillic_ie = 0x6c5 ;
    long XK_Cyrillic_ef = 0x6c6 ;
    long XK_Cyrillic_ghe = 0x6c7 ;
    long XK_Cyrillic_ha = 0x6c8 ;
    long XK_Cyrillic_i = 0x6c9 ;
    long XK_Cyrillic_shorti = 0x6ca ;
    long XK_Cyrillic_ka = 0x6cb ;
    long XK_Cyrillic_el = 0x6cc ;
    long XK_Cyrillic_em = 0x6cd ;
    long XK_Cyrillic_en = 0x6ce ;
    long XK_Cyrillic_o = 0x6cf ;
    long XK_Cyrillic_pe = 0x6d0 ;
    long XK_Cyrillic_ya = 0x6d1 ;
    long XK_Cyrillic_er = 0x6d2 ;
    long XK_Cyrillic_es = 0x6d3 ;
    long XK_Cyrillic_te = 0x6d4 ;
    long XK_Cyrillic_u = 0x6d5 ;
    long XK_Cyrillic_zhe = 0x6d6 ;
    long XK_Cyrillic_ve = 0x6d7 ;
    long XK_Cyrillic_softsign = 0x6d8 ;
    long XK_Cyrillic_yeru = 0x6d9 ;
    long XK_Cyrillic_ze = 0x6da ;
    long XK_Cyrillic_sha = 0x6db ;
    long XK_Cyrillic_e = 0x6dc ;
    long XK_Cyrillic_shcha = 0x6dd ;
    long XK_Cyrillic_che = 0x6de ;
    long XK_Cyrillic_hardsign = 0x6df ;
    long XK_Cyrillic_YU = 0x6e0 ;
    long XK_Cyrillic_A = 0x6e1 ;
    long XK_Cyrillic_BE = 0x6e2 ;
    long XK_Cyrillic_TSE = 0x6e3 ;
    long XK_Cyrillic_DE = 0x6e4 ;
    long XK_Cyrillic_IE = 0x6e5 ;
    long XK_Cyrillic_EF = 0x6e6 ;
    long XK_Cyrillic_GHE = 0x6e7 ;
    long XK_Cyrillic_HA = 0x6e8 ;
    long XK_Cyrillic_I = 0x6e9 ;
    long XK_Cyrillic_SHORTI = 0x6ea ;
    long XK_Cyrillic_KA = 0x6eb ;
    long XK_Cyrillic_EL = 0x6ec ;
    long XK_Cyrillic_EM = 0x6ed ;
    long XK_Cyrillic_EN = 0x6ee ;
    long XK_Cyrillic_O = 0x6ef ;
    long XK_Cyrillic_PE = 0x6f0 ;
    long XK_Cyrillic_YA = 0x6f1 ;
    long XK_Cyrillic_ER = 0x6f2 ;
    long XK_Cyrillic_ES = 0x6f3 ;
    long XK_Cyrillic_TE = 0x6f4 ;
    long XK_Cyrillic_U = 0x6f5 ;
    long XK_Cyrillic_ZHE = 0x6f6 ;
    long XK_Cyrillic_VE = 0x6f7 ;
    long XK_Cyrillic_SOFTSIGN = 0x6f8 ;
    long XK_Cyrillic_YERU = 0x6f9 ;
    long XK_Cyrillic_ZE = 0x6fa ;
    long XK_Cyrillic_SHA = 0x6fb ;
    long XK_Cyrillic_E = 0x6fc ;
    long XK_Cyrillic_SHCHA = 0x6fd ;
    long XK_Cyrillic_CHE = 0x6fe ;
    long XK_Cyrillic_HARDSIGN = 0x6ff ;

    /*
     * Greek
     * Byte 3 = 7
     */

    long XK_Greek_ALPHAaccent = 0x7a1 ;
    long XK_Greek_EPSILONaccent = 0x7a2 ;
    long XK_Greek_ETAaccent = 0x7a3 ;
    long XK_Greek_IOTAaccent = 0x7a4 ;
    long XK_Greek_IOTAdiaeresis = 0x7a5 ;
    long XK_Greek_OMICRONaccent = 0x7a7 ;
    long XK_Greek_UPSILONaccent = 0x7a8 ;
    long XK_Greek_UPSILONdieresis = 0x7a9 ;
    long XK_Greek_OMEGAaccent = 0x7ab ;
    long XK_Greek_accentdieresis = 0x7ae ;
    long XK_Greek_horizbar = 0x7af ;
    long XK_Greek_alphaaccent = 0x7b1 ;
    long XK_Greek_epsilonaccent = 0x7b2 ;
    long XK_Greek_etaaccent = 0x7b3 ;
    long XK_Greek_iotaaccent = 0x7b4 ;
    long XK_Greek_iotadieresis = 0x7b5 ;
    long XK_Greek_iotaaccentdieresis = 0x7b6 ;
    long XK_Greek_omicronaccent = 0x7b7 ;
    long XK_Greek_upsilonaccent = 0x7b8 ;
    long XK_Greek_upsilondieresis = 0x7b9 ;
    long XK_Greek_upsilonaccentdieresis = 0x7ba ;
    long XK_Greek_omegaaccent = 0x7bb ;
    long XK_Greek_ALPHA = 0x7c1 ;
    long XK_Greek_BETA = 0x7c2 ;
    long XK_Greek_GAMMA = 0x7c3 ;
    long XK_Greek_DELTA = 0x7c4 ;
    long XK_Greek_EPSILON = 0x7c5 ;
    long XK_Greek_ZETA = 0x7c6 ;
    long XK_Greek_ETA = 0x7c7 ;
    long XK_Greek_THETA = 0x7c8 ;
    long XK_Greek_IOTA = 0x7c9 ;
    long XK_Greek_KAPPA = 0x7ca ;
    long XK_Greek_LAMDA = 0x7cb ;
    long XK_Greek_LAMBDA = 0x7cb ;
    long XK_Greek_MU = 0x7cc ;
    long XK_Greek_NU = 0x7cd ;
    long XK_Greek_XI = 0x7ce ;
    long XK_Greek_OMICRON = 0x7cf ;
    long XK_Greek_PI = 0x7d0 ;
    long XK_Greek_RHO = 0x7d1 ;
    long XK_Greek_SIGMA = 0x7d2 ;
    long XK_Greek_TAU = 0x7d4 ;
    long XK_Greek_UPSILON = 0x7d5 ;
    long XK_Greek_PHI = 0x7d6 ;
    long XK_Greek_CHI = 0x7d7 ;
    long XK_Greek_PSI = 0x7d8 ;
    long XK_Greek_OMEGA = 0x7d9 ;
    long XK_Greek_alpha = 0x7e1 ;
    long XK_Greek_beta = 0x7e2 ;
    long XK_Greek_gamma = 0x7e3 ;
    long XK_Greek_delta = 0x7e4 ;
    long XK_Greek_epsilon = 0x7e5 ;
    long XK_Greek_zeta = 0x7e6 ;
    long XK_Greek_eta = 0x7e7 ;
    long XK_Greek_theta = 0x7e8 ;
    long XK_Greek_iota = 0x7e9 ;
    long XK_Greek_kappa = 0x7ea ;
    long XK_Greek_lamda = 0x7eb ;
    long XK_Greek_lambda = 0x7eb ;
    long XK_Greek_mu = 0x7ec ;
    long XK_Greek_nu = 0x7ed ;
    long XK_Greek_xi = 0x7ee ;
    long XK_Greek_omicron = 0x7ef ;
    long XK_Greek_pi = 0x7f0 ;
    long XK_Greek_rho = 0x7f1 ;
    long XK_Greek_sigma = 0x7f2 ;
    long XK_Greek_finalsmallsigma = 0x7f3 ;
    long XK_Greek_tau = 0x7f4 ;
    long XK_Greek_upsilon = 0x7f5 ;
    long XK_Greek_phi = 0x7f6 ;
    long XK_Greek_chi = 0x7f7 ;
    long XK_Greek_psi = 0x7f8 ;
    long XK_Greek_omega = 0x7f9 ;
    long XK_Greek_switch = 0xFF7E ; /* Alias for mode_switch */

    /*
     * Technical
     * Byte 3 = 8
     */

    long XK_leftradical = 0x8a1 ;
    long XK_topleftradical = 0x8a2 ;
    long XK_horizconnector = 0x8a3 ;
    long XK_topintegral = 0x8a4 ;
    long XK_botintegral = 0x8a5 ;
    long XK_vertconnector = 0x8a6 ;
    long XK_topleftsqbracket = 0x8a7 ;
    long XK_botleftsqbracket = 0x8a8 ;
    long XK_toprightsqbracket = 0x8a9 ;
    long XK_botrightsqbracket = 0x8aa ;
    long XK_topleftparens = 0x8ab ;
    long XK_botleftparens = 0x8ac ;
    long XK_toprightparens = 0x8ad ;
    long XK_botrightparens = 0x8ae ;
    long XK_leftmiddlecurlybrace = 0x8af ;
    long XK_rightmiddlecurlybrace = 0x8b0 ;
    long XK_topleftsummation = 0x8b1 ;
    long XK_botleftsummation = 0x8b2 ;
    long XK_topvertsummationconnector = 0x8b3 ;
    long XK_botvertsummationconnector = 0x8b4 ;
    long XK_toprightsummation = 0x8b5 ;
    long XK_botrightsummation = 0x8b6 ;
    long XK_rightmiddlesummation = 0x8b7 ;
    long XK_lessthanequal = 0x8bc ;
    long XK_notequal = 0x8bd ;
    long XK_greaterthanequal = 0x8be ;
    long XK_integral = 0x8bf ;
    long XK_therefore = 0x8c0 ;
    long XK_variation = 0x8c1 ;
    long XK_infinity = 0x8c2 ;
    long XK_nabla = 0x8c5 ;
    long XK_approximate = 0x8c8 ;
    long XK_similarequal = 0x8c9 ;
    long XK_ifonlyif = 0x8cd ;
    long XK_implies = 0x8ce ;
    long XK_identical = 0x8cf ;
    long XK_radical = 0x8d6 ;
    long XK_includedin = 0x8da ;
    long XK_includes = 0x8db ;
    long XK_intersection = 0x8dc ;
    long XK_union = 0x8dd ;
    long XK_logicaland = 0x8de ;
    long XK_logicalor = 0x8df ;
    long XK_partialderivative = 0x8ef ;
    long XK_function = 0x8f6 ;
    long XK_leftarrow = 0x8fb ;
    long XK_uparrow = 0x8fc ;
    long XK_rightarrow = 0x8fd ;
    long XK_downarrow = 0x8fe ;

    /*
     *  Special
     *  Byte 3 = 9
     */

    long XK_blank = 0x9df ;
    long XK_soliddiamond = 0x9e0 ;
    long XK_checkerboard = 0x9e1 ;
    long XK_ht = 0x9e2 ;
    long XK_ff = 0x9e3 ;
    long XK_cr = 0x9e4 ;
    long XK_lf = 0x9e5 ;
    long XK_nl = 0x9e8 ;
    long XK_vt = 0x9e9 ;
    long XK_lowrightcorner = 0x9ea ;
    long XK_uprightcorner = 0x9eb ;
    long XK_upleftcorner = 0x9ec ;
    long XK_lowleftcorner = 0x9ed ;
    long XK_crossinglines = 0x9ee ;
    long XK_horizlinescan1 = 0x9ef ;
    long XK_horizlinescan3 = 0x9f0 ;
    long XK_horizlinescan5 = 0x9f1 ;
    long XK_horizlinescan7 = 0x9f2 ;
    long XK_horizlinescan9 = 0x9f3 ;
    long XK_leftt = 0x9f4 ;
    long XK_rightt = 0x9f5 ;
    long XK_bott = 0x9f6 ;
    long XK_topt = 0x9f7 ;
    long XK_vertbar = 0x9f8 ;

    /*
     *  Publishing
     *  Byte 3 = a
     */

    long XK_emspace = 0xaa1 ;
    long XK_enspace = 0xaa2 ;
    long XK_em3space = 0xaa3 ;
    long XK_em4space = 0xaa4 ;
    long XK_digitspace = 0xaa5 ;
    long XK_punctspace = 0xaa6 ;
    long XK_thinspace = 0xaa7 ;
    long XK_hairspace = 0xaa8 ;
    long XK_emdash = 0xaa9 ;
    long XK_endash = 0xaaa ;
    long XK_signifblank = 0xaac ;
    long XK_ellipsis = 0xaae ;
    long XK_doubbaselinedot = 0xaaf ;
    long XK_onethird = 0xab0 ;
    long XK_twothirds = 0xab1 ;
    long XK_onefifth = 0xab2 ;
    long XK_twofifths = 0xab3 ;
    long XK_threefifths = 0xab4 ;
    long XK_fourfifths = 0xab5 ;
    long XK_onesixth = 0xab6 ;
    long XK_fivesixths = 0xab7 ;
    long XK_careof = 0xab8 ;
    long XK_figdash = 0xabb ;
    long XK_leftanglebracket = 0xabc ;
    long XK_decimalpoint = 0xabd ;
    long XK_rightanglebracket = 0xabe ;
    long XK_marker = 0xabf ;
    long XK_oneeighth = 0xac3 ;
    long XK_threeeighths = 0xac4 ;
    long XK_fiveeighths = 0xac5 ;
    long XK_seveneighths = 0xac6 ;
    long XK_trademark = 0xac9 ;
    long XK_signaturemark = 0xaca ;
    long XK_trademarkincircle = 0xacb ;
    long XK_leftopentriangle = 0xacc ;
    long XK_rightopentriangle = 0xacd ;
    long XK_emopencircle = 0xace ;
    long XK_emopenrectangle = 0xacf ;
    long XK_leftsinglequotemark = 0xad0 ;
    long XK_rightsinglequotemark = 0xad1 ;
    long XK_leftdoublequotemark = 0xad2 ;
    long XK_rightdoublequotemark = 0xad3 ;
    long XK_prescription = 0xad4 ;
    long XK_minutes = 0xad6 ;
    long XK_seconds = 0xad7 ;
    long XK_latincross = 0xad9 ;
    long XK_hexagram = 0xada ;
    long XK_filledrectbullet = 0xadb ;
    long XK_filledlefttribullet = 0xadc ;
    long XK_filledrighttribullet = 0xadd ;
    long XK_emfilledcircle = 0xade ;
    long XK_emfilledrect = 0xadf ;
    long XK_enopencircbullet = 0xae0 ;
    long XK_enopensquarebullet = 0xae1 ;
    long XK_openrectbullet = 0xae2 ;
    long XK_opentribulletup = 0xae3 ;
    long XK_opentribulletdown = 0xae4 ;
    long XK_openstar = 0xae5 ;
    long XK_enfilledcircbullet = 0xae6 ;
    long XK_enfilledsqbullet = 0xae7 ;
    long XK_filledtribulletup = 0xae8 ;
    long XK_filledtribulletdown = 0xae9 ;
    long XK_leftpointer = 0xaea ;
    long XK_rightpointer = 0xaeb ;
    long XK_club = 0xaec ;
    long XK_diamond = 0xaed ;
    long XK_heart = 0xaee ;
    long XK_maltesecross = 0xaf0 ;
    long XK_dagger = 0xaf1 ;
    long XK_doubledagger = 0xaf2 ;
    long XK_checkmark = 0xaf3 ;
    long XK_ballotcross = 0xaf4 ;
    long XK_musicalsharp = 0xaf5 ;
    long XK_musicalflat = 0xaf6 ;
    long XK_malesymbol = 0xaf7 ;
    long XK_femalesymbol = 0xaf8 ;
    long XK_telephone = 0xaf9 ;
    long XK_telephonerecorder = 0xafa ;
    long XK_phonographcopyright = 0xafb ;
    long XK_caret = 0xafc ;
    long XK_singlelowquotemark = 0xafd ;
    long XK_doublelowquotemark = 0xafe ;
    long XK_cursor = 0xaff ;

    /*
     *  APL
     *  Byte 3 = b
     */

    long XK_leftcaret = 0xba3 ;
    long XK_rightcaret = 0xba6 ;
    long XK_downcaret = 0xba8 ;
    long XK_upcaret = 0xba9 ;
    long XK_overbar = 0xbc0 ;
    long XK_downtack = 0xbc2 ;
    long XK_upshoe = 0xbc3 ;
    long XK_downstile = 0xbc4 ;
    long XK_underbar = 0xbc6 ;
    long XK_jot = 0xbca ;
    long XK_quad = 0xbcc ;
    long XK_uptack = 0xbce ;
    long XK_circle = 0xbcf ;
    long XK_upstile = 0xbd3 ;
    long XK_downshoe = 0xbd6 ;
    long XK_rightshoe = 0xbd8 ;
    long XK_leftshoe = 0xbda ;
    long XK_lefttack = 0xbdc ;
    long XK_righttack = 0xbfc ;

    /*
     * Hebrew
     * Byte 3 = c
     */

    long XK_hebrew_doublelowline = 0xcdf ;
    long XK_hebrew_aleph = 0xce0 ;
    long XK_hebrew_bet = 0xce1 ;
    long XK_hebrew_beth = 0xce1 ; /* deprecated */
    long XK_hebrew_gimel = 0xce2 ;
    long XK_hebrew_gimmel = 0xce2 ; /* deprecated */
    long XK_hebrew_dalet = 0xce3 ;
    long XK_hebrew_daleth = 0xce3 ; /* deprecated */
    long XK_hebrew_he = 0xce4 ;
    long XK_hebrew_waw = 0xce5 ;
    long XK_hebrew_zain = 0xce6 ;
    long XK_hebrew_zayin = 0xce6 ; /* deprecated */
    long XK_hebrew_chet = 0xce7 ;
    long XK_hebrew_het = 0xce7 ; /* deprecated */
    long XK_hebrew_tet = 0xce8 ;
    long XK_hebrew_teth = 0xce8 ; /* deprecated */
    long XK_hebrew_yod = 0xce9 ;
    long XK_hebrew_finalkaph = 0xcea ;
    long XK_hebrew_kaph = 0xceb ;
    long XK_hebrew_lamed = 0xcec ;
    long XK_hebrew_finalmem = 0xced ;
    long XK_hebrew_mem = 0xcee ;
    long XK_hebrew_finalnun = 0xcef ;
    long XK_hebrew_nun = 0xcf0 ;
    long XK_hebrew_samech = 0xcf1 ;
    long XK_hebrew_samekh = 0xcf1 ; /* deprecated */
    long XK_hebrew_ayin = 0xcf2 ;
    long XK_hebrew_finalpe = 0xcf3 ;
    long XK_hebrew_pe = 0xcf4 ;
    long XK_hebrew_finalzade = 0xcf5 ;
    long XK_hebrew_finalzadi = 0xcf5 ; /* deprecated */
    long XK_hebrew_zade = 0xcf6 ;
    long XK_hebrew_zadi = 0xcf6 ; /* deprecated */
    long XK_hebrew_qoph = 0xcf7 ;
    long XK_hebrew_kuf = 0xcf7 ; /* deprecated */
    long XK_hebrew_resh = 0xcf8 ;
    long XK_hebrew_shin = 0xcf9 ;
    long XK_hebrew_taw = 0xcfa ;
    long XK_hebrew_taf = 0xcfa ; /* deprecated */
    long XK_Hebrew_switch = 0xFF7E ; /* Alias for mode_switch */

    /*
     * Thai
     * Byte 3 = d
     */

    long XK_Thai_kokai = 0xda1 ;
    long XK_Thai_khokhai = 0xda2 ;
    long XK_Thai_khokhuat = 0xda3 ;
    long XK_Thai_khokhwai = 0xda4 ;
    long XK_Thai_khokhon = 0xda5 ;
    long XK_Thai_khorakhang = 0xda6 ;
    long XK_Thai_ngongu = 0xda7 ;
    long XK_Thai_chochan = 0xda8 ;
    long XK_Thai_choching = 0xda9 ;
    long XK_Thai_chochang = 0xdaa ;
    long XK_Thai_soso = 0xdab ;
    long XK_Thai_chochoe = 0xdac ;
    long XK_Thai_yoying = 0xdad ;
    long XK_Thai_dochada = 0xdae ;
    long XK_Thai_topatak = 0xdaf ;
    long XK_Thai_thothan = 0xdb0 ;
    long XK_Thai_thonangmontho = 0xdb1 ;
    long XK_Thai_thophuthao = 0xdb2 ;
    long XK_Thai_nonen = 0xdb3 ;
    long XK_Thai_dodek = 0xdb4 ;
    long XK_Thai_totao = 0xdb5 ;
    long XK_Thai_thothung = 0xdb6 ;
    long XK_Thai_thothahan = 0xdb7 ;
    long XK_Thai_thothong = 0xdb8 ;
    long XK_Thai_nonu = 0xdb9 ;
    long XK_Thai_bobaimai = 0xdba ;
    long XK_Thai_popla = 0xdbb ;
    long XK_Thai_phophung = 0xdbc ;
    long XK_Thai_fofa = 0xdbd ;
    long XK_Thai_phophan = 0xdbe ;
    long XK_Thai_fofan = 0xdbf ;
    long XK_Thai_phosamphao = 0xdc0 ;
    long XK_Thai_moma = 0xdc1 ;
    long XK_Thai_yoyak = 0xdc2 ;
    long XK_Thai_rorua = 0xdc3 ;
    long XK_Thai_ru = 0xdc4 ;
    long XK_Thai_loling = 0xdc5 ;
    long XK_Thai_lu = 0xdc6 ;
    long XK_Thai_wowaen = 0xdc7 ;
    long XK_Thai_sosala = 0xdc8 ;
    long XK_Thai_sorusi = 0xdc9 ;
    long XK_Thai_sosua = 0xdca ;
    long XK_Thai_hohip = 0xdcb ;
    long XK_Thai_lochula = 0xdcc ;
    long XK_Thai_oang = 0xdcd ;
    long XK_Thai_honokhuk = 0xdce ;
    long XK_Thai_paiyannoi = 0xdcf ;
    long XK_Thai_saraa = 0xdd0 ;
    long XK_Thai_maihanakat = 0xdd1 ;
    long XK_Thai_saraaa = 0xdd2 ;
    long XK_Thai_saraam = 0xdd3 ;
    long XK_Thai_sarai = 0xdd4 ;
    long XK_Thai_saraii = 0xdd5 ;
    long XK_Thai_saraue = 0xdd6 ;
    long XK_Thai_sarauee = 0xdd7 ;
    long XK_Thai_sarau = 0xdd8 ;
    long XK_Thai_sarauu = 0xdd9 ;
    long XK_Thai_phinthu = 0xdda ;
    long XK_Thai_maihanakat_maitho = 0xdde ;
    long XK_Thai_baht = 0xddf ;
    long XK_Thai_sarae = 0xde0 ;
    long XK_Thai_saraae = 0xde1 ;
    long XK_Thai_sarao = 0xde2 ;
    long XK_Thai_saraaimaimuan = 0xde3 ;
    long XK_Thai_saraaimaimalai = 0xde4 ;
    long XK_Thai_lakkhangyao = 0xde5 ;
    long XK_Thai_maiyamok = 0xde6 ;
    long XK_Thai_maitaikhu = 0xde7 ;
    long XK_Thai_maiek = 0xde8 ;
    long XK_Thai_maitho = 0xde9 ;
    long XK_Thai_maitri = 0xdea ;
    long XK_Thai_maichattawa = 0xdeb ;
    long XK_Thai_thanthakhat = 0xdec ;
    long XK_Thai_nikhahit = 0xded ;
    long XK_Thai_leksun = 0xdf0 ;
    long XK_Thai_leknung = 0xdf1 ;
    long XK_Thai_leksong = 0xdf2 ;
    long XK_Thai_leksam = 0xdf3 ;
    long XK_Thai_leksi = 0xdf4 ;
    long XK_Thai_lekha = 0xdf5 ;
    long XK_Thai_lekhok = 0xdf6 ;
    long XK_Thai_lekchet = 0xdf7 ;
    long XK_Thai_lekpaet = 0xdf8 ;
    long XK_Thai_lekkao = 0xdf9 ;

    /*
     *   Korean
     *   Byte 3 = e
     */

    long XK_Hangul = 0xff31 ; /* Hangul start/stop(toggle) */
    long XK_Hangul_Start = 0xff32 ; /* Hangul start */
    long XK_Hangul_End = 0xff33 ; /* Hangul end, English start */
    long XK_Hangul_Hanja = 0xff34 ; /* Start Hangul->Hanja Conversion */
    long XK_Hangul_Jamo = 0xff35 ; /* Hangul Jamo mode */
    long XK_Hangul_Romaja = 0xff36 ; /* Hangul Romaja mode */
    long XK_Hangul_Codeinput = 0xff37 ; /* Hangul code input mode */
    long XK_Hangul_Jeonja = 0xff38 ; /* Jeonja mode */
    long XK_Hangul_Banja = 0xff39 ; /* Banja mode */
    long XK_Hangul_PreHanja = 0xff3a ; /* Pre Hanja conversion */
    long XK_Hangul_PostHanja = 0xff3b ; /* Post Hanja conversion */
    long XK_Hangul_SingleCandidate = 0xff3c ; /* Single candidate */
    long XK_Hangul_MultipleCandidate = 0xff3d ; /* Multiple candidate */
    long XK_Hangul_PreviousCandidate = 0xff3e ; /* Previous candidate */
    long XK_Hangul_Special = 0xff3f ; /* Special symbols */
    long XK_Hangul_switch = 0xFF7E ; /* Alias for mode_switch */

    /* Hangul Consonant Characters */
    long XK_Hangul_Kiyeog = 0xea1 ;
    long XK_Hangul_SsangKiyeog = 0xea2 ;
    long XK_Hangul_KiyeogSios = 0xea3 ;
    long XK_Hangul_Nieun = 0xea4 ;
    long XK_Hangul_NieunJieuj = 0xea5 ;
    long XK_Hangul_NieunHieuh = 0xea6 ;
    long XK_Hangul_Dikeud = 0xea7 ;
    long XK_Hangul_SsangDikeud = 0xea8 ;
    long XK_Hangul_Rieul = 0xea9 ;
    long XK_Hangul_RieulKiyeog = 0xeaa ;
    long XK_Hangul_RieulMieum = 0xeab ;
    long XK_Hangul_RieulPieub = 0xeac ;
    long XK_Hangul_RieulSios = 0xead ;
    long XK_Hangul_RieulTieut = 0xeae ;
    long XK_Hangul_RieulPhieuf = 0xeaf ;
    long XK_Hangul_RieulHieuh = 0xeb0 ;
    long XK_Hangul_Mieum = 0xeb1 ;
    long XK_Hangul_Pieub = 0xeb2 ;
    long XK_Hangul_SsangPieub = 0xeb3 ;
    long XK_Hangul_PieubSios = 0xeb4 ;
    long XK_Hangul_Sios = 0xeb5 ;
    long XK_Hangul_SsangSios = 0xeb6 ;
    long XK_Hangul_Ieung = 0xeb7 ;
    long XK_Hangul_Jieuj = 0xeb8 ;
    long XK_Hangul_SsangJieuj = 0xeb9 ;
    long XK_Hangul_Cieuc = 0xeba ;
    long XK_Hangul_Khieuq = 0xebb ;
    long XK_Hangul_Tieut = 0xebc ;
    long XK_Hangul_Phieuf = 0xebd ;
    long XK_Hangul_Hieuh = 0xebe ;

    /* Hangul Vowel Characters */
    long XK_Hangul_A = 0xebf ;
    long XK_Hangul_AE = 0xec0 ;
    long XK_Hangul_YA = 0xec1 ;
    long XK_Hangul_YAE = 0xec2 ;
    long XK_Hangul_EO = 0xec3 ;
    long XK_Hangul_E = 0xec4 ;
    long XK_Hangul_YEO = 0xec5 ;
    long XK_Hangul_YE = 0xec6 ;
    long XK_Hangul_O = 0xec7 ;
    long XK_Hangul_WA = 0xec8 ;
    long XK_Hangul_WAE = 0xec9 ;
    long XK_Hangul_OE = 0xeca ;
    long XK_Hangul_YO = 0xecb ;
    long XK_Hangul_U = 0xecc ;
    long XK_Hangul_WEO = 0xecd ;
    long XK_Hangul_WE = 0xece ;
    long XK_Hangul_WI = 0xecf ;
    long XK_Hangul_YU = 0xed0 ;
    long XK_Hangul_EU = 0xed1 ;
    long XK_Hangul_YI = 0xed2 ;
    long XK_Hangul_I = 0xed3 ;

    /* Hangul syllable-final (JongSeong) Characters */
    long XK_Hangul_J_Kiyeog = 0xed4 ;
    long XK_Hangul_J_SsangKiyeog = 0xed5 ;
    long XK_Hangul_J_KiyeogSios = 0xed6 ;
    long XK_Hangul_J_Nieun = 0xed7 ;
    long XK_Hangul_J_NieunJieuj = 0xed8 ;
    long XK_Hangul_J_NieunHieuh = 0xed9 ;
    long XK_Hangul_J_Dikeud = 0xeda ;
    long XK_Hangul_J_Rieul = 0xedb ;
    long XK_Hangul_J_RieulKiyeog = 0xedc ;
    long XK_Hangul_J_RieulMieum = 0xedd ;
    long XK_Hangul_J_RieulPieub = 0xede ;
    long XK_Hangul_J_RieulSios = 0xedf ;
    long XK_Hangul_J_RieulTieut = 0xee0 ;
    long XK_Hangul_J_RieulPhieuf = 0xee1 ;
    long XK_Hangul_J_RieulHieuh = 0xee2 ;
    long XK_Hangul_J_Mieum = 0xee3 ;
    long XK_Hangul_J_Pieub = 0xee4 ;
    long XK_Hangul_J_PieubSios = 0xee5 ;
    long XK_Hangul_J_Sios = 0xee6 ;
    long XK_Hangul_J_SsangSios = 0xee7 ;
    long XK_Hangul_J_Ieung = 0xee8 ;
    long XK_Hangul_J_Jieuj = 0xee9 ;
    long XK_Hangul_J_Cieuc = 0xeea ;
    long XK_Hangul_J_Khieuq = 0xeeb ;
    long XK_Hangul_J_Tieut = 0xeec ;
    long XK_Hangul_J_Phieuf = 0xeed ;
    long XK_Hangul_J_Hieuh = 0xeee ;

    /* Ancient Hangul Consonant Characters */
    long XK_Hangul_RieulYeorinHieuh = 0xeef ;
    long XK_Hangul_SunkyeongeumMieum = 0xef0 ;
    long XK_Hangul_SunkyeongeumPieub = 0xef1 ;
    long XK_Hangul_PanSios = 0xef2 ;
    long XK_Hangul_KkogjiDalrinIeung = 0xef3 ;
    long XK_Hangul_SunkyeongeumPhieuf = 0xef4 ;
    long XK_Hangul_YeorinHieuh = 0xef5 ;

    /* Ancient Hangul Vowel Characters */
    long XK_Hangul_AraeA = 0xef6 ;
    long XK_Hangul_AraeAE = 0xef7 ;

    /* Ancient Hangul syllable-final (JongSeong) Characters */
    long XK_Hangul_J_PanSios = 0xef8 ;
    long XK_Hangul_J_KkogjiDalrinIeung = 0xef9 ;
    long XK_Hangul_J_YeorinHieuh = 0xefa ;

    /* Korean currency symbol */
    long XK_Korean_Won = 0xeff ;

    long XK_EcuSign = 0x20a0 ;
    long XK_ColonSign = 0x20a1 ;
    long XK_CruzeiroSign = 0x20a2 ;
    long XK_FFrancSign = 0x20a3 ;
    long XK_LiraSign = 0x20a4 ;
    long XK_MillSign = 0x20a5 ;
    long XK_NairaSign = 0x20a6 ;
    long XK_PesetaSign = 0x20a7 ;
    long XK_RupeeSign = 0x20a8 ;
    long XK_WonSign = 0x20a9 ;
    long XK_NewSheqelSign = 0x20aa ;
    long XK_DongSign = 0x20ab ;
    long XK_EuroSign = 0x20ac ;

    // vendor-specific keys from ap_keysym.h, DEC/Sun/HPkeysym.h

    long apXK_Copy = 0x1000FF02;
    long apXK_Cut = 0x1000FF03;
    long apXK_Paste = 0x1000FF04;

    long DXK_ring_accent = 0x1000FEB0;
    long DXK_circumflex_accent = 0x1000FE5E;
    long DXK_cedilla_accent = 0x1000FE2C;
    long DXK_acute_accent = 0x1000FE27;
    long DXK_grave_accent = 0x1000FE60;
    long DXK_tilde = 0x1000FE7E;
    long DXK_diaeresis = 0x1000FE22;

    long hpXK_ClearLine  = 0x1000FF6F;
    long hpXK_InsertLine  = 0x1000FF70;
    long hpXK_DeleteLine  = 0x1000FF71;
    long hpXK_InsertChar  = 0x1000FF72;
    long hpXK_DeleteChar  = 0x1000FF73;
    long hpXK_BackTab  = 0x1000FF74;
    long hpXK_KP_BackTab  = 0x1000FF75;
    long hpXK_Modelock1  = 0x1000FF48;
    long hpXK_Modelock2  = 0x1000FF49;
    long hpXK_Reset  = 0x1000FF6C;
    long hpXK_System  = 0x1000FF6D;
    long hpXK_User  = 0x1000FF6E;
    long hpXK_mute_acute  = 0x100000A8;
    long hpXK_mute_grave  = 0x100000A9;
    long hpXK_mute_asciicircum  = 0x100000AA;
    long hpXK_mute_diaeresis  = 0x100000AB;
    long hpXK_mute_asciitilde  = 0x100000AC;
    long hpXK_lira  = 0x100000AF;
    long hpXK_guilder  = 0x100000BE;
    long hpXK_Ydiaeresis  = 0x100000EE;
    long hpXK_IO   = 0x100000EE;
    long hpXK_longminus  = 0x100000F6;
    long hpXK_block  = 0x100000FC;

    long osfXK_Copy  = 0x1004FF02;
    long osfXK_Cut  = 0x1004FF03;
    long osfXK_Paste  = 0x1004FF04;
    long osfXK_BackTab  = 0x1004FF07;
    long osfXK_BackSpace  = 0x1004FF08;
    long osfXK_Clear  = 0x1004FF0B;
    long osfXK_Escape  = 0x1004FF1B;
    long osfXK_AddMode  = 0x1004FF31;
    long osfXK_PrimaryPaste  = 0x1004FF32;
    long osfXK_QuickPaste  = 0x1004FF33;
    long osfXK_PageLeft  = 0x1004FF40;
    long osfXK_PageUp  = 0x1004FF41;
    long osfXK_PageDown  = 0x1004FF42;
    long osfXK_PageRight  = 0x1004FF43;
    long osfXK_Activate  = 0x1004FF44;
    long osfXK_MenuBar  = 0x1004FF45;
    long osfXK_Left  = 0x1004FF51;
    long osfXK_Up  = 0x1004FF52;
    long osfXK_Right  = 0x1004FF53;
    long osfXK_Down  = 0x1004FF54;
    long osfXK_EndLine  = 0x1004FF57;
    long osfXK_BeginLine  = 0x1004FF58;
    long osfXK_EndData  = 0x1004FF59;
    long osfXK_BeginData  = 0x1004FF5A;
    long osfXK_PrevMenu  = 0x1004FF5B;
    long osfXK_NextMenu  = 0x1004FF5C;
    long osfXK_PrevField  = 0x1004FF5D;
    long osfXK_NextField  = 0x1004FF5E;
    long osfXK_Select  = 0x1004FF60;
    long osfXK_Insert  = 0x1004FF63;
    long osfXK_Undo  = 0x1004FF65;
    long osfXK_Menu  = 0x1004FF67;
    long osfXK_Cancel = 0x1004FF69;
    long osfXK_Help = 0x1004FF6A;
    long osfXK_Delete = 0x1004FFFF;
    long osfXK_Prior = 0x1004FF55;
    long osfXK_Next = 0x1004FF56;

    long  SunXK_FA_Grave  = 0x1005FF00;
    long  SunXK_FA_Circum  = 0x1005FF01;
    long  SunXK_FA_Tilde  = 0x1005FF02;
    long  SunXK_FA_Acute  = 0x1005FF03;
    long  SunXK_FA_Diaeresis  = 0x1005FF04;
    long  SunXK_FA_Cedilla  = 0x1005FF05;

    long  SunXK_F36  = 0x1005FF10; /* Labeled F11 */
    long  SunXK_F37  = 0x1005FF11; /* Labeled F12 */

    long SunXK_Sys_Req     = 0x1005FF60;
    long SunXK_Print_Screen  = 0x0000FF61; /* Same as XK_Print */

    long SunXK_Compose  = 0x0000FF20; /* Same as XK_Multi_key */
    long SunXK_AltGraph  = 0x0000FF7E; /* Same as XK_Mode_switch */

    long SunXK_PageUp  = 0x0000FF55;  /* Same as XK_Prior */
    long SunXK_PageDown  = 0x0000FF56; /* Same as XK_Next */

    long SunXK_Undo  = 0x0000FF65; /* Same as XK_Undo */
    long SunXK_Again  = 0x0000FF66; /* Same as XK_Redo */
    long SunXK_Find  = 0x0000FF68; /* Same as XK_Find */
    long SunXK_Stop  = 0x0000FF69; /* Same as XK_Cancel */
    long SunXK_Props  = 0x1005FF70;
    long SunXK_Front  = 0x1005FF71;
    long SunXK_Copy  = 0x1005FF72;
    long SunXK_Open  = 0x1005FF73;
    long SunXK_Paste  = 0x1005FF74;
    long SunXK_Cut  = 0x1005FF75;

    long SunXK_PowerSwitch  = 0x1005FF76;
    long SunXK_AudioLowerVolume  = 0x1005FF77;
    long SunXK_AudioMute   = 0x1005FF78;
    long SunXK_AudioRaiseVolume  = 0x1005FF79;
    long SunXK_VideoDegauss  = 0x1005FF7A;
    long SunXK_VideoLowerBrightness  = 0x1005FF7B;
    long SunXK_VideoRaiseBrightness  = 0x1005FF7C;
    long SunXK_PowerSwitchShift  = 0x1005FF7D;

}
