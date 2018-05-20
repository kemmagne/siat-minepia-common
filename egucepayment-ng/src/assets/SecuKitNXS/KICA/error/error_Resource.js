//Script Error 정의
var KICA_ERROR_RESOURCE = (function () {
    var ERRORCODE_KR = {
        ERR_SCRIPT_RANGE: "정의되지 않은 오류",

        ERR_MEDIA_NO_SUPPORT: "해당 저장매체는 지원하지 않습니다.",

        //INSTALL
        ERR_CLIENT_NO_INSTALL: "SecuKit NXS 클라이언트 프로그램 설치가 필요합니다.",
        ERR_CLIENT_RELEASE_VERSION: "SecuKit NXS 클라이언트 프로그램이 최신버전이 아닙니다. 최신 버전으로 설치 후 진행하세요.",
        ERR_CLIENT_NO_INSTALL_XML: "SecuXML 클라이언트 프로그램 설치가 필요합니다.",
        ERR_CLIENT_RELEASE_VERSION_XML: "SecuXML 클라이언트 프로그램이 최신버전이 아닙니다. 최신 버전으로 설치 후 진행하세요.",

        ERR_CLIENT_NO_INSTALL_KMS: "KMS 클라이언트 프로그램 설치가 필요합니다.",
        ERR_CLIENT_RELEASE_VERSION_KMS: "KMS 클라이언트 프로그램이 최신버전이 아닙니다. 최신 버전으로 설치 후 진행하세요.",

        ERR_CLIENT_LICENSE : "라이센스가 일치하지 않습니다. 라이센스가 일치하지 않는 경우 프로그램이 정상적으로 동작하지 않을 수 있습니다.",

        //BASE64
        ERR_SCRIPT_BASE64_INPUT: "입력 정보가 없습니다.",

        //BioHSM
        ERR_SCRIPT_BIOHSM_INPUT: "입력 정보가 없습니다.",
        ERR_SCRIPT_BIOHSM_NO_SELECT_CERT: "선택된 인증서 정보가 없습니다.",
        ERR_SCRIPT_BIOHSM_NO_INPUT_EXTRAVALUE2: "공고번호 정보가 없습니다.",

        //Dialog
        ERR_SCRIPT_DIALOG_INPUT: "인증서를 선택 후 진행해 주십시오.",
        ERR_SCRIPT_DIALOG_SELECTCERT: "인증서 정보를 가져오는데 실패하였습니다.",
        ERR_SCRIPT_DIALOG_SELECT_STORAGE: "저장매체 정보를 가져오는데 실패하였습니다.",

        //Digest
        ERR_SCRIPT_DIGEST_INPUT: "입력 정보가 없습니다.",
        ERR_SCRIPT_DIGEST_INSERT_ALGO: "알고리즘 정보가 없습니다.",
        ERR_SCRIPT_DIGEST_INSERT_MAC: "MAC KEY 정보가 없습니다.",

        ERR_SCRIPT_DIGEST_INSERT_SOURCE: "원문 데이터 정보가 없습니다.",
        ERR_SCRIPT_DIGEST_INSERT_SOURCE_FILE: "원문 파일 정보가 없습니다.",

        //GET CertINFO
        ERR_SCRIPT_CERTINFO_INPUT: "입력 정보가 없습니다.",
        ERR_SCRIPT_CERTINFO_NO_SELECT_CERT: "선택된 인증서가 없습니다.",
        ERR_SCRIPT_CERTINFO_SSN_INPUT: "입력 정보가 없습니다.",

        //PKCS7
        ERR_SCRIPT_PKCS7_INPUT: "입력 정보가 없습니다.",
        ERR_SCRIPT_PKCS7_NO_SELECT_CERT: "선택된 인증서 정보가 없습니다.",
        ERR_SCRIPT_PKCS7_INSERT_SELECT_CERT_TYPE: "인증서 타입 정보가 없습니다.",
        ERR_SCRIPT_PKCS7_INSERT_EXTRACT_ORDER: "정보 추출을 위한 데이터(순번)가 없습니다.",
        ERR_SCRIPT_PKCS7_INSERT_SIGNED_DATA: "전자서명 데이터가 없습니다.",

        ERR_SCRIPT_PKCS7_INSERT_FILEPATH: "입력 파일 경로 정보가 없습니다.",
        ERR_SCRIPT_PKCS7_INSERT_IN_FILETYPE: "입력 파일 타입 정보가 없습니다.",
        ERR_SCRIPT_PKCS7_INSERT_OUTPUT_FILEPATH: "파일이 출력될 경로 정보가 입력되지 않았습니다.",
        ERR_SCRIPT_PKCS7_INSERT_OUT_FILETYPE: "출력 파일 타입 정보가 없습니다.",

        ERR_SCRIPT_PKCS7_ENVELOPE_SERVERCERT: "서버인증서 정보가 없습니다.",
        ERR_SCRIPT_PKCS7_ENVELOPE_SERVERKEY: "서버인증서 키 정보가 없습니다.",
        ERR_SCRIPT_PKCS7_ENVELOPE_SOURCE: "대상 정보가 없습니다.",

        ERR_SCRIPT_PKCS7_SING_ENVELOPE_SOURCE: "PKCS#7 전자서명 및 암호화 대상 정보가 없습니다.",
        ERR_SCRIPT_PKCS7_SING_ENVELOPE_IN_FILEPATH: "PKCS#7 전자서명 및 암호화 대상 입력 파일 정보가 없습니다.",
        ERR_SCRIPT_PKCS7_SING_ENVELOPE_OUT_FILETYPE: "PKCS#7 전자서명 및 암호화 결과 출력 파일 타입 정보가 없습니다.",

        //PKI
        ERR_SCRIPT_PKI_INPUT: "입력 정보가 없습니다.",
        ERR_SCRIPT_PKI_NO_SELECT_CERT: "선택된 인증서 정보가 없습니다.",
        ERR_SCRIPT_PKI_INSERT_SELECT_CERT_TYPE: "인증서 타입 정보가 없습니다.",
        ERR_SCRIPT_PKI_INSERT_ALGO: "알고리즘 정보가 없습니다.",
        ERR_SCRIPT_PKI_SIGN_SOURCE: "서명 대상 정보가 없습니다.",
        ERR_SCRIPT_PKI_SIGN_FILEPATH: "입력 파일 정보가 없습니다.",

        //Symmetric
        ERR_SCRIPT_SYMM_ALGO: "알고리즘 정보가 없습니다.",
        ERR_SCRIPT_SYMM_INSERT_KEYNAME: "KEY NAME 정보가 없습니다.",
        ERR_SCRIPT_SYMM_INSERT_ENC_SOURCE: "대칭키 암호화 대상 정보가 없습니다.",
        ERR_SCRIPT_SYMM_INSERT_DEC_SOURCE: "대칭키 복호화 대상 정보가 없습니다.",

        ERR_SCRIPT_SYMM_INSERT_ENC_SOURCE_FILEPATH: "대칭키 암호화 대상 파일 정보가 없습니다.",
        ERR_SCRIPT_SYMM_INSERT_DEC_SOURCE_FILEPATH: "대칭키 복호화 대상 파일 정보가 없습니다.",

        ERR_SCRIPT_SYMM_INSERT_SERVER_CERT: "서버인증서 정보가 없습니다.",
        ERR_SCRIPT_SYMM_INSERT_RSA_VERSION: "RSA 암호화 버전 정보가 없습니다.",

        ERR_SCRIPT_SYMM_INSERT_SERVERKEY: "서버 키 정보가 없습니다.",
        ERR_SCRIPT_SYMM_INSERT_ALGO: "알고리즘 정보가 없습니다.",
        ERR_SCRIPT_SYMM_INSERT_KEYIV: "KEY IV값 정보가 없습니다.",

        //Util
        ERR_SCRIPT_UTIL_INPUT: "입력 정보가 없습니다.",
        ERR_SCRIPT_UTIL_INSERT_CERTTYPE: "인증서 타입 정보가 없습니다.",
        ERR_SCRIPT_UTIL_INSERT_SERVERCERT: "서버 인증서 정보가 없습니다.",
        ERR_SCRIPT_UTIL_INSERT_RSA_VERSION: "RSA 암호화 버전 정보가 없습니다.",
        ERR_SCRIPT_UTIL_NO_SELECT_CERT: "선택된 인증서 정보가 없습니다.",
        ERR_SCRIPT_UTIL_INSERT_SSN: "사회 보장번호 정보가 없습니다.",

        //VID
        ERR_SCRIPT_VID_NO_SELECT_CERT: "선택된 인증서 정보가 없습니다.",
        ERR_SCRIPT_VID_INSERT_SNN: "사회 보장번호 정보가 없습니다.",


        //ISSUE
        ERR_SCRIPT_VALID_PW_INPUT: "인증서 비밀번호를 입력하세요.",
        ERR_SCRIPT_VALID_PW_RANGE: "인증서 비밀번호는 10자리 이상이어야 합니다.",
        ERR_SCRIPT_VALID_PW_SPECIAL: "인증서 비밀번호는 하나 이상의 영문자 숫자 및 특수문자를 포함해야 합니다. (예: ', \", \\, | 는 사용 불가능)",
        ERR_SCRIPT_VALID_PW_SPECIAL_SAME: "인증서 비밀번호는 연속된 동일한 3개의 문자 또는 숫자가 포함 될 수 없습니다. (예 : aaa)",
        ERR_SCRIPT_VALID_PW_SPECIAL_STRAIGHT: "인증서 비밀번호는 연속된 3개의 문자 또는 숫자가 포함 될 수 없습니다. (예 : abc, cba, 123)",
        ERR_SCRIPT_VALID_PW_INVALID: "입력한 비밀번호가 일치하지 않습니다.",
        ERR_SCRIPT_INPUT_AUTH_VALUE: "인증서 발급을 위한 인가코드 혹은 참조번호 정보가 없습니다."
    };

    var ERRORCODE_EN = {
        ERR_SCRIPT_RANGE: "Undefined error.",

        ERR_MEDIA_NO_SUPPORT: "Selected storage medium isn’t supported.",

        //INSTALL
        ERR_CLIENT_NO_INSTALL: "Need to install client software(SecuKit NXS)",
        ERR_CLIENT_RELEASE_VERSION: "Not the latest version of client software(SecuKit NXS). Proceed after install latest version.",

        ERR_CLIENT_NO_INSTALL_XML: "Need to install client software(SecuXML)",
        ERR_CLIENT_RELEASE_VERSION_XML: "Not the latest version of client software(SecuXML). Proceed after install latest version.",

        ERR_CLIENT_NO_INSTALL_KMS: "Need to install client software(KMS)",
        ERR_CLIENT_RELEASE_VERSION_KMS: "Not the latest version of client software(KMS). Proceed after install latest version.",

        ERR_CLIENT_LICENSE: "Client License Error.",

        //BASE64
        ERR_SCRIPT_BASE64_INPUT: "No input information",

        //BioHSM
        ERR_SCRIPT_BIOHSM_INPUT: "No input information",
        ERR_SCRIPT_BIOHSM_NO_SELECT_CERT: "No selected certificate information",
        ERR_SCRIPT_BIOHSM_NO_INPUT_EXTRAVALUE2: "No notification number information",

        //Dialog
        ERR_SCRIPT_DIALOG_INPUT: "No input information",
        ERR_SCRIPT_DIALOG_SELECTCERT: "Fail to get certificate information",
        ERR_SCRIPT_DIALOG_SELECT_STORAGE: "Fail to get storage medium information",

        //Digest
        ERR_SCRIPT_DIGEST_INPUT: "No input information",
        ERR_SCRIPT_DIGEST_INSERT_ALGO: "No algorithm information",
        ERR_SCRIPT_DIGEST_INSERT_MAC: "No mac key information",

        ERR_SCRIPT_DIGEST_INSERT_SOURCE: "No original data information",
        ERR_SCRIPT_DIGEST_INSERT_SOURCE_FILE: "No original file information",

        //GET CertINFO
        ERR_SCRIPT_CERTINFO_INPUT: "No input information",
        ERR_SCRIPT_CERTINFO_NO_SELECT_CERT: "No selected certificate information",
        ERR_SCRIPT_CERTINFO_SSN_INPUT: "No input information",
        ERR_SCRIPT_CERTINFO_KMCERT: "No encryption certificate information",

        //PKCS7
        ERR_SCRIPT_PKCS7_INPUT: "No input information",
        ERR_SCRIPT_PKCS7_NO_SELECT_CERT: "No selected certificate information",
        ERR_SCRIPT_PKCS7_INSERT_SELECT_CERT_TYPE: "No certificate type information",
        ERR_SCRIPT_PKCS7_INSERT_EXTRACT_ORDER: "No data to extract information",
        ERR_SCRIPT_PKCS7_INSERT_SIGNED_DATA: "No signature data information",

        ERR_SCRIPT_PKCS7_INSERT_FILEPATH: "No information of input file path",
        ERR_SCRIPT_PKCS7_INSERT_IN_FILETYPE: "No information of input file type",
        ERR_SCRIPT_PKCS7_INSERT_OUTPUT_FILEPATH: "No information of output file path",
        ERR_SCRIPT_PKCS7_INSERT_OUT_FILETYPE: "No information of output file type",

        ERR_SCRIPT_PKCS7_ENVELOPE_SERVERCERT: "No server certificate information",
        ERR_SCRIPT_PKCS7_ENVELOPE_SERVERKEY: "No key information of server certificate",
        ERR_SCRIPT_PKCS7_ENVELOPE_SOURCE: "No original data information",

        ERR_SCRIPT_PKCS7_SING_ENVELOPE_SOURCE: "No original data information",
        ERR_SCRIPT_PKCS7_SING_ENVELOPE_IN_FILEPATH: "No information of input file path",
        ERR_SCRIPT_PKCS7_SING_ENVELOPE_OUT_FILETYPE: "No information of output file type",

        //PKI
        ERR_SCRIPT_PKI_INPUT: "No input information",
        ERR_SCRIPT_PKI_NO_SELECT_CERT: "No selected certificate information",
        ERR_SCRIPT_PKI_INSERT_SELECT_CERT_TYPE: "No certificate type information",
        ERR_SCRIPT_PKI_INSERT_ALGO: "No algorithm information",
        ERR_SCRIPT_PKI_SIGN_SOURCE: "No original data information",
        ERR_SCRIPT_PKI_SIGN_FILEPATH: "No input file information",

        //Symmetric
        ERR_SCRIPT_SYMM_ALGO: "No algorithm information",
        ERR_SCRIPT_SYMM_INSERT_KEYNAME: "No key name information",
        ERR_SCRIPT_SYMM_INSERT_ENC_SOURCE: "No original data information for symmetric encryption",
        ERR_SCRIPT_SYMM_INSERT_DEC_SOURCE: "No target information for symmetric decryption",

        ERR_SCRIPT_SYMM_INSERT_ENC_SOURCE_FILEPATH: "No input file information for symmetric encryption",
        ERR_SCRIPT_SYMM_INSERT_DEC_SOURCE_FILEPATH: "No target file information for symmetric decryption",

        ERR_SCRIPT_SYMM_INSERT_SERVER_CERT: "No server certificate information",
        ERR_SCRIPT_SYMM_INSERT_RSA_VERSION: "No version information of RSA Encryption",

        ERR_SCRIPT_SYMM_INSERT_SERVERKEY: "No server key information",
        ERR_SCRIPT_SYMM_INSERT_ALGO: "No algorithm information",
        ERR_SCRIPT_SYMM_INSERT_KEYIV: "No Key-IV information",

        //Util
        ERR_SCRIPT_UTIL_INPUT: "No input information",
        ERR_SCRIPT_UTIL_INSERT_CERTTYPE: "No certificate type information",
        ERR_SCRIPT_UTIL_INSERT_SERVERCERT: "No server certificate information ",
        ERR_SCRIPT_UTIL_INSERT_RSA_VERSION: "No version information of RSA Encryption",
        ERR_SCRIPT_UTIL_NO_SELECT_CERT: "No selected certificate information",
        ERR_SCRIPT_UTIL_INSERT_SSN: "No information of social security number",

        //VID
        ERR_SCRIPT_VID_NO_SELECT_CERT: "No selected certificate information",
        ERR_SCRIPT_VID_INSERT_SNN: "No information of social security number",


        //ISSUE
        ERR_SCRIPT_VALID_PW_INPUT: "Please enter a password.",
        ERR_SCRIPT_VALID_PW_RANGE: "Your password must have at least 10 digits.",
        ERR_SCRIPT_VALID_PW_SPECIAL: "The password must contain at least one alpha-numeric and special characters. ( ', \", \\, | Characters Not available)",
        ERR_SCRIPT_VALID_PW_SPECIAL_SAME: "The password can not be the same, including three consecutive letters or numbers. (ex : aaa)",
        ERR_SCRIPT_VALID_PW_SPECIAL_STRAIGHT: "The password can not be contained three consecutive letters or numbers. (ex : abc, cba, 123)",
        ERR_SCRIPT_VALID_PW_INVALID: "The password you entered does not match.",
        ERR_SCRIPT_INPUT_AUTH_VALUE: "No information (AuthCode, RefCode)."
    };

    var ERRORCODE_FR = {
        ERR_SCRIPT_RANGE:	"Erreur inconnue.",
	
        ERR_MEDIA_NO_SUPPORT:	"Incompatibilite du media selectionne.",
	
        //INSTALL
        ERR_CLIENT_NO_INSTALL:	"Installez le logiciel(SecuKit NX)",    
        ERR_CLIENT_RELEASE_VERSION:	"Veuillez installer la version mise a jour du logiciel (SecuKit NX).",

        ERR_CLIENT_NO_INSTALL_XML: "Installez le logiciel(SecuXML)",
        ERR_CLIENT_RELEASE_VERSION_XML: "Veuillez installer la version mise a jour du logiciel(SecuXML).",

        ERR_CLIENT_NO_INSTALL_KMS: "Installez le logiciel(KMS)",
        ERR_CLIENT_RELEASE_VERSION_KMS: "Veuillez installer la version mise a jour du logiciel(KMS).",

        ERR_CLIENT_LICENSE: "Client License Error.",
        //BASE64
        ERR_SCRIPT_BASE64_INPUT:	"Aucune donnee entree",
	
        //BioHSM
        ERR_SCRIPT_BIOHSM_INPUT:	"Aucune donnee entree",
        ERR_SCRIPT_BIOHSM_NO_SELECT_CERT:	"Aucun detail sur ce certificat",
        ERR_SCRIPT_BIOHSM_NO_INPUT_EXTRAVALUE2:	"No notification number information",
	
        //Dialog
        ERR_SCRIPT_DIALOG_INPUT:	"Aucune donnee d'entree",
        ERR_SCRIPT_DIALOG_SELECTCERT:	"Echec d'obtention des details du certificat",
        ERR_SCRIPT_DIALOG_SELECT_STORAGE:	"Echec d'obtention des proprietes du media",
	
        //Digest
        ERR_SCRIPT_DIGEST_INPUT:	"Aucune donnee d'entree",
        ERR_SCRIPT_DIGEST_INSERT_ALGO:	"Algorithme inconnu",
        ERR_SCRIPT_DIGEST_INSERT_MAC:	"Cle Mac inconnue",
	
        ERR_SCRIPT_DIGEST_INSERT_SOURCE:	"Aucune donnee d'entree",
        ERR_SCRIPT_DIGEST_INSERT_SOURCE_FILE:	"Pas de fichier selectionne",
	
        //GET CertINFO
        ERR_SCRIPT_CERTINFO_INPUT:	"Aucune donnee d'entree",
        ERR_SCRIPT_CERTINFO_NO_SELECT_CERT:	"Aucun detail sur ce certificat",
        ERR_SCRIPT_CERTINFO_SSN_INPUT:	"Aucune donnee d'entree",
        ERR_SCRIPT_CERTINFO_KMCERT:	"Aucun detail sur ce certificat",
	
        //PKCS7
        ERR_SCRIPT_PKCS7_INPUT:	"Aucune donnee d'entree",
        ERR_SCRIPT_PKCS7_NO_SELECT_CERT:	"Aucun detail sur ce certificat",
        ERR_SCRIPT_PKCS7_INSERT_SELECT_CERT_TYPE:	"Aucun detail sur le type du certificat",
        ERR_SCRIPT_PKCS7_INSERT_EXTRACT_ORDER:	"No data to extract information",
        ERR_SCRIPT_PKCS7_INSERT_SIGNED_DATA:	"Aucune donnee de signature",
        ERR_SCRIPT_PKCS7_INSERT_FILEPATH:	"Chemin du fichier inconnu",
        ERR_SCRIPT_PKCS7_INSERT_IN_FILETYPE:	"type de fichier inconnu",
        ERR_SCRIPT_PKCS7_INSERT_OUTPUT_FILEPATH:	"Chemin du fichier inconnu",
        ERR_SCRIPT_PKCS7_INSERT_OUT_FILETYPE:	"type de fichier inconnu",
        ERR_SCRIPT_PKCS7_ENVELOPE_SERVERCERT:	"Certificat serveur inconnu",
        ERR_SCRIPT_PKCS7_ENVELOPE_SERVERKEY:	"Cle du certificat serveur inconnue",
        ERR_SCRIPT_PKCS7_ENVELOPE_SOURCE:	"Aucune donnee d'entree",
        ERR_SCRIPT_PKCS7_SING_ENVELOPE_SOURCE:	"Aucune donnee d'entree",
        ERR_SCRIPT_PKCS7_SING_ENVELOPE_IN_FILEPATH:	"Chemin du fichier inconnu",
        ERR_SCRIPT_PKCS7_SING_ENVELOPE_OUT_FILETYPE:	"type de fichier inconnu",
	
        //PKI
        ERR_SCRIPT_PKI_INPUT:	"Aucune donnee d'entree",
        ERR_SCRIPT_PKI_NO_SELECT_CERT:	"Aucun detail sur ce certificat",
        ERR_SCRIPT_PKI_INSERT_SELECT_CERT_TYPE:	"Type de certificat inconnu",
        ERR_SCRIPT_PKI_INSERT_ALGO:	"Algorithme inconnu",
        ERR_SCRIPT_PKI_SIGN_SOURCE:	"Aucune donnee d'entree",
        ERR_SCRIPT_PKI_SIGN_FILEPATH:	"Fichier inconnu",
	
        //Symmetric
        ERR_SCRIPT_SYMM_ALGO:	"Algorithme inconnu",
        ERR_SCRIPT_SYMM_INSERT_KEYNAME:	"Nom de la cle inconnu",
        ERR_SCRIPT_SYMM_INSERT_ENC_SOURCE:	"Aucune donnee a chiffrer",
        ERR_SCRIPT_SYMM_INSERT_DEC_SOURCE:	"Aucune donnee a dechiffrer",
        ERR_SCRIPT_SYMM_INSERT_ENC_SOURCE_FILEPATH:	"Aucun fichier a chiffrer",
        ERR_SCRIPT_SYMM_INSERT_DEC_SOURCE_FILEPATH:	"Aucun fichier a dechiffrer",
        ERR_SCRIPT_SYMM_INSERT_SERVER_CERT:	"Certificat serveur inconnu",
        ERR_SCRIPT_SYMM_INSERT_RSA_VERSION:	"Version de l'algorithme RSA inconnu",
        ERR_SCRIPT_SYMM_INSERT_SERVERKEY:	"Absence de la cle du serveur",
        ERR_SCRIPT_SYMM_INSERT_ALGO:	"Algorithme inconnu",
        ERR_SCRIPT_SYMM_INSERT_KEYIV:	"Pas de Cle de l'IV",
	
        //Util
        ERR_SCRIPT_UTIL_INPUT:	"Aucune donnee d'entree",
        ERR_SCRIPT_UTIL_INSERT_CERTTYPE:	"Type de certificat inconnu",
        ERR_SCRIPT_UTIL_INSERT_SERVERCERT:	"Certificat serveur inconnu ",
        ERR_SCRIPT_UTIL_INSERT_RSA_VERSION:	"Version de l'algorithme RSA inconnu",
        ERR_SCRIPT_UTIL_NO_SELECT_CERT:	"Aucun detail sur ce certificat",
        ERR_SCRIPT_UTIL_INSERT_SSN:	"No information of social security number",
	
        //VID
        ERR_SCRIPT_VID_NO_SELECT_CERT:	"Aucun detail sur ce certificat",
        ERR_SCRIPT_VID_INSERT_SNN:	"Numero d'identification inconnu",
	
	
        //ISSUE
        ERR_SCRIPT_VALID_PW_INPUT:	"Entrez le mot de passe.",
        ERR_SCRIPT_VALID_PW_RANGE:	"Au moins 10 caracteres pour le mot de passe.",
        ERR_SCRIPT_VALID_PW_SPECIAL:	"Votre mot de passe doit contenir au moins un caractere alphanumerique et des caracteres speciaux. ( ', \", \\, | caracteres not acceptes)",
        ERR_SCRIPT_VALID_PW_SPECIAL_SAME:	"le mot de passe ne doit pas contenir 33 caracteres identiques successifs. (ex : aaa)",
        ERR_SCRIPT_VALID_PW_SPECIAL_STRAIGHT:	"Le mot de passe ne doit pas contenir 3 caracteres successifss. (ex : abc, cba, 123)",
        ERR_SCRIPT_VALID_PW_INVALID:	"Mot de passe incorrect.",
        ERR_SCRIPT_INPUT_AUTH_VALUE:	"Aucune infos (AuthCode, RefCode)."

    };

    var ErrorMessage = function (location, reason, errorCode) {
        var LocaleType = getNXLocaleInfo();
        var errorMessage = '',
            matchCode = '';
        if (LocaleType === 'KR') {

            matchCode = ERRORCODE_KR[errorCode];

            if (typeof matchCode === 'undefined' || matchCode === null) {
                errorMessage = ERRORCODE_KR[ERR_SCRIPT_RANGE];
            }
            else {
                errorMessage = ERRORCODE_KR[errorCode];
            }
            $('.nx-cert-select').hide(); $('#nx-pki-ui-wrapper').hide(); KICA_Error.init();
            KICA_Error.setScriptError(location, reason, errorMessage);
        }
        else if (LocaleType === 'EN') {

            matchCode = ERRORCODE_EN[errorCode];

            if (typeof matchCode === 'undefined' || matchCode === null) {
                errorMessage = ERRORCODE_EN[ERR_SCRIPT_RANGE];
            }
            else {
                errorMessage = ERRORCODE_EN[errorCode];
                errorMessage;
            }
            $('.nx-cert-select').hide(); $('#nx-pki-ui-wrapper').hide(); KICA_Error.init();
            KICA_Error.setScriptError(location, reason, errorMessage);
        }
		else if (LocaleType === 'FR') {

            matchCode = ERRORCODE_FR[errorCode];

            if (typeof matchCode === 'undefined' || matchCode === null) {
                errorMessage = ERRORCODE_FR[ERR_SCRIPT_RANGE];
            }
            else {
                errorMessage = ERRORCODE_FR[errorCode];
                errorMessage;
            }
            $('.nx-cert-select').hide(); $('#nx-pki-ui-wrapper').hide(); KICA_Error.init();
            KICA_Error.setScriptError(location, reason, errorMessage);
        }
    };

    return {
        ErrorMessage: ErrorMessage
    };
})();