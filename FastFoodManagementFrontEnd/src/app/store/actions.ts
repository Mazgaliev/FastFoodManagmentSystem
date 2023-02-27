import {createAction, props} from "@ngrx/store";
import {Order} from "../models/Order/Order";
import {Item} from "../models/Item/Item";
import {OrderItem} from "../models/Order/OrderItem";
import {FastFoodShopId} from "../models/FastFoodShop/FastFoodShopId";
import {FastFoodShop} from "../models/FastFoodShop/FastFoodShop";
import {RefreshItems} from "../models/Item/RefreshItems";
import {CreateItem} from "../models/Item/CreateItem";
import {DeleteItem} from "../models/Item/DeleteItem";
import {EditItem} from "../models/Item/EditItem";
import {CreateOrder} from "../models/Order/CreateOrder";
import {CreateFastFoodShop} from "../models/FastFoodShop/CreateFastFoodShop";
import {WorkerForm} from "../models/Person/WorkerForm";
import {Person} from "../models/Person/Person";
import {RemoveWorkerForm} from "../models/Person/RemoveWorkerForm";
import {DeleteOrder} from "../models/Order/DeleteOrder";

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
  REFRESH_ITEMS = '[Item] Refresh items',
  REFRESH_ITEMS_SUCCESS = '[Item] Refresh items successfully',

  LOGIN = '[General] Log in',
  LOGIN_SUCCESS = '[General] Log in successfully',
  LOGIN_FAIL = '[General] Log in failure',
  LOGOUT = '[General] Log out',
  LOGOUT_SUCCESS = '[General] Log out successfully',
  REGISTER_SHOP = '[General] Register a store',
  REGISTER_SHOP_SUCCESS = '[General] Register a store successfully',
  REGISTER_WORKER = '[General] Register a worker',
  REGISTER_WORKER_SUCCESS = '[General] Register a worker successfully',


  FETCH_WORKERS = '[Worker] Refresh shop workers',
  FETCH_WORKERS_SUCCESS = '[Worker] Refresh shop workers success',
  REMOVE_WORKER = '[Worker] delete a worker',
  DELETE_WORKER_SUCCESS = '[Worker] delete a worker successfully'

}

export const removeWorker = createAction(
  Actions.REMOVE_WORKER,
  props<{ deletionForm: RemoveWorkerForm }>()
)
export const removeWorkerSuccess = createAction(
  Actions.DELETE_WORKER_SUCCESS,
)

export const fetchWorkers = createAction(
  Actions.FETCH_WORKERS,
  props<{ id: FastFoodShopId }>()
)
export const fetchWorkersSuccess = createAction(
  Actions.FETCH_WORKERS_SUCCESS,
  props<{ workers: Person[] }>()
)
export const createWorker = createAction(
  Actions.REGISTER_WORKER,
  props<{ worker: WorkerForm }>()
)
export const createWorkerSuccess = createAction(
  Actions.REGISTER_WORKER_SUCCESS
)

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
  props<{ shopId: FastFoodShopId }>()
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
  props<{ item: OrderItem }>()
)

export const saveOrder = createAction(
  Actions.SAVE_ORDER,
  props<{ order: CreateOrder }>()
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
  props<{ item: OrderItem }>()
)
export const removeOrder = createAction(
  Actions.REMOVE_ORDER,
  props<{ order: DeleteOrder }>()
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
  props<{ item: CreateItem }>()
)

export const createItemSuccess = createAction(
  Actions.CREATE_ITEM_SUCCESS,
)

export const editItem = createAction(
  Actions.EDIT_ITEM,
  props<{ item: EditItem }>()
)
export const editItemSuccess = createAction(
  Actions.EDIT_ITEM_SUCCESS,
)

export const deleteItem = createAction(
  Actions.DELETE_ITEM,
  props<{ item: DeleteItem }>()
)
export const deleteItemSuccess = createAction(
  Actions.DELETE_ITEM_SUCCESS,
)
export const login = createAction(
  Actions.LOGIN,
  props<{ username: string, password: string, shopId: string }>()
)

export const loginSuccess = createAction(
  Actions.LOGIN_SUCCESS,
  props<{ shop: FastFoodShop }>()
)
export const loginFail = createAction(
  Actions.LOGIN_FAIL,
  props<{ error: string }>()
)

export const logout = createAction(
  Actions.LOGOUT,
)
export const logoutSuccess = createAction(
  Actions.LOGOUT_SUCCESS,
)
export const refreshItems = createAction(
  Actions.REFRESH_ITEMS,
)
export const refreshItemsSuccess = createAction(
  Actions.REFRESH_ITEMS_SUCCESS,
  props<{ items: RefreshItems }>()
)

export const registerStore = createAction(
  Actions.REGISTER_SHOP,
  props<{ shop: CreateFastFoodShop }>()
)
export const registerStoreSuccess = createAction(
  Actions.REGISTER_SHOP_SUCCESS
)
