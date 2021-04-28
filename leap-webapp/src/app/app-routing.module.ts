import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CapabilityComponent } from './capability/capability.component';
import { CapabilityAddComponent} from "./capability-add/capability-add.component";

const routes: Routes = [
  { path: 'capabilities', component: CapabilityComponent},
  { path: 'capabilities/add', component: CapabilityAddComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
