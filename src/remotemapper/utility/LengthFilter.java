/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remotemapper.utility;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author rohu7
 */
public class LengthFilter extends DocumentFilter
{
   int limit = 5;
    
  public LengthFilter (int limit)
  {
      this.limit = limit;
  }
  
  @Override
  public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException
  {
    Document doc = fb.getDocument();
    StringBuilder sb = new StringBuilder();
    sb.append(doc.getText(0, doc.getLength()));
    sb.insert(offset, string);

    super.insertString(fb, offset, string, attr);
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

  @Override
  public void remove(FilterBypass fb, int offset, int length) throws BadLocationException
  {
    Document doc = fb.getDocument();
    StringBuilder sb = new StringBuilder();
    sb.append(doc.getText(0, doc.getLength()));
    sb.delete(offset, offset + length);
    
    super.remove(fb, offset, length);
  }
}
