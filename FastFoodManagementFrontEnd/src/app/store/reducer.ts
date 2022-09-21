import {createReducer, on} from "@ngrx/store";
import {initialState} from "./state";
import {
  addItemCount,
  addToOrder,
  createItem,
  createItemSuccess,
  getOrders,
  getOrdersFail,
  getOrdersSuccess, reduceItemCount, removeItemFromOrder,
  saveOrder, saveOrderFail,
  saveOrderSuccess
} from "./actions";

export const reducer = createReducer(
  initialState,
  on(getOrders, (state) => ({
    ...state,
    allOrders: []
  })),
  on(getOrdersSuccess, (state, {orders}) => ({
    ...state,
    allOrders: orders
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
  })),
  on(saveOrderFail, (state) => ({
    ...state
  })),
  on(addToOrder, (state, {item}) => ({
    ...state,
    orderState: {
      worker: state.orderState.worker,
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
      worker: state.orderState.worker,
      items: state.orderState.items
    }
  })),
  on(removeItemFromOrder, (state, {item}) => ({
    ...state,
    orderState: {
      items: state.orderState.items.filter(i => i.id != item.id),
      worker: state.orderState.worker,
      total: {
        currency: state.orderState.total.currency,
        amount: state.orderState.total.amount - item.price.amount
      }
    }

  })),
  on(reduceItemCount, (state, {amount}) => ({
    ...state,
    orderState: {
      items: state.orderState.items,
      worker: state.orderState.worker,
      total: {
        currency: state.orderState.total.currency,
        amount: state.orderState.total.amount - amount
      }
    }
  }))
);

