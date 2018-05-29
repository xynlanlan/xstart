package com.example.start.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public final class ValidationCodeJPG {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationCodeJPG.class);

    private String code;

    private BufferedImage image;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    /**
     * 写出图片
     *
     * @param out
     */
    public void write(OutputStream out) {
        try {
            ImageIO.write(this.getImage(), "jpg", out);
        } catch (IOException e) {
            LOGGER.error("Generate Validation Code", e);
        }
    }

    /**
     * 写出图片
     *
     * @param path
     */
    public void write(String path) {
        try {
            write(new FileOutputStream(path));
        } catch (IOException e) {
            LOGGER.error("Generate Validation Code", e);
        }
    }

    public ValidationCodeJPG() {
        this(120, 30, 4, 4, 18);
    }

    public ValidationCodeJPG(int width, int height, int len, int lineNum, int fontSize) {
        int tlen = len;
        if (len == 0) {
            tlen = 4;
        }
        this.setCode(RandomUtils.randomCodeStr(tlen));
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufferedImage.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        Font[] fonts = {new Font("宋体", Font.BOLD, fontSize),
                new Font("Arial", Font.ITALIC, fontSize), new Font("fantasy", Font.ITALIC, fontSize), new Font("helvetica", Font.ITALIC, fontSize),
                new Font("Arial", Font.HANGING_BASELINE, fontSize), new Font("fantasy", Font.HANGING_BASELINE, fontSize), new Font("helvetica", Font
                .HANGING_BASELINE, fontSize),
                new Font("Arial", Font.BOLD, fontSize), new Font("fantasy", Font.BOLD, fontSize), new Font("helvetica", Font.BOLD, fontSize)
        };
        /*for (int i = 0; i < lineNum; i++) {
            int xs = RandomUtils.randomInt(width);
            int ys = RandomUtils.randomInt(height);
            int xe = xs + RandomUtils.randomInt(width / 4);
            int ye = ys + RandomUtils.randomInt(height / 4);
            g.setColor(new Color(RandomUtils.randomInt(255), RandomUtils.randomInt(255), RandomUtils.randomInt(255)));
            g.setStroke(new BasicStroke(RandomUtils.randomInt(4)));
            g.drawLine(xs, ys, xe, ye);
            g.setStroke(new BasicStroke(RandomUtils.randomInt(4)));
            int arcW = RandomUtils.randomInt(width);
            g.drawArc(RandomUtils.randomInt(width),
                    RandomUtils.randomInt(height),
                    arcW,
                    arcW,
                    RandomUtils.randomInt(360),
                    RandomUtils.randomInt(360));
        }*/

        char[] chars = this.getCode().toCharArray();
        int offset = 10;
        for (int i = 0; i < chars.length; i++) {
            g.setFont(fonts[RandomUtils.randomInt(fonts.length)]);
            g.setColor(new Color(RandomUtils.randomInt(255), RandomUtils.randomInt(255), RandomUtils.randomInt(255)));
            g.drawString(chars[i] + "", offset, fontSize);
            offset+=30;
        }

        this.setImage(bufferedImage);
    }
}