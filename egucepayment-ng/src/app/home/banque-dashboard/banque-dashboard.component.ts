import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpService } from "../../modules/custom-http/http.service";
import { Subscription } from "rxjs/Subscription";
import { Config } from "../../config";
import { TranslateService } from "ng2-translate";
import { MoneyFormatPipe } from "../../pipes/money-format.pipe";

@Component({
    selector: 'app-banque-dashboard',
    templateUrl: './banque-dashboard.component.html'
})
export class BanqueDashboardComponent implements OnInit, OnDestroy {

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
    max: number = 10;

    private findStatsSub: Subscription;

    statistics: any[];

    constructor(private http: HttpService, private translateService: TranslateService) {}

    ngOnInit() {
        this.chartData = {
            labels: [],
            datasets: []
        };
        this.findStats();
    }

    ngOnDestroy() {
        this.findStatsSub.unsubscribe();
    }

    private findStats() {
        this.findStatsSub = this.http.get(`statistics/transfer/orders/by-bank/last/${this.max}/${this.translateService.currentLang}`, true).subscribe(
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
