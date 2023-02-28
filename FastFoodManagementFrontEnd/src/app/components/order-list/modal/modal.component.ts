import {Component, OnInit} from '@angular/core';
import {Order} from "../../../models/Order/Order";

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css']
})

export class ModalComponent implements OnInit {

  order: Order | null = null;

  constructor() {

  }

  ngOnInit() {
  }

}
