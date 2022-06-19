package korisnikApp;

import dbUtil.dbConnection;
import dodajRacunApp.DodajRacunController;
import invoiceApp.FirmaData;
import invoiceApp.KorisnikData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import report.InvoicePrintReport;
import urediRacunApp.UrediRacunController;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class KorisnikController implements Initializable
{
    KorisnikData korisnikData;
    FirmaData firmaData;

    RacunData selectedRacunData = null;

    public KorisnikController(KorisnikData korisnikData, FirmaData firmaData)
    {
        this.korisnikData = korisnikData;
        this.firmaData = firmaData;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        populateKorisnikTextFields();

        loadRacunData();

        disableTextFields();

        disableTextFieldsRacun();

        btnDodajRacun.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                try
                {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dodajRacunApp/dodajRacun.fxml"));
                    DodajRacunController controller = new DodajRacunController(korisnikData.getIdKorisnik());
                    fxmlLoader.setController(controller);
                    Parent root = fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/RekovacIcon.png")));
                    stage.setTitle(korisnikData.getIme());
                    stage.setScene(new Scene(root));
                    stage.show();
                    // Hide this current window (if this is what you want)
                    // ((Node)(event.getSource())).getScene().getWindow().hide();
                } catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            }
        });

        btnObrisiRacun.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                obrisiRacun();
            }
        });

        btnUrediRacun.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                urediRacun();
            }
        });

        btnUrediKorisnika.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                enableTextFields();
            }
        });

        btnSacuvajKorisnika.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                sacuvajKorisnika();
                disableTextFields();
            }
        });

        btnObrisiKorisnika.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                obrisiKorisnika();
            }
        });

        btnStampajRacun.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                showReport();
            }
        });

        this.tvRacun.setRowFactory(tv ->
        {
            TableRow<RacunData> row = new TableRow<>();
            row.setOnMouseClicked(event ->
            {
                if (event.getClickCount() == 1 && (!row.isEmpty()))
                {
                    selectedRacunData = row.getItem();
                    enableTextFieldsRacun();
                }
                else if (event.getClickCount() == 2 && (!row.isEmpty()))
                {
                    showReport();
                }
            });
            return row;
        });

        btnOsvezi.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                loadRacunData();
            }
        });
    }

    // KORISNIK DATA
    @FXML
    private TextField tfIdKorisnik;

    @FXML
    private TextField tfImeKor;

    @FXML
    private TextField tfPostaKor;

    @FXML
    private TextField tfMestoKor;

    @FXML
    private TextField tfAdresaKor;

    @FXML
    private TextField tfPibKor;

    @FXML
    private TextField tfDug;

    private void showReport()
    {
        try
        {
            if (selectedRacunData == null)
            {
                return;
            }
            // --- Show Jasper Report on click-----
            new InvoicePrintReport().showReport(selectedRacunData, korisnikData, firmaData);
        } catch (JRException | SQLException e1)
        {
            e1.printStackTrace();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (FontFormatException ex) {
            throw new RuntimeException(ex);
        }
    }
    private void populateKorisnikTextFields()
    {
        this.tfIdKorisnik.setText(this.korisnikData.getIdKorisnik());
        this.tfImeKor.setText(this.korisnikData.getIme());
        this.tfPostaKor.setText(this.korisnikData.getPosta());
        this.tfMestoKor.setText(this.korisnikData.getMesto());
        this.tfAdresaKor.setText(this.korisnikData.getAdresa());
        this.tfPibKor.setText(this.korisnikData.getPib());
        this.tfDug.setText(this.korisnikData.getDug());
    }

    private void enableTextFields()
    {
        this.btnUrediKorisnika.setDisable(true);
        this.btnSacuvajKorisnika.setDisable(false);

        // this.tfIdKorisnik.setDisable(false);
        this.tfImeKor.setDisable(false);
        this.tfPostaKor.setDisable(false);
        this.tfMestoKor.setDisable(false);
        this.tfAdresaKor.setDisable(false);
        this.tfPibKor.setDisable(false);
        this.tfDug.setDisable(false);
    }

    private void disableTextFields()
    {
        this.btnUrediKorisnika.setDisable(false);
        this.btnSacuvajKorisnika.setDisable(true);

        this.tfIdKorisnik.setDisable(true);
        this.tfImeKor.setDisable(true);
        this.tfPostaKor.setDisable(true);
        this.tfMestoKor.setDisable(true);
        this.tfAdresaKor.setDisable(true);
        this.tfPibKor.setDisable(true);
        this.tfDug.setDisable(true);
    }

    private void enableTextFieldsRacun()
    {
        this.btnUrediRacun.setDisable(false);
        this.btnObrisiRacun.setDisable(false);
        this.btnStampajRacun.setDisable(false);
    }
    private void disableTextFieldsRacun()
    {
        this.btnUrediRacun.setDisable(true);
        this.btnObrisiRacun.setDisable(true);
        this.btnStampajRacun.setDisable(true);
    }

    // RACUN DATA TABLE
    private ObservableList<RacunData> racunData;

    @FXML
    private TableView<RacunData> tvRacun;

    @FXML
    private TableColumn<RacunData, String> colIdRacun;

    @FXML
    private TableColumn<RacunData, String> colBrojRacuna;

    @FXML
    private TableColumn<RacunData, String> colPozivNaBroj;

    @FXML
    private TableColumn<RacunData, String> colDatumIzdavanja;

    @FXML
    private TableColumn<RacunData, String> colMestoIzdavanja;

    @FXML
    private TableColumn<RacunData, String> colDospeva;

    @FXML
    private TableColumn<RacunData, String> colDatumPrometa;

    @FXML
    private TableColumn<RacunData, String> colRedniBroj;

    @FXML
    private TableColumn<RacunData, String> colNaziv;

    @FXML
    private TableColumn<RacunData, String> colJm;

    @FXML
    private TableColumn<RacunData, String> colKolicina;

    @FXML
    private TableColumn<RacunData, String> colCena;

    @FXML
    private TableColumn<RacunData, String> colPdvProcenat;

    @FXML
    private TableColumn<RacunData, String> colCenaSaPdv;

    @FXML
    private TableColumn<RacunData, String> colPdv;

    @FXML
    private TableColumn<RacunData, String> colIznos;

    @FXML
    private TableColumn<RacunData, String> colNazivDazbine;

    @FXML
    private TableColumn<RacunData, String> colIznosPoreza;

    @FXML
    private TableColumn<RacunData, String> colZaUplatu;

    @FXML
    private TableColumn<RacunData, String> colSlovima;

    @FXML
    private TableColumn<RacunData, String> colIdKorisnik;

    @FXML
    private TableColumn<RacunData, String> colNapomena;

    @FXML
    private TableColumn<RacunData, String> colNapomenaOPO;

    private void loadRacunData()
    {
        try
        {
            Connection conn = dbConnection.getConnection();

            this.racunData = FXCollections.observableArrayList();

            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM racun WHERE idKorisnik = " + korisnikData.getIdKorisnik());

            while (rs.next())
            {
                this.racunData.add(new RacunData(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11),
                        rs.getString(12), rs.getString(13), rs.getString(14),
                        rs.getString(15), rs.getString(16), rs.getString(17),
                        rs.getString(18), rs.getString(19), rs.getString(20),
                        rs.getString(21), rs.getString(22), rs.getString(23)));
            }

            conn.close();
            rs.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        this.colIdRacun.setCellValueFactory(new PropertyValueFactory("idRacun"));
        this.colBrojRacuna.setCellValueFactory(new PropertyValueFactory("brojRacuna"));
        this.colPozivNaBroj.setCellValueFactory(new PropertyValueFactory("pozivNaBroj"));
        this.colDatumIzdavanja.setCellValueFactory(new PropertyValueFactory("datumIzdavanja"));
        this.colMestoIzdavanja.setCellValueFactory(new PropertyValueFactory("mestoIzdavanja"));
        this.colDospeva.setCellValueFactory(new PropertyValueFactory("dospeva"));
        this.colDatumPrometa.setCellValueFactory(new PropertyValueFactory("datumPrometa"));
        this.colRedniBroj.setCellValueFactory(new PropertyValueFactory("redniBroj"));
        this.colNaziv.setCellValueFactory(new PropertyValueFactory("naziv"));
        this.colJm.setCellValueFactory(new PropertyValueFactory("jm"));
        this.colKolicina.setCellValueFactory(new PropertyValueFactory("kolicina"));
        this.colCena.setCellValueFactory(new PropertyValueFactory("cena"));
        this.colPdvProcenat.setCellValueFactory(new PropertyValueFactory("pdvProcenat"));
        this.colCenaSaPdv.setCellValueFactory(new PropertyValueFactory("cenaSaPdv"));
        this.colPdv.setCellValueFactory(new PropertyValueFactory("pdv"));
        this.colIznos.setCellValueFactory(new PropertyValueFactory("iznos"));
        this.colNazivDazbine.setCellValueFactory(new PropertyValueFactory("nazivDazbine"));
        this.colIznosPoreza.setCellValueFactory(new PropertyValueFactory("iznosPoreza"));
        this.colZaUplatu.setCellValueFactory(new PropertyValueFactory("zaUplatu"));
        this.colSlovima.setCellValueFactory(new PropertyValueFactory("slovima"));
        this.colIdKorisnik.setCellValueFactory(new PropertyValueFactory("idKorisnik"));
        this.colNapomena.setCellValueFactory(new PropertyValueFactory("napomena"));
        this.colNapomenaOPO.setCellValueFactory(new PropertyValueFactory("napomenaOPO"));


        this.tvRacun.setItems(null);
        this.tvRacun.setItems(this.racunData);
    }

    private void obrisiRacun()
    {
        if (selectedRacunData == null)
        {
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potvrdite akciju!");
        alert.setHeaderText("Obrisati korisnikov račun?");
        alert.setContentText("Da li ste sigurni?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            String sql = "DELETE FROM racun WHERE idRacun = ?";
            try
            {
                Connection conn = dbConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.setString(1, selectedRacunData.getIdRacun());

                stmt.executeUpdate();
                conn.close();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Akcija uspešna!");
                alert.setHeaderText("Račun uspešno obrisan!");
                alert.setContentText("Molimo pritisnite OK.");
                alert.showAndWait();

                // ALEKSA TODO: BUG obrisati racun da nema ni jedan i onda stisnuti uredi
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    private void urediRacun()
    {
        try
        {
            if (selectedRacunData == null)
            {
                return;
            }
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/urediRacunApp/urediRacun.fxml"));
            UrediRacunController controller = new UrediRacunController(selectedRacunData);
            fxmlLoader.setController(controller);
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/RekovacIcon.png")));
            stage.setTitle(selectedRacunData.getBrojRacuna());
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void sacuvajKorisnika()
    {
        String sql = "UPDATE korisnik SET ime = ?, posta = ?, mesto = ?, adresa = ?, pib = ?, dug = ? WHERE idKorisnik = ?";
        try
        {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, tfImeKor.getText());
            stmt.setString(2, tfPostaKor.getText());
            stmt.setString(3, tfMestoKor.getText());
            stmt.setString(4, tfAdresaKor.getText());
            stmt.setString(5, tfPibKor.getText());
            stmt.setString(6, tfDug.getText());
            stmt.setString(7, korisnikData.getIdKorisnik());

            stmt.executeUpdate();
            conn.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Akcija uspešna!");
            alert.setHeaderText("Ažuriranje korisnika uspešno!");
            alert.setContentText("Molimo pritisnite OK.");
            alert.showAndWait();

        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    private void obrisiKorisnika()
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potvrdite akciju!");
        alert.setHeaderText("Obrisati korisnika i sve njegove račune?");
        alert.setContentText("Da li ste sigurni?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            String sql = "DELETE FROM korisnik WHERE idKorisnik = ?";
            try
            {
                Connection conn = dbConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.setString(1, korisnikData.getIdKorisnik());

                stmt.executeUpdate();
                conn.close();
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }

            sql = "DELETE FROM racun WHERE idKorisnik = ?";
            try
            {
                Connection conn = dbConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.setString(1, korisnikData.getIdKorisnik());

                stmt.executeUpdate();
                conn.close();
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Akcija uspešna!");
            alert.setHeaderText("Korisnik uspešno obrisan!");
            alert.setContentText("Molimo pritisnite OK.");
            alert.showAndWait();

            Stage stage = (Stage) btnObrisiKorisnika.getScene().getWindow();
            stage.close();
        }
    }


    @FXML
    private Button btnDodajRacun;

    @FXML
    private Button btnObrisiRacun;

    @FXML
    private Button btnUrediRacun;

    @FXML
    private Button btnStampajRacun;

    @FXML
    private Button btnUrediKorisnika;

    @FXML
    private Button btnSacuvajKorisnika;

    @FXML
    private Button btnObrisiKorisnika;

    @FXML
    private Button btnOsvezi;
}
