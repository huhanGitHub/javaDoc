import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.Doclet;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.RootDoc;

import java.io.*;

public class Train_process extends Doclet {
    public static boolean start(RootDoc root) {
        ClassDoc[] classes = root.classes();
        File api = new File("./API.txt");
        File comment = new File("./Comment.txt");
        if (!api.exists()) {
            try {
                api.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!comment.exists()) {
            try {
                api.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            BufferedWriter bw_api = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(api.getPath(),true),"UTF-8"));
            BufferedWriter bw_comment = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(comment.getPath(),true),"UTF-8"));

            for (ClassDoc cls : classes) {
                //System.out.println(cls);
                MethodDoc[] methods = cls.methods();
                //System.out.println(cls.commentText());
                for (MethodDoc meth : methods) {
                    String comment_ = meth.commentText();
                    if (!comment_.equals("")){
                        String api_ = meth.toString();
                        api_ = api_.substring(0, api_.lastIndexOf("("));
                        api_ = getSubStr(api_);
                        System.out.println(api_);
                        comment_ = comment_.replace("\n", "");
                        if (comment_.indexOf(".")>0){
                            comment_ = comment_.substring(0, comment_.indexOf("."));
                        }
                        System.out.println(comment_);
                        bw_api.write(api_ + "\n");
                        bw_comment.write(comment_ + "\n");
                    }
                }
            }
            bw_api.close();
            bw_comment.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


        return true;
    }

    private static String getSubStr(String str) {
            String []ss=str.split("\\.");
            if (ss.length>=3){
                String tmp = str.substring(0, str.lastIndexOf("."));
                String sub = tmp.substring(0, tmp.lastIndexOf("."));
                sub = str.substring(sub.length() + 1);
                return sub;
            }
            return str;

    }
}
