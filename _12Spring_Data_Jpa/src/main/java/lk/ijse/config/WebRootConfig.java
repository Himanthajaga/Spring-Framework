package lk.ijse.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//parent context services handling
@Configuration
@Import(JPAConfig.class)
public class WebRootConfig {
}
