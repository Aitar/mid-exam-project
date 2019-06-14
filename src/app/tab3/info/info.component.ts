import { Component, OnInit } from '@angular/core';
import {User} from '../../../assets/entity/User';
import {Router} from '@angular/router';
import {DataService} from '../../data.service';

@Component({
  selector: 'app-info',
  templateUrl: './info.component.html',
  styleUrls: ['./info.component.scss'],
})
export class InfoComponent implements OnInit {

  constructor(private router: Router, private dataService: DataService) { }

  ngOnInit() {}

  //测试数据
  user: User = this.dataService.currUser;



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
