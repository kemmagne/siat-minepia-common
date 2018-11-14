import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-confirm-dialog',
  template: `
      <p-dialog header="Confirmation" [(visible)]="display" [closable]="false" (onHide)="onHide($event)" [modal]="true" width="300" 
      [responsive]="true" [closeOnEscape]="false" appendTo="body">
          {{contentText | translate}}
          <p-footer>
              <button class="ui-button-danger" type="button" pButton icon="fa-close" (click)="decline()" label="{{'no' | translate}}"></button>
              <button type="button" pButton icon="fa-check" (click)="confirm()" label="{{'yes' | translate}}"></button>
          </p-footer>
      </p-dialog>
  `
})
export class ConfirmDialogComponent implements OnInit {

    @Input()
    contentText: string;

    @Input()
    display: boolean;
    @Output()
    displayChange = new EventEmitter<boolean>();

    @Output()
    onConfirm = new EventEmitter<boolean>();
    @Output()
    onCancel = new EventEmitter<boolean>();
    @Output()
    onClose = new EventEmitter<boolean>();

    constructor() {}

    ngOnInit() {}

    decline() {
        this.hide();
        this.onCancel.emit(true);
    }

    confirm() {
        this.hide();
        this.onConfirm.emit(true);
    }

    onHide(event) {
        this.hide();
        this.onClose.emit(true);
    }

    private hide() {
        this.display = false;
        this.displayChange.emit(this.display);
    }

}
