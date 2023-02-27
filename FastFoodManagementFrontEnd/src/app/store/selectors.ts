import {createFeatureSelector, createSelector, select} from "@ngrx/store";
import {AppState} from "./state";

export const appFeatureKey = 'appState';

export const selectAppState = createFeatureSelector<AppState>(appFeatureKey);


export const selectShop = createSelector(
  selectAppState,
  (state: AppState) => state.shopState
);
export const selectOrders = createSelector(
  selectAppState,
  (state: AppState) => state.allOrders
);
export const selectOrder = createSelector(
  selectAppState,
  (state: AppState) => state.orderState
);
export const selectOrderItems = createSelector(
  selectAppState,
  (state) => state.orderState.items
);
export const selectOrderTotal = createSelector(
  selectAppState,
  (state) => state.orderState.total
);
export const selectShopId = createSelector(
  selectAppState,
  (state) => state.shopState.id
)
export const selectLoggedIn = createSelector(
  selectAppState,
  (state) => state.loggedIn
)
export const selectFoods = createSelector(
  selectShop,
  (state) => state.foods
)
export const selectDrinks = createSelector(
  selectShop,
  (state) => state.drinks
)
export const selectAdditives = createSelector(
  selectShop,
  (state) => state.additives
)
export const selectRole = createSelector(
  selectShop,
  (state) => state.currentWorker.role
)
export const selectWorkers = createSelector(
  selectAppState,
  (state) => state.workers
)
