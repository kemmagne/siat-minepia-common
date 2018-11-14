import {Component, ViewChild, AfterViewInit, OnInit} from '@angular/core';
import {ScrollPanel, SelectItem} from 'primeng/primeng';
import { LayoutComponent } from './layout/layout.component';

@Component({
    selector: 'app-rightpanel',
    template: `
        <div class="layout-rightpanel" [ngClass]="{'layout-rightpanel-active': app.rightPanelActive}" (click)="app.onRightPanelClick()">
            <p-scrollPanel #scrollRightPanel [style]="{height: '100%'}">
              <div class="layout-rightpanel-wrapper">
                <div *ngIf="false" class="layout-rightpanel-header">
                    <div class="weather-day">Wednesday</div>
                    <div class="weather-date">Jan 26</div>
                </div>

                <div class="layout-rightpanel-content">
                    <h1>{{'ergo.params' | translate}}</h1>

                    <div class="ui-g form-group">
                        <div class="ui-g-12 ui-md-12">
                            <p-dropdown id="menuOrientation" [options]="menuOrientations"
                            [(ngModel)]="selectedMenuOrientation"
                            (ngModelChange)="menuOrientationChange()">
                                <ng-template let-item pTemplate="selectedItem">
                                    {{item.label | translate}}
                                </ng-template>
                                <ng-template let-o pTemplate="item">
                                    {{o.label | translate}}
                                </ng-template>
                            </p-dropdown>
                        </div>

                        <div class="ui-g-12 ui-md-12">
                            <p-dropdown id="menuShine" [options]="menuShineOptions"
                            [(ngModel)]="selectedMenuShine"
                            (ngModelChange)="menuShineChange()">
                                <ng-template let-item pTemplate="selectedItem">
                                    {{item.label | translate}}
                                </ng-template>
                                <ng-template let-s pTemplate="item">
                                    {{s.label | translate}}
                                </ng-template>
                            </p-dropdown>
                        </div>
                    </div>
                </div>
              </div>
            </p-scrollPanel>
        </div>
    `
})
export class AppRightpanelComponent implements AfterViewInit, OnInit {

    @ViewChild('scrollRightPanel') rightPanelMenuScrollerViewChild: ScrollPanel;

    menuOrientations: SelectItem[];
    selectedMenuOrientation: string;

    menuShineOptions:  SelectItem[];
    selectedMenuShine: string;

    constructor(public app: LayoutComponent) {}

    ngOnInit() {
        this.menuOrientations = [
            {value: null, label: 'menu.orientation'},
            {value: 'STATIC', label: 'static.menu'},
            {value: 'OVERLAY', label: 'overlay.menu'},
            {value: 'SLIM', label: 'slim.menu'},
            {value: 'HORIZONTAL', label: 'horizontal.menu'}
        ];

        this.menuShineOptions = [
            {value: null, label: 'menu.shine'},
            {value: 'LIGHT', label: 'light.menu'},
            {value: 'DARK', label: 'dark.menu'}
        ];
    }

    ngAfterViewInit() {
        setTimeout(() => {this.rightPanelMenuScrollerViewChild.moveBar();}, 100);
    }

    menuOrientationChange() {
        console.log(this.selectedMenuOrientation);
        if(this.selectedMenuOrientation === 'STATIC') {
            this.app.changeToStaticMenu();
        } else if(this.selectedMenuOrientation === 'OVERLAY') {
            this.app.changeToOverlayMenu();
        } else if(this.selectedMenuOrientation === 'SLIM') {
            this.app.changeToSlimMenu();
        } else if(this.selectedMenuOrientation === 'HORIZONTAL') {
            this.app.changeToHorizontalMenu();
        } else {
            this.app.changeToStaticMenu();
        }
    }

    menuShineChange() {
        console.log(this.selectedMenuShine);
        this.app.darkMenu = this.selectedMenuShine === 'DARK';
    }
}
