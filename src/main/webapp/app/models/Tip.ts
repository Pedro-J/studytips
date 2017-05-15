import {Area} from "./Area";

export class Tip{
    id:number;
    title:string;
    description:string;
    area:Area;

    constructor(tip?:{
        id?:number,
        title?:string,
        description?:string,
        area?:Area
    }){}
}

