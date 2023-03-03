import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {RouterOutlet} from "@angular/router";
import {ShopModuleComponent} from './components/shop-module/shop-module.component';
import {ShopModuleModule} from "./components/shop-module/shop-module.module";
import {CommonModule} from "@angular/common";
import {StoreModule} from "@ngrx/store";
import {StoreDevtoolsModule} from "@ngrx/store-devtools";
import {environment} from "../environments/environment";
import {EditItemModalComponent} from './components/item-management/edit-item-modal/edit-item-modal.component';
import {InterceptorService} from "./interceptor/interceptor.service";

@NgModule({
  declarations: [
    AppComponent,
    ShopModuleComponent,
    EditItemModalComponent,

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
  providers: [{provide: HTTP_INTERCEPTORS, useClass: InterceptorService, multi: true}],
  exports: [],
  bootstrap: [AppComponent]
})
export class AppModule {

}
