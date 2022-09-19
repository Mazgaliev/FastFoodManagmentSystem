import {FastFoodShopId} from "../FastFoodShop/FastFoodShopId";
import {Currency} from "../Val_objects/Currency";

export interface CreateOrder {

  itemIds: number[];
  workerUsername: string;
  shopId: FastFoodShopId;
  currency: Currency;
  amount: number;

}
