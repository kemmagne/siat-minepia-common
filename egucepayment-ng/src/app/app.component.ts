import {Component, OnInit} from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

    constructor(private translate: TranslateService) {
    }

    ngOnInit() {
        this.initialiazeLanguageSettings();
    }

    isTablet() {
        const width = window.innerWidth;
        return width <= 1024 && width > 640;
    }

    isDesktop() {
        return window.innerWidth > 1024;
    }

    isMobile() {
        return window.innerWidth <= 640;
    }

    private initialiazeLanguageSettings() {
        let lang = 'fr';
        this.translate.addLangs(['en', 'fr']);
        this.translate.setDefaultLang('fr');

        let browserLang = this.translate.getBrowserLang();
        lang = browserLang.match(/en|fr/) ? browserLang : lang;
        this.translate.use(lang);
    }

    public changeLanguage(language: string) {
        this.translate.use(language);
    }

}
