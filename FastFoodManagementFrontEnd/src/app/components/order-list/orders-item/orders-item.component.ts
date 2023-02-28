import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Order} from "../../../models/Order/Order";
import {NgbModal, ModalDismissReasons} from "@ng-bootstrap/ng-bootstrap";
import {ModalComponent} from "../modal/modal.component";

@Component({
  selector: 'app-orders-item',
  templateUrl: './orders-item.component.html',
  styleUrls: ['./orders-item.component.css']
})
export class OrdersItemComponent implements OnInit {


  @Input() item!: Order;
  @Output() removeOrderEmitter: EventEmitter<number> = new EventEmitter<number>();
  closeResult: string = "";

  constructor(private modalService: NgbModal) {
  }

  ngOnInit(): void {
  }

  edit() {

  }

  remove() {
    this.removeOrderEmitter.emit(this.item.id)
  }

  openModal() {
    const modal = this.modalService.open(ModalComponent, {size: "lg", animation: true, centered: true})
    modal.componentInstance.order = this.item;
  }

}
