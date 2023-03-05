import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Item} from "../../../models/Item/Item";
import {ItemType} from "../../../models/Item/ItemType";
import {EditItem} from "../../../models/Item/EditItem";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {EditItemModalComponent} from "../edit-item-modal/edit-item-modal.component";

@Component({
  selector: 'app-shop-item',
  templateUrl: './shop-item.component.html',
  styleUrls: ['./shop-item.component.css']
})
export class ShopItemComponent implements OnInit {

  @Input() item: Item = {id: 999, name: "", price: {amount: 1, currency: "MKD"}, type: ItemType.FOOD};
  @Output() deleteItemEmitter: EventEmitter<number> = new EventEmitter<number>();
  @Output() editItemEmitter: EventEmitter<EditItem> = new EventEmitter<EditItem>();
  closeResult: any;

  constructor(private modalService: NgbModal) {
  }

  ngOnInit(): void {


  }

  deleteItem() {

    this.deleteItemEmitter.emit(this.item.id);
  }

  editItem() {
    const modal = this.modalService.open(EditItemModalComponent, {size: "lg"})

    modal.componentInstance.item = this.item
  }
}
