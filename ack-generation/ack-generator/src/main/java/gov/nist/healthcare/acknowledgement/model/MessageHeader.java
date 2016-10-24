package gov.nist.healthcare.acknowledgement.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class MessageHeader {
	private String separators;
	private String sendApp;
	private String sendFa;
	private String recApp;
	private String dateTime;
	private String msgType;
	private String messageControl;
	private String processId;
	private String versionId;
	private String aaType;
	private String appAType;
	private String profileI;
	
	
	
	
	public MessageHeader(String reF) {
		super();
		long ms = System.currentTimeMillis();
		Date resultdate = new Date(ms);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssZZZZ");
		this.dateTime = sdf.format(resultdate);
		
		this.separators = "|^~\\&";
		this.sendApp    = "AckGen";
		this.sendFa     = "NIST";
		this.recApp		= reF;
		this.msgType    = "ACK^V04^ACK";
		this.messageControl = UUID.randomUUID().toString();
		this.processId	= "P";
		this.versionId	= "2.5.1";
		this.aaType		= "NE";
		this.appAType	= "NE";
		this.profileI	= "Z23^CDCPHINVS";
		
	}
	
	public String toString(){
		return "MSH"+separators+"|"+sendApp+"|"+sendFa+"|"+recApp+"||"+dateTime+"||"+msgType+"|"+messageControl+"|"+processId+"|"+versionId
				+ "|||"+aaType+"|"+appAType+"|||||"+profileI;
	}
	
	
	public String getSeparators() {
		return separators;
	}
	public void setSeparators(String separators) {
		this.separators = separators;
	}
	public String getSendApp() {
		return sendApp;
	}
	public void setSendApp(String sendApp) {
		this.sendApp = sendApp;
	}
	public String getSendFa() {
		return sendFa;
	}
	public void setSendFa(String sendFa) {
		this.sendFa = sendFa;
	}
	public String getRecApp() {
		return recApp;
	}
	public void setRecApp(String recApp) {
		this.recApp = recApp;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getMessageControl() {
		return messageControl;
	}
	public void setMessageControl(String messageControl) {
		this.messageControl = messageControl;
	}
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	public String getVersionId() {
		return versionId;
	}
	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}
	public String getAaType() {
		return aaType;
	}
	public void setAaType(String aaType) {
		this.aaType = aaType;
	}
	public String getAppAType() {
		return appAType;
	}
	public void setAppAType(String appAType) {
		this.appAType = appAType;
	}
	public String getProfileI() {
		return profileI;
	}
	public void setProfileI(String profileI) {
		this.profileI = profileI;
	}
	
	
	
}
