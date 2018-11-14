//JQuery 하위 호환성 문제를 해결하기 위해 $ 재정의
//var NXJQUERY = $.noConflict(false);

//if (typeof NX_Issue_pubUi !== "undefined") { alert(NX_ISSUE_PUB_TEXT_1); } else {
if (typeof NX_Issue_pubUi === "12314142") { alert(NX_ISSUE_PUB_TEXT_1); } else {
	var NX_Issue_pubUi = {};
    /* input placeholder */
    NX_Issue_pubUi.placeholder = function () {
        var input = $('.nx-pki-ui-wrapper .js-placeholder').next('input:enabled'); //활성화된 텍스트박스
        var label = $('.nx-pki-ui-wrapper input:enabled').siblings('.js-placeholder'); //활성화된 텍스트박스의 레이블

        //최초 로딩시 바인딩된 이벤트를 제거
        $('.nx-pki-ui-wrapper .js-placeholder').next('input').off('focus, blur, change');
        $('.nx-pki-ui-wrapper input').siblings('.js-placeholder').off('click');

        //이벤트 할당
        input.on({
            focus: function () {
                $(this).prev('.js-placeholder').css('visibility', 'hidden');
            },
            blur: function () {
                if ($(this).val() === '' && $(this).html() === '') {
                    $(this).prev('.js-placeholder').css('visibility', 'visible');
                }
                else {
                    $(this).prev('.js-placeholder').css('visibility', 'hidden');
                }
            },
            change: function () {
                if ($(this).val() === '' && $(this).html() === '') {
                    $(this).prev('.js-placeholder').css('visibility', 'visible');
                }
                else {
                    $(this).prev('.js-placeholder').css('visibility', 'hidden');
                }
            }
        }).blur();
        label.on('click', function () {
            $(this).css('visibility', 'hidden');
            $(this).next('input').focus();
        });
    };

    /***********************************************
    공통 : 추가매체 창 닫기 버튼 클릭시
    ************************************************/
    NX_Issue_pubUi.moreSaveMediaHide5 = function () {
        TargetMediaInfo.setMediaType('');
        TargetMediaInfo.setExtraValue('');
        $("#MediaSet_1>li>button.active").removeClass("active");
        $("#MediaSet_2>li>button.active").removeClass("active");

        var msg = NX_ISSUE_PUB_TEXT_2 + ' :';
        document.getElementById("targetMedia_noti").innerHTML = msg;

        $('.cert-location-area .pki-wrap4').fadeOut('fast');
        $('.cert-location-area .pki-wrap3').fadeOut('fast');
    };

    /***********************************************
    인증서 갱신 안내
    ************************************************/
    NX_Issue_pubUi.moreSaveMediaHide4 = function () {
        $('#nx-error-cert-update').fadeOut('fast');
    };

    /* 인증서 목록 전체삭제-초기화 */
    NX_Issue_pubUi.certListReset = function () {
        $('#select-cert-list-area').remove();
        $('#select-cert-list').append(KICA_CERTLISTEMPTY);
    };

    /* 저장매체 목록 선택시 컬러변경 */
    NX_Issue_pubUi.certSelectRow = function (index) {
        NXInit();
        var index_ = index - 1;
        var obj = $('.nx-pki-ui-wrapper .js-selrow');
        obj.find('tr').each(function (n) {
            $(this).removeClass('on');
            if (n === index_) {
                $(this).addClass('on');
            }
            $(this).on('click', function () {
                obj.find('tr').each(function (n) {
                    $(this).removeClass('on');
                });
                $(this).addClass('on');
                return false;
            });
        });
    };

    /* 인증서 선택창 목록 선택시 컬러변경 */
    NX_Issue_pubUi.certSelectRow_CertList = function (index) {
        NXInit();
        var obj = $('.nx-pki-ui-wrapper .js-selrow-certlist');
        obj.find('tr').each(function (n) {
            $(this).removeClass('on');

            if (n === index) {
                $(this).addClass('on');
            }

            $(this).on('click', function () {
                obj.find('tr').each(function (n) {
                    $(this).removeClass('on');
                });
                $(this).addClass('on');
                return false;
            });
        });
    };

    /* 인증서 id값 리턴 */
    NX_Issue_pubUi.certIdReturn = function (_this) {
        $('.nx-pki-ui-wrapper #NX_SelectCertIndex').val(_this.id);
        certListInfo.setCertListIndex(_this.id);

        //인증서 유효기간 검사
        var certListObj = certListInfo.getCertListInfo();
        var fullCertTodate = certListObj[_this.id].todate;
        var certTodate = fullCertTodate.split('-');
        var d = new Date();
        var NowYear = ZerosDate(d.getFullYear(), 4);
        var NowMonth = ZerosDate(d.getMonth() + 1, 2);
        var NowDate = ZerosDate(d.getDate(), 2);
        var selectCertTodateString = certTodate[0] + certTodate[1] + certTodate[2];
        var dueDateString = NowYear + NowMonth + NowDate;

        // 만료기한 계산 : 보완이 좀 필요하다!!
        var restDate = getDifferentDays(dueDateString, selectCertTodateString);
        var restDateInt = parseInt(restDate);

        if ((restDateInt <= 31) && (restDateInt >= 0)) {
            // 기존 메시지 내용 삭제
            $('#nx-error-cert-update-msg-contents').remove();

            var updateMessage = '<div id=\"nx-error-cert-update-msg-contents\">';
            updateMessage += '<div class=\"pki-body5\">';
            updateMessage += '<p class=\"txt-l\" style=\"line-height:1.5; font-size:13px;\">' + NX_ISSUE_PUB_TEXT_3 + '<br />';
            updateMessage += '' + NX_ISSUE_PUB_TEXT_4 + '<br />';
            updateMessage += '<strong>' + certTodate[0] + NX_ISSUE_PUB_TEXT_5 + certTodate[1] + NX_ISSUE_PUB_TEXT_6 + certTodate[2] + NX_ISSUE_PUB_TEXT_7 + '</strong>' + NX_ISSUE_PUB_TEXT_8 + ' </p></div>';
            updateMessage += '<div class=\"pki-bottom5\">';
            updateMessage += '<div class=\"gray-box2 mb10\" style=\"line-height:1.5; font-size:13px;\">' + NX_ISSUE_PUB_TEXT_9 + '(' + NowYear + '/' + NowMonth + '/' + NowDate + ')' + NX_ISSUE_PUB_TEXT_10 + ' <strong>' + restDateInt + '' + NX_ISSUE_PUB_TEXT_11 + '</strong> ' + NX_ISSUE_PUB_TEXT_12 + '</div>';
            updateMessage += '<p class=\"txt-l\" style=\"line-height:1.5; font-size:13px;\"><a href=\"http://www.signgate.com\"><strong>http://www.signgate.com</strong></a>' + NX_ISSUE_PUB_TEXT_13 + '<br />';
            updateMessage += '' + NX_ISSUE_PUB_TEXT_14 + '</p> ';
            updateMessage += '<button class=\"btn-ok\" onclick=\"NX_Issue_pubUi.moreSaveMediaHide4();return false;\">' + NX_ISSUE_PUB_TEXT_15 + '</button></div></div>';

            // 신규 메시지 append
            $('#nx-error-cert-update-msg-box').append(updateMessage);
            $('#nx-error-cert-update').show();

            if (NX_DIALOG_MOVE === true) {
                try {
                    //추가 미디어 창 커서 이동을 위해 추가
                    $(".pki-wrap5").draggable({ handle: ".pki-head5", cursor: "move" });
                } catch (e) { console.log(e); }
            }
        }
    };

    /* 확장매체 클릭시 */
    NX_Issue_pubUi.moreSaveMediaShow = function () {
        // 추가매체 창 제거
        NX_Issue_pubUi.moreSaveMediaHide5();
        // 첫번째 인증서 선택된 index값 삭제
        certListInfo.setCertListIndex('');

        $('.cert-location-area .sub-layer').fadeIn('fast');
    };

    NX_Issue_pubUi.moreSaveMediaHide = function () {
        $('.cert-location-area .sub-layer').fadeOut('fast');
    };

    /* 추가매체 클릭시 */
    NX_Issue_pubUi.moreSaveMediaShow2 = function (media, extra) {
        // 확장매체 표시 제거
        NX_Issue_pubUi.moreSaveMediaHide();
        // 첫번째 인증서 선택된 index값 삭제
        certListInfo.setCertListIndex('');

        // 유심인 경우에는 추가 매체창이 출력되지 않도록
        // 나중에 서비스업체별 선택사항으로 변경시 if문 제거
        if (media === "USIM" || media === "usim") {
            $('.cert-location-area .pki-wrap3').hide();
        } else {
            $('.cert-location-area .pki-wrap3').fadeIn('fast');
        }

        SelectMediaInfo.setMediaType(media);//미디어 정보 Set
        SelectMediaInfo.setExtraValue(extra);
        //추가 정보(드라이브명 등)를 얻기 위한 함수 호출
        targetMediaFlag.init();
        targetMediaFlag.setFlag(false);
        //인증서 리스트 초기화
        NX_Issue_pubUi.certListReset();
        setTimeout(function () { NX_selectStorage(media, extra); }, 100);
    };

    NX_Issue_pubUi.moreSaveMediaHide2 = function () {
        var media = SelectMediaInfo.getMediaType();
        var extra = SelectMediaInfo.getExtraValue();

        if (extra === '' || extra === "NULL") {
            alert(NX_ISSUE_PUB_TEXT_16);
        } else {
            $('.cert-location-area .pki-wrap3').fadeOut('fast');

            // Token인 경우 KISA 검증 여부 판단
            if (media === 'HSM' || media === 'BIOHSM') {
                var tmpObj = TokenMediaInfo.getTokenMediaObj();
                var tokenVrf = '';

                var i;
                for (i = 1 ; i <= tmpObj.size; i++) {
                    if (extra === tmpObj[i].diskName) {
                        tokenVrf = tmpObj[i].verify;

                        if (tokenVrf === 'N') {
                            alert(NX_ISSUE_PUB_TEXT_32);
                        }
                    }
                }
            }

            // BIOHSM인 경우
            if (media === 'BIOHSM') {
                var Tmp_BioTokenInfo = '';
                Tmp_BioTokenInfo = extra.split('|');
                extra = Tmp_BioTokenInfo[1];
            }

            // 인증서 리스트 호출
            Dialog.selectStorage(media, extra);
        }
    };

    /* 인증서 보기 탭클릭 */
    NX_Issue_pubUi.certTabClick = function (_this) {
        var obj = $('.pki-tab');
        obj.find('a').removeClass('on');
        $(_this).addClass('on');
        $('.cert-content').hide();
        $($(_this).attr('href')).show();
        return false;
    };

    /* 인증서 선택창 Extra Value id값 리턴 */
    NX_Issue_pubUi.extraValueIdReturn = function (_this) {
        //NX_Issue_pubUi.certSelectRow();
        SelectMediaInfo.setExtraValue(_this.id);
    };

    /* 인증서 선택창 확인 버튼  */
    NX_Issue_pubUi.selectCertConfirm = function () {
        var media = SelectMediaInfo.getMediaType();
        var extra = SelectMediaInfo.getExtraValue();
        var Logic_flag = processLogic.getProcessLogic();

        //갱신창이 열려있는 경우 닫기
        $('#nx-error-cert-update').fadeOut('fast');

        // 인증서 선택 유무 확인
        var selectIndex = certListInfo.getCertListIndex();
        if (selectIndex === '') {
            alert(NX_WEBUI_SELECT_TEXT_5);
            return;
        }

        // 삭제 기능인 경우
        if (Logic_flag === 'KICA.MANAGEMENT.DeleteCertificate') {
            NX_Issue_pubUi.deleteCertificate();

        } else {
            var certIndex = certListInfo.getCertListIndex();
            var pwd = '';
            if (NOS_F === true) {
                // 잉카 키보드보안 동작하는 경우
                var nosData = npPfsCtrl.GetReplaceField('formRe', 'RePwName');   //NOS
                var nosTable = npPfsCtrl.GetResultField('formRe', 'RePwName');   //NOS
                pwd = nosTable + '|' + nosData; //  구분자 '|'

            } else if (RAON_F === true) {
                GetEncYT();
                return;
            }
            else {
                pwd = $('#certPwd').val();
                if (pwd === '' && (media !== 'BIOHSM' || media !== 'HSM')) { alert(NX_WEBUI_SELECT_TEXT_8); return; }
                else if (pwd === '' && (media === 'BIOHSM' || media === 'HSM')) { alert(NX_WEBUI_SELECT_TEXT_10); return; }
            }

            // 관리 기능인 경우
            if ((Logic_flag === 'KICA.MANAGEMENT.CheckPassword') ||
                (Logic_flag === 'KICA.MANAGEMENT.CopyCert')) {
                CertManagement.init();
                CertManagement.setPwd(pwd);
            }

            NXInit();
            var tmp = certListInfo.getCertID();
            var certID = '';

            if (tmp === '' || tmp === null || tmp === undefined) {
                certID = '';
            } else {
                certID = tmp;
            }

            Dialog.selectCertificate(certIndex, pwd, certID);
        }
    };

    /* 인증서 선택창 취소 버튼  */
    NX_Issue_pubUi.selectCertCancel = function () {
        // 인증서 선택창에서 선택한 인증서 Index 초기화
        certListInfo.setCertListIndex('');

        //갱신창이 열려있는 경우 닫기
        $('#nx-error-cert-update').fadeOut('fast');
        NXInit();
        $('#nx-cert-select').hide();
        $('#nx-pki-ui-wrapper').hide();
    };

    /***********************************************
     인증서 보기 버튼
     ************************************************/
    NX_Issue_pubUi.viewCertInfo = function () {
        var selectCertIndex = certListInfo.getCertListIndex();
        if (selectCertIndex !== '') {
            NXviewCertDetailInfomation(selectCertIndex);
        } else {
            alert(NX_ISSUE_PUB_TEXT_34);
        }
    };
    /* 인증서 정보창 닫기 버튼  */
    NX_Issue_pubUi.viewCertInfoConfirm = function () {
        certListInfo.setCertListIndex('');
        $('#nx-cert-detail-info').hide();
        $('#nx-cert-select').show();
    };

    /**############################################################ 발급/관리 모듈 전용 ###########################################################**/

    /***********************************************
     발급 모듈 : 관리버튼
     ************************************************/
    NX_Issue_pubUi.managemnetPageOpen = function () {
        //인증서 선택창 hide
        $('#nx-cert-select').hide();
        //관리창 show
        $('#nx-cert-managemnet').show();
    };

    /***********************************************
    발급 모듈 : 관리창 확인 버튼
    ************************************************/
    NX_Issue_pubUi.managemnetPageOpenConfirm = function () {
        //관리창 show
        $('#nx-cert-managemnet').hide();
        //인증서 선택창 hide
        $('#nx-cert-select').show();
    };

    /***********************************************
     발급 모듈 : 인증서 삭제 버튼
    ************************************************/
    NX_Issue_pubUi.deleteCertificate = function () {
        var certPwd = $('#certPwd').val();
        var selectCertIndex = certListInfo.getCertListIndex();
        var certID = certListInfo.getCertID();
        if ((selectCertIndex !== '') && (certPwd !== '')) {
            // 인증서 삭제 로직 설정
            processLogic.setProcessLogic('KICA.MANAGEMENT.DeleteCertificate');
            Dialog.selectCertificate(selectCertIndex, certPwd, certID);
        } else {
            NXInit();
            alert(NX_ISSUE_PUB_TEXT_17);
        }
    };

    /* 인증서 삭제 예 클릭시 */
    NX_Issue_pubUi.deleteCertConfirm = function () {
        var certPwd = $('#certPwd').val();
        var selectCertIndex = certListInfo.getCertListIndex();
        var certID = certListInfo.getCertID();

        NXInit();
        $('#nx-cert-delete').hide();
        CertManagement.deleteCertIssue(selectCertIndex, certPwd, certID);
    };

    /* 인증서 삭제 아니오 클릭시 */
    NX_Issue_pubUi.deleteCertCancel = function () {
        NXInit();
        $('#nx-cert-delete').hide();
        $('#nx-cert-select').show();
        alert(NX_WEBUI_ALERT_MESSAGE_1);
    };

    ///***********************************************
    // 발급 모듈 : 인증서 폐지 버튼
    //************************************************/
    //NX_Issue_pubUi.revokeCertificate = function () {
    //    var certPwd = $('#certPwd').val();
    //    var selectCertIndex = certListInfo.getCertListIndex();
    //    var certID = certListInfo.getCertID();
    //    if ((selectCertIndex !== '') && (certPwd !== '')) {
    //        // 인증서 폐지 로직 설정
    //        processLogic.setProcessLogic('KICA.ISSUE.RevokeCert');
    //        Dialog.selectCertificate(selectCertIndex, certPwd, certID);
    //    } else {
    //        alert(NX_ISSUE_PUB_TEXT_17);
    //    }
    //};

    ///* 인증서 폐지 예 클릭시 */
    //NX_Issue_pubUi.revokeCertConfirm = function () {
    //    var certPwd = $('#certPwd').val();
    //    var selectCertIndex = certListInfo.getCertListIndex();
    //    var certID = certListInfo.getCertID();

    //    $('#certPwd').val('');
    //    $('#nx-cert-revoke').hide();
    //    issueCertificate.revokeCert();
    //};

    ///* 인증서 폐지 아니오 클릭시 */
    //NX_Issue_pubUi.revokeCertCancel = function () {
    //    $('#nx-cert-revoke').hide();
    //    $('#nx-cert-select').show();
    //};

    /***********************************************
     발급 모듈 :  인증서 발급&재발급&갱신&복사 타켓 미디어 선택시 이벤트 시작
    ************************************************/
    /* 타켓 미디어 Extra Value id값 리턴 */
    NX_Issue_pubUi.IssueExtraValueIdReturn = function (_this) {
        TargetMediaInfo.setExtraValue(_this.id);
    };

    /* 발급 대상 미디어창 추가매체 클릭시 */
    NX_Issue_pubUi.moreSaveMediaShow3 = function (media, extra) {
        if (media !== 'USIM') {
            $('.cert-location-area .pki-wrap4').fadeIn('fast');
        } else {
            NX_Issue_pubUi.moreSaveMediaHide();
        }
        TargetMediaInfo.setMediaType(media);//미디어 정보 Set

        if ((InsertNullCheck(extra) === true) || (extra === 'NULL')) {
            extra = '';
        }

        TargetMediaInfo.setExtraValue(extra);
        //추가 정보(드라이브명 등)를 얻기 위한 함수 호출
        targetMediaFlag.init();
        targetMediaFlag.setFlag(true);

        if (media !== 'USIM') {
            NX_selectStorage(media, extra);
        } else {
            // USIM site code set
            TargetMediaInfo.setExtraValue(USIM_SITECODE);

            var selectMedia_noti = NX_TARGET_DIALOG_TEXT_13 + ' : ' + NX_TARGET_DIALOG_TEXT_4;
            document.getElementById("targetMedia_noti").innerHTML = selectMedia_noti;
        }
    };

    NX_Issue_pubUi.moreSaveMediaHide3 = function () {
        var media = TargetMediaInfo.getMediaType();
        var extra = TargetMediaInfo.getExtraValue();

        var selectMedia_noti = '';
        if (media === 'USB') {
            selectMedia_noti = NX_ISSUE_PUB_TEXT_2 + ' : ' + NX_ISSUE_PUB_TEXT_19 + ', ' + extra;
        }
        if (media === 'HSM') {
            selectMedia_noti = NX_ISSUE_PUB_TEXT_2 + ' : ' + NX_ISSUE_PUB_TEXT_20 + ', ' + extra;
        }
        if (media === 'BIOHSM') {
            selectMedia_noti = NX_ISSUE_PUB_TEXT_2 + ' : ' + NX_ISSUE_PUB_TEXT_21 + ', ' + extra;
        }
        if (media === 'USIM') {
            selectMedia_noti = NX_ISSUE_PUB_TEXT_2 + ' : ' + NX_ISSUE_PUB_TEXT_22 + ', ' + extra;
        }
        if (media !== '') {
            document.getElementById("targetMedia_noti").innerHTML = selectMedia_noti;
        }

        //extraValue 선택 안했을 시 알림
        if (extra === '') {
            alert(NX_ISSUE_PUB_TEXT_16);
        } else {
            $('.cert-location-area .pki-wrap4').fadeOut('fast');
        }
    };

    /* 저장매체 선택 확인 버튼  */
    NX_Issue_pubUi.selectMediaConfirm = function () {
        //저장매체가 PIN번호를 요구하는 저장매체인지 일반 비밀번호를 요구하는 저장매체 인지 분기처리
        var mediaType = TargetMediaInfo.getMediaType();
        var extraValue = TargetMediaInfo.getExtraValue();

        if ((mediaType !== '') && (extraValue !== '')) {
            if (mediaType === 'HSM' || mediaType === 'BIOHSM') {
                //PIN 번호를 입력하는 경우
                $('#nx-targetMedia-select').hide();
                $('#nx-secureToken-pin-insert').show();
            }
            else {
                //인증서 복사인 경우 암호입력을 별도로 받지 않는다.
                var Logic_flag = processLogic.getProcessLogic();
                if (Logic_flag === 'KICA.MANAGEMENT.CopyCert') {
                    _copyMedia.disabled = false;
                    _copyMedia = null;

                    $('#nx-targetMedia-select').hide();
                    CertManagement.copyCert(mediaType, extraValue, '');
                }
                else {
                    //인증서 암호 입력인 경우
                    $('#nx-targetMedia-select').hide();
                    $('#nx-pwd-insert').show();
                }
            }

        } else {
            alert(NX_ISSUE_PUB_TEXT_23);
        }
    };

    /* 저장매체 선택 취소 버튼  */
    NX_Issue_pubUi.selectMediaCancel = function () {
        $('#nx-targetMedia-select').hide();
        // WebUI 백그라운드 화면 버튼 클릭 차단 해제
        $('#nx-pki-ui-wrapper').hide();

        var Logic_flag = processLogic.getProcessLogic();
        if (Logic_flag.indexOf('CopyCert') !== -1) {
            _copyMedia.disabled = false;
            _copyMedia = null;
            alert(NX_WEBUI_COPY_TEXT_9);
        }
    };

    /* 비밀번호 확인 버튼  */
    NX_Issue_pubUi.insertPWConfirm = function () {
        //인증서 비밀번호 확인
        var issueCertPW = $('#nx_issue_cert_pw').val();
        var issueCertPWConfirm = $('#nx_issue_cert_pw_confirm').val();

        // 초기화
        NXInit();

        //비밀번호 검증 로직 필요
        var res_ValidPW = validPassword(issueCertPW, issueCertPWConfirm);
        if (res_ValidPW === 'result_OK') {

            //암호입력창 제거
            $('#nx-pwd-insert').hide();

            var Logic_flag = processLogic.getProcessLogic();

            // 발급 관련 진행인 경우
            if (Logic_flag.indexOf('ISSUE') !== -1) {

                issueCertificate.setPw(issueCertPW);
                issueCertificate.setConfirmPw(issueCertPWConfirm);

                // 진행중 창 표시
                $('.nx-issue-ing-alert-head-msg').remove();
                var headMessage = '';
                var alertMessage = '';
                headMessage += '<div class=\"nx-issue-ing-alert-head-msg\">';
                if (Logic_flag.indexOf('KICA.ISSUE.IssueCert') !== -1) {
                    headMessage += '<h1>' + NX_ISSUE_PUB_TEXT_24 + '</h1>';
                    alertMessage = '<div id=\"issue-ing-alert-message\" class=\"gray-box2\"><p class=\"txt-c\"><span class=\"inline-tit\">' + NX_ISSUE_PUB_TEXT_25 + '<br><br>' + NX_ISSUE_PUB_TEXT_35 + '<br><br>' + NX_ISSUE_PUB_TEXT_36 + '</span></p></div>';
                } else if (Logic_flag.indexOf('KICA.ISSUE.ReissueCertificate') !== -1) {
                    headMessage += '<h1>' + NX_ISSUE_PUB_TEXT_26 + '</h1>';
                    alertMessage = '<div id=\"issue-ing-alert-message\" class=\"gray-box2\"><p class=\"txt-c\"><span class=\"inline-tit\">' + NX_ISSUE_PUB_TEXT_27 + '<br><br>' + NX_ISSUE_PUB_TEXT_35 + '<br><br>' + NX_ISSUE_PUB_TEXT_36 + '</span></p></div>';
                } else if (Logic_flag === 'KICA.ISSUE.RenewCertificate') {
                    headMessage += '<h1>' + NX_ISSUE_PUB_TEXT_28 + '</h>';
                    alertMessage = '<div id=\"issue-ing-alert-message\" class=\"gray-box2\"><p class=\"txt-c\"><span class=\"inline-tit\">' + NX_ISSUE_PUB_TEXT_29 + '<br><br>' + NX_ISSUE_PUB_TEXT_35 + '<br><br>' + NX_ISSUE_PUB_TEXT_36 + '</span></p></div>';
                }
                headMessage += '</div>';
                $('#nx-issue-ing-alert-head').append(headMessage);
                $('#issue-ing-alert-message').remove();
                $('#nx-issue-ing-alert-box').append(alertMessage);

                setTimeout(function () {
                    $('#nx-issue-ing-alert').show();
                }, 100);

            } else {
                //인증서 발급 이외의 비밀번호 사용인 경우 : 비밀번호 변경
                CertManagement.init();
                CertManagement.setNEW_NXPWD(issueCertPW);
                CertManagement.setNEW_NXPWD_CONFIRM(issueCertPWConfirm);
            }

            //인증서 발급&복사 실행
            setTimeout(function () {
                if (Logic_flag.indexOf('KICA.ISSUE.IssueCert') !== -1) {
                    //인증서 발급
                    issueCertificate.issueCert();
                } else if (Logic_flag.indexOf('KICA.ISSUE.ReissueCertificate') !== -1) {
                    //인증서 재발급
                    issueCertificate.reIssueCert();
                } else if (Logic_flag.indexOf('KICA.ISSUE.RenewCertificate') !== -1) {
                    //인증서 갱신
                    issueCertificate.updateCert();
                } else if (Logic_flag === 'KICA.MANAGEMENT.ChangePassword') {
                    CertManagement.changePassword();
                }

            }, 200);

        } else {
            alert(res_ValidPW);
        }
    };

    /* 비밀번호 취소 버튼  */
    NX_Issue_pubUi.insertPWCancel = function () {
        // 초기화 
        NXInit();
        $('#nx-pwd-insert').hide();
        // WebUI 백그라운드 화면 버튼 클릭 차단 해제
        $('#nx-pki-ui-wrapper').hide();

        var Logic_flag = processLogic.getProcessLogic();
        if (Logic_flag.indexOf('IssueCert') !== -1) {
            // 인증서 발급
            alert(NX_WEBUI_ALERT_MESSAGE_2);
        } else if (Logic_flag.indexOf('ReissueCertificate') !== -1) {
            // 인증서 재발급
            alert(NX_WEBUI_ALERT_MESSAGE_3);
        } else if (Logic_flag.indexOf('RenewCertificate') !== -1) {
            // 인증서 갱신
            alert(NX_WEBUI_ALERT_MESSAGE_4);
        } else if (Logic_flag.indexOf('ChangePassword') !== -1) {
            // 비밀번호 변경
            alert(NX_WEBUI_ALERT_MESSAGE_5);
        }
    };

    /* PIN 확인 버튼  */
    NX_Issue_pubUi.insertPINConfirm = function () {
        var Logic_flag = processLogic.getProcessLogic();

        //PIN 비밀번호 확인
        var issueCertPIN = $('#nx_issue_cert_pin').val();

        if (issueCertPIN !== '') {
            //PIN 입력창 제거
            $('#nx-secureToken-pin-insert').hide();

            // 초기화
            NXInit();

            //인증서 복사인 경우
            if (Logic_flag === 'KICA.MANAGEMENT.CopyCert') {
                CertManagement.init();
                CertManagement.setPwd(issueCertPIN);
            }
            else {
                issueCertificate.setPw(issueCertPIN);
                issueCertificate.setConfirmPw(issueCertPIN);

                // 진행중 창 표시
                $('.nx-issue-ing-alert-head-msg').remove();
                var headMessage = '';
                var alertMessage = '';
                headMessage += '<div class=\"nx-issue-ing-alert-head-msg\">';
                //if (Logic_flag === 'KICA.ISSUE.IssueCert') {
                if (Logic_flag.indexOf('KICA.ISSUE.IssueCert') !== -1) {
                    headMessage += '<h1>' + NX_ISSUE_PUB_TEXT_24 + '</h1>';
                    alertMessage = '<div id=\"issue-ing-alert-message\" class=\"gray-box2\"><p class=\"txt-c\"><span class=\"inline-tit\">' + NX_ISSUE_PUB_TEXT_25 + '<br><br>' + NX_ISSUE_PUB_TEXT_35 + '<br><br>' + NX_ISSUE_PUB_TEXT_36 + '</span></p></div>';

                    //} else if (Logic_flag === 'KICA.ISSUE.ReissueCertificate') {
                } else if (Logic_flag.indexOf('KICA.ISSUE.ReissueCertificate') !== -1) {
                    headMessage += '<h1>' + NX_ISSUE_PUB_TEXT_26 + '</h1>';
                    alertMessage = '<div id=\"issue-ing-alert-message\" class=\"gray-box2\"><p class=\"txt-c\"><span class=\"inline-tit\">' + NX_ISSUE_PUB_TEXT_27 + '<br><br>' + NX_ISSUE_PUB_TEXT_35 + '<br><br>' + NX_ISSUE_PUB_TEXT_36 + '</span></p></div>';

                    //} else if (Logic_flag.indexOf('KICA.ISSUE.RenewCertificate') !== -1) {
                } else if (Logic_flag === 'KICA.ISSUE.RenewCertificate') {
                    headMessage += '<h1>' + NX_ISSUE_PUB_TEXT_28 + '</h>';
                    alertMessage = '<div id=\"issue-ing-alert-message\" class=\"gray-box2\"><p class=\"txt-c\"><span class=\"inline-tit\">' + NX_ISSUE_PUB_TEXT_29 + '<br><br>' + NX_ISSUE_PUB_TEXT_35 + '<br><br>' + NX_ISSUE_PUB_TEXT_36 + '</span></p></div>';
                }
                headMessage += '</div>';
                $('#nx-issue-ing-alert-head').append(headMessage);
                $('#issue-ing-alert-message').remove();
                $('#nx-issue-ing-alert-box').append(alertMessage);

                setTimeout(function () { $('#nx-issue-ing-alert').show(); }, 100);
            }

            //인증서 발급 실행
            setTimeout(function () {
                if (Logic_flag === 'KICA.ISSUE.IssueCert') {
                    //인증서 발급
                    issueCertificate.issueCert();

                } else if (Logic_flag === 'KICA.ISSUE.ReissueCertificate') {
                    //인증서 재발급
                    issueCertificate.reIssueCert();

                } else if (Logic_flag === 'KICA.ISSUE.RenewCertificate') {
                    //인증서 갱신
                    issueCertificate.updateCert();
                } else if (Logic_flag === 'KICA.MANAGEMENT.CopyCert') {
                    //인증서 복사
                    var mediaType = TargetMediaInfo.getMediaType();
                    var extraValue = TargetMediaInfo.getExtraValue();
                    var overWrite = '';
                    CertManagement.copyCert(mediaType, extraValue, overWrite);
                }

            }, 200);
        } else {
            alert(NX_ISSUE_PUB_TEXT_30);
        }
    };

    /* PIN 취소 버튼  */
    NX_Issue_pubUi.insertPINCancel = function () {
        // 초기화
        NXInit();
        // PIN 입력창 제거
        $('#nx-secureToken-pin-insert').hide();

        // WebUI 백그라운드 화면 버튼 클릭 차단 해제
        $('#nx-pki-ui-wrapper').hide();
    };

    /* 인증서 발급 완료 확인 버튼 */
    NX_Issue_pubUi.issueCertConfirm = function () {
        $('#nx-issue-success-alert').hide();
        $('#nx-pki-ui-wrapper').hide();
    };

    /* 인증서 발급 실패 확인 버튼 */
    NX_Issue_pubUi.issueCertFailConfirm = function () {
        $('#nx-issue-fail-alert').hide();
        $('#nx-pki-ui-wrapper').hide();

        // WebUI 백그라운드 화면 버튼 클릭 차단 해제
        $('#nx-pki-ui-wrapper').hide();
    };

    /***********************************************
     인증서 관리 
     ************************************************/
    /* 인증서 내보내기 버튼 클릭 */
    NX_Issue_pubUi.exportPFX = function () {
        // 인증서 관리 창 제거
        $('#nx-cert-managemnet').hide();
        // 인증서 선택창 비밀번호 입력창 초기화
        NXInit();
        // 인증서 내보내기 파일 지정 화면 표시
        $('#nx-cert-management-exportPFX-fileName').show();
    };

    /* 인증서 내보내기 파일 이름 지정 확인 버튼 */
    NX_Issue_pubUi.exportPFXFileNameConfirm = function () {

        // 인증서 내보내기 파일 지정 화면 제거
        $('#nx-cert-management-exportPFX-fileName').hide();

        // 인증서 내보내기 키 분배용 인증서 확인 창 표시
        $('#nx-cert-management-exportPFX-withKm').show();

    };

    /* 인증서 내보내기 파일 이름 지정 취소 버튼 */
    NX_Issue_pubUi.exportPFXFileNameCancel = function () {
        // 입력한 p12 파일명 삭제
        $('#exportP12Name').val('');

        // 인증서 내보내기 파일 지정 화면 제거
        $('#nx-cert-management-exportPFX-fileName').hide();

        // 로직 초기화
        processLogic.init();

        // 인증서 관리창 표시
        $('#nx-cert-managemnet').show();
    };

    /* 인증서 내보내기 개인키 포함 여부 확인 버튼 */
    NX_Issue_pubUi.exportPFXWithKmConfirm = function () {
        // 인증서 내보내기 키 분배용 인증서 확인 창 제거
        $('#nx-cert-management-exportPFX-withKm').hide();

        // 인증서 내보내기 로직 세팅
        var flag = 'KICA.MANAGEMENT.EXPORT_PFX';
        processLogic.setProcessLogic(flag);

        // 인증서 선택 알림창 표시
        alert(NX_ISSUE_PUB_TEXT_31);

        // 인증서 선택창 표시
        $('#nx-cert-select').show();
    };

    /* 인증서 내보내기 개인키 포함 여부 취소 버튼 */
    NX_Issue_pubUi.exportPFXWithKmCancel = function () {
        // 입력한 p12 파일명 삭제
        $('#exportP12Name').val('');

        // 인증서 내보내기 키 분배용 인증서 확인 창 제거
        $('#nx-cert-management-exportPFX-withKm').hide();

        // 로직 초기화
        processLogic.init();

        // 인증서 관리창 표시
        $('#nx-cert-managemnet').show();
    };

    /* 인증서 가져오기 버튼 클릭 */
    NX_Issue_pubUi.importPFX = function () {
        // 인증서 관리창 제거
        $('#nx-cert-managemnet').hide();
        // 인증서 가져오기 경로
        document.getElementById('importPFXFileName').value = "";
        // 인증서 가져오기 화면 표시
        $('#nx-cert-management-importPFX').show();
    };

    /* 인증서 가져오기 확인 버튼 클릭 */
    NX_Issue_pubUi.importPFXConfirm = function () {
        // 인증서 가져오기 화면 제거
        $('#nx-cert-management-importPFX').hide();

        // 인증서 가져오기 로직 세팅
        var flag = 'KICA.MANAGEMENT.IMPORT_PFX';
        processLogic.setProcessLogic(flag);

        // 가져오기 진행
        NX_branchLogic_ISSUE();
    };

    /* 인증서 가져오기 취소 버튼 클릭 */
    NX_Issue_pubUi.importPFXCancel = function () {
        // 인증서 가져오기 화면 제거
        $('#nx-cert-management-importPFX').hide();
        alert(NX_CERT_MANAGEMENT_TEXT_32);
        // 로직 초기화
        processLogic.init();
        // 인증서 관리창 표시
        $('#nx-cert-managemnet').show();
    };

    /* 인증서 비밀번호 확인 성공 */
    NX_Issue_pubUi.checkPasswordConfirm = function () {
        // 인증서 비밀번호 확인 성공창 제거
        $('#nx-cert-checkPassword').hide();
        $('#nx-pki-ui-wrapper').hide();
    };

    /* 인증서 중복 복사 확인 버튼 */
    NX_Issue_pubUi.copyCertDuplicationConfirm = function () {
        // 선택한 값 가져오기
        var OverWriteFlag_Y = document.getElementById('certChangeYes');
        var OverWriteFlag_N = document.getElementById('certChangeNo');

        if (OverWriteFlag_Y.checked) {
            var mediaType = TargetMediaInfo.getMediaType();
            var extraValue = TargetMediaInfo.getExtraValue();
            var overWrite = 'Y';
            CertManagement.copyCert(mediaType, extraValue, overWrite);
        }

        if (OverWriteFlag_N.checked) {
            CertManagement.init();
            alert(NX_ISSUE_PUB_TEXT_33);
            $('#nx-cert-copy-duplication').hide();
            $('#nx-pki-ui-wrapper').hide();
        } else {
            // 화면 제거
            $('#nx-cert-copy-duplication').hide();
        }
    };

    /* 인증서 중복 복사 취소 버튼 */
    NX_Issue_pubUi.copyCertDuplicationCancel = function () {
        // 인증서 복사를 진행하지 않고 화면 제거
        $('#nx-cert-copy-duplication').hide();
        // WebUI 백그라운드 화면 버튼 클릭 차단 해제
        $('#nx-pki-ui-wrapper').hide();

        var Logic_flag = processLogic.getProcessLogic();
        if (Logic_flag.indexOf('CopyCert') !== -1) {
            alert(NX_WEBUI_COPY_TEXT_9);
        }
    };

    /* 인증서 신원확인 확인 버튼 */
    NX_Issue_pubUi.VerifyIdentifyConfirm = function () {
        var tmp = $('#chkSSN').val();
        CertManagement.setNXSSN(tmp);   //입력된 ssn set

        // 초기화
        NXInit();

        $('#nx-cert-VerifyIdentify').hide();
        CertManagement.verifyIdentity();
    };

    /* 인증서 신원확인 취소 버튼 */
    NX_Issue_pubUi.VerifyIdentifyCancel = function () {
        alert(NX_CERT_MANAGEMENT_TEXT_31);
        // 초기화
        NXInit();
        $('#nx-cert-VerifyIdentify').hide();
        // WebUI 백그라운드 화면 버튼 클릭 차단 해제
        $('#nx-pki-ui-wrapper').hide();
    };

    /* 문서로딩시 실행 */
    $(document).ready(function () {
        NX_Issue_pubUi.placeholder();
    });
}


//function NXResizeCertArea() {
//    NX_Issue_pubUi.certSelectRow();
//    var onSampleResized = function (e) {
//        var columns = $(e.currentTarget).find("th");
//        alert(columns);
//    };

//    $("#divtable").colResizable({
//        liveDrag: true,
//        gripInnerHtml: "<div class='grip'></div>",
//        draggingClass: "dragging",
//        onResize: onSampleResized
//    });
//}


// 저장매체 선택시 Hold 효과를 위한 Function
//$(function () {
function NXStopMediaSelect() {
    $("#MediaSet_1>li>button").click(function (e) {
        e.stopPropagation();
        if (!$(this).hasClass("active")) {
            $("#MediaSet_1>li>button.active").removeClass("active");
            $(this).addClass("active");
        }
    });

    $("#MediaSet_1-sub>button").click(function (e) {
        e.stopPropagation();
        if (!$(this).hasClass("active")) {
            $("#MediaSet_1-sub>button.active").removeClass("active");
            $(this).addClass("active");
        }
    });
}

// 타겟 저장매체 선택시 Hold 효과를 위한 Function
// 호출은 targetDialog.js에서
function NXStopMediaSelect2() {
    $("#MediaSet_2>li>button").click(function (e) {
        e.stopPropagation();
        if (!$(this).hasClass("active")) {
            $("#MediaSet_2>li>button.active").removeClass("active");
            $(this).addClass("active");
        }
    });

    $("#MediaSet_2-sub>button").click(function (e) {
        e.stopPropagation();
        if (!$(this).hasClass("active")) {
            $("#MediaSet_2-sub>button.active").removeClass("active");
            $(this).addClass("active");
        }
    });
}

// 라온 키보드보안 실행 함수
function GetEncYT() {
    var pubkey = "-----BEGIN CERTIFICATE-----MIIDPTCCAiWgAwIBAgIJAOYjCX4wgWNVMA0GCSqGSIb3DQEBCwUAMGcxCzAJBgNVBAYTAktSMR0wGwYDVQQKExRSYW9uU2VjdXJlIENvLiwgTHRkLjEaMBgGA1UECxMRUXVhbGl0eSBBc3N1cmFuY2UxHTAbBgNVBAMTFFJhb25TZWN1cmUgQ28uLCBMdGQuMB4XDTE2MDUyNDAyMzEwMVoXDTM2MDUxOTAyMzEwMVowOTELMAkGA1UEBhMCS1IxDzANBgNVBAoTBkhhbndoYTEZMBcGA1UEAxMQd3d3LnBpbmVjbHViLmNvbTCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBALpvn2sDKsEwJDwej9u3343M/rSyWz1+dfmfmmRFJPZVpYAXpxNxuUlDnonVgcK5km18DFOq27pcx3NhypJdIZ87L/9jkkgYuL9cTdC7VlS3iprZ5GKDTcCtqucmMKrK+b4TSyqGgxKTWsfFX2cuiNFI6YNiUDBG00LMxmgeUvMlwme8KhlI2qe3qQ1sQgljmThe0wYtIIVXouLHYGZ/q9v1W4ap7jkWYxrrvpxV6XTRLQdflPjvoB52mipjKZ9djh0gL05ZJUIU2Di89UiqX6cYDaPStoIdhhAF4ba6b7C3r9cQEmQoYwtgcuxbcYqgeH3np8NyZA7NYybDE/buky0CAwEAAaMaMBgwCQYDVR0TBAIwADALBgNVHQ8EBAMCBeAwDQYJKoZIhvcNAQELBQADggEBAFWCzVKe50JuX4cILaYNdV9gL88UbcUbIaUCbkcXV+o9Bv+oDUxJdJqHiFhhduPW2ztp/1cdI0Z4f5VZ7CUMalK4jPFQyQfOO2nJa575MIrdEZ/sySmNkYz5ugq+EeGWqKD9qLlAwHTCQI6fZ6jsXOu+RMNt4kBTp6PKjsi8DzzRRMrh6tSqwpzSECO4ck9V0vj2Owgz/D6vd5cREqg+xpw8TIis5Mk5HE5roWOMHHaAJC/mY8eMMl0MTlpzu55sy3y5kJVpaTJVdYanmI6xQCHRK2QTR1LKCcrirPGuA3Szs3yit1QDW1udW1xnHAa8GIaHh4WZusGZYCAZzW821n4=-----END CERTIFICATE-----";
    var frmName = "formRe";
    var eleName = "certPwd";
    TK_GetEncYT(frmName, eleName, pubkey, function (result) {

        var tmp = result;
        var pwd = 'raon' + '|' + tmp;
        tmp = '';

        // 초기화
        NXInit();
        TK_Clear("formRe", "certPwd"); // 라온시큐어 키보드보안 메모리 해제

        var certID = certListInfo.getCertID();
        var certIndex = certListInfo.getCertListIndex();

        Dialog.selectCertificate(certIndex, pwd, certID);    //result : 치환된 비밀번호
    });
}