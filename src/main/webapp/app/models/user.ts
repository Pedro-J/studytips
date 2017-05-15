
export class User {
    id: number;
    login: string;
    firstName:string;
    lastName:string;
    birthDate:string;
    saveDate:string;
    password:string;
    status:string;
    profile: string;
    authorities:Array<string>;

    constructor(user?: {
        id?: number,
        login?: string,
        firstName?:string,
        lastName?:string,
        birthDate?:string,
        saveDate?:string,
        password?:string,
        status?:string,
        profile?:string,
        authorities?:Array<string>
    }){}
}