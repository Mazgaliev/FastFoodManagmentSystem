import {ItemType} from "../Item/ItemType";
import {Money} from "../Val_objects/Money";

export interface OrderItem {
  specificId: string;
  id: number;
  name: string;
  type: ItemType;
  price: Money;
}
