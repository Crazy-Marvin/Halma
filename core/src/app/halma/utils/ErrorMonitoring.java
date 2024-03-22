package app.halma.utils;

import io.sentry.Sentry;
import io.sentry.SentryLevel;

public class ErrorMonitoring {
    public static void init() {
        Sentry.init(options -> {
            options.setDsn("https://378ae0f63eb29c1e801856858805a0f9@o282785.ingest.us.sentry.io/4506740692811776");

            // Configure the background worker which sends events to sentry:
            // Wait up to 5 seconds before shutdown while there are events to send.
            options.setShutdownTimeoutMillis(5000);

            // Enable SDK logging with Debug level
            options.setDebug(true);
            // To change the verbosity, use:
            options.setDiagnosticLevel(SentryLevel.ERROR);
        });
    }

    public static void startMonitoring() {
        Sentry.startSession();
    }

    public static void stopMonitoring() {
        Sentry.endSession();
        Sentry.close();
        HalmaPreferences.monitorErrors(false);
    }
}
