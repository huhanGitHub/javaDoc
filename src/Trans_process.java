import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.Doclet;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.RootDoc;

import java.io.*;

public class Trans_process extends Doclet{
    public static boolean start(RootDoc root) {
        ClassDoc[] classes = root.classes();
        File api = new File("./API.txt");
        if (!api.exists()) {
            try {
                api.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            BufferedWriter bw_api = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(api.getPath(),true),"UTF-8"));
            for (ClassDoc cls : classes) {
                //System.out.println(cls);
                MethodDoc[] methods = cls.methods();
                //System.out.println(cls.commentText());
                for (MethodDoc meth : methods) {
                        System.out.println(meth);
                        String api_ = meth.toString();
                        api_ = api_.substring(0, api_.lastIndexOf("("));
                        api_ = getSubStr(api_);
                        System.out.println(api_);
                        bw_api.write(api_ + "\n");
                }
            }
            bw_api.close();
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
