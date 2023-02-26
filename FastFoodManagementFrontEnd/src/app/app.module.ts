import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {RouterOutlet} from "@angular/router";
import {ShopModuleComponent} from './components/shop-module/shop-module.component';
import {ShopModuleModule} from "./components/shop-module/shop-module.module";
import {CommonModule} from "@angular/common";
import {StoreModule} from "@ngrx/store";
import {StoreDevtoolsModule} from "@ngrx/store-devtools";
import {environment} from "../environments/environment";
import {CreateWorkerComponent} from './components/create-worker/create-worker.component';
import {ManageStaffComponent} from './components/manage-staff/manage-staff.component';

@NgModule({
  declarations: [
    AppComponent,
    ShopModuleComponent,
  ],
  imports: [
    CommonModule,
    BrowserModule,
    StoreModule.forRoot({}, {}),
    StoreDevtoolsModule.instrument({
      maxAge: 10
    }),
    StoreDevtoolsModule.instrument({maxAge: 25, logOnly: environment.production}),
    HttpClientModule,
    ShopModuleModule,
    RouterOutlet,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {

}
