import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpService } from "../../modules/custom-http/http.service";
import { Subscription } from "rxjs/Subscription";
import { Config } from "../../config";

@Component({
    selector: 'app-beneficiaries',
    templateUrl: './beneficiaries.component.html'
})
export class BeneficiariesComponent implements OnInit, OnDestroy {

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
        this.findPartnersSub = this.http.get(`partners/by-types/${Config.PARTNER_TYPE_BENEFICIARY}/${0}/${0}`).subscribe(
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
