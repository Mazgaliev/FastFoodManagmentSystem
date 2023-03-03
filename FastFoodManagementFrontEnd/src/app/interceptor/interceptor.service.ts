import {Injectable} from "@angular/core";
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";


@Injectable()
export class InterceptorService implements HttpInterceptor {


  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    let token: string | null = sessionStorage.getItem("token");

    if (token) {
      let newReq: HttpRequest<any> = req.clone({
        setHeaders: {"Authorization": "Bearer " + token}
      });
      return next.handle(newReq);
    }

    return next.handle(req);
  }

}
