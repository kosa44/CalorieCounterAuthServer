package pl.kosa.caloriecounter.config;

import org.keycloak.platform.PlatformProvider;
import org.keycloak.services.ServicesLogger;

public class SimplePlatformProvider implements PlatformProvider {

    Runnable shutdownHook;

    @Override
    public void onStartup(Runnable runnable) {
        runnable.run();
    }

    @Override
    public void onShutdown(Runnable runnable) {
        this.shutdownHook = runnable;
    }

    @Override
    public void exit(Throwable throwable) {
        ServicesLogger.LOGGER.fatal(throwable);
        exit(1);
    }

    private void exit(int status) {
        new Thread() {
            @Override
            public void run() {
                System.exit(status);
            }
        }.start();
    }
}
