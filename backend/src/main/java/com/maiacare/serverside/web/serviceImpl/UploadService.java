package com.maiacare.serverside.web.serviceImpl;

import com.maiacare.serverside.Utils.FilenameUtils;
import com.maiacare.serverside.web.service.IuploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Service
public class UploadService implements IuploadService {
    @Value("${filePath}")
    String realPath;

    @Override
    public String uploadPicture(MultipartFile uploadFile, HttpServletRequest request) {
//        String realPath="\\root\\photo";
        File file = new File(realPath);


        System.out.println(file);
        System.out.println(file.getAbsolutePath());

        // verify readpath is directory
        if (!file.isDirectory()) {
            file.mkdirs();
        }
        // upload file
        String fileName = FilenameUtils.getFileName(uploadFile.getOriginalFilename());

        //Build the real file path
        File newFile = new File(file.getAbsolutePath() + File.separator + fileName);
        // Export the file to the specified path. If the file name is repeated, the previous file
        // will be overwritten. Here is to upload the file to "Absolute path".
        try {
            uploadFile.transferTo(newFile);


//            String filePath ="http://127.0.0.1:8091/image/"+fileName;

            String filePath = request.getScheme() + "://" + request.getServerName() + ":"
                    + request.getServerPort() + "/image/" + fileName;

            return filePath;
        } catch (Exception e) {

        }
        return "no photo";
    }
}
