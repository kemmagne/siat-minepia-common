import { Pipe, PipeTransform } from '@angular/core';

import {Config} from '../config';

@Pipe({
  name: 'getLabel'
})
export class GetLabelPipe implements PipeTransform {

    transform(value: string, args?: any): string {
        return Config['INVOICE_' + value];
    }

}
