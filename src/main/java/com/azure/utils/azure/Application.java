package com.azure.utils.azure;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.azure.utils.azure.storage.M3AzureStorageUtils;
/**
 * Application class
 * @author Sandeep Kumar
 *
 */
@SpringBootApplication
public class Application implements CommandLineRunner  {

	private static final Logger LOGGER = LogManager.getLogger(Application.class);	
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	public void run(String...args) throws Exception {
		
		/**
		 * In case, if you have proxy which need to set in the java program to access
		 * the internet below options can be used
		 * 
		 * Option 1: use the proxy details into the jar file calling as
		 * java -Djava.net.useSystemProxies=true -Dhttp.nonProxyHosts="localhost|127.0.0.1|10.*.*.*|*.foo.com‌​|etc" -Dhttp.proxyHost=10.10.10.10 -Dhttp.proxyPort=8080 -Dhttp.proxyUser=username -Dhttp.proxyPassword=password -jar myJar.jar 
		 * 
		 * Option 2: set the proxy from the code by using below piece of code
		 * System.setProperty("java.net.useSystemProxies", "true");
		 * System.setProperty("http.proxyHost", "");
		 * System.setProperty("http.proxyPort", "");
		 * System.setProperty("http.proxyUser", "");
		 * System.setProperty("http.proxyPassword", "");
		 * System.setProperty("http.nonProxyHosts", "localhost|127.0.0.1|10.*.*.*|*.foo.com‌​|etc");
		 *  
		 */
		
		
		/**
		 * In this utility project there are three utility class used:
		 * 1. M1AzureStorageUtils - This utility class is using the "com.microsoft.azure:azure-storage:8.6.4" library
		 * provided by Microsoft Azure. It provides rich set of APIs to manage Storage account from the Java code
		 * 
		 * It has methods like upload, download, delete, SharedAccessSignature (SAS) url generator
		 * 
		 * 2. M2AzureStorageUtils - This utility class is using the "com.microsoft.azure:azure-storage-blob:11.0.0" library
		 * provided by Microsoft Azure. It provide APIs backed by HTTP protocol and has observed comparatively slow than the
		 * azure-storage APIs.
		 * 
		 * It has methods like upload, download, delete, SharedAccessSignature (SAS) url generator
		 * 
		 * 3. M3AzureStorageUtils - This utility class usage the same azure library as M1AzureStorageUtils but it has been enhanced
		 * to support local file system as well along with AzureStorage.
		 * 
		 * It has methods like upload, download, delete, SharedAccessSignature (SAS) url generator, metadata association with files,
		 * generate SAS token for container, local upload, download, delete
		 * 
		 */
		
		/**
		 * 
		 */
		String connectionString = "connection string";
		
		/**
		 * In case using M3AzureStorageUtils, and want to use local directory instead of azure.
		 * There are three part of this connection string:
		 * LOCAL - Indicate local directory will be used instead of azure
		 * Path - Path of local directory where file will be uploaded/download/deleted in case of local
		 * URL - SAS url to load resource in case of local
		 * 
		 */
		//connectionString = "LOCAL;C:/Sandeep/temp/20201228;http://localhost:8599/context/sas";
		
		/**
		 * It will contain the container name
		 */
		String containerName = "image";

		/**
		 * Path of the resource to be uploaded/deleted/downloaded 
		 */
		String path = "abc/def/xyz.jpg";
		
		/**
		 * Read file data from local machine to upload
		 */
		String file = "C:/Sandeep/skumar.jpg";
		byte[] fileData = M3AzureStorageUtils.readFileFromFilePath(file); 
		
		/**
		 * Operation with M1AzureStorageUtils
		 */
		//M1AzureStorageUtils.uploadFile(connectionString, containerName, path, fileData);
		//M1AzureStorageUtils.downloadFile(connectionString, containerName, path);
		//M1AzureStorageUtils.sasURL(connectionString, containerName, path);
		//M1AzureStorageUtils.delete(connectionString, containerName, path);
		
		
		/**
		 * Operation with M2AzureStorageUtils
		 */
		//M2AzureStorageUtils.uploadFile(connectionString, containerName, path, fileData);
		//M2AzureStorageUtils.downloadFile(connectionString, containerName, path);
		//M2AzureStorageUtils.sasURL(connectionString, containerName, path);
		//M2AzureStorageUtils.delete(connectionString, containerName, path);
		
		/**
		 * Operation with M2AzureStorageUtils
		 */
		//M3AzureStorageUtils.uploadFile(connectionString, containerName, path, fileData);
		//M3AzureStorageUtils.downloadFile(connectionString, containerName, path);
		//M3AzureStorageUtils.sasURL(connectionString, containerName, path);
		//M3AzureStorageUtils.delete(connectionString, containerName, path);
		
		/**
		 * Metadata operations
		 */
		//HashMap<String, String> metadata = new HashMap<String, String>();
		//metadata.put("type", "image");
		//M3AzureStorageUtils.uploadMetadata(connectionString, containerName, path, metadata);
		
		//Map<String, String> metadata1 = M3AzureStorageUtils.downloadMetadata(connectionString, containerName, path);
		//System.out.println(metadata1);


		/**
		 * List files/directories from Azure Storage from given path
		 */
		M3AzureStorageUtils.listFiles(connectionString, containerName, path).stream().forEach(e -> {
			System.out.println(e.getContainer() + ", FileName: " + e.getFilePath());
		});
		
		
		/**
		 * SAS URL with write permission on container
		 */
		//String sasURL = M3AzureStorageUtils.sasDirectoryURL(connectionString, containerName, path);
		//System.out.println("Container SAS URL : " + sasURL);
		
		/**
		 * Using SAS URL doing Blob operation 
		 */
		//String urltest = "sas url to be provided";
		//RestTemplate restTemplate = new RestTemplate();
		//HttpHeaders header = new HttpHeaders();
		//header.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		//header.add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
		//header.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.80 Safari/537.36 Edg/86.0.622.48");
		//HttpEntity<String> httpEntity = new HttpEntity<>(header);
		//ResponseEntity<String> response = restTemplate.exchange(urltest, HttpMethod.GET, httpEntity, String.class);
		//System.out.println(response);
	}

	
	
}
