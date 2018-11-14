import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpService } from "../../modules/custom-http/http.service";

@Component({
    selector: 'app-asset-clearance',
    templateUrl: './asset-clearance.component.html'
})
export class AssetClearanceComponent implements OnInit, OnDestroy {

    private errorMsg;

    invoicesFound: any[];
    invoicesToPay = [];
    totalAmount = '0';

    constructor(private http: HttpService) {}

    ngOnInit() {}

    ngOnDestroy() {}

    private clearAsset() {

    }

}
