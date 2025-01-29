package lk.ijse.bean;

import org.springframework.stereotype.Component;

//@Component
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SpringBean {
    public SpringBean() {
        System.out.println("Spring Bean 1 object Instantiated...");
    }
}
