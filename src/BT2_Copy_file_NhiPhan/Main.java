package BT2_Copy_file_NhiPhan;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class Main {
    private static Object Files;

    private static void copyFileUsingJava7Files(File source, File target) throws IOException {
        java.nio.file.Files.copy(source.toPath(),target.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
    private static void copyFileUsingStream(File source, File target) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(target);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer))>0){
                os.write(buffer,0,length);
            }
        }finally {
            is.close();
            os.close();
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter source.txt file:");
        String sourcePath = in.nextLine();
        System.out.println("Enter target.txt file:");
        String targetPath = in.nextLine();
        File sourceFile = new File(sourcePath);
        File targetFile = new File(targetPath);
        try {
            copyFileUsingJava7Files(sourceFile,targetFile);
            System.out.println("copy thành công");
        }catch (IOException e){
            System.out.println("không thể copy file");
            System.out.println(e.getMessage());
        }
    }
}
