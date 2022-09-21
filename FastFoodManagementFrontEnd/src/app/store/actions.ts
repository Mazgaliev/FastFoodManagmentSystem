import {createAction, props} from "@ngrx/store";
import {Order} from "../models/Order/Order";
import {Item} from "../models/Item/Item";

const enum Actions {
  FETCH_ORDERS = '[Order] Get orders',
  FETCH_ORDERS_SUCCESS = '[Order] Successfully get orders',
  FETCH_ORDERS_FAIL = '[Order] Failure while getting orders',

  SAVE_ORDER = '[Order] Save order',
  SAVE_ORDER_SUCCESS = '[Order] Save order successfully',
  SAVE_ORDER_FAIL = '[Order] Save order failure',
  REMOVE_ORDER = '[Order] Remove order',
  REMOVE_ORDER_SUCCESS = '[Order] Remove order successfully',
  EDIT_ORDER = '[Order] Edit order',
  EDIT_ORDER_SUCCESS = '[Order] Edit order successfully',

  ADD_TO_ORDER = '[Item] Add item to the order',
  ADD_TO_ORDER_SUCCESS = '[Item] Add item successfully',
  ADD_TO_ORDER_FAIL = '[Item] Add item failure',
  REMOVE_FROM_ORDER = '[Item] Remove item from the order',
  REMOVE_FROM_ORDER_SUCCESS = '[Item] Remove item from the order successfully',
  REMOVE_FROM_ORDER_FAIL = '[Item] Remove item from the order failure',
  CREATE_ITEM = '[Item] Create item',
  CREATE_ITEM_SUCCESS = '[Item] Create item successfully',
  EDIT_ITEM = '[Item] Edit item',
  EDIT_ITEM_SUCCESS = '[Item] Edit item successfully',
  DELETE_ITEM = '[Item] Delete item',
  DELETE_ITEM_SUCCESS = '[Item] Delete item successfully',
  ADD_ITEM_COUNT = '[Item] Add item count',
  REDUCE_ITEM_COUNT = '[Item] Reduce item count',

  LOGIN = '[General] Log in',
  LOGIN_SUCCESS = '[General] Log in successfully',
  LOGIN_FAIL = '[General] Log in failure',
  LOGOUT = '[General] Log out',


}

export const addItemCount = createAction(
  Actions.ADD_ITEM_COUNT,
  props<{ amount: number }>()
)
export const reduceItemCount = createAction(
  Actions.REDUCE_ITEM_COUNT,
  props<{ amount: number }>()
)
export const getOrders = createAction(
  Actions.FETCH_ORDERS,
  props<{ shopId: string }>()
)
export const getOrdersSuccess = createAction(
  Actions.FETCH_ORDERS_SUCCESS,
  props<{ orders: Order[] }>()
)
export const getOrdersFail = createAction(
  Actions.FETCH_ORDERS_FAIL,
  props<{ error: string }>
)
export const addToOrder = createAction(
  Actions.ADD_TO_ORDER,
  props<{ item: Item }>()
)
export const addToOrderSuccess = createAction(
  Actions.ADD_TO_ORDER_SUCCESS,
)
export const addToOrderFail = createAction(
  Actions.ADD_TO_ORDER_FAIL,
  props<{ error: string }>
)
export const saveOrder = createAction(
  Actions.SAVE_ORDER,
  props<{ order: Order }>
)
export const saveOrderSuccess = createAction(
  Actions.SAVE_ORDER_SUCCESS,
)
export const saveOrderFail = createAction(
  Actions.SAVE_ORDER_FAIL,
  props<{ error: string }>()
)
export const removeItemFromOrder = createAction(
  Actions.REMOVE_FROM_ORDER,
  props<{ item: Item }>()
)
export const removeItemFromOrderSuccess = createAction(
  Actions.REMOVE_FROM_ORDER_SUCCESS
)
export const removeItemFromOrderFail = createAction(
  Actions.REMOVE_FROM_ORDER_FAIL
)
export const removeOrder = createAction(
  Actions.REMOVE_ORDER,
  props<{ order: Order }>()
)
export const removeOrderSuccess = createAction(
  Actions.REMOVE_ORDER_SUCCESS
)
export const editOrder = createAction(
  Actions.EDIT_ORDER,
  props<{ order: Order }>()
)

export const editOrderSuccess = createAction(
  Actions.EDIT_ORDER_SUCCESS
)

export const createItem = createAction(
  Actions.CREATE_ITEM,
  props<{ item: Item }>()
)

export const createItemSuccess = createAction(
  Actions.CREATE_ITEM_SUCCESS,
)

export const editItem = createAction(
  Actions.EDIT_ITEM,
  props<{ item: Item }>()
)
export const editItemSuccess = createAction(
  Actions.EDIT_ITEM_SUCCESS,
)

export const deleteItem = createAction(
  Actions.DELETE_ITEM,
  props<{ item: Item }>()
)
export const deleteItemSuccess = createAction(
  Actions.DELETE_ITEM,
)
export const login = createAction(
  Actions.LOGIN,
  props<{ credentials: { username: string, password: string, shopId: string } }>
)

export const loginSuccess = createAction(
  Actions.LOGIN_SUCCESS
)
export const loginFail = createAction(
  Actions.LOGIN_FAIL,
  props<{ error: string }>()
)

export const logout = createAction(
  Actions.LOGOUT
)
