import {Component} from '@angular/core';
import {AccountEventsService} from '../../services/account.events.service';
import {LoginService} from '../../services/login.service';

@Component({
    selector: 'nav-bar',
    templateUrl: './app/components/nav-bar/nav-bar.component.html',
    providers: [LoginService],
})
export class NavBarComponent {
    authenticated:boolean;
    loginService:LoginService;

    constructor(accountEventService:AccountEventsService,loginService:LoginService) {
        this.loginService = loginService;
        accountEventService.subscribe((account) => {
            if(!account.authenticated) {
                this.authenticated = false;
                this.loginService.logout(false);
            } else {
                this.authenticated = true;
            }
        });
    }
    logout(event:Event):void {
        event.preventDefault();
        this.loginService.logout();
    }
}
