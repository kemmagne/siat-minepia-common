import { Component, Input, Output, EventEmitter, OnInit, OnDestroy } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/timer';
import { Config } from "../../config";
import { Subscription } from "rxjs/Subscription";

@Component({
    moduleId: module.id,
    selector: 'app-alert',
    template: `
        <div class="alert alert-{{message?.severity}}" role="alert">
            <strong style="float:left;font-size:1.5em">{{message?.summary | translate}}</strong>
            <span class="mx-3"></span>{{message?.detail | translate}}
            <span class="fa fa-times" style="float:right;cursor:pointer" (click)="hide()"></span>
        </div>
    `
})
export class AlertComponent implements OnInit, OnDestroy {

    @Input()
    message;
    @Output()
    messageChange = new EventEmitter();

    private timerSub: Subscription;

    ngOnInit() {
        let timer = Observable.timer(Config.ALERT_TIMER, null);
        this.timerSub = timer.subscribe(
            t => {
                this.hide();
            }
        );
    }

    ngOnDestroy() {
        this.timerSub.unsubscribe();
    }

    public hide() {
        this.message = null;
        this.messageChange.emit(this.message);
    }

}
