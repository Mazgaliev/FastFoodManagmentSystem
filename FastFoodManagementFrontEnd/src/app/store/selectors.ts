import {createFeatureSelector, createSelector} from "@ngrx/store";
import {AppState} from "./state";

export const appFeatureKey = 'appState';

export const selectAppState = createFeatureSelector<AppState>(appFeatureKey);


export const selectShop = createSelector(
  selectAppState,
  (state: AppState) => state.shopState
);
