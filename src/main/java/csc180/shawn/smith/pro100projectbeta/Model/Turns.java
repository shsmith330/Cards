//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package csc180.shawn.smith.pro100projectbeta.Model;


import csc180.shawn.smith.pro100projectbeta.HelloController;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;

public class Turns extends TimerTask {
    private static int timedTurn = 0;
    private static HelloController c;

    static {
        try {
            c = new HelloController();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean quit = false;

    public Turns() {
    }

    public static void setQuit(boolean quit) {
        Turns.quit = quit;
    }

    public static int getTimedTurn() {
        return timedTurn;
    }

    public static void gameTurn() throws IOException, ParseException, URISyntaxException {
        final Timer t = new Timer();
        TimerTask up = new TimerTask() {
            public void run() {
                if (!Turns.quit) {
                    ++Turns.timedTurn;
                    try {
                        Turns.gameTurn();
                    } catch (ParseException | IOException var2) {
                        Exception e = var2;
                        throw new RuntimeException(e);
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    t.cancel();
                    System.exit(0);
                }

            }
        };
        t.schedule(up, 5000L, 5000L);
        c.init();
        if (timedTurn % 2 == 0) {
            System.out.println("Player turn");
            c.setQuit(false);
            c.run();
        } else {
            System.out.println("Opponent turn");
            c.OpponentTurn();
        }

    }

    public void run() {

    }
}
