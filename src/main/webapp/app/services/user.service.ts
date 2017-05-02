import {Injectable} from '@angular/core';
import {Http, Response, Headers} from '@angular/http';
import * as AppUtils from '../utils/app.utils';
import {Observable} from 'rxjs/Observable';
import {User} from '../models/user';

@Injectable()
export class UserService {
    http:Http;
    constructor(http:Http) {
        this.http = http;
    }
    getAll():Observable<User[]> {
        return this.http.get(AppUtils.BACKEND_API_ROOT_URL+'/users').map((res:Response) => res.json());
    }

    getAllByPage(currentPage:number, pageSize:number):Observable<any> {
        let params = 'page='+currentPage+'&'+'size='+pageSize+'&sort=login,asc';
        return this.http.get(AppUtils.BACKEND_API_ROOT_URL+'/users?'+params).map((res:Response) => res.json());
    }

    getById(id:string):Observable<User> {
        return this.http.get(AppUtils.BACKEND_API_ROOT_URL+'/users/'+id).map((res:Response) => res.json() );
    }
    getProfiles():Observable<Array<string>> {
        return this.http.get(AppUtils.BACKEND_API_ROOT_URL+'/users/profiles').map((res:Response) => res.json());
    }
    saveUser(user:User):Observable<User> {
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');

        if(user.id) {
            user.authorities = null;
            return this.http.put(AppUtils.BACKEND_API_ROOT_URL + '/users/' + user.id, JSON.stringify(user), {headers: headers})
                .map((res: Response) => {
                    return new User(res.json());
                });
        }else{
            return this.http.post(AppUtils.BACKEND_API_ROOT_URL+'/users/add', JSON.stringify(user), {headers: headers})
                .map((res: Response) => {
                    return new User(res.json());
                });
        }
    }

    delete(user:User):Observable<Response>{
        console.log('deletando id: '+ user.id);
        return this.http.delete(AppUtils.BACKEND_API_ROOT_URL+ '/users/' + user.id);
    }
}
