package com.zuhlke.authenticator.infrastructure.services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class QRCodeGenerator
{
    public File generate(String totpKeyUri, int width, int height, String filePath) {
        var writer = new MultiFormatWriter();
        try {
            var bitMatrix = writer.encode(totpKeyUri, BarcodeFormat.QR_CODE, width, height);
            try(FileOutputStream qrcodeFile = new FileOutputStream(filePath)){
                MatrixToImageWriter.writeToStream(bitMatrix,"png", qrcodeFile );
            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            }
            return new File(filePath);
        } catch (WriterException e) {

        }
        return null;
    }
}
