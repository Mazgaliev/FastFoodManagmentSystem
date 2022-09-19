import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {HeaderComponent} from './components/header/header.component';
import {WorkDeskComponent} from './components/work-desk/work-desk.component';
import {ItemListComponent} from './components/work-desk/item-list/item-list.component';
import {OrderComponent} from './components/work-desk/order/order.component';
import {ItemComponent} from './components/work-desk/item/item.component';
import {RouterModule, Routes} from "@angular/router";

const routes: Routes = [
  {
    path: 'home', component: WorkDeskComponent, children: []
  },
  {path: '', redirectTo: '/home', pathMatch: 'full'},

]

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    WorkDeskComponent,
    ItemListComponent,
    OrderComponent,
    ItemComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {

}
