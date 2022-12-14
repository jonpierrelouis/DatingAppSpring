import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-component',
  templateUrl: './login-component.component.html',
  styleUrls: ['./login-component.component.css']
})
export class LoginComponentComponent implements OnInit {

  //inputs for forms
  //default login
  submitEmail: string = "";
  submitPassword: string = "";

  //switch pages
  page: number = 1;

  //unique to sign up
  submitName: string = "";
  submitPasswordRetype: string = "";

  constructor(private loginService: LoginService, private router: Router) { };

  ngOnInit(): void {
    let route = this.router.url;
    // console.log(route);
    if(route == 'register'){
      this.page = 2;
    }

  }

  loginUser(){
    console.log(this.submitEmail);
    console.log(this.submitPassword);

    this.loginService.loginUser(this.submitEmail, this.submitPassword).subscribe((resp) =>{
      console.log(resp);
      this.router.navigate(['homepage']);
    })
  }

  switchToSignUpPage(){
    this.page = 2;
  }

  switchToLoginPage(){
    this.page = 1;
  }

  registerUser(){
    //check to see if passwords are the same
    if(this.submitPassword == this.submitPasswordRetype && this.submitPassword != ""){
      this.loginService.registerUser(this.submitName, this.submitEmail, this.submitPassword).subscribe((resp) =>{
        console.log(resp);
        this.submitEmail = "";
        this.submitPassword = "";
        this.page = 1;
      })
    }

  }

}
