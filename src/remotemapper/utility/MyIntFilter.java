/*
 * Copyright (C) 2018 Robert Hutter
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package remotemapper.utility;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

public class MyIntFilter extends DocumentFilter {
  final int limit = 10;
  
  @Override
  public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException
  {
    Document doc = fb.getDocument();
    StringBuilder sb = new StringBuilder();
    sb.append(doc.getText(0, doc.getLength()));
    sb.insert(offset, string);

    if (test(sb.toString()))
    {
      super.insertString(fb, offset, string, attr);
    } else
    {
      // warn the user and don't allow the insert
        
    }
  }

  private boolean test(String text) {
    try
    {
      if (text.trim().isEmpty())
      {
        return true;
      }

      Integer.parseInt(text);
      return true;
    } catch (NumberFormatException e)
    {
      return false;
    }
  }

  @Override
  public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException
  {  
    Document doc = fb.getDocument();
    StringBuilder sb = new StringBuilder();
    sb.append(doc.getText(0, doc.getLength()));
    sb.replace(offset, offset + length, text);

    if (test(sb.toString()))
    {
      // this part is check if the new input plus old length - replace length with
      // the limited.
      if(doc.getLength()  +  text.length() - length <= limit)
      {
        super.replace(fb, offset, length, text, attrs);
      }
    } else
    {
      // warn the user and don't allow the insert
        
    }

  }

  @Override
  public void remove(FilterBypass fb, int offset, int length) throws BadLocationException
  {
    Document doc = fb.getDocument();
    StringBuilder sb = new StringBuilder();
    sb.append(doc.getText(0, doc.getLength()));
    sb.delete(offset, offset + length);

    if (test(sb.toString()))
    {
      super.remove(fb, offset, length);
    } else
    {
      // warn the user and don't allow the insert
        
    }

  }
}