import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {PersonRole} from "../../models/Person/PersonRole";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {


  @Input() shopName: string = "";
  @Input() loggedIn: boolean = false;
  @Input() role: PersonRole = PersonRole.EMPTY;
  @Output() logoutEmitter: EventEmitter<Boolean> = new EventEmitter<Boolean>();
  isOwner: boolean = false;

  constructor() {
  }

  ngOnInit(): void {
    this.isOwner =this.role.toString() == PersonRole[PersonRole.OWNER]
  }

  logout() {

    this.logoutEmitter.emit(true);
  }
}
