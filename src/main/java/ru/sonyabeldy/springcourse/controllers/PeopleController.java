package ru.sonyabeldy.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sonyabeldy.springcourse.models.Person;
import ru.sonyabeldy.springcourse.services.ItemService;
import ru.sonyabeldy.springcourse.services.PeopleService;
import ru.sonyabeldy.springcourse.util.PersonValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

//    private final PersonDAO personDAO;
    private final PeopleService peopleService;
    private final ItemService itemService;
    private final PersonValidator personValidator;


    @Autowired
    public PeopleController(PeopleService peopleService, PersonValidator personValidator, ItemService itemService) {
        this.personValidator = personValidator;
        this.peopleService = peopleService;
        this.itemService = itemService;
    }

    @GetMapping()
    public String index(Model model) {
        //Получим всех людей из DAO и передадим на отображение в представление
        model.addAttribute("people", peopleService.findAll());

        itemService.findByItemName("Airpods");
        itemService.findByOwner(peopleService.findAll().get(0));

        peopleService.test();

        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        //получим одного человека по id из DAO и передадим отображение в представление
        model.addAttribute("person", peopleService.findOne(id));
        return "people/show";
    }
    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());

        return "people/new";
    }
    @PostMapping
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {

//        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/new";
        }
        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", peopleService.findOne(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult, @PathVariable("id") int id) {

        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/edit";
        }
        peopleService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        peopleService.delete(id);
        return "redirect:/people";
    }



}
