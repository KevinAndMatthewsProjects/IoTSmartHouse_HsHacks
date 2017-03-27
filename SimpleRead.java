import java.io.*;
import java.util.*;
import javax.comm.*;

public class SimpleRead implements Runnable, SerialPortEventListener {
    static CommPortIdentifier portId;
    static Enumeration portList;

    InputStream inputStream;
    OutputStream outputStream;
    SerialPort serialPort;
    Thread readThread;

    public static void main(String[] args) {
        try {
            CommPortIdentifier portList1 = CommPortIdentifier.getPortIdentifier("/dev/ttyACM0");
            System.out.println(portList1.getName() + " | " + portList1.getCurrentOwner() + " | " + portList1.getPortType());
        } catch (Exception e) {
            e.printStackTrace();
        }

        portList = CommPortIdentifier.getPortIdentifiers();
        while (portList.hasMoreElements()) {
            portId = (CommPortIdentifier) portList.nextElement();
            System.out.println(portId.getName() + " | " + portId.getCurrentOwner() + " | " + portId.getPortType());
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                if (portId.getName().contains("ttyACM0")) {
                    //                if (portId.getName().equals("/dev/term/a")) {
                    SimpleRead reader = new SimpleRead();
                }
            }
        }
    }

    public SimpleRead() {
        try {
            serialPort = (SerialPort) portId.open("SimpleReadApp", 2000);
        } catch (PortInUseException e) {System.out.println(e);}
        try {
            inputStream = serialPort.getInputStream();
            outputStream = serialPort.getOutputStream();
        } catch (IOException e) {System.out.println(e);}
        try {
            serialPort.addEventListener(this);
        } catch (TooManyListenersException e) {System.out.println(e);}
        serialPort.notifyOnDataAvailable(true);
        try {
            serialPort.setSerialPortParams(9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
        } catch (UnsupportedCommOperationException e) {System.out.println(e);}
        readThread = new Thread(this);
        readThread.start();
        PubnubUtil.init();
        SmartHouse.ardunio = this;
        SmartHouse.start();
    }

    public void write(byte b) {
        try {
            //System.out.println("Attempting to write " + (char)b);
            outputStream.write(new byte[]{b});
        } catch (IOException e) {e.printStackTrace();}
    }

    public void run() {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {System.out.println(e);}
    }

    public void serialEvent(SerialPortEvent event) {
        switch(event.getEventType()) {
            case SerialPortEvent.BI:
            case SerialPortEvent.OE:
            case SerialPortEvent.FE:
            case SerialPortEvent.PE:
            case SerialPortEvent.CD:
            case SerialPortEvent.CTS:
            case SerialPortEvent.DSR:
            case SerialPortEvent.RI:
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                break;
            case SerialPortEvent.DATA_AVAILABLE:
                byte[] readBuffer = new byte[20];

                try {
                    int numBytes = 0;
                    while (inputStream.available() > 0) {
                        numBytes = inputStream.read(readBuffer);
                       // System.out.println(new String(readBuffer));
                    }
                    for(int i = 0; i < numBytes; i++) {
                        SmartHouse.read(readBuffer[i]);
                    }
                   // System.out.print(new String(readBuffer) + " " + numBytes + "\n");
                } catch (IOException e) {System.out.println(e);}
                break;
        }
    }
}
