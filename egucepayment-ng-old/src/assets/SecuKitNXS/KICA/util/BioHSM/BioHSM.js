/**
 * @public
 * @class
 * @description  BioHSM 정보 추출 함수 정의 class
 */
var BioHSM = (function () {

    var b_selectBioHSM = false;

    var BioHSM_DeviceCompanyCode = '',   //지문보안HSM 장치의 제조사 코드 
        BioHSM_DeviceSerial = '',   //지문보안HSM 장치의 일련번호 
        BioHSM_UserCode = '',   //지문보안HSM 장치의 사용자 번호 
        BioHSM_CompanyCode = '',   //지문보안HSM 장치의 법인 사업자 번호 
        BioHSM_DeviceAuthValue = '';   //지문보안HSM 장치의 장치 인증값 

    /**
    * @public
    * @memberof BioHSM
    * @method init
    * @description BioHSM 정보를 초기화 한다.
    * 초기화 정보 : 제조사 코드, 장치 일련번호, 사용자 번호, 법인 사업자 번호, 장치 인증값
    */
    var init = function () {
        //정보 초기화
        BioHSM_DeviceCompanyCode = '';
        BioHSM_DeviceSerial = '';
        BioHSM_UserCode = '';
        BioHSM_CompanyCode = '';
        BioHSM_DeviceAuthValue = '';
        selectBioHSM = false;
    };

    var setb_SelectBioHSM = function (flag) {
        b_selectBioHSM = flag;
    };

    var getb_SelectBioHSM = function () {
        return b_selectBioHSM;
    };

    /**
    * @public
    * @memberof BioHSM
    * @method setBioHSM_Info
    * @description BioHSM 장치 정보 설정
    */
    var setBioHSM_Info = function () {
        //@Todo 변수 확인
    };

    /**
    * @public
    * @memberof BioHSM
    * @method getBioHSM_DeviceCompanyCode
    * @description BioHSM 제조사 코드 정보를 리턴한다.
    */
    var getBioHSM_DeviceCompanyCode = function () {
        //console.log(BioHSM_DeviceCompanyCode);
        return BioHSM_DeviceCompanyCode;
    };

    /**
    * @public
    * @memberof BioHSM
    * @method getBioHSM_DeviceSerial
    * @description BioHSM 장치 일련번호를 리턴한다.
    */
    var getBioHSM_DeviceSerial = function () {
        //console.log(BioHSM_DeviceSerial);
        return BioHSM_DeviceSerial;
    };

    /**
    * @public
    * @memberof BioHSM
    * @method getBioHSM_UserCode
    * @description BioHSM 사용자 번호를 리턴한다.
    */
    var getBioHSM_UserCode = function () {
        //console.log(BioHSM_UserCode);
        return BioHSM_UserCode;
    };

    /**
    * @public
    * @memberof BioHSM
    * @method getBioHSM_CompanyCode
    * @description BioHSM 사업자 번호를 리턴한다.
    */
    var getBioHSM_CompanyCode = function () {
        //console.log(BioHSM_CompanyCode);
        return BioHSM_CompanyCode;
    };

    /**
    * @public
    * @memberof BioHSM
    * @method getBioHSM_DeviceAuthValue
    * @description BioHSM 장치 검증 값을 리턴한다.
    */
    var getBioHSM_DeviceAuthValue = function () {
        //console.log(BioHSM_DeviceAuthValue);
        return BioHSM_DeviceAuthValue;
    };

    /**
     * @public
     * @memberof BioHSM
     * @method getDeviceInformation
     * @description 장치 정보를 가져온다.
     * @param {String} certID
     * @param {String} extravalue   지문보안토큰 정보추출 구분 ("NEW" : 장치정보 객체 생성 후 진행(최초), "" NULL : certID만으로 정보 추출)                          
     * @param {String} extravalue2  Random 값
     */
    var getDeviceInformation = function (certID, extravalue, extravalue2) {
        if ((InsertNullCheck(certID) === false)) {
            var cmd = 'BioHSM.getDeviceInformation';
            var Data = {
                'certID': certID,
                'extraValue': extravalue,
                'extraValue2': extravalue2
            };
            var param = JSON.stringify(Data);
            secukitnxInterface.SecuKitNX(cmd, param);
        } else {
            $('.nx-cert-select').hide(); $('#nx-pki-ui-wrapper').hide(); KICA_Error.init();
            var location = 'BioHSM.getDeviceInformation';
            var reason = '';
            var errorcode = '';

            if (InsertNullCheck(certID)) {
                reason = 'certID';
                errorcode = 'ERR_SCRIPT_BIOHSM_NO_SELECT_CERT';
            }

            KICA_ERROR_RESOURCE.ErrorMessage(location, reason, errorcode);
            var ScriptErrorMessage = KICA_Error.getScriptError();
            alert(ScriptErrorMessage);
        }
    };

    /**
    * @public
    * @memberof BioHSM
    * @method getDeviceInformationCallback
    * @description getDeviceInformation 콜백함수 
    * @param reply 콜백 데이터
    * @return 
    *      성공 : BioHSM등 장치 정보, 실패 : ERROR_CODE:에러코드, ERROR_MESSAGE:에러메시지
    */
    var getDeviceInformationCallback = function (reply) {
        var errorCheck = -1;
        try {
            errorCheck = reply.ERROR_CODE;
        } catch (err) {
            //console.log(err);
        }

        if (errorCheck === undefined) {
            alert('BioHSM getDeviceInformation Success');
            BioHSM_DeviceCompanyCode = reply.manufacture;        //manufacture : 제조사 코드
            BioHSM_DeviceSerial = reply.csn;                //csn : 일련번호
            BioHSM_UserCode = reply.person;             //person : 사용자 번호
            BioHSM_CompanyCode = reply.corporation;        //corporation : 법인 사업자번호
            BioHSM_DeviceAuthValue = reply.deviceAuth;         //deviceAuth : 장치 인증값
        }
        else {
            $('.nx-cert-select').hide(); $('#nx-pki-ui-wrapper').hide(); KICA_Error.init();
            KICA_Error.setError(reply.ERROR_CODE, reply.ERROR_MESSAGE);
            var errorMsg = KICA_Error.getError();
            alert(errorMsg);
        }
    };

    return {
        init: init,

        setb_SelectBioHSM: setb_SelectBioHSM,
        getb_SelectBioHSM: getb_SelectBioHSM,

        setBioHSM_Info: setBioHSM_Info,
        getBioHSM_DeviceCompanyCode: getBioHSM_DeviceCompanyCode,
        getBioHSM_DeviceSerial: getBioHSM_DeviceSerial,
        getBioHSM_UserCode: getBioHSM_UserCode,
        getBioHSM_CompanyCode: getBioHSM_CompanyCode,
        getBioHSM_DeviceAuthValue: getBioHSM_DeviceAuthValue,

        getDeviceInformation: getDeviceInformation,
        getDeviceInformationCallback: getDeviceInformationCallback
    };
})();