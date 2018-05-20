import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from "rxjs/Subscription";
import { HttpService } from "../../modules/custom-http/http.service";
import { TranslateService } from "ng2-translate";

@Component({
    selector: 'app-beneficiaire-dashboard',
    templateUrl: './beneficiaire-dashboard.component.html'
})
export class BeneficiaireDashboardComponent implements OnInit, OnDestroy {
    
    max: number = 10;
    chartData: {
        labels: any[];
        datasets: {
            label: string;
            backgroundColor: string;
            borderColor: string;
            data: any[];
        }[];
    };

    alertMessage;
    private errorMsg;

    private findStatsSub: Subscription;

    statistics: any[];

    constructor(private http: HttpService, private translateService: TranslateService) {}

    ngOnInit() {
        this.findStats();
    }

    ngOnDestroy() {
        this.findStatsSub.unsubscribe();
    }

    private findStats() {
        this.findStatsSub = this.http.get(`statistics/transfer/orders/by-beneficiary/last/${this.max}/${this.translateService.currentLang}`, true).subscribe(
            data => {
                this.statistics = data.json();
            },
            error => {
                this.errorMsg = error;
                console.log(this.errorMsg);
            },
            () => {
                let labels = [];
                let values = [];
                for(let stat of this.statistics) {
                    labels.push(stat.startedDate);
                    values.push(stat.amount);
                }
                this.chartData = {
                    labels: labels,
                    datasets: [{
                        label: "",
                        backgroundColor: "#42A5F5",
                        borderColor: '#1E88E5',
                        data: values
                    }]
                };
            }
        );
    }

}
