import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {ToastController} from '@ionic/angular';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {
  valid: boolean = false;
  errortext: string = null;

  constructor(private http: HttpClient,private router: Router, public toastController: ToastController) { }

  ngOnInit() {
  }

  isValid(form: NgForm){
    if(form.value.username != "" && form.value.password != "")
      this.valid = true;
    console.log("isValid work!")
  }

  loginCheck(form: NgForm) {
    console.log(form);
    if (this.valid) {
      let request = {
        "username": form.value.username,
        "password": form.value.password
      };
      console.log(request);
      this.http.post("http://localhost:8088/untitled2_war_exploded/user", request)
          .subscribe(
          (data : any) =>{
            console.log(data.access_token);
            this.router.navigateByUrl("user");
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
