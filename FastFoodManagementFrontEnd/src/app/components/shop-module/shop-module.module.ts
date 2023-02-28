import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HeaderComponent} from "../header/header.component";
import {WorkDeskComponent} from "../work-desk/work-desk.component";
import {ItemListComponent} from "../work-desk/item-list/item-list.component";
import {OrderComponent} from "../work-desk/order/order.component";
import {ItemComponent} from "../work-desk/item/item.component";
import {OrderListComponent} from "../order-list/order-list.component";
import {RouterLink, RouterLinkWithHref, RouterModule, Routes} from "@angular/router";
import {LoginComponent} from "../login/login.component";
import {OrderItemComponent} from "../work-desk/order/order-item/order-item.component";
import {OrdersItemComponent} from "../order-list/orders-item/orders-item.component";
import {StoreModule} from "@ngrx/store";
import {reducer} from "../../store/reducer";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {EffectsModule} from "@ngrx/effects";
import {AppEffects} from "../../store/effects";
import {RegisterComponent} from "../register/register.component";
import {ManageStaffComponent} from "../manage-staff/manage-staff.component";
import {CreateWorkerComponent} from "../create-worker/create-worker.component";
import {StaffComponent} from "../manage-staff/staff/staff.component";
import {MatButtonModule} from "@angular/material/button";
import {MatDialogModule} from "@angular/material/dialog";
import {ModalComponent} from "../order-list/modal/modal.component";
import {MatFormFieldModule} from "@angular/material/form-field";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {CreateItemComponent} from "../create-item/create-item.component";

const routes: Routes = [
  {
    path: 'home', component: WorkDeskComponent, children: []
  },
  {path: 'item', component: CreateItemComponent},
  {path: 'manage', component: ManageStaffComponent},
  {path: 'create', component: CreateWorkerComponent},
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  {path: 'register', component: RegisterComponent},
  {path: 'orders', component: OrderListComponent},
  {path: 'login', component: LoginComponent}

]

@NgModule({
  declarations: [
    HeaderComponent,
    WorkDeskComponent,
    ItemListComponent,
    OrderItemComponent,
    OrdersItemComponent,
    ItemComponent,
    OrderListComponent,
    OrderComponent,
    LoginComponent,
    RegisterComponent,
    ManageStaffComponent,
    CreateWorkerComponent,
    StaffComponent,
    ModalComponent,
    CreateItemComponent
  ],
  exports: [
    HeaderComponent,
    ItemListComponent
  ],
  imports: [
    CommonModule,
    RouterLinkWithHref,
    RouterLink,
    RouterModule.forRoot(routes),
    StoreModule.forFeature('appState', reducer),
    EffectsModule.forRoot([AppEffects]),
    ReactiveFormsModule,
    MatButtonModule,
    MatDialogModule,
    MatFormFieldModule,
    FormsModule,
    NgbModule
  ]
})
export class ShopModuleModule {
}
