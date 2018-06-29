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
public class AngleFilter extends DocumentFilter
{
    final int limit = 6;
  
  @Override
  public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException
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

      float i = Float.parseFloat(text);
      
      if (i < 360 && i >= 0)
        return true;
      else
          return false;
      
    } catch (NumberFormatException e)
    {
      return false;
    }
  }

  @Override
  public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException
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
  public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException
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
