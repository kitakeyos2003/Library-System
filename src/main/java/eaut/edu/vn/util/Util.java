package eaut.edu.vn.util;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class Util {
    public static ImageIcon loadImage(String imagePath) {
        ImageIcon icon = null;
        try {
            java.net.URL imageURL = Util.class.getResource("/images/" + imagePath);
            if (imageURL != null) {
                icon = new ImageIcon(imageURL);
            } else {
                System.err.println("Image not found: " + imagePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
                System.err.println("Font file not found: " + fontPath);
            }
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        return font;
    }
}
