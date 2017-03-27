import java.util.Arrays;

/**
 * Created by matthew on 3/26/17.
 */
public class SmartHouse {

    private static Neural neural;
    private static boolean light1 = false;
    private static boolean light2 = false;
    private static boolean auto = false;
    private static boolean servo = false;
    public static SimpleRead ardunio;

    private static long lastTime = System.nanoTime();
    private static long startTime = System.nanoTime();
    private static double timeMin = 0;
    private static int day = 0;
    private static final double timeScale = 2*60*60;

    private static double timeOn = 0;
    private static double timeOnC = 0;
    private static double timeOff = 0;
    private static double timeOffC = 0;

    public static void read(byte b) {
       // System.out.println((char)b);
      //  write((byte)('b'));
    }

    public static void write(byte b) {
        ardunio.write(b);
    }

    public static void process(String s) {
       // System.out.println("Got " + s);
        if(s.contains("on1")) {
           light1 = true;
            System.out.println("Turning on light 1");
            //neural.addData(new double[]{timeMin, day}, new double[]{1});
            timeOn = (timeOn*timeOnC+timeMin)/(++timeOnC);

        } else if(s.contains("off1")) {
            light1 = false;
            System.out.println("Turning off light 1");
            //neural.addData(new double[]{timeMin, day}, new double[]{0});
            timeOff = (timeOff*timeOffC+timeMin)/(++timeOffC);
        } else if(s.contains("on2")) {
            light2 = true;
            System.out.println("Turning on light 2");
            //neural.addData(new double[]{timeMin, day}, new double[]{1});
        } else if(s.contains("off2")) {
            light2 = false;
            System.out.println("Turning off light 2");
            //neural.addData(new double[]{timeMin, day}, new double[]{1});
        } else if(s.contains("onA")) {
            auto = true;
            System.out.println("Turning on Auto");
            //neural.addData(new double[]{timeMin, day}, new double[]{1});
        } else if(s.contains("offA")) {
            auto = false;
            light1 = false;
            System.out.println("Turning off Auto");
            //neural.addData(new double[]{timeMin, day}, new double[]{1});
        } else if(s.contains("onS")) {
            servo = true;
            System.out.println("Turning on Servo");
            //neural.addData(new double[]{timeMin, day}, new double[]{1});
        } else if(s.contains("offS")) {
            servo = false;
            System.out.println("Turning off Servo");
            //neural.addData(new double[]{timeMin, day}, new double[]{1});
        }
    }

    public static synchronized void start() {
        //Clock c = new Clock(500, 500);
        neural = new Neural(2, 1);
        System.out.println("Starting smart house");
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                int b = 0;
                while(true) {

                    startTime = System.nanoTime();
                    long deltaTime = startTime - lastTime;
                    timeMin += (deltaTime/1000000000d/60d)*timeScale;
                    if(timeMin > 24*60) {
                        timeMin=0;
                        day++;
                    }
                    if(day > 6) {
                        day = 0;
                    }
                    if(b++ > 1000000) {
                        b = 0;
                       // c.run((int)timeMin);
                        System.out.println(Math.round(timeMin/60) + ":" + Math.round(timeMin%60)); //+ " " + timeMin + " " + timeOn + " " + timeOff);
                    }

                        if (light1) {
                            //System.out.println("asd");
                            write((byte) ('b'));

                        }
                    if(auto)  {
                       // double[] out = neural.calculate(new double[]{timeMin, day});
                        //System.out.println(Arrays.toString(out));
                        if(timeMin > timeOn && timeMin < timeOff) {
                            light1 = true;
                        } else {
                            light1 = false;
                        }
                    }
                        if (light2) {
                            write((byte) ('d'));
                        }

                        if(servo) {
                            write((byte)('f'));
                        }

                    Thread.yield();
                    lastTime = startTime;
                }
            }
        });
        t.start();
    }

}
