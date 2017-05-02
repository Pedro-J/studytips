import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {UserService} from '../../services/user.service';
import {User} from "../../models/user";


@Component({
    selector:'user-list',
    templateUrl:'./app/components/user/user-list.component.html',
})
export class UserListComponent implements OnInit{
    public users:User[];
    public router: Router;
    public userService:UserService;
    public mensagem:string = "";

    public totalItems: number = 64;
    public currentPage: number = 1;
    public itemsPerPage: number = 5;

    constructor(router: Router, userService:UserService) {
        this.users = [];
        this.userService = userService;
        this.router = router;
        //userService.getAll().subscribe( users => this.users = users);
    }

    ngOnInit():void{
        this.userService.getAllByPage(this.getPageServer(), this.itemsPerPage).subscribe(
            (res) => {
                this.users = res.content;
                this.totalItems = res.totalElements;
            }
        )
    }

    public setPage(pageNo: number): void {
        this.currentPage = pageNo;
    }

    public getPageServer(){
        return (this.currentPage - 1);
    }

    public pageChanged(event: any): void {
        console.log('Page changed to: ' + event.page);
        console.log('Number items per page: ' + event.itemsPerPage);

        let pageNumber = event.page - 1;
        this.userService.getAllByPage(pageNumber, event.itemsPerPage).subscribe(
            (res) => {
                this.users = res.content;
                this.totalItems = res.totalElements;
            }
        );
    }

    public onDetailUser(event:Event, id:string):void {
        event.preventDefault();
        this.router.navigate(['/user/detail',id]);
    }

    public onEditUser(event:Event,id:string):void {
        event.preventDefault();
        this.router.navigate(['/user/edit',id]);
    }

    public onDeleteUser(user:User):void {
        this.userService
        .delete(user)
        .subscribe(
            () => {

                let novosUsers = this.users.slice(0);
                let indice = novosUsers.indexOf(user);
                novosUsers.splice(indice, 1);
                this.users = novosUsers;
                this.mensagem = 'User has been deleted.';
            },
            erro => {
                console.log(erro);
                this.mensagem = "It hasn't been possible to delete the user.";
            }
        );
    }

    public onAddUser(event:Event){
        event.preventDefault();
        this.router.navigate(['/user/add']);
    }
}
