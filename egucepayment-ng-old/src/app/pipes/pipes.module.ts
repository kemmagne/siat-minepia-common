import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {GetLabelPipe} from './get-label.pipe';
import {MoneyFormatPipe} from './money-format.pipe';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [GetLabelPipe, MoneyFormatPipe],
  exports: [GetLabelPipe, MoneyFormatPipe]
})
export class PipesModule { }
