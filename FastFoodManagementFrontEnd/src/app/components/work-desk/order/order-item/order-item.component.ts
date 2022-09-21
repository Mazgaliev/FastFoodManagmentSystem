import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Item} from "../../../../models/Item/Item";
import {ItemType} from "../../../../models/Item/ItemType";

@Component({
  selector: 'app-order-item',
  templateUrl: './order-item.component.html',
  styleUrls: ['./order-item.component.css']
})
export class OrderItemComponent implements OnInit {

  @Input() item: Item = {name: "", id: 1, price: {currency: "MKD", amount: 0}, type: ItemType.FOOD};
  readonly itemType: ItemType = ItemType.ADDITIVE;
  @Output() itemAddAmountEmitter: EventEmitter<number> = new EventEmitter<number>();
  @Output() itemRemoveAmountEmitter: EventEmitter<{ amount: number, id: number, count: number }> = new EventEmitter<{ amount: number, id: number, count: number }>();
  @Output() itemRemoveAllAmountEmitter: EventEmitter<Item> = new EventEmitter<Item>();
  count: number = 1;

  constructor() {
  }

  ngOnInit(): void {
  }

  removeItem(amount: number) {
    this.count += 1;
    this.itemAddAmountEmitter.emit(amount);
  }

  addItem(amount: number, id: number, count: number) {
    this.count -= 1;
    this.itemRemoveAmountEmitter.emit({amount: amount, id: id, count: count - 1});
  }

  removeAllOfItem(item: Item) {
    this.itemRemoveAllAmountEmitter.emit(item);
  }

}
