package invoiceApp;

import dbUtil.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
        // addKorisnik();
        loadKorisnikData();
        loadFirmaData();
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
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        this.colIdFirma.setCellValueFactory(new PropertyValueFactory("idFirma"));
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
        this.tvFirma.setItems(this.firmaData);
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

    private void loadKorisnikData()
    {
        Connection conn;
        try
        {
            conn = dbConnection.getConnection();

            this.korisnikData = FXCollections.observableArrayList();

            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM korisnik");

            while (rs.next())
            {
                this.korisnikData.add(new KorisnikData(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6)));
            }

            conn.close();
            rs.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        this.colIdKorisnik.setCellValueFactory(new PropertyValueFactory("idKorisnik"));
        this.colImeKor.setCellValueFactory(new PropertyValueFactory("ime"));
        this.colPostaKor.setCellValueFactory(new PropertyValueFactory("posta"));
        this.colMestoKor.setCellValueFactory(new PropertyValueFactory("mesto"));
        this.colAdresaKor.setCellValueFactory(new PropertyValueFactory("adresa"));
        this.colPibKor.setCellValueFactory(new PropertyValueFactory("pib"));

        this.tvKorisnik.setItems(null);
        this.tvKorisnik.setItems(this.korisnikData);
    }

    // ADD KORISNIK
    private void addKorisnik()
    {
        String sql = "INSERT INTO `korisnik`(`ime`, `posta`, `mesto`, `adresa`, `pib`) VALUES (?, ?, ?, ?, ?)";
        try
        {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, "Mihailo Matejic");
            stmt.setString(2, "11000");
            stmt.setString(3, "Kaludjerica");
            stmt.setString(4, "Stevana Sidnelica 57a");
            stmt.setString(5, "1051065106");

            stmt.execute();
            conn.close();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
}
