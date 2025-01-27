
/*
 * Created on 2024-02-19 ( Time 10:54:59 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Geo. All Rights Reserved.
 */

 package tech.dev.eVoyageBackend.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.LocaleUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.security.crypto.bcrypt.BCrypt;
 
 /**
  * Utilities
  * 
  * @author Geo
  *
  */
 public class Utilities {
	 static final String alphabet = "#abcdefghijklmnopqrstuvwxyz";
 
 
	 public static Date getCurrentDate() {
		 return new Date();
	 }
 
	 public static boolean areEquals(Object obj1, Object obj2) {
		 return (Objects.equals(obj1, obj2));
	 }
	 public static boolean isValidID(Integer id) {
		 return id != null && id > 0;
	 }
 
	 public static <T extends Comparable<T>, Object> boolean areEquals(T obj1, T obj2) {
		 return (obj1 == null ? obj2 == null : obj1.equals(obj2));
	 }
 
	 public static String generatenumericCode(Integer nbreCaractere) {
		 String formatted = null;
		 formatted = RandomStringUtils.randomNumeric(nbreCaractere).toUpperCase();
		 return formatted;
	 }
 
	 public static boolean areNotEquals(Object obj1, Object obj2) {
		 return !areEquals(obj1, obj2);
	 }
 
	 public static <T extends Comparable<T>, Object> boolean areNotEquals(T obj1, T obj2) {
		 return !(areEquals(obj1, obj2));
	 }
 
	 private static final String[] IP_HEADER_CANDIDATES = { "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED", "HTTP_X_CLUSTER_CLIENT_IP",
			 "HTTP_CLIENT_IP", "HTTP_FORWARDED_FOR", "HTTP_FORWARDED", "HTTP_VIA", "REMOTE_ADDR" };
 
	 /**
	  * 
	  * @param request
	  * @return
	  */
	 public static String getClientIp(HttpServletRequest request) {
		 for (String header : IP_HEADER_CANDIDATES) {
			 String ip = request.getHeader(header);
			 if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
				 return ip;
			 }
		 }
		 return request.getRemoteAddr();
	 }
 
	 private static List<String> listeBase = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1",
			 "2", "3", "4", "5", "6", "7", "8", "9");
 
	 public static String combinaisonString() {
		 String lettres = "";
		 try {
			 Random random;
			 for (int i = 0; i < 10; i++) {
				 random = new Random();
				 int rn = random.nextInt(35 - 0 + 1) + 0;
				 lettres += listeBase.get(rn);
			 }
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 return lettres;
	 }
 
	 public static String formatDate(String date, String initDateFormat, String endDateFormat) throws ParseException {
		 if (!notBlank(date)) {
			 return "";
		 }
		 Date initDate = new SimpleDateFormat(initDateFormat).parse(date);
		 SimpleDateFormat formatter = new SimpleDateFormat(endDateFormat);
		 String parsedDate = formatter.format(initDate);
 
		 return parsedDate;
	 }
 
	 public static Date asDate(LocalDate localDate) {
		 return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	 }
 
	 public static Date asDate(LocalDateTime localDateTime) {
		 return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	 }
 
	 public static LocalDate asLocalDate(Date date) {
		 return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	 }
 
	 public static LocalDateTime asLocalDateTime(Date date) {
		 return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
	 }
 
	 public static int duration(Date startDate, Date endDate) {
		 long duration = ChronoUnit.DAYS.between(asLocalDate(startDate), asLocalDate(endDate));
		 return Integer.parseInt(String.valueOf(duration + 1));
	 }
 
	 public static int duration(LocalDate startLocalDate, LocalDate endLocalDate) {
		 long duration = ChronoUnit.DAYS.between(startLocalDate, endLocalDate);
		 return Integer.parseInt(String.valueOf(duration + 1));
	 }
	 
	 public static boolean isNotBlank(String str) {
		 return !isBlank(str);
	 }
 
	 /**
	  * Check if a String given is an Integer.
	  *
	  * @param s
	  * @return isValidInteger
	  *
	  */
	 public static boolean isInteger(String s) {
		 boolean isValidInteger = false;
		 try {
			 Integer.parseInt(s);
 
			 // s is a valid integer
			 isValidInteger = true;
		 } catch (NumberFormatException ex) {
			 // s is not an integer
		 }
 
		 return isValidInteger;
	 }
 
	 public static String generateCodeOld() {
		 String formatted = null;
		 formatted = RandomStringUtils.randomAlphanumeric(8).toUpperCase();
		 return formatted;
	 }
 
	 public static String generateCode() {
		 String formatted = null;
		 SecureRandom secureRandom = new SecureRandom();
		 int num = secureRandom.nextInt(100000000);
		 formatted = String.format("%05d", num);
		 return formatted;
	 }
 
	 public static boolean isTrue(Boolean b) {
		 return b != null && b;
	 }
 
	 public static boolean isFalse(Boolean b) {
		 return !isTrue(b);
	 }
 
	 public static boolean isNumeric(String str) {
		 try {
			 double d = Long.parseLong(str);
		 } catch (NumberFormatException nfe) {
			 return false;
		 }
		 return true;
	 }
 
	 /**
	  * Check if a Integer given is an String.
	  *
	  * @param i
	  * @return isValidString
	  *
	  */
	 public static boolean isString(Integer i) {
		 boolean isValidString = true;
		 try {
			 Integer.parseInt(i + "");
 
			 // i is a valid integer
 
			 isValidString = false;
		 } catch (NumberFormatException ex) {
			 // i is not an integer
		 }
 
		 return isValidString;
	 }
 
	 public static boolean isValidEmail(String email) {
		 String regex = "^(.+)@(.+)$";
		 Pattern pattern = Pattern.compile(regex);
		 Matcher matcher = pattern.matcher(email);
 
		 return matcher.matches();
	 }
 
 
	 public static boolean isValidateCongolesePhoneNumber(String phoneNumber) {
		 String regex = "^(\\(\\+243\\)|\\+243|\\(00243\\)|00243)?(\\s)?[0-9]{2}([ .-]?[0-9]{2}){3}$";
		 return (phoneNumber != null && phoneNumber.matches(regex));
	 }
 
	 public static String congolesePhoneNumberToStandardFormat(String phoneNumber) {
		 String beginRegex = "^(\\(\\+243\\)|\\+243|\\(00243\\)|00243)?";
		 String specialCharRegex = "[ .-]?";
		 String simplePhoneNumber;
 
		 if (phoneNumber == null)
			 return null;
 
		 simplePhoneNumber = phoneNumber.replaceAll(beginRegex, "");
		 return simplePhoneNumber.replaceAll(specialCharRegex, "");
	 }
 
 
	 public static String encrypt(String str) throws Exception {
		 MessageDigest digest = MessageDigest.getInstance("SHA-1");
		 byte[] hashedBytes = digest.digest(str.getBytes("UTF-8"));
 
		 return convertByteArrayToHexString(hashedBytes);
	 }
 
	 public static boolean isDateValid(String date) {
		 try {
			 String simpleDateFormat = "dd/MM/yyyy";
 
			 if (date.contains("-"))
				 simpleDateFormat = "dd-MM-yyyy";
			 else if (date.contains("/"))
				 simpleDateFormat = "dd/MM/yyyy";
			 else
				 return false;
 
			 DateFormat df = new SimpleDateFormat(simpleDateFormat);
			 df.setLenient(false);
			 df.parse(date);
			 return true;
		 } catch (ParseException e) {
			 return false;
		 }
	 }
 
	 public static String GenerateValueKey(String code) {
		 String result = null;
		 // String prefix = prefixe;
		 String suffix = null;
		 String middle = null;
		 String separator = "-";
		 final String defaut = "000001";
		 try {
 
			 SimpleDateFormat dt = new SimpleDateFormat("yy-MM-dd-ss");
			 String _date = dt.format(new Date());
			 String[] spltter = _date.split(separator);
			 middle = spltter[0] + spltter[1] + spltter[2] + spltter[3];
			 if (code != null) {
				 // Splitter le code pour recuperer les parties
				 // String[] parts = code(separator);
				 String part = code.substring(1);
				 System.out.println("part" + part);
 
				 if (part != null) {
					 int cpt = new Integer(part);
					 cpt++;
 
					 String _nn = String.valueOf(cpt);
 
					 switch (_nn.length()) {
					 case 1:
						 suffix = "00000" + _nn;
						 break;
					 case 2:
						 suffix = "0000" + _nn;
						 break;
					 case 3:
						 suffix = "000" + _nn;
						 break;
					 case 4:
						 suffix = "00" + _nn;
						 break;
					 case 5:
						 suffix = "0" + _nn;
						 break;
					 default:
						 suffix = _nn;
						 break;
					 }
					 // result = prefix + separator + middle + separator +
					 // suffix;
					 result = middle + separator + suffix;
				 }
			 } else {
				 // result = prefix + separator + middle + separator + defaut;
				 result = middle + separator + defaut;
			 }
		 } catch (Exception e) {
 
		 }
		 return result;
	 }
 
	 public static Integer getAge(Date dateNaissance) throws ParseException, Exception {
		 Integer annee = 0;
 
		 if (dateNaissance == null) {
			 annee = 0;
		 }
		 Calendar birth = new GregorianCalendar();
		 birth.setTime(dateNaissance);
		 Calendar now = new GregorianCalendar();
		 now.setTime(new Date());
		 int adjust = 0;
		 if (now.get(Calendar.DAY_OF_YEAR) - birth.get(Calendar.DAY_OF_YEAR) < 0) {
			 adjust = -1;
		 }
		 annee = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR) + adjust;
		 return annee;
	 }
 
	 public static Boolean AvailableCode(String code) {
		 if (code == null || code.isEmpty()) {
			 return false;
		 }
		 Locale local = new Locale(code, "");
		 return LocaleUtils.isAvailableLocale(local);
 
	 }
 
	 public static String normalizeFileName(String fileName) {
		 String fileNormalize = null;
		 fileNormalize = fileName.trim().replaceAll("\\s+", "_");
		 fileNormalize = fileNormalize.replace("'", "");
		 fileNormalize = Normalizer.normalize(fileNormalize, Normalizer.Form.NFD);
		 fileNormalize = fileNormalize.replaceAll("[^\\p{ASCII}]", "");
 
		 return fileNormalize;
	 }
 
	 private static String convertByteArrayToHexString(byte[] arrayBytes) {
		 StringBuffer stringBuffer = new StringBuffer();
		 for (int i = 0; i < arrayBytes.length; i++) {
			 stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16).substring(1));
		 }
		 return stringBuffer.toString();
	 }
 
	 public static SimpleDateFormat findDateFormat(String date) {
		 SimpleDateFormat simpleDateFormat = null;
		 String regex_dd_MM_yyyy = "\\A0?(?:3[01]|[12][0-9]|[1-9])[/.-]0?(?:1[0-2]|[1-9])[/.-][0-9]{4}\\z";
 
		 if (date.matches(regex_dd_MM_yyyy))
			 if (date.contains("-"))
				 simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
			 else if (date.contains("/"))
				 simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
 
		 return simpleDateFormat;
	 }
 
	 /**
	  * @return Permet de retourner la date courante du système
	  *
	  */
	 public static String getCurrentLocalDateTimeStamp() {
		 return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	 }
 
	 /**
	  * @param l
	  *            liste de vérification de doublons
	  * @return retourne le nombre de doublon trouvé
	  *
	  */
	 public static int getDupCount(List<String> l) {
		 int cnt = 0;
		 HashSet<String> h = new HashSet<>(l);
 
		 for (String token : h) {
			 if (Collections.frequency(l, token) > 1)
				 cnt++;
		 }
 
		 return cnt;
	 }
 
	 public static boolean saveImage(String base64String, String nomCompletImage, String extension) throws Exception {
 
		 BufferedImage image = decodeToImage(base64String);
 
		 if (image == null) {
 
			 return false;
 
		 }
 
		 File f = new File(nomCompletImage);
 
		 // write the image
 
		 ImageIO.write(image, extension, f);
 
		 return true;
 
	 }
 
	 public static boolean saveVideo(String base64String, String nomCompletVideo) throws Exception {
 
		 try {
 
			 byte[] decodedBytes = Base64.getDecoder().decode(base64String);
			 File file2 = new File(nomCompletVideo);
			 FileOutputStream os = new FileOutputStream(file2, true);
			 os.write(decodedBytes);
			 os.close();
 
		 } catch (Exception e) {
			 // TODO: handle exception
			 return false;
		 }
 
		 return true;
 
	 }
 
 
	 private boolean isValidPhoneNumber(String phoneNumber) {
		 // Vérifiez si le numéro de téléphone correspond à un format valide
		 Pattern pattern = Pattern.compile("^\\+?[0-9]{10,15}$");
		 Matcher matcher = pattern.matcher(phoneNumber);
		 return matcher.matches();
	 }
 
 
	 public static BufferedImage decodeToImage(String imageString) throws Exception {
 
		 BufferedImage image = null;
 
		 byte[] imageByte;
 
		 imageByte = Base64.getDecoder().decode(imageString);
 
		 try (ByteArrayInputStream bis = new ByteArrayInputStream(imageByte)) {
 
			 image = ImageIO.read(bis);
 
		 }
 
		 return image;
 
	 }
 
	 public static String encodeToString(BufferedImage image, String type) {
 
		 String imageString = null;
 
		 ByteArrayOutputStream bos = new ByteArrayOutputStream();
 
		 try {
 
			 ImageIO.write(image, type, bos);
 
			 byte[] imageBytes = bos.toByteArray();
 
			 imageString = new String(Base64.getEncoder().encode(imageBytes));
 
			 bos.close();
 
		 } catch (IOException e) {
 
			 e.printStackTrace();
 
		 }
 
		 return imageString;
 
	 }
 
	 public static String convertFileToBase64(String pathFichier) {
		 File originalFile = new File(pathFichier);
		 String encodedBase64 = null;
		 try {
			 FileInputStream fileInputStreamReader = new FileInputStream(originalFile);
			 byte[] bytes = new byte[(int) originalFile.length()];
			 fileInputStreamReader.read(bytes);
			 encodedBase64 = new String(Base64.getEncoder().encodeToString((bytes)));
		 } catch (FileNotFoundException e) {
			 e.printStackTrace();
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
 
		 return encodedBase64;
	 }
 
	 public static String getImageExtension(String str) {
		 String extension = "";
		 int i = str.lastIndexOf('.');
		 if (i >= 0) {
			 extension = str.substring(i + 1);
			 return extension;
		 }
		 return null;
	 }
 
	 public static boolean fileIsImage(String image) {
 
		 String IMAGE_PATTERN = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp|jpeg))$)";
		 Pattern pattern = Pattern.compile(IMAGE_PATTERN);
		 Matcher matcher = pattern.matcher(image);
 
		 return matcher.matches();
 
	 }
 
	 public static boolean fileIsVideo(String video) {
 
		 String IMAGE_PATTERN = "([^\\s]+(\\.(?i)(mp4|avi|camv|dvx|mpeg|mpg|wmv|3gp|mkv))$)";
		 Pattern pattern = Pattern.compile(IMAGE_PATTERN);
		 Matcher matcher = pattern.matcher(video);
 
		 return matcher.matches();
 
	 }
 
	 public static void createDirectory(String chemin) {
		 File file = new File(chemin);
		 if (!file.exists()) {
			 try {
				 FileUtils.forceMkdir(file);
			 } catch (IOException e) {
				 // TODO Auto-generated catch block
				 e.printStackTrace();
			 }
		 }
 
	 }
 
	 public static void deleteFolder(String chemin) {
		 File file = new File(chemin);
		 try {
			 if (file.exists() && file.isDirectory()) {
				 FileUtils.forceDelete(new File(chemin));
			 }
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
	 }
 
	 public static void deleteFile(String chemin) {
		 File file = new File(chemin);
		 try {
			 if (file.exists() && file.getName() != null && !file.getName().isEmpty()) {
 
				 FileUtils.forceDelete(new File(chemin));
 
			 }
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
	 }
 
	 public static boolean notBlank(String str) {
		 return str != null && !str.isEmpty() && !str.equals("\n");
	 }
 
	 public static boolean notEmpty(List<String> lst) {
		 return lst != null && !lst.isEmpty() && lst.stream().noneMatch(s -> s.equals("\n")) && lst.stream().noneMatch(s -> s.equals(null));
	 }
 
	 public static <T> boolean isNotEmpty(List<T> list){
		 return (list != null && !list.isEmpty());
	 }
 
	 static public String GetCode(String Value, Map<String, String> Table) {
 
		 for (Entry<String, String> entry : Table.entrySet()) {
			 if (entry.getValue().equals(Value)) {
				 return entry.getKey();
			 }
		 }
		 return Value;
	 }
 
	 public static boolean anObjectFieldsMapAllFieldsToVerify(List<Object> objets, Map<String, Object> fieldsToVerify) {
		 for (Object objet : objets) {
			 boolean oneObjectMapAllFields = true;
			 JSONObject jsonObject = new JSONObject(objet);
			 for (Entry<String, Object> entry : fieldsToVerify.entrySet()) {
				 // slf4jLogger.info("jsonObject " +jsonObject);
				 String key = entry.getKey();
				 Object value = entry.getValue();
				 try {
					 if (!jsonObject.get(key).equals(value)) {
						 oneObjectMapAllFields = false;
						 break;
					 }
				 } catch (Exception e) {
					 oneObjectMapAllFields = false;
					 break;
				 }
			 }
			 if (oneObjectMapAllFields)
				 return true;
		 }
 
		 return false;
	 }
 
	 public static String generateAlphanumericCode(Integer nbreCaractere) {
		 String formatted = null;
		 formatted = RandomStringUtils.randomAlphanumeric(nbreCaractere).toUpperCase();
		 return formatted;
	 }
 
	 public static String generateAlphanumericCodeLite(Integer nbreCaractere) {
		 String formatted = null;
		 formatted = RandomStringUtils.randomAlphanumeric(nbreCaractere);
		 return formatted;
	 }
 
	 public static Boolean verifierEmail(String email) {
		 Pattern emailPattern = Pattern.compile(".+@.+\\.[a-z]+");
		 Matcher emailMatcher = emailPattern.matcher(email);
		 return emailMatcher.matches();
	 }
 
	 public static <T> boolean isEmpty(List<T> list) {
		 return (list == null || list.isEmpty());
	 }
 
	 public static <T> List<T> paginner(List<T> allItems, Integer index, Integer size) {
		 if (isEmpty(allItems)) {
			 return null;
		 }
 
		 List<T> items = new ArrayList<T>();
		 // si une pagination est pécisée, ne prendre que les éléments demandés
		 if (index != null && size != null) {
			 Integer fromIndex = index * size;
			 if (fromIndex < allItems.size()) {
				 Integer toIndex = fromIndex + size;
				 if (toIndex > allItems.size())
					 toIndex = allItems.size();
				 items.addAll(allItems.subList(fromIndex, toIndex));
			 }
		 } else {
			 items.addAll(allItems);
		 }
 
		 return items;
	 }
	 public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list)
	 {
 
		 // Create a new LinkedHashSet
		 Set<T> set = new LinkedHashSet<>();
 
		 // Add the elements to set
		 set.addAll(list);
 
		 // Clear the list
		 list.clear();
 
		 // add the elements of set
		 // with no duplicates to the list
		 list.addAll(set);
 
		 // return the list
		 return list;
	 }
	 public static Integer getColumnIndex(String column) {
		 column = new StringBuffer(column.toLowerCase()).reverse().toString();
		 Double columnPosition = 0d;
		 for (int i = 0; i < column.length(); i++) {
			 columnPosition += alphabet.indexOf(column.charAt(i)) * Math.pow(26, i);
		 }
		 return columnPosition.intValue() - 1;
	 }
 
 
 
	 public static boolean isBlank(String str) {
		 int strLen;
		 if (str == null || (strLen = str.length()) == 0 || str.isEmpty()) {
			 return true;
		 }
		 for (int i = 0; i < strLen; i++) {
			 if ((Character.isWhitespace(str.charAt(i)) == false)) {
				 return false;
			 }
		 }
		 return true;
	 }
 
 
 
 
 
	 //	public static List<Date> determineFuturePrelevementDates(String frequencePrelevement, int numberOfPeriods, Date createdAt) {
	 //	    List<Date> futureDates = new ArrayList<>(); // Initialiser une liste vide pour stocker les dates futures.
	 //	    
	 //	    // Vérifier si la fréquence est "mensuelle". Sinon, lancer une exception.
	 //	    if (!frequencePrelevement.equalsIgnoreCase("mensuelle")) {
	 //	        throw new IllegalArgumentException("Unsupported frequency: " + frequencePrelevement);
	 //	    }
	 //
	 //	    // Obtenir une instance de Calendar et la définir à la date createdAt.
	 //	    Calendar calendar = Calendar.getInstance();
	 //	    calendar.setTime(createdAt);
	 //
	 //	    // Boucler pour calculer les dates futures pour le nombre de périodes spécifié.
	 //	    for (int i = 1; i <= numberOfPeriods; i++) {
	 //	        // Cloner le calendrier pour éviter de modifier la date originale.
	 //	        Calendar clonedCalendar = (Calendar) calendar.clone();
	 //	        
	 //	        // Ajouter 'i' mois au calendrier cloné.
	 //	        clonedCalendar.add(Calendar.MONTH, i);
	 //	        
	 //	        // Maintenir le même jour du mois que la date originale (createdAt).
	 //	        int originalDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
	 //	        int maxDayOfMonth = clonedCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	 //	        
	 //	        // Définir le jour du mois, en s'assurant qu'il ne dépasse pas le nombre maximum de jours du mois cible.
	 //	        clonedCalendar.set(Calendar.DAY_OF_MONTH, Math.min(originalDayOfMonth, maxDayOfMonth));
	 //
	 //	        // Ajouter la date calculée à la liste des dates futures.
	 //	        futureDates.add(clonedCalendar.getTime());
	 //	    }
	 //
	 //	    return futureDates; 
	 //	}
 
	 // Liste des formats de date
	 private static final List<String> FORMATS = Arrays.asList(
		 "dd/MM/yyyy", "dd-MM-yyyy", "yyyy-MM-dd", "MM/dd/yyyy", "yyyy/MM/dd",
		 "dd/MM/yyyy HH:mm:ss", "dd-MM-yyyy HH:mm:ss", "yyyy-MM-dd HH:mm:ss", 
		 "MM/dd/yyyy HH:mm:ss", "yyyy/MM/dd HH:mm:ss", "dd/MM/yyyy HH:mm",
		 "dd-MM-yyyy HH:mm", "yyyy-MM-dd HH:mm", "MM/dd/yyyy HH:mm", "yyyy/MM/dd HH:mm"
	 );
 
	 // DateTimeFormatter pour les formats modernes
	 private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
 
	 /**
	  * Parse a date string into a Date object, trying multiple formats.
	  *
	  * @param dateStr the date string
	  * @return the parsed Date object
	  * @throws ParseException if no formats matched
	  */
	 public static Date parseDate(String dateStr) throws ParseException {
		 // First try using DateTimeFormatter
		 try {
			 LocalDateTime localDateTime = LocalDateTime.parse(dateStr, DATE_FORMATTER);
			 return java.sql.Timestamp.valueOf(localDateTime);
		 } catch (Exception e) {
			 // Continue to try SimpleDateFormat formats
		 }
 
		 // Fallback to SimpleDateFormat for older formats
		 for (String format : FORMATS) {
			 try {
				 SimpleDateFormat sdf = new SimpleDateFormat(format);
				 return sdf.parse(dateStr);
			 } catch (ParseException e) {
				 // Continue to the next format
			 }
		 }
 
		 // If none of the formats work, throw a ParseException
		 throw new ParseException("Date parsing failed for all formats", 0);
	 }


	 /**
	  * Vérifie si le mot de passe respecte les critères de complexité.
	  *
	  * @param password Le mot de passe à vérifier.
	  * @return true si le mot de passe est conforme, sinon false.
	  */
	 public static boolean isPasswordComplex(String password) {
		 if (password == null || password.isEmpty()) {
			 return false;
		 }

		 // Vérifie la longueur minimale
		 if (password.length() < 8) {
			 return false;
		 }

		 // Regex pour valider la complexité
		 String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
		 return password.matches(regex);
	 }

	 /**
	  * Génère un identifiant unique basé sur le timestamp actuel.
	  *
	  * @return Un identifiant unique sous forme de chaîne.
	  */
	 public static int generateRequestId() {
		 // Convertir le timestamp actuel en int en prenant un modulo pour éviter un débordement
		 return (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
	 }


	 /**
	  * Génère un token sécurisé en utilisant l'algorithme SHA-512.
	  *
	  * @param accountAlias Le compte ou alias de l'utilisateur.
	  * @param requestId    L'identifiant de la requête.
	  * @param operatorCode Le code opérateur.
	  * @param password     Le mot de passe sécurisé pour l'opérateur.
	  * @return Un token sécurisé sous forme de chaîne hexadécimale.
	  */
	 public static String generateRequestToken(String accountAlias, String requestId, String operatorCode, String password) {
		 try {
			 // Construire la chaîne à hasher
			 String valueToHash = accountAlias + requestId + operatorCode + password;

			 // Utiliser SHA-512 pour générer le hash
			 MessageDigest digest = MessageDigest.getInstance("SHA-512");
			 byte[] hashBytes = digest.digest(valueToHash.getBytes());

			 // Convertir le hash en une chaîne hexadécimale
			 StringBuilder hexString = new StringBuilder();
			 for (byte b : hashBytes) {
				 hexString.append(String.format("%02x", b));
			 }

			 return hexString.toString();
		 } catch (NoSuchAlgorithmException e) {
			 throw new RuntimeException("Erreur lors de la génération du token : " + e.getMessage());
		 }
	 }
	 public static String hashSha512(String valueToHash) {
		 try {
			 MessageDigest digest = MessageDigest.getInstance("SHA-512");
			 byte[] hash = digest.digest(valueToHash.getBytes(StandardCharsets.UTF_8));
			 StringBuilder hexString = new StringBuilder();
			 for (byte b : hash) {
				 String hex = Integer.toHexString(0xff & b);
				 if (hex.length() == 1) hexString.append('0');
				 hexString.append(hex);
			 }
			 return hexString.toString();
		 } catch (NoSuchAlgorithmException e) {
			 throw new RuntimeException("Erreur lors du hachage SHA-512", e);
		 }
	 }

	 public static boolean matchPassword(String plainPassword, String hashedPassword) {
		 return BCrypt.checkpw(plainPassword, hashedPassword);
	 }
 }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
