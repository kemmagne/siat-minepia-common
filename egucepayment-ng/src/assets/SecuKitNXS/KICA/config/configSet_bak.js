//======================================= 수정 금지 X =============================================

/***********************************************
환경 설정 변수 SET 함수 모음
************************************************/
function NXsetCommonInfo() {
    var logicFlag = processLogic.getProcessLogic();
    var cmd;
    if ((logicFlag.indexOf('ISSUE') !== -1) || (logicFlag.indexOf('MANAGEMENT') !== -1)) {
        // 발급모듈에서 진행한 경우
        cmd = 'NXsetCommonInfo.setCommonInfoIssue';
    } else {
        // 이용모듈에서 진행한 경우
        cmd = 'NXsetCommonInfo.setCommonInfo';
    }
    var Data = {
        'caIP': NX_CA_IP,
        'caCmpPort': NX_CA_PORT,
        'anyPolicy': NX_AnyPolicy,
        'policies': NX_POLICIES

    };
    var param = JSON.stringify(Data);
    secukitnxInterface.SecuKitNX_EX(cmd, param);
}

function NXsetCommonInfoCallback(reply) {
    var errorCheck = -1;
    try {
        errorCheck = reply.ERROR_CODE;
    } catch (err) {
        nxlog(err);
    }

    if (errorCheck === undefined) {
        nxlog('NXsetCommonInfoCallback : ' + reply.setCommonInfo);
    }
}

// 타겟 미디어 비활성화
function NXsetTargetMediaDisable(targetMedia) {
    var index = 0;
    while (true) {
        media = NX_TARGET_MEDIA[index].MEDIA;
        if (media === targetMedia) {
            NX_TARGET_MEDIA[index].ABLE = 'disable';
            break;
        }
        index++;
    }
}

// 타겟 미디어 활성화
function NXsetTargetMediaAble(targetMedia) {
    var index = 0;
    while (true) {
        media = NX_TARGET_MEDIA[index].MEDIA;
        if (media === targetMedia) {
            NX_TARGET_MEDIA[index].ABLE = 'able';
            break;
        }
        index++;
    }
}

// 인증서 선택창 미디어 비활성화
function NXsetMediaDisable(selectMedia) {
    var index = 0;
    while (true) {
        media = NX_SELECT_CERT_MEDIA[index].MEDIA;
        if (media === selectMedia) {
            NX_SELECT_CERT_MEDIA[index].ABLE = 'disable';
            break;
        }
        index++;
    }
}

// 인증서 선택창 미디어 활성화
function NXsetMediaAble(selectMedia) {
    var index = 0;
    while (true) {
        media = NX_SELECT_CERT_MEDIA[index].MEDIA;
        if (media === selectMedia) {
            NX_SELECT_CERT_MEDIA[index].ABLE = 'able';
            break;
        }
        index++;
    }
}

// 배너 정보 변경
var downClientFlag = false;
function NXsetBanner(info) {
    downClientFlag = true;
    NX_Banner_IMG_URL = info;
}

// 배너 이미지 변경 : 저장매체 선택창
function NXsetBanner_TargetMedia() {
    $("#nx-targetMedia-select-logo-area img").remove();

    var data = '';
    data += '<img src=';
    data += '\"' + NX_Banner_IMG_URL + '\"';
    data += 'alt="A World of Trust 한국정보인증 로고">';

    $("#nx-targetMedia-select-logo-area").append(data);
    $("#nx-cert-select-logo-area img").remove();

    var data = '';
    data += '<img src=';
    data += '\"' + NX_Banner_IMG_URL + '\"';
    data += 'alt="A World of Trust 한국정보인증 로고">';

    $("#nx-cert-select-logo-area").append(data);
}

// 다운로드
function NXdownClientURL() {
    // Client 프로그램 자동 다운로드 실행               
    window.location = NXClient_DownLoad_URL;
}

function getNXLocaleInfo() {
    return NXLOCALE;
}

/**
  * @public
  * @memberof NXVersionInfo
  * @method setEnvironment
  * @description SecukitNX CharSet을 설정한다.
  */
function NXsetEnvironment() {
    //nxlog('base64Encode call');
    var cmd = 'NXsetEnvironment.setEnvironment';
    var Data = {
        'inCharset': inCharset,
        'outCharset': outCharset
    };
    var param = JSON.stringify(Data);

    secukitnxInterface.SecuKitNX_EX(cmd, param);
}

/**
 * @public
 * @memberof NXVersionInfo
 * @method setEnvironmentCallback
 * @description SecukitNX CharSet을 설정한다.
 * @param reply 콜백 데이터
 * @return 
 *      성공 : true , 실패 : ERROR_CODE:에러코드, ERROR_MESSAGE:에러메시지
 */
function NXsetEnvironmentCallback(reply) {
    var errorCheck = -1;
    try {
        errorCheck = reply.ERROR_CODE;
    } catch (e) {
        //console.log(e);
    }

    if (errorCheck === undefined) {
        var SecuKit_CharSet = reply.setEnvironment;
        if (SecuKit_CharSet === 'true') {
            var logFlag = ConsoleLogFlag;
            if (logFlag === true) {
                //console.log('SecuKit_CharSet : ' + SecuKit_CharSet);
            }
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
  * @memberof NXVersionInfo
  * @method getVersion
  * @description check SecuKitNX DLL version.
  */
function NXgetVersion() {
    var cmd = 'NXgetVersion.getVersion';
    var Data = {
        'version': 'NULL'
    };
    var param = JSON.stringify(Data);

    secukitnxInterface.SecuKitNX_EX(cmd, param);
}

/**
* @public
* @memberof NXVersionInfo
* @method getVersionCallback
* @description check SecuKitNX DLL version.
* @param reply callback data
* @return 
*      success : DLL ver. info , fail : ERROR_CODE:error code, ERROR_MESSAGE:error message
*/
function NXgetVersionCallback(reply) {
    var errorCheck = -1;
    try {
        errorCheck = reply.ERROR_CODE;
    } catch (err) {
        //console.log(err);
    }

    if (errorCheck === undefined) {
        if (reply.version !== '0000') {
            Current_DLL_Version = reply.version;
            checkInstallAndVersion(Release_DLL_Version);
        }
    }
    else {
        $('.nx-cert-select').hide(); $('#nx-pki-ui-wrapper').hide(); KICA_Error.init();
        KICA_Error.setError(reply.ERROR_CODE, reply.ERROR_MESSAGE);
        var errorMsg = KICA_Error.getError();
        alert(errorMsg);
    }
}

function getUsimSiteCode() {
    return USIM_SITECODE;
}



/**
 * @brief NXModule 통합 환경 설정 함수, 결과값에 에러가 없는 설정에 대한 통합 설정 함수
 * @param type 설정할 환경에 대한 종류 ex) USIM, NFC...
 * @param param1  usim: section ex)0001-라온, 0002-드림, 0004-수미온|
 * @param param2  usim: downloadURL									|
 * @param param3  usim: siteDomainURL								|
 * @param param4  usim: serverIP									|
 * @param param5  usim: serverPort									|
 * @param param6  usim: siteCode									|
 * @param param7  usim: modeCode									|
 * @param param8  													|
 * @param param9  													|
 * @param param10 													|
*/
function NXsetIntegration(type, param1, param2, param3, param4, param5, param6, param7, param8, param9, param10) {
    //사용안함
    var cmd = 'NXsetIntegration.setIntegration';
    var Data = {
        'type': type,
        'param1': param1,
        'param2': param2,
        'param3': param3,
        'param4': param4,
        'param5': param5,
        'param6': param6,
        'param7': param7,
        'param8': param8,
        'param9': param9,
        'param10': param10

    };
    var param = JSON.stringify(Data);

    secukitnxInterface.SecuKitNX_EX(cmd, param);
}


function NXsetIntegrationCallback(reply) {
    var errorCheck = -1;
    try {
        errorCheck = reply.ERROR_CODE;
    } catch (err) {
        //console.log(err);
    }

    if (errorCheck === undefined) {
        //console.log(' Set Config Result : ' + reply.setIntegration);

    } else {
        $('.nx-cert-select').hide(); $('#nx-pki-ui-wrapper').hide(); KICA_Error.init();
        KICA_Error.setError(reply.ERROR_CODE, reply.ERROR_MESSAGE);
        var errorMsg = KICA_Error.getError();
        alert(errorMsg);
    }
}


/**
 * @brief NXModuleIssue 통합 환경 설정 함수, 결과값에 에러가 없는 설정에 대한 통합 설정 함수
 * @param type 설정할 환경에 대한 종류 ex) USIM, NFC...
 * @param param1  usim: section ex)0001-라온, 0002-드림, 0004-수미온|
 * @param param2  usim: downloadURL									|
 * @param param3  usim: siteDomainURL								|
 * @param param4  usim: serverIP									|
 * @param param5  usim: serverPort									|
 * @param param6  usim: siteCode									|
 * @param param7  usim: modeCode									|
 * @param param8  													|
 * @param param9  													|
 * @param param10 													|
*/
function NXsetIntegrationIssue(type, param1, param2, param3, param4, param5, param6, param7, param8, param9, param10) {
    //사용안함
    var cmd = 'NXsetIntegration.setIntegrationIssue';
    var Data = {
        'type': type,
        'param1': param1,
        'param2': param2,
        'param3': param3,
        'param4': param4,
        'param5': param5,
        'param6': param6,
        'param7': param7,
        'param8': param8,
        'param9': param9,
        'param10': param10

    };
    var param = JSON.stringify(Data);

    secukitnxInterface.SecuKitNX_EX(cmd, param);
}


function NXsetIntegrationIssueCallback(reply) {
    var errorCheck = -1;
    try {
        errorCheck = reply.ERROR_CODE;
    } catch (err) {
        //console.log(err);
    }

    if (errorCheck === undefined) {
        //console.log(' Set Config Result : ' + reply.setIntegration);

    } else {
        $('.nx-cert-select').hide(); $('#nx-pki-ui-wrapper').hide(); KICA_Error.init();
        KICA_Error.setError(reply.ERROR_CODE, reply.ERROR_MESSAGE);
        var errorMsg = KICA_Error.getError();
        alert(errorMsg);
    }
}

/***********************************************
     set configuration before issue,reissue
************************************************/
function NXIssueConfigSet() {

    // CA IP & PORT
    NXsetCommonInfo();

    // init target media info
    TargetMediaInfo.init();

    // generate storage location 
    //NXsetBanner_TargetMedia();
    TargetDialogSet();
    NXStopMediaSelect2();

    // set media in selection cert box
    SelectDialogSet();
    NXStopMediaSelect();

    var msg = NX_MEDIA_SELECT_TEXT_4;
    document.getElementById("targetMedia_noti").innerHTML = msg;
}

/***********************************************
     set configuration before selecttion cert box
************************************************/
function NXConfigSet() {

    // CA IP & PORT
    NXsetCommonInfo();

    // init target media info
    TargetMediaInfo.init();

    // generate storage location 
    //NXsetBanner_TargetMedia();
    TargetDialogSet();
    NXStopMediaSelect2();

    // set media in selection cert box
    SelectDialogSet();
    NXStopMediaSelect();
}