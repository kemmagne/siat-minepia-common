import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpService } from "../modules/custom-http/http.service";
import { Subscription } from "rxjs/Subscription";
import { DatePipe } from "@angular/common";

@Component({
    selector: 'app-sitting-date-management',
    templateUrl: './sitting-date-management.component.html'
})
export class SittingDateManagementComponent implements OnInit, OnDestroy {

    alertMessage;
    private errorMessage: any;

    sittingDates: any[];
    sittingDate;
    showConfirm: boolean;
    showNewSittingDateDialog: boolean;

    private findSittingDatesSub: Subscription;
    private saveSittingDateSub: Subscription;

    constructor(private http: HttpService) {}

    ngOnInit() {
        this.sittingDate = {};
        this.findSittingDates();
    }

    ngOnDestroy() {
        if(this.findSittingDatesSub) {
            this.findSittingDatesSub.unsubscribe();
        }
        if(this.saveSittingDateSub) {
            this.saveSittingDateSub.unsubscribe();
        }
    }

    newSittingDate() {
        this.sittingDate = {};
        this.showNewSittingDateDialog = true;
    }

    private findSittingDates() {
        this.findSittingDatesSub = this.http.getData('admin/sitting/date/all', true).subscribe(
            data => {
                this.sittingDates = data.json();
            },
            error => {
                this.errorMessage = error;
                console.error(this.errorMessage);
            }
        );
    }

    onConfirmSave(event) {
        this.saveSittingDate();
    }

    private saveSittingDate() {
        let minDateParts = this.sittingDate.minDate.split(':');
        let maxDateParts = this.sittingDate.maxDate.split(':');
        if(23 < +minDateParts[0] || 59 < +minDateParts[1] || 23 < +maxDateParts[0] || 59 < +maxDateParts) {
            this.alertMessage = {severity: 'warning', summary: 'warning', detail: 'incorrect.hour'};
            return;
        }
        if(+minDateParts[0] > +maxDateParts[0]) {
            this.alertMessage = {severity: 'warning', summary: 'warning', detail: 'incoherent.date'};
            return;
        }
        if(+minDateParts[0] === +maxDateParts[0] && +minDateParts[1] === +maxDateParts[1]) {
            this.alertMessage = {severity: 'warning', summary: 'warning', detail: 'min.hour.equals.max.hour'};
            return;
        }
        this.saveSittingDateSub = this.http.postData('admin/sitting/date', this.sittingDate).subscribe(
            res => {
                let response = res.json();
                if(response.data) {
                    if(0 === +response.data) {
                        return;
                    }
                    return;
                }
                this.sittingDate = {};
                this.alertMessage = {severity: 'success', summary: 'operation.succeeded', detail: 'sitting.date.saved'};
                this.findSittingDates();
            }
        );
    }

}
