package urediRacunApp;

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
import korisnikApp.RacunData;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UrediRacunController implements Initializable
{

    RacunData racunData;

    public UrediRacunController(RacunData racunData)
    {
        this.racunData = racunData;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        populateUrediRacunTextFields();

        btnSacuvajIzmene.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                sacuvajIzmene();
            }
        });
    }

    @FXML
    private Button btnSacuvajIzmene;

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
    private DatePicker dpDatumIzdavanja;

    @FXML
    private DatePicker dpDospeva;

    @FXML
    private DatePicker dpDatumPrometa;

    private void populateUrediRacunTextFields()
    {
        tfBrojRacuna.setText(racunData.getBrojRacuna());
        tfPozivNaBroj.setText(racunData.getPozivNaBroj());
        dpDatumIzdavanja.getEditor().setText(racunData.getDatumIzdavanja());
        tfMestoIzdavanja.setText(racunData.getMestoIzdavanja());
        dpDospeva.getEditor().setText(racunData.getDospeva());

        dpDatumPrometa.getEditor().setText(racunData.getDatumPrometa());
        tfRedniBroj.setText(racunData.getRedniBroj());
        tfNaziv.setText(racunData.getNaziv());
        tfJm.setText(racunData.getJm());
        tfKolicina.setText(racunData.getKolicina());

        tfCena.setText(racunData.getCena());
        tfPdvProcenat.setText(racunData.getPdvProcenat());
        tfCenaSaPdv.setText(racunData.getCenaSaPdv());
        tfPdv.setText(racunData.getPdv());
        tfIznos.setText(racunData.getIznos());

        tfNazivDazbine.setText(racunData.getNazivDazbine());
        tfIznosPoreza.setText(racunData.getIznosPoreza());
        tfZaUplatu.setText(racunData.getZaUplatu());
        tfSlovima.setText(racunData.getSlovima());
    }

    private void sacuvajIzmene()
    {
        String sql = "UPDATE racun SET brojRacuna = ?, pozivNaBroj = ?, datumIzdavanja = ?, mestoIzdavanja = ?, dospeva = ?, " +
                "datumPrometa = ?, redniBroj = ?, naziv = ?, jm = ?, kolicina = ?, " +
                "cena = ?, pdvProcenat = ?, cenaSaPdv = ?, pdv = ?, iznos = ?," +
                "nazivDazbine = ?, iznosPoreza = ?, zaUplatu = ?, slovima = ?" +
                "WHERE idRacun = ?";

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
            stmt.setString(20, racunData.getIdRacun());

            stmt.executeUpdate();
            conn.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Akcija uspesna!");
            alert.setHeaderText("Azuriranje racuna uspesno!");
            alert.setContentText("Molimo pritisnite OK.");
            alert.showAndWait();

            Stage stage = (Stage) btnSacuvajIzmene.getScene().getWindow();
            stage.close();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
}
