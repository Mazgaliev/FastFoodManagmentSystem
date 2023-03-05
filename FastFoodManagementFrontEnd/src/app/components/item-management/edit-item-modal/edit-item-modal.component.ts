import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {Store} from "@ngrx/store";
import {FastFoodShopId} from "../../../models/FastFoodShop/FastFoodShopId";
import {AppActions, Selectors} from "../../../store";
import {DeleteItem} from "../../../models/Item/DeleteItem";
import {EditItem} from "../../../models/Item/EditItem";
import {Money} from "../../../models/Val_objects/Money";
import {Item} from "../../../models/Item/Item";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-edit-item-modal',
  templateUrl: './edit-item-modal.component.html',
  styleUrls: ['./edit-item-modal.component.css']
})
export class EditItemModalComponent implements OnInit {

  item: Item | null = null;

  editItemForm = this.formBuilder.group({
    name: [this.item?.name, Validators.minLength(1)],
    amount: [this.item?.price.amount, Validators.required],
    type: [this.item?.type, Validators.required]
  })

  constructor(private formBuilder: FormBuilder, private readonly store: Store, public activeModal: NgbActiveModal) {
  }

  ngOnInit(): void {
  }

  editItem() {
    let id: FastFoodShopId = {id: ""}
    this.store.select(Selectors.selectShopId).subscribe(shopId => {
      id = shopId
    })

    const form = this.editItemForm.value;
    const itemPrice: Money = {
      amount: form.amount!,
      currency: this.item?.price.currency!
    }

    const formData: EditItem = {
      shopId: id,
      itemId: this.item?.id!,
      amount: form.amount ? form.amount : this.item?.price.amount!,
      currency: this.item?.price.currency!,
      itemName: form.name ? form.name : this.item?.name!,
      type: this.item?.type!
    }

    this.store.dispatch(AppActions.editItem({item: formData}))

    this.activeModal.close("Edited Item")
  }
}
