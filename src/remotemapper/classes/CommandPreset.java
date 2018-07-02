/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package remotemapper.classes;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import remotemapper.exceptions.LoadWorkspaceException;

/**
 * A Class for holding command presets.
 * 
 * @author Robert Hutter
 */
public class CommandPreset
{

    /**
     * String command containing the presets command.
     */
    protected String command;

    /**
     * String containing the presets name.
     */
    protected String name;

    /**
     * String containing the presets short description.
     */
    protected String description;
    
    /**
     * Integer that contains the presets id. Constant.
     */
    private final int id;
    
    /**
     *
     * @param name Presets name
     * @param command Presets command
     * @param description Presets short description
     * @param id Presets ID number
     */
    public CommandPreset (String name, String command, String description, int id)
    {
        this.name = name;
        this.command = command;
        this.description = description;
        this.id = id;
    }
    
    /**
     *
     * @param name Presets name
     * @param id Presets ID number
     */
    public CommandPreset (String name, int id)
    {
        this.name = name;
        this.command = "";
        this.description = "";
        this.id = id;
    }
    
    /**
     * Creates an empty preset. ID initialized to 0.
     */
    public CommandPreset ()
    {
        this.command = "";
        this.name = "";
        this.description = "";
        this.id = 0;
    }
    
    /**
     * Save preset to specified output file in XML format.
     * 
     * @param out File to save to
     * @throws IOException
     */
    public void saveToFile (File out) throws IOException
    {
        /*
        * Check to see if file is a valid XML,
        * if is, append / overwrite command preset,
        * if not, save command preset in a new XML.
        */
        
        FileOutputStream fos = null;
        
        try
        {
            SAXBuilder saxBuilder = new SAXBuilder();
            Document doc = saxBuilder.build(out);
            
            Element root = doc.getRootElement();
            List<Element> subElements = root.getChildren();
            
            // Try to find preset and get update its element
            Element preset = null;
            for (int i = 0; i < subElements.size(); i++)
            {
                if (subElements.get(i).getAttributeValue("id").equals(""+this.id))
                {
                    preset = subElements.get(i);
                    break;
                }
            }
            
            // Check to see if we found the element:
            if (preset != null)
            {
                // Update the element's data
                List<Element> data = preset.getChildren();
                preset.setAttribute("name", this.name);
                
                for (int i = 0; i < data.size(); i++)
                {
                    switch (data.get(i).getName())
                    {                          
                        case "command":
                            data.get(i).setText(this.command);
                            break;
                            
                        case "description":
                            data.get(i).setText(this.description);
                            break;
                    }
                }
            }
            else
            {
                // Append the new preset
                preset = new Element("preset");
                preset.setAttribute(new Attribute("name", this.name));
                preset.setAttribute(new Attribute("id", ""+this.id));
                
                Element cmd = new Element("command");
                cmd.setText(this.command);
                
                Element desc = new Element("description");
                desc.setText(this.description);
                
                preset.addContent(cmd);
                preset.addContent(desc);
                
                root.addContent(preset);
            }
            
            // Output to file
            fos = new FileOutputStream(out);
            
            XMLOutputter XMLOutput = new XMLOutputter();
            XMLOutput.setFormat(Format.getPrettyFormat());
            XMLOutput.output(root, fos);
            
            fos.flush();
            fos.close();
        }
        catch (JDOMException | FileNotFoundException | NullPointerException jdome)
        {
            Logger.getLogger(CommandPreset.class.getName()).log(Level.WARNING, null, jdome);
            
            try 
            {
                // Write new XML file
                Element presetsElement = new Element("command_presets");
                Document doc = new Document (presetsElement);
                
                Element preset = new Element("preset");
                preset.setAttribute(new Attribute("name", this.name));
                preset.setAttribute(new Attribute("id", ""+this.id));
                
                Element cmd = new Element("command");
                cmd.setText(this.command);
                
                Element desc = new Element("description");
                desc.setText(this.description);
                
                preset.addContent(cmd);
                preset.addContent(desc);
                
                presetsElement.addContent(preset);
                
                fos = new FileOutputStream(out);
                
                XMLOutputter XMLOutput = new XMLOutputter();
                XMLOutput.setFormat(Format.getPrettyFormat());
                XMLOutput.output(doc, fos);
                
                fos.flush();
                fos.close();
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(CommandPreset.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally
            {
                try
                {
                    fos.close();
                }
                catch (IOException e)
                {
                    Logger.getLogger(CommandPreset.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }
    
    /**
     * Load all presets form a file saved with the XML format.
     * 
     * @param file File to read data from.
     * @return An arrayList containing the presets.
     * @throws remotemapper.exceptions.LoadWorkspaceException
     */
    public static ArrayList<CommandPreset> loadPresets (File file) throws LoadWorkspaceException
    {
        try {
            SAXBuilder sax = new SAXBuilder();
            Document doc = sax.build(file);
            Element root = doc.getRootElement();
            
            ArrayList<CommandPreset> cps = new ArrayList();
            List<Element> presets = root.getChildren();
            
            for (int i = 0; i < presets.size(); i++)
            {
                cps.add(new CommandPreset(presets.get(i).getAttributeValue("name"), i));
                
                for (Element d : presets.get(i).getChildren())
                {
                    switch (d.getName())
                    {
                        case "command":
                            cps.get(i).setCommand(d.getText());
                            break;
                            
                        case "description":
                            cps.get(i).setDescription(d.getText());
                            break;
                    }
                }
            }
            
            return cps;
        }
        catch (IOException | JDOMException ex)
        {
            Logger.getLogger(CommandPreset.class.getName()).log(Level.WARNING, null, ex);
            
            // Throw unified exception to let processing know, load failed
            throw new LoadWorkspaceException();
        }
    }
    
    /**
     * 
     * @return 
     */
    public int getId ()
    {
       return id; 
    }

    /**
     *
     * @return
     */
    public String getCommand() {
        return command;
    }

    /**
     *
     * @param command
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
