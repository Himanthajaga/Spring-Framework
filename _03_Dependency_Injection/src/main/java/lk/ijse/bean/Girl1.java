package lk.ijse.bean;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Girl1 implements Agreement{


    public Girl1() {
        System.out.println("Girl object Instantiated...");
    }

    @Override
    public void chat() {
        System.out.println("Chatting Girl 1");
    }
}
