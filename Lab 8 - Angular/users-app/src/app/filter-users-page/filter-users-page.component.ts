import { Component, OnInit } from '@angular/core';
import {User} from '../shared/user.model';
import {UserService} from '../shared/user.service';

@Component({
  selector: 'app-filter-users-page',
  templateUrl: './filter-users-page.component.html',
  styleUrls: ['./filter-users-page.component.css']
})
export class FilterUsersPageComponent implements OnInit {
  filteredUsers: User[] = [];
  filterBy: string;
  filterValue: string;
  previousFilterType = 'not filtered';
  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }

  filterUsers(): void{
    this.userService.filterUsers(this.filterBy, this.filterValue).subscribe(
      (res: User[]) => {
        this.filteredUsers = res;
      },
      (error) => {
        window.alert(error);
      }
    );
    this.previousFilterType = this.filterBy;
  }
}
