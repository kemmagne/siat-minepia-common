import { Injectable } from '@angular/core';
import { Subject } from 'rxjs/Subject';

@Injectable()
export class LoaderService {

    private loaderSubject = new Subject<LoaderState>();

    loaderState = this.loaderSubject.asObservable();

    constructor() {}

    showGet() {
        this.loaderSubject.next(<LoaderState>{showGet: true});
    }

    showPost() {
        this.loaderSubject.next(<LoaderState>{showPost: true});
    }

    hideGet() {
        this.loaderSubject.next(<LoaderState>{showGet: false});
    }

    hidePost() {
        this.loaderSubject.next(<LoaderState>{show: false});
    }

    show() {
        //this.loaderSubject.next(<LoaderState>{show: true});
    }

    hide() {
        //this.loaderSubject.next(<LoaderState>{show: false});
    }

}

export interface LoaderState {

    show?: boolean;
    showGet?: boolean;
    showPost?: boolean;
    
}
