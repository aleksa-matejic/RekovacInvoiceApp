package invoiceApp;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FirmaData
{
    private final StringProperty idFirma;
    private final StringProperty imeFirme;
    private final StringProperty posta;
    private final StringProperty mesto;
    private final StringProperty adresa;
    private final StringProperty maticniBroj;
    private final StringProperty pib;
    private final StringProperty sifraDelatnosti;
    private final StringProperty tekuciRacun;
    private final StringProperty kodBanke;
    private final StringProperty email;

    public FirmaData(String idFirma, String imeFirme, String posta, String mesto, String adresa, String maticniBroj, String pib, String sifraDelatnosti, String tekuciRacun, String kodBanke, String email)
    {
        this.idFirma = new SimpleStringProperty(idFirma);
        this.imeFirme = new SimpleStringProperty(imeFirme);
        this.posta = new SimpleStringProperty(posta);
        this.mesto = new SimpleStringProperty(mesto);
        this.adresa = new SimpleStringProperty(adresa);
        this.maticniBroj = new SimpleStringProperty(maticniBroj);
        this.pib = new SimpleStringProperty(pib);
        this.sifraDelatnosti = new SimpleStringProperty(sifraDelatnosti);
        this.tekuciRacun = new SimpleStringProperty(tekuciRacun);
        this.kodBanke = new SimpleStringProperty(kodBanke);
        this.email = new SimpleStringProperty(email);
    }

    public String getIdFirma()
    {
        return idFirma.get();
    }

    public StringProperty idFirmaProperty()
    {
        return idFirma;
    }

    public void setIdFirma(String idFirma)
    {
        this.idFirma.set(idFirma);
    }

    public String getImeFirme()
    {
        return imeFirme.get();
    }

    public StringProperty imeFirmeProperty()
    {
        return imeFirme;
    }

    public void setImeFirme(String imeFirme)
    {
        this.imeFirme.set(imeFirme);
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

    public String getMaticniBroj()
    {
        return maticniBroj.get();
    }

    public StringProperty maticniBrojProperty()
    {
        return maticniBroj;
    }

    public void setMaticniBroj(String maticniBroj)
    {
        this.maticniBroj.set(maticniBroj);
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

    public String getSifraDelatnosti()
    {
        return sifraDelatnosti.get();
    }

    public StringProperty sifraDelatnostiProperty()
    {
        return sifraDelatnosti;
    }

    public void setSifraDelatnosti(String sifraDelatnosti)
    {
        this.sifraDelatnosti.set(sifraDelatnosti);
    }

    public String getTekuciRacun()
    {
        return tekuciRacun.get();
    }

    public StringProperty tekuciRacunProperty()
    {
        return tekuciRacun;
    }

    public void setTekuciRacun(String tekuciRacun)
    {
        this.tekuciRacun.set(tekuciRacun);
    }

    public String getKodBanke()
    {
        return kodBanke.get();
    }

    public StringProperty kodBankeProperty()
    {
        return kodBanke;
    }

    public void setKodBanke(String kodBanke)
    {
        this.kodBanke.set(kodBanke);
    }

    public String getEmail()
    {
        return email.get();
    }

    public StringProperty emailProperty()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email.set(email);
    }
}
