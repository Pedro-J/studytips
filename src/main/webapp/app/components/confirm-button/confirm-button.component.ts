import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
    selector:'confirm-button',
    templateUrl:'./app/components/confirm-button/confirm-button.component.html'
})
export class ConfirmButtonComponent{
    @Input() nome: string = 'Ok';
    @Input() estilo: string = 'btn-default';
    @Input() tipo: string = 'button';
    @Input() desabilitado: boolean = false;
    @Output() acao = new EventEmitter();
    @Input() confirmacao:boolean = false;

    executaAcao() {
        if(this.confirmacao){
            if( confirm('Tem certeza?') ) {
                this.acao.emit(null);
            }
            return;
        }
        this.acao.emit(null);
        
    }

}