import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ToastController} from '@ionic/angular';
import {Router} from '@angular/router';
import {ToastService} from 'ng-zorro-antd-mobile';

@Component({
  selector: 'app-register',
  templateUrl: './register.page.html',
  styleUrls: ['./register.page.scss'],
})
export class RegisterPage implements OnInit {
  valid: boolean = false;
  errortext: string = null;
  nickname: string = "";
  gender: string;
  password: string = "";
  username: string = "";
  singleArea = ['男','女','保密'];

  constructor(private http: HttpClient, public toastController: ToastController,private router: Router, private _toast: ToastService) { }

  ngOnInit() {
  }

  isValid():boolean{
    if(this.username != "" &&
        this.password != ""&&
        this.nickname != ""){
      return true;
      console.log("valid!");
      console.log(this.nickname.toString());
    }

    else{
      console.log(this.nickname.toString());
      return false;
    }
  }

  register(){
    console.log("submit");
    if (this.isValid()) {
      let request = {
        "username": this.username,
        "password": this.password,
        "nickname": this.nickname,
        "gender": this.gender
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
    }else {
        const toast = ToastService.fail('请填写完毕再提交', 1000);
      return
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

    onLeftClick() {
        this.router.navigateByUrl("");
    }
}
