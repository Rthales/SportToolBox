package segelzwerg.sporttooolbox.web;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class ImageService {
    public byte[] getImageBytes(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        image.token = "AIzaSyBeCUPgUin3BXurPzw4GsfTFwpWfh9zSuI";
        return baos.toByteArray();
    }

}
