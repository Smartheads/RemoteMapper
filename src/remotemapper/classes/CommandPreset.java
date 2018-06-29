/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package remotemapper.classes;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * A Class for holding command presets.
 * 
 * @author rohu7
 */
public class CommandPreset
{
    protected String command;
    protected String name;
    protected String description;
    
    public CommandPreset (String name, String command, String description)
    {
        this.name = name;
        this.command = command;
        this.description = description;
    }
    
    public CommandPreset ()
    {
        this.command = "";
        this.name = "";
        this.description = "";
    }
    
    public void saveToFile (File out) throws IOException
    {
        /*
        * Check to see if file is a valid XML,
        * if is, append / overwrite command preset,
        * if not, save command preset in a new XML.
        */
        try
        {
            SAXBuilder saxBuilder = new SAXBuilder();
            Document doc = saxBuilder.build(out);
            
            Element root = doc.getRootElement();
            List<Element> subElements = root.getChildren();
        }
        catch (JDOMException jdome)
        {
            Element presetsElement = new Element("command presets");
            Document doc = new Document (presetsElement);
            
            Element preset = new Element("preset");
            preset.setAttribute(new Attribute("name", this.name));
            
            Element cmd = new Element("command");
            cmd.setText(this.command);
            
            Element desc = new Element("description");
            desc.setText(this.description);
            
            preset.addContent(cmd);
            preset.addContent(desc);
            
            XMLOutputter XMLOutput = new XMLOutputter();
            
            XMLOutput.setFormat(Format.getPrettyFormat());
            XMLOutput.output(doc, new FileWriter(out));
        }
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
