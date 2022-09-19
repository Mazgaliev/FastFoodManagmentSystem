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
  shopId: FastFoodShopId = {id: ""};

  constructor(private readonly genService: GeneralService) {
  }


  ngOnInit(): void {
    this.shopId = {
      id: "8d195355-8dbc-4199-b1e9-50262bba4c7a"
    }
    this.genService.getAllOrders(this.shopId).subscribe(data => {
      console.log(data);
    })
  }
}
