import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {generate} from 'rxjs';
import {ModalService, ToastService} from 'ng-zorro-antd-mobile';
import {DataService} from '../../data.service';
import {HttpClient} from '@angular/common/http';


@Component({
  selector: 'app-info-edit',
  templateUrl: './info-edit.component.html',
  styleUrls: ['./info-edit.component.scss'],
})
export class InfoEditComponent implements OnInit {
  nickname: string = null;
  gender: string = null;
  singleArea = ['男','女','保密'];
  oldPassword: string = null;
  newPassword: string = null;



  constructor(private router: Router,
              private _modal: ModalService,
              private _toast: ToastService,
              private dataService: DataService,
              private http: HttpClient
  ) { }

  ngOnInit() {
    console.log(this.nickname);
    console.log(this.gender);
    console.log(this.oldPassword);
    console.log(this.newPassword);
  }

  showAlert(){
    ModalService.alert('确定要离开？', '未保存的修改将丢失', [
      { text: '否', onPress: () => {return} },
      { text: '是', onPress: () => {this.router.navigateByUrl("user/tabs/tab3");} }
    ]);
  }

  onOk3(result) {
    console.log(result);
    console.log(this.gender);
  }

  onLeftClick() {
    if(this.nickname != null || this.gender != null || this.newPassword != null || this.oldPassword != null){
      console.log(0);
      this.showAlert();
    }else {
      this.router.navigateByUrl("user/tabs/tab3");
      console.log(1);
    }
  }

  /**
  显示错误提示
   **/
  failToast() {
    const toast = ToastService.fail('Load failed !!!', 1000);
  }

  submit() {
    this.http.post()
  }
}
