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

    public static String numberFormat(long m) {
        StringBuilder text = new StringBuilder();
        long num = m / 1000L + 1L;
        int num2 = 0;
        while ((long) num2 < num) {
            if (m < 1000L) {
                text.insert(0, m);
                break;
            }
            long num3 = m % 1000L;
            if (num3 == 0L) {
                text.insert(0, ".000");
            } else if (num3 < 10L) {
                text.insert(0, ".00" + num3);
            } else if (num3 < 100L) {
                text.insert(0, ".0" + num3);
            } else {
                text.insert(0, "." + num3);
            }
            m /= 1000L;
            num2++;
        }
        return text.toString();
    }

    public static boolean isNumber(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
