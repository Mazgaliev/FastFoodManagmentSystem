import {FastFoodShop} from "../models/FastFoodShop/FastFoodShop";
import {Order} from "../models/Order/Order";
import {PersonRole} from "../models/Person/PersonRole";
import {OrderState} from "../models/Order/OrderState";
import {ItemType} from "../models/Item/ItemType";
import {Person} from "../models/Person/Person";

export const defaultShop: FastFoodShop = {
  id: {
    id: ""
  },
  name: "",
  location: {
    latitude: "1",
    longitude: "2",
    city: "N"
  },
  currentWorker: {
    id: {
      id: ''
    },
    username: "",
    role: null,
  },
  owner: {
    name: "",
    surname: "",
    e_mail: "",
    phoneNumber: {
      operator: "",
      number: ""
    }
  },
  foods: [{name: "Borger", price: {currency: "MKD", amount: 150}, id: 1, type: ItemType.FOOD}],
  drinks: [],
  additives: []
};

export const defaultOrders: OrderState = {
  items: [],
  worker: {
    id: {
      id: ''
    },
    username: "Mite",
    role: PersonRole.WORKER
  },
  total: {
    currency: "MKD",
    amount: 0
  },
};

export interface AppState {
  shopState: FastFoodShop;
  orderState: OrderState;
  workers: Person[];
  allOrders: Order[];
  loggedIn: boolean;
}

export const initialState: AppState = {
  shopState: defaultShop,
  orderState: defaultOrders,
  allOrders: [],
  workers: [],
  loggedIn: false
}
