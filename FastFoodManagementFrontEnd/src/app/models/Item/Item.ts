import {ItemType} from "./ItemType";
import {Money} from "../Val_objects/Money";

export interface Item {
  name: string;
  type: ItemType;
  price: Money;
}
