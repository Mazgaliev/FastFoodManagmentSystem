import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Item} from "../../../../models/Item/Item";
import {ItemType} from "../../../../models/Item/ItemType";
import {OrderItem} from "../../../../models/Order/OrderItem";
import {Money} from "../../../../models/Val_objects/Money";

@Component({
  selector: 'app-order-item',
  templateUrl: './order-item.component.html',
  styleUrls: ['./order-item.component.css']
})
export class OrderItemComponent implements OnInit {

  @Input() item: OrderItem = {
    specificId: "",
    name: "",
    id: 1,
    price: {currency: "MKD", amount: 0},
    type: ItemType.FOOD
  };
  readonly itemType: ItemType = ItemType.ADDITIVE;
  @Output() itemAddAmountEmitter: EventEmitter<number> = new EventEmitter<number>();
  @Output() itemRemoveAmountEmitter: EventEmitter<{ item: OrderItem, count: number }> = new EventEmitter<{ item: OrderItem, count: number }>();
  @Output() itemRemoveAllAmountEmitter: EventEmitter<OrderItem> = new EventEmitter<OrderItem>();
  count: number = 1;
  specialId: string = "";

  constructor() {
  }

  ngOnInit(): void {
  }

  removeItem(amount: number) {
    this.count += 1;
    this.itemAddAmountEmitter.emit(amount);
  }

  addItem(item: OrderItem, count: number) {
    this.count -= 1;
    this.itemRemoveAmountEmitter.emit({item: item, count: count - 1});
  }

  removeAllOfItem(item: OrderItem) {

    let mony: Money = {
      amount: item.price.amount * this.count,
      currency: item.price.currency
    }

    let send: OrderItem = {
      specificId: item.specificId,
      id: item.id,
      price: mony,
      type: item.type,
      name: item.name
    }
    this.itemRemoveAllAmountEmitter.emit(send);
  }

}
