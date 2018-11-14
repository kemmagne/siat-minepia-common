import { Component, OnInit, Input, EventEmitter, Output, OnDestroy } from '@angular/core';
import { HttpService } from "../custom-http/http.service";
import { Subscription } from "rxjs/Subscription";

@Component({
    selector: 'app-transfer-order-filter',
    templateUrl: './transfer-order-filter.component.html'
})
export class TransferOrderFilterComponent implements OnInit, OnDestroy {

    private errorMsg;

    //@Input()
    toFound: any[];
    @Output()
    toFoundChange = new EventEmitter<any[]>();

    private filterToSub: Subscription;

    constructor(private http: HttpService) { }

    ngOnInit() {}

    ngOnDestroy() {
        if(this.filterToSub) {
            this.filterToSub.unsubscribe();
        }
    }

    filterTos() {
        let filter = {};
        this.filterToSub = this.http.postData('', filter).subscribe();
    }

}
