import {ItemType} from "./ItemType";
import {Money} from "../Val_objects/Money";

export interface Item {
  id: number;
  name: string;
  type: ItemType;
  price: Money;
}
