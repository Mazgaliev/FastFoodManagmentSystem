import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Item} from "../../../models/Item/Item";
import {OrderItem} from "../../../models/Order/OrderItem";
import {Store} from "@ngrx/store";
import {Selectors} from "../../../store";

@Component({
  selector: 'app-item-list',
  templateUrl: './item-list.component.html',
  styleUrls: ['./item-list.component.css']
})
export class ItemListComponent implements OnInit {
  characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';

  @Input() foods: Item[] = [];
  @Input() drinks: Item[] = [];
  @Input() additives: Item[] = [];
  @Output() addItemToOrderEmitter: EventEmitter<OrderItem> = new EventEmitter<OrderItem>();

  showFood: boolean = true;
  showDrink: boolean = false;

  constructor(private readonly store:Store) {
  }

  ngOnInit(): void {

  }

  viewFood() {
    this.showFood = true;
    this.showDrink = false;
  }

  addToOrder(item: Item): void {
    let specialId: string = '';

    for (let i = 0; i < 10; i++) {
      specialId += this.characters.charAt(Math.floor(Math.random() * this.characters.length));
    }
    const orderItem: OrderItem = {
      specificId: specialId,
      type: item.type,
      name: item.name,
      id: item.id,
      price: item.price
    }
    this.addItemToOrderEmitter.emit(orderItem);
  }

  viewDrink() {
    this.showFood = false;
    this.showDrink = true;
  }
}
