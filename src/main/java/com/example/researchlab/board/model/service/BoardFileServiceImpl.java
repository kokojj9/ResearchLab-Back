package com.example.researchlab.board.model.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class BoardFileServiceImpl implements BoardFileService{

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public String saveFile(HttpSession session, MultipartFile upfile) throws IOException {
        String originName = upfile.getOriginalFilename();

        String ext = originName.substring(originName.lastIndexOf("."));
        String currentTime = new SimpleDateFormat("yyyyMMdd").format(new Date());
        int random = (int) (Math.random() * 90000) + 10000;
        String changeName = currentTime + "_" + random + ext;

        String savePath = uploadDir;
        File folder = new File(savePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File dest = new File(savePath, changeName);
        upfile.transferTo(dest);

        return "upfiles/boardImages/" + changeName;
    }








}
