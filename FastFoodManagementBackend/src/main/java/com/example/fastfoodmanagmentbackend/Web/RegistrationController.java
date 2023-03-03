package com.example.fastfoodmanagmentbackend.Web;


import com.example.fastfoodmanagmentbackend.Model.FastFoodShop;
import com.example.fastfoodmanagmentbackend.Model.Person;
import com.example.fastfoodmanagmentbackend.Service.FastFoodShopService;
import com.example.fastfoodmanagmentbackend.Service.converter.Converter;
import com.example.fastfoodmanagmentbackend.Service.dto.FastFoodShopDto;
import com.example.fastfoodmanagmentbackend.Service.forms.FastFoodShopForm;
import com.example.fastfoodmanagmentbackend.Service.forms.WorkerForm;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register")
@CrossOrigin(origins = "http://localhost:4200")
public class RegistrationController {

    private final FastFoodShopService fastFoodShopService;
    private final Converter converter;

    public RegistrationController(FastFoodShopService fastFoodShopService, Converter converter) {
        this.fastFoodShopService = fastFoodShopService;
        this.converter = converter;
    }


    @PostMapping
    public boolean registerShop(@RequestBody FastFoodShopForm form) {
        FastFoodShop shop = this.fastFoodShopService.createShop(form);
        return shop != null;
    }

    @PostMapping("/user")
    public void registerUser(@RequestBody WorkerForm form) {
        this.fastFoodShopService.createShopWorker(form.getUsername(), form.getPassword(), form.getRole(), form.getFastFoodShopId());

    }

}
