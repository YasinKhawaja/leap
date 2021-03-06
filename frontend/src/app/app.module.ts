import { HttpClientModule, HttpClientXsrfModule, HTTP_INTERCEPTORS } from '@angular/common/http';
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
import { BusinessprocessAddComponent } from './components/businessprocess-add/businessprocess-add.component';
import { BusinessprocessDeleteComponent } from './components/businessprocess-delete/businessprocess-delete.component';
import { BusinessprocessEditComponent } from './components/businessprocess-edit/businessprocess-edit.component';
import { BusinessprocessComponent } from './components/businessprocess/businessprocess.component';
import { CapabilityAddComponent } from './components/capability-add/capability-add.component';
import { CapabilityApplicationAddComponent } from './components/capability-application-add/capability-application-add.component';
import { CapabilityApplicationDeleteComponent } from './components/capability-application-delete/capability-application-delete.component';
import { CapabilityApplicationEditComponent } from './components/capability-application-edit/capability-application-edit.component';
import { CapabilityApplicationComponent } from './components/capability-application/capability-application.component';
import { CapabilityBusinessprocessAddComponent } from './components/capability-businessprocess-add/capability-businessprocess-add.component';
import { CapabilityBusinessprocessDeleteComponent } from './components/capability-businessprocess-delete/capability-businessprocess-delete.component';
import { CapabilityBusinessprocessComponent } from './components/capability-businessprocess/capability-businessprocess.component';
import { CapabilityDeleteComponent } from './components/capability-delete/capability-delete.component';
import { CapabilityEditComponent } from './components/capability-edit/capability-edit.component';
import { CapabilityResourceAddComponent } from './components/capability-resource-add/capability-resource-add.component';
import { CapabilityResourceDeleteComponent } from './components/capability-resource-delete/capability-resource-delete.component';
import { CapabilityResourceComponent } from './components/capability-resource/capability-resource.component';
import { CapabilityStrategyitemsAddComponent } from './components/capability-strategyitems-add/capability-strategyitems-add.component';
import { CapabilityStrategyitemsDeleteComponent } from './components/capability-strategyitems-delete/capability-strategyitems-delete.component';
import { CapabilityStrategyitemsEditComponent } from './components/capability-strategyitems-edit/capability-strategyitems-edit.component';
import { CapabilityStrategyitemsComponent } from './components/capability-strategyitems/capability-strategyitems.component';
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
import { ResourceDeleteComponent } from './components/resource-delete/resource-delete.component';
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
import { UserAddComponent } from './components/user-add/user-add.component';
import { UserDeleteComponent } from './components/user-delete/user-delete.component';
import { UserEditComponent } from './components/user-edit/user-edit.component';
import { AuthInterceptor } from './services/authinterceptor/auth-interceptor';
import { CapabilityService } from './services/capability/capability.service';
import { RouterGuard } from './services/guard/router.guard';
import { CapabilityResourceEditComponent } from './components/capability-resource-edit/capability-resource-edit.component';
import { ProjectComponent } from './components/project/project.component';
import { ProjectAddComponent } from './components/project-add/project-add.component';
import { ProjectEditComponent } from './components/project-edit/project-edit.component';
import { ProjectDeleteComponent } from './components/project-delete/project-delete.component';
import { ProgramComponent } from './components/program/program.component';
import { ProgramAddComponent } from './components/program-add/program-add.component';
import { ProgramEditComponent } from './components/program-edit/program-edit.component';
import { ProgramDeleteComponent } from './components/program-delete/program-delete.component';
import { CapabilityProjectComponent } from './components/capability-project/capability-project.component';
import { CapabilityProjectAddComponent } from './components/capability-project-add/capability-project-add.component';
import { CapabilityProjectDeleteComponent } from './components/capability-project-delete/capability-project-delete.component';
import { InformationComponent } from './components/information/information.component';
import { InformationAddComponent } from './components/information-add/information-add.component';
import { InformationDeleteComponent } from './components/information-delete/information-delete.component';
import { InformationEditComponent } from './components/information-edit/information-edit.component';
import { CapabilityInformationComponent } from './components/capability-information/capability-information.component';
import { CapabilityInformationAddComponent } from './components/capability-information-add/capability-information-add.component';
import { CapabilityInformationEditComponent } from './components/capability-information-edit/capability-information-edit.component';
import { CapabilityInformationDeleteComponent } from './components/capability-information-delete/capability-information-delete.component';

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
  { path: 'strategies', component: StrategyComponent, canActivate: [RouterGuard] },
  { path: 'capstrategyitems', component: CapabilityStrategyitemsComponent, canActivate: [RouterGuard] }
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
    UserAddComponent,
    UserEditComponent,
    UserDeleteComponent,
    BusinessprocessComponent,
    BusinessprocessAddComponent,
    BusinessprocessEditComponent,
    BusinessprocessDeleteComponent,
    CapabilityBusinessprocessComponent,
    CapabilityBusinessprocessAddComponent,
    CapabilityBusinessprocessDeleteComponent,
    ResourceEditComponent,
    CapabilityResourceComponent,
    CapabilityResourceDeleteComponent,
    CapabilityResourceAddComponent,
    ResourceDeleteComponent,
    CapabilityStrategyitemsComponent,
    CapabilityStrategyitemsAddComponent,
    CapabilityStrategyitemsEditComponent,
    CapabilityStrategyitemsDeleteComponent,
    CapabilityResourceEditComponent,
    ProjectComponent,
    ProjectAddComponent,
    ProjectEditComponent,
    ProjectDeleteComponent,
    ProgramComponent,
    ProgramAddComponent,
    ProgramEditComponent,
    ProgramDeleteComponent,
    CapabilityProjectComponent,
    CapabilityProjectAddComponent,
    CapabilityProjectDeleteComponent,
    InformationComponent,
    InformationAddComponent,
    InformationDeleteComponent,
    InformationEditComponent,
    CapabilityInformationComponent,
    CapabilityInformationAddComponent,
    CapabilityInformationEditComponent,
    CapabilityInformationDeleteComponent
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
    NgxPrintModule,
    HttpClientXsrfModule.withOptions({
      cookieName: 'XSRF-TOKEN',
      headerName: 'X-XSRF-TOKEN'
    })
  ],
  providers: [CapabilityService, NgxPrintModule, ResourceComponent,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }],
  bootstrap: [AppComponent]
})
export class AppModule { }
