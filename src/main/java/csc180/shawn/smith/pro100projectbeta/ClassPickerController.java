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

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ClassPickerController {
    public ArrayList<Image> classLi=new ArrayList<>();
    public ArrayList<String> nameLi=new ArrayList<>();
    public ArrayList<String> descLi=new ArrayList<>();

    public ArrayList<Image> getClassLi() {
        try {
            Object json = new JSONParser().parse(new FileReader("C:\\Users\\shsmith\\Workspace\\Pro100_GoupProject\\PRO100ProjectBeta\\data\\class.json"));
            //System.out.println("fileReaderWorked");
            JSONArray jsonA = (JSONArray) json;
            for (int i = 0; i < jsonA.size(); i++) {
                JSONObject customerR = (JSONObject) jsonA.get(i);
               // System.out.println("json object Created");
                classLi.add(new Image((String) customerR.get("url")));
               // System.out.println("image created");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        System.out.println(classLi);
        return classLi;
    }
    public ArrayList<String> getNameLi() {
        try {
            Object json = new JSONParser().parse(new FileReader("C:\\Users\\shsmith\\Workspace\\Pro100_GoupProject\\PRO100ProjectBeta\\data\\class.json"));
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
        System.out.println(nameLi);
        return nameLi;
    }
    public ArrayList<String> getDescLi() {
        try {
            Object json = new JSONParser().parse(new FileReader("C:\\Users\\shsmith\\Workspace\\Pro100_GoupProject\\PRO100ProjectBeta\\data\\class.json"));
            //System.out.println("made it");
            JSONArray jsonA = (JSONArray) json;
            for (int i = 0; i < jsonA.size(); i++) {
                JSONObject customerR = (JSONObject) jsonA.get(i);
                descLi.add((String) customerR.get("description").toString());
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        System.out.println(descLi);
        return descLi;
    }
    public int arrayIterator=0;

    @FXML
    private Button backButton;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Button forwardsButton;

    @FXML
    private Label className;

    @FXML
    private Button startButton;

    @FXML
    private Button testButton;

    @FXML
    public ImageView charImage;


//    @FXML
//    public void startGame(ActionEvent event) throws IOException, ParseException {
//        System.out.println("hit");
//
//
//        HelloController hc=ChangeScene.changeSceneRC(event, "hello-view.fxml");
//        hc.gameStart();
//    }

//    @FXML
//    public void testit(ActionEvent event) throws IOException, ParseException {
//        System.out.println("hit");
//        HelloController hc=ChangeScene.changeSceneRC(event, "hello-view.fxml");
//        hc.gameStart();
//    }
    @FXML
    protected void back(ActionEvent event) throws IOException {
        if (classLi.size()==0&&nameLi.size()==0&&descLi.size()==0) {
            getClassLi();
            getDescLi();
            getNameLi();
        }
        arrayIterator--;
    try {
     if(arrayIterator<0) {
         arrayIterator=3;
        charImage.setImage(classLi.get(arrayIterator));
        className.setText(nameLi.get(arrayIterator));
        descriptionLabel.setText(descLi.get(arrayIterator));
     }else {
        charImage.setImage(classLi.get(arrayIterator));
        className.setText(nameLi.get(arrayIterator));
        descriptionLabel.setText(descLi.get(arrayIterator));
    }

    }catch (IndexOutOfBoundsException e) {
        e.printStackTrace();
    }





    }
    @FXML
    protected void forward(ActionEvent event) throws IOException, IndexOutOfBoundsException {
        arrayIterator++;
        if (classLi.size() == 0 && nameLi.size() == 0 && descLi.size() == 0) {
            getClassLi();
            getDescLi();
            getNameLi();
        }
        try {
            if (arrayIterator > 3) {
                arrayIterator = 0;
                charImage.setImage(classLi.get(arrayIterator));
                className.setText(nameLi.get(arrayIterator));
                descriptionLabel.setText(descLi.get(arrayIterator));
            } else {
                charImage.setImage(classLi.get(arrayIterator));
                className.setText(nameLi.get(arrayIterator));
                descriptionLabel.setText(getDescLi().get(arrayIterator));
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

}

