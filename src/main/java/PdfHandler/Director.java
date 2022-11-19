package PdfHandler;

import com.itextpdf.text.Font;

public class Director {
    public void constructRegistrationCertificate(PdfBuilder builder) {
        builder.setTitle("CERTIFICAT D'IMMATRICULATION DE VÉHICULE", Font.FontFamily.HELVETICA,12);
        builder.setSubTitle("LE VÉHICULE", Font.FontFamily.HELVETICA, 12);
    }

}
