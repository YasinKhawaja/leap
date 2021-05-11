import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CapabilityComponent } from './components/capability/capability.component';
import { CapabilityAddComponent} from "./components/capability-add/capability-add.component";
import { RegisterFormUseradminComponent } from './components/register-form-useradmin/register-form-useradmin.component';
import { RegisterFormComponent } from './components/register-form/register-form.component';
import { EnvironmentComponent } from './components/environment/environment.component';
import { EnvironmentEditComponent } from './components/environment-edit/environment-edit.component';
import { EnvironmentAddComponent } from './components/environment-add/environment-add.component';
import { EnvironmentDeleteComponent } from './components/environment-delete/environment-delete.component';
import { CapabilityDeleteComponent } from './components/capability-delete/capability-delete.component';
import { CapabilityEditComponent } from './components/capability-edit/capability-edit.component';
import { ExportComponent } from './components/export/export.component';
import { LoginComponent } from './components/login/login.component';
import { CompanyRequestComponent } from './components/company-request/company-request.component';



const routes: Routes = [
  { path: 'capabilities', component: CapabilityComponent},
  { path: 'capabilities/add', component: CapabilityAddComponent},
  { path: 'register-useradmin', component: RegisterFormUseradminComponent },
  { path: 'register', component: RegisterFormComponent },
  { path: 'environments', component: EnvironmentComponent },
  { path: 'environments/create', component: EnvironmentAddComponent},
  { path: 'environments/:id/update', component: EnvironmentEditComponent },
  { path: 'environments/:id/delete', component: EnvironmentDeleteComponent },
  { path: 'capabilities/delete', component: CapabilityDeleteComponent},
  { path: 'capabilities/delete/:name', component: CapabilityDeleteComponent},
  { path: 'capabilities/edit/:name', component: CapabilityEditComponent},
  { path: 'export', component: ExportComponent},
  { path: 'login', component: LoginComponent},
  { path: 'company/register', component: CompanyRequestComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
