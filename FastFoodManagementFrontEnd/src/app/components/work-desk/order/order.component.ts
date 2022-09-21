import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Item} from "../../../models/Item/Item";
import {ItemType} from "../../../models/Item/ItemType";
import {Store} from "@ngrx/store";
import {Observable} from "rxjs";
import {Selectors} from "../../../store";
import {selectOrderItems} from "../../../store/selectors";
import {Money} from "../../../models/Val_objects/Money";
import {Order} from "../../../models/Order/Order";
import {OrderState} from "../../../models/Order/OrderState";

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  @Input() order!: OrderState | null;
  total: number = 0;
  @Output() removeItemEmitter: EventEmitter<{ amount: number, id: number, count: number }> = new EventEmitter<{ amount: number; id: number; count: number }>()
  @Output() addItemEmitter: EventEmitter<number> = new EventEmitter<number>();
  @Output() clearAllItemsWithIdEmitter: EventEmitter<Item> = new EventEmitter<Item>()

  constructor() {
  }

  ngOnInit(): void {
    console.log(this.order);
  }

  addItem(event: number) {
    this.total += event;
    this.addItemEmitter.emit(event);
  }

  removeItem(event: { amount: number, id: number, count: number }) {
    // console.log(event.count)
    // if (event.count == 0) {
    //   for (let i = 0; i < this.items.length; i++) {
    //     if (this.items[i].id == event.id) {
    //       this.items.splice(i, 1);
    //       this.total -= event.amount;
    //     }
    //   }
    // } else {
    //   this.total -= event.amount;
    // }
    this.removeItemEmitter.emit(event);
  }

  removeAllOfItemId(event: Item) {
    // console.log(event);
    // this.total -= event.amount;
    // for (let i = 0; i < this.items.length; i++) {
    //   if (this.items[i].id == event.id) {
    //     this.items.splice(i, 1);
    //   }
    // }
    this.clearAllItemsWithIdEmitter.emit(event);
  }
}
