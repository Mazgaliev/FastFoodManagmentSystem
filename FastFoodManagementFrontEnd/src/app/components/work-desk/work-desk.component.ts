import {Component, OnInit} from '@angular/core';
import {Store} from "@ngrx/store";
import {Observable} from "rxjs";
import {AppActions, Selectors} from "../../store";
import {Item} from "../../models/Item/Item";
import {OrderState} from "../../models/Order/OrderState";
import {FastFoodShop} from "../../models/FastFoodShop/FastFoodShop";

@Component({
  selector: 'app-work-desk',
  templateUrl: './work-desk.component.html',
  styleUrls: ['./work-desk.component.css']
})
export class WorkDeskComponent implements OnInit {

  $orderState: Observable<OrderState> = this.store.select(Selectors.selectOrder);
  $fastFoodShop: Observable<FastFoodShop> = this.store.select(Selectors.selectShop);
  itemList: Item[] = [];

  constructor(private readonly store: Store) {
  }

  ngOnInit(): void {
  }

  addItemToOrder(item: Item) {
    this.store.dispatch(AppActions.addToOrder({item: item}))
  }

  removeItemFromOrder(item: Item) {
    this.store.dispatch(AppActions.removeItemFromOrder({item: item}))
  }

  clearAllItemsWithId() {

  }

  clearAllItemsInOrder() {

  }

  lowerItemCount(event: { amount: number, count: number, id: number }) {
    if (event.count != 0) {
      this.store.dispatch(AppActions.reduceItemCount({amount: event.amount}));
    } else {
      this.$fastFoodShop.subscribe(data => {
        for (let i = 0; i < data.foods.length; i++) {
          this.itemList.push(data.foods[i]);
        }
        for (let i = 0; i < data.drinks.length; i++) {
          this.itemList.push(data.drinks[i]);
        }
        for (let i = 0; i < data.additives.length; i++) {
          this.itemList.push(data.foods[i]);
        }
        console.log(data);
      })
      var item = this.itemList.filter(i => i.id === event.id)[0];
      console.log(item);
      this.store.dispatch(AppActions.removeItemFromOrder({item}));
    }
  }

  addItemCount(amount: number) {

    this.store.dispatch(AppActions.addItemCount({amount: amount}));
  }

}
