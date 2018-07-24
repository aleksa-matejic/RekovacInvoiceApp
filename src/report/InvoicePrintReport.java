package report;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

import invoiceApp.FirmaData;
import invoiceApp.KorisnikData;
import korisnikApp.RacunData;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;

public class InvoicePrintReport extends JFrame
{
    private static final long serialVersionUID = 1L;

    public void showReport(RacunData racunData, KorisnikData korisnikData, FirmaData firmaData) throws JRException, ClassNotFoundException, SQLException
    {
        // First, compile jrxml file.
        InputStream is = this.getClass().getResourceAsStream("/report/RekovacInvoiceReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(is);
        // Fields for report
        HashMap<String, Object> parameters = new HashMap<String, Object>();

        parameters.put("racunBroj", racunData.getBrojRacuna());
        parameters.put("pozivNaBroj", racunData.getPozivNaBroj());
        parameters.put("datumIzdavanjaRacuna", racunData.getDatumIzdavanja());
        parameters.put("mestoIzdavanjaRacuna", racunData.getMestoIzdavanja());
        parameters.put("dospeva", racunData.getDospeva());
        parameters.put("datumPrometa", racunData.getDatumPrometa());

        parameters.put("ime", korisnikData.getIme());
        parameters.put("adresa", korisnikData.getAdresa());
        parameters.put("posta_i_mesto", korisnikData.getPosta() + " " + korisnikData.getMesto());
        parameters.put("pib", "PIB: " + korisnikData.getPib());

        parameters.put("redniBroj", racunData.getRedniBroj());
        parameters.put("naziv", racunData.getNaziv());
        parameters.put("jm", racunData.getJm());
        parameters.put("kolicina", racunData.getKolicina());

        parameters.put("cena", racunData.getCena());
        parameters.put("pdvProcenat", racunData.getPdvProcenat());
        parameters.put("cenaSaPdv", racunData.getCenaSaPdv());
        parameters.put("pdv", racunData.getPdv());

        parameters.put("iznos", racunData.getIznos());
        parameters.put("nazivDazbine", racunData.getNazivDazbine());
        parameters.put("iznosPoreza", racunData.getIznosPoreza());
        parameters.put("zaUplatu", racunData.getZaUplatu());

        parameters.put("slovima", racunData.getSlovima());
        parameters.put("maticniBroj", firmaData.getMaticniBroj());
        parameters.put("sifraDelatnosti", firmaData.getSifraDelatnosti());
        parameters.put("pibFirma", firmaData.getPib());
        // Aleksa TODO: NEMA NAPOMENE
        parameters.put("napomena", "Нема");
        parameters.put("tekuciRacun", firmaData.getTekuciRacun());
        parameters.put("kodBanke", firmaData.getKodBanke());

        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        list.add(parameters);

        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);
        JasperPrint print = JasperFillManager.fillReport(jasperReport, null, beanColDataSource);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(700, 500);
        this.setVisible(true);
        System.out.print("Done!");
    }
}