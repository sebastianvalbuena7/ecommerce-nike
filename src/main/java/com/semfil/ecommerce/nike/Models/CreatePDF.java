package com.semfil.ecommerce.nike.Models;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class CreatePDF {
    public static void generatePDF(Set<PaymentClient> paymentClients, Client client, HttpServletResponse httpServletResponse) throws Exception {
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=payment.pdf");

        Font fontLogo = FontFactory.getFont("Helvetica", 30, Color.BLACK);
        Font fontTitle = FontFactory.getFont("Helvetica", 20, Color.BLACK);
        Font fontText = FontFactory.getFont("Helvetica", 15, Color.BLACK);

        Paragraph logo = new Paragraph("E-COMMERCE NIKE", fontLogo);
        logo.setAlignment(Element.ALIGN_CENTER);
        logo.setSpacingAfter(12);

        Paragraph title = new Paragraph("Your commercial invoice", fontTitle);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(12);

        Paragraph subTitle = new Paragraph("Client: " + client.getFirstName() + " " + client.getLastName(), fontText);
        subTitle.setAlignment(Element.ALIGN_CENTER);
        subTitle.setSpacingAfter(20);

        PdfPTable tablePayments = new PdfPTable(3);
        tablePayments.setSpacingAfter(15);
        Stream.of("Date", "Amount", "Client").forEach(t -> tablePayments.addCell(t));

        paymentClients.forEach(paymentClient -> {
            tablePayments.addCell(String.valueOf(paymentClient.getLocalDateTime()));
            tablePayments.addCell(String.valueOf(paymentClient.getTotalAmount()));
            tablePayments.addCell(String.valueOf(paymentClient.getClient().getEmail()));
        });

        Document document = new Document();
        document.setPageSize(PageSize.LETTER.rotate());
        PdfWriter.getInstance(document, httpServletResponse.getOutputStream());
        //abrir el documento
        document.open();
        //agregar contenido al documento
        document.add(logo);
        document.add(title);
        document.add(subTitle);
        document.add(tablePayments);
        //cerrar el documento
        document.close();
    }
}