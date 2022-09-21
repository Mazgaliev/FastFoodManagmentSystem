import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {RouterOutlet} from "@angular/router";
import {StoreModule} from '@ngrx/store';
import {StoreDevtoolsModule} from '@ngrx/store-devtools';

import {reducer} from "./store/reducer";
import {environment} from '../environments/environment';
import {ShopModuleComponent} from './components/shop-module/shop-module.component';
import {ShopModuleModule} from "./components/shop-module/shop-module.module";
import {CommonModule} from "@angular/common";

@NgModule({
  declarations: [
    AppComponent,
    ShopModuleComponent,
  ],
  imports: [
    CommonModule,
    BrowserModule,
    HttpClientModule,
    ShopModuleModule,
    RouterOutlet
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {

}
