package invoiceApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InvoiceApp extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = (Parent) FXMLLoader.load(getClass().getResource("invoice.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Rekovac Invoice App");
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
