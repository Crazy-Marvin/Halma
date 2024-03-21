package app.halma.utils;

import io.sentry.Sentry;
import io.sentry.SentryLevel;

public class ErrorMonitoring {
    public static void init() {
        Sentry.init(options -> {
            options.setDsn("https://46f71f9d000c4ee6ac65f25f9a81b153@o282785.ingest.sentry.io/1509904");

            // Configure the background worker which sends events to sentry:
            // Wait up to 5 seconds before shutdown while there are events to send.
            options.setShutdownTimeout(5000);

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
