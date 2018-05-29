import { Component, OnInit, OnDestroy } from '@angular/core';
import { LazyLoadEvent } from "primeng/primeng";
import { HttpService } from "../../modules/custom-http/http.service";
import { Subscription } from "rxjs/Subscription";
import { Config } from "../../config";

@Component({
    selector: 'app-principals',
    templateUrl: './principals.component.html'
})
export class PrincipalsComponent implements OnInit, OnDestroy {

    findPartnersSub: Subscription;
    countPrincipalsSub: Subscription;
    searchSub: Subscription;

    private errorMsg;
    partners: any[];
    nbPartners: Number;
    partnerCode: string;
    taxPayerNumber: string;

    constructor(private http: HttpService) {}

    ngOnInit() {
        this.countPrincipals();
        this.taxPayerNumber = null;
        this.partnerCode = null;
    }

    ngOnDestroy() {
        if(this.findPartnersSub) {
            this.findPartnersSub.unsubscribe();
        }
        if(this.countPrincipalsSub) {
            this.countPrincipalsSub.unsubscribe();
        }
        if(this.searchSub) {
            this.searchSub.unsubscribe();
        }
    }

    countPrincipals() {
        this.partnerCode = null;
        this.taxPayerNumber = null;
        this.countPrincipalsSub = this.http.getData('public/partners/principals/count').subscribe(
            res => {
                this.nbPartners = +res.json().data;
                this.findPartners(0, 10);
            },
            error => {
                this.errorMsg = error;
                console.error(this.errorMsg);
            }
        );
    }

    private findPartners(start, end: number) {
        this.findPartnersSub = this.http.getData(`public/partners/principals/${start}/${end}`).subscribe(
            data => {
                this.partners = data.json();
            },
            error => {
                this.errorMsg = error;
                console.error(this.errorMsg);
            }
        );
    }

    search() {
        if(!this.partnerCode) {
            this.partnerCode = null;
        }
        if(!this.taxPayerNumber) {
            this.taxPayerNumber = null;
        }
        this.nbPartners = null;
        this.searchSub = this.http.getData(`public/partners/by-code/${this.partnerCode}/${this.taxPayerNumber}`).subscribe(
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
        this.countPrincipals();
    }

    onLazyLoad(event: LazyLoadEvent) {
        setTimeout(() => {
            if(this.partners) {
                this.findPartners(event.first, event.first + event.rows);
            }
        }, Config.LAZY_TIMER);
    }

}
