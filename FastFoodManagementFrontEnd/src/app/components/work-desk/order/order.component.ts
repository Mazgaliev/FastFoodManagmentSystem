import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {OrderState} from "../../../models/Order/OrderState";
import {OrderItem} from "../../../models/Order/OrderItem";

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  @Input() order!: OrderState;
  total: number = 0;
  @Output() removeItemEmitter: EventEmitter<{ item: OrderItem, count: number }> = new EventEmitter<{ item: OrderItem, count: number }>()
  @Output() addItemEmitter: EventEmitter<number> = new EventEmitter<number>();
  @Output() clearAllItemsWithIdEmitter: EventEmitter<OrderItem> = new EventEmitter<OrderItem>()
  @Output() saveOrderEmitter: EventEmitter<OrderState> = new EventEmitter<OrderState>();

  constructor() {
  }

  ngOnInit(): void {
  }

  addItem(event: number) {
    this.total += event;
    this.addItemEmitter.emit(event);
  }

  removeItem(event: { item: OrderItem, count: number }) {

    this.removeItemEmitter.emit(event);
  }

  removeAllOfItemId(event: OrderItem) {
    // console.log(event);
    // this.total -= event.amount;
    // for (let i = 0; i < this.items.length; i++) {
    //   if (this.items[i].id == event.id) {
    //     this.items.splice(i, 1);
    //   }
    // }

    this.clearAllItemsWithIdEmitter.emit(event);
  }

  makeOrder(order: OrderState) {


    this.saveOrderEmitter.emit(this.order);
  }
}
