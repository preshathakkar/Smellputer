/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smell.component.controls;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;

/**
 *
 * @author Presha Thakkar
 */
public class SerialClass implements SerialPortEventListener{
    
    public SerialPort serialPort;
/** The port we're normally going to use. */
private static final String PORT_NAMES[] = {
"/dev/tty.usbserial-A9007UX1", // Mac OS X
"/dev/ttyUSB0", // Linux
"COM21", // Windows
};

public static BufferedReader input;
public static OutputStream output;
/** Milliseconds to block while waiting for port open */
public static final int TIME_OUT = 2000;
/** Default bits per second for COM port. */
public static final int DATA_RATE = 9600;

public void initialize() {
CommPortIdentifier portId = null;
Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
//First, Find an instance of serial port as set in PORT_NAMES.
while (portEnum.hasMoreElements()) {
CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
for (String portName : PORT_NAMES) {
if (currPortId.getName().equals(portName)) {
portId = currPortId;
break;
}
}
}
if (portId == null) {
    System.out.println("Hello");
System.out.println("Could not find COM port.");
return;
}
try {
// open serial port, and use class name for the appName.
serialPort = (SerialPort) portId.open(this.getClass().getName(),
TIME_OUT);
// set port parameters
serialPort.setSerialPortParams(DATA_RATE,
SerialPort.DATABITS_8,
SerialPort.STOPBITS_1,
SerialPort.PARITY_NONE);
// open the streams
input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
output = serialPort.getOutputStream();
char ch = 1;
output.write(ch);
// add event listeners
serialPort.addEventListener(this);
serialPort.notifyOnDataAvailable(true);
} catch (Exception e) {
System.err.println(e.toString());
}
}
public synchronized void close() {
if (serialPort != null) {
serialPort.removeEventListener();
serialPort.close();
}
}
public synchronized void serialEvent(SerialPortEvent oEvent) {
if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
try {
String inputLine=input.readLine();
System.out.println(inputLine);
} catch (Exception e) {
System.err.println(e.toString());
}
}
}
public synchronized void writeData(String data) {
System.out.println("Sent: " + data);

try {
output.write(data.getBytes());  
//output.flush();
} catch (Exception e) {
e.printStackTrace();
System.out.println("could not write to port");


}
}

}