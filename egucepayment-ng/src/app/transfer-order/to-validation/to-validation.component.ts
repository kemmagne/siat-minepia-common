import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from "rxjs/Subscription";
import { HttpService } from "../../modules/custom-http/http.service";

@Component({
    selector: 'app-to-validation',
    templateUrl: './to-validation.component.html'
})
export class ToValidationComponent implements OnInit, OnDestroy {

    private errorMsg;

    transferOrders: any[];
    private findTransferOrdersSub: Subscription;

    constructor(private http: HttpService) {}

    ngOnInit() {
        this.findTransferOrders();
    }

    ngOnDestroy() {
        this.findTransferOrdersSub.unsubscribe();
    }

    private findTransferOrders() {
        this.findTransferOrdersSub = this.http.get(`transfers/orders/${true}/${0}/${0}/${false}`, true).subscribe(
            data => {
                this.transferOrders = data.json();
            },
            error => {
                this.errorMsg = error;
            }
        );
    }

}
