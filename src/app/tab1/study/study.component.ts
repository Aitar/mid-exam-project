import {Component, OnInit} from '@angular/core';
import {Word} from '../../../assets/entity/Word';
import {mockWords} from '../../../assets/mock-data/mock-words';
import {Router} from '@angular/router';

@Component({
  selector: 'app-study',
  templateUrl: './study.component.html',
  styleUrls: ['./study.component.scss'],
})
export class StudyComponent implements OnInit {
  words: Word[] = []; //将要学习的单词数组
  curWord: Word;
  studied: number = 0; //已学习的单词数量

  constructor(private router: Router) { }

  /**
   * 根据用户是否知道这个单词来确定复习的次数，并跳转到下一个单词
   * @param isknow
   */
  isKnow(isknow: boolean){
    if(this.studied == 5)   //测试代码，当等于5时跳转到复习页面
      this.router.navigateByUrl("review");
    if(!isknow)
      this.curWord.wrongTime+=2;
    this.curWord.wrongTime+=2;  //至少复习两次
    this.studied++;
    this.curWord = this.words[this.studied];
  }
  
  ngOnInit() {
    this.words = mockWords;
    this.curWord = this.words[0];
  }

}
