package korisnikApp;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RacunData
{
    private final StringProperty idRacun;
    private final StringProperty brojRacuna;
    private final StringProperty pozivNaBroj;
    private final StringProperty datumIzdavanja;
    private final StringProperty mestoIzdavanja;
    private final StringProperty dospeva;
    private final StringProperty datumPrometa;
    private final StringProperty redniBroj;
    private final StringProperty naziv;
    private final StringProperty jm;
    private final StringProperty kolicina;
    private final StringProperty cena;
    private final StringProperty pdvProcenat;
    private final StringProperty cenaSaPdv;
    private final StringProperty pdv;
    private final StringProperty iznos;
    private final StringProperty nazivDazbine;
    private final StringProperty iznosPoreza;
    private final StringProperty zaUplatu;
    private final StringProperty slovima;
    private final StringProperty idKorisnik;
    private final StringProperty napomena;
    private final StringProperty napomenaOPO;

    public RacunData(String idRacun, String brojRacuna, String pozivNaBroj, String datumIzdavanja, String mestoIzdavanja,
                     String dospeva, String datumPrometa, String redniBroj, String naziv, String jm, String kolicina,
                     String cena, String pdvProcenat, String cenaSaPdv, String pdv, String iznos, String nazivDazbine,
                     String iznosPoreza, String zaUplatu, String slovima, String idKorisnik, String napomena, String napomenaOPO)
    {
        this.idRacun = new SimpleStringProperty(idRacun);
        this.brojRacuna = new SimpleStringProperty(brojRacuna);
        this.pozivNaBroj = new SimpleStringProperty(pozivNaBroj);
        this.datumIzdavanja = new SimpleStringProperty(datumIzdavanja);
        this.mestoIzdavanja = new SimpleStringProperty(mestoIzdavanja);
        this.dospeva = new SimpleStringProperty(dospeva);
        this.datumPrometa = new SimpleStringProperty(datumPrometa);
        this.redniBroj = new SimpleStringProperty(redniBroj);
        this.naziv = new SimpleStringProperty(naziv);
        this.jm = new SimpleStringProperty(jm);
        this.kolicina = new SimpleStringProperty(kolicina);
        this.cena = new SimpleStringProperty(cena);
        this.pdvProcenat = new SimpleStringProperty(pdvProcenat);
        this.cenaSaPdv = new SimpleStringProperty(cenaSaPdv);
        this.pdv = new SimpleStringProperty(pdv);
        this.iznos = new SimpleStringProperty(iznos);
        this.nazivDazbine = new SimpleStringProperty(nazivDazbine);
        this.iznosPoreza = new SimpleStringProperty(iznosPoreza);
        this.zaUplatu = new SimpleStringProperty(zaUplatu);
        this.slovima = new SimpleStringProperty(slovima);
        this.idKorisnik = new SimpleStringProperty(idKorisnik);
        this.napomena = new SimpleStringProperty(napomena);
        this.napomenaOPO = new SimpleStringProperty(napomenaOPO);
    }

    public String getIdRacun()
    {
        return idRacun.get();
    }

    public StringProperty idRacunProperty()
    {
        return idRacun;
    }

    public void setIdRacun(String idRacun)
    {
        this.idRacun.set(idRacun);
    }

    public String getBrojRacuna()
    {
        return brojRacuna.get();
    }

    public StringProperty brojRacunaProperty()
    {
        return brojRacuna;
    }

    public void setBrojRacuna(String brojRacuna)
    {
        this.brojRacuna.set(brojRacuna);
    }

    public String getPozivNaBroj()
    {
        return pozivNaBroj.get();
    }

    public StringProperty pozivNaBrojProperty()
    {
        return pozivNaBroj;
    }

    public void setPozivNaBroj(String pozivNaBroj)
    {
        this.pozivNaBroj.set(pozivNaBroj);
    }

    public String getDatumIzdavanja()
    {
        return datumIzdavanja.get();
    }

    public StringProperty datumIzdavanjaProperty()
    {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(String datumIzdavanja)
    {
        this.datumIzdavanja.set(datumIzdavanja);
    }

    public String getMestoIzdavanja()
    {
        return mestoIzdavanja.get();
    }

    public StringProperty mestoIzdavanjaProperty()
    {
        return mestoIzdavanja;
    }

    public void setMestoIzdavanja(String mestoIzdavanja)
    {
        this.mestoIzdavanja.set(mestoIzdavanja);
    }

    public String getDospeva()
    {
        return dospeva.get();
    }

    public StringProperty dospevaProperty()
    {
        return dospeva;
    }

    public void setDospeva(String dospeva)
    {
        this.dospeva.set(dospeva);
    }

    public String getDatumPrometa()
    {
        return datumPrometa.get();
    }

    public StringProperty datumPrometaProperty()
    {
        return datumPrometa;
    }

    public void setDatumPrometa(String datumPrometa)
    {
        this.datumPrometa.set(datumPrometa);
    }

    public String getRedniBroj()
    {
        return redniBroj.get();
    }

    public StringProperty redniBrojProperty()
    {
        return redniBroj;
    }

    public void setRedniBroj(String redniBroj)
    {
        this.redniBroj.set(redniBroj);
    }

    public String getNaziv()
    {
        return naziv.get();
    }

    public StringProperty nazivProperty()
    {
        return naziv;
    }

    public void setNaziv(String naziv)
    {
        this.naziv.set(naziv);
    }

    public String getJm()
    {
        return jm.get();
    }

    public StringProperty jmProperty()
    {
        return jm;
    }

    public void setJm(String jm)
    {
        this.jm.set(jm);
    }

    public String getKolicina()
    {
        return kolicina.get();
    }

    public StringProperty kolicinaProperty()
    {
        return kolicina;
    }

    public void setKolicina(String kolicina)
    {
        this.kolicina.set(kolicina);
    }

    public String getCena()
    {
        return cena.get();
    }

    public StringProperty cenaProperty()
    {
        return cena;
    }

    public void setCena(String cena)
    {
        this.cena.set(cena);
    }

    public String getPdvProcenat()
    {
        return pdvProcenat.get();
    }

    public StringProperty pdvProcenatProperty()
    {
        return pdvProcenat;
    }

    public void setPdvProcenat(String pdvProcenat)
    {
        this.pdvProcenat.set(pdvProcenat);
    }

    public String getCenaSaPdv()
    {
        return cenaSaPdv.get();
    }

    public StringProperty cenaSaPdvProperty()
    {
        return cenaSaPdv;
    }

    public void setCenaSaPdv(String cenaSaPdv)
    {
        this.cenaSaPdv.set(cenaSaPdv);
    }

    public String getPdv()
    {
        return pdv.get();
    }

    public StringProperty pdvProperty()
    {
        return pdv;
    }

    public void setPdv(String pdv)
    {
        this.pdv.set(pdv);
    }

    public String getIznos()
    {
        return iznos.get();
    }

    public StringProperty iznosProperty()
    {
        return iznos;
    }

    public void setIznos(String iznos)
    {
        this.iznos.set(iznos);
    }

    public String getNazivDazbine()
    {
        return nazivDazbine.get();
    }

    public StringProperty nazivDazbineProperty()
    {
        return nazivDazbine;
    }

    public void setNazivDazbine(String nazivDazbine)
    {
        this.nazivDazbine.set(nazivDazbine);
    }

    public String getIznosPoreza()
    {
        return iznosPoreza.get();
    }

    public StringProperty iznosPorezaProperty()
    {
        return iznosPoreza;
    }

    public void setIznosPoreza(String iznosPoreza)
    {
        this.iznosPoreza.set(iznosPoreza);
    }

    public String getZaUplatu()
    {
        return zaUplatu.get();
    }

    public StringProperty zaUplatuProperty()
    {
        return zaUplatu;
    }

    public void setZaUplatu(String zaUplatu)
    {
        this.zaUplatu.set(zaUplatu);
    }

    public String getSlovima()
    {
        return slovima.get();
    }

    public StringProperty slovimaProperty()
    {
        return slovima;
    }

    public void setSlovima(String slovima)
    {
        this.slovima.set(slovima);
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

    public String getNapomena()
    {
        return napomena.get();
    }

    public StringProperty napomenaProperty()
    {
        return napomena;
    }

    public void setNapomena(String napomena)
    {
        this.napomena.set(napomena);
    }

    public String getNapomenaOPO()
    {
        return napomenaOPO.get();
    }

    public StringProperty napomenaOPOProperty()
    {
        return napomenaOPO;
    }

    public void setNapomenaOPO(String napomenaOPO)
    {
        this.napomenaOPO.set(napomenaOPO);
    }
}
