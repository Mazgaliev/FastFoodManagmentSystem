import {Component, OnInit} from '@angular/core';
import {Store} from "@ngrx/store";
import {AppActions, Selectors} from "../../store";
import {FastFoodShopId} from "../../models/FastFoodShop/FastFoodShopId";
import {RemoveWorkerForm} from "../../models/Person/RemoveWorkerForm";

@Component({
  selector: 'app-manage-staff',
  templateUrl: './manage-staff.component.html',
  styleUrls: ['./manage-staff.component.css']
})
export class ManageStaffComponent implements OnInit {

  $workers = this.store.select(Selectors.selectWorkers);

  constructor(private readonly store: Store) {
  }

  ngOnInit(): void {
    let id: FastFoodShopId = {id: ""};
    this.store.select(Selectors.selectShopId).subscribe(d => {
      id = d;
    })

    this.store.dispatch(AppActions.fetchWorkers({id: id}))
  }


  deleteWorker(id: FastFoodShopId) {

    let form: RemoveWorkerForm = {
      shopId: {id: ""},
      workerId: id
    }
    this.store.select(Selectors.selectShopId).subscribe(id => {
      form.shopId = id
    })
    // console.log(form)
    this.store.dispatch(AppActions.removeWorker({deletionForm: form}))
  }

  editWorker() {

  }
}
