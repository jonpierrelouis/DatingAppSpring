import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { LoginComponentComponent } from './components/login-component/login-component.component';
import { HomepageComponentComponent } from './components/homepage-component/homepage-component.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    LoginComponentComponent,
    HomepageComponentComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
