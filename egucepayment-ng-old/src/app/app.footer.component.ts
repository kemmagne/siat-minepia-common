import {Component} from '@angular/core';

@Component({
    selector: 'app-footer',
    template: `
        <div *ngIf="false" class="footer">
            <div class="card">
                <div class="card-block">
                    <span class="">Copyright &copy; GUCE-GIE Octobre 2017</span>
                    <span class="pull-right"><span class="ui-icon ui-icon-copyright"></span>  <span>{{'copyright' | translate}}</span></span>
                </div>
            </div>
        </div>
    `
})
export class AppFooterComponent {

}
