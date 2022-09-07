import { Component, OnInit } from '@angular/core';
import {User} from '../shared/user.model';
import {UserService} from '../shared/user.service';

@Component({
  selector: 'app-update-user-page',
  templateUrl: './update-user-page.component.html',
  styleUrls: ['./update-user-page.component.css']
})
export class UpdateUserPageComponent implements OnInit {
  users: User[];
  selectedUser: User = {userid: null, name: null, username: null, password: null, age: null, role: null, email: null, webpage: null};
  newUser: User = {userid: null, name: null, username: null, password: null, age: null, role: null, email: null, webpage: null};

  onUpdate = false;
  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
    this.onUpdate = false;
    this.userService.getAll().subscribe((users: User[]) => {
      this.users = users;
      console.log(this.users);
    });
  }

  updateUser(form): void {
    this.newUser = form.value;
    this.newUser.userid = this.selectedUser.userid;
    this.userService.updateUser(form.value).subscribe(
      (result: object) => {
        window.alert('User updated!');
        this.ngOnInit();
      },
      error => {
        window.alert(error);
        this.ngOnInit();
      });
  }

  setSelectedUser(user: User): void {
    this.onUpdate = true;
    this.selectedUser = user;
  }
}
