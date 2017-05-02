import {Component} from '@angular/core';
import {LoginService} from './services/login.service';
import {Router} from '@angular/router';

@Component({
    selector: 'app-study-tips',
    templateUrl:'./app/app.component.html',
    providers: [LoginService]
})
export class AppComponent {
    constructor(router:Router,loginService:LoginService) {
        router.events.subscribe(e => {
            if(e.url !== '/authenticate') {
                if(!loginService.isAuthenticated()) {
                    router.navigate(['/authenticate']);
                } else {
                    loginService.sendLoginSuccess();
                }
            }else {
                if(loginService.isAuthenticated()){
                    router.navigate(['/home']);
                }
            }
        });
    }
}
