import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login-component',
  templateUrl: './login-component.component.html',
  styleUrls: ['./login-component.component.css']
})
export class LoginComponentComponent implements OnInit {

  //inputs for forms
  submitEmail: string = "";
  submitPassword: string = "";

  constructor(private loginService: LoginService) { };

  ngOnInit(): void {
  }

  loginUser(){
    console.log(this.submitEmail);
    console.log(this.submitPassword);

    this.loginService.loginUser(this.submitEmail, this.submitPassword).subscribe((resp) =>{
      console.log(resp);
    })
  }

}
