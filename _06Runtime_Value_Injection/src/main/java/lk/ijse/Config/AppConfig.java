package lk.ijse.Config;

import lk.ijse.bean.Springbean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
//@ImportResource("classpath:lk/ijse/Config/beans.xml")

//@ImportResource("file:absolute-path-of-hibernate.xml")

@ComponentScan(basePackages = "lk.ijse.bean")
public class AppConfig {

}
