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
import java.io.*;
import java.util.*;
// import javax.servlet.*;
// import javax.servlet.http.*;

public class TemperatureTableServlet /* extends HttpServlet */{
        public static void main(String[] args) {
//      public void doGet(HttpServletRequest request, HttpServletResponse response)
//              throws IOException, ServletException {
                HelloWorld.main(args); // so we can we non-JSP code too
                PrintStream out = System.out;
//              response.setContentType("text/html");
//              PrintWriter out = response.getWriter();
//@line 1
                out.println("<html>");
//@line 2
                out.println("<head>");
//@line 3
                out.println("   <title>Temperature Table</title>");
//@line 4
                out.println("</head>");
//@line 5
                out.println("<body>");
//@line 6
                out.println("");
//@line 7
                out.println("<h1>Temperature Table</h1>");
//@line 8
                out.println("<p>American tourists visiting Canada can use this handy temperature");
//@line 9
                out.println("table which converts from Fahrenheit to Celsius:");
//@line 10
                out.println("<br><br>");
//@line 11
                out.println("");
//@line 12
                out.println("<table BORDER COLS=2 WIDTH=\"20%\" >");
//@line 13
                out.println("<tr BGCOLOR=\"#FFFF00\">");
//@line 14
                out.println("<th>Fahrenheit</th>");
//@line 15
                out.println("<th>Celsius</th>");
//@line 16
                out.println("</tr>");
//@line 17
                out.println("");
//@line 18
                for (int i = 0; i <= 100; i += 10) {
//@line 19
                        out.println("<tr ALIGN=RIGHT BGCOLOR=\"#CCCCCC\">");
//@line 20
                        out.println("<td>" + i + "</td>");
//@line 21
                        out.println("<td>" + ((i - 32) * 5 / 9) + "</td>");
//@line 22
                        out.println("</tr>");
//@line 23
                }
//@line 24
                out.println("");
//@line 25
                out.println("</table>");
//@line 26
                out.println("<p><i>Created " + new Date () + "</i></p>");
//@line 27
                out.println("");
//@line 28
                out.println("</body>");
//@line 29
                out.println("</html>");
        }
}
