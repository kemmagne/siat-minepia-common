import * as CryptoJS from 'crypto-js';

import { saveAs } from 'file-saver/dist/FileSaver';

import {Config} from './config';

export class Utils {

    static getBrowserPrint(): string {
        // here we generate string based on stable information taken from the browser
        const browserPrintTab = [];

        for (let i = 0; i < navigator.plugins.length; i++) {
            browserPrintTab.push(navigator.plugins[i].name);
            browserPrintTab.push(navigator.plugins[i].filename);
            browserPrintTab.push(navigator.plugins[i].description);
            browserPrintTab.push(navigator.plugins[i].version);
        }

        // take user agent
        browserPrintTab.push(navigator.userAgent);

        // take screen resolution
        browserPrintTab.push(screen.availHeight);
        browserPrintTab.push(screen.availWidth);
        browserPrintTab.push(screen.colorDepth);
        browserPrintTab.push(screen.height);
        browserPrintTab.push(screen.pixelDepth);
        browserPrintTab.push(screen.width);

        return CryptoJS.SHA256(browserPrintTab.join());
    }

    static downloadFile(httpResponse, fileName?: string) {
        if(!fileName) {
            const contentDispositionHeader: string = httpResponse.headers.get(Config.FILE_DOWNLOAD_HEADER);
            const parts: string[] = contentDispositionHeader.split(';');
            fileName = parts[1].split('=')[1];
        }
        console.info(httpResponse);
        const contentType = httpResponse.headers.get(Config.CONTENT_TYPE_HEADER);
        const blob = new Blob([httpResponse.blob()], {type: contentType});
        saveAs(blob, fileName);
    }

}
