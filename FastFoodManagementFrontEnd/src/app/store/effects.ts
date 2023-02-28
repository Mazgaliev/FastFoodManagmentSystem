import {Injectable} from "@angular/core";
import {Actions, createEffect, ofType} from "@ngrx/effects";
import {GeneralService} from "../services/GeneralService";
import {Store} from "@ngrx/store";
import {AppActions, Selectors} from "./index";
import {exhaustMap, map, withLatestFrom} from "rxjs";

@Injectable()
export class AppEffects {
  constructor(private readonly generalService: GeneralService, private readonly actions$: Actions, private readonly store: Store) {
  }

  fetchOrders$ = createEffect(
    () => this.actions$.pipe(
      ofType(AppActions.getOrders),
      exhaustMap(shopId =>
        this.generalService.getAllOrders(shopId.shopId).pipe(
          map(orders => AppActions.getOrdersSuccess({orders: orders}))
        ))
    )
  )
  $login = createEffect(
    () => this.actions$.pipe(
      ofType(AppActions.login),
      exhaustMap(data =>
        this.generalService.login(data.username, data.password, data.shopId).pipe(
          map(shop => {
            return AppActions.loginSuccess({shop: shop});
          })
        ))
    )
  )
  $logout = createEffect(
    () => this.actions$.pipe(
      ofType(AppActions.logout),
      exhaustMap(() => this.generalService.logout().pipe(
        map(() => AppActions.logoutSuccess())
      ))
    )
  )
  $refreshItems = createEffect(
    () => this.actions$.pipe(
      ofType(AppActions.refreshItems),
      withLatestFrom(this.store.select(Selectors.selectShopId)),
      exhaustMap(([_, id]) => {
        return this.generalService.refreshItems(id).pipe(
          map(response => AppActions.refreshItemsSuccess({items: response}))
        )
      })
    )
  )
  $createItem = createEffect(
    () => this.actions$.pipe(
      ofType(AppActions.createItem),
      exhaustMap(item => this.generalService.makeItem(item.item).pipe(
        map(() => AppActions.refreshItems())
      ))
    )
  )
  $removeItem = createEffect(
    () => this.actions$.pipe(
      ofType(AppActions.deleteItem),
      exhaustMap(delItem => this.generalService.deleteItem(delItem.item).pipe(
        map(() => AppActions.refreshItems())
      ))
    )
  )

  // $refreshItems = createEffect(
  //   () => this.actions$.pipe(
  //     ofType(AppActions.refreshItems),
  //     withLatestFrom(this.store.select(Selectors.selectShopId)),
  //     exhaustMap(([_, id]) => {
  //       return this.generalService.refreshItems(id).pipe(
  //         map(data => AppActions.refreshItemsSuccess({items: data}))
  //       )
  //     })
  //   )
  // )
  $editItem = createEffect(
    () => this.actions$.pipe(
      ofType(AppActions.editItem),
      exhaustMap(data => this.generalService.editItem(data.item).pipe(
        map(() => AppActions.refreshItems())
      ))
    )
  )
  $saveOrder = createEffect(
    () => this.actions$.pipe(
      ofType(AppActions.saveOrder),
      exhaustMap(data => this.generalService.makeOrder(data.order).pipe(
        map(() => AppActions.saveOrderSuccess())
      ))
    )
  )

  $registerStore = createEffect(
    () => this.actions$.pipe(
      ofType(AppActions.registerStore),
      exhaustMap(shop => this.generalService.registerPlace(shop.shop).pipe(
        map(() => AppActions.registerStoreSuccess())
      ))
    )
  )

  $createWorker = createEffect(
    () => this.actions$.pipe(
      ofType(AppActions.createWorker),
      exhaustMap(workerForm => this.generalService.registerWorker(workerForm.worker).pipe(
        map(() => AppActions.createWorkerSuccess())
      ))
    )
  )

  $fetchWorkers = createEffect(
    () => this.actions$.pipe(
      ofType(AppActions.fetchWorkers),
      exhaustMap(shopId => this.generalService.getWorkers(shopId.id).pipe(
        map(workers => AppActions.fetchWorkersSuccess({workers: workers}))
      ))
    )
  )

  $removeWorker = createEffect(
    () => this.actions$.pipe(
      ofType(AppActions.removeWorker),
      exhaustMap(workerForm => this.generalService.removeWorker(workerForm.deletionForm).pipe(
        map(() => AppActions.fetchWorkers({id: workerForm.deletionForm.shopId}))
      ))
    )
  )

  $deleteOrder = createEffect(
    () => this.actions$.pipe(
      ofType(AppActions.removeOrder),
      exhaustMap(delForm => this.generalService.deleteOrder(delForm.order).pipe(
        map(() => AppActions.getOrders({shopId: delForm.order.shopId}))
      ))
    )
  )


  //$createUser
  //$removeUser
  //$saveOrder
  //$deleteOrder
  //$editOrder
  //$betweenDatesOrders
  //$refreshWorkDesk

}
