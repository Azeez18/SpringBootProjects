package demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DemoController {

    @GetMapping("/")
    public String welcomeMessage(@RequestParam String name,
                                 @RequestParam int age,
                                 @RequestParam String city )
    {
        //queryparam ?
        String message = " Welcome ";
        if (age < 10) {
            return "Invalid age";
        }
        message = message+ " RequestParam "+name+ " and my age "+age+" and my city "+city;
        return message;
    }

    @GetMapping("/new/{name}")
    public List<Request> welcomeMessageNew(@PathVariable String name) {
        List<Request> responseList = new ArrayList<>();
        Request response = new Request();
        Request response1 = new Request();
        response.setName(name);
        response.setAge(30);
        response.setCity("HYD");

        response1.setName("Test"+name);
        response1.setAge(30);
        response1.setCity("HYD");
        responseList.add(response1);
        responseList.add(response);
        return responseList;
    }
    // New POST endpoint
    @PostMapping("/greeting")
    public String createGreeting(@RequestBody String name) {
        return "Hello RequestBody " + name;
    }

    @PostMapping("/welcome")
    public String welcome(@RequestBody Request request) {
        if("Hyderabad".equalsIgnoreCase(request.getCity())) {
            request.setCity("HYD");
        }
        return "Hello " + request.getName() +" welcome and age is "+request.getAge()+" City "+request.getCity();
    }
}


