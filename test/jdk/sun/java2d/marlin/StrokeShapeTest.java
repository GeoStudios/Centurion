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
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * @author chrisn@google.com (Chris Nokleberg)
 * @author yamauchi@google.com (Hiroshi Yamauchi)
 */
public class StrokeShapeTest {
  public static void main(String[] args) throws Exception {
    BufferedImage image = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
    Graphics2D g = image.createGraphics();
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g.setPaint(Color.WHITE);
    g.fill(new Rectangle(image.getWidth(), image.getHeight()));
    g.translate(25, 100);

    Stroke stroke = new BasicStroke(200, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
    Shape shape = new Polygon(new int[] {0, 1500, 0}, new int[] {750, 0, -750}, 3);

    g.scale(.1, .1);
    g.setPaint(Color.BLACK);
    g.setStroke(stroke);
    g.draw(shape);
    g.setPaint(Color.RED);
    g.fill(stroke.createStrokedShape(shape));

    // To visually check it
    //ImageIO.write(image, "PNG", new File(args[0]));

    boolean blackPixelFound = false;
    outer:
    for (int x = 0; x < 200; ++x) {
      for (int y = 0; y < 200; ++y) {
        if (image.getRGB(x, y) == Color.BLACK.getRGB()) {
          blackPixelFound = true;
          break outer;
        }
      }
    }
    if (blackPixelFound) {
      throw new RuntimeException("The shape hasn't been filled in red.");
    }
  }
}
