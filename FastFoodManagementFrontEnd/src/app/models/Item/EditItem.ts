import {FastFoodShopId} from "../FastFoodShop/FastFoodShopId";
import {Money} from "../Val_objects/Money";
import {Currency} from "../Val_objects/Currency";
import {ItemType} from "./ItemType";

export interface EditItem {
  itemId: number;
  shopId: FastFoodShopId;
  itemName: string;
  currency: string;
  amount: number;
  type: ItemType;
}
