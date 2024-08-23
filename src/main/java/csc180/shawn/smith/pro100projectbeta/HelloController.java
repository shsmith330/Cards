package csc180.shawn.smith.pro100projectbeta;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.util.*;


public class HelloController {

    @FXML
    private ImageView eCard0 = new ImageView();

    @FXML
    private ImageView eCard1 = new ImageView();

    @FXML
    private ImageView eCard2 = new ImageView();

    @FXML
    private ImageView eCard3 = new ImageView();

    @FXML
    private ImageView eCard4 = new ImageView();

    @FXML
    private Label eHealth = new Label();

    @FXML
    private ProgressBar enemyHPBar;

    @FXML
    private ImageView enemyImage;

    @FXML
    private ImageView pCard0 = new ImageView();

    @FXML
    private ImageView pCard1 = new ImageView();

    @FXML
    private ImageView pCard2 = new ImageView();

    @FXML
    private ImageView pCard3 = new ImageView();

    @FXML
    private ImageView pCard4 = new ImageView();

    @FXML
    private Label pHealth = new Label();

    @FXML
    private ProgressBar playerHPBar;

    @FXML
    private ImageView playerImage = new ImageView();

    private boolean quit = false;
    ChangeScene.Player testPlayer = new ChangeScene.Player(10, "TestP", 1, false);
    Enemy testEnemy = new Enemy(10, "TestE", false);
    ArrayList<Enemy.Card> deck = new ArrayList();
    ArrayList<Enemy.Card> hand = new ArrayList();
    ArrayList<ImageView> handView = new ArrayList();
    ArrayList<ImageView> ehandView = new ArrayList();
    ArrayList<Enemy.Card> ehand = new ArrayList();


    public void setQuit(boolean quit) {
        this.quit = quit;
    }

    public void run() throws IOException, ParseException, URISyntaxException {
        OpponentTurn();
    }

    @FXML
    public void init() throws IOException, ParseException {
        int amount = 20;
        int handSize = 5;
        playerImage.setImage(new Image("https://github.com/M4ne55/Pro100_GoupProject/blob/b61d45efb034e13d71f8480c19829dbca66c76a7/Tragic/CardImages!!!/CardGuard.png"));
        PrintStream var10000 = System.out;
        String var10001 = this.testPlayer.getName();
        var10000.println(var10001 + "             " + this.testEnemy.getName() + "\nHP: " + this.testPlayer.getHp() + "             HP: " + this.testEnemy.getHp() + "\nSheild: " + this.testPlayer.getSheildCount() + "             Sheild: " + this.testEnemy.getSheildCount() + "\n\n");
        this.collectCards(this.deck, amount);
        this.collectCards(this.hand, handSize);
        this.collectCards(this.ehand, handSize);
        setHand();
        seteHand();
    }

    @FXML
    private void setHand() {
        handView.add(pCard0);
        handView.add(pCard1);
        handView.add(pCard2);
        handView.add(pCard3);
        handView.add(pCard4);
        pCard0.setImage(new Image(hand.get(0).getImage()));
        pCard1.setImage(new Image(hand.get(1).getImage()));
        pCard2.setImage(new Image(hand.get(2).getImage()));
        pCard3.setImage(new Image(hand.get(3).getImage()));
        pCard4.setImage(new Image(hand.get(4).getImage()));
    }

    @FXML
    private void seteHand() {
        ehandView.add(eCard0);
        ehandView.add(eCard1);
        ehandView.add(eCard2);
        ehandView.add(eCard3);
        ehandView.add(eCard4);
        eCard0.setImage(new Image(ehand.get(0).getImage()));
        eCard1.setImage(new Image(ehand.get(1).getImage()));
        eCard2.setImage(new Image(ehand.get(2).getImage()));
        eCard3.setImage(new Image(ehand.get(3).getImage()));
        eCard4.setImage(new Image(ehand.get(4).getImage()));
    }

    public void collectCards(ArrayList<Enemy.Card> collection, int size) throws IOException, ParseException {
        if (collection.size() != size) {
            while (collection.size() <= size) {
                Random rand = new Random();
                int r = rand.nextInt(18);
                Object json = null;
                json = (new JSONParser()).parse(new FileReader("src/main/data/cards.json"));
                JSONArray jsonA = (JSONArray) json;
                JSONObject cards = (JSONObject) jsonA.get(r);
                Random rand1 = new Random();
                int k = rand1.nextInt(2);
                switch (k) {
                    case 0:
                        collection.add(new Enemy.attackCards(cards));
                        break;
                    case 1:
                        collection.add(new HelloApplication.DefenseCards(cards));
                }
            }
        }
    }

    public void attackCard() throws IOException, ParseException {
        this.init();
        System.out.println("Pick a card:");
        System.out.println("HAND!!");
        int i = 0;
        Iterator var2 = this.hand.iterator();

        while (var2.hasNext()) {
            Enemy.Card card = (Enemy.Card) var2.next();
            ++i;
            String cardCount = String.format("%d): " + card.getName(), i);
            System.out.println(cardCount);
        }
    }

    public void drawMenu(ActionEvent event) throws IOException, ParseException, URISyntaxException {
        while (!this.quit) {
            this.attackCard();
            Random rand = new Random();
            String choice = event.getSource().toString();
            if (choice.isEmpty()) {
                OpponentTurn();
            }

            if (!this.quit) {
                int intChoice = Integer.parseInt(choice);
                int cv;
                int left;
                if ((this.hand.get(intChoice - 1)).isIsattack()) {
                    cv = Integer.parseInt(((Enemy.Card) this.hand.get(intChoice - 1)).getValue());
                    if (this.testEnemy.getSheildCount() > 0) {
                        this.testEnemy.setSheildCount(this.testEnemy.getSheildCount() - cv);
                        if (this.testEnemy.getSheildCount() < 0) {
                            left = 0 - this.testEnemy.getSheildCount();
                            this.testEnemy.setSheildCount(0);
                            this.testEnemy.setHp(this.testEnemy.getHp() - left);
                            eHealth.setText(String.valueOf(testEnemy.getHp()));
                        }
                    } else {
                        this.testEnemy.setHp(this.testEnemy.getHp() - cv);
                        eHealth.setText(String.valueOf(testEnemy.getHp()));
                        if (this.testEnemy.getHp() < 0) {
                            this.testEnemy.setHp(0);
                        }
                    }
                } else if (!(this.hand.get(intChoice - 1)).isIsattack()) {
                    cv = Integer.parseInt((this.hand.get(intChoice - 1)).getValue());
                    left = this.testPlayer.getSheildCount();
                    this.testPlayer.setSheildCount(left + cv);
                }
                System.out.println(this.hand.get(intChoice - 1).getName());
                this.hand.remove(intChoice - 1);
                winCodition();
            }
        }

    }

    @FXML
    public void OpponentTurn() throws IOException, ParseException, URISyntaxException {
        this.init();
        Random rand = new Random();
        int r = rand.nextInt(1, 6);
        int cv;
        int left;
        if (this.ehand.get(r - 1).isIsattack()) {
            cv = Integer.parseInt((this.ehand.get(r - 1)).getValue());
            if (this.testPlayer.getSheildCount() > 0) {
                this.testPlayer.setSheildCount(this.testPlayer.getSheildCount() - cv);
                if (this.testPlayer.getSheildCount() < 0) {
                    left = 0 - this.testPlayer.getSheildCount();
                    this.testPlayer.setSheildCount(0);
                    this.testPlayer.setHp(this.testPlayer.getHp() - left);
                    pHealth.setText(String.valueOf(testPlayer.getHp()));
                }
            } else {
                this.testPlayer.setHp(this.testPlayer.getHp() - cv);
                pHealth.setText(String.valueOf(testPlayer.getHp()));
                if (this.testPlayer.getHp() < 0) {
                    this.testPlayer.setHp(0);
                }
            }
        } else if (!(this.ehand.get(r - 1)).isIsattack()) {
            cv = Integer.parseInt((this.ehand.get(r - 1)).getValue());
            left = this.testEnemy.getSheildCount();
            this.testEnemy.setSheildCount(left + cv);
        }
        System.out.println(this.ehand.get(r - 1).getName());
        this.ehand.remove(r - 1);
        this.winCodition();
    }

    @FXML
    public void winCodition() throws IOException, ParseException, URISyntaxException {
        if (this.testPlayer.getHp() <= 0) {
            this.init();
            System.out.println("Enemy Wins");
            this.quit = true;
            Turns.setQuit(true);
        } else if (this.testEnemy.getHp() <= 0) {
            this.init();
            System.out.println("Player Wins");
            this.quit = true;
            Turns.setQuit(true);

        } else {
            new Turns().run();
        }
    }

    @FXML
    public void gameStart() throws IOException, ParseException {
        new Turns().start();
    }

    public static class Turns extends TimerTask {
        private static int timedTurn = 0;
        private static HelloController c;

        static {
            c = new HelloController();
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

        @FXML
        public static void gameTurn() throws IOException, ParseException, URISyntaxException {
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

        @FXML
        public void start() {
            run();
        }

        @FXML
        public void run() {
            final Timer t = new Timer();
            TimerTask up = new TimerTask() {
                public void run() {
                    Platform.runLater(() -> {//Thank you Chat Gpt
                        if (!Turns.quit) {
                            ++Turns.timedTurn;
                            try {
                                System.out.println(timedTurn);
                                Turns.gameTurn();
                            } catch (ParseException | IOException var2) {
                                Exception e = var2;
                                throw new RuntimeException(e);
                            } catch (URISyntaxException e) {
                                throw new RuntimeException(e);
                            }
                        } else {
                            t.cancel();
                            try {
                                ChangeScene.changeSceneSimple("EndScreen.fxml");
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }

                    });
                }
            };
            t.schedule(up, 5000, 10000);
        }

        public static enum Classes {
            SQUIRE,
            KNIGHT,
            TANK,
            ROGUE;

            private Classes() {
            }
        }
    }
}
