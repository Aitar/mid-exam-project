import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaderResponse, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';
import {ToastController} from '@ionic/angular';
import {NgForm} from '@angular/forms';
import {DataService} from '../data.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {
  valid: boolean = false;
  errortext: string = null;

  constructor(private http: HttpClient,
              private router: Router,
              public toastController: ToastController,
              private userService: DataService
  ) { }

  ngOnInit() {
  }

  isValid(form: NgForm){
    if(form.value.username != "" && form.value.password != "")
      this.valid = true;
    console.log("isValid work!")
  }

  loginCheck(form: NgForm) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'my-auth-token'
      })
    }

    if (this.valid) {

      let request = {
        "username": form.value.username,
        "password": form.value.password
      };
      console.log(request);

      this.http.post("http://localhost:8088/untitled2_war_exploded/user", request,{ observe: 'response' })
          .subscribe(
          (data) =>{
            console.log(data);
            this.userService.userLogin(data.status - 200);

          },
          (error: any) =>{
            console.log(error);
            this.errortext = error.error.description;
            this.presentToast();
          }
      );
    }
  }

  async presentToast() {
    const toast = await this.toastController.create({
      message: this.errortext,
      duration: 2000,
      showCloseButton: true,
      position: 'middle',
      closeButtonText:"close"
    });
    toast.present();
  }
}
