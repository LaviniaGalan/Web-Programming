import { Component, OnInit } from '@angular/core';
import { User } from '../shared/user.model';
import { UserService } from '../shared/user.service';

@Component({
  selector: 'app-get-all-users-page',
  templateUrl: './get-all-users-page.component.html',
  styleUrls: ['./get-all-users-page.component.css']
})
export class GetAllUsersPageComponent implements OnInit {
  users: User[] = [];

  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
    this.getAll();
  }

  getAll(): void {
    this.userService.getAll().subscribe(
      (res: User[]) => {
        this.users = res;
      },
      (error) => {
        window.alert(error);
      }
    );
  }
}
