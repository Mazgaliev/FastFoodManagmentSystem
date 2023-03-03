import {Component, OnInit} from '@angular/core';
import {Store} from "@ngrx/store";
import {AppActions, Selectors} from "../../store";
import {Observable, tap} from "rxjs";
import {Item} from "../../models/Item/Item";
import {FastFoodShopId} from "../../models/FastFoodShop/FastFoodShopId";
import {DeleteItem} from "../../models/Item/DeleteItem";

@Component({
  selector: 'app-item-management',
  templateUrl: './item-management.component.html',
  styleUrls: ['./item-management.component.css']
})
export class ItemManagementComponent implements OnInit {

  $foods: Observable<Item[]> = this.store.select(Selectors.selectFoods);
  $additives: Observable<Item[]> = this.store.select(Selectors.selectAdditives);
  $drinks: Observable<Item[]> = this.store.select(Selectors.selectDrinks);

  constructor(private readonly store: Store) {
  }

  ngOnInit(): void {
  }


  deleteItem(item: any): void {
    let id: FastFoodShopId = {id: ""}
    this.store.select(Selectors.selectShopId).pipe(tap(shopId => {
      id = shopId
    }))

    const formData: DeleteItem = {
      shopId: id,
      itemId: item
    }

    this.store.dispatch(AppActions.deleteItem({item: formData}))
    // obs.unsubscribe()
  }
}
