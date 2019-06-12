import { Component, OnInit } from '@angular/core';
import {NgForm} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {ToastController} from '@ionic/angular';

@Component({
  selector: 'app-register',
  templateUrl: './register.page.html',
  styleUrls: ['./register.page.scss'],
})
export class RegisterPage implements OnInit {
  valid: boolean = false;
  errortext: string = null;

  constructor(private http: HttpClient, public toastController: ToastController) { }

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
    if (this.valid) {
      let request = {
        "username": form.value.username,
        "password": form.value.password,
        "nickname": form.value.nickname
      };
      console.log(request);
      this.http.post("http://localhost:8088/untitled2_war_exploded/user", request)
          .subscribe(
              (data : any) =>{
                console.log(data);
                // this.router.navigateByUrl("user");
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
