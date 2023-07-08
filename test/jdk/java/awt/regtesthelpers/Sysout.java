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

package test.java.awt.regtesthelpers;

import java.awt.*;

/**
   This class creates a dialog (with the instructions) and is the interface
   for sending text messages to the user.
   To print the instructions, send an array of strings to
   Sysout.createDialogWithInstructions() method.
   Put one line of instructions per array entry.
   To display a message for the tester to see, simply call Sysout.println()
   with the string to be displayed.
   This mimics System.out.println but works within the test harness as well
   as standalone.
   @build TestDialog
 */

public final class Sysout
{
    private static TestDialog dialog;

    public static void createDefaultInstructionDialog()
    {
        final String[] instructions =
        {
            "This is an AUTOMATIC test, simply wait until it is done.",
            "The result (passed or failed) will be shown in the",
            "message window below."
        };
        Sysout.createDialogWithInstructions(instructions);
    }

    public static void createDialogWithInstructions( String[] instructions )
    {
        dialog = new TestDialog(null, "Instructions" );
        dialog.printInstructions( instructions );
        dialog.setVisible(true);
        println( "Any messages for the tester will display here." );
    }

    public static void createDialog( )
    {
        String[] defInstr = { "Instructions will appear here. ",
                              "",
                              "Any messages for the tester will display here." } ;
        createDialogWithInstructions(defInstr);
    }

    public static void printInstructions( String[] instructions )
    {
        dialog.printInstructions( instructions );
    }

    public static void println( String messageIn )
    {
        if (dialog != null){
            dialog.displayMessage( messageIn );
        }
        System.out.println(messageIn);
    }
}// Sysout  class

/**
  This class provides a place for the
  test instructions to be displayed, and a place for interactive messages
  to the user to be displayed.
  To have the test instructions displayed, see Sysout class.
  To have a message to the user be displayed, see Sysout class.
*/

final class TestDialog extends Dialog
{

    private TextArea instructionsText;
    private TextArea messageText;
    private int maxStringLength = 80;

    public TestDialog( Frame frame, String name )
    {
        super( frame, name );
        int scrollBoth = TextArea.SCROLLBARS_BOTH;
        instructionsText = new TextArea( "", 15, maxStringLength, scrollBoth );
        add( "North", instructionsText );

        messageText = new TextArea( "", 5, maxStringLength, scrollBoth );
        add("Center", messageText);

        pack();

        setVisible(true);
    }// TestDialog()

    public void printInstructions( String[] instructions )
    {
        //Clear out any current instructions
        instructionsText.setText( "" );

        //Go down array of instruction strings

        String printStr, remainingStr;
        for( int i=0; i < instructions.length; i++ )
        {
            //chop up each into pieces maxSringLength long
            remainingStr = instructions[ i ];
            while( remainingStr.length() > 0 )
            {
                //if longer than max then chop off first max chars to print
                if( remainingStr.length() >= maxStringLength )
                {
                    //Try to chop on a word boundary
                    int posOfSpace = remainingStr.
                        lastIndexOf( ' ', maxStringLength - 1 );

                    if( posOfSpace <= 0 ) posOfSpace = maxStringLength - 1;

                    printStr = remainingStr.substring( 0, posOfSpace + 1 );
                    remainingStr = remainingStr.substring( posOfSpace + 1 );
                }
                //else just print
                else
                {
                    printStr = remainingStr;
                    remainingStr = "";
                }

                instructionsText.append( printStr + "\n" );

            }// while

        }// for

    }//printInstructions()

    public void displayMessage( String messageIn )
    {
        messageText.append( messageIn + "\n" );
    }
}// TestDialog  class
