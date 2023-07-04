/*
 * Copyright (c) 2023 Geo-Studios and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 only, as published
 * by the Free Software Foundation. Geo-Studios designates this particular
 * file as subject to the "Classpath" exception as provided
 * by Geo-Studio in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License version 2 for more details (a copy is
 * included in the LICENSE file that accompanied this code).
 *
 * You should have received a copy of the GNU General Public License
 * version 2 along with this work; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * @author chrisn@google.com (Chris Nokleberg)
 * @author yamauchi@google.com (Hiroshi Yamauchi)
 */
public class ThinLineTest {
  private static final int PIXEL = 381;

  public static void main(String[] args) throws Exception {
    BufferedImage image = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
    Graphics2D g = image.createGraphics();

    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g.setPaint(Color.WHITE);
    g.fill(new Rectangle(image.getWidth(), image.getHeight()));

    g.scale(0.5 / PIXEL, 0.5 / PIXEL);
    g.setPaint(Color.BLACK);
    g.setStroke(new BasicStroke(PIXEL));
    g.draw(new Ellipse2D.Double(PIXEL * 50, PIXEL * 50, PIXEL * 300, PIXEL * 300));

    // To visually check it
    //ImageIO.write(image, "PNG", new File(args[0]));

    boolean nonWhitePixelFound = false;
    for (int x = 0; x < 200; ++x) {
      if (image.getRGB(x, 100) != Color.WHITE.getRGB()) {
        nonWhitePixelFound = true;
        break;
      }
    }
    if (!nonWhitePixelFound) {
      throw new RuntimeException("The thin line disappeared.");
    }
  }
}
