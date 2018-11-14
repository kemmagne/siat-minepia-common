import { Pipe, PipeTransform } from '@angular/core';

import {TranslateService} from 'ng2-translate';

@Pipe({
    name: 'moneyFormat'
})
export class MoneyFormatPipe implements PipeTransform {

    constructor(private translateService: TranslateService) {}

    transform(value: string, args?: any): string {
        return Number.parseFloat(value).toLocaleString(this.translateService.currentLang) + ' F CFA';
    }

}
