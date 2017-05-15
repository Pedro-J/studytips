import {Tip} from "./Tip";
import {User} from "./user";

export class UserTip{
    id:number;
    text:string;
    tipLevel:string;
    tip:Tip;
    user:User;

    constructor(bookTip?:{
        id?:number,
        text?:string,
        tipLevel?:string,
        tip?:Tip,
        user?:User
    }){

    }
}
