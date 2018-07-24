package dodajKorisnikaApp;

import dbUtil.dbConnection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DodajKorisnikaController implements Initializable
{
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

        btnDodajKorisnika.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                addKorisnik();
            }
        });
    }

    // ADD KORISNIK
    private void addKorisnik()
    {
        if (tfIme.getText().isEmpty())
        {
            return;
        }
        String sql = "INSERT INTO `korisnik`(`ime`, `posta`, `mesto`, `adresa`, `pib`) VALUES (?, ?, ?, ?, ?)";
        try
        {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, tfIme.getText());
            stmt.setString(2, tfPosta.getText());
            stmt.setString(3, tfMesto.getText());
            stmt.setString(4, tfAdresa.getText());
            stmt.setString(5, tfPib.getText());

            stmt.execute();
            conn.close();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    @FXML
    private Button btnDodajKorisnika;

    @FXML
    private TextField tfIme;

    @FXML
    private TextField tfPosta;

    @FXML
    private TextField tfMesto;

    @FXML
    private TextField tfAdresa;

    @FXML
    private TextField tfPib;
}
