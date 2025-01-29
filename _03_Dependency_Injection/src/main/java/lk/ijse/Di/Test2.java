package lk.ijse.Di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Test2 implements DIInterface{
//    @Autowired
    DI test1;
//    Constructor Injection
//@Autowired
//    public Test2(DI test1) {
//       this.test1 = test1;
//    }

//Setter Injection
//    @Autowired
//    public void setter(DI test1){
//        this.test1 = test1;
//    }
    public void Display(){
        test1.sayHello();
    }
//    Interface Injection

@Autowired
    @Override
    public void inject(DI test1) {
        this.test1 = test1;
    }
}
