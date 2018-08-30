package invoiceApp;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class KorisnikData
{
    private final StringProperty idKorisnik;
    private final StringProperty ime;
    private final StringProperty posta;
    private final StringProperty mesto;
    private final StringProperty adresa;
    private final StringProperty pib;
    private final StringProperty dug;

    public KorisnikData(String idKorisnik, String ime, String posta, String mesto, String adresa, String pib, String dug)
    {
        this.idKorisnik = new SimpleStringProperty(idKorisnik);
        this.ime = new SimpleStringProperty(ime);
        this.posta = new SimpleStringProperty(posta);
        this.mesto = new SimpleStringProperty(mesto);
        this.adresa = new SimpleStringProperty(adresa);
        this.pib = new SimpleStringProperty(pib);
        this.dug = new SimpleStringProperty(dug);
    }

    public String getIdKorisnik()
    {
        return idKorisnik.get();
    }

    public StringProperty idKorisnikProperty()
    {
        return idKorisnik;
    }

    public void setIdKorisnik(String idKorisnik)
    {
        this.idKorisnik.set(idKorisnik);
    }

    public String getIme()
    {
        return ime.get();
    }

    public StringProperty imeProperty()
    {
        return ime;
    }

    public void setIme(String ime)
    {
        this.ime.set(ime);
    }

    public String getPosta()
    {
        return posta.get();
    }

    public StringProperty postaProperty()
    {
        return posta;
    }

    public void setPosta(String posta)
    {
        this.posta.set(posta);
    }

    public String getMesto()
    {
        return mesto.get();
    }

    public StringProperty mestoProperty()
    {
        return mesto;
    }

    public void setMesto(String mesto)
    {
        this.mesto.set(mesto);
    }

    public String getAdresa()
    {
        return adresa.get();
    }

    public StringProperty adresaProperty()
    {
        return adresa;
    }

    public void setAdresa(String adresa)
    {
        this.adresa.set(adresa);
    }

    public String getPib()
    {
        return pib.get();
    }

    public StringProperty pibProperty()
    {
        return pib;
    }

    public void setPib(String pib)
    {
        this.pib.set(pib);
    }

    public String getDug()
    {
        return dug.get();
    }

    public StringProperty dugProperty()
    {
        return dug;
    }

    public void setDug(String dug)
    {
        this.dug.set(dug);
    }
}
