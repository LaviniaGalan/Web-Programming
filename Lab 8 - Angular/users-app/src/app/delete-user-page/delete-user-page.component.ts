import { Component, OnInit } from '@angular/core';
import {User} from '../shared/user.model';
import {UserService} from '../shared/user.service';

@Component({
  selector: 'app-delete-user-page',
  templateUrl: './delete-user-page.component.html',
  styleUrls: ['./delete-user-page.component.css']
})
export class DeleteUserPageComponent implements OnInit {
  users: User[];

  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
    this.userService.getAll().subscribe((users: User[]) => {
      this.users = users;
      console.log(this.users);
    });
  }

  deleteUser(id): void {
    this.userService.deleteUser(id).subscribe(
      (result: object) => {
        window.alert('User was deleted!');
        this.ngOnInit();
      },
      error => {
        window.alert(error);
        this.ngOnInit();
      });
  }
}
