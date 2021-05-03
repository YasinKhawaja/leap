import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { HttpClientModule } from '@angular/common/http'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CapabilityComponent } from './capability/capability.component';
import { CapabilityAddComponent } from './capability-add/capability-add.component';
import { CapabilityService } from './service/capability.service';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterFormComponent } from './components/register-form/register-form.component';
import { RegisterFormUseradminComponent } from './components/register-form-useradmin/register-form-useradmin.component';
import { EnvironmentComponent } from './components/environment/environment.component';
import { EnvironmentAddComponent } from './components/environment-add/environment-add.component';

const appRoutes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'capabilities', component: CapabilityComponent },
  { path: 'register-useradmin', component: RegisterFormUseradminComponent },
  { path: 'register', component: RegisterFormComponent },
  { path: 'environments', component: EnvironmentComponent}
]

@NgModule({
  declarations: [
    AppComponent,
    CapabilityComponent,
    CapabilityAddComponent,
    RegisterFormComponent,
    RegisterFormUseradminComponent,
    EnvironmentComponent,
    EnvironmentAddComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot(appRoutes),
    HttpClientModule
  ],
  providers: [CapabilityService],
  bootstrap: [AppComponent]
})
export class AppModule { }
