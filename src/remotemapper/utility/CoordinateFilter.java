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
 * A document filter that filters coodinates. That means the following chars
 * are allowed: Positive integers, ',', '\n'
 * 
 * @author Robert Hutter
 */
public class CoordinateFilter extends IntFilter
{

    /**
     * Instances a new coordinate filter.
     */
    public CoordinateFilter ()
    {
        this.limit = Integer.MAX_VALUE;
    }
    
    @Override
    protected boolean test(String test)
    {
        return test.matches("^[0-9,\\s\n]*$");
    }
}
