import { Component, OnInit } from '@angular/core';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.page.html',
  styleUrls: ['./register.page.scss'],
})
export class RegisterPage implements OnInit {
  valid: boolean = false;

  constructor() { }

  ngOnInit() {
  }

  isValid(form: NgForm){
    if(form.value.username != "" &&
        form.value.password != ""&&
        form.value.nickname != ""){
      this.valid = true;
      console.log("work!");
      console.log(form.value.nickname.toString());
    }

    else{
      console.log(form.value.nickname.toString());
      this.valid = false;
    }
  }

  register(form: NgForm){
    console.log("submit");
  }
}
