import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { LoginComponentComponent } from './components/login-component/login-component.component';
import { HomepageComponentComponent } from './components/homepage-component/homepage-component.component';

@NgModule({
  declarations: [
    AppComponent,
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
