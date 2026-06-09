package org.kinemine.jsonserializer.impl;

import org.kinemine.jsonserializer.JsonSerializer;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;

public class BufferedImageSerializer implements JsonSerializer<BufferedImage> {

    @Override
    public String serialize(BufferedImage image) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", bos); 
        }
        catch (IOException ignore) {} // impossible
        byte[] imageBytes = bos.toByteArray();
        StringBuilder sb = new StringBuilder();
        sb.append('\"');
        sb.append(Base64.getEncoder().encodeToString(imageBytes));
        sb.append('\"');
        return sb.toString();
    }

    @Override
    public Class<BufferedImage> getSupportedType() {
        return BufferedImage.class;
    }

}
