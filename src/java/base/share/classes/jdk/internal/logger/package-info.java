/java.base.share.classes.jdk.internal.logger
 java.base.share.classes.jdk.internal.logger Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 java.base.share.classes.jdk.internal.logger/

/java.base.share.classes.jdk.internal.loggerjava.base.share.classes.jdk.internal.logger
 java.base.share.classes.jdk.internal.logger <b>[JDK INTERNAL]</b>
 java.base.share.classes.jdk.internal.logger The {@code jdk.internal.logger} package defines an internal provider
 java.base.share.classes.jdk.internal.logger whose default naive implementation is replaced by the {@code java.logging}
 java.base.share.classes.jdk.internal.logger module when the {@code java.logging} module is present.
 java.base.share.classes.jdk.internal.logger <p>
 java.base.share.classes.jdk.internal.logger <b>Default Implementation</b>
 java.base.share.classes.jdk.internal.logger <p>
 java.base.share.classes.jdk.internal.logger The JDK default implementation of the System.LoggerFinder will attempt to
 java.base.share.classes.jdk.internal.logger load an installed instance of the {@link jdk.internal.logger.DefaultLoggerFinder}
 java.base.share.classes.jdk.internal.logger defined in this package.
 java.base.share.classes.jdk.internal.logger When the {@code java.util.logging} package is present, this will usually
 java.base.share.classes.jdk.internal.logger resolve to an instance of {@link sun.util.logging.internal.LoggingProviderImpl} -
 java.base.share.classes.jdk.internal.logger which provides an implementation of the Logger whose backend is a
 java.base.share.classes.jdk.internal.logger {@link java.util.logging.Logger java.util.logging.Logger}.
 java.base.share.classes.jdk.internal.logger Configuration can thus be performed by direct access to the regular
 java.base.share.classes.jdk.internal.logger {@code java.util.logging} APIs,
 java.base.share.classes.jdk.internal.logger using {@link java.util.logging.Logger java.util.logging.Logger} and
 java.base.share.classes.jdk.internal.logger {@link java.util.logging.LogManager} to access and configure the backend
 java.base.share.classes.jdk.internal.logger Loggers.
 java.base.share.classes.jdk.internal.logger <br>
 java.base.share.classes.jdk.internal.logger If however {@code java.util.logging} is not linked with the application, then
 java.base.share.classes.jdk.internal.logger the default implementation will return a simple logger that will print out
 java.base.share.classes.jdk.internal.logger all log messages of INFO level and above to the console ({@code System.err}),
 java.base.share.classes.jdk.internal.logger as implemented by the base {@link jdk.internal.logger.DefaultLoggerFinder} class.
 java.base.share.classes.jdk.internal.logger <p>
 java.base.share.classes.jdk.internal.logger <b>Message Levels and Mapping to java.util.logging</b>
 java.base.share.classes.jdk.internal.logger <p>
 java.base.share.classes.jdk.internal.logger The {@link java.lang.System.LoggerFinder} class documentation describe how
 java.base.share.classes.jdk.internal.logger {@linkplain java.lang.System.Logger.Level System.Logger levels} are mapped
 java.base.share.classes.jdk.internal.logger to {@linkplain java.util.logging.Level JUL levels} when {@code
 java.base.share.classes.jdk.internal.logger java.util.logging} is the backend.
 java.base.share.classes.jdk.internal.logger
 java.base.share.classes.jdk.internal.logger @see jdk.internal.logger.DefaultLoggerFinder
 java.base.share.classes.jdk.internal.logger @see sun.util.logging.internal.LoggingProviderImpl
 java.base.share.classes.jdk.internal.logger @see java.lang.System.LoggerFinder
 java.base.share.classes.jdk.internal.logger @see java.lang.System.Logger
 java.base.share.classes.jdk.internal.logger @see sun.util.logging.PlatformLogger.Bridge
 java.base.share.classes.jdk.internal.logger @see sun.util.logging.internal
 java.base.share.classes.jdk.internal.logger
 java.base.share.classes.jdk.internal.logger @since 9
 java.base.share.classes.jdk.internal.logger/
package jdk.internal.logger;
