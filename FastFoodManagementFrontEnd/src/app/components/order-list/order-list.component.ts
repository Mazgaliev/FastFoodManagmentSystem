import {Component, OnInit} from '@angular/core';
import {Store} from "@ngrx/store";
import {AppActions, Selectors} from "../../store";
import {FastFoodShopId} from "../../models/FastFoodShop/FastFoodShopId";
import {DeleteOrder} from "../../models/Order/DeleteOrder";

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent implements OnInit {
  $orders = this.store.select(Selectors.selectOrders);

  constructor(private readonly store: Store) {
  }

  ngOnInit(): void {

    this.store.select(Selectors.selectShopId).subscribe(
      shopId => {
        this.store.dispatch(AppActions.getOrders({shopId: shopId}))
      }
    )
  }

  removeOrder(orderId: number) {
    let sId: FastFoodShopId = {id: ""};
    this.store.select(Selectors.selectShopId).subscribe(
      shopId => {
        sId = shopId
      }
    )
    const deleteOrderForm: DeleteOrder = {
      orderId: orderId,
      shopId: sId
    }
    this.store.dispatch(AppActions.removeOrder({order: deleteOrderForm}))
  }
}

