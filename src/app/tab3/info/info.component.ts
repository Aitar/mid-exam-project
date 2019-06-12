import { Component, OnInit } from '@angular/core';
import {User} from '../../../assets/entity/User';
import {Router} from '@angular/router';

@Component({
  selector: 'app-info',
  templateUrl: './info.component.html',
  styleUrls: ['./info.component.scss'],
})
export class InfoComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {}

  //测试数据
  user: User =     {
    username: '593939245@qq.com',
    password: 'huang980518',
    nickname: '芒果西米露',
    lastLogin: '2019-6-12 14:18',
    wNumPerD: 25,
    gender: '男'
  };



  renderHeader2() {
    return 'Customized Right Side（Empty Content / Text / Image）';
  }

  onClick(){
    console.log("click");
    this.router.navigateByUrl('edit')
  }

  /**
   * 上传头像的方法
   */
  changeAvater() {
    console.log("changeAvater");
  }

  onLeftClick() {

  }

  exit() {
    console.log("退出登录");
  }
}
