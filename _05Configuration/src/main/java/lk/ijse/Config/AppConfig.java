package lk.ijse.Config;

import lk.ijse.bean.SpringBeanOne;
import lk.ijse.bean.Springbean;
import org.springframework.context.annotation.*;

@Configuration
@Import({AppConfigOne.class,AppConfigTwo.class})
//@ImportResource("classpath:lk/ijse/Config/beans.xml")

//@ImportResource("file:absolute-path-of-hibernate.xml")

//@ComponentScan(basePackages = "lk.ijse.bean")
public class AppConfig {
    @Bean
    public SpringBeanOne getspringbean(){
        return new SpringBeanOne();
    }
}
