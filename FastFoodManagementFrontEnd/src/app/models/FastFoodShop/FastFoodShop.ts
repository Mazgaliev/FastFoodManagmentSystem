import {FastFoodShopId} from "./FastFoodShopId";
import {Location} from "../Val_objects/Location";
import {Owner} from "../Person/Owner";
import {Item} from "../Item/Item";
import {Person} from "../Person/Person";

export interface FastFoodShop {
  id: FastFoodShopId;
  name: string;
  location: Location;
  owner: Owner;

  currentWorker:Person;

  foods: Item[];
  additives: Item[];
  drinks: Item[];

}
