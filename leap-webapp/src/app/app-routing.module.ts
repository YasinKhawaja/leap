import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CapabilityComponent } from './capability/capability.component';
import { CapabilityAddComponent} from "./capability-add/capability-add.component";
import { CapabilityDeleteComponent } from './capability-delete/capability-delete.component';
import { CapabilityEditComponent } from './capability-edit/capability-edit.component';

const routes: Routes = [
  { path: 'capabilities', component: CapabilityComponent},
  { path: 'capabilities/add', component: CapabilityAddComponent},
  { path: 'capabilities/delete', component: CapabilityDeleteComponent},
  { path: 'capabilities/delete/:name', component: CapabilityDeleteComponent},
  { path: 'capabilities/edit/:name', component: CapabilityEditComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
