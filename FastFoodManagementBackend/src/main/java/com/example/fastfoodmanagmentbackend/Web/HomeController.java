package com.example.fastfoodmanagmentbackend.Web;


import com.example.fastfoodmanagmentbackend.Model.ValueObjects.FastFoodShopId;
import com.example.fastfoodmanagmentbackend.Service.FastFoodShopService;
import com.example.fastfoodmanagmentbackend.Service.converter.Converter;
import com.example.fastfoodmanagmentbackend.Service.dto.FastFoodShopDto;
import com.example.fastfoodmanagmentbackend.Service.dto.ShopItemsDto;
import com.example.fastfoodmanagmentbackend.Service.dto.WorkerDto;
import com.example.fastfoodmanagmentbackend.Service.forms.DeleteWorkerForm;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = {"/api", "/api/home", "/"})
public class HomeController {

    private final FastFoodShopService fastFoodShopService;
    private final Converter converter;

    public HomeController(FastFoodShopService fastFoodShopService, Converter converter) {
        this.fastFoodShopService = fastFoodShopService;
        this.converter = converter;
    }

    @GetMapping

    public FastFoodShopDto homePage(Authentication authentication) {

        FastFoodShopDto dto = (FastFoodShopDto) authentication.getPrincipal();

        return dto;
    }

    @GetMapping("/refreshItems")
    public ShopItemsDto refreshPage(@RequestParam FastFoodShopId shopId) {

        return this.converter.convertToDto(this.fastFoodShopService.findById(shopId));
    }

    @PostMapping("/remove")
    public WorkerDto removeWorker(@RequestBody DeleteWorkerForm form) {
        this.fastFoodShopService.deleteShopWorker(form.getWorkerId(), form.getShopId());

        return null;
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('OWNER')")
    public boolean deleteShop(@RequestParam FastFoodShopId shopId) {


        return this.fastFoodShopService.deleteShop(shopId);
    }


}
