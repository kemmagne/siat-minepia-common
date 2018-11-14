import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpService } from "../../modules/custom-http/http.service";
import { Subscription } from "rxjs/Subscription";
import { Config } from "../../config";

@Component({
    selector: 'app-to-follow-up',
    templateUrl: './to-follow-up.component.html'
})
export class ToFollowUpComponent implements OnInit, OnDestroy {

    errorMessage: any;

    transferOrders: any[];
    nbTransferOrders: Number;

    findTransferOdersSub: Subscription

    constructor(private http: HttpService) {}

    ngOnInit() {
        this.countTransferOders();
    }

    ngOnDestroy() {
        this.findTransferOdersSub.unsubscribe();
    }
    
    private countTransferOders() {
        this.findTransferOdersSub = this.http.getData(`transfers/orders/by-validator/${0}/${0}/${true}`, true).subscribe(
            res => {
                this.nbTransferOrders = +res.json().data;
                this.findTransferOders(0, 10);
            },
            error => {
                this.errorMessage = error;
                console.error(this.errorMessage);
            }
        );
    }
    
    private findTransferOders(start, end: number) {
        this.findTransferOdersSub = this.http.getData(`transfers/orders/by-validator/${start}/${end}/${false}`, true).subscribe(
            data => {
                this.transferOrders = data.json();
            },
            error => {
                this.errorMessage = error;
                console.error(this.errorMessage);
            }
        );
    }

    onLazyLoad(event) {
        let rows = +event.rows;
        setTimeout(() => {
            this.findTransferOders(event.first, event.first + rows);
        }, Config.LAZY_TIMER);
    }

}
