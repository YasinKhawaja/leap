import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule, Routes } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CapabilityAddComponent } from './components/capability-add/capability-add.component';
import { CapabilityDeleteComponent } from './components/capability-delete/capability-delete.component';
import { CapabilityEditComponent } from './components/capability-edit/capability-edit.component';
import { CapabilityComponent } from './components/capability/capability.component';
import { CompanyRequestComponent } from './components/company-request/company-request.component';
import { EnvironmentAddComponent } from './components/environment-add/environment-add.component';
import { EnvironmentDeleteComponent } from './components/environment-delete/environment-delete.component';
import { EnvironmentEditComponent } from './components/environment-edit/environment-edit.component';
import { EnvironmentComponent } from './components/environment/environment.component';
import { ExportComponent } from "./components/export/export.component";
import { HomeComponent } from './components/home/home.component';
import { ItapplicationAddComponent } from './components/itapplication-add/itapplication-add.component';
import { ItapplicationDeleteComponent } from './components/itapplication-delete/itapplication-delete.component';
import { ItapplicationEditComponent } from './components/itapplication-edit/itapplication-edit.component';
import { ItapplicationComponent } from './components/itapplication/itapplication.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterFormUseradminComponent } from './components/register-form-useradmin/register-form-useradmin.component';
import { RegisterFormComponent } from './components/register-form/register-form.component';
import { CapabilityService } from './services/capability/capability.service';


const appRoutes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'register-useradmin', component: RegisterFormUseradminComponent },
  { path: 'register', component: RegisterFormComponent },
  { path: 'login', component: LoginComponent },
  { path: 'environments', component: EnvironmentComponent },
  { path: 'capabilities', component: CapabilityComponent },
  { path: 'itapplication', component: ItapplicationComponent }
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
    LoginComponent,
    ItapplicationAddComponent,
    ItapplicationDeleteComponent,
    ItapplicationEditComponent,
    ItapplicationComponent
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
