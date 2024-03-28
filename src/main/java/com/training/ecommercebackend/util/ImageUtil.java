package com.training.ecommercebackend.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class ImageUtil {

    public static String saveImage(MultipartFile imageFile) throws IOException {

        Path filePath;
        String thePath;

        // 1. Get base path for image storage
        Path basePath = Paths.get("product-image");

        // 2. Generate a unique filename
        String fileCode = UUID.randomUUID().toString() + "." + getFileExtension(imageFile);

        try(InputStream inputStream = imageFile.getInputStream()){

            // 3. Create the file path
            //to convert Path object into string path  just do filepath.toSting()
             filePath = basePath.resolve(fileCode);

            // 4. Save the image file
            Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);

        }catch(IOException exc){
            throw new IOException("error saving upload file : "+fileCode,exc);

        }

        // 5. get a file path
        Path path = Paths.get(basePath.toString(),fileCode);
        thePath = path.toString();

        return thePath;

    }


    private static String getFileExtension(MultipartFile imageFile) {
        String contentType = imageFile.getContentType();
        String extension = "";
        if (contentType != null) {
            extension = contentType.split("/")[1];
        }

        return extension;
    }

    public static void deleteFile(String imagePath) throws IOException {

        Path path = Paths.get(imagePath);

        if(Files.exists(path)){
            Files.delete(path);
        }else {
           throw new IOException(" Image not found");
        }

    }

}




