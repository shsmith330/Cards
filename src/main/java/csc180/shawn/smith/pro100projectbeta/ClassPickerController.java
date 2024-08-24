package csc180.shawn.smith.pro100projectbeta;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ClassPickerController {
    public ArrayList<String> classLi=new ArrayList<>();
    public ArrayList<String> nameLi=new ArrayList<>();
    public ArrayList<String> descLi=new ArrayList<>();

    public ArrayList<String> getClassLi() {
        try {
            Object json = new JSONParser().parse(new FileReader("data/class.json"));
            System.out.println("fileReaderWorked");
            JSONArray jsonA = (JSONArray) json;
            for (int i = 0; i < jsonA.size(); i++) {
                JSONObject customerR = (JSONObject) jsonA.get(i);
               // System.out.println("json object Created");
                classLi.add(customerR.get("url").toString() );
               // System.out.println("image created");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return classLi;
    }
    public ArrayList<String> getNameLi() {
        try {
            Object json = new JSONParser().parse(new FileReader("data/class.json"));
            System.out.println("fileReaderWorked2");

            JSONArray jsonA = (JSONArray) json;
            for (int i = 0; i < jsonA.size(); i++) {
                JSONObject customerR = (JSONObject) jsonA.get(i);
                //System.out.println("Made it");
                nameLi.add((String) customerR.get("name").toString());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return nameLi;
    }
    public ArrayList<String> getDescLi() {
        try {
            Object json = new JSONParser().parse(new FileReader("data/class.json"));
            //System.out.println("made it");
            JSONArray jsonA = (JSONArray) json;
            for (int i = 0; i < jsonA.size(); i++) {
                JSONObject customerR = (JSONObject) jsonA.get(i);
                descLi.add((String) customerR.get("description").toString());
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        return descLi;
    }
    public int arrayIterator;

    @FXML
    private Button backB;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Button forwardB;

    @FXML
    private Label className;

    @FXML
    private Button startButton;

    @FXML
    private Button testButton;

    @FXML
    public ImageView charImage;


    @FXML
    public void startGame(ActionEvent event) throws IOException, ParseException {
        System.out.println("hit");


        HelloController hc=ChangeScene.changeSceneRC(event, "hello-view.fxml");

//        switch (arrayIterator){
//            case 0:
//                hc.testPlayer=new ChangeScene.Player(20,"Squire", 0, false);
//                break;
//                case 1:
//                    hc.testPlayer=new ChangeScene.Player(25,"Knight", 1, false);
//                    break;
//                    case 2:
//                        hc.testPlayer=new ChangeScene.Player(30,"Tank", 2, false);
//                        break;
//                        case 3:
//                            hc.testPlayer=new ChangeScene.Player(15,"Rogue", 3, false);
//                            break;
//
//        }
        hc.gameStart();
    }

//    @FXML
//    public void testit(ActionEvent event) throws IOException, ParseException {
//        System.out.println("hit");
//        HelloController hc=ChangeScene.changeSceneRC(event, "hello-view.fxml");
//        hc.gameStart();
//    }
    @FXML
    protected void back(ActionEvent event) throws IOException {
        System.out.println(descLi);
        System.out.println(classLi);
        System.out.println(nameLi);


        arrayIterator--;
    try {
        if (arrayIterator > 3) {
            arrayIterator = 0;
            String s=classLi.get(arrayIterator);
            File f= new File(s);
            String uri=f.toURI().toString();
            charImage.setImage(new Image(uri));
            className.setText(nameLi.get(arrayIterator));
            descriptionLabel.setText(descLi.get(arrayIterator));
        } else {
            String s=classLi.get(arrayIterator);
            File f= new File(s);
            String uri=f.toURI().toString();
            charImage.setImage(new Image(uri));
            className.setText(nameLi.get(arrayIterator));
            descriptionLabel.setText(descLi.get(arrayIterator));
        }

    }catch (IndexOutOfBoundsException e) {
        e.printStackTrace();
    }





    }
    @FXML
    protected void forward(ActionEvent event) throws IOException, IndexOutOfBoundsException {
        if (classLi.isEmpty()) {
            getClassLi();
            getDescLi();
            getNameLi();
        }
        System.out.println(descLi);
        System.out.println(classLi);
        System.out.println(nameLi);
        backB.setVisible(true);
        arrayIterator++;

        try {
            if (arrayIterator > 3) {
                arrayIterator = 0;
                String s=classLi.get(arrayIterator);
                File f= new File(s);
                String uri=f.toURI().toString();
                charImage.setImage(new Image(uri));
                className.setText(nameLi.get(arrayIterator));
                descriptionLabel.setText(descLi.get(arrayIterator));
            } else {
                String s=classLi.get(arrayIterator);
                File f= new File(s);
                String uri=f.toURI().toString();
                charImage.setImage(new Image(uri));
                className.setText(nameLi.get(arrayIterator));
                descriptionLabel.setText(descLi.get(arrayIterator));
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

}

