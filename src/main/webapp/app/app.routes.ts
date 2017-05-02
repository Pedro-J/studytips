import {Routes, RouterModule} from '@angular/router';
import {ModuleWithProviders} from '@angular/core';
import {LoginComponent} from './components/login/login.component';
import {UserListComponent} from './components/user/user-list.component';
import {UserFormComponent} from './components/user/user-form.component';
import {HomeComponent} from './components/home/home.component';

export const routes:Routes = [
    {path: 'authenticate', component: LoginComponent},
    {path: 'home', component: HomeComponent},
    {path: 'users', component: UserListComponent},
    {path: 'user/add', component: UserFormComponent},
    {path: 'user/edit/:id', component: UserFormComponent},
    {path: 'user/detail/:id', component: UserFormComponent},
    {path: '', component: LoginComponent}
];

export const RoutesModule: ModuleWithProviders = RouterModule.forRoot(routes);

