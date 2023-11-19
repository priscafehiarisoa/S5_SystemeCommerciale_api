package s5_systemecommerciale_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class S5SystemeCommercialeAPi2Application {

    public static void main(String[] args) {
        SpringApplication.run(S5SystemeCommercialeAPi2Application.class, args);
    }

    @GetMapping
    public String test(){
        System.out.println("mety io ");
        return "hello";
    }
}
