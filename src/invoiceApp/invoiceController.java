package invoiceApp;

import dbUtil.dbConnection;
import dodajKorisnikaApp.DodajKorisnikaController;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import korisnikApp.KorisnikController;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class invoiceController implements Initializable
{

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        loadKorisnikData();
        loadFirmaData();

        btnUrediFirmu.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                enableTextFields();
            }
        });

        btnSacuvajPromene.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                updateFirma();
                disableTextFields();
            }
        });

        btnDodajKorisnika.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                try
                {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dodajKorisnikaApp/dodajKorisnika.fxml"));
                    DodajKorisnikaController controller = new DodajKorisnikaController();
                    fxmlLoader.setController(controller);
                    Parent root = fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/RekovacIcon.png")));
                    stage.setTitle("Dodaj korisnika");
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

        btnOsvezi.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                loadKorisnikData();
            }
        });

        disableTextFields();
    }

    private void disableTextFields()
    {
        this.btnUrediFirmu.setDisable(false);
        this.btnSacuvajPromene.setDisable(true);

        this.tfIdFirma.setDisable(true);
        this.tfImeFirme.setDisable(true);
        this.tfPosta.setDisable(true);
        this.tfMesto.setDisable(true);
        this.tfAdresa.setDisable(true);
        this.tfMaticniBroj.setDisable(true);
        this.tfPib.setDisable(true);
        this.tfSifraDelatnosti.setDisable(true);
        this.tfTekuciRacun.setDisable(true);
        this.tfKodBanke.setDisable(true);
        this.tfEmail.setDisable(true);
    }

    private void enableTextFields()
    {
        this.btnUrediFirmu.setDisable(true);
        this.btnSacuvajPromene.setDisable(false);

        // this.tfIdFirma.setDisable(false);
        this.tfImeFirme.setDisable(false);
        this.tfPosta.setDisable(false);
        this.tfMesto.setDisable(false);
        this.tfAdresa.setDisable(false);
        this.tfMaticniBroj.setDisable(false);
        this.tfPib.setDisable(false);
        this.tfSifraDelatnosti.setDisable(false);
        this.tfTekuciRacun.setDisable(false);
        this.tfKodBanke.setDisable(false);
        this.tfEmail.setDisable(false);
    }

    // FIRM DATA TABLE
    private ObservableList<FirmaData> firmaData;

    @FXML
    private TableView<FirmaData> tvFirma;

    @FXML
    private TableColumn<FirmaData, String> colIdFirma;

    @FXML
    private TableColumn<FirmaData, String> colImeFirme;

    @FXML
    private TableColumn<FirmaData, String> colPosta;

    @FXML
    private TableColumn<FirmaData, String> colMesto;

    @FXML
    private TableColumn<FirmaData, String> colAdresa;

    @FXML
    private TableColumn<FirmaData, String> colMaticniBroj;

    @FXML
    private TableColumn<FirmaData, String> colPib;

    @FXML
    private TableColumn<FirmaData, String> colSifraDelatnosti;

    @FXML
    private TableColumn<FirmaData, String> colTekuciRacun;

    @FXML
    private TableColumn<FirmaData, String> colKodBanke;

    @FXML
    private TableColumn<FirmaData, String> colEmail;

    private void loadFirmaData()
    {
        Connection conn;
        try
        {
            conn = dbConnection.getConnection();

            this.firmaData = FXCollections.observableArrayList();

            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM firma");

            while (rs.next())
            {
                this.firmaData.add(new FirmaData(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11)));
            }

            conn.close();
            rs.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        this.tfIdFirma.setText(firmaData.get(0).getIdFirma());
        this.tfImeFirme.setText(firmaData.get(0).getImeFirme());
        this.tfPosta.setText(firmaData.get(0).getPosta());
        this.tfMesto.setText(firmaData.get(0).getMesto());
        this.tfAdresa.setText(firmaData.get(0).getAdresa());
        this.tfMaticniBroj.setText(firmaData.get(0).getMaticniBroj());
        this.tfPib.setText(firmaData.get(0).getPib());
        this.tfSifraDelatnosti.setText(firmaData.get(0).getSifraDelatnosti());
        this.tfTekuciRacun.setText(firmaData.get(0).getTekuciRacun());
        this.tfKodBanke.setText(firmaData.get(0).getKodBanke());
        this.tfEmail.setText(firmaData.get(0).getEmail());

        /*this.colIdFirma.setCellValueFactory(new PropertyValueFactory("idFirma"));
        this.colImeFirme.setCellValueFactory(new PropertyValueFactory("imeFirme"));
        this.colPosta.setCellValueFactory(new PropertyValueFactory("posta"));
        this.colMesto.setCellValueFactory(new PropertyValueFactory("mesto"));
        this.colAdresa.setCellValueFactory(new PropertyValueFactory("adresa"));
        this.colMaticniBroj.setCellValueFactory(new PropertyValueFactory("maticniBroj"));
        this.colPib.setCellValueFactory(new PropertyValueFactory("pib"));
        this.colSifraDelatnosti.setCellValueFactory(new PropertyValueFactory("sifraDelatnosti"));
        this.colTekuciRacun.setCellValueFactory(new PropertyValueFactory("tekuciRacun"));
        this.colKodBanke.setCellValueFactory(new PropertyValueFactory("kodBanke"));
        this.colEmail.setCellValueFactory(new PropertyValueFactory("email"));

        this.tvFirma.setItems(null);
        this.tvFirma.setItems(this.firmaData);*/
    }

    // KORISNIK DATA TABLE
    private ObservableList<KorisnikData> korisnikData;

    @FXML
    private TableView<KorisnikData> tvKorisnik;

    @FXML
    private TableColumn<KorisnikData, String> colIdKorisnik;

    @FXML
    private TableColumn<KorisnikData, String> colImeKor;

    @FXML
    private TableColumn<KorisnikData, String> colPostaKor;

    @FXML
    private TableColumn<KorisnikData, String> colMestoKor;

    @FXML
    private TableColumn<KorisnikData, String> colAdresaKor;

    @FXML
    private TableColumn<KorisnikData, String> colPibKor;

    @FXML
    private TableColumn<KorisnikData, String> colDug;

    private void loadKorisnikData()
    {
        try
        {
            Connection conn = dbConnection.getConnection();

            this.korisnikData = FXCollections.observableArrayList();

            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM korisnik");

            while (rs.next())
            {
                this.korisnikData.add(new KorisnikData(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7)));
            }

            conn.close();
            rs.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        this.colIdKorisnik.setCellValueFactory(new PropertyValueFactory("idKorisnik"));
        this.colImeKor.setCellValueFactory(new PropertyValueFactory("ime"));
        this.colPostaKor.setCellValueFactory(new PropertyValueFactory("posta"));
        this.colMestoKor.setCellValueFactory(new PropertyValueFactory("mesto"));
        this.colAdresaKor.setCellValueFactory(new PropertyValueFactory("adresa"));
        this.colPibKor.setCellValueFactory(new PropertyValueFactory("pib"));
        this.colDug.setCellValueFactory(new PropertyValueFactory("dug"));

        this.tvKorisnik.setItems(null);
        this.tvKorisnik.setItems(this.korisnikData);

        this.tvKorisnik.setRowFactory(tv ->
        {
            TableRow<KorisnikData> row = new TableRow<>();
            row.setOnMouseClicked(event ->
            {
                if (event.getClickCount() == 2 && (!row.isEmpty()))
                {
                    KorisnikData rowData = row.getItem();

                    try
                    {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/korisnikApp/korisnik.fxml"));
                        KorisnikController controller = new KorisnikController(rowData, firmaData.get(0));
                        fxmlLoader.setController(controller);
                        Parent root = fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/RekovacIcon.png")));
                        stage.setTitle(rowData.getIme());
                        stage.setScene(new Scene(root));
                        stage.show();
                        // Hide this current window (if this is what you want)
                        // ((Node)(event.getSource())).getScene().getWindow().hide();
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                }
            });
            return row;
        });
    }


    // FIRMA TEXT FIELDS
    @FXML
    private TextField tfIdFirma;

    @FXML
    private TextField tfImeFirme;

    @FXML
    private TextField tfPosta;

    @FXML
    private TextField tfMesto;

    @FXML
    private TextField tfAdresa;

    @FXML
    private TextField tfMaticniBroj;

    @FXML
    private TextField tfPib;

    @FXML
    private TextField tfSifraDelatnosti;

    @FXML
    private TextField tfTekuciRacun;

    @FXML
    private TextField tfKodBanke;

    @FXML
    private TextField tfEmail;

    @FXML
    private Button btnUrediFirmu;

    @FXML
    private Button btnSacuvajPromene;

    private void updateFirma()
    {
        String sql = "UPDATE firma SET imeFirme = ?, posta = ?, mesto = ?, adresa = ?, maticniBroj = ?, pib = ?, " +
                "sifraDelatnosti = ?, tekuciRacun = ?, kodBanke = ?, email = ? WHERE idFirma = ?";
        try
        {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, tfImeFirme.getText());
            stmt.setString(2, tfPosta.getText());
            stmt.setString(3, tfMesto.getText());
            stmt.setString(4, tfAdresa.getText());
            stmt.setString(5, tfMaticniBroj.getText());
            stmt.setString(6, tfPib.getText());
            stmt.setString(7, tfSifraDelatnosti.getText());
            stmt.setString(8, tfTekuciRacun.getText());
            stmt.setString(9, tfKodBanke.getText());
            stmt.setString(10, tfEmail.getText());
            stmt.setString(11, firmaData.get(0).getIdFirma());

            stmt.executeUpdate();
            conn.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Akcija uspesna!");
            alert.setHeaderText("Azuriranje podataka o firmi uspesno!");
            alert.setContentText("Molimo pritisnite OK.");
            alert.showAndWait();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    @FXML
    private Button btnDodajKorisnika;

    @FXML
    private Button btnOsvezi;
}
