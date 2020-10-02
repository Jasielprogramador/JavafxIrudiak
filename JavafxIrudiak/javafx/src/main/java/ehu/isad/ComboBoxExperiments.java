
package ehu.isad;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComboBoxExperiments extends Application {

    private ImageView imageView = new ImageView();
    private ComboBox comboBilduma = new ComboBox();
    private ListView listViewOfArgazki;


    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Animaliak");


        List<String> bildumak = List.of("abereak", "landareak", "frutak");
        ObservableList<String> bildumaList = FXCollections.observableArrayList(bildumak);
        comboBilduma.setItems(bildumaList);


        /*Relacionas las imagenes con los nombres y todo esto con tu tipo*/

        Map<String, List<Argazki>> bildumaMap = new HashMap<>();

        bildumaMap.put("abereak", List.of(
                new Argazki("Elefantea", "elefantea.jpeg"),
                new Argazki("Txakurra", "txakurra.jpeg"),
                new Argazki("Untxia", "untxia.png")
        ));


        bildumaMap.put("landareak", List.of(
                new Argazki("Cactus", "cactus.png"),
                new Argazki("Landareberdea", "landareberdea.jpeg"),
                new Argazki("Landarehoria", "landarehoria.jpeg")
        ));


        bildumaMap.put("frutak", List.of(
                new Argazki("Sandia", "sandia.png"),
                new Argazki("Sagarra", "sagarra.jpeg"),
                new Argazki("Fresa", "fresa.jpeg")
        ));


        ObservableList<Argazki> argazkiList = FXCollections.observableArrayList();


        comboBilduma.getSelectionModel().selectFirst();

        listViewOfArgazki = new ListView<>(argazkiList);
        argazkiList.addAll(bildumaMap.get("abereak"));

        comboBilduma.setOnAction(es -> {

            if(!argazkiList.contains(comboBilduma.getValue())){
                argazkiList.remove(0,3);
                argazkiList.addAll(bildumaMap.get(comboBilduma.getValue()));
            }

        });

            listViewOfArgazki.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (observable.getValue() == null) return; String fitx = ((Argazki) observable.getValue()).getIrudia();

            try {

                fitx = ((Argazki) observable.getValue()).getIrudia();
                imageView.setImage(lortuIrudia(fitx /* 48x48 */));

            } catch (IOException e) {
                e.printStackTrace();
            }

        });


        //Te permite escribir
        comboBilduma.setEditable(true);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(comboBilduma,listViewOfArgazki,imageView);
        Scene scene = new Scene(vbox, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    private Image lortuIrudia(String location) throws IOException {

        InputStream is = getClass().getResourceAsStream("/" + location);
        BufferedImage reader = ImageIO.read(is);
        return SwingFXUtils.toFXImage(reader, null);

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}