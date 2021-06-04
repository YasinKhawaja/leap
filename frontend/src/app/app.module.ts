import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule, Routes } from '@angular/router';
import { NgIdleKeepaliveModule } from '@ng-idle/keepalive';
import { MomentModule } from 'angular2-moment';
import { NgxPrintModule } from 'ngx-print';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CapabilityAddComponent } from './components/capability-add/capability-add.component';
import { CapabilityApplicationAddComponent } from './components/capability-application-add/capability-application-add.component';
import { CapabilityApplicationDeleteComponent } from './components/capability-application-delete/capability-application-delete.component';
import { CapabilityApplicationEditComponent } from './components/capability-application-edit/capability-application-edit.component';
import { CapabilityApplicationComponent } from './components/capability-application/capability-application.component';
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
import { ResetpasswordConfirmComponent } from './components/resetpassword-confirm/resetpassword-confirm.component';
import { ResetpasswordComponent } from './components/resetpassword/resetpassword.component';
import { ResourceAddComponent } from './components/resource-add/resource-add.component';
import { ResourceEditComponent } from './components/resource-edit/resource-edit.component';
import { ResourceComponent } from './components/resource/resource.component';
import { StrategyAddComponent } from './components/strategy-add/strategy-add.component';
import { StrategyDeleteComponent } from './components/strategy-delete/strategy-delete.component';
import { StrategyEditComponent } from './components/strategy-edit/strategy-edit.component';
import { StrategyItemsAddComponent } from './components/strategy-items-add/strategy-items-add.component';
import { StrategyItemsDeleteComponent } from './components/strategy-items-delete/strategy-items-delete.component';
import { StrategyItemsEditComponent } from './components/strategy-items-edit/strategy-items-edit.component';
import { StrategyItemsComponent } from './components/strategy-items/strategy-items.component';
import { StrategyComponent } from './components/strategy/strategy.component';
import { CapabilityService } from './services/capability/capability.service';
import { RouterGuard } from './services/guard/router.guard';
import { CapabilityResourceComponent } from './components/capability-resource/capability-resource.component';

const appRoutes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'register-useradmin', component: RegisterFormUseradminComponent },
  { path: 'register', component: RegisterFormComponent },
  { path: 'login', component: LoginComponent },
  { path: 'environments', component: EnvironmentComponent, canActivate: [RouterGuard] },
  { path: 'capabilities', component: CapabilityComponent, canActivate: [RouterGuard] },
  { path: 'itapplication', component: ItapplicationComponent, canActivate: [RouterGuard] },
  { path: 'capability-application', component: CapabilityApplicationComponent, canActivate: [RouterGuard] },
  { path: 'strategies', component: StrategyComponent, canActivate: [RouterGuard] }
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
    ItapplicationComponent,
    CapabilityApplicationComponent,
    CapabilityApplicationAddComponent,
    CapabilityApplicationDeleteComponent,
    CapabilityApplicationEditComponent,
    StrategyComponent,
    StrategyAddComponent,
    StrategyDeleteComponent,
    StrategyEditComponent,
    StrategyItemsComponent,
    StrategyItemsAddComponent,
    StrategyItemsEditComponent,
    StrategyItemsDeleteComponent,
    ResourceComponent,
    ResetpasswordComponent,
    ResetpasswordConfirmComponent,
    ResourceAddComponent,
    ResourceEditComponent,
    CapabilityResourceComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot(appRoutes),
    HttpClientModule,
    BrowserAnimationsModule,
    NgIdleKeepaliveModule.forRoot(),
    MomentModule,
    NgxPrintModule
  ],
  providers: [CapabilityService, NgxPrintModule],
  bootstrap: [AppComponent]
})
export class AppModule { }
