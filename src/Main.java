import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        try {
        ArrayList<File> java_files=getFiles("C:\\Users\\Administrator\\Desktop\\paper\\src");
        for (File file:java_files){
            if (file.renameTo(new File("C:\\Users\\Administrator\\Desktop\\paper\\classes\\All\\"+file.getName()))){
                System.out.println(file.getName()+" moved success");
            }else {
                System.out.println(file.getName()+" moved fail");
            }

        }
            } catch (Exception e1) {
            e1.printStackTrace();
        }

    }

    public static ArrayList<File> getFiles(String path) throws Exception{
        ArrayList<File> fileList=new ArrayList<File>();
        File file=new File(path);
        if (file.isDirectory()){
            File []files=file.listFiles();
            for (File fileIndex:files){
                if (fileIndex.isDirectory()){
                    fileList.addAll(getFiles(fileIndex.getPath()));
                }else {
                    fileList.add(fileIndex);
                }
            }
        }
        return fileList;
    }

}
