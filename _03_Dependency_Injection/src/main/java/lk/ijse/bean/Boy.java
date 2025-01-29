package lk.ijse.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Boy {
    @Autowired
    @Qualifier("girl1")
   Agreement girl;
    public void chatWithGirl(){
      //Girl girl = new Girl();
       girl.chat();
    }

    public Boy() {
        System.out.println("Boy object Instantiated...");
    }
}
