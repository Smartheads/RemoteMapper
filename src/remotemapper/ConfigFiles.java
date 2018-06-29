/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remotemapper;

/**
 * A collection of configuration files for the application to run.
 * 
 * @author rohu7
 */
public enum ConfigFiles {
    // Files
    MAP("map.d"),
    CMD_PRESETS("presets.xml");
    
    private final String file;
    
    ConfigFiles (String file)
    {
        this.file = file;
    }
    
    public String getName ()
    {
        return file;
    }
}
