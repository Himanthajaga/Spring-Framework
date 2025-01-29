package lk.ijse.Config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
//@ImportResource("classpath:lk/ijse/Config/beans.xml")

//@ImportResource("file:absolute-path-of-hibernate.xml")
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "lk.ijse.bean")
public class AppConfig {

}
