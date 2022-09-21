import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Item} from "../../../models/Item/Item";
import {ItemType} from "../../../models/Item/ItemType";
import {Store} from "@ngrx/store";
import {AppActions} from "../../../store";

@Component({
  selector: 'app-item-list',
  templateUrl: './item-list.component.html',
  styleUrls: ['./item-list.component.css']
})
export class ItemListComponent implements OnInit {

  @Input() foods: Item[] = [{name: "Borger", price: {currency: "MKD", amount: 150}, id: 1, type: ItemType.FOOD}];
  @Input() drinks: Item[] = [];
  @Input() additives: Item[] = [];
  @Output() addItemToOrderEmitter: EventEmitter<Item> = new EventEmitter<Item>();

  showFood: boolean = true;
  showDrink: boolean = false;

  constructor(private readonly store: Store) {
  }

  ngOnInit(): void {
  }

  viewFood() {
    this.showFood = true;
    this.showDrink = false;
  }

  addToOrder(item: Item): void {
    this.addItemToOrderEmitter.emit(item);
  }

  viewDrink() {
    this.showFood = false;
    this.showDrink = true;
  }

}
