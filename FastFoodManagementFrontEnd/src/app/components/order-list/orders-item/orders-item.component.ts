import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Order} from "../../../models/Order/Order";

@Component({
  selector: 'app-orders-item',
  templateUrl: './orders-item.component.html',
  styleUrls: ['./orders-item.component.css']
})
export class OrdersItemComponent implements OnInit {


  @Input() item!: Order;
  @Output() removeOrderEmitter: EventEmitter<number> = new EventEmitter<number>();

  constructor() {
  }

  ngOnInit(): void {
  }

  edit() {

  }

  remove() {
    this.removeOrderEmitter.emit(this.item.id)
  }

}
