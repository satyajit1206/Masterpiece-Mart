package com.rest.product.restproduct.util;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import java.util.zip.DataFormatException;

public final class ImageUtils {
    private ImageUtils() {}
    public static byte[] compressImage(byte[] data) {
        Deflater deflater = new Deflater();
        try {
            deflater.setInput(data);
            deflater.finish();
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            while (!deflater.finished()) output.write(buffer, 0, deflater.deflate(buffer));
            return output.toByteArray();
        } finally { deflater.end(); }
    }
    public static byte[] decompressImage(byte[] data) {
        Inflater inflater = new Inflater();
        try {
            inflater.setInput(data);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                if (count == 0 && !inflater.finished()) throw new IllegalArgumentException("Invalid compressed image");
                output.write(buffer, 0, count);
                if (output.size() > 10 * 1024 * 1024) throw new IllegalArgumentException("Image exceeds maximum size");
            }
            return output.toByteArray();
        } catch (DataFormatException e) { throw new IllegalArgumentException("Invalid compressed image", e); }
        finally { inflater.end(); }
    }
}
