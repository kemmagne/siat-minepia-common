import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from "rxjs/Subscription";
import { Config } from "../../config";
import { HttpService } from "../../modules/custom-http/http.service";

@Component({
    selector: 'app-agencies',
    templateUrl: './agencies.component.html'
})
export class AgenciesComponent implements OnInit, OnDestroy {

    selectedBank: string;
    errorMsg: any;
    findPartnersSub: Subscription;
    findBanksSub: Subscription;
    partners: any[];
    banks: any[];

    constructor(private http: HttpService) {}

    ngOnInit() {
        this.findBanks();
    }

    ngOnDestroy() {
        this.findBanksSub.unsubscribe();
        if(this.findPartnersSub) {
            this.findPartnersSub.unsubscribe();
        }
    }

    onBankChange(event) {
        this.findPartnersSub = this.http.get(`partners/children/${this.selectedBank}`, true).subscribe(
            data => {
                this.partners = data.json();
            },
            error => {
                this.errorMsg = error;
                console.error(this.errorMsg);
            }
        );
    }

    private findBanks() {
        this.findBanksSub = this.http.get(`partners/by-types/${Config.PARTNER_TYPE_BANK}/${0}/${0}`, true).subscribe(
            data => {
                this.banks = data.json();
            },
            error => {
                this.errorMsg = error;
                console.error(this.errorMsg);
            }
        );
    }

    onPartnerSave(event) {
        if(!!event) {
            this.selectedBank = event;
        }
        if(!this.selectedBank) {
            return;
        }
        this.onBankChange(null);
    }

}
