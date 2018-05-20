import { Component, OnInit, OnDestroy } from '@angular/core';
import { Config } from "../../config";
import { HttpService } from "../../modules/custom-http/http.service";
import { Subscription } from "rxjs/Subscription";

@Component({
    selector: 'app-banks',
    templateUrl: './banks.component.html'
})
export class BanksComponent implements OnInit, OnDestroy {

    errorMsg: any;
    findPartnersSub: Subscription;
    partners: any[];

    constructor(private http: HttpService) {}

    ngOnInit() {
        this.findPartners();
    }

    ngOnDestroy() {
        this.findPartnersSub.unsubscribe();
    }

    private findPartners() {
        this.findPartnersSub = this.http.get(`partners/by-types/${Config.PARTNER_TYPE_BANK}/${0}/${0}`).subscribe(
            data => {
                this.partners = data.json();
            },
            error => {
                this.errorMsg = error;
                console.error(this.errorMsg);
            }
        );
    }

    onPartnerSave() {
        this.findPartners();
    }

}
