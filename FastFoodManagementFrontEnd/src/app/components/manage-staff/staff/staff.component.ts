import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Person} from "../../../models/Person/Person";
import {PersonRole} from "../../../models/Person/PersonRole";
import {WorkerId} from "../../../models/Person/WorkerId";

@Component({
  selector: 'app-staff',
  templateUrl: './staff.component.html',
  styleUrls: ['./staff.component.css']
})
export class StaffComponent implements OnInit {

  @Input() person: Person = {username: "", role: PersonRole.WORKER, id: {id: ""}};
  @Output() deletePersonEmitter: EventEmitter<WorkerId> = new EventEmitter<WorkerId>()

  constructor() {
  }

  ngOnInit(): void {
  }


  edit() {

  }

  delete() {

    this.deletePersonEmitter.emit(this.person.id);
  }
}
