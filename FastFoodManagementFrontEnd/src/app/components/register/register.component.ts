import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {Store} from "@ngrx/store";
import {AppActions} from "../../store";
import {CreateFastFoodShop} from "../../models/FastFoodShop/CreateFastFoodShop";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registrationForm = this.formBuilder.group({
    name: ["", Validators.required],
    longitude: ["", Validators.required],
    latitude: ["", Validators.required],
    city: ["", Validators.required],
    ownerName: ["", Validators.required],
    ownerSurname: ["", Validators.required],
    e_mail: ["", Validators.required],
    ownerOperator: ["", Validators.required],
    ownerPhone: ["", Validators.minLength(9)]
  },)

  constructor(private formBuilder: FormBuilder, private readonly store: Store) {
  }

  ngOnInit(): void {
  }


  register() {
    // console.log(this.registrationForm)
    const vals = this.registrationForm.value;
    let shop: CreateFastFoodShop = {
      name: vals.name!,
      longitude: vals.longitude!,
      latitude: vals.latitude!,
      city: vals.city!,
      e_mail: vals.e_mail!,
      ownerName: vals.ownerName!,
      ownerOperator: vals.ownerOperator!,
      ownerPhone: vals.ownerPhone!,
      ownerSurname: vals.ownerSurname!
    };
    this.store.dispatch(AppActions.registerStore({shop: shop}))
  }

}
