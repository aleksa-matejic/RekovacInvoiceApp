package invoiceApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.time.LocalDateTime;

public class InvoiceApp extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        LocalDateTime dateNow = LocalDateTime.now();
        LocalDateTime dateExpires = LocalDateTime.of(2022, 6, 24, 0, 0);

        boolean trialExpired = dateExpires.isBefore(dateNow);

        if (trialExpired) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Probna verzija istekla!");
            alert.setHeaderText("Probna verzija aplikacije je istekla, molimo da se javite developeru na mail aljosa.gk@gmail.com");
            alert.setContentText("Molimo pritisnite OK da ugasite aplikaciju.");
            alert.showAndWait();
            System.exit(1);
        }

        Parent root = (Parent) FXMLLoader.load(getClass().getResource("invoice.fxml"));

        Scene scene = new Scene(root);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/RekovacIcon.png")));
        stage.setScene(scene);
        stage.setTitle("Rekovac Invoice App");
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
