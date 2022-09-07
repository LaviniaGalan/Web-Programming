import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomePageComponent } from './home-page/home-page.component';
import { AddUserPageComponent } from './add-user-page/add-user-page.component';
import { GetAllUsersPageComponent } from './get-all-users-page/get-all-users-page.component';
import { DeleteUserPageComponent } from './delete-user-page/delete-user-page.component';
import { UpdateUserPageComponent } from './update-user-page/update-user-page.component';
import { FilterUsersPageComponent } from './filter-users-page/filter-users-page.component';

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    AddUserPageComponent,
    GetAllUsersPageComponent,
    DeleteUserPageComponent,
    UpdateUserPageComponent,
    FilterUsersPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
