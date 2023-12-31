package eaut.edu.vn.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Util {
    public static ImageIcon loadImage(String imagePath) {
        ImageIcon icon = null;
        try {
            java.net.URL imageURL = Util.class.getResource("/images/" + imagePath);
            if (imageURL != null) {
                icon = new ImageIcon(imageURL);
            } else {
                Log.error("Image not found: " + imagePath);
            }
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
        return icon;
    }

    public static Font loadFontFromResource(String fontPath, int fontStyle, float fontSize) {
        Font font = null;

        try (InputStream fontStream = Util.class.getResourceAsStream("/fonts/" + fontPath)) {
            if (fontStream != null) {
                Font baseFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
                font = baseFont.deriveFont(fontStyle, fontSize);
            } else {
                Log.error("Font file not found: " + fontPath);
            }
        } catch (FontFormatException | IOException e) {
            Log.error(e.getMessage(), e);
        }

        return font;
    }

    public static ImageIcon resizedImage(String imagePath, int newWidth, int newHeight) {
        try {
            URL imageURL = Util.class.getResource("/images/" + imagePath);
            BufferedImage originalImage = ImageIO.read(imageURL);
            Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        } catch (IOException e) {
            Log.error("resize image", e);
        }
        return null;
    }
}
