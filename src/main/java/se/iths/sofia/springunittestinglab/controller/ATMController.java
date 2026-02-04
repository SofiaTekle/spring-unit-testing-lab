package se.iths.sofia.springunittestinglab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.iths.sofia.springunittestinglab.service.ATMService;

@Controller
@RequestMapping("/")
public class ATMController {
    private ATMService atmService;

    public ATMController(ATMService atmService) {
        this.atmService = atmService;
    }

    @GetMapping
    public String getBalance(Model model) {
        int balance = atmService.getAccountBalance();
        model.addAttribute("balance", balance);
        return "balance";

    }


}
