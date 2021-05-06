import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CapabilityComponent } from './capability/capability.component';
import { CapabilityAddComponent} from "./capability-add/capability-add.component";
import { RegisterFormUseradminComponent } from './components/register-form-useradmin/register-form-useradmin.component';
import { RegisterFormComponent } from './components/register-form/register-form.component';
import { EnvironmentComponent } from './components/environment/environment.component';
import { EnvironmentEditComponent } from './components/environment-edit/environment-edit.component';
import { EnvironmentAddComponent } from './components/environment-add/environment-add.component';
import { EnvironmentDeleteComponent } from './components/environment-delete/environment-delete.component';
import { CapabilityDeleteComponent } from './capability-delete/capability-delete.component';
import { CapabilityEditComponent } from './capability-edit/capability-edit.component';
import { ExportComponent } from './export/export.component';

const routes: Routes = [
  { path: 'capabilities', component: CapabilityComponent},
  { path: 'capabilities/add', component: CapabilityAddComponent},
  { path: 'register-useradmin', component: RegisterFormUseradminComponent },
  { path: 'register', component: RegisterFormComponent },
  { path: 'environments', component: EnvironmentComponent },
  { path: 'environments/add', component: EnvironmentAddComponent},
  { path: 'environments/:id/edit', component: EnvironmentEditComponent },
  { path: 'environments/:id/delete', component: EnvironmentDeleteComponent },
  { path: 'capabilities/delete', component: CapabilityDeleteComponent},
  { path: 'capabilities/delete/:name', component: CapabilityDeleteComponent},
  { path: 'capabilities/edit/:name', component: CapabilityEditComponent},
  { path: 'export', component: ExportComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
