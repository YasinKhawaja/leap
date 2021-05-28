import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CapabilityApplication } from './classes/capability-application/capability-application';
import { CapabilityApplicationAddComponent } from './components/capability-application-add/capability-application-add.component';
import { CapabilityApplicationDeleteComponent } from './components/capability-application-delete/capability-application-delete.component';
import { CapabilityApplicationEditComponent } from './components/capability-application-edit/capability-application-edit.component';
import { CapabilityDeleteComponent } from './components/capability-delete/capability-delete.component';
import { CapabilityEditComponent } from './components/capability-edit/capability-edit.component';
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
import { ResourceComponent } from './components/resource/resource.component';

const routes: Routes = [
  { path: 'environments', component: EnvironmentComponent },
  { path: 'environments/create', component: EnvironmentAddComponent },
  { path: 'environments/:envId/update', component: EnvironmentEditComponent },
  { path: 'environments/:envId/delete', component: EnvironmentDeleteComponent },
  { path: 'environments/:envId/capabilities', component: CapabilityComponent },
  { path: 'environments/:envId/capabilities/:capId/update', component: CapabilityEditComponent },
  { path: 'environments/:envId/capabilities/:capId/delete', component: CapabilityDeleteComponent },
  { path: 'register-useradmin', component: RegisterFormUseradminComponent },
  { path: 'register', component: RegisterFormComponent },
  { path: 'export', component: ExportComponent },
  { path: 'login', component: LoginComponent },
  { path: 'company/register', component: CompanyRequestComponent },
  { path: 'itapplication/', component: ItapplicationComponent },
  { path: 'itapplication/:itapplicationId/delete', component: ItapplicationDeleteComponent },
  { path: 'itapplication/:itapplicationId/update', component: ItapplicationEditComponent },
  { path: 'itapplication/create', component: ItapplicationAddComponent },
  { path: 'capability-application/:capabilityId', component: CapabilityApplication },
  { path: 'capability-application/:capabilityId/create', component: CapabilityApplicationAddComponent },
  { path: 'capability-application/:capabilityId/update/:capabilityapplicationId', component: CapabilityApplicationEditComponent },
  { path: 'capability-application/:capabilityId/delete/:capabilityapplicationId', component: CapabilityApplicationDeleteComponent },
  { path: 'resources', component: ResourceComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }