export class Word {
    id: number;
    en: string;
    zh: string;
    wrongTime: number; //该单词错误的次数

    constructor(id: number, en: string, zh: string, wrongTime: number) {
        this.id = id;
        this.en = en;
        this.zh = zh;
        this.wrongTime = wrongTime;
    }
}
