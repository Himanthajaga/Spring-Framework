package lk.ijse;

import lk.ijse.bean.MyConnection;
import lk.ijse.bean.TestBean1;
import lk.ijse.bean.TestBean2;
import lk.ijse.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppInitializer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();
TestBean1 ref1 = context.getBean("testBean1",TestBean1.class);
TestBean1 ref2 = context.getBean("testBean1",TestBean1.class);
        System.out.println(ref1);
        System.out.println(ref2);

        TestBean2 ref3 = context.getBean("testBean2",TestBean2.class);
        TestBean2 ref4 = context.getBean("testBean2",TestBean2.class);
        System.out.println(ref3);
        System.out.println(ref4);

        MyConnection ref5 = context.getBean("connection",MyConnection.class);
        MyConnection ref6 = context.getBean("connection",MyConnection.class);
        System.out.println(ref5);
        System.out.println(ref6);

//        Object springBean = context.getBean("springBean");
//        System.out.println(springBean);
//        SpringBean bean = context.getBean(SpringBean.class);
//        System.out.println(bean);
//        bean.testBean();
//        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            System.out.println("Spring Container is shutting down...");
//            context.close();
//        }));
//        }

//        TestBean1 testBean1 = context.getBean(TestBean1.class);
//        System.out.println(testBean1);
//
////        TestBean2 testBean2 = (TestBean2) context.getBean("testBean2");
////        TestBean2 testBean2 = context.getBean("testBean2",TestBean2.class);
//        TestBean2 testBean2 = context.getBean("bean2",TestBean2.class);
//        System.out.println(testBean2);
//
//        MyConnection ref1 = context.getBean(MyConnection.class);
//        System.out.println(ref1);
//
//        MyConnection ref2 = context.getBean("connection", MyConnection.class);
//        System.out.println(ref2);

        context.registerShutdownHook();
    }
}
