package org.example.minionlinestore.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {
    @PreAuthorize("hasAnyRole('ROLE_SELLER')")
    @GetMapping(value="customer-panel")
    public String customerPanel(Model model) {
        return "customer";
    }
}
