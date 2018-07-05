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

/**
 * A document filter that filters entrys that are not positive integers.
 * @see IntFilter
 * @author Robert Hutter
 */
public class PositiveIntFilter extends IntFilter
{
    @Override
    protected boolean test(String text)
    {
        try
        {
          if (text.trim().isEmpty())
          {
            return true;
          }

          if (Integer.parseInt(text) > 0)
          {
              return true;
          }
          
        } catch (NumberFormatException e)
        {
            // Logger.getLogger(PositiveIntFilter.class.getName()).log(Level.WARNING, null, e);
        }
        
        return false;
    }
}
