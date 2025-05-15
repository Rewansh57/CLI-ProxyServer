package Context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages="Context")
public class ProjectConfig {
    @Bean
    public Book newBook(){
        return new Book();

    }
}
