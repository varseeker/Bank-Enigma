package com.pascal.bankenigma.service;

import com.pascal.bankenigma.service.UserData.UserDataService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

@Service
public class StorageService {

    @Autowired
    UserDataService userDataService;

    public void saveFileTo(MultipartFile multipartFile, String id) throws IOException {

        userDataService.getDataById(id);

//        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
//        String destination = "/home/dell/Academy/Pascal-7/Shopaa/upload-img/"+id+"."+extension;

        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        File makeDir = new File("/home/dell/Academy/Pascal-7/BankEnigma/upload-img/"+id);
        makeDir.mkdir();
        String destination = "/home/dell/Academy/Pascal-7/BankEnigma/upload-img/"+id+"/profile_picture."+extension;

        File fileTemp = new File(destination);
        multipartFile.transferTo(fileTemp);
    }
}
