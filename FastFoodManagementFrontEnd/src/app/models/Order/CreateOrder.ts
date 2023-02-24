import {FastFoodShopId} from "../FastFoodShop/FastFoodShopId";
import {Currency} from "../Val_objects/Currency";

export interface CreateOrder {

  itemIds: number[];
  workerUsername: string | null;
  shopId: FastFoodShopId;
  currency: string;
  amount: number;

}
