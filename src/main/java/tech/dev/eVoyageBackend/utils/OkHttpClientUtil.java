package tech.dev.eVoyageBackend.utils;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;

public class OkHttpClientUtil {

	public static OkHttpClient getInsecureOkHttpClient() throws NoSuchAlgorithmException, KeyManagementException {
		// Créer un TrustManager qui accepte tous les certificats
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				// Ne rien faire et accepter tous les certificats des clients
			}

			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				// Ne rien faire et accepter tous les certificats du serveur
			}

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return new X509Certificate[0];
			}
		} };

		// Créer un SSLContext avec le TrustManager personnalisé
		SSLContext sslContext = SSLContext.getInstance("TLS");
		sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

		// Configurer un SSLSocketFactory utilisant le SSLContext personnalisé
		SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

		// Créer un OkHttpClient avec le SSLSocketFactory personnalisé
		return new OkHttpClient.Builder().sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0])
				.hostnameVerifier((hostname, session) -> true).build();
	}

}
