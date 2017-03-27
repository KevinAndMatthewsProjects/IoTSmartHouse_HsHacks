import javax.swing.*;
import java.applet.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.text.*;

public class Clock {

    int width, height;
    Thread t = null;
    boolean threadSuspended;
    int hours=0, minutes=0, seconds=0;
    String timeString = "";

    private JFrame frame;
    private BufferedImage buffer;
    private Graphics bufferG;
    private BufferedImage screen;
    private Graphics screenG;

    public Clock(int width, int height) {
        this.width = width;
        this.height = height;
        JFrame frame = new JFrame();
        frame.setSize(width, height);
        buffer = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        bufferG = buffer.createGraphics();
        screen = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        screenG = screen.createGraphics();
        frame.add(new JLabel(new ImageIcon(screen)));
        frame.pack();
        frame.setVisible(true);
    }



    public void run(int min) {
        try {

               // Calendar cal = Calendar.getInstance();
                hours = min/60;
                if ( hours > 12 ) hours -= 12;
                 minutes = min%60;
               //seconds = cal.get( Calendar.SECOND );

                SimpleDateFormat formatter
                        = new SimpleDateFormat( "hh:mm:ss", Locale.getDefault() );
                //Date date = cal.getTime();
                //timeString = formatter.format( date );
                timeString = (minutes/60) + ":" + (minutes%60);
                // Now the thread checks to see if it should suspend itself

                paint();

                screenG.drawImage(buffer, 0, 0, null);

                t.sleep( 1000 );  // interval specified in milliseconds

        }
        catch (Exception e) { }
    }

    void drawHand( double angle, int radius ) {
        angle -= 0.5 * Math.PI;
        int x = (int)( radius*Math.cos(angle) );
        int y = (int)( radius*Math.sin(angle) );
        bufferG.drawLine( width/2, height/2, width/2 + x, height/2 + y );
    }

    void drawWedge( double angle, int radius ) {
        angle -= 0.5 * Math.PI;
        int x = (int)( radius*Math.cos(angle) );
        int y = (int)( radius*Math.sin(angle) );
        angle += 2*Math.PI/3;
        int x2 = (int)( 5*Math.cos(angle) );
        int y2 = (int)( 5*Math.sin(angle) );
        angle += 2*Math.PI/3;
        int x3 = (int)( 5*Math.cos(angle) );
        int y3 = (int)( 5*Math.sin(angle) );
        bufferG.drawLine( width/2+x2, height/2+y2, width/2 + x, height/2 + y );
        bufferG.drawLine( width/2+x3, height/2+y3, width/2 + x, height/2 + y );
        bufferG.drawLine( width/2+x2, height/2+y2, width/2 + x3, height/2 + y3 );
    }

    public void paint(  ) {

        bufferG.setColor( Color.gray );
        drawWedge( 2*Math.PI * hours / 12, width/5);
        drawWedge( 2*Math.PI * minutes / 60, width/3);
       // drawHand( 2*Math.PI * seconds / 60, width/2, g );
        bufferG.setColor( Color.white );
        bufferG.drawString( timeString, 10, height-10 );
    }
}
