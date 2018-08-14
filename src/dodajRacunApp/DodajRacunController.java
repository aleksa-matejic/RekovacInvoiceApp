package dodajRacunApp;

import dbUtil.dbConnection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DodajRacunController implements Initializable
{
    private String idKorisnik;

    public DodajRacunController(String idKorisnik)
    {
        this.idKorisnik = idKorisnik;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        btnDodaj.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                addRacun();
            }
        });
    }

    @FXML
    private Button btnDodaj;

    @FXML
    private TextField tfBrojRacuna;

    @FXML
    private TextField tfPozivNaBroj;

    @FXML
    private TextField tfMestoIzdavanja;

    @FXML
    private TextField tfRedniBroj;

    @FXML
    private TextField tfNaziv;

    @FXML
    private TextField tfJm;

    @FXML
    private TextField tfKolicina;

    @FXML
    private TextField tfCena;

    @FXML
    private TextField tfPdvProcenat;

    @FXML
    private TextField tfCenaSaPdv;

    @FXML
    private TextField tfPdv;

    @FXML
    private TextField tfIznos;

    @FXML
    private TextField tfNazivDazbine;

    @FXML
    private TextField tfIznosPoreza;

    @FXML
    private TextField tfZaUplatu;

    @FXML
    private TextField tfSlovima;

    @FXML
    private TextField tfNapomena;

    @FXML
    private DatePicker dpDatumIzdavanja;

    @FXML
    private DatePicker dpDospeva;

    @FXML
    private DatePicker dpDatumPrometa;

    private void addRacun()
    {
        String sql = "INSERT INTO `racun`(`brojRacuna`, `pozivNaBroj`, `datumIzdavanja`, `mestoIzdavanja`, `dospeva`," +
                " `datumPrometa`, `redniBroj`, `naziv`, `jm`, `kolicina`, " +
                "`cena`, `pdvProcenat`, `cenaSaPdv`, `pdv`, `iznos`, `" +
                "nazivDazbine`, `iznosPoreza`, `zaUplatu`, `slovima`, `idKorisnik`, `napomena`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try
        {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, tfBrojRacuna.getText());
            stmt.setString(2, tfPozivNaBroj.getText());
            stmt.setString(3, ((TextField) dpDatumIzdavanja.getEditor()).getText());
            stmt.setString(4, tfMestoIzdavanja.getText());
            stmt.setString(5, ((TextField) dpDospeva.getEditor()).getText());

            stmt.setString(6, ((TextField) dpDatumPrometa.getEditor()).getText());
            stmt.setString(7, tfRedniBroj.getText());
            stmt.setString(8, tfNaziv.getText());
            stmt.setString(9, tfJm.getText());
            stmt.setString(10, tfKolicina.getText());

            stmt.setString(11, tfCena.getText());
            stmt.setString(12, tfPdvProcenat.getText());
            stmt.setString(13, tfCenaSaPdv.getText());
            stmt.setString(14, tfPdv.getText());
            stmt.setString(15, tfIznos.getText());

            stmt.setString(16, tfNazivDazbine.getText());
            stmt.setString(17, tfIznosPoreza.getText());
            stmt.setString(18, tfZaUplatu.getText());
            stmt.setString(19, tfSlovima.getText());
            stmt.setString(20, idKorisnik);
            stmt.setString(21, tfNapomena.getText());

            stmt.execute();
            conn.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Akcija uspesna!");
            alert.setHeaderText("Racun uspesno dodat korisniku!");
            alert.setContentText("Molimo pritisnite OK.");
            alert.showAndWait();

            Stage stage = (Stage) btnDodaj.getScene().getWindow();
            stage.close();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
}
