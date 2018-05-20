/**
 * @public
 * @class
 * @description define functions that interation with WebUI
 */
var Dialog = (function () {

    var initDialog = function () {
        //reset cert List of selection cert box
        NX_Issue_pubUi.certListReset();
    };

    /**
   * @public
   * @memberof Dialog
   * @method selectStorage
   * @description getting all list of certicicate in choosen media
   * @param {String} mediaType storage(HDD, HSM, USB, etc..)
   * @param {String} extraValue HDD - NULL, HSM - NULL or name of Drive
   */
    var selectStorage = function (mediaType, extraValue) {
        if (InsertNullCheck(mediaType) === false) {
            if (mediaType === "USIM" || mediaType === "usim") {
                extraValue = USIM_SITECODE;
            }

            var logicFlag = processLogic.getProcessLogic();
            var cmd;
            if ((logicFlag.indexOf('ISSUE') !== -1) || (logicFlag.indexOf('MANAGEMENT') !== -1)) {
                // issue module
                cmd = 'Dialog.selectStorageIssue';
            } else {
                // use module
                cmd = 'Dialog.selectStorage';
            }
            var Data = {
                'mediaType': mediaType,
                'extraValue': extraValue
            };
            var param = JSON.stringify(Data);
            secukitnxInterface.SecuKitNX(cmd, param);
        } else {
            $('.nx-cert-select').hide(); $('#nx-pki-ui-wrapper').hide(); KICA_Error.init();
            var location = 'Dialog.selectStorage';
            var reason = 'mediaType';
            KICA_ERROR_RESOURCE.ErrorMessage(location, reason, 'ERR_SCRIPT_DIALOG_INPUT');
            var ScriptErrorMessage = KICA_Error.getScriptError();
            alert(ScriptErrorMessage);
        }
    };

    /**
     * @public
     * @memberof Dialog
     * @method selectStorageCallback
     * @description selectStorage callback function
     * @param reply callback data
     * @returns 
     *          input mediaType HDD : list of cert
     *          input mediaType HSM : list of device (Driver list), input HSM & Driver name : list of cert 
     */
    var selectStorageCallback = function (reply) {
        var errorCheck = -1;
        try {
            errorCheck = reply.ERROR_CODE;
        } catch (err) {
            //console.log(err);
        }

        if (errorCheck === undefined) {

            //save the infomation of cert list
            certListInfo.setCertListInfo(reply);

            //HDD
            if (reply.mediaType === 'HDD') {
                var listObj = new MakeCertList();
                listObj.init();
                var data = listObj.makeCertListHTML(reply);
                $('#select-cert-list #select-cert-list-area').remove();
                $('#select-cert-list').append(data);

                // guide of input passwd
                document.getElementById("pwdnoti_1").innerHTML = NX_WEBUI_SELECT_TEXT_8;
                document.getElementById("pwdnoti_2").innerHTML = NX_WEBUI_SELECT_TEXT_9;
            }

            //removable Disk
            if (reply.mediaType === 'USB') {
                var listObj = new MakeCertList();
                listObj.init();
                var data = listObj.makeCertListHTML(reply);
                $('#select-cert-list #select-cert-list-area').remove();
                $('#select-cert-list').append(data);

                // guide of input passwd
                document.getElementById("pwdnoti_1").innerHTML = NX_WEBUI_SELECT_TEXT_8;
                document.getElementById("pwdnoti_2").innerHTML = NX_WEBUI_SELECT_TEXT_9;
            }

            //USIM
            if (reply.mediaType === 'USIM') {
            }

            //secure token
            if (reply.mediaType === 'HSM') {
                var listObj = new MakeCertList();
                listObj.init();
                var data = listObj.makeCertListHTML(reply);
                $('#select-cert-list #select-cert-list-area').remove();
                $('#select-cert-list').append(data);

                // guide of input PIN number
                document.getElementById("pwdnoti_1").innerHTML = NX_WEBUI_SELECT_TEXT_10;
                document.getElementById("pwdnoti_2").innerHTML = NX_WEBUI_SELECT_TEXT_11;
            }

            //bio secure token
            if (reply.mediaType === 'BIOHSM') {
                var listObj = new MakeCertList();
                listObj.init();
                var data = listObj.makeCertListHTML(reply);
                $('#select-cert-list #select-cert-list-area').remove();
                $('#select-cert-list').append(data);

                // guide of input PIN number
                document.getElementById("pwdnoti_1").innerHTML = NX_WEBUI_SELECT_TEXT_10;
                document.getElementById("pwdnoti_2").innerHTML = NX_WEBUI_SELECT_TEXT_11;
            }

            // first certificate is choosen by default - 160414
            NX_Issue_pubUi.certSelectRow_CertList(1);
            certListInfo.setCertListIndex(reply[1].index);

            $("#divtable").colResizable({ liveDrag: true });
        }
        else if (reply.ERROR_CODE) {
            KICA_Error.init();
            if (reply.ERROR_CODE === '357236736') {
            } else {
                KICA_Error.setError(reply.ERROR_CODE, reply.ERROR_MESSAGE);
                var errorMsg = KICA_Error.getError();
                alert(errorMsg);
            }
        }
    };

    /**
     * @public
     * @memberof Dialog
     * @method selectStorageCallback
     * @description selectStorage callback function
     * @param reply callback data
     * @returns 
     *          input mediaType HDD : list of cert
     *          input mediaType HSM : list of device (Driver list), input HSM & Driver name : list of cert  
     */
    var selectStorageIssueCallback = function (reply) {
        var errorCheck = -1;
        try {
            errorCheck = reply.ERROR_CODE;
        } catch (err) {
            //console.log(err);
        }

        if (errorCheck === undefined) {

            //save the infomation of cert list
            certListInfo.setCertListInfo(reply);

            //HDD
            if (reply.mediaType === 'HDD') {
                var listObj = new MakeCertList();
                listObj.init();
                var data = listObj.makeCertListHTML(reply);
                $('#select-cert-list #select-cert-list-area').remove();
                $('#select-cert-list').append(data);
            }

            //removable Disk
            if (reply.mediaType === 'USB') {
                var listObj = new MakeCertList();
                listObj.init();
                var data = listObj.makeCertListHTML(reply);
                $('#select-cert-list #select-cert-list-area').remove();
                $('#select-cert-list').append(data);

            }

            //USIM
            if (reply.mediaType === 'USIM') {
            }

            //secure token
            if (reply.mediaType === 'HSM') {
                var listObj = new MakeCertList();
                listObj.init();
                var data = listObj.makeCertListHTML(reply);
                $('#select-cert-list #select-cert-list-area').remove();
                $('#select-cert-list').append(data);

                // guide of input PIN number
                document.getElementById("pwdnoti_1").innerHTML = NX_WEBUI_SELECT_TEXT_10;
                document.getElementById("pwdnoti_2").innerHTML = NX_WEBUI_SELECT_TEXT_11;
            }

            //bio secure token
            if (reply.mediaType === 'BIOHSM') {
                var listObj = new MakeCertList();
                listObj.init();
                var data = listObj.makeCertListHTML(reply);
                $('#select-cert-list #select-cert-list-area').remove();
                $('#select-cert-list').append(data);

                // guide of input PIN number
                document.getElementById("pwdnoti_1").innerHTML = NX_WEBUI_SELECT_TEXT_10;
                document.getElementById("pwdnoti_2").innerHTML = NX_WEBUI_SELECT_TEXT_11;
            }

            // first certificate is choosen by default - 160414
            NX_Issue_pubUi.certSelectRow_CertList(1);
            certListInfo.setCertListIndex(reply[1].index);

            $("#divtable").colResizable({ liveDrag: true });
        }
        else if (reply.ERROR_CODE) {
            KICA_Error.init();
            if (reply.ERROR_CODE === '0') {
                //console.log(NX_MEMBER_OF_DIALOG_TEXT_1);

                var listObj = new MakeCertList();
                listObj.init();
                var data = listObj.makeCertListHTML(reply);
                $('#select-cert-list #select-cert-list-area').remove();
                $('#select-cert-list').append(data);

            } else {
                KICA_Error.setError(reply.ERROR_CODE, reply.ERROR_MESSAGE);
                var errorMsg = KICA_Error.getError();
                alert(errorMsg);
            }
        }
    };

    /**
   * @public
   * @memberof Dialog
   * @description selectCertificate after put passwd 
   * @param {String}  ID cert DN, Serial   
   * @param {String}  password cert password
   * @param {String} certID  CertID or Null
   */
    var selectCertificate = function (ID, password, certID) {
        if ((InsertNullCheck(ID) === false) && (InsertNullCheck(password) === false)) {
            var logicFlag = processLogic.getProcessLogic();
            var cmd;
            if ((logicFlag.indexOf('ISSUE') !== -1) || (logicFlag.indexOf('MANAGEMENT') !== -1)) {
                // issue module (issue/reissue/renew/revoke/copy/...etc)
                cmd = 'Dialog.selectCertificateIssue';
            } else {
                // use module
                cmd = 'Dialog.selectCertificate';
            }
            var Data = {
                'ID': ID,
                'password': password,
                'certID': certID
            };
            var param = JSON.stringify(Data);
            secukitnxInterface.SecuKitNX(cmd, param);
        } else {
            KICA_Error.init();
            var location = 'Dialog.selectCertificate';
            var reason = '';

            if (InsertNullCheck(ID)) {
                reason = 'ID';
            }

            if (InsertNullCheck(password)) {
                reason = 'password';
            }

            KICA_ERROR_RESOURCE.ErrorMessage(location, reason, 'ERR_SCRIPT_DIALOG_INPUT');
            var ScriptErrorMessage = KICA_Error.getScriptError();
            alert(ScriptErrorMessage);
        }
    };

    /**
     * @public
     * @memberof GetCertInfo
     * @description Callback function(selectCertificate) that get cert ID  
     * @param reply callback data
     * @returns
     *       success : {String} CertId   fail : false
     *       
     */
    var selectCertificateCallback = function (reply) {
        var errorCheck = -1;
        try {
            errorCheck = reply.ERROR_CODE;
        } catch (err) {
            //console.log(err);
        }
        if (errorCheck === undefined) {
            certListInfo.setCertID(reply.certID);
            var logic = processLogic.getProcessLogic();
            SecuKitNXS_RESULT(logic, reply.certID);
            $('.nx-cert-select').hide();
            $('#nx-pki-ui-wrapper').hide();
        }
        else if (reply.ERROR_CODE) {
            NXInit();
            KICA_Error.init();
            if (reply.ERROR_CODE === '357236736') {
                //console.log('');

            } else {
                KICA_Error.setError(reply.ERROR_CODE, reply.ERROR_MESSAGE);
                var errorMsg = KICA_Error.getError();
                alert(errorMsg);

            }
        }
    };


    /**
     * @public
     * @memberof GetCertInfo
     * @description Callback function(selectCertificate) that get cert ID  
     * @param reply callback data
     * @returns
     *       success : {String} CertId   fail : false
     *       
     */
    var selectCertificateIssueCallback = function (reply) {
        var errorCheck = -1;
        try {
            errorCheck = reply.ERROR_CODE;
        } catch (err) {
            //console.log(err);
        }
        if (errorCheck === undefined) {
            certListInfo.setCertID(reply.certID);
            //SecuKitNXS_RESULT(logic, reply.certID);

            //call function that getting cert infomation
            var certType = 'SignCert';
            var certID = certListInfo.getCertID();

            var Logic_flag = processLogic.getProcessLogic();
            if (Logic_flag.indexOf('RenewCertificateInfo') !== -1) {
                Dialog.viewCertInfomationWithVID(certType, certID);
            }
            else
            {
                NX_branchLogic_ISSUE();
                $('.nx-cert-select').hide();
            }
            
        }
        else {
            NXInit();
            KICA_Error.init();
            KICA_Error.setError(reply.ERROR_CODE, reply.ERROR_MESSAGE);
            var errorMsg = KICA_Error.getError();
            alert(errorMsg);
        }
    };

    /**
* @public
* @memberof Dialog
* @description infomation of cert after select certificate
* @param {String} ID order of cert(start from 1)
*/
    viewCertInfomationWithVID = function (certType, certID) {
        var isViewVID = '0';    // 0 : get VID ,  1 : donot get VID
        if ((InsertNullCheck(certID) === false)) {
            var logicFlag = processLogic.getProcessLogic();
            if ((logicFlag.indexOf('ISSUE') !== -1) || (logicFlag.indexOf('MANAGEMENT') !== -1)) {
                // 발급모듈에서 진행한 경우
                var cmd = 'Dialog.viewCertInfomationWithVIDIssue';
            } else {
                var cmd = 'Dialog.viewCertInfomationWithVID';
            }

            var Data = {
                'certType': certType,
                'certID': certID,
                'isViewVID': isViewVID
            };
            var param = JSON.stringify(Data);
            secukitnxInterface.SecuKitNX(cmd, param);
        } else {
            KICA_Error.init();
            var location = 'Dialog.viewCertInfomationWithVID';
            var reason = 'certID';
            KICA_ERROR_RESOURCE.ErrorMessage(location, reason, 'ERR_SCRIPT_DIALOG_INPUT');
            var ScriptErrorMessage = KICA_Error.getScriptError();
            alert(ScriptErrorMessage);
        }
    };

    /**
    * @public
    * @memberof Dialog
    * @description 인증서 세부 정보 리스트를 콜백 받는 함수
    * @param reply 콜백 데이터
    * @returns
    *       성공 : {Json String} 인증서 세부 정보 리스트   실패 : ERROR_CODE:에러코드, ERROR_MESSAGE:에러메시지
    */
    viewCertInfomationWithVIDIssueCallback = function (reply) {
        var errorCheck = -1;
        try {
            errorCheck = reply.ERROR_CODE;
        } catch (err) {
            console.log(err);
        }

        if (errorCheck === undefined) {
            //인증서 정보 저장
            CertInfo.init();
            CertInfo.setCertInfo(reply);

            NX_branchLogic_ISSUE();
            $('.nx-cert-select').hide();
        }
        else {
            $('#certPwd').val('');
            KICA_Error.init();
            KICA_Error.setError(reply.ERROR_CODE, reply.ERROR_MESSAGE);
            var errorMsg = KICA_Error.getError();
            alert(errorMsg);
        }
    };

    return {
        initDialog: initDialog,

        selectStorage: selectStorage,
        selectStorageCallback: selectStorageCallback,
        selectStorageIssueCallback: selectStorageIssueCallback,

        selectCertificate: selectCertificate,
        selectCertificateCallback: selectCertificateCallback,
        selectCertificateIssueCallback: selectCertificateIssueCallback,

        viewCertInfomationWithVID: viewCertInfomationWithVID,
        //viewCertInfomationWithVIDCallback: viewCertInfomationWithVIDCallback,
        viewCertInfomationWithVIDIssueCallback: viewCertInfomationWithVIDIssueCallback
    };

})();

/**
 * @public
 * @memberof Dialog
 * @description 인증서 선택 후 세부 정보 리스트를 요청하는 함수
 * @param {String} ID 인증서 리스트상의 순번(1부터 시작)
 */
function NXviewCertDetailInfomation(ID) {
    if (InsertNullCheck(ID) === false) {
        var logicFlag = processLogic.getProcessLogic();
        var cmd;
        if ((logicFlag.indexOf('ISSUE') !== -1) || (logicFlag.indexOf('MANAGEMENT') !== -1)) {
            // 발급모듈에서 진행한 경우
            cmd = 'NXviewCertDetailInfomation.viewCertDetailInfomationIssue';
        } else {
            // 이용모듈에서 진행한 경우
            cmd = 'NXviewCertDetailInfomation.viewCertDetailInfomation';
        }

        var Data = {
            'ID': ID
        };
        var param = JSON.stringify(Data);
        secukitnxInterface.SecuKitNX_EX(cmd, param);
    } else {
        KICA_Error.init();
        var location = 'Dialog.viewCertDetailInfomation';
        var reason = 'ID';
        KICA_ERROR_RESOURCE.ErrorMessage(location, reason, 'ERR_SCRIPT_DIALOG_INPUT');
        var ScriptErrorMessage = KICA_Error.getScriptError();
        alert(ScriptErrorMessage);
    }
}

/**
 * @public
 * @memberof Dialog
 * @description 인증서 세부 정보 리스트를 콜백 받는 함수
 * @param reply 콜백 데이터
 * @returns
 *       성공 : {Json String} 인증서 세부 정보 리스트   실패 : ERROR_CODE:에러코드, ERROR_MESSAGE:에러메시지
 *
 * CertInfo = {
    *       CertString : 서명용 인증서 ex) MIIFzzCCBLegAwIBAgIEArGBxDANBgkqhkiG9w0BAQsFADBKMQswCQYDVQQGEwJLUjENMA...,
    *       UserDN : DN ex) cn=테스트(법인-A),ou=RA센터,ou=TEST인증서,ou=TEST발급용,ou=licensedCA,o=KICA,c=KR,
    *       SignatureAlgorithmID : 서명 알고리즘 정보 ex) sha256WithRSAEncryption,
    *       Version : 인증서 버전 정보 ex) Version 3,
    *       Serial : 시리얼 정보,
    *       IssueDN : 발행기관 DN  ex) cn=signGATE CA4,ou=AccreditedCA,o=KICA,c=KR,
    *       Issuer : 발행기관 ex) KICA,
    *       UserPublicKeyAlgorithmID : ex) rsaEncryption,
    *       ValidateFrom : 인증서 유효기간 ex) 2014-06-23 11:32:00,
    *       ValidateTo : 인증서 유효기간 ex) 2015-07-19,
    *       UserName : ex) 테스트(법인-A),
    *       Usage : ex) digitalSignature,nonRepudiation,
    *       Policy : ex) 1.2.410.200004.5.2.1.1,
    *       AuthorityKeyID :  ex) ae52fd0e0e01f83086377e...,
    *       UserKeyID : ex) bf83c19c48c310ee47473ec38696...,
    *       PublicKey : ex) 3082010a0282010100cd344ba481a86876a7a3e52681f4f34e287e1695657ae308031725fafe8af56dda1c...,
    *       PublicKeySize: ex) 2160
    *       }
    */
function NXviewCertDetailInfomationCallback(reply) {
    var errorCheck = -1;
    try {
        errorCheck = reply.ERROR_CODE;
    } catch (err) {
        //console.log(err);
    }

    if (errorCheck === undefined) {
        var makeCertListObj = new MakeCertList();
        var data = makeCertListObj.makeCertDetailInfoHTML(reply);
        $('#cert-detail-info').remove();
        $('#certContent_01_box').append(data);
        //인증서 선택창 hide
        $('#nx-cert-select').hide();
        //인증서 정보창 show
        $('#nx-cert-detail-info').show();
    }
    else {
        KICA_Error.init();
        KICA_Error.setError(reply.ERROR_CODE, reply.ERROR_MESSAGE);
        var errorMsg = KICA_Error.getError();
        alert(errorMsg);
    }
}

//인증서 선택창(Web UI) 노출
function NX_ShowDialog() {

    if (SECUKITNX_CONST.load) {
        try {
            // 환경 설정 함수 호출
            var flag = processLogic.getProcessLogic();
            if ((flag.indexOf('ISSUE') !== -1) || (flag.indexOf('MANAGEMENT') !== -1)) {
                // 환경설정 Set
                NXIssueConfigSet();
            } else {
                // 환경설정 Set
                NXConfigSet();
            }
        } catch (e) { }

        //인증서 선택창 WebUI 노출
        Dialog.initDialog();
        NX_Issue_pubUi.certListReset();
        setTimeout(function () {
            $('#nx-pki-ui-wrapper').show();
            $('#nx-cert-select').show();
            $("#certPwd").attr("tabindex", -1).focus();

            //디폴트로 하드디스크 저장매체 선택 표시시
            var flag = NX_SELECT_CERT_MEDIA[0].DEFAULT;
            if (flag == 'able') {
                NX_SetMediaHDD();
            }

        }, 300);

        if (NX_DIALOG_MOVE === true) {
            try {
                //인증서 선택창 커서 이동을 위해 삽입
                $(".nx-pki-ui-wrapper").draggable({ handle: ".pki-head2", cursor: "move" });
                //$(".nx-pki-ui-wrapper").draggable({ handle: ".pki-wrap", cursor: "move" });
            } catch (e) { }
        }
    }
}
