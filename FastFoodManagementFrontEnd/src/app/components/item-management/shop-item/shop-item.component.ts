import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Item} from "../../../models/Item/Item";
import {ItemType} from "../../../models/Item/ItemType";

@Component({
  selector: 'app-shop-item',
  templateUrl: './shop-item.component.html',
  styleUrls: ['./shop-item.component.css']
})
export class ShopItemComponent implements OnInit {

  @Input() item: Item = {id: 999, name: "", price: {amount: 1, currency: "MKD"}, type: ItemType.FOOD};
  @Output() deleteItemEmitter: EventEmitter<number> = new EventEmitter<number>();


  constructor() {
  }

  ngOnInit(): void {


  }

  deleteItem() {

    this.deleteItemEmitter.emit(this.item.id);
  }
}
