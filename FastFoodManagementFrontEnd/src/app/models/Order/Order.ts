import {Money} from "../Val_objects/Money";
import {Item} from "../Item/Item";
import {Person} from "../Person/Person";

export interface Order {
  id: number;
  itemsIds: Item[];
  worker: Person;
  orderTime: string;
  total: Money;
}
