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

const routes: Routes = [
  { path: 'capabilities', component: CapabilityComponent},
  { path: 'capabilities/add', component: CapabilityAddComponent},
  { path: 'register-useradmin', component: RegisterFormUseradminComponent },
  { path: 'register', component: RegisterFormComponent },
  { path: 'environments', component: EnvironmentComponent },
  { path: 'environments/add', component: EnvironmentAddComponent},
  { path: 'environments/:id/edit', component: EnvironmentEditComponent },
  { path: 'environments/:id/delete', component: EnvironmentDeleteComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
