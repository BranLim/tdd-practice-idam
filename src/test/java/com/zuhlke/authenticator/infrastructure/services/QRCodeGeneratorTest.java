package com.zuhlke.authenticator.infrastructure.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class QRCodeGeneratorTest {

    @Test
    public void generateTotpQRCodeSucceed() {
        String totpKeyUri = "otpauth://totp/ZuhlkeTest%3Atestuser1@ZuhlkeTest.com?secret=PJ2WQ3DLMVSW24DPO5SXE2LOM5UWIZLB&issuer=ZuhlkeTest";
        int width = 256;
        int height = 256;
        String filePath = "test.png";

        QRCodeGenerator generator = new QRCodeGenerator();
        File qrcodeFile = generator.generate(totpKeyUri, width, height, filePath);

        Assertions.assertTrue(qrcodeFile != null && qrcodeFile.exists());

    }
}
