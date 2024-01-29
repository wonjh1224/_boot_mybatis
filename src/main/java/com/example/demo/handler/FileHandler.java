package com.example.demo.handler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.FileVO;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;



@Component
public class FileHandler {

	private final String UP_DIR = "C:\\myProject\\java\\fileUpload";
	
	public List<FileVO> uploadFiles(MultipartFile[] files){
		List<FileVO> flist = new ArrayList<>();
		
		LocalDate date = LocalDate.now();
		
		String today = date.toString();

		today = today.replace("-", File.separator);
		
		//C:\myProject\java\fileUpload\2024\01\29
		File folders = new File(UP_DIR, today);
		
		//실제 폴저를 생성하는 명령어 mkdir / mkdirs(여러 폴더 한번에 생성)
		if(!folders.exists()) { //폴더가 없다면
			folders.mkdirs();
		}
		
		// ------ 폴더 생성 완료 ------
		
		//FileVO 생성
		for(MultipartFile file : files) {
			FileVO fvo = new FileVO();
			fvo.setSaveDir(today);
			fvo.setFileSize(file.getSize());
			
			String originalFileName = file.getOriginalFilename();
			String onlyFileName = originalFileName.substring(
					originalFileName.lastIndexOf(File.separator)+1);
			fvo.setFileName(originalFileName);
			
			UUID uuid = UUID.randomUUID();
			fvo.setUuid(uuid.toString());
			
			//----------fvo 설정 마무리
			
			//디스크에 저장할 파일 생성
			String fullFileName = uuid.toString()+"_"+onlyFileName;
			//C:\myProject\java\fileUpload\2024\01\29\\uuid_name
			File storFile = new File(folders, fullFileName);
			
			//저장
			try {
				//원본파일
				file.transferTo(storFile);
				//file-type 이미지 파일이면 1, 아니면 0
				if(isImageFile(storFile)) {
					fvo.setFileType(1);
					File thumbnail = new File(folders, uuid.toString()+"_th_"+onlyFileName);
					//이미지만 썸네일이 가능.
					Thumbnails.of(storFile).size(75, 75).toFile(thumbnail);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			//for문 안
			flist.add(fvo);
		}
		
		
		
		return flist;
	}
	
	
	private boolean isImageFile(File file) throws IOException{
		String mimeType = new Tika().detect(file);
		return mimeType.startsWith("image") ? true : false;
	}
	
		
}
