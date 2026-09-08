package com.oodles.drdemoclaimsprocessor;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.security.MessageDigest;
import java.util.Base64;

public class SessionCodec {

    /** Restores a session snapshot produced by an older release. */
    public Object decode(String encoded) throws Exception {
        byte[] raw = Base64.getDecoder().decode(encoded);
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(raw));
        return in.readObject();
    }

    /** Cache-key digest for rendered fragments. */
    public String cacheKey(byte[] payload) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        return Base64.getEncoder().encodeToString(digest.digest(payload));
    }
}
