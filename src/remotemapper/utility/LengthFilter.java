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

/**
 * A document filter that only allows a certain amount of characters to be
 * entered. Counts whitespace.
 * 
 * @author Robert Hutter
 */
public class LengthFilter extends DocumentFilter
{
    protected int limit = 10;
    /**
     *
     * @param limit
     */
    public LengthFilter(int limit) {
        this.limit = limit;
    }
    
    @Override
  public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException
  {  
    Document doc = fb.getDocument();
    StringBuilder sb = new StringBuilder();
    sb.append(doc.getText(0, doc.getLength()));
    sb.replace(offset, offset + length, text);

    if(doc.getLength()  +  text.length() - length <= limit)
    {
        super.replace(fb, offset, length, text, attrs);
    }
  }
    
    /**
     *
     * @return
     */
    public int getLimit()
    {
        return limit;
    }
    
    /**
     *
     * @param limit
     */
    public void setLimit(int limit)
    {
        this.limit = limit;
    }
}