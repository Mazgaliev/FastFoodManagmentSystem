import {Item} from "../Item/Item";
import {Money} from "../Val_objects/Money";
import {Person} from "../Person/Person";

export interface OrderState {

  items: Item[];
  total: Money;
  worker: Person;

}
