/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.macosx.classes.sun.nio.ch;

import java.base.share.classes.java.io.IOException;
import java.base.share.classes.java.nio.channels.spi.AbstractSelector;
import java.nio.channels.*;

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 23/4/2023
 */

public class KQueueSelectorProvider
    extends SelectorProviderImpl
{
    public AbstractSelector openSelector() throws IOException {
        return new KQueueSelectorImpl(this);
    }

    public Channel inheritedChannel() throws IOException {
        return InheritedChannel.getChannel();
    }
}
