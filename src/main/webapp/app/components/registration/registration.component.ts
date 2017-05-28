import {Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {FormBuilder, Validators, FormGroup, FormControl } from '@angular/forms';
import {UserService} from '../../services/user.service';
import {User} from '../../models/user';
import {GlobalValidator} from "../../utils/GlobalValidation";

@Component({
    selector:'registration',
    templateUrl:'./app/components/registration/registration.component.html',
    styleUrls:['./app/components/registration/registration.component.css']

})
export class RegistrationComponent implements OnInit{
    userForm:FormGroup;
    mailAddress: FormControl;

    user:User;
    passwordConfirm:string;
    profiles:Array<string>;

    constructor(private router: Router, private userService:UserService,
                private route: ActivatedRoute, private formBuilder: FormBuilder) {
        this.user = new User();
        this.profiles = [];

        this.addValidations();

    }
    ngOnInit():void {

    }

    saveUser():void {
        this.userService.saveUser(this.user).subscribe(() => this.router.navigate(['/home']));
    }

    private addValidations():void{
        this.mailAddress = new FormControl(
            '',
            Validators.compose([Validators.required, GlobalValidator.mailFormat])
        );
        this.userForm = this.formBuilder.group(
        {
            login: this.mailAddress,
            password: ['', Validators.compose([Validators.required,Validators.minLength(5)])],
            confirmPassword: ['', Validators.compose([Validators.required,Validators.minLength(5)]) ],
        },{ validator: GlobalValidator.matchPassword });
    }
    cancel():void {
        this.router.navigate(['/authenticate']);
    }

}
