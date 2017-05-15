import {Tip} from "./Tip";
import {User} from "./user";

export class BookTip{
    id:number;
    text:string;
    title:string;
    author:string;
    edition:string;
    tipLevel:string;
    tip:Tip;
    user:User;

    constructor(webTip?:{
        id?:number,
        text?:string,
        title?:string,
        author?:string,
        edition?:string,
        tipLevel?:string,
        tip?:Tip,
        user?:User
    }){}

}