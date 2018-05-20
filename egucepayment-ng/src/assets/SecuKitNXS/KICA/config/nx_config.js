/********
 * 
 * 
 */
secure=true;
toolkitPath="/PayOnlineGuce/";
/***********************************************
    Console log 
************************************************/
var ConsoleLogFlag = true;

/***********************************************
    block the outter WebUI before client Loading
************************************************/
var NXSBlockWrapLayer = true;

/***********************************************
   SecuKit NX JavaScript License
************************************************/
var NXS_LICENSE = '';
if (document.location.hostname.indexOf('domain') >= 0) {
    // domain license
    NXS_LICENSE = '';
}
else {
    // license code for test (90days valid)
    NXS_LICENSE = 'WjlWVGJEczhUYXNtSjhUTjEvLzFYMXlHbzgxSTdmTHhnSnFaTUZoa08xQWFmOHFPcUcydHU5WFRzUlVqTWNYTnFmZ0VhQm9STDZpcjA0WlFVVU9FY2c9PVxmT0RDMG1DV21yRT1cZzVvV3NpQkhyZXhaTklpaFJSMWRkVHRCTW5JPQ==';
}

/***********************************************
   JavaScript path : SecuKitNXS/KICA/config/LoadSecuKitNX.js
************************************************/
var secukitnxBaseDir = toolkitPath+"/SecuKitNXS/";

/***********************************************
    image path
************************************************/
var NX_MEDIA_IMG_URL = toolkitPath+'/SecuKitNXS/WebUI/images/media/';
var NX_DEFAULT_IMG_URL = toolkitPath+'/SecuKitNXS/WebUI/images/';

/***********************************************
    set locale  ex: KR, EN, FR
************************************************/
var NXLOCALE = 'FR';

/***********************************************
    banner image URL : width : 410px height : 93px
************************************************/
var NX_Banner_IMG_URL = NX_DEFAULT_IMG_URL + 'banner/default_banner.png';

/***********************************************
    SecuKit NX download path
************************************************/
var NXClient_DownLoad_URL = secukitnxBaseDir + 'Install/SecuKitNXS.exe';    /** Client ��ġ����**/
var NXClient_DownLoad_URL_XML = secukitnxBaseDir + 'Install/SecuXML.exe';   /** Client ��ġ����**/
var NXClient_DownLoad_URL_KMS = secukitnxBaseDir + 'Install/KMS.exe';       /** Client ��ġ����**/


/***********************************************
    SecuKit NX install page path
************************************************/
var NX_INSTALL_FLAG = false;                                 // true : using other install page / false : on page
var NX_INSTALL_PAGENAME = 'install';                      // name of install page : page name 
var NX_INSTALL_PAGE = './install.html';     // URL of install page


/***********************************************
    Client version information
************************************************/

var Module_KPMCNT_Ver = '1.0.0.45';
var Module_KPMSVC_Ver = '1.0.0.30';
var Module_NX_Ver = '1.0.3.0';

//===============================================
var Module_XML_Ver = '1.0.0.15';
var Module_KMS_Ver = '1.0.0.30';
//===============================================

/***********************************************
    KMS Info
************************************************/
var DEF_KMS_COUNT = "1";
var DEF_KMS_INFO = [{ "ip": "192.168.220.238", "port": "38443", "path": "/kmsapi/opp/clientRequest.do", "protocol": "POST", "kmsNumber": "1" }];

/***********************************************
    selection box moving - true : move / false : didnt move
************************************************/
var NX_DIALOG_MOVE = true;

/***********************************************
    SecuKitNX NX CharSet : client to browser
************************************************/
var inCharset = 'UTF-8';
var outCharset = 'UTF-8';

/***********************************************
    Bio Token PKCS#7 
************************************************/
var BioTokenP7Message = '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';

/***********************************************
    CA connection URL for issue/reissue/renew
************************************************/
var NX_CA_IP = 'gca.camgovca.cm', // ca IP
    NX_CA_PORT = '4502';

/***********************************************
    Policy
************************************************/
var NX_AnyPolicy = 'Y';
var NX_POLICIES = '1.2.410.200004.5.2.1.2';


/***********************************************
    Policy name
************************************************/
var oidList = {
	'2.16.120.200001.4.1.1.1.1':'Administration',
	'2.16.120.200001.4.1.1.1.2':'Server',
	'2.16.120.200001.4.1.1.1.3':'CorpPublic',
	'2.16.120.200001.4.1.1.2.1':'Individual',
	'2.16.120.200001.4.1.1.2.2':'CorpPrivate',
	'2.16.120.200001.4.1.1.2.3':'RAAdmin',
	'2.16.120.200001.4.1.1.2.4':'test'
};

/***********************************************
    set media storage on the certficate selection
************************************************/
var NX_SELECT_CERT_MEDIA = [
{
    MEDIA: 'HDD',
    ORDER: 2,           // display order
    ABLE: 'able',       // active or deactive (able / disable)
    DEFAULT: 'able'  // default media option (able / disable)
},
{
    MEDIA: 'USB',
    ORDER: 1,
    ABLE: 'able'
},
{
    MEDIA: 'HSM',
    ORDER: 3,
    ABLE: 'able'
},
{
    MEDIA: 'BIOHSM',
    ORDER: 4,
    ABLE: 'able'
},
{
    MEDIA: 'USIM',
    ORDER: 5,
    ABLE: 'able'
},
{
    MEDIA: 'EXTENSION',
    ORDER: 6,           // do not modify
    ABLE: 'able'
}
];

/***********************************************
    Set target media : when the issue/reissue
************************************************/
var NX_TARGET_MEDIA = [
{
    MEDIA: 'HDD',
    ORDER: 2, // display order
    ABLE: 'able'   // active or deactive (able / disable)
},
{
    MEDIA: 'USB',
    ORDER: 1,
    ABLE: 'able'
},
{
    MEDIA: 'HSM',
    ORDER: 3,
    ABLE: 'able'
},
{
    MEDIA: 'BIOHSM',
    ORDER: 4,
    ABLE: 'able'
},
{
    MEDIA: 'USIM',
    ORDER: 5,
    ABLE: 'able'
},
{
    MEDIA: 'EXTENSION',
    ORDER: 6,           // do not modify
    ABLE: 'able'
}
];

/***********************************************
     activation management windows - true : enable / false : disable
************************************************/
var CERTMGR_F = true;

/***********************************************
     USIM
************************************************/
//USIM DLL name
var USIMDRIVE_NAME = {
    RAON: "USIMCert.dll",         // raon security
    DREAM: "USIMDream.dll"        // dream security
};

//USIM download URL
var USIM_DOWNLOAD_URL = "http://center.smartcert.co.kr/";   //dream security
var USIM_OPEN_SIZE_W = '';
var USIM_OPEN_SIZE_H = '';

var USIM_DOWNLOAD_EXE = "http://ids.smartcert.kr/";

//USIM sitecode
var USIM_SITECODE = "000000000";

/***********************************************
     keyboard security
************************************************/
var NOS_F = false;	 
var RAON_F = false; 