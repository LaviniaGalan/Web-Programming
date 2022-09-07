import { Component, OnInit } from '@angular/core';
import {User} from '../shared/user.model';
import {UserService} from '../shared/user.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-add-user-page',
  templateUrl: './add-user-page.component.html',
  styleUrls: ['./add-user-page.component.css']
})
export class AddUserPageComponent implements OnInit {
  addedUser: User = new User();

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
  }

  addUser(form): void {
    this.userService.addUser(form.value).subscribe(
      (result: object) => {
        window.alert('User was added!');
        this.router.navigate(['/home-page']);
      },
      error => {
        window.alert(error);
        this.router.navigate(['/home-page']);
      });
  }
}
