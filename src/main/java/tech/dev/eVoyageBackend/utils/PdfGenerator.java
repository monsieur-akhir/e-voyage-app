package tech.dev.eVoyageBackend.utils;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfAConformanceLevel;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;
import org.thymeleaf.TemplateEngine;
import tech.dev.eVoyageBackend.dao.entity.Tickets;

import java.io.File;
import java.util.Base64;
import java.text.SimpleDateFormat;
import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

import org.xhtmlrenderer.pdf.ITextRenderer;


@Component
public class PdfGenerator {

    @Autowired
    private TemplateEngine templateEngine;
    public static String generateTicketPdf2(Tickets ticket, String qrCodeBase64, String outputFilePath) throws Exception {
        // Créer les dossiers si nécessaires
        File file = new File(outputFilePath);
        file.getParentFile().mkdirs();

        // Charger les polices standards
        PdfFont regularFont = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        PdfFont boldFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        PdfFont italicFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_OBLIQUE);

        // Décoder l'image QR code depuis le Base64
        byte[] decodedQrCode = Base64.getDecoder().decode(qrCodeBase64);

        // Récupérer les informations nécessaires
        String originCity = ticket.getBookings().getDeparts().getStations().getCities().getName();
        String destinationCity = ticket.getBookings().getDeparts().getStations2().getCities().getName();
        double pricePerSeat = ticket.getBookings().getDeparts().getPrice();
        int numberOfSeats = ticket.getBookings().getNumberOfSeats();
        double totalPrice = pricePerSeat * numberOfSeats;

        // Créer un PDF avec iText
        try (PdfWriter writer = new PdfWriter(outputFilePath);
             Document document = new Document(new com.itextpdf.kernel.pdf.PdfDocument(writer))) {

            // Configurer le format A5
            document.setMargins(20, 20, 20, 20);

            // Titre centré et coloré
            Paragraph title = new Paragraph("Ticket de Réservation")
                    .setFont(boldFont)
                    .setFontSize(20)
                    .setFontColor(ColorConstants.BLUE)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(20);
            document.add(title);

            // Informations principales centrées
            document.add(new Paragraph("ID du Ticket : " + ticket.getId())
                    .setFont(regularFont)
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(5));

            document.add(new Paragraph("Compagnie : " + ticket.getCompanies().getName())
                    .setFont(regularFont)
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(5));

            document.add(new Paragraph("Ville de départ : " + originCity)
                    .setFont(regularFont)
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(5));

            document.add(new Paragraph("Ville d'arrivée : " + destinationCity)
                    .setFont(regularFont)
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(5));

            document.add(new Paragraph("Date de départ : " + ticket.getBookings().getDeparts().getDepartureDate())
                    .setFont(regularFont)
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(5));

            document.add(new Paragraph("Heure de départ : " + ticket.getBookings().getDeparts().getDepartureTime())
                    .setFont(regularFont)
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(5));

            document.add(new Paragraph("Nombre de places : " + numberOfSeats)
                    .setFont(regularFont)
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(5));

            document.add(new Paragraph("Prix par place : " + pricePerSeat + " FCFA")
                    .setFont(regularFont)
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(5));

            document.add(new Paragraph("Prix total : " + totalPrice + " FCFA")
                    .setFont(boldFont)
                    .setFontSize(14)
                    .setFontColor(ColorConstants.RED)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(20));

            // Ajouter un QR code centré avec une bordure
            Paragraph qrCodeLabel = new Paragraph("Scannez le QR Code ci-dessous :")
                    .setFont(regularFont)
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(10);
            document.add(qrCodeLabel);

            Image qrCodeImage = new Image(ImageDataFactory.create(decodedQrCode))
                    .setWidth(UnitValue.createPercentValue(50))
                    .setHorizontalAlignment(com.itextpdf.layout.properties.HorizontalAlignment.CENTER);
            qrCodeImage.setBorder(new SolidBorder(ColorConstants.BLACK, 1));
            document.add(qrCodeImage);

            // Message de remerciement centré et coloré
            Paragraph thankYouMessage = new Paragraph("Merci d'avoir réservé avec nous !")
                    .setFont(boldFont)
                    .setFontSize(14)
                    .setFontColor(ColorConstants.GREEN)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginTop(30);
            document.add(thankYouMessage);

            // Ajouter le message de politique de remboursement
            Paragraph refundPolicy = new Paragraph(
                    "IMPORTANT : Le remboursement du ticket est impossible après l'achat ou après le départ du car.")
                    .setFont(italicFont)
                    .setFontSize(10)
                    .setFontColor(ColorConstants.RED)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginTop(15)
                    .setMarginBottom(15);
            document.add(refundPolicy);

            // Pied de page centré
            Paragraph footer = new Paragraph("Compagnie de Transport - Réservations en ligne")
                    .setFont(italicFont)
                    .setFontSize(10)
                    .setFontColor(ColorConstants.GRAY)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginTop(20);
            document.add(footer);
        }

        return outputFilePath; // Retourne le chemin du fichier
    }

    public void generateTicketPdf(Tickets ticket, String qrCodeBase64, String outputFilePath) throws Exception {
        // Préparer le contexte pour Thymeleaf

        // Récupérer les informations nécessaires
        String originCity = ticket.getBookings().getDeparts().getStations().getCities().getName();
        String destinationCity = ticket.getBookings().getDeparts().getStations2().getCities().getName();
        double pricePerSeat = ticket.getBookings().getDeparts().getPrice();
        int numberOfSeats = ticket.getBookings().getNumberOfSeats();
        double totalPrice = pricePerSeat * numberOfSeats;

        Context context = new Context();
        context.setVariable("ticketId", ticket.getId());
        context.setVariable("companyName", ticket.getCompanies().getName());
        context.setVariable("originCity",originCity);
        context.setVariable("destinationCity", destinationCity);
        context.setVariable("departureDate", ticket.getBookings().getDeparts().getDepartureDate());
        context.setVariable("departureTime", ticket.getBookings().getDeparts().getDepartureTime());
        context.setVariable("numberOfSeats", numberOfSeats);
        context.setVariable("totalPrice", totalPrice);
        context.setVariable("qrCodeImage", "data:image/png;base64," + qrCodeBase64);

        // Générer le contenu HTML
        String htmlContent = templateEngine.process("ticket.html", context);

        // Convertir le HTML en PDF
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            HtmlConverter.convertToPdf(htmlContent, new FileOutputStream(outputFilePath));
        }

    }


    /**
     * Génère un PDF à partir d'un modèle HTML avec Flying Saucer.
     *
     * @param ticket         L'objet Ticket contenant les informations nécessaires.
     * @param qrCodeBase64   L'image QR Code encodée en Base64.
     * @param outputFilePath Le chemin du fichier de sortie PDF.
     * @throws Exception En cas d'erreur lors de la génération du PDF.
     */


    public void generateTicketPdfWithFlyingSaucer(Tickets ticket, String qrCodeBase64, String outputFilePath) throws Exception {
        // Récupérer les informations nécessaires
        String originCity = ticket.getBookings().getDeparts().getStations().getCities().getName();
        String destinationCity = ticket.getBookings().getDeparts().getStations2().getCities().getName();
        double pricePerSeat = ticket.getBookings().getDeparts().getPrice();
        int numberOfSeats = ticket.getBookings().getNumberOfSeats();
        double totalPrice = pricePerSeat * numberOfSeats;

        // Formatter les dates en dd/MM/yyyy
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        String departureDate = dateFormatter.format(ticket.getBookings().getDeparts().getDepartureDate());
        String dateAchat = dateFormatter.format(ticket.getCreatedAt());

        Context context = new Context();
        context.setVariable("ticketId", ticket.getId());
        context.setVariable("companyName", ticket.getCompanies().getName());
        context.setVariable("originCity", originCity);
        context.setVariable("destinationCity", destinationCity);
        context.setVariable("departureDate", departureDate);
        context.setVariable("departureTime", ticket.getBookings().getDeparts().getDepartureTime());
        context.setVariable("numberOfSeats", numberOfSeats);
        context.setVariable("totalPrice", totalPrice);

        // Encode le QR Code pour éviter les problèmes XML
        context.setVariable("qrCodeImage", "data:image/png;base64," + HtmlUtils.htmlEscape(qrCodeBase64));

        context.setVariable("dateAchat", dateAchat);

        // Générer le contenu HTML
        String htmlContent = templateEngine.process("ticket.html", context);

        // Debugging: Vérifiez le contenu HTML généré
        System.out.println(htmlContent);

        // Convertir le HTML en PDF avec Flying Saucer
        try (FileOutputStream outputStream = new FileOutputStream(outputFilePath)) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(outputStream, false);
            renderer.finishPDF();
        }
    }

}
