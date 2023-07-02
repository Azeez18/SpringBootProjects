package demo;

import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/new/{name}/{city}")
    public String welcomeMessageNew(@PathVariable String name, @PathVariable String city) {
        //append URL
        return "Hello PathVariable "+name+" and my city "+city;
    }
}