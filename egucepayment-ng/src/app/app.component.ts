import { Component, ViewChild, ElementRef, OnInit } from '@angular/core';
import { TranslateService } from "ng2-translate";
import { BlockableUI } from "primeng/primeng";
import { PersistenceService, StorageType } from "angular-persistence";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {

    constructor(private translate: TranslateService) {}

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

}
