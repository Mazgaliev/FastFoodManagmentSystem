import {ItemType} from "./ItemType";
import {Money} from "../Val_objects/Money";
import {FastFoodShopId} from "../FastFoodShop/FastFoodShopId";

export interface CreateItem {
  name: string;
  itemType: ItemType;
  price: Money;
  shopId: FastFoodShopId;
}
