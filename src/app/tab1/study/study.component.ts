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
  preWord: Word = new Word();
  studied: number = 0; //已学习的单词数量
  showZH: boolean = false;

  constructor(private router: Router) { }

  /**
   * 根据用户是否知道这个单词来确定复习的次数，并跳转到下一个单词
   * @param isknow
   */
  isKnow(isknow: boolean){
    if(this.studied == 5)   //测试代码，当等于5时跳转到复习页面
      this.router.navigateByUrl("user/tabs/tab1/review");
    if(!isknow)
      this.curWord.wrongTime+=2;
    this.curWord.wrongTime+=2;  //至少复习两次
    this.showZH = true;
  }


  next(){
    this.preWord = this.curWord;
    //如果太长了就把超过10的部分用...代替
    if(this.preWord.zh.length > 10){
      this.preWord.zh = this.preWord.zh.slice(0,9);
      this.preWord.zh.concat("...");
    }
    this.studied++;
    this.curWord = this.words[this.studied];
    this.showZH = false;
  }
  
  ngOnInit() {
    this.words = mockWords;
    this.curWord = this.words[0];
    this.preWord.en = "";
    this.preWord.zh = "";
    console.log(this.preWord);
  }

    onLeftClick() {
      this.router.navigateByUrl("user/tabs/tab1");
    }
}
