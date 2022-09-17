package com.example.fastfoodmanagmentbackend.Web;


import com.example.fastfoodmanagmentbackend.Service.FastFoodShopService;
import com.example.fastfoodmanagmentbackend.Service.forms.WorkerForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
public class RegisterUserController {

    private final FastFoodShopService fastFoodShopService;

    public RegisterUserController(FastFoodShopService fastFoodShopService) {
        this.fastFoodShopService = fastFoodShopService;
    }


    @PostMapping
    public void registerUser(@RequestBody WorkerForm form) {
        this.fastFoodShopService.createShopWorker(form.getUsername(), form.getPassword(), form.getRole(), form.getFastFoodShopId());

    }

}
