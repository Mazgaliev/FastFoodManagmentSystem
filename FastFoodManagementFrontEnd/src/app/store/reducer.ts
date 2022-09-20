import {createReducer, on} from "@ngrx/store";
import {initialState} from "./state";
import {getOrders} from "./actions";

export const reducer = createReducer(
  initialState,
  on(getOrders, (state) => ({
    ...state,

  }))
)
