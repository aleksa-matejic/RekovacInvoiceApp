package report;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
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

    public void showReport(RacunData racunData, KorisnikData korisnikData, FirmaData firmaData) throws JRException, SQLException, IOException, FontFormatException {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        InputStream isNormalFont = this.getClass().getResourceAsStream("/report/DejaVuSans.ttf");
        Font normalFont = Font.createFont(Font.TRUETYPE_FONT, isNormalFont);

        InputStream isBoldFont = this.getClass().getResourceAsStream("/report/DejaVuSans-Bold.ttf");
        Font boldFont = Font.createFont(Font.TRUETYPE_FONT, isBoldFont);

        ge.registerFont(normalFont);
        ge.registerFont(boldFont);

        // Fields for report
        HashMap<String, Object> parameters = new HashMap<String, Object>();

        BufferedImage image = null;
        try
        {
            image = ImageIO.read(getClass().getResource("/images/RekovacIcon.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        // First, compile jrxml file.
        InputStream is = null;

        if (racunData.getNapomena().isEmpty())
        {
            is = this.getClass().getResourceAsStream("/report/RekovacInvoiceReport.jrxml");
        }
        else
        {
            is = this.getClass().getResourceAsStream("/report/RekovacInvoiceReportNapomena.jrxml");
            parameters.put("napomena", racunData.getNapomena());
        }

        if (!korisnikData.getDug().isEmpty())
        {
            is = this.getClass().getResourceAsStream("/report/RekovacInvoiceReportDug.jrxml");
            parameters.put("dug", korisnikData.getDug());
            parameters.put("dugPlusUplata", dugPlusUplata(korisnikData.getDug(), racunData.getZaUplatu()));
        }

        JasperReport jasperReport = JasperCompileManager.compileReport(is);

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

        parameters.put("napomenaOPO", racunData.getNapomenaOPO());
        parameters.put("tekuciRacun", firmaData.getTekuciRacun());
        parameters.put("kodBanke", firmaData.getKodBanke());

        parameters.put("logo", image);

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
    }

    private String dugPlusUplata(String dug, String zaUplatu)
    {
        Double sum = getDoubleFromString(dug) + getDoubleFromString(zaUplatu);

        String tmp = String.format("%,.2f", sum);
        tmp = tmp.replace("," , " ");
        tmp = tmp.replace("." , ",");
        tmp = tmp.replace(" " , ".");

        return tmp;
    }

    private Double getDoubleFromString(String numberString)
    {
        numberString = numberString.replace(".", "");
        numberString = numberString.replace(",", ".");

        // Aleksa TODO: crash if string is not number
        return Double.parseDouble(numberString);
    }
}