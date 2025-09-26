package vn.BAITAP.TestAPI.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitProgram implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub
        System.out.println(">>>>>>> The program is running on 8017");
    }

}
