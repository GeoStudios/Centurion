/*
 * Geo Studios Protective License
 *
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 *
 * Whoever collects this software or tool may not distribute the copy that has been obtained.
 *
 * This software or tool may not be used to gain a commercial or monetary advantage.
 *
 * Copyright will be included in any software or tool using this license, no matter the size or type of software or tool.
 *
 * This software or tool is not under any patent, but the software or tool shall not be
 * sold or uploaded as some other product or without the original creators consent and
 * permission. If the following happens, consequences will occur due to following
 * instructions or not following the rules written in this document.
 */

package java.base.share.classes.sun.util.locale.provider;

import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.ServiceConfigurationError;
import java.util.spi.LocaleServiceProvider;

/**
 * LocaleProviderAdapter implementation for the host locale data.
 * Currently it is only implemented on Windows Vista or later.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */
public class HostLocaleProviderAdapter extends AuxLocaleProviderAdapter {

    /**
     * Returns the type of this LocaleProviderAdapter
     */
    @Override
    public LocaleProviderAdapter.Type getAdapterType() {
        return LocaleProviderAdapter.Type.HOST;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected <P extends LocaleServiceProvider> P findInstalledProvider(final Class<P> c) {
        try {
            return (P)Class.forName(
                        "sun.util.locale.provider.HostLocaleProviderAdapterImpl")
                    .getMethod("get" + c.getSimpleName(), (Class<?>[]) null)
                    .invoke(null, (Object[]) null);
        } catch (ClassNotFoundException |
                 NoSuchMethodException ex) {
            // permissible exceptions as platform may not support host adapter
            return null;
        } catch (IllegalAccessException |
                 IllegalArgumentException |
                 InvocationTargetException ex) {
            throw new ServiceConfigurationError(
                "Host locale provider cannot be located.", ex);
        }
    }

    /**
     * Utility to make the decimal format specific to integer, called
     * by the platform dependent adapter implementations.
     *
     * @param df A DecimalFormat object
     * @return The same DecimalFormat object in the argument, modified
     *          to allow integer formatting/parsing only.
     */
    static DecimalFormat makeIntegerFormatter(DecimalFormat df) {
        df.setMaximumFractionDigits(0);
        df.setDecimalSeparatorAlwaysShown(false);
        df.setParseIntegerOnly(true);
        return df;
    }
}
