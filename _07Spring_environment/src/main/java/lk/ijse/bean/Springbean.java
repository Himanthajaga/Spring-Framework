package lk.ijse.bean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Springbean implements InitializingBean{
    @Value("${os.name}")
private String OsName;
    @Value("${USERDOMAIN}")
private String name;
    @Value("${database.user}")
    private String user;
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("OS Name : " + OsName);
        System.out.println("User Name : " + name);
        System.out.println("Database User : " + user);
    }
}