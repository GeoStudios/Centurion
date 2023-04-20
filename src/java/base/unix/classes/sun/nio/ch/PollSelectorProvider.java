/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.unix.classes.sun.nio.ch;

import java.io.IOException;
import java.nio.channels.Channel;
import java.nio.channels.spi.AbstractSelector;

/**
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */

public class PollSelectorProvider
    extends SelectorProviderImpl
{
    public AbstractSelector openSelector() throws IOException {
        return new PollSelectorImpl(this);
    }

    public Channel inheritedChannel() throws IOException {
        return InheritedChannel.getChannel();
    }
}
