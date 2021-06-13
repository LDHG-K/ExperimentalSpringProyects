package com.Quda.MailProyect.Servicios;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.alignment.HorizontalAlignment;
import com.lowagie.text.alignment.VerticalAlignment;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.io.*;

@Service
public class PdfServicio  {

    public void exportarPdf(String mail, String total, Boolean encriptacion) throws FileNotFoundException {
        
        Document documento = new Document(PageSize.LETTER);


        PdfWriter writer = PdfWriter.getInstance(documento,new FileOutputStream(new File("/home/luis/Escritorio/Proyectos Spring Boot/ExperimentalSpringProyects/MailProyect/MailProyect/src/main/resources/static/"+mail+".pdf")));

        //Encriptacion ====================================
        if (encriptacion){
            writer.setEncryption("USER_PASSWORD".getBytes(), "OWNER_PASSWORD".getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
        }

        //=================================================

        //Tabla      ======================================
        Font font = new Font(Font.HELVETICA, 12, Font.BOLD);
        Table table = new Table(3);
        table.setPadding(5);
        table.setSpacing(1);
        table.setWidth(100);
        table.setBorderWidth(1/4);


        Cell cell = new Cell("Informacion de la factura");
        cell.setHeader(true);
        cell.setVerticalAlignment(VerticalAlignment.CENTER);
        cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
        cell.setColspan(3);
        cell.setBackgroundColor(Color.pink);
        table.addCell(cell);

        table.addCell(new Phrase("ITEM", font));
        table.addCell(new Phrase("UNIDADES", font));
        table.addCell(new Phrase("TOTAL", font));
        table.endHeaders();

        table.addCell("Shampoo");
        table.addCell("3");
        table.addCell("24.500");

        Cell cellTotal = new Cell("Total : "+total);
        cellTotal.setHeader(true);
        cellTotal.setVerticalAlignment(VerticalAlignment.CENTER);
        cellTotal.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        cellTotal.setColspan(3);
        cellTotal.setBackgroundColor(Color.pink);
        table.addCell(cellTotal);

        //===================================================


        documento.open();

        documento.addCreationDate();
        documento.addTitle("GRACIAS POR TU COMPRA");
        documento.add(table);


        documento.close();
        writer.close();
    }
    /*
    Para poder encriptar hace falta esta dependencia
    <dependency>
        <groupId>org.bouncycastle</groupId>
        <artifactId>bcprov-jdk15on</artifactId>
        <version>1.61</version>
    </dependency>
     */


}
