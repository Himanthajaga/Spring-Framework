package lk.ijse.bean;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;

public class SpringBeanOne implements BeanNameAware, ApplicationContextAware {
@Bean
    public SpringBeanTwo springBeanTwo() {
      SpringBeanThree springBeanThree1 = springBeanThree();
        SpringBeanThree springBeanThree2 = springBeanThree();
    System.out.println(springBeanThree1);
    System.out.println(springBeanThree2);
        return new SpringBeanTwo();
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Bean Name : " + name);
    }
    public SpringBeanThree springBeanThree() {
        return new SpringBeanThree();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("Application Context : " + applicationContext);
    }
}
