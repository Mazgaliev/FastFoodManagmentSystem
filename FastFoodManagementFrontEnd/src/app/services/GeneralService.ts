import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {FastFoodShopId} from "../models/FastFoodShop/FastFoodShopId";
import {Observable} from "rxjs";
import {FastFoodShop} from "../models/FastFoodShop/FastFoodShop";
import {CreateItem} from "../models/Item/CreateItem";
import {DeleteItem} from "../models/Item/DeleteItem";
import {CreateOrder} from "../models/Order/CreateOrder";
import {DeleteOrder} from "../models/Order/DeleteOrder";
import {EditOrder} from "../models/Order/EditOrder";
import {EditItem} from "../models/Item/EditItem";
import {Order} from "../models/Order/Order";
import {Item} from "../models/Item/Item";
import {CreateFastFoodShop} from "../models/FastFoodShop/CreateFastFoodShop";
import {RefreshItems} from "../models/Item/RefreshItems";
import {WorkerForm} from "../models/Person/WorkerForm";
import {Person} from "../models/Person/Person";


@Injectable({providedIn: 'root'})
export class GeneralService {
  private readonly homeUrl = 'api/home';
  private readonly makeOrderUrl = 'api/order';
  private readonly getAllOrdersUrl = 'api/order';

  constructor(private readonly httpClient: HttpClient) {
  }

  login(username: string, password: string, shopId: string): Observable<FastFoodShop> {

    let formData: any = new FormData();
    formData.append("username", username);
    formData.append("password", password);
    formData.append("shopId", shopId);

    return this.httpClient.post<FastFoodShop>("api/login", formData);
  }

  logout(): Observable<any> {
    // this.httpClient.post("")

    return this.httpClient.post<any>("api/logout", null);
  }

  refreshItems(shopId: FastFoodShopId): Observable<RefreshItems> {
    return this.httpClient.get<RefreshItems>("api/home/refreshItems", {params: {'shopId': shopId.id}});
  }

  makeItem(itemForm: CreateItem): Observable<any> {

    return this.httpClient.post("/api/items/add", itemForm);
  }

  deleteItem(delItemForm: DeleteItem): Observable<boolean> {

    return this.httpClient.delete<boolean>("/api/items/delete", {body: delItemForm});
  }

  makeOrder(orderForm: CreateOrder): Observable<Order> {

    return this.httpClient.post<Order>("/api/order", orderForm);
  }

  deleteOrder(delOrderForm: DeleteOrder): Observable<any> {

    return this.httpClient.delete("/api/order/remove", {body: delOrderForm});
  }

  editOrder(editOrderForm: EditOrder): Observable<Order> {

    return this.httpClient.post<Order>("/api/order/edit", editOrderForm);
  }

  editItem(editItemForm: EditItem): Observable<Item> {

    return this.httpClient.post<Item>("/api/items/edit", editItemForm);
  }

  getAllOrders(shopId: FastFoodShopId): Observable<Order[]> {

    return this.httpClient.get<Order[]>("/api/order" + '/' + shopId.id);
  }

  getOrdersBetween(): Observable<Order[]> {

    return this.httpClient.get<Order[]>("api/orders/view/between");
  }

  registerPlace(createPlaceForm: CreateFastFoodShop): Observable<boolean> {

    return this.httpClient.post<boolean>("/api/register", createPlaceForm);
  }

  registerWorker(form: WorkerForm): Observable<boolean> {
    return this.httpClient.post<boolean>("/api/register/user", form);
  }

  getWorkers(): Observable<Person[]> {
    return this.httpClient.get<Person[]>("/api/home/workers");
  }

}
