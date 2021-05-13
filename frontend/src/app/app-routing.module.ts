import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CapabilityAddComponent } from "./components/capability-add/capability-add.component";
import { CapabilityDeleteComponent } from './components/capability-delete/capability-delete.component';
import { CapabilityEditComponent } from './components/capability-edit/capability-edit.component';
import { CapabilityComponent } from './components/capability/capability.component';
import { CompanyRequestComponent } from './components/company-request/company-request.component';
import { EnvironmentAddComponent } from './components/environment-add/environment-add.component';
import { EnvironmentDeleteComponent } from './components/environment-delete/environment-delete.component';
import { EnvironmentEditComponent } from './components/environment-edit/environment-edit.component';
import { EnvironmentComponent } from './components/environment/environment.component';
import { ExportComponent } from './components/export/export.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterFormUseradminComponent } from './components/register-form-useradmin/register-form-useradmin.component';
import { RegisterFormComponent } from './components/register-form/register-form.component';

const routes: Routes = [
  { path: 'environments', component: EnvironmentComponent },
  { path: 'environments/create', component: EnvironmentAddComponent },
  { path: 'environments/:envName/update', component: EnvironmentEditComponent },
  { path: 'environments/:envName/delete', component: EnvironmentDeleteComponent },
  { path: 'environments/:envName/capabilities', component: CapabilityComponent },
  { path: 'environments/:envName/capabilities/create', component: CapabilityAddComponent },
  { path: 'environments/:envName/capabilities/:capName/delete', component: CapabilityDeleteComponent },
  { path: 'environments/:envName/capabilities/:capName/update', component: CapabilityEditComponent },
  { path: 'register-useradmin', component: RegisterFormUseradminComponent },
  { path: 'register', component: RegisterFormComponent },
  { path: 'export', component: ExportComponent },
  { path: 'login', component: LoginComponent },
  { path: 'company/register', component: CompanyRequestComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }