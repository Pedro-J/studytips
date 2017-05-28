import {Component} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {FormBuilder, Validators, FormGroup, FormControl } from '@angular/forms';
import {UserService} from '../../services/user.service';
import {User} from '../../models/user';
import {GlobalValidator} from "../../utils/GlobalValidation";

@Component({
    selector:'user-form',
    templateUrl:'./app/components/user/user-form.component.html',
})
export class UserFormComponent {
    userForm:FormGroup;
    mailAddress: FormControl;

    user:User;
    passwordConfirm:string;
    profiles:Array<string>;

    private sub:any;
    private _modoEdicao:boolean;
    private _modoDetail:boolean;

    constructor(private router: Router, private userService:UserService,
                private route: ActivatedRoute, private formBuilder: FormBuilder) {
        this.user = new User();
        this.profiles = [];
        this._modoEdicao = false;
        this._modoDetail = false;

        this.addValidations();


        this.getProfiles();
    }
    ngOnInit():void {
        this.router.events.subscribe(e => {
            if(e.url.indexOf('/user/add') >= 0 ) {
                this._modoEdicao = false;
                this._modoDetail = false;
            }
            if(e.url.indexOf('/user/edit') >= 0 ) {
                this._modoEdicao = true;
                this._modoDetail = false;
            }
            if(e.url.indexOf('/user/detail') >= 0 ) {
                this._modoEdicao = false;
                this._modoDetail = true;
            }
        });

        this.sub = this.route.params.subscribe(params => {
            let id = params['id'];
            if( id != null ) {
                this.getUser(params['id']);
            }
        });

    }
    ngOnDestroy():void {
        this.sub.unsubscribe();
    }
    getUser(id:string):void {
        this.userService.getById(id).subscribe((user:User) => {
            this.user = user;

            if( this.user != null && this.user.id != null ) {
                console.log('aqui');
                this.userForm.removeControl('password');
                this.userForm.removeControl('passwordConfirm');
            }
        });
    }

    getProfiles():void {
        this.userService.getProfiles().subscribe((profiles:Array<string>) => this.profiles = profiles);
    }
    saveUser():void {
        this.userService.saveUser(this.user).subscribe(() => this.router.navigate(['/users']));
    }
    cancel():void {
        this.router.navigate(['/users']);
    }

    private addValidations():void{
        this.mailAddress = new FormControl(
            '',
            Validators.compose([Validators.required, GlobalValidator.mailFormat])
        );

        this.userForm = this.formBuilder.group({
            login: this.mailAddress,
            password: ['', Validators.compose([Validators.required,Validators.minLength(5)])],
            confirmPassword: ['', Validators.compose([Validators.required,Validators.minLength(5)]) ],
            profile: ['', Validators.required],
            birthDate: ['', Validators.required],
            firstName:['', Validators.required],
            lastName:['', Validators.required]
        });
    }

    get modoEdicao(): boolean {
        return this._modoEdicao;
    }

    get modoDetail(): boolean {
        return this._modoDetail;
    }

}
