package ru.sonyabeldy.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/first")
public class FirstController {

    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "surname", required = false) String surname,
                            Model model) {

//        System.out.println("Hello, " + name + " " + surname);
        model.addAttribute("message", "Hello, " + name + " " + surname);

        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodByePage() {
        return "first/goodbye";
    }

    @GetMapping("/calculator")
    public String calc(@RequestParam(value = "a") int a,
                       @RequestParam(value = "b") int b,
                       @RequestParam(value = "action") String action,
                       Model model) {

        int res = a + b;
        String actionSymbol = "+";
        switch (action) {
            case "mul":
                res = a * b;
                actionSymbol = "*";
                break;
            case "sub":
                res = a - b;
                actionSymbol = "-";
                break;
            case "div":
                res = a/b;
                actionSymbol = "/";
        }
        model.addAttribute("calc", a + " " + actionSymbol + " " + b + " = " + res);
        return "first/calculator";
    }
}
