/***********************************************
    set media in selection cert box
************************************************/
function SelectDialogSet() {
    var length = NX_SELECT_CERT_MEDIA.length;
    var res = '';

    var media = '';
    var order = '';
    var able = '';
    var Upper_able = '';

    var j = 0;

    $('.cert-location-area-select-media ul').remove();

    res = '<ul id="MediaSet_1">';
    var i;
    for (i = 1; i <= length; i++) {
        var index = 0;
        while (true) {
            media = NX_SELECT_CERT_MEDIA[index].MEDIA;
            order = NX_SELECT_CERT_MEDIA[index].ORDER;
            able = NX_SELECT_CERT_MEDIA[index].ABLE;
            index++;

            if (order === i) {
                break;
            }
        }

        Upper_able = able.toUpperCase();

        // start extra media
        if (i === 5) {

            if (NX_SELECT_CERT_MEDIA[5].ABLE === 'able') {
                res += "<li><button style=\"background-image:url('" + NX_MEDIA_IMG_URL + "MEDIA_LIST.png')\" onclick=\"NX_Issue_pubUi.moreSaveMediaShow();\">" + NX_TARGET_DIALOG_TEXT_1 + "</button>";
                res += "<div class='sub-layer'>";
                res += "<div class='inner' id='MediaSet_1-sub'>";
            } else {
                // extra media disable
                res += "<li><button disabled style=\"background-image:url('" + NX_MEDIA_IMG_URL + "MEDIA_LIST.png')\" onclick=\"NX_Issue_pubUi.moreSaveMediaShow();\">" + NX_TARGET_DIALOG_TEXT_1 + "</button>";
                res += "<div class='sub-layer'>";
                res += "<div class='inner' id='MediaSet_1-sub'>";
            }
        }

        if (i >= 5) {
            // show extra media
            switch (media) {
                case 'HDD':
                    if (Upper_able === 'ABLE') {
                        res += "<button style=\"background-image:url('" + NX_MEDIA_IMG_URL + "HDD.png')\" onclick=\"NX_SetMediaHDD();\">" + NX_TARGET_DIALOG_TEXT_2 + "</button> ";
                    }
                    else {
                        res += "<button disabled style=\"background-image:url('" + NX_MEDIA_IMG_URL + "HDD.png')\">" + NX_TARGET_DIALOG_TEXT_2 + "</button>";
                    }
                    break;

                case 'USB':
                    if (Upper_able === 'ABLE') {
                        res += "<button style=\"background-image:url('" + NX_MEDIA_IMG_URL + "USB.png')\" onclick=\"NX_Issue_pubUi.moreSaveMediaShow2();\">" + NX_TARGET_DIALOG_TEXT_3 + "</button>";
                    }
                    else {
                        res += "<button disabled style=\"background-image:url('" + NX_MEDIA_IMG_URL + "USB.png')\">" + NX_TARGET_DIALOG_TEXT_3 + "</button> ";
                    }
                    break;

                case 'USIM':
                    if (Upper_able === 'ABLE') {
                        res += "<button style=\"background-image:url('" + NX_MEDIA_IMG_URL + "USIM.png')\" onclick=\"NX_Issue_pubUi.moreSaveMediaShow2('USIM','NULL');\">" + NX_TARGET_DIALOG_TEXT_4 + "</button>";
                    }
                    else {
                        res += "<button disabled style=\"background-image:url('" + NX_MEDIA_IMG_URL + "USIM.png')\">" + NX_TARGET_DIALOG_TEXT_4 + "</button>";
                    }
                    break;

                case 'HSM':
                    if (Upper_able === 'ABLE') {
                        res += "<button style=\"background-image:url('" + NX_MEDIA_IMG_URL + "HSM.png')\" onclick=\"NX_Issue_pubUi.moreSaveMediaShow2('HSM','NULL');\">" + NX_TARGET_DIALOG_TEXT_5 + "</button>";
                    }
                    else {
                        res += "<button disabled style=\"background-image:url('" + NX_MEDIA_IMG_URL + "HSM.png')\">" + NX_TARGET_DIALOG_TEXT_5 + "</button>";
                    }
                    break;
				case 'BIO_TOKEN':
                case 'BIOHSM':
                    if (Upper_able === 'ABLE') {
                        res += "<button style=\"background-image:url('" + NX_MEDIA_IMG_URL + "BIOHSM.png')\" onclick=\"NX_Issue_pubUi.moreSaveMediaShow2('BIOHSM','" + BioTokenP7Message + "');\">" + NX_TARGET_DIALOG_TEXT_6 + "</button>";
                    }
                    else {
                        res += "<button disabled style=\"background-image:url('" + NX_MEDIA_IMG_URL + "BIOHSM.png')\">" + NX_TARGET_DIALOG_TEXT_6 + "</button>";
                    }
                    break;
            }//end switch

        }
        else {
            // show front side
            switch (media) {
                case 'HDD':
                    if (Upper_able === 'ABLE') {
                        var flag = NX_SELECT_CERT_MEDIA[0].DEFAULT;
                        if (flag === 'able') {
                            res += "<li> <button class=\"active\" style=\"background-image:url(\'" + NX_MEDIA_IMG_URL + "HDD.png\')\" onclick=\"NX_SetMediaHDD();\">" + NX_TARGET_DIALOG_TEXT_2 + "</button></li> ";
                        } else {
                            res += "<li> <button style=\"background-image:url(\'" + NX_MEDIA_IMG_URL + "HDD.png\')\" onclick=\"NX_SetMediaHDD();\">" + NX_TARGET_DIALOG_TEXT_2 + "</button></li> ";
                        }
                    }
                    else {
                        res += "<li> <button disabled style=\"background-image:url('" + NX_MEDIA_IMG_URL + "HDD.png')\">" + NX_TARGET_DIALOG_TEXT_2 + "</button></li> ";
                    }
                    break;

                case 'USB':
                    if (Upper_able === 'ABLE') {
                        res += "<li> <button style=\"background-image:url('" + NX_MEDIA_IMG_URL + "USB.png')\" onclick=\"NX_Issue_pubUi.moreSaveMediaShow2('USB', 'NULL');\">" + NX_TARGET_DIALOG_TEXT_3 + "</button></li> ";
                    }
                    else {
                        res += "<li> <button disabled style=\"background-image:url('" + NX_MEDIA_IMG_URL + "USB.png')\">" + NX_TARGET_DIALOG_TEXT_3 + "</button></li> ";
                    }
                    break;

                case 'USIM':
                    if (Upper_able === 'ABLE') {
                        res += "<li> <button style=\"background-image:url('" + NX_MEDIA_IMG_URL + "USIM.png')\" onclick=\"NX_Issue_pubUi.moreSaveMediaShow2('USIM','NULL');\">" + NX_TARGET_DIALOG_TEXT_4 + "</button></li> ";
                    }
                    else {
                        res += "<li> <button disabled style=\"background-image:url('" + NX_MEDIA_IMG_URL + "USIM.png')\">" + NX_TARGET_DIALOG_TEXT_4 + "</button></li> ";
                    }
                    break;

                case 'HSM':
                    if (Upper_able === 'ABLE') {
                        res += "<li> <button style=\"background-image:url('" + NX_MEDIA_IMG_URL + "HSM.png')\" onclick=\"NX_Issue_pubUi.moreSaveMediaShow2('HSM','NULL');\">" + NX_TARGET_DIALOG_TEXT_5 + "</button></li> ";
                    }
                    else {
                        res += "<li> <button disabled style=\"background-image:url('" + NX_MEDIA_IMG_URL + "HSM.png')\">" + NX_TARGET_DIALOG_TEXT_5 + "</button></li> ";
                    }
                    break;
				case 'BIOHSM':
                case 'BIO_TOKEN':
                    if (Upper_able === 'ABLE') {
                        res += "<li> <button style=\"background-image:url('" + NX_MEDIA_IMG_URL + "BIOHSM.png')\" onclick=\"NX_Issue_pubUi.moreSaveMediaShow2('BIOHSM', '" + BioTokenP7Message + "');\">" + NX_TARGET_DIALOG_TEXT_6 + "</button></li> ";
                    }
                    else {
                        res += "<li> <button disabled style=\"background-image:url('" + NX_MEDIA_IMG_URL + "BIOHSM.png')\">" + NX_TARGET_DIALOG_TEXT_6 + "</button></li> ";
                    }
                    break;
            }//end switch
        }

        //end of extra media
        if (i === length) {
            res += '<a href="#" class="btn-close" onclick="NX_Issue_pubUi.moreSaveMediaHide();;return false;">' + NX_TARGET_DIALOG_TEXT_7 + '</a> </div></div></li>';
        }

    }//end for          

    res += '</ul>';

    $('.cert-location-area-select-media').append(res);
}


/**
  * @public
  * @memberof Dialog
  * @method selectStorage
  * @description get all list of certificate in selected media
  * @param {String} mediaType (HDD, HSM, USB..)
  * @param {String} extraValue HDD : NULL, HSM : NULL or Drive name
  */
function NXselectStorageIssue(mediaType, extraValue) {
    if (InsertNullCheck(mediaType) === false) {
        if (mediaType === "USIM" || mediaType === "usim") {
            extraValue = USIM_SITECODE;
        }

        var cmd = 'NXselectStorageIssue.selectStorageIssue';
        var Data = {
            'mediaType': mediaType,
            'extraValue': extraValue
        };
        var param = JSON.stringify(Data);
        secukitnxInterface.SecuKitNX_EX(cmd, param);
    } else {
        $('.nx-cert-select').hide(); $('#nx-pki-ui-wrapper').hide(); KICA_Error.init();
        var location = 'NXselectStorageIssue';
        var reason = 'mediaType';
        KICA_ERROR_RESOURCE.ErrorMessage(location, reason, 'ERR_SCRIPT_DIALOG_INPUT');
        var ScriptErrorMessage = KICA_Error.getScriptError();
        alert(ScriptErrorMessage);
    }
}

/**
 * @public
 * @memberof Dialog
 * @method selectStorageCallback
 * @description selectStorage callback function
 * @param reply callback data
 * @returns 
 *          mediaType HDD  : cert list
 *          mediaType HSM  : device list (Driver list), HSM & Driver : cert list 
 */
function NXselectStorageIssueCallback(reply) {
    var errorCheck = -1;
    try {
        errorCheck = reply.ERROR_CODE;
    } catch (err) {
        //console.log(err);
    }

    if (errorCheck === undefined) {


    }
    else if (reply.ERROR_CODE) {
        $('.nx-cert-select').hide(); $('#nx-pki-ui-wrapper').hide(); KICA_Error.init();
        if (reply.ERROR_CODE === '357236736') {
            //console.log('');
        } else {
            KICA_Error.setError(reply.ERROR_CODE, reply.ERROR_MESSAGE);
            var errorMsg = KICA_Error.getError();
            alert(errorMsg);
        }
    }
}

/***********************************************
    issue, reissue, copy : set media 
************************************************/
function TargetDialogSet() {
    var length = NX_TARGET_MEDIA.length;
    var res = '';

    var media = '';
    var order = '';
    var able = '';
    var Upper_able = '';

    var j = 0;

    $('.cert-location-area-targetMedia ul').remove();

    res = '<ul id="MediaSet_2">';
    for (var i = 1; i <= length; i++) {
        var index = 0;
        while (true) {
            media = NX_TARGET_MEDIA[index].MEDIA;
            order = NX_TARGET_MEDIA[index].ORDER;
            able = NX_TARGET_MEDIA[index].ABLE;
            index++;

            if (order === i) {
                break;
            }
        }

        Upper_able = able.toUpperCase();

        if (i === 5) {
            if (NX_TARGET_MEDIA[5].ABLE === 'able') {
                res += "<li><button style=\"background-image:url('" + NX_MEDIA_IMG_URL + "MEDIA_LIST.png')\" onclick=\"NX_Issue_pubUi.moreSaveMediaShow();\">" + NX_TARGET_DIALOG_TEXT_1 + "</button>";
                res += "<div class='sub-layer'>";
                res += "<div class='inner' id='MediaSet_1-sub'>";
            } else {
                res += "<li><button disabled style=\"background-image:url('" + NX_MEDIA_IMG_URL + "MEDIA_LIST.png')\" onclick=\"NX_Issue_pubUi.moreSaveMediaShow();\">" + NX_TARGET_DIALOG_TEXT_1 + "</button>";
                res += "<div class='sub-layer'>";
                res += "<div class='inner' id='MediaSet_1-sub'>";
            }
        }

        if (i >= 5) {
            switch (media) {
                case 'HDD':
                    if (Upper_able === 'ABLE') {
                        res += "<button id=\"HDD\" style=\"background-image:url('" + NX_MEDIA_IMG_URL + "HDD.png')\" onclick=\"NX_SetMediaHDD2();\">" + NX_TARGET_DIALOG_TEXT_2 + "</button> ";
                    }
                    else {
                        res += "<button id=\"HDD\" disabled style=\"background-image:url('" + NX_MEDIA_IMG_URL + "HDD.png')\">" + NX_TARGET_DIALOG_TEXT_2 + "</button>";
                    }
                    break;

                case 'USB':
                    if (Upper_able === 'ABLE') {
                        res += "<button id=\"USB\" style=\"background-image:url('" + NX_MEDIA_IMG_URL + "USB.png')\" onclick=\"NX_Issue_pubUi.moreSaveMediaShow3();\">" + NX_TARGET_DIALOG_TEXT_3 + "</button>";
                    }
                    else {
                        res += "<button id=\"USB\" disabled style=\"background-image:url('" + NX_MEDIA_IMG_URL + "USB.png')\">" + NX_TARGET_DIALOG_TEXT_3 + "</button> ";
                    }
                    break;

                case 'USIM':
                    if (Upper_able === 'ABLE') {
                        res += "<button id=\"USIM\" style=\"background-image:url('" + NX_MEDIA_IMG_URL + "USIM.png')\" onclick=\"NX_Issue_pubUi.moreSaveMediaShow3('USIM','NULL');\">" + NX_TARGET_DIALOG_TEXT_4 + "</button>";
                    }
                    else {
                        res += "<button id=\"USIM\" disabled style=\"background-image:url('" + NX_MEDIA_IMG_URL + "USIM.png')\">" + NX_TARGET_DIALOG_TEXT_4 + "</button>";
                    }
                    break;

                case 'HSM':
                    if (Upper_able === 'ABLE') {
                        res += "<button id=\"HSM\" style=\"background-image:url('" + NX_MEDIA_IMG_URL + "HSM.png')\" onclick=\"NX_Issue_pubUi.moreSaveMediaShow3('HSM','NULL');\">" + NX_TARGET_DIALOG_TEXT_5 + "</button>";
                    }
                    else {
                        res += "<button id=\"HSM\" disabled style=\"background-image:url('" + NX_MEDIA_IMG_URL + "HSM.png')\">" + NX_TARGET_DIALOG_TEXT_5 + "</button>";
                    }
                    break;

                case 'BIO_TOKEN':
                    if (Upper_able === 'ABLE') {
                        res += "<button id=\"BIOHSM\" style=\"background-image:url('" + NX_MEDIA_IMG_URL + "BIOHSM.png')\" onclick=\"NX_Issue_pubUi.moreSaveMediaShow3('BIOHSM','" + BioTokenP7Message + "');\">" + NX_TARGET_DIALOG_TEXT_6 + "</button>";
                    }
                    else {
                        res += "<button id=\"BIOHSM\" disabled style=\"background-image:url('" + NX_MEDIA_IMG_URL + "BIOHSM.png')\">" + NX_TARGET_DIALOG_TEXT_6 + "</button>";
                    }
                    break;
            }//end switch

        }
        else {
            switch (media) {
                case 'HDD':
                    if (Upper_able === 'ABLE') {
                        res += "<li> <button id=\"HDD\" style=\"background-image:url(\'" + NX_MEDIA_IMG_URL + "HDD.png\')\" onclick=\"NX_SetMediaHDD2();\">" + NX_TARGET_DIALOG_TEXT_2 + "</button></li> ";
                    }
                    else {
                        res += "<li> <button id=\"HDD\" disabled style=\"background-image:url('" + NX_MEDIA_IMG_URL + "HDD.png')\">" + NX_TARGET_DIALOG_TEXT_2 + "</button></li> ";
                    }
                    break;

                case 'USB':
                    if (Upper_able === 'ABLE') {
                        res += "<li> <button id=\"USB\" style=\"background-image:url('" + NX_MEDIA_IMG_URL + "USB.png')\" onclick=\"NX_Issue_pubUi.moreSaveMediaShow3('USB', 'NULL');\">" + NX_TARGET_DIALOG_TEXT_3 + "</button></li> ";
                    }
                    else {
                        res += "<li> <button id=\"USB\" disabled style=\"background-image:url('" + NX_MEDIA_IMG_URL + "USB.png')\">" + NX_TARGET_DIALOG_TEXT_3 + "</button></li> ";
                    }
                    break;

                case 'USIM':
                    if (Upper_able === 'ABLE') {
                        res += "<li> <button id=\"USIM\" style=\"background-image:url('" + NX_MEDIA_IMG_URL + "USIM.png')\" onclick=\"NX_Issue_pubUi.moreSaveMediaShow3('USIM','NULL');\">" + NX_TARGET_DIALOG_TEXT_4 + "</button></li> ";
                    }
                    else {
                        res += "<li> <button id=\"USIM\" disabled style=\"background-image:url('" + NX_MEDIA_IMG_URL + "USIM.png')\">" + NX_TARGET_DIALOG_TEXT_4 + "</button></li> ";
                    }
                    break;

                case 'HSM':
                    if (Upper_able === 'ABLE') {
                        res += "<li> <button id=\"HSM\" style=\"background-image:url('" + NX_MEDIA_IMG_URL + "HSM.png')\" onclick=\"NX_Issue_pubUi.moreSaveMediaShow3('HSM','NULL');\">" + NX_TARGET_DIALOG_TEXT_5 + "</button></li> ";
                    }
                    else {
                        res += "<li> <button id=\"HSM\" disabled style=\"background-image:url('" + NX_MEDIA_IMG_URL + "HSM.png')\">" + NX_TARGET_DIALOG_TEXT_5 + "</button></li> ";
                    }
                    break;

                case 'BIO_TOKEN':
                    if (Upper_able === 'ABLE') {
                        res += "<li> <button id=\"BIO_TOKEN\" style=\"background-image:url('" + NX_MEDIA_IMG_URL + "BIOHSM.png')\" onclick=\"NX_Issue_pubUi.moreSaveMediaShow3('BIOHSM','" + BioTokenP7Message + "');\">" + NX_TARGET_DIALOG_TEXT_6 + "</button></li> ";
                    }
                    else {
                        res += "<li> <button id=\"BIO_TOKEN\" disabled style=\"background-image:url('" + NX_MEDIA_IMG_URL + "BIOHSM.png')\">" + NX_TARGET_DIALOG_TEXT_6 + "</button></li> ";
                    }
                    break;
            }//end switch
        }

        if (i === length) {
            res += '<a href="#" class="btn-close" onclick="NX_Issue_pubUi.moreSaveMediaHide();return false;">' + NX_TARGET_DIALOG_TEXT_7 + '</a> </div></div></li>';
        }

    }//end for          

    res += '</ul>';

    $('.cert-location-area-targetMedia').append(res);
}

function NX_selectStorage(mediaType, extraValue) {

    if (InsertNullCheck(mediaType) === false) {
        if (mediaType === "USIM" || mediaType === "usim") {
            extraValue = USIM_SITECODE;
        }

        //branch logic
        var Logic_flag = processLogic.getProcessLogic();
        var cmd;
        var targetMediaF = targetMediaFlag.getFlag();
        if (targetMediaF === true) {
            // issue, reissue
            // only need target media infomation
            cmd = 'NX_selectStorage.selectStorage2Issue';
        }
        else {
            if ((Logic_flag.indexOf('ISSUE') !== -1) ||
                (Logic_flag.indexOf('MANAGEMENT') !== -1)) {

                // renew 
                // because of getting cert 
                cmd = 'NX_selectStorage.selectStorageIssue';
            } else {
                // other use function (p7,enc,dec,...)
                cmd = 'NX_selectStorage.selectStorage';
            }
        }

        var Data = {
            'mediaType': mediaType,
            'extraValue': extraValue
        };
        var param = JSON.stringify(Data);
        secukitnxInterface.SecuKitNX_EX(cmd, param);
    } else {
        $('.nx-cert-select').hide(); $('#nx-pki-ui-wrapper').hide(); KICA_Error.init();
        var location = 'NX_selectStorage';
        var reason = 'mediaType';
        KICA_ERROR_RESOURCE.ErrorMessage(location, reason, 'ERR_SCRIPT_DIALOG_INPUT');
        var ScriptErrorMessage = KICA_Error.getScriptError();
        alert(ScriptErrorMessage);
    }
}

function NX_selectStorageCallback(reply) {
    var errorCheck = -1;
    try {
        errorCheck = reply.ERROR_CODE;
    } catch (err) {
        //console.log(err);
    }

    if (errorCheck === undefined) {

        var targetMediaF = targetMediaFlag.getFlag();
        if (targetMediaF === true) {

            $('.pki-wrap4 #pki-extra-media-box-contents4').remove();

            var mediaType = TargetMediaInfo.getMediaType();
            var extraMsg = '';
            if (mediaType === 'USB') {
                extraMsg = NX_TARGET_DIALOG_TEXT_3;
            }
            if (mediaType === 'HSM') {
                extraMsg = NX_TARGET_DIALOG_TEXT_5;

                TokenMediaInfo.init();
                TokenMediaInfo.setTokenMediaObj(reply);
            }
            if (mediaType === 'BIOHSM') {
                extraMsg = NX_TARGET_DIALOG_TEXT_6;

                TokenMediaInfo.init();
                TokenMediaInfo.setTokenMediaObj(reply);
            }

            var sublayer = '<div id=\"pki-extra-media-box-contents4\">';
            sublayer += '<div class=\"pki-head4\"><h1>' + extraMsg + ' ' + NX_TARGET_DIALOG_TEXT_8 + '</h1><a href=\"#\" class=\"btn-close1\" onclick=\"NX_Issue_pubUi.moreSaveMediaHide5();return false;\">' + NX_TARGET_DIALOG_TEXT_9 + '</a></div>';
            sublayer += '<div class=\"pki-body4\">';
            sublayer += '<div class=\"titwrap\">';
            sublayer += '<h2 class=\"tit1\">' + extraMsg + ' ' + NX_TARGET_DIALOG_TEXT_10 + '</h2>';
            sublayer += '<div class=\"cert-select-area4\" id=\"cert-select-area4\">';

            sublayer += '<input type=\"hidden\" id=\"NX_EX_TargetMediaIndex\" value=\"\">';
            sublayer += '<table id=\"divtable2\" cellpadding=\"0\" cellspacing=\"0\" class=\"js-selrow\">';

            var listIndex = 1;
            var tokenNameTmp = '';
            for (var i = 1; i <= reply.size; i++) {
                if (reply[i].diskName.indexOf('Mobile') === -1) {

                    if (mediaType === 'BIOHSM')
                    {
                        var Tmp_BioTokenInfo = '';
                        Tmp_BioTokenInfo = reply[i].diskName.split('|');
                        tokenNameTmp = Tmp_BioTokenInfo[0] + ' | ' + Tmp_BioTokenInfo[3];
                    }
                    else
                    {
                        tokenNameTmp = reply[i].diskName;
                    }

                    sublayer += '<tr id="' + reply[i].diskName + '" onclick="NX_Issue_pubUi.IssueExtraValueIdReturn(this); NX_Issue_pubUi.certSelectRow(' + listIndex + '); ">';
                    sublayer += '<td>' + tokenNameTmp + '</td></tr>';
                    ++listIndex;
                    tokenNameTmp = '';
                }
            }
            sublayer += '</table>';
            sublayer += '</div></div></div>';
            sublayer += '<div class=\"pki-bottom4\">';
            if (mediaType === 'BIOHSM' || mediaType === 'HSM') {
                sublayer += '<div class=\"gray-box2 mb10\"> ' + '<a target="_blank" href="http://rootca.or.kr/">' + NX_TARGET_DIALOG_TEXT_5 + '</a>' + ' </div>';
            } else {
                sublayer += '<div class=\"gray-box2 mb10\"> ' + extraMsg + '' + NX_TARGET_DIALOG_TEXT_11 + ' </div>';
            }
            sublayer += '<button class=\"btn-ok\" onclick="NX_Issue_pubUi.moreSaveMediaHide3();return false;">' + NX_TARGET_DIALOG_TEXT_12 + '</button>';
            sublayer += '</div><div>';


            $('.pki-wrap4 #pki-extra-media-box4').append(sublayer);
            $('.cert-location-area .pki-wrap4').fadeIn('fast');

        } else {
            // include cert list
            $('.pki-wrap3 #pki-extra-media-box-contents3').remove();

            var mediaType = SelectMediaInfo.getMediaType();
            var extraMsg = '';
            if (mediaType === 'USB') {
                extraMsg = NX_TARGET_DIALOG_TEXT_3;
            }
            if (mediaType === 'HSM') {
                extraMsg = NX_TARGET_DIALOG_TEXT_5;

                TokenMediaInfo.init();
                TokenMediaInfo.setTokenMediaObj(reply);
            }
            if (mediaType === 'BIOHSM') {
                extraMsg = NX_TARGET_DIALOG_TEXT_6;

                TokenMediaInfo.init();
                TokenMediaInfo.setTokenMediaObj(reply);
            }

            if (mediaType === 'USIM' && reply.extraValue === 'NULL') {
                alert(NX_WEBUI_SELECT_TEXT_12);
                $("#MediaSet_1>li>button.active").removeClass("active");
                if (USIM_DOWNLOAD_EXE !== '') {
                    window.open(USIM_DOWNLOAD_EXE, '', 'width="500", height="300", menubar=no, status=no, toolbar=no');
                } else {
                    var PopUPWindow = window.open(USIM_DOWNLOAD_URL);
                }
            } else if (mediaType === 'USIM' && reply.verify === 'Y') {
                Dialog.selectCertificate("1", "USIM", "NULL");
            } else {

                var sublayer = '<div id=\"pki-extra-media-box-contents3\">';
                sublayer += '<div class=\"pki-head3\"><h1>' + extraMsg + ' ' + NX_TARGET_DIALOG_TEXT_8 + '</h1><a href=\"#\" class=\"btn-close1\" onclick=\"NX_Issue_pubUi.moreSaveMediaHide5();return false;\">' + NX_TARGET_DIALOG_TEXT_9 + '</a></div>';
                sublayer += '<div class=\"pki-body3\">';
                sublayer += '<div class=\"titwrap\">';
                sublayer += '<h2 class=\"tit1\">' + extraMsg + ' ' + NX_TARGET_DIALOG_TEXT_10 + '</h2>';
                sublayer += '<div class=\"cert-select-area3\" id=\"cert-select-area3\">';

                sublayer += '<input type=\"hidden\" id=\"NX_EX_SelectMediaIndex\" value=\"\">';
                sublayer += '<table cellpadding=\"0\" cellspacing=\"0\" class=\"js-selrow\">';

                var listIndex = 1;
                var tokenNameTmp = '';
                for (var i = 1; i <= reply.size; i++) {
                    if (reply[i].diskName.indexOf('Mobile') === -1) {

                        if (mediaType === 'BIOHSM') {
                            var Tmp_BioTokenInfo = '';
                            Tmp_BioTokenInfo = reply[i].diskName.split('|');
                            tokenNameTmp = Tmp_BioTokenInfo[0] + ' | ' + Tmp_BioTokenInfo[3];
                        }
                        else {
                            tokenNameTmp = reply[i].diskName;
                        }

                        sublayer += '<tr id=\"' + reply[i].diskName + '\" onclick=\"NX_Issue_pubUi.extraValueIdReturn(this); NX_Issue_pubUi.certSelectRow(' + listIndex + ');\">';
                        sublayer += '<td>' + tokenNameTmp + '</td></tr>';
                        ++listIndex;
                        tokenNameTmp = '';
                    }
                }

                sublayer += '</table>';
                sublayer += '</div></div></div>';
                sublayer += '<div class=\"pki-bottom3\">';
                if (mediaType === 'BIOHSM' || mediaType === 'HSM') {
                    sublayer += '<div class=\"gray-box2 mb10\"> ' + '<a target="_blank" href="http://rootca.or.kr/">' + NX_TARGET_DIALOG_TEXT_5 + '</a>' + ' </div>';
                } else {
                    sublayer += '<div class=\"gray-box2 mb10\"> ' + extraMsg + '' + NX_TARGET_DIALOG_TEXT_11 + ' </div>';
                }
                sublayer += '<button class=\"btn-ok\" onclick="NX_Issue_pubUi.moreSaveMediaHide2();return false;">' + NX_TARGET_DIALOG_TEXT_12 + '</button>';
                sublayer += '</div></div>';

                $('.pki-wrap3 #pki-extra-media-box3').append(sublayer);
                $('.cert-location-area .pki-wrap3').fadeIn('fast');

                if (NX_DIALOG_MOVE === true) {
                    try {
                        $(".pki-wrap3").draggable({ handle: ".pki-head3", cursor: "move" });
                    } catch (e) { //console.log(e);}
                    }
                }

            }
        }
    }
    else {
        $('.cert-location-area .pki-wrap3').hide();
        $("#MediaSet_1>li>button.active").removeClass("active");
        $('.nx-cert-select').hide(); $('#nx-pki-ui-wrapper').hide(); KICA_Error.init();
        KICA_Error.setError(reply.ERROR_CODE, reply.ERROR_MESSAGE);
        var errorMsg = KICA_Error.getError();
        alert(errorMsg);
    }
}

// infomation of selected media
var TargetMediaInfo = (function () {
    var mediaType = '';
    var extraValue = '';

    var init = function () {
        mediaType = '';
        extraValue = '';
    };

    var setMediaType = function (media) {
        mediaType = media;
    };
    var getMediaType = function () {
        return mediaType;
    };

    var setExtraValue = function (extra) {
        extraValue = extra;
    };
    var getExtraValue = function () {
        return extraValue;
    };

    return {
        init: init,
        setMediaType: setMediaType,
        getMediaType: getMediaType,
        setExtraValue: setExtraValue,
        getExtraValue: getExtraValue
    };
})();

// infomation of selected media
var SelectMediaInfo = (function () {
    var mediaType = '';
    var extraValue = '';

    var init = function () {
        mediaType = '';
        extraValue = '';
    };

    var setMediaType = function (media) {
        mediaType = media;
    };
    var getMediaType = function () {
        return mediaType;
    };

    var setExtraValue = function (extra) {
        extraValue = extra;
    };
    var getExtraValue = function () {
        return extraValue;
    };

    return {
        init: init,
        setMediaType: setMediaType,
        getMediaType: getMediaType,
        setExtraValue: setExtraValue,
        getExtraValue: getExtraValue
    };
})();

var TokenMediaInfo = (function () {
    var TokenMediaObj = '';

    var init = function () {
        TokenMediaObj = '';
    };

    var setTokenMediaObj = function (obj) {
        TokenMediaObj = obj;
    };

    var getTokenMediaObj = function () {
        return TokenMediaObj;
    };

    return {
        init: init,
        setTokenMediaObj: setTokenMediaObj,
        getTokenMediaObj: getTokenMediaObj
    };
})();


// HDD: MediaType, ExtraValue Set
function NX_SetMediaHDD() {

    NX_Issue_pubUi.moreSaveMediaHide();

    TargetMediaInfo.setMediaType('HDD');
    TargetMediaInfo.setExtraValue('NULL');
    SelectMediaInfo.setMediaType('HDD');
    SelectMediaInfo.setExtraValue('NULL');

    var Logic_flag = processLogic.getProcessLogic();

    if (Logic_flag === 'KICA.ISSUE.IssueCert' ||
        Logic_flag === 'KICA.ISSUE.ReissueCertificate') {
        var selectMedia_noti = NX_TARGET_DIALOG_TEXT_13 + ' : ';
        document.getElementById("targetMedia_noti").innerHTML = selectMedia_noti;

    } else {

        if (Logic_flag === 'KICA.MANAGEMENT.CopyCert') {
            var selectMedia_noti = NX_TARGET_DIALOG_TEXT_13 + ' : ';
            document.getElementById("targetMedia_noti").innerHTML = selectMedia_noti;
        }

        Dialog.selectStorage('HDD', 'NULL');
    }

}

// HDD: MediaType, ExtraValue Set
function NX_SetMediaHDD2() {

    TargetMediaInfo.setMediaType('HDD');
    TargetMediaInfo.setExtraValue('NULL');
    SelectMediaInfo.setMediaType('HDD');
    SelectMediaInfo.setExtraValue('NULL');

    var selectMedia_noti = NX_TARGET_DIALOG_TEXT_13 + ' : ' + NX_TARGET_DIALOG_TEXT_2;
    document.getElementById("targetMedia_noti").innerHTML = selectMedia_noti;
}
