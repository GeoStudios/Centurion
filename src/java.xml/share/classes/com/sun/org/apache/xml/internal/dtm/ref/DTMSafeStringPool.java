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

package java.xml.share.classes.com.sun.org.apache.xml.internal.dtm.ref;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/** <p>Like DTMStringPool, but threadsafe. It's been proposed that DTMs
 * share their string pool(s); that raises threadsafety issues which
 * this addresses. Of course performance is inferior to that of the
 * bare-bones version.</p>
 *
 * <p>Status: Passed basic test in main().</p>
 * */
public class DTMSafeStringPool
extends DTMStringPool
{
  public synchronized void removeAllElements()
    {
      super.removeAllElements();
    }

  /** @return string whose value is uniquely identified by this integer index.
   * @throws java.lang.ArrayIndexOutOfBoundsException
   *  if index doesn't map to a string.
   * */
  public synchronized String indexToString(int i)
    throws java.lang.ArrayIndexOutOfBoundsException
    {
      return super.indexToString(i);
    }

  /** @return integer index uniquely identifying the value of this string. */
  public synchronized int stringToIndex(String s)
    {
      return super.stringToIndex(s);
    }

  /** Command-line unit test driver. This test relies on the fact that
   * this version of the pool assigns indices consecutively, starting
   * from zero, as new unique strings are encountered.
   */
  public static void _main(String[] args)
  {
    String[] word={
      "Zero","One","Two","Three","Four","Five",
      "Six","Seven","Eight","Nine","Ten",
      "Eleven","Twelve","Thirteen","Fourteen","Fifteen",
      "Sixteen","Seventeen","Eighteen","Nineteen","Twenty",
      "Twenty-One","Twenty-Two","Twenty-Three","Twenty-Four",
      "Twenty-Five","Twenty-Six","Twenty-Seven","Twenty-Eight",
      "Twenty-Nine","Thirty","Thirty-One","Thirty-Two",
      "Thirty-Three","Thirty-Four","Thirty-Five","Thirty-Six",
      "Thirty-Seven","Thirty-Eight","Thirty-Nine"};

    DTMStringPool pool=new DTMSafeStringPool();

    System.out.println("If no complaints are printed below, we passed initial test.");

    for(int pass=0;pass<=1;++pass)
      {
        int i;

        for(i=0;i<word.length;++i)
          {
            int j=pool.stringToIndex(word[i]);
            if(j!=i)
              System.out.println("\tMismatch populating pool: assigned "+
                                 j+" for create "+i);
          }

        for(i=0;i<word.length;++i)
          {
            int j=pool.stringToIndex(word[i]);
            if(j!=i)
              System.out.println("\tMismatch in stringToIndex: returned "+
                                 j+" for lookup "+i);
          }

        for(i=0;i<word.length;++i)
          {
            String w=pool.indexToString(i);
            if(!word[i].equals(w))
              System.out.println("\tMismatch in indexToString: returned"+
                                 w+" for lookup "+i);
          }

        pool.removeAllElements();

        System.out.println("\nPass "+pass+" complete\n");
      } // end pass loop
  }
} // DTMSafeStringPool