package springMvcPractice.profile;
  /*
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@Controller
public class PictureUploadController {
    public static final Resource PICTURES_DIR = new FileSystemResource("./pictures");
    @RequestMapping("upload")
    public String uploadPage() {
        return "profile/uploadPage";
    }
 @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String onUpload(MultipartFile file) throws IOException {
        System.out.println("file :"+file.getOriginalFilename());
        String filename = file.getOriginalFilename();
        File tempFile = File.createTempFile("pic",
                getFileExtension(filename), PICTURES_DIR.getFile());
        try (InputStream in = file.getInputStream();
             OutputStream out = new FileOutputStream(tempFile)) {
            IOUtils.copy(in, out);
        }
        return "profile/uploadPage";
    }
    private static String getFileExtension(String name) {
        return name.substring(name.lastIndexOf("."));
    }
}*/
  /*
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;

@Controller
public class PictureUploadController {
    public static final Resource PICTURES_DIR = new
            FileSystemResource("./pictures");
    @RequestMapping("upload")
    public String uploadPage() {
        return "profile/uploadPage";
    }
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String onUpload(MultipartFile file, RedirectAttributes
            redirectAttrs) throws IOException {
        if (file.isEmpty() || !isImage(file)) {
            redirectAttrs.addFlashAttribute("error", "Incorrect file. Please upload a picture.");
            return "redirect:/upload";
        }
        copyFileToPictures(file);
        return "profile/uploadPage";
    }
    private Resource copyFileToPictures(MultipartFile file) throws
            IOException {
        String fileExtension = getFileExtension(file.
                getOriginalFilename());
        File tempFile = File.createTempFile("pic", fileExtension,
                PICTURES_DIR.getFile());
        try (InputStream in = file.getInputStream();
             OutputStream out = new FileOutputStream(tempFile)) {
            IOUtils.copy(in, out);
        }
        return new FileSystemResource(tempFile);
    }
    private boolean isImage(MultipartFile file) {
        return file.getContentType().startsWith("image");
    }
    private static String getFileExtension(String name) {
        return name.substring(name.lastIndexOf("."));
    }
    @RequestMapping(value = "/uploadedPicture")
    public void getUploadedPicture(HttpServletResponse response) throws
            IOException {
        ClassPathResource classPathResource = new ClassPathResource("D:\\idea\\springMvc\\springMvcPracticesrc/main/resources/images/anonymous.png");
                response.setHeader("Content-Type", URLConnection.guessContentTypeFromName(classPathResource.getFilename()));
                System.out.println("picture image: "+classPathResource.getFilename());
        IOUtils.copy(classPathResource.getInputStream(), response.
                getOutputStream());
    }
}*/


import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import springMvcPractice.config.PictureUploadProperties;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
@Controller
public class PictureUploadController {
    private final Resource picturesDir;
    private final Resource anonymousPicture;
    @Autowired
    public PictureUploadController(PictureUploadProperties uploadProperties) {
        picturesDir = uploadProperties.getUploadPath();
        anonymousPicture = uploadProperties.getAnonymousPicture();
    }
//    @RequestMapping(value = "/uploadedPicture")
//    public void getUploadedPicture(HttpServletResponse response)
//            throws IOException {
//        response.setHeader("Content-Type", URLConnection.guessContentTypeFromName(anonymousPicture.getFilename()));
//        IOUtils.copy(anonymousPicture.getInputStream(), response.
//                getOutputStream());
//    }



    public static final Resource PICTURES_DIR = new
            FileSystemResource("./pictures");
    @RequestMapping("upload")
    public String uploadPage() {
        return "profile/uploadPage";
    }
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String onUpload(MultipartFile file, RedirectAttributes
            redirectAttrs) throws IOException {
        if (file.isEmpty() || !isImage(file)) {
            redirectAttrs.addFlashAttribute("error", "Incorrect file. Please upload a picture.");
            return "redirect:/upload";
        }
        copyFileToPictures(file);
        return "profile/uploadPage";
    }
    private Resource copyFileToPictures(MultipartFile file) throws
            IOException {
        String fileExtension = getFileExtension(file.
                getOriginalFilename());
        File tempFile = File.createTempFile("pic", fileExtension,
                PICTURES_DIR.getFile());
        try (InputStream in = file.getInputStream();
             OutputStream out = new FileOutputStream(tempFile)) {
            IOUtils.copy(in, out);
        }
        return new FileSystemResource(tempFile);
    }
    private boolean isImage(MultipartFile file) {
        return file.getContentType().startsWith("image");
    }
    private static String getFileExtension(String name) {
        return name.substring(name.lastIndexOf("."));
    }
    @RequestMapping(value = "/uploadedPicture")
    public void getUploadedPicture(HttpServletResponse response) throws
            IOException {
        ClassPathResource classPathResource = new ClassPathResource("classpath:/images/anonymous.png");
        response.setHeader("Content-Type", URLConnection.guessContentTypeFromName(classPathResource.getFilename()));
        System.out.println("picture image: "+classPathResource.getFilename());
        IOUtils.copy(classPathResource.getInputStream(), response.
                getOutputStream());
    }

    @ExceptionHandler(IOException.class)
    public ModelAndView handleIOException(IOException exception) {
        ModelAndView modelAndView = new ModelAndView("profile/uploadPage");
                modelAndView.addObject("error", exception.getMessage());
        return modelAndView;
    }
// The rest of the code remains the same
}