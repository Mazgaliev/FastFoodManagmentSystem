package com.example.fastfoodmanagmentbackend.Web;


import com.example.fastfoodmanagmentbackend.Model.FastFoodShop;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.FastFoodShopId;
import com.example.fastfoodmanagmentbackend.Service.FastFoodShopService;
import com.example.fastfoodmanagmentbackend.Service.MailService;
import com.example.fastfoodmanagmentbackend.Service.converter.Converter;
import com.example.fastfoodmanagmentbackend.Service.dto.FastFoodShopDto;
import com.example.fastfoodmanagmentbackend.Service.dto.ShopItemsDto;
import com.example.fastfoodmanagmentbackend.Service.dto.WorkerDto;
import com.example.fastfoodmanagmentbackend.Service.forms.DeleteWorkerForm;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = {"/api", "/api/home", "/"})
@CrossOrigin(origins = "http://localhost:4200")
public class HomeController {

    private final FastFoodShopService fastFoodShopService;
    private final Converter converter;

    private final MailService mailService;

    public HomeController(FastFoodShopService fastFoodShopService, Converter converter, MailService mailService) {
        this.fastFoodShopService = fastFoodShopService;
        this.converter = converter;
        this.mailService = mailService;
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
    @PreAuthorize("hasAuthority('OWNER')")
    public boolean removeWorker(@RequestBody DeleteWorkerForm form) {
        FastFoodShop shop = this.fastFoodShopService.deleteShopWorker(form.getWorkerId(), form.getShopId());
        if (shop != null) {
            this.mailService.sendEmail(
                    shop.getOwner().getE_mail(),
                    "DELETION INFO",
                    "Worker with ID: " + form.getWorkerId().getId() + "\n Has been successfully deleted."
            );
            return true;
        }
        return false;
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('OWNER')")
    public boolean deleteShop(@RequestParam FastFoodShopId shopId) {

        FastFoodShop shop = this.fastFoodShopService.deleteShop(shopId);

        if (shop != null) {
            this.mailService.sendEmail(
                    shop.getOwner().getE_mail(),
                    "DELETION INFORMATION",
                    "The shop with id" + shop.getId().getId() + "has been successfully deleted"
            );
        }

        return this.fastFoodShopService.deleteShop(shopId) != null;
    }

    @GetMapping("/workers/{id}")
    public Set<WorkerDto> getShopWorkers(@PathVariable FastFoodShopId id) {
        Set<WorkerDto> workers = converter.convertToWorkerDto(this.fastFoodShopService.findAllWorkers(id));

        return workers;
    }


}
