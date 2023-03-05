package com.example.fastfoodmanagmentbackend.Web;


import com.example.fastfoodmanagmentbackend.Model.Item;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.FastFoodShopId;
import com.example.fastfoodmanagmentbackend.Service.FastFoodShopService;
import com.example.fastfoodmanagmentbackend.Service.forms.DeleteItemForm;
import com.example.fastfoodmanagmentbackend.Service.forms.EditItemForm;
import com.example.fastfoodmanagmentbackend.Service.forms.ItemForm;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = {"/api/items"})
@CrossOrigin(origins = "http://localhost:4200")
public class ItemController {

    private final FastFoodShopService fastFoodShopService;

    public ItemController(FastFoodShopService fastFoodShopService) {
        this.fastFoodShopService = fastFoodShopService;
    }


    @GetMapping
    @PreAuthorize("hasAuthority('OWNER')")
    public Set<Item> allItems(@RequestParam FastFoodShopId id) {
        return this.fastFoodShopService.findAllItems(id);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('OWNER')")
    public void addItem(@RequestBody ItemForm form) {

        this.fastFoodShopService.addItem(form.getName(), form.getPrice().getCurrency(), form.getPrice().getAmount(), form.getItemType(), form.getShopId());

    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('OWNER')")
    public void deleteItem(@RequestBody DeleteItemForm form) {

        this.fastFoodShopService.deleteItem(form.getItemId(), form.getShopId());
    }

    @PostMapping("/edit")
    @PreAuthorize("hasAuthority('OWNER')")
    public Item modifyItem(@RequestBody EditItemForm form) {
        return this.fastFoodShopService.editItem(form.getShopId(), form.getItemId(), form.getItemName(), form.getCurrency(), form.getAmount());

    }

}
