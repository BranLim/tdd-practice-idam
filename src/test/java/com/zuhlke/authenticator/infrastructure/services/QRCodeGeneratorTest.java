package com.zuhlke.authenticator.infrastructure.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class QRCodeGeneratorTest {

    @Test
    public void generateTotpQRCodeSucceed() {
        String totpKeyUri = "otpauth://totp/Example%3Atestuser1%40example%2Ecom%3Fsecret%3DPJ2WQ3DLMVSW24DPO5SXE2LOM5UWIZLB";
        int width = 256;
        int height = 256;
        String filePath = "test.png";

        QRCodeGenerator generator = new QRCodeGenerator();
        File qrcodeFile = generator.generate(totpKeyUri, width, height, filePath);

        Assertions.assertTrue(qrcodeFile != null && qrcodeFile.exists());

    }
}
