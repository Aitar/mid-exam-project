export class User {
    username: string;
    password: string;
    nickname: string;
    lastLogin: string;  //最近登录时间
    wNumPerD: number;   //每天学习的单词数量
    gender: string;
    studied: number;


    constructor(username: string, password: string, nickname: string, lastLogin: string, wNumPerD: number, gender: string, studied: number) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.lastLogin = lastLogin;
        this.wNumPerD = wNumPerD;
        this.gender = gender;
        this.studied = studied;
    }
}
