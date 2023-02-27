import {Component, OnInit} from '@angular/core';
import {Store} from "@ngrx/store";
import {Observable, tap} from "rxjs";
import {AppActions, Selectors} from "../../store";
import {OrderState} from "../../models/Order/OrderState";
import {FastFoodShop} from "../../models/FastFoodShop/FastFoodShop";
import {OrderItem} from "../../models/Order/OrderItem";
import {CreateOrder} from "../../models/Order/CreateOrder";
import {Item} from "../../models/Item/Item";
import {AppState} from "../../store/state";

@Component({
  selector: 'app-work-desk',
  templateUrl: './work-desk.component.html',
  styleUrls: ['./work-desk.component.css']
})
export class WorkDeskComponent implements OnInit {

  $orderState: Observable<OrderState> = this.store.select(Selectors.selectOrder);
  $fastFoodShop: Observable<FastFoodShop> = this.store.select(Selectors.selectShop);
  $foods: Observable<Item[]> = this.store.select(Selectors.selectFoods);
  $additives: Observable<Item[]> = this.store.select(Selectors.selectAdditives);
  $drinks: Observable<Item[]> = this.store.select(Selectors.selectDrinks);

  constructor(private readonly store: Store<AppState>) {


  }

  ngOnInit(): void {
  }


  addItemToOrder(item: OrderItem) {
    this.store.dispatch(AppActions.addToOrder({item: item}))
  }

  removeItemFromOrder(item: OrderItem) {
    this.store.dispatch(AppActions.removeItemFromOrder({item: item}))
  }

  clearAllItemsWithId() {

  }

  clearAllItemsInOrder(item: OrderItem) {
  this.store.dispatch(AppActions.removeItemFromOrder({item:item}))
  }

  lowerItemCount(event: { item: OrderItem, count: number, }) {
    if (event.count != 0) {
      this.store.dispatch(AppActions.reduceItemCount({amount: event.item.price.amount}));
    } else {

      this.store.dispatch(AppActions.removeItemFromOrder({item: event.item}))
    }
  }

  addItemCount(amount: number) {

    this.store.dispatch(AppActions.addItemCount({amount: amount}));
  }

  makeOrder(order: OrderState) {
    const ids = order.items.map(item => item.id);
    let shopId = '';
    this.$fastFoodShop.subscribe(data => {
      shopId = data.id.id
    })
    const createOrder: CreateOrder = {
      shopId: {
        id: shopId
      },
      itemIds: ids,
      amount: order.total.amount,
      currency: order.total.currency,
      workerUsername: order.worker.username,

    }
    this.store.dispatch(AppActions.saveOrder({order: createOrder}))
  }
}
