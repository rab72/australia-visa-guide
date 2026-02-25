package com.visaguide.controller;

import com.visaguide.model.VisaCategory;
import com.visaguide.service.VisaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class VisaController {

    private final VisaService visaService;

    public VisaController(VisaService visaService) {
        this.visaService = visaService;
    }

    @GetMapping("/")
    public String home(Model model,
                       @RequestParam(value = "q", required = false) String query,
                       @RequestParam(value = "category", required = false) String category) {

        List<VisaCategory> visas;

        if (query != null && !query.isBlank()) {
            visas = visaService.searchVisas(query);
            model.addAttribute("searchQuery", query);
        } else if (category != null && !category.isBlank()) {
            visas = visaService.getAllVisas().stream()
                    .filter(v -> v.getCategory().equalsIgnoreCase(category))
                    .toList();
            model.addAttribute("activeCategory", category);
        } else {
            visas = visaService.getAllVisas();
        }

        Map<String, List<VisaCategory>> grouped = visaService.getVisasByCategory();

        model.addAttribute("visas", visas);
        model.addAttribute("grouped", grouped);
        model.addAttribute("categories", grouped.keySet());
        model.addAttribute("totalCount", visaService.getAllVisas().size());

        return "index";
    }

    @GetMapping("/visa/{id}")
    public String visaDetail(@PathVariable String id, Model model) {
        Optional<VisaCategory> visa = visaService.getVisaById(id);
        if (visa.isEmpty()) {
            return "redirect:/";
        }
        model.addAttribute("visa", visa.get());
        return "visa-detail";
    }
}
