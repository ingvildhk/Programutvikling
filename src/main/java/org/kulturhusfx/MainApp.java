package org.kulturhusfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kulturhusfx.base.ContactPerson;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.model.HallModel;
import org.kulturhusfx.model.HappeningModel;
import org.kulturhusfx.util.fileHandling.ReadFileCsv;
import org.kulturhusfx.util.fileHandling.ReadFileJobj;

import java.util.List;


public class MainApp extends Application {

    private HappeningModel happeningModel = HappeningModel.getInstance();
    private HallModel hallModel = HallModel.getInstance();
    private List<Hall> hallList = hallModel.getHallList();

    private static final String ARR_FILE_PATH = "Arrangement.csv";
    private static final String HALL_FILE_PATH = "Sal.jobj";

    private ReadFileJobj readFileJobj = new ReadFileJobj();
    private ReadFileCsv readFileCsv = new ReadFileCsv();

    @Override
    public void start(Stage stage) throws Exception {
        try {
            readFileCsv.readHappeningFromFile(ARR_FILE_PATH);
        } catch (Exception e) {
            Hall hall = new Hall("Hovedsalen", "Konsertsal", "150");
            happeningModel.createHappening(new ContactPerson("Lene Hansen", "87654321", "lene.hansen@gmail.com",
                            "lene.com", "Lene Hansen A/S", "Ressursnr: 12345"), "Lille Eyolf", "Pia Tjelta, Kåre Conradi",
                    "Lille Eyolf;Pia Tjelta, Kåre Conradi;Lille Eyolf er kanskje det av Ibsens stykker som i minst grad preges av samfunnet rundt.",
                    hall, "Teater", "2019-05-30", "19:00", "450");
            hallList.add(hall);
        }

        try {
            readFileJobj.readHallFromFile(HALL_FILE_PATH);
        } catch (Exception e) {
            hallModel.createHall("Amfisalen", "Foredragssal", "100");
        }

        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        stage.setTitle("Programutvikling Semesteroppgave");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
