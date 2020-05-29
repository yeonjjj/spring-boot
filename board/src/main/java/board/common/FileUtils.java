package board.common;

import board.board.dto.BoardFileDto;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component  //해당 클래스를 스프링의 빈으로 등록
public class FileUtils {

    public List<BoardFileDto> parseFileInfo(int boardIdx, MultipartHttpServletRequest
            multipartHttpServletRequest) throws Exception{
        if(ObjectUtils.isEmpty(multipartHttpServletRequest)){
            return null;
        }

        List<BoardFileDto> fileList = new ArrayList<>();
        //파일이 업로드 될 때마다 images 폴더 밑에 yyyyMMdd 형식으로 폴더 생성
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
        ZonedDateTime current = ZonedDateTime.now();    //오늘 날짜 확인(JDK 1.8부터)
        String path = "images/"+current.format(format);
        File file = new File(path);
        //해당 폴더가 없을 경우에만 생성
        if(file.exists()==false){
            file.mkdirs();
        }

        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();

        String newFileName, originalFileExtension, contentType;

        while(iterator.hasNext()){
            List<MultipartFile> list = multipartHttpServletRequest.getFiles(iterator.next());
            for(MultipartFile multipartFile : list){
                if(multipartFile.isEmpty()==false){
                    //파일 형식을 확인하여 확장자 지정
                    //실제 개발 시에는 nio 패키지(JDK 1.7 이상)나 Apache Tika 라이브러리 이용하여 파일 형식 확인
                    contentType = multipartFile.getContentType();
                    if(ObjectUtils.isEmpty(contentType)){
                        break;
                    }
                    else{
                        if(contentType.contains("image/jpeg")){
                            originalFileExtension=".jpg";
                        }
                        else if(contentType.contains("image/png")){
                            originalFileExtension=".png";
                        }
                        else if(contentType.contains("image/gif")){
                            originalFileExtension=".gif";
                        }
                        else{
                            break;
                        }
                    }

                    //중복되지 않도록 파일이 업로드 된 나노초를 이용해 서버에 저장될 파일 이름 생성
                    newFileName = Long.toString(System.nanoTime())+originalFileExtension;
                    //DB에 저장할 파일 정보 기록
                    BoardFileDto boardFile = new BoardFileDto();
                    boardFile.setBoardIdx(boardIdx);
                    boardFile.setFileSize(multipartFile.getSize());
                    boardFile.setOriginalFileName(multipartFile.getOriginalFilename());
                    boardFile.setStoredFilePath(path+"/"+newFileName);
                    fileList.add(boardFile);

                    //업로드 된 파일을 새로운 이름으로 바꾸어 지정된 경로에 저장
                    file = new File(path+"/"+newFileName);
                    multipartFile.transferTo(file);
                }
            }
        }
        return fileList;
    }

}
