import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponentComponent } from './components/login-component/login-component.component';
import { HomepageComponentComponent } from './components/homepage-component/homepage-component.component';

const routes : Routes = [
    { path: "", redirectTo: "/login", pathMatch: 'full' },
    { path: "login", component: LoginComponentComponent },
    // { path: "register", component: LoginComponentComponent },
    { path: "homepage", component: HomepageComponentComponent }
]

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})

export class AppRoutingModule {}