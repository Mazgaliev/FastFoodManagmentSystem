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
import {StoreModule} from '@ngrx/store';
import {StoreDevtoolsModule} from '@ngrx/store-devtools';

import {reducer} from "./store/reducer";
import { environment } from '../environments/environment';

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
    StoreModule.forRoot({}, {}),
    StoreModule.forFeature('AppState', reducer),
    StoreDevtoolsModule.instrument({
      maxAge: 10
    }),
    StoreDevtoolsModule.instrument({ maxAge: 25, logOnly: environment.production })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {

}
