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
package remotemapper.data;

/**
 * A enum containing rover commands.
 * 
 * @author Robert Hutter
 */
public enum RoverCommands {
    FORWARDS("forward"),
    BACKWARDS("backward"),
    LEFT("left"),
    RIGHT("right");
    
    private final String command;
    
    RoverCommands (String cmd)
    {
        this.command = cmd;
    }
    
    public String getText ()
    {
        return this.command;
    }
}