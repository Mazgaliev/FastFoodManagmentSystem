import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {CreateItem} from "../../models/Item/CreateItem";
import {ItemType} from "../../models/Item/ItemType";
import {Money} from "../../models/Val_objects/Money";
import {AppActions, Selectors} from "../../store";
import {FastFoodShopId} from "../../models/FastFoodShop/FastFoodShopId";
import {Store} from "@ngrx/store";

@Component({
  selector: 'app-create-item',
  templateUrl: './create-item.component.html',
  styleUrls: ['./create-item.component.css']
})
export class CreateItemComponent implements OnInit {

  itemForm = this.formBuilder.group({
    name: ['', Validators.minLength(1)],
    itemType: ItemType.ADDITIVE,
    amount: ['', Validators.required],
    currency: ['', Validators.required],
  })

  constructor(private formBuilder: FormBuilder, private readonly store: Store) {
  }

  ngOnInit(): void {
  }

  createItem() {
    let id: FastFoodShopId = {id: ""};

    this.store.select(Selectors.selectShopId).subscribe(event => {
      id = event
    })

    const formData = this.itemForm.value;

    const amount: Money = {
      amount: parseInt(formData.amount!),
      currency: formData.currency!,
    };

    let itemForm: CreateItem = {
      name: formData.name!,
      shopId: id,
      itemType: formData.itemType!,
      price: amount
    }

    this.store.dispatch(AppActions.createItem({item: itemForm}));

  }
}
