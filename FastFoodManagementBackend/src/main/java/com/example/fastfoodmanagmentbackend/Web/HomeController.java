package com.example.fastfoodmanagmentbackend.Web;


import com.example.fastfoodmanagmentbackend.Service.FastFoodShopService;
import com.example.fastfoodmanagmentbackend.Service.dto.SucessLoginDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/api", "/api/home", "/"})
public class HomeController {

    private final FastFoodShopService fastFoodShopService;

    public HomeController(FastFoodShopService fastFoodShopService) {
        this.fastFoodShopService = fastFoodShopService;
    }

    @GetMapping
    public SucessLoginDto homePage() {

        return null;
    }


}
