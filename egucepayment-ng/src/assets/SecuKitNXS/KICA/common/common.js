function NXInit() {
    //password input text box initiate in selection certificate box
    $('#certPwd').val('');
    // password initiate 
    $('#nx_issue_cert_pw').val('');
    $('#nx_issue_cert_pw_confirm').val('');
    // PIN number initiate
    $('#nx_issue_cert_pin').val('');
    // verify infomation
    $('#chkSSN').val('');
}

/**
 * @public
 * @memberof Common
 * @method initCert
 * @description initiate infomation of certificate
 * @param {String} certID certID
*/
function NXinitCert(certID) {
    var cmd = 'NXinitCert.initCert';
    var Data = {
        'certID': certID
    };
    var param = JSON.stringify(Data);
    secukitnxInterface.SecuKitNX_EX(cmd, param);
}

/**
 * @public
 * @memberof Common
 * @method initCertCallback
 * @description initCert callback function
 * @param reply callback date
 * @return 
 *      success : true, fail : ERROR_CODE:error code, ERROR_MESSAGE:error message
 */
function NXinitCertCallback(reply) {
    var errorCheck = -1;
    try {
        errorCheck = reply.ERROR_CODE;
    } catch (e) {
        console.log(e);
    }

    if (errorCheck === undefined) {

        if (reply.initCert === 'true') {
            alert(NX_COMMON_TEXT_1);
        }
        else {
            alert(NX_COMMON_TEXT_2);
        }
    }
    else {
        $('.nx-cert-select').hide(); $('#nx-pki-ui-wrapper').hide(); KICA_Error.init();
        KICA_Error.setError(reply.ERROR_CODE, reply.ERROR_MESSAGE);
        var errorMsg = KICA_Error.getError();
        alert(errorMsg);
    }
}

/**
* @public
* @memberof Common
* @method initCert
* @description initiate infomation of certificate : issue module
* @param {String} certID certID
*/
function NXinitCertIssue(certID) {
    var cmd = 'NXinitCertIssue.initCertIssue';
    var Data = {
        'certID': certID
    };
    var param = JSON.stringify(Data);

    secukitnxInterface.SecuKitNX_EX(cmd, param);
}

/**
 * @public
 * @memberof Common
 * @method initCertCallback
 * @description initCert callback function
 * @param reply callback data
 * @return 
 *      success : true, fail : ERROR_CODE:error code, ERROR_MESSAGE:error message
 */
function NXinitCertIssueCallback(reply) {
    var errorCheck = -1;
    try {
        errorCheck = reply.ERROR_CODE;
    } catch (e) {
        console.log(e);
    }

    if (errorCheck === undefined) {

        if (reply.initCert === 'true') {
            alert(NX_COMMON_TEXT_1);
        }
        else {
            alert(NX_COMMON_TEXT_2);
        }
    }
    else {
        $('.nx-cert-select').hide(); $('#nx-pki-ui-wrapper').hide(); KICA_Error.init();
        KICA_Error.setError(reply.ERROR_CODE, reply.ERROR_MESSAGE);
        var errorMsg = KICA_Error.getError();
        alert(errorMsg);
    }
}

// callback function : infomation fo client version
function NXClientGetVersion() {
    SECUKITNX.Invoke('getVersion', [""], 'secukitnxInterface.SecuKitNX_EXCallBack', 'NXClientGetVersionCallback');
}

function NXClientGetVersionCallback(reply) {
    var errorCheck = -1;
    try {
        errorCheck = reply.ERROR_CODE;
    } catch (e) {
        console.log(e);
    }

    if (errorCheck === undefined) {
        
        if (SECUKITNX_CONST.serviceType === 1)
        {   // 이용 & 발급 모듈
            var NXClientVer = 'SecuKitNX Version ';
        }
        else if (SECUKITNX_CONST.serviceType === 2) {
            // XML
            var NXClientVer = 'SecuXML Version ';
        }
        else if (SECUKITNX_CONST.serviceType === 3) {
            // KMS
            var NXClientVer = 'KMS Version ';
        }
  
        NXClientVer += reply.version[0];
        NXClientVer += '.';
        NXClientVer += reply.version[1];
        NXClientVer += '.';
        NXClientVer += reply.version[2];
        NXClientVer += '.';
        NXClientVer += reply.version[3];

        document.getElementById("client-version").innerHTML = NXClientVer;
    }
}


function removeCRLF(str) {
    var i = 0;
    var buf = "";
    for (i = 0; i < str.length; i++) {
        if (str.charAt(i) !== '\n' && str.charAt(i) !== '\r') {
            buf += str.charAt(i);
        }
    }
    return buf;
}

function removePEMHeader(str) {
    var startStr = "-----BEGIN CERTIFICATE-----";
    var endStr = "-----END CERTIFICATE-----";
    var startP7 = "-----BEGIN PKCS7-----";
    var endP7 = "-----END PKCS7-----";
    var resultStr1 = str.replace(startStr, "");
    var resultStr2 = resultStr1.replace(endStr, "");
    var resultStr3 = resultStr2.replace(startP7, "");
    var resultStr4 = resultStr3.replace(endP7, "");

    return resultStr4;
}

function insertLFtoPEMCert(strCert) {
    if (strCert === null || strCert === "") {
        return "";
    }
    var pemHeader = "-----BEGIN CERTIFICATE-----";
    var pemTrailer = "-----END CERTIFICATE-----";
    var buf = removeCRLF(strCert);
    var i = 0;
    var nCount = 0;
    for (i = 0; i < pemHeader.length; i++) {
        if (pemHeader.charAt(i) === buf.charAt(i)) {
            nCount = nCount + 1;
        }
    }
    if (nCount !== pemHeader.length) {
        return "";
    }

    nCount = 0;
    for (i = 0; i < pemTrailer.length; i++) {
        if (pemTrailer.charAt(i) === buf.charAt(buf.length - pemTrailer.length + i)) {
            nCount = nCount + 1;
        }
    }
    if (nCount !== pemTrailer.length) {
        return "";
    }

    var strPEMCert = "";
    nCount = 0;
    for (i = 0; i < buf.length - pemHeader.length - pemTrailer.length; i++) {
        strPEMCert += buf.charAt(i + pemHeader.length);
        nCount = nCount + 1;
        if (nCount === 64) {
            strPEMCert += '\n';
            nCount = 0;
        }
    }
    strPEMCert = pemHeader + "\n" + strPEMCert + "\n" + pemTrailer;
    return strPEMCert;
}

//NULL check
function InsertNullCheck(data) {
    return (typeof data !== 'undefined' && data !== null && data !== "") ? false : true;
}

// start, end format: yyyymmdd
function getDifferentDays(start, end) {
    var dateStart = new Date(start.substring(0, 4), start.substring(4, 6) - 1, start.substring(6, 8));
    var dateEnd = new Date(end.substring(0, 4), end.substring(4, 6) - 1, end.substring(6, 8));
    var difDays = (dateEnd.getTime() - dateStart.getTime()) / (24 * 60 * 60 * 1000);

    return Math.ceil(difDays);
}


function NXclearSymmetricKey(keyName) {
    var cmd = 'NXclearSymmetricKey.clearSymmetricKey';
    var Data = {
        'keyName': keyName
    };

    var param = JSON.stringify(Data);
    secukitnxInterface.SecuKitNX_EX(cmd, param);
}


function NXclearSymmetricKeyCallback(reply) {
    var errorCheck = -1;
    try {
        errorCheck = reply.ERROR_CODE;
    } catch (err) {
        console.log(err);
    }

    if (errorCheck === undefined) {
        //result
        var res = reply.clearSymmetricKey;
    }
    else {
        KICA_Error.init();
        KICA_Error.setError(reply.ERROR_CODE, reply.ERROR_MESSAGE);
        var errorMsg = KICA_Error.getError();
        alert(errorMsg);
    }
}