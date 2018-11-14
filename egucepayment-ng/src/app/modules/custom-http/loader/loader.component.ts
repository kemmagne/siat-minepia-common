import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';

import { LoaderService, LoaderState } from './loader.service';

@Component({
    selector: 'app-loader',
    template: `
            <div>
                <div>
                    <p-dialog [closable]="false" [closeOnEscape]="false" [showHeader]="false" [draggable]="false" [resizable]="false" [modal]="true" [(visible)]="showPost">
                        <p-progressBar mode="indeterminate" [style]="{'height': '6px'}"></p-progressBar>
                    </p-dialog>
                    <p-dialog [closable]="false" [closeOnEscape]="false" [showHeader]="false" [draggable]="false" [resizable]="false" [modal]="true" [(visible)]="showGet">
                        <p-progressBar mode="indeterminate" [style]="{'height': '6px'}"></p-progressBar>
                    </p-dialog>
                </div>
            </div>
            <div [class.loader-hidden]="!showGet">
                <div class="loader-overlay">
                    <div>
                        <p-progressSpinner *ngIf="showGet" [style]="{width: '50px', height: '50px'}" strokeWidth="8" fill="#EEEEEE" animationDuration=".5s"></p-progressSpinner>
                    </div>
                </div>
            </div>
    `,
    styleUrls: ['./loader.component.css']
})
export class LoaderComponent implements OnInit, OnDestroy {

    showGet: boolean;
    showPost: boolean;

    private subscription: Subscription;

    constructor(private loaderService: LoaderService) {}

    ngOnInit() {
        this.subscription = this.loaderService.loaderState.subscribe((state: LoaderState) => {
            this.showGet = state.showGet;
            this.showPost = state.showPost;
        });
    }

    ngOnDestroy() {
        this.showGet = false;
        this.showPost = false;
        this.subscription.unsubscribe();
    }

}
