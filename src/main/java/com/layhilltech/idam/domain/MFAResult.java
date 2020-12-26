package com.layhilltech.idam.domain;

public class MFAResult {
    private final String mfaQrUri;
    private final String secretKey;

    public MFAResult(String mfaQrUri, String secretKey) {
        this.mfaQrUri = mfaQrUri;
        this.secretKey = secretKey;
    }

    public String getKeyUri() {
        return mfaQrUri;
    }

    public String getSecretKey() {
        return secretKey;
    }
}
