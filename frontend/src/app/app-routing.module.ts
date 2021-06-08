import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CapabilityApplication } from './classes/capability-application/capability-application';
import { BusinessprocessAddComponent } from './components/businessprocess-add/businessprocess-add.component';
import { BusinessprocessDeleteComponent } from './components/businessprocess-delete/businessprocess-delete.component';
import { BusinessprocessEditComponent } from './components/businessprocess-edit/businessprocess-edit.component';
import { BusinessprocessComponent } from './components/businessprocess/businessprocess.component';
import { CapabilityApplicationAddComponent } from './components/capability-application-add/capability-application-add.component';
import { CapabilityApplicationDeleteComponent } from './components/capability-application-delete/capability-application-delete.component';
import { CapabilityApplicationEditComponent } from './components/capability-application-edit/capability-application-edit.component';
import { CapabilityBusinessprocessAddComponent } from './components/capability-businessprocess-add/capability-businessprocess-add.component';
import { CapabilityBusinessprocessDeleteComponent } from './components/capability-businessprocess-delete/capability-businessprocess-delete.component';
import { CapabilityBusinessprocessComponent } from './components/capability-businessprocess/capability-businessprocess.component';
import { CapabilityDeleteComponent } from './components/capability-delete/capability-delete.component';
import { CapabilityEditComponent } from './components/capability-edit/capability-edit.component';
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
import { ExportComponent } from './components/export/export.component';
import { ItapplicationAddComponent } from './components/itapplication-add/itapplication-add.component';
import { ItapplicationDeleteComponent } from './components/itapplication-delete/itapplication-delete.component';
import { ItapplicationEditComponent } from './components/itapplication-edit/itapplication-edit.component';
import { ItapplicationComponent } from './components/itapplication/itapplication.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterFormUseradminComponent } from './components/register-form-useradmin/register-form-useradmin.component';
import { RegisterFormComponent } from './components/register-form/register-form.component';
import { ResetpasswordConfirmComponent } from './components/resetpassword-confirm/resetpassword-confirm.component';
import { ResetpasswordComponent } from './components/resetpassword/resetpassword.component';
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
import { RouterGuard } from './services/guard/router.guard';
import { JwtService } from './services/jwt/jwt.service';

const routes: Routes = [
  { path: 'environments', component: EnvironmentComponent, canActivate: [RouterGuard] },
  { path: 'environments/create', component: EnvironmentAddComponent, canActivate: [RouterGuard] },
  { path: 'environments/:envId/update', component: EnvironmentEditComponent, canActivate: [RouterGuard] },
  { path: 'environments/:envId/delete', component: EnvironmentDeleteComponent, canActivate: [RouterGuard] },
  { path: 'capabilities', component: CapabilityComponent, canActivate: [RouterGuard] },
  { path: 'capabilities/update', component: CapabilityEditComponent, canActivate: [RouterGuard] },
  { path: 'capabilities/:capId/delete', component: CapabilityDeleteComponent, canActivate: [RouterGuard] },
  { path: 'register-useradmin', component: RegisterFormUseradminComponent },
  { path: 'register', component: RegisterFormComponent },
  { path: 'export', component: ExportComponent, canActivate: [RouterGuard] },
  { path: 'login', component: LoginComponent },
  { path: 'company/register', component: CompanyRequestComponent },
  { path: 'strategies/', component: StrategyComponent, canActivate: [RouterGuard] },
  { path: 'strategies/:strId/delete', component: StrategyDeleteComponent, canActivate: [RouterGuard] },
  { path: 'strategies/:strId/update', component: StrategyEditComponent, canActivate: [RouterGuard] },
  { path: 'strategies/create', component: StrategyAddComponent, canActivate: [RouterGuard] },
  { path: 'strategies/:strId/strategyItems', component: StrategyItemsComponent, canActivate: [RouterGuard] },
  { path: 'strategies/:strId/strategyItems/create', component: StrategyItemsAddComponent, canActivate: [RouterGuard] },
  { path: 'strategies/:strId/strategyItems/:strItemId/update', component: StrategyItemsEditComponent, canActivate: [RouterGuard] },
  { path: 'strategies/:strId/strategyItems/:strItemId/delete', component: StrategyItemsDeleteComponent, canActivate: [RouterGuard] },
  { path: 'itapplication/', component: ItapplicationComponent, canActivate: [RouterGuard] },
  { path: 'itapplication/:itapplicationId/delete', component: ItapplicationDeleteComponent, canActivate: [RouterGuard] },
  { path: 'itapplication/:itapplicationId/update', component: ItapplicationEditComponent, canActivate: [RouterGuard] },
  { path: 'itapplication/create', component: ItapplicationAddComponent, canActivate: [RouterGuard] },
  { path: 'capability-application/', component: CapabilityApplication, canActivate: [RouterGuard] },
  { path: 'capability-application/create', component: CapabilityApplicationAddComponent, canActivate: [RouterGuard] },
  { path: 'capability-application/update/:capabilityapplicationId', component: CapabilityApplicationEditComponent, canActivate: [RouterGuard] },
  { path: 'capability-application/delete/:capabilityapplicationId', component: CapabilityApplicationDeleteComponent, canActivate: [RouterGuard] },
  { path: 'resources', component: ResourceComponent, canActivate: [RouterGuard] },
  { path: 'capability-resource', component: CapabilityResourceComponent },
  { path: 'resetpassword', component: ResetpasswordComponent },
  { path: 'resetpassword/confirm', component: ResetpasswordConfirmComponent },
  { path: 'add-user', component: UserAddComponent },
  { path: 'edit-user/:userId', component: UserEditComponent },
  { path: 'del-user/:userId', component: UserDeleteComponent },
  { path: 'businessprocess', component: BusinessprocessComponent },
  { path: 'businessprocess/add', component: BusinessprocessAddComponent },
  { path: 'businessprocess/edit/:businessprocessid', component: BusinessprocessEditComponent },
  { path: 'businessprocess/delete/:businessprocessid', component: BusinessprocessDeleteComponent },
  { path: 'capability-businessprocess', component: CapabilityBusinessprocessComponent },
  { path: 'capability-businessprocess/add', component: CapabilityBusinessprocessAddComponent },
  { path: 'capability-businessprocess/delete/:capabilitybusinessprocessid', component: CapabilityBusinessprocessDeleteComponent },
  { path: 'capstrategyitems', component: CapabilityStrategyitemsComponent, canActivate: [RouterGuard] },
  { path: 'capstrategyitems/create', component: CapabilityStrategyitemsAddComponent, canActivate: [RouterGuard] },
  { path: 'capstrategyitems/update/:capabilityStrategyItemID', component: CapabilityStrategyitemsEditComponent, canActivate: [RouterGuard] },
  { path: 'capstrategyitems/delete/:capabilityStrategyItemID', component: CapabilityStrategyitemsDeleteComponent, canActivate: [RouterGuard] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [JwtService]
})
export class AppRoutingModule { }
