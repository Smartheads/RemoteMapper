
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import remotemapper.classes.CommandPreset;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rohu7
 */
public class TestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JDOMException {
        try {
            /*
            try {
            Element root = new Element ("command_presets");
            Document doc = new Document(root);
            Element preset = new Element("preset");
            preset.setAttribute(new Attribute("name", "Preset 1"));
            
            Element cmd = new Element("command");
            cmd.setText("");
            
            Element desc = new Element("description");
            desc.setText("");
            
            preset.addContent(cmd);
            preset.addContent(desc);
            
            root.addContent(preset);
            
            XMLOutputter XMLOutput = new XMLOutputter();
            XMLOutput.setFormat(Format.getPrettyFormat());
            XMLOutput.output(doc, new FileOutputStream(new File ("testXML.xml")));
            
            } catch (FileNotFoundException ex) {
            Logger.getLogger(TestMain.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
            Logger.getLogger(TestMain.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
            SAXBuilder saxBuilder = new SAXBuilder();
            Document doc = saxBuilder.build(new File (""));
            
            Element root = doc.getRootElement();
            List<Element> subElements = root.getChildren();
            
            // Try to find prseset and get update its element
            Element preset = null;
            for (int i = 0; i < subElements.size(); i++)
            {
                if (subElements.get(i).getAttribute("name").getValue().equals("Preset 1"))
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
                
                for (int i = 0; i < data.size(); i++)
                {
                    switch (data.get(i).getName())
                    {
                        case "name":
                            data.get(i).setText("Preset 2");
                            break;
                            
                        case "command":
                            data.get(i).setText("System.exit(0)");
                            break;
                            
                        case "description":
                            data.get(i).setText("Stops the program");
                            break;
                    }
                }
            }
            else
            {
                // Append the new preset
                preset = new Element("preset");
                preset.setAttribute(new Attribute("name", "Stuped preset!"));
                
                Element cmd = new Element("command");
                cmd.setText("blablabla");
                
                Element desc = new Element("description");
                desc.setText("Does nothing!");
                
                preset.addContent(cmd);
                preset.addContent(desc);
                
                root.addContent(preset);
            }
            
            // Output to file
            XMLOutputter XMLOutput = new XMLOutputter();
            XMLOutput.setFormat(Format.getPrettyFormat());
            XMLOutput.output(root, new FileOutputStream(new File("testXML2.xml")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TestMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TestMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
            
            SAXBuilder sax = new SAXBuilder();
            Document doc = sax.build(new File("C:\\Users\\rohu7\\OneDrive\\Smart Crew\\RM workspace\\presets.xml"));
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
            
            CommandPreset[] cpa = new CommandPreset[6];
            
            System.out.println(cps.size());
            
            for (int i = 0; i < cpa.length; i++)
            {
                cpa[i] = cps.get(i);
                
            }
        
        }   catch (IOException ex) {
            Logger.getLogger(TestMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
