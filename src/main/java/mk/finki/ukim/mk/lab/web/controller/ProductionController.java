package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Production;
import mk.finki.ukim.mk.lab.service.ProductionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/productions")
public class ProductionController {
    public final ProductionService productionService;

    public ProductionController(ProductionService productionService) {
        this.productionService = productionService;
    }

    @GetMapping
    public String getProductionsPage( Model model) {
        //productionService.transferDataToDatabase();
        List<Production> productions = productionService.findAll();
        model.addAttribute("productions", productions);
        return "listProductions";
    }
    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        this.productionService.deleteById(id);
        return "redirect:/productions";
    }
    @GetMapping("/add-formP")
    public String getAddMoviePage() {
        return "add-production";
    }
    @PostMapping("/add")
    public String saveProduction(@RequestParam String productionName,
                                 @RequestParam String address,
                                 @RequestParam String country) {
        this.productionService.saveProduction(productionName, country, address);
        return "redirect:/productions";
    }
    @GetMapping("/edit-formP/{id}")
    public String getEditMovieForm(@PathVariable Long id, Model model) {
        if(this.productionService.findById(id).isPresent()) {
            Production production = this.productionService.findById(id).get();
            model.addAttribute("production", production);
            return "add-production";
        }
        return "redirect:/production?error=ProductionNotFound";
    }
    @PostMapping("/productions/edit/{productionId}")
    public String editProduction(@PathVariable Long productionId,
                                 @RequestParam String productionName,
                                 @RequestParam String country,
                                 @RequestParam String address) {
        Production production = this.productionService.findById(productionId).get();
        production.setName(productionName);
        production.setCountry(country);
        production.setAddress(address);
        return "redirect:/productions";
    }
}
