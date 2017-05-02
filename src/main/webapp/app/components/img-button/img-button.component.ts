import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
    selector:'img-button',
    templateUrl:'./app/components/img-button/img-button.component.html'
})
export class ImgButtonComponent{
    @Input() styleValue:string = "";
    @Input() directoryValue:string = "";
    @Input() tipValue:string = "";
    @Input() textValue:string = "image not loaded";
    @Input() confirmation:boolean = false;

    @Output() eventAction = new EventEmitter();

    executeAction() {
        if(this.confirmation){
            if( confirm('Tem certeza?') ) {
                this.eventAction.emit(null); //TODO vê como passar parâmetro para emit
            }
            return;
        }
        this.eventAction.emit(null);

    }
}