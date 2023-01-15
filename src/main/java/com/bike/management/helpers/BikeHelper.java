package com.bike.management.helpers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import com.bike.management.configurations.GlobalConstants;

import ch.qos.logback.core.Context;
@Component
public class BikeHelper {

	@Autowired
	ResourceLoader resourceLoader;
	
	public static void saveProductImage(MultipartFile file,int bikeId,String fileName) throws IOException{
		
		System.out.println(" FileName is " + fileName);
	  Path uploadPath = Paths.get(GlobalConstants.PUBLIC_DIR);
        
       if (!Files.exists(uploadPath)) {
           Files.createDirectories(uploadPath);
       }
        
       try (InputStream inputStream = file.getInputStream()) {
           Path filePath = uploadPath.resolve(bikeId+ "_" +fileName);
           Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
       } catch (IOException ioe) {        
           throw new IOException("Could not save image file: " + fileName, ioe);
       }      
		     System.out.println(fileName + " uploaded....");
		
		
	}
}
