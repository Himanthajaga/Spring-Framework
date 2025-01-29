package lk.ijse.Config;

import lk.ijse.bean.SpringBeanOne;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfigOne {
    public AppConfigOne() {
        System.out.println("AppConfigOne Constructor...");
    }
}
