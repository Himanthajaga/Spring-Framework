package lk.ijse.bean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Springbean implements InitializingBean {
    @Value("Udara")
    private String name;
    public Springbean() {
        System.out.println(name);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(name);
    }

//@Autowired(required = false)
//    public Springbean(@Value("Udara") String name,
//                      @Value("1") int id,@Value("true") boolean status) {
//        {
//            System.out.println("Spring Bean constructor...");
//            System.out.println(name);
//            System.out.println(id);
//            System.out.println(status);
//        }
//    }
//@Autowired(required = false)
//    public Springbean(@Value("Udara") String name,
//                      @Value("1") int id) {
//        {
//            System.out.println("Spring Bean constructor...");
//            System.out.println(name);
//            System.out.println(id);
//        }
//    }
}