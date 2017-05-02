//Angular
import { NgModule } from '@angular/core';
import { Http, XHRBackend, RequestOptions, HttpModule } from '@angular/http';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';
import { RouterModule } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

//Third
import { PaginationModule } from "ng2-bootstrap/ng2-bootstrap";

//App Essentials
import { AppComponent } from './app.component';
import { UtilsModule } from './utils/utils.module';
import { HmacHttpClient } from './utils/hmac-http-client';
import { RoutesModule } from './app.routes';

//Components
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { LoginComponent } from './components/login/login.component';
import { UserFormComponent } from './components/user/user-form.component';
import { UserListComponent } from './components/user/user-list.component';
import { ConfirmButtonComponent } from './components/confirm-button/confirm-button.component';
import { ImgButtonComponent } from './components/img-button/img-button.component';
import { HomeComponent } from "./components/home/home.component";

//Services
import { AccountEventsService } from './services/account.events.service';
import { LoginService } from './services/login.service';
import { UserService } from './services/user.service';


@NgModule({
    imports:[
        HttpModule,
        RouterModule,
        BrowserModule,
        RoutesModule,
        ReactiveFormsModule,
        FormsModule,
        UtilsModule,
        PaginationModule.forRoot()
    ],

    declarations:[
        AppComponent,
        HomeComponent,
        NavBarComponent,
        LoginComponent,
        UserFormComponent,
        UserListComponent,
        ConfirmButtonComponent,
        ImgButtonComponent
    ],

    providers: [
        LoginService,
        UserService,
        AccountEventsService,
        {provide: LocationStrategy, useClass: HashLocationStrategy},
        {
            provide: Http,
            useFactory: (xhrBackend: XHRBackend, requestOptions: RequestOptions, accountEventService: AccountEventsService) => {
               return new HmacHttpClient(xhrBackend, requestOptions, accountEventService);
            },
            deps: [XHRBackend, RequestOptions, AccountEventsService],
            multi: false
        }
    ],
    bootstrap:[
        AppComponent,
        NavBarComponent
    ]
})
export class AppModule { }
