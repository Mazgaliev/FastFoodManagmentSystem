import {FastFoodShop} from "../models/FastFoodShop/FastFoodShop";
import {Order} from "../models/Order/Order";
import {PersonRole} from "../models/Person/PersonRole";

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
    username: "",
    role: PersonRole.WORKER,
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
  foods: [],
  drinks: [],
  additives: []
};
export const defaultOrders: Order = {
  id: 1,
  items: [],
  worker: {
    username: "",
    role: PersonRole.WORKER
  },
  total: {
    currency: "MKD",
    amount: 0
  },
  orderTime: "",
};

export interface AppState {
  shopState: FastFoodShop;
  orderState: Order;
  allOrders: Order[];
  loggedIn: boolean;
}

export const initialState: AppState = {
  shopState: defaultShop,
  orderState: defaultOrders,
  allOrders: [],
  loggedIn: false
}
