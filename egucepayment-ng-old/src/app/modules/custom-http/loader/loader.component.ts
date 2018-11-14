import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';

import { LoaderService, LoaderState } from './loader.service';

@Component({
    selector: 'app-loader',
    template: `
            <div>
                <div>
                    <p-dialog [closable]="false" [closeOnEscape]="false" [showHeader]="false" [draggable]="false" [resizable]="false" [modal]="true" [(visible)]="showPost">
                        <div class="progress">
                            <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:100%"></div>
                        </div>
                    </p-dialog>
                    <p-dialog [closable]="false" [closeOnEscape]="false" [showHeader]="false" [draggable]="false" [resizable]="false" [modal]="true" [(visible)]="showGet">
                        <div class="progress">
                            <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:100%"></div>
                        </div>
                    </p-dialog>
                </div>
            </div>
            <div [class.loader-hidden]="!showGet">
                <div class="loader-overlay">
                    <div>
                        <span class="fa fa-refresh fa-spin" *ngIf="showGet"></span>
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
