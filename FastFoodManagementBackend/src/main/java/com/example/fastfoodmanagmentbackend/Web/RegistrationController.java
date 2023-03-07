package com.example.fastfoodmanagmentbackend.Web;


import com.example.fastfoodmanagmentbackend.Model.FastFoodShop;
import com.example.fastfoodmanagmentbackend.Service.FastFoodShopService;
import com.example.fastfoodmanagmentbackend.Service.MailService;
import com.example.fastfoodmanagmentbackend.Service.converter.Converter;
import com.example.fastfoodmanagmentbackend.Service.dto.RegisterMailDto;
import com.example.fastfoodmanagmentbackend.Service.forms.FastFoodShopForm;
import com.example.fastfoodmanagmentbackend.Service.forms.WorkerForm;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register")
@CrossOrigin(origins = "http://localhost:4200")
public class RegistrationController {

    private final FastFoodShopService fastFoodShopService;
    private final Converter converter;

    private final MailService mailService;


    public RegistrationController(FastFoodShopService fastFoodShopService, Converter converter, MailService mailService) {
        this.fastFoodShopService = fastFoodShopService;
        this.converter = converter;
        this.mailService = mailService;
    }


    @PostMapping
    public boolean registerShop(@RequestBody FastFoodShopForm form) {
        RegisterMailDto shop = this.fastFoodShopService.createShop(form);


        if (shop != null) {
            String body = "Your shop login ID is: " + shop.getShopId().getId() + "\n"
                    + "Your username is: " + shop.getUsername() + "\n"
                    + "Your password is: " + shop.getPassword();
            this.mailService.sendEmail(shop.getEmail(),
                    "IMPORTANT REGISTRATION INFORMATION",
                    body
            );
            return true;
        }


        return false;
    }

    @PostMapping("/user")
    public void registerUser(@RequestBody WorkerForm form) {
        FastFoodShop shop = this.fastFoodShopService.createShopWorker(form.getUsername(), form.getPassword(), form.getRole(), form.getFastFoodShopId());
        this.mailService.sendEmail(
                shop.getOwner().getE_mail(),
                "Worker creation status",
                "The user has been successfully created \n"
                        + "Username: " + form.getUsername() + "\n"
                        + "Password: " + form.getPassword() + "\n"
                        + "Role: " + form.getRole()
        );


    }

}
