package com.maiacare.serverside.Utils;

import java.util.UUID;

public class FilenameUtils {

    public static String getSuffix(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }



    public static  String getFileName(String originalFilename){

        return generateFileName()+getSuffix(originalFilename);
    }


    public static String generateFileName(){
       return  UUID.randomUUID().toString().replace("-","");
    }



}
