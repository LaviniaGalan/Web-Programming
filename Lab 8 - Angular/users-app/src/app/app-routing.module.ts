import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {GetAllUsersPageComponent} from './get-all-users-page/get-all-users-page.component';
import {DeleteUserPageComponent} from './delete-user-page/delete-user-page.component';
import {HomePageComponent} from './home-page/home-page.component';
import {AddUserPageComponent} from './add-user-page/add-user-page.component';
import {UpdateUserPageComponent} from './update-user-page/update-user-page.component';
import {FilterUsersPageComponent} from './filter-users-page/filter-users-page.component';

const routes: Routes = [
  {path: '', redirectTo: '/home-page', pathMatch: 'full' },
  {path: 'get-all-users-page', component: GetAllUsersPageComponent},
  {path: 'delete-user-page', component: DeleteUserPageComponent},
  {path: 'add-user-page', component: AddUserPageComponent},
  {path: 'home-page', component: HomePageComponent},
  {path: 'update-user-page', component: UpdateUserPageComponent},
  {path: 'filter-users-page', component: FilterUsersPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
