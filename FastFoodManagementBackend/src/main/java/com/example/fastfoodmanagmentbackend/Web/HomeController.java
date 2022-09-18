package com.example.fastfoodmanagmentbackend.Web;


import com.example.fastfoodmanagmentbackend.Model.ValueObjects.WorkerId;
import com.example.fastfoodmanagmentbackend.Service.FastFoodShopService;
import com.example.fastfoodmanagmentbackend.Service.dto.FastFoodShopDto;
import com.example.fastfoodmanagmentbackend.Service.dto.WorkerDto;
import com.example.fastfoodmanagmentbackend.Service.forms.DeleteWorkerForm;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = {"/api", "/api/home", "/"})
public class HomeController {

    private final FastFoodShopService fastFoodShopService;

    public HomeController(FastFoodShopService fastFoodShopService) {
        this.fastFoodShopService = fastFoodShopService;
    }

    @GetMapping
    public FastFoodShopDto homePage(Authentication authentication) {
        FastFoodShopDto dto = (FastFoodShopDto) authentication.getPrincipal();

        return dto;
    }

    @PostMapping("/remove")
    public WorkerDto removeWorker(@RequestBody DeleteWorkerForm form) {
        this.fastFoodShopService.deleteShopWorker(form.getWorkerId(), form.getShopId());

        return null;
    }


}
