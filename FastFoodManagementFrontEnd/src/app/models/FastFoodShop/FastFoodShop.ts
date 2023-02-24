import {FastFoodShopId} from "./FastFoodShopId";
import {Location} from "../Val_objects/Location";
import {Owner} from "../Person/Owner";
import {Person} from "../Person/Person";
import {Item} from "../Item/Item";

export interface FastFoodShop {
  id: FastFoodShopId;
  name: string;
  location: Location;
  owner: Owner;

  currentWorker: Person;

  foods: Item[];
  additives: Item[];
  drinks: Item[];

}
