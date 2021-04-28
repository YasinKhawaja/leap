import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { HttpClientModule } from '@angular/common/http'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CapabilityComponent } from './capability/capability.component';
import { CapabilityAddComponent } from './capability-add/capability-add.component';
import { CapabilityService } from './service/capability.service';

@NgModule({
  declarations: [
    AppComponent,
    CapabilityComponent,
    CapabilityAddComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [CapabilityService],
  bootstrap: [AppComponent]
})
export class AppModule { }
