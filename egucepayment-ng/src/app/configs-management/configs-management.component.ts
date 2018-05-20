import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpService } from "../modules/custom-http/http.service";
import { Subscription } from "rxjs/Subscription";

@Component({
    selector: 'app-configs-management',
    templateUrl: './configs-management.component.html'
})
export class ConfigsManagementComponent implements OnInit, OnDestroy {

    private findConfigsSub: Subscription;

    constructor(private http: HttpService) {}

    ngOnInit() {
        //this.findConfigs();
    }

    ngOnDestroy() {
        //this.findConfigsSub.unsubscribe();
    }

    private findConfigs() {
        this.findConfigsSub = this.http.get('admin/configs/all', true).subscribe();
    }

}
