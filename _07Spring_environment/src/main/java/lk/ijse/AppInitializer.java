package lk.ijse;

import lk.ijse.Config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;
import java.util.Properties;

public class AppInitializer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
//        context.register(AppConfigOne.class);
        context.refresh();
//        Map<String, String> getenv = System.getenv();
//        for (
//                String key : getenv.keySet()) {
//            System.out.println(key + " : " + getenv.get(key));
//        }

//        Properties properties = System.getProperties();
//        for (Object key : properties.keySet()) {
//            System.out.println(key + " : " + properties.get(key));
//        }
//        String property = System.getProperty("java.version");
//        System.getProperty("osName");
//        System.out.println(property);

        context.registerShutdownHook();


    }
}
