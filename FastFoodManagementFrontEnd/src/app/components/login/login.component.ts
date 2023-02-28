import {Component, OnInit} from '@angular/core';
import {FormBuilder} from "@angular/forms";
import {Store} from "@ngrx/store";
import {AppActions} from "../../store";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm = this.formBuilder.group({
    username: '',
    password: '',
    shopId: ''
  });

  constructor(private formBuilder: FormBuilder, private readonly store: Store) {
  }

  ngOnInit(): void {

  }

  login() {
    let username = this.loginForm.get('username')?.value;
    let shopId = this.loginForm.get('shopId')?.value;
    let password = this.loginForm.get('password')?.value;
    if (username != null && shopId != null && password != null) {
      this.store.dispatch(AppActions.login({username: username, password: password, shopId: shopId}))

    }
  }
}
