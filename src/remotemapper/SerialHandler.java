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

package remotemapper;

import remotemapper.exceptions.SerialException;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author Robert Hutter
 */
public class SerialHandler {
    private final JTextArea out;
    private SerialPort port;
    private java.io.File streamFile;
    public boolean streamOn;
    private java.io.FileWriter fw;

    SerialHandler() {
        out = null;
    }
    
    public void close ()
    {
        if (port != null)
            port.closePort();
    }
    
    public void open (String port, int rate, String charset) throws SerialException
    {
        this.port = SerialPort.getCommPort(port);
        this.port.setBaudRate(rate);
        if (!this.port.openPort())
            throw new SerialException();
        
        this.port.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_AVAILABLE; }
            @Override
            public void serialEvent(SerialPortEvent event)
            {
                if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
                    return;
                
                final String data = getIncommingData (charset);
                
                out.append(data);
                
                if (streamOn && streamFile != null)
                {
                    try {
                        fw.write (data);
                        fw.flush();
                    } catch (IOException ex) {
                        Logger.getLogger(SerialHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
    
    public void send (String data, String charset) throws SerialException
    {
        try {
            send (data.getBytes(charset));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SerialHandler.class.getName()).log(Level.SEVERE, null, ex);
            send (data.getBytes());
        }
    }
    
    public void send (byte[] data) throws SerialException
    {
        if (this.port != null)
        {
            if (this.port.isOpen())
            {
                this.port.writeBytes(data, data.length);
            }
            else
            {
               throw new SerialException(); 
            }
        } else
        {
            throw new SerialException();
        }
    }
    
    private String getIncommingData (String charset)
    {
        byte[] newData = new byte[port.bytesAvailable()];
        port.readBytes(newData, newData.length);
        
        try
        {
            System.out.print(new String (newData, charset));
            return (new String (newData, charset));
        } catch (UnsupportedEncodingException e)
        {
            System.out.print(new String (newData));
            return (new String (newData));
        }
    }
    
    /**
     *
     * @param port
     * @param rate
     * @param charset
     * @throws Exception
     */
    public void update (String port, int rate, String charset) throws Exception
    {
        this.close();
        this.open (port, rate, charset);
    }
    
    public SerialHandler (JTextArea output)
    {
        this.out = output;
        this.port = null;
    }
    
    public boolean isOpen ()
    {
        if (this.port != null)
            return this.port.isOpen();
        else
            return false;
    }
    
    public String portName ()
    {
        return this.port.getSystemPortName();
    }
    
    public static SerialPort[] getCommPorts()
    {
        return SerialPort.getCommPorts();
    }
    
    public void setStreamFile (java.io.File file, boolean overwrite) throws IOException
    {
        this.streamFile = file;
        this.fw = new java.io.FileWriter(this.streamFile, !overwrite);
    }
    
    public java.io.File getStreamFile ()
    {
            return this.streamFile;
    }
}
