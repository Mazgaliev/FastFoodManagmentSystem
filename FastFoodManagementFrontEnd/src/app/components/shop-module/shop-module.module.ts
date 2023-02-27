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
import {ReactiveFormsModule} from "@angular/forms";
import {EffectsModule} from "@ngrx/effects";
import {AppEffects} from "../../store/effects";
import {RegisterComponent} from "../register/register.component";
import {ManageStaffComponent} from "../manage-staff/manage-staff.component";
import {CreateWorkerComponent} from "../create-worker/create-worker.component";
import {StaffComponent} from "../manage-staff/staff/staff.component";

const routes: Routes = [
  {
    path: 'home', component: WorkDeskComponent, children: []
  },
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
    StaffComponent
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
  ]
})
export class ShopModuleModule {
}
