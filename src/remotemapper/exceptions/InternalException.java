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
package remotemapper.exceptions;

/**
 *
 * @author rohu7
 */
public class InternalException extends Exception {

    /**
     * Creates a new instance of <code>InternalException</code> without detail
     * message.
     */
    public InternalException() {
    }

    /**
     * Constructs an instance of <code>InternalException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InternalException(String msg) {
        super(msg);
    }
}
