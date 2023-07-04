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
package java2d;


import java.awt.Component;
import java.awt.Image;
import java.awt.MediaTracker;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * A collection of all the demo images found in the images directory.
 * Certain classes are preloaded; the rest are loaded lazily.
 */
@SuppressWarnings("serial")
public class DemoImages extends Component {

    private static final String[] names = {
        "bld.jpg", "boat.png", "box.png",
        "boxwave.png", "clouds.jpg", "duke.gif", "duke.running.gif",
        "dukeplug.png", "fight.png", "globe.png",
        "jumptojavastrip.png", "magnify.png", "painting.png",
        "remove.gif", "snooze.png", "star7.gif", "surfing.png",
        "thumbsup.png", "tip.png", "duke.png", "print.gif",
        "loop.gif", "looping.gif", "start.gif", "start2.gif",
        "stop.gif", "stop2.gif", "clone.gif"
    };
    private static final Map<String, Image> cache =
            new ConcurrentHashMap<String, Image>(names.length);

    private DemoImages() {
    }

    public static void newDemoImages() {
        DemoImages demoImages = new DemoImages();
        for (String name : names) {
            cache.put(name, getImage(name, demoImages));
        }
    }


    /*
     * Gets the named image using the toolkit of the specified component.
     * Note that this has to work even before we have had a chance to
     * instantiate DemoImages and preload the cache.
     */
    public static Image getImage(String name, Component cmp) {
        Image img = null;
        if (cache != null) {
            if ((img = cache.get(name)) != null) {
                return img;
            }
        }

        ClassLoader cl = cmp.getClass().getClassLoader();
        URL fileLoc = cl.getResource("images/" + name);
        img = cmp.getToolkit().getImage(fileLoc);

        MediaTracker tracker = new MediaTracker(cmp);
        tracker.addImage(img, 0);
        try {
            tracker.waitForID(0);
            if (tracker.isErrorAny()) {
                System.out.println("Error loading image " + name);
            }
        } catch (Exception ex) {
            Logger.getLogger(DemoImages.class.getName()).log(Level.SEVERE, null, ex);
        }
        return img;
    }
}
