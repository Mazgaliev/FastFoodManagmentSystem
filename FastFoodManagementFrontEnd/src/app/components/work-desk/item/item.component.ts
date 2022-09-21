import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Item} from "../../../models/Item/Item";
import {ItemType} from "../../../models/Item/ItemType";

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.css']
})
export class ItemComponent implements OnInit {

  @Input() item: Item = {name: "", id: 1, price: {currency: "MKD", amount: 0}, type: ItemType.FOOD};
  @Output() itemEmitter: EventEmitter<Item> = new EventEmitter<Item>();

  constructor() {
  }

  ngOnInit(): void {
  }


  emitItem(): void {
    this.itemEmitter.emit(this.item);
  }


}
