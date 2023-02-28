import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {PersonRole} from "../../models/Person/PersonRole";
import {Store} from "@ngrx/store";
import {AppActions, Selectors} from "../../store";
import {WorkerForm} from "../../models/Person/WorkerForm";
import {FastFoodShopId} from "../../models/FastFoodShop/FastFoodShopId";

@Component({
  selector: 'app-create-worker',
  templateUrl: './create-worker.component.html',
  styleUrls: ['./create-worker.component.css']
})
export class CreateWorkerComponent implements OnInit {

  workerForm = this.formBuilder.group({
    username: ['', Validators.minLength(1)],
    password: ['', Validators.required],
    role: PersonRole.EMPTY
  })

  constructor(private readonly formBuilder: FormBuilder, private readonly store: Store) {
  }

  ngOnInit(): void {
  }

  registerUser() {
    let formData = this.workerForm.value;
    let id: FastFoodShopId;
    this.store.select(Selectors.selectShopId).subscribe(event => {
      id = event
    })


    let form: WorkerForm = {
      fastFoodShopId: id!,
      username: formData.username!,
      password: formData.password!,
      role: PersonRole[formData.role!]
    }

    this.store.dispatch(AppActions.createWorker({worker: form}))
  }
}
