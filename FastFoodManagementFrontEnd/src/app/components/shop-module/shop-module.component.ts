import {Component, OnInit} from '@angular/core';
import {Store} from "@ngrx/store";
import {AppActions, Selectors} from "../../store";

@Component({
  selector: 'app-shop-module',
  templateUrl: './shop-module.component.html',
  styleUrls: ['./shop-module.component.css']
})
export class ShopModuleComponent implements OnInit {

  $loggedIn = this.store.select(Selectors.selectLoggedIn);
  $role = this.store.select(Selectors.selectRole);
  $shopName = this.store.select(Selectors.selectShopName);

  constructor(private readonly store: Store) {
  }

  ngOnInit(): void {

  }

  logout(logout: any) {

    this.store.dispatch(AppActions.logout());
  }
}
