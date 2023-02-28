import {createReducer, on} from "@ngrx/store";
import {defaultOrders, defaultShop, initialState} from "./state";
import {
  addItemCount,
  addToOrder,
  createItem,
  createItemSuccess,
  deleteItem,
  deleteItemSuccess,
  editItem,
  editItemSuccess,
  editOrder,
  editOrderSuccess, fetchWorkers, fetchWorkersSuccess,
  getOrders,
  getOrdersFail,
  getOrdersSuccess,
  loginSuccess,
  logout,
  logoutSuccess,
  reduceItemCount, refreshItemsSuccess, registerStore, registerStoreSuccess,
  removeItemFromOrder,
  removeOrder,
  removeOrderSuccess,
  saveOrder,
  saveOrderFail,
  saveOrderSuccess
} from "./actions";

export const reducer = createReducer(
    initialState,
    on(getOrders, (state) => ({
      ...state,
      allOrders: []
    })),
    on(refreshItemsSuccess, (state, {items}) => ({
      ...state,
      shopState: {
        id: state.shopState.id,
        name: state.shopState.name,
        owner: state.shopState.owner,
        drinks: items.drinks,
        additives: items.additives,
        foods: items.foods,
        currentWorker: state.shopState.currentWorker,
        location: state.shopState.location
      }
    })),
    on(getOrdersSuccess, (state, {orders}) => ({
      ...state,
      allOrders: [...orders]
    })),
    on(getOrdersFail, (state) => ({
      ...state,
    })),
    on(createItem, (state, {item}) => ({
      ...state,
    })),
    on(createItemSuccess, (state) => ({
      ...state,
    })),
    on(saveOrder, (state) => ({
      ...state,
    })),
    on(saveOrderSuccess, (state) => ({
      ...state,
      orderState: {
        items: [],
        worker: {...state.shopState.currentWorker},
        total: {
          currency: state.orderState.total.currency,
          amount: 0
        }
      }
    })),
    on(saveOrderFail, (state) => ({
      ...state
    })),
    on(addToOrder, (state, {item}) => ({
      ...state,
      orderState: {
        worker: {...state.orderState.worker},
        items: [...state.orderState.items, item],
        total: {
          currency: state.orderState.total.currency,
          amount: state.orderState.total.amount + item.price.amount
        }
      }
    })),
    on(addItemCount, (state, {amount}) => ({
      ...state,
      orderState: {
        total: {
          amount: state.orderState.total.amount + amount,
          currency: state.orderState.total.currency
        },
        worker: {...state.orderState.worker},
        items: [...state.orderState.items]
      }
    })),
    on(removeItemFromOrder, (state, {item}) => ({
      ...state,
      orderState: {
        items: state.orderState.items.filter(i => i.specificId != item.specificId),
        worker: {...state.orderState.worker},
        total: {
          currency: state.orderState.total.currency,
          amount: state.orderState.total.amount - item.price.amount
        }
      }

    })),

    on(reduceItemCount, (state, {amount}) => ({
      ...state,
      orderState: {
        items: [...state.orderState.items],
        worker: {...state.orderState.worker},
        total: {
          currency: state.orderState.total.currency,
          amount: state.orderState.total.amount - amount
        }
      }
    }))
    , on(createItem, (state) => ({
      ...state
    })),
    on(createItemSuccess, (state) => ({
      ...state
    })),
    on(editItem, (state) => ({
      ...state
    })),
    on(editItemSuccess, (state) => ({
      ...state,
    })),
    on(deleteItem, (state) => ({
      ...state
    })),
    on(deleteItemSuccess, (state) => ({
      ...state,
    })),
    on(logout, (state) => ({
      ...state,
      shopState: defaultShop,
      orderState: defaultOrders,
      allOrders: [],
      loggedIn: false
    })),
    on(editOrder, (state) => ({
      ...state
    })),
    on(editOrderSuccess, (state) => ({
      ...state
    })),
    on(removeOrder, (state) => ({
      ...state,
    })),
    on(removeOrderSuccess, (state) => ({
      ...state,
    })),
    on(logoutSuccess, (state) => ({
      ...state,
      shopState: defaultShop,
      orderState: defaultOrders,
      allOrders: [],
      loggedIn: false
    })),
    on(loginSuccess, (state, {shop}) => ({
      ...state,
      shopState: {...shop},
      loggedIn: true
    })),
    on(registerStore, (state) => ({
      ...state,
    })),
    on(registerStoreSuccess, (state) => ({
      ...state,
    })),
    on(fetchWorkers, (state) => ({
      ...state,
    }))
    ,
    on(fetchWorkersSuccess, (state, {workers}) => ({
      ...state,
      workers: [...workers]
    }))
  )
;

