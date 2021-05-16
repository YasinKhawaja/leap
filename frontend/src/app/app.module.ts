import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { HttpClientModule } from '@angular/common/http'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CapabilityComponent } from './components/capability/capability.component';
import { CapabilityAddComponent } from './components/capability-add/capability-add.component';
import { CapabilityService } from './services/capability/capability.service';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterFormComponent } from './components/register-form/register-form.component';
import { RegisterFormUseradminComponent } from './components/register-form-useradmin/register-form-useradmin.component';
import { EnvironmentComponent } from './components/environment/environment.component';
import { EnvironmentAddComponent } from './components/environment-add/environment-add.component';
import { EnvironmentEditComponent } from './components/environment-edit/environment-edit.component';
import { EnvironmentDeleteComponent } from './components/environment-delete/environment-delete.component';
import { CapabilityDeleteComponent } from './components/capability-delete/capability-delete.component';
import { CapabilityEditComponent } from './components/capability-edit/capability-edit.component';
import { ExportComponent } from "./components/export/export.component";
import { CompanyRequestComponent } from './components/company-request/company-request.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

const appRoutes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'register-useradmin', component: RegisterFormUseradminComponent },
  { path: 'register', component: RegisterFormComponent },
  { path: 'login', component: LoginComponent },
  { path: 'envs', component: EnvironmentComponent },
  { path: 'caps', component: CapabilityComponent }
]

@NgModule({
  declarations: [
    AppComponent,
    CapabilityComponent,
    CapabilityAddComponent,
    RegisterFormComponent,
    RegisterFormUseradminComponent,
    EnvironmentComponent,
    EnvironmentAddComponent,
    EnvironmentEditComponent,
    EnvironmentDeleteComponent,
    CapabilityDeleteComponent,
    CapabilityEditComponent,
    ExportComponent,
    CompanyRequestComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot(appRoutes),
    HttpClientModule,
    BrowserAnimationsModule
  ],
  providers: [CapabilityService],
  bootstrap: [AppComponent]
})
export class AppModule { }
