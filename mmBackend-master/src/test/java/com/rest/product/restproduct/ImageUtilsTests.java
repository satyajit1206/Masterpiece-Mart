package com.rest.product.restproduct;
import org.junit.jupiter.api.Test;
import com.rest.product.restproduct.util.ImageUtils;
import static org.junit.jupiter.api.Assertions.*;
class ImageUtilsTests {
    @Test void imageRoundTrips() { byte[] data = "sample image bytes".getBytes(); assertArrayEquals(data, ImageUtils.decompressImage(ImageUtils.compressImage(data))); }
    @Test void truncatedImageFailsInsteadOfHanging() { assertTimeoutPreemptively(java.time.Duration.ofSeconds(1), () -> assertThrows(IllegalArgumentException.class, () -> ImageUtils.decompressImage(new byte[]{120, -100}))); }
}
