import {Money} from "../Val_objects/Money";
import {Person} from "../Person/Person";
import {OrderItem} from "./OrderItem";

export interface OrderState {

  items: OrderItem[];
  total: Money;
  worker: Person;

}
