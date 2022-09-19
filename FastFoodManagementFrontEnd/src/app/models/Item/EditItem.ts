import {FastFoodShopId} from "../FastFoodShop/FastFoodShopId";
import {Money} from "../Val_objects/Money";

export interface EditItem {
  itemId:number;
  shopId:FastFoodShopId;
  name:string;
  price:Money;
}
