package com.loungeapp.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.loungeapp.configuration.CustomUserDetails;
import com.loungeapp.domain.OtpDtl;
import com.loungeapp.domain.RoleMasterDtl;
import com.loungeapp.exception.ServicesException;
import com.loungeapp.model.PaginationDetails;
import com.loungeapp.persitence.ConfigurationRepository;
import com.loungeapp.persitence.OtpDtlRepository;

@Component
public class CommonUtility {

	@Autowired
	Properties configProperties;

	@Autowired
	private ConfigurationRepository configurationRepository;

	@Autowired
	Properties responseMessageProperties;


	@Autowired
	private OtpDtlRepository otpDtlRepository;

	// FOR PDF CREATIOn
	/*
	 * static Font infoFont = new Font(Font.FontFamily.TIMES_ROMAN, 8,
	 * Font.BOLD); static Font defFont = new Font(Font.FontFamily.TIMES_ROMAN,
	 * 8); static Font defFont1 = new Font(Font.FontFamily.TIMES_ROMAN, 8,
	 * Font.BOLD);
	 */
	public static final String logo = "https://dl.dropboxusercontent.com/s/l4mn9ua405hazmm/NTC-LOGO-2018.png?dl=0";
	// static Font invoiceDetailsFont = new Font(Font.FontFamily.TIMES_ROMAN,
	// 12, Font.BOLD);
	final static String INVOICE_HEADER = "NEXUS TECHNOCRAFTS AUTOMATIONS & SOLUTIONS PVT. LTD.";

	// final String pdfFileDrivePath =
	// configProperties.getProperty("pdf.file.path");
	// static String pdfFileDrivePath =
	// "C:\\\\Users\\\\Administrator\\\\Documents\\\\NTCPDF";
	static String pdfFileExt = ".pdf";

	public static Date getParsedDate(String dateToParse) throws ParseException {

		String[] formatStrings = new String[] { "M/y", "M/d/y", "M-d-y", "dd-mm-yy", "dd/mm/yyyy", "dd.mm.yyyy",
				"d month yyyy", "yyyy-mm-dd", " MMM/DD/YYYY", "yyyymmdd", "dd-mm-yyyy", "DD-MM-YY ", "YYYYMMDD",
				"dd-mmm-yyyy HH:MM:SS", "yyyymmddTHHMMSS", "yyyy-mm-dd HH:MM:SS", "mmm.dd.yyyy HH:MM:SS" };

		System.out.println("date To Parse : " + dateToParse);
		return DateUtils.parseDateStrictly(dateToParse, formatStrings);
	}

	/**
	 * Description : Validates the input parameters for null check and empty
	 * 
	 * @param Map<Object,
	 *            Object>
	 * @return Object
	 */
	public Object isInputValid(Map<Object, Object> inputParams) {

		Object returnVal = null;

		Set<Object> keySet = inputParams.keySet();
		for (Object key : keySet) {
			Object inputParam = inputParams.get(key);
			if (null == inputParam) {
				returnVal = key;
				break;
			} else if ((inputParam instanceof String) && ((String) inputParam).isEmpty()) {
				returnVal = key;
				break;
			}
		}

		return returnVal;
	}

	public Object selectConfigurationValue(String configName) {

		return configurationRepository.findByConfigName(configName);
		// return jdbcTemplate.queryForObject("select CONFIG_VAL from
		// configuration where CONFIG_NAME=?", Integer.class,
		// configName);
	}

	// GENEARTE RANDOM OTP
	public String getOTP() {
		Random random = new Random();
		int otp = random.nextInt(10000);
		return Integer.toString(otp);
	}

	// VERIFY OTP USING CNTC NUM AND OTP AND DEVICE INFO
	public boolean verifyOTP(String cntcNum, int otp, String deviceInfo) throws ServicesException, Exception {
		List<OtpDtl> otpDetailsList = otpDtlRepository.findByCellnumberAndOtpAndDeviceInfo(cntcNum, otp, deviceInfo);
		int otpDetailsSize = otpDetailsList.size();
		if (otpDetailsSize == 1) {
			OtpDtl otpDetails = otpDetailsList.get(0);
			// TODO: Check if OTP is Expired. i.e. OTP sent time more than 15
			// mins
			if (otpDetails.getOtp() == otp) {
				otpDtlRepository.delete(otpDetails.getOtpId()); // Clear out the
																// OTP record
				return true;
			} else {
				return false;
			}
		} else if (otpDetailsSize == 0) {
			return false;
		} else {
			throw new ServicesException("622");
		}
	}

	// GENERATE UUID USING UUID
	public String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}

	/**
	 * Sends email to the toAddress specified, with message body containing the
	 * baseURL appended with activate user account page. This forms the
	 * activation link for the user's account
	 * 
	 * @param toAddress
	 *            The email address to which mail is to be sent
	 * @param baseURL
	 *            The base URl of the application
	 * @return isEmailSent true if mail is sent successfully.false if mail
	 *         sending fails
	 */
	public boolean sendEmail(String toAddress, String message, String subject) {
		final String userName = configProperties.getProperty("email.username");

		final String password = configProperties.getProperty("email.password");
		boolean isEmailSent = false;
		try {
			// sets SMTP server properties
			Properties properties = new Properties();
			properties.put(configProperties.getProperty("smtp.server.host"),
					configProperties.getProperty("smtp.server.gmail"));
			properties.put("mail.smtp.port", 587);
			properties.put("mail.smtp.auth", "true");
			properties.put(configProperties.getProperty("smtp.server.starttls"),
					configProperties.getProperty("smtp.server.starttls.value"));
			System.out.println(configProperties.getProperty("smtp.server.gmail"));
			// creates a new session with an authenticator
			Authenticator auth = new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userName, password);
				}
			};
			Session session = Session.getInstance(properties, auth);
			// creates a new e-mail message
			Message mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress(userName));
			InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
			mimeMessage.setRecipients(Message.RecipientType.TO, toAddresses);
			mimeMessage.setSubject(subject);
			mimeMessage.setSentDate(new Date());

			// Create the message part
			MimeBodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
			messageBodyPart.setContent(message, "text/html");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			// Put parts in message
			mimeMessage.setContent(multipart);

			mimeMessage.setText(message);

			// sends the e-mail
			Transport.send(mimeMessage);
			isEmailSent = true;
			return isEmailSent;
		} catch (Exception e) {
			e.printStackTrace();
			return isEmailSent;
		}
	}

	public void sendEmailToAdmin(String message, String subject) {
		final String userName = configProperties.getProperty("email.username");

		final String password = configProperties.getProperty("email.password");

		try {
			// sets SMTP server properties
			Properties properties = new Properties();
			properties.put(configProperties.getProperty("smtp.server.host"),
					configProperties.getProperty("smtp.server.gmail"));
			properties.put("mail.smtp.port", 587);
			properties.put("mail.smtp.auth", "true");
			properties.put(configProperties.getProperty("smtp.server.starttls"),
					configProperties.getProperty("smtp.server.starttls.value"));
			System.out.println(configProperties.getProperty("smtp.server.gmail"));
			// creates a new session with an authenticator
			Authenticator auth = new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userName, password);
				}
			};
			Session session = Session.getInstance(properties, auth);
			// creates a new e-mail message
			Message mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress(userName));
			// InternetAddress[] toAddresses = { new
			// InternetAddress("tekchandbheda@gmail.com"), new
			// InternetAddress("komaldahanu@gmail.com") };
			InternetAddress[] toAddresses = { new InternetAddress("piyush.jadhav@repleteinc.com"),
					new InternetAddress("piyushjadhav65@gmail.com") };
			mimeMessage.setRecipients(Message.RecipientType.TO, toAddresses);
			mimeMessage.setSubject(subject);
			mimeMessage.setSentDate(new Date());

			// Create the message part
			MimeBodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
			messageBodyPart.setContent(message, "text/html");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			// Put parts in message
			mimeMessage.setContent(multipart);

			mimeMessage.setText(message);

			// sends the e-mail
			Transport.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void checkRowAffected(int rowAffected) throws ServicesException {
		if (rowAffected != 1)
			throw new ServicesException("Something went wrong while performing updates");
	}

	public CustomUserDetails getLoggedUser() {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return (CustomUserDetails) userDetails;
	}

	public Set<RoleMasterDtl> getLoggedUserRolesDtl() {

		CustomUserDetails userDetails = getLoggedUser();
		return userDetails.getRoles();
	}

	public List<String> getLoggedUserRoles() {

		CustomUserDetails userDetails = getLoggedUser();
		Set<RoleMasterDtl> roles = userDetails.getRoles();
		List<String> rolesList = new ArrayList<String>();

		for (RoleMasterDtl roleMasterDtl : roles) {
			rolesList.add(roleMasterDtl.getRolesMasterDtlsId());
		}

		return rolesList;
	}

	public void checkRowUpdated(int rowAffected) {
		if (rowAffected != 1) {
			throw new ServiceException("608");
		}

	}

	public String getLoggedUserId() {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;
		return customUserDetails.getUserDtl().getUserDtlsId();
	}

	/*
	 * public PdfPCell insertCell(String text, Font font, int elementAlign) {
	 * PdfPCell cell = new PdfPCell(); cell.setPaddingBottom(5); cell = new
	 * PdfPCell(new Phrase(text, font));
	 * cell.setHorizontalAlignment(elementAlign); cell.setBorder(PdfPCell.BOX);
	 * return cell; }
	 * 
	 * public PdfPCell insertCellWithouBorder(String text, Font font, int
	 * elementAlign) { PdfPCell cell = new PdfPCell(); cell.setPaddingBottom(5);
	 * cell = new PdfPCell(new Phrase(text, font));
	 * cell.setHorizontalAlignment(elementAlign);
	 * cell.setBorder(PdfPCell.NO_BORDER); return cell; }
	 * 
	 * public PdfPCell insertImageCellWithouBorder(Image img) { PdfPCell
	 * imageCell = new PdfPCell(); img.setAlignment(Image.ALIGN_CENTER);
	 * imageCell.addElement(img); imageCell.setBorder(PdfPCell.TOP |
	 * PdfPCell.RIGHT | PdfPCell.LEFT); return imageCell; }
	 */

	public PageRequest getPageRequest(int pageNo) {

		int maxFetchSize = Integer.parseInt(configProperties.getProperty("records.maxfetch.size"));
		return new PageRequest((pageNo - 1), maxFetchSize);
	}
	
	public PageRequest getPageRequest(int pageNo, Sort sort) {

		int maxFetchSize = Integer.parseInt(configProperties.getProperty("records.maxfetch.size"));
		return new PageRequest((pageNo - 1), maxFetchSize, sort);
	}


	public PageRequest getPageRequestSortedByCreatedTs(int pageNo) {
		int maxFetchSize = Integer.parseInt(configProperties.getProperty("records.maxfetch.size"));
		Sort sort = new Sort(Direction.DESC, "createdTs");
		return new PageRequest((pageNo - 1), maxFetchSize, sort);
	}

	public PaginationDetails getPaginationDetails(double totalCount, double currentPageNo) {

		double maxFetchSize = Double.parseDouble(configProperties.getProperty("records.maxfetch.size"));
		double totalPages = Math.ceil(totalCount / maxFetchSize);
		System.out.println("Total Count = " + totalCount + ", Total Pages = " + totalPages);

		PaginationDetails paginationDetails = new PaginationDetails(totalPages, currentPageNo);
		return paginationDetails;
	}

	public float calGrandTotal(float subTotal, float taxPercentage, float discount) {
		return ((subTotal) + (subTotal / 100) * taxPercentage) - discount;
	}

	public String getISTTimeFromUTCTime12HourFormat(String utcTime) throws ParseException {

		System.out.println("UTC Time String = " + utcTime);

		DateFormat sdf = new SimpleDateFormat("hh:mm a");
		// sdf.setTimeZone(TimeZone.getTimeZone("IST"));
		DateFormat df = new SimpleDateFormat("HHmmss");

		Calendar cal = Calendar.getInstance();
		cal.setTime(df.parse(utcTime));

		String utcTime12HourFormat = sdf.format(cal.getTime());
		System.out.println("UTC Time 12 Hour = " + utcTime12HourFormat);

		cal.add(Calendar.HOUR, 5);
		cal.add(Calendar.MINUTE, 30);

		String istTime12HourFormat = sdf.format(cal.getTime());
		System.out.println("IST Time = " + istTime12HourFormat);

		return istTime12HourFormat;
	}

	public Double getDistanceFromLatLonInKm(Double lat1, Double lon1, Double lat2, Double lon2) {

		// System.out.println("lat1 = " + lat1 + ", lon2 = " + lon2 + ", lat2 =
		// " + lat2 + ", lon2 = " + lon2);

		int R = 6371; // Radius of the earth in km
		Double dLat = deg2rad(lat2 - lat1); // deg2rad below
		Double dLon = deg2rad(lon2 - lon1);
		Double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
		Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		Double d = R * c; // Distance in km

		return d;
	}

	public Double deg2rad(Double deg) {
		return deg * (Math.PI / 180);
	}

	public Double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}

	public boolean hasRole(String roleToCheck) {

		List<String> loggedInUserRoles = getLoggedUserRoles();
		return loggedInUserRoles.contains(roleToCheck);
	}

	public long getDiffInMinutes(long startTs, long endTs) {

		long diff = endTs - startTs;
		System.out.println("diff = " + diff + ", endTs = " + endTs + ",  startTs = " + startTs);

		long diffInMinutes = diff / (60 * 1000) % 60;
		System.out.println("Diff in Mins = " + diffInMinutes);

		return diffInMinutes;
	}

	public Date getDateFromUtcDtAndTmFromTln(String utcDt, String utcTm) throws ParseException {
		String dateStringToConvert = utcDt + utcTm;

		DateFormat dfDdMmYyHhMmSs = new SimpleDateFormat("ddMMyyHHmmss");
		DateFormat dfYyyyMmDdHhMmSs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date convertedDate = dfYyyyMmDdHhMmSs.parse(dfYyyyMmDdHhMmSs.format(dfDdMmYyHhMmSs.parse(dateStringToConvert)));
		return convertedDate;
	}

	public Date convertUTCToIST(Date dateOfEntryOrExit) throws ParseException {

		DateFormat dfIST = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dfIST.setTimeZone(TimeZone.getTimeZone("IST"));
		Date dateToReturn = dfIST.parse(dfIST.format(dateOfEntryOrExit));
		return dateToReturn;
	}

	@Autowired

	public String getCurrentTsString() {

		DateFormat dfYyyyMmDdHhMmSs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dfYyyyMmDdHhMmSs.setTimeZone(TimeZone.getTimeZone("UTC"));

		return dfYyyyMmDdHhMmSs.format(new Date());
	}

	// CREATE CELLS WITH BORDER
	public Cell cellWithBorder(int rowCounter, int cellNo, HSSFRow row, HSSFWorkbook workbook) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);

		Cell cell = row.createCell(cellNo);
		cell.setCellStyle(style);
		return cell;

	}


	public String getUtcTsFromIst(String tsToCompare) throws ParseException {

		DateFormat dfUtc = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dfUtc.setTimeZone(TimeZone.getTimeZone("UTC"));

		DateFormat dfIst = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dfIst.setTimeZone(TimeZone.getTimeZone("IST"));

		Date istDate = dfIst.parse(tsToCompare);
		System.out.println("istDate = " + istDate);

		String utcTs = dfUtc.format(istDate);
		System.out.println("utcTs = " + utcTs);

		return utcTs;
	}


	public String getISTDateTimeFromUTCDateTime(String utcDateTime) throws ParseException {

		DateFormat dfUtc = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dfUtc.setTimeZone(TimeZone.getTimeZone("UTC"));

		DateFormat dfIst = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dfUtc.setTimeZone(TimeZone.getTimeZone("IST"));

		Date utcFormatDate = dfUtc.parse(utcDateTime);

		String istDateTime = dfIst.format(utcFormatDate);

		System.out.println("UTC Time = " + utcDateTime + ", IST = " + istDateTime);

		return istDateTime;
	}

}
