package edu.unm.casaa.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;


public class Main extends Application {

    public Preferences appPrefs;
    private Stage mainStage;
    private MainController mainController;


    @Override
    public void init() throws Exception {
        // link app appPrefs
        appPrefs = Preferences.userNodeForPackage(Main.class);
    }


    @Override
    public void start(Stage primaryStage) throws Exception{

        // store reference
        mainStage = primaryStage;

        FXMLLoader fxmlLoader = new FXMLLoader();
        Locale locale = new Locale("en", "US");
        ResourceBundle resourceStrings = ResourceBundle.getBundle("strings", locale);
        Parent root = fxmlLoader.load(getClass().getResource("Main.fxml"), resourceStrings);

        //
        mainController = fxmlLoader.getController();

        // main window position and size from preferences
        double windX = appPrefs.getDouble("main.wind.x", 0.0);
        double windY = appPrefs.getDouble("main.wind.y", 0.0);
        double windH = appPrefs.getDouble("main.wind.h", 400.0);
        double windW = appPrefs.getDouble("main.wind.w", 600.0);
        mainStage.setX(windX);
        mainStage.setY(windY);
        mainStage.setHeight(windH);
        mainStage.setWidth(windW);
        mainStage.setTitle(resourceStrings.getString("wind.title.main"));
        // application window icon is set
        mainStage.getIcons().add(new Image(Main.class.getResourceAsStream("/media/UNM_Color.png")));
        mainStage.setScene(new Scene(root));
        mainStage.show();
    }


    @Override
    public void stop() throws Exception {

        // save user appPrefs for position and size of main window
        appPrefs.putDouble("main.wind.x",mainStage.getX());
        appPrefs.putDouble("main.wind.y",mainStage.getY());
        appPrefs.putDouble("main.wind.h",mainStage.getHeight());
        appPrefs.putDouble("main.wind.w",mainStage.getWidth());

        // save current volume preference
        //appPrefs.putDouble("player.volume",mainController.sldVolume.getValue());

    }

    public static void main(String[] args) { launch(args); }

}
