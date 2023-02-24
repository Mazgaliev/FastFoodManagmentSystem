import {Component, OnInit} from "@angular/core";
import {GeneralService} from "./services/GeneralService";
import {FastFoodShopId} from "./models/FastFoodShop/FastFoodShopId";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'FastFoodManagementFrontEnd';

  constructor() {
  }


  ngOnInit(): void {
  }
}
