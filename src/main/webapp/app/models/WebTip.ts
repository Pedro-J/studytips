import {User} from "./user";
import {Tip} from "./Tip";
import {Link} from "./Link";

export class WebTip{
    id:number;
    text:string;
    tipLevel:string;
    tip:Tip;
    user:User;
    links:Array<Link>

    constructor(webTip?:{
        id?:number,
        text?:string,
        tipLevel?:string,
        tip?:Tip,
        user?:User
    }){

    }
}
