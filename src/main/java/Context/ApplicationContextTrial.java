package Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextTrial {
    private static final Logger log = LoggerFactory.getLogger(ApplicationContextTrial.class);

    public static void main(String [] args){
        ApplicationContext context=new AnnotationConfigApplicationContext(ProjectConfig.class);
    Book book1=context.getBean(Book.class);
    book1.getName();


    }
}
