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
import {StoreDevtoolsModule} from "@ngrx/store-devtools";
import {environment} from "../../../environments/environment";

const routes: Routes = [
  {
    path: 'home', component: WorkDeskComponent, children: []
  },
  {path: '', redirectTo: '/home', pathMatch: 'full'},
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
  ],
  exports: [
    HeaderComponent
  ],
  imports: [
    CommonModule,
    RouterLinkWithHref,
    RouterLink,
    RouterModule.forRoot(routes),
    StoreModule.forRoot({}, {}),
    StoreModule.forFeature('appState', reducer),
    StoreDevtoolsModule.instrument({
      maxAge: 10
    }),
    StoreDevtoolsModule.instrument({maxAge: 25, logOnly: environment.production}),
  ]
})
export class ShopModuleModule {
}
