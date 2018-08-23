/*
 * The MIT License
 *
 * Copyright 2018 Robert Hutter
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package serial;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import remotemapper.exceptions.SerialException;

/**
 *
 * @author Robert Hutter
 */
public class RMSerialHandler extends SerialHandler
{
    private StringBuilder requestBuilder;
    
    {
        requestBuilder = new StringBuilder("");
    }
    
    @Override
    public void open(String port, int rate, String charset) throws SerialException
    {
        this.port = SerialPort.getCommPort(port);
        this.port.setBaudRate(rate);
        if (!this.port.openPort())
            throw new SerialException();
        
        this.port.addDataListener(new SerialPortDataListener()
        {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
            }

            @Override
            public void serialEvent(SerialPortEvent spe) {
                if (spe.getEventType() == SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
                {
                    try {
                        RMSerialHandler.this.processRequest(port.getBytes(charset));
                    } catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(RMSerialHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
    
    private void processRequest(byte[] data)
    {
        requestBuilder.append(new String(data));
        
        if (requestBuilder.toString().contains(";"))
        {
            /* A full request has arrived. Process */
            char[] text = requestBuilder.toString().toCharArray();
            StringBuilder request = new StringBuilder("");
            ArrayList<String> args = new ArrayList<>();
            
            for (int i = 0; i < text.length; i++)
            {
                if (text[i] != ';')
                {
                    if (text[i] == '(')
                    {
                        
                    }
                }
                else
                {
                    break;
                }
            }
        }
    }
}
