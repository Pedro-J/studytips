/**
 * Created by comp-dev on 5/14/17.
 */
export class Area{
    id:number;
    name:string;
    areaParentId:number;

    constructor(area?:{
        id?:number,
        name?:string,
        areaParentId?:number
    }){}
}
