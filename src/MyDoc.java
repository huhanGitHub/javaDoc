import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.Doclet;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.RootDoc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MyDoc extends Doclet {
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
            FileWriter fw_api = new FileWriter(api, true);
            FileWriter fw_comment = new FileWriter(comment, true);

            for (ClassDoc cls : classes) {
                //System.out.println(cls);
                MethodDoc[] methods = cls.methods();
                //System.out.println(cls.commentText());
                for (MethodDoc meth : methods) {
                    String comment_ = meth.commentText();
                    if (!comment_.equals("")){
                        System.out.println(meth);
                        String api_ = meth.toString();
                        api_ = api_.substring(0, api_.lastIndexOf("("));
                        api_ = getSubStr(api_);
                        System.out.println(api_);
                        comment_ = comment_.replace("\n", "");
                        if (comment_.indexOf(".")>0){
                            comment_ = comment_.substring(0, comment_.indexOf("."));
                        }
                        System.out.println(meth.commentText());
                        System.out.println(comment_);
                        fw_api.write(api_ + "\n");
                        fw_comment.write(comment_ + "\n");
                    }
                }
            }
            fw_api.close();
            fw_comment.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


        return true;
    }

    private static String getSubStr(String str) {
            System.out.println(str);
            String []ss=str.split("\\.");
            System.out.println(ss.length);
            if (ss.length>=3){
                String tmp = str.substring(0, str.lastIndexOf("."));
                String sub = tmp.substring(0, tmp.lastIndexOf("."));
                sub = str.substring(sub.length() + 1);
                return sub;
            }

            return str;

    }
}
