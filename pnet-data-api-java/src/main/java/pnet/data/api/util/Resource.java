package pnet.data.api.util;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Some binary response with data and type
 *
 * @author HAM
 */
public class Resource {

    private final String type;
    private final byte[] data;

    public Resource(String type, byte[] data) {
        super();
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public byte[] getData() {
        return data;
    }

    public Image toImage() {
        try (ByteArrayInputStream stream = new ByteArrayInputStream(data)) {
            return ImageIO.read(stream);
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to read image", e);
        }
    }
}
