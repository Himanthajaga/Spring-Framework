package lk.ijse;

import lk.ijse.Config.AppConfig;
import lk.ijse.Di.Test2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class AppInitializer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();

//        Boy boy = context.getBean(Boy.class);
        Test2 test2 = context.getBean(Test2.class);
        test2.Display();
        context.registerShutdownHook();
    }
}