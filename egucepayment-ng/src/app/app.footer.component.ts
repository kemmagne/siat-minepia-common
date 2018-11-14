import {Component} from '@angular/core';

@Component({
    selector: 'app-footer',
    template: `
        <div class="footer">
            <div class="card clearfix">
                <span class="footer-text-left">e-GUCE Payment Version 1.0</span>
                <span class="footer-text-right">
                    <span class="material-icons ui-icon-copyright"></span>
                    <span>GUCE-GIE - {{'january' | translate}} 2019 - {{'copyright' | translate}}</span>
                </span>
            </div>
        </div>
    `
})
export class AppFooterComponent {

}
