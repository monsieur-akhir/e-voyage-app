package tech.dev.eVoyageBackend.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class ParamsUtils {

	@Value("${smtp.mail.adresse}")
	private String smtpLogin;

	@Value("${url.admin}")
	private String urlAdmin;
	
	@Value("${url.appl}")
	private String urlAppl;

	@Value("${smtp.mail.host}")
	private String smtpHost;

	@Value("${smtp.mail.port}")
	private Integer smtpPort;

	@Value("${smtp.mail.adresse}")
	private String smtpMailAdresse;

	@Value("${smtp.mail.password}")
	private String smtpPassword;

	@Value("${baseUrl}")
	private String baseUrl;
	
//	@Value("${api.annulation.pin}")
//    protected String pin;
//    @Value("${api.annulation.addon_id}")
//    protected String addonId;
//    @Value("${api.annulation.country_id}")
//    protected String countryId;
//    @Value("${api.annulation.username}")
//    protected String autoDebitusername;
//    @Value("${api.annulation.password}")
//    protected String autoDebitpassword;
//    @Value("${api.annulation.hostname.cdf}")
//    protected String hostnameCdf;
//    @Value("${api.annulation.hostname.usd}")
//    protected String hostnameUsd;
//    @Value("${api.annulation.authEndpoint}")
//    protected String authEndpoint;
//    @Value("${api.annulation.initCorrection}")
//    protected String initCorrectionEndPoint;
//    @Value("${api.annulation.freezEndpoint}")
//    protected String freezEndpoint;
//    @Value("${api.annulation.rejectApprove}")
//    protected String rejectApproveEndPoint;
//    @Value("${api.annulation.userenquiry}")
//    protected String userEnqueryEndPoint;
//    @Value("${api.annulation.currency.cdf}")
//    protected String cdfCurrency;
//    @Value("${api.annulation.currency.usd}")
//    protected String usdCurrency;
//    @Value("${api.annulation.userenquiry.addon_id}")
//    protected String userEnquiryAdonId;
    
	
//	@Value("${api.username}")
//	private String apiUsername;
//
//	@Value("${api.password}")
//	private String apiPasssword;
//
//	@Value("${api.channelId}")
//	private String apiChannelId;
//
//	@Value("${api.pricePlanCode}")
//	private String apipRicePlanCode;
//	
//	@Value("${api.acctResCode}")
//	private String AcctResCode;
//
//	@Value("${api.pin.om}")
//	private String apiPin;
//
//	@Value("${api.username.om}")
//	private String apiUsernameOm;
//
//	@Value("${api.password.om}")
//	private String apiPassorwdOm;
//
//	@Value("${api.mercode.om.cdf}")
//	private String apiMercodeOmCdf;
//
//	@Value("${api.mercode.om.usd}")
//	private String apiMercodeOmUsd;

//    @Value("${file.directory.templates}")
//    private String templatesDirectory;
//    
//    @Value("${pricePlanCode-sup20}")
//    private String pricePlanCodeSup20;
//    
//    @Value("${pricePlanCode-inf20}")
//    private String pricePlanCodeInf20;
//    
//    @Value("${csv.file-path}")
//    private String csvFilePath;
//    
//    @Value("${file.name.csv}")
//    private String fileNameCsv;
//    
//    @Value("${url-bonusTV}")
//    private String urlBonusTV;
//    
//    @Value("${url.grade}")
//    private String urlGrade;
//    
//    @Value("${url.bonnus.recharge.data}")
//    private String urlBonnusRechageData;
//    
//    @Value("${grade.marchand}")
//    private String gradeMarchant;
//    
//    @Value("${grade.marchSimpl}")
//    private String gradeMarchSimpl;
//    
//    //ConfigurationValues
//    @Value("${bonus.valable.1jour}")
//    private Integer bonusValable1Jour;
//
//    @Value("${bonus.valable.3jour}")
//    private Integer bonusValable3Jour;
//
//    @Value("${bonus.valable.5jour}")
//    private Integer bonusValable5Jour;
//
//    @Value("${montant.tv.max}")
//    private Integer montantTvMax;
//
//    @Value("${randomval}")
//    private Integer randomVal;
//
//    @Value("${transactionsn}")
//    private Integer transactionSn;
//
//    @Value("${montant.min.petit}")
//    private Double montantMinPetit;
//
//    @Value("${montant.max.petit}")
//    private Double montantMaxPetit;
//
//    @Value("${jours.petit}")
//    private Integer joursPetit;
//
//    @Value("${montant.min.moyen}")
//    private Double montantMinMoyen;
//
//    @Value("${montant.max.moyen}")
//    private Double montantMaxMoyen;
//
//    @Value("${jours.moyen}")
//    private Integer joursMoyen;
//
//    @Value("${montant.min.grand}")
//    private Double montantMinGrand;
//
//    @Value("${montant.max.grand}")
//    private Double montantMaxGrand;
//
//    @Value("${jours.grand}")
//    private Integer joursGrand;
//
//    @Value("${usd.to.cdf}")
//    private Double usdToCdf;
//
//    @Value("${bonus.percentage}")
//    private Double bonusPercentage;
//
//    @Value("${usd.to.u}")
//    private Double usdToU;
//
//    @Value("${usd}")
//    private Double usd;
//    
    @Value("${url-envoie-sms}")
	private String urlEnvoieSMS;
//    
    @Value("${session-time}")
	private Integer sessionTime;
//    
//    @Value("${file.export}")
//	private String fileExport;
    
    
//	@Value("${module.code}")
//	private String	moduleCode;

}
