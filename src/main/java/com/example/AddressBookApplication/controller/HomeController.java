package com.example.AddressBookApplication.controller;


import com.example.AddressBookApplication.model.dto.BindingModels.InputDataBindingModel;
import com.example.AddressBookApplication.service.email.EmailService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private final EmailService emailService;

    public HomeController(EmailService emailService) {
        this.emailService = emailService;
    }


    @GetMapping("/")
    public ModelAndView home() {

        InputDataBindingModel inputDataBindingModel = new InputDataBindingModel();
        return new ModelAndView("/index").addObject("inputDataBindingModel", inputDataBindingModel);
    }

    @GetMapping("/index")
    public ModelAndView index() {

        InputDataBindingModel inputDataBindingModel = new InputDataBindingModel();
        return new ModelAndView("/index").addObject("inputDataBindingModel", inputDataBindingModel);
    }

    @GetMapping("/result-ok")
    public ModelAndView resultOk() {
        return new ModelAndView("/result-ok");
    }

    @GetMapping("/result-not-ok")
    public ModelAndView resultNotOk() {
        return new ModelAndView("/result-not-ok");
    }


    @PostMapping("/sendEmail")
    public ModelAndView sendEmail(@ModelAttribute("inputDataBindingModel")
                               @Valid InputDataBindingModel inputDataBindingModel) {

        try {
            emailService.sendEmail(inputDataBindingModel);
            return new ModelAndView("redirect:/result-ok");
        }
        catch (Exception e) {
            return new ModelAndView("redirect:/result-not-ok");
        }

    }
}
