package lk.ijse;

import lk.ijse.Config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppInitializer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
//        context.register(AppConfigOne.class);
        context.refresh();

        context.registerShutdownHook();


    }
}
