package mansha99.com.restclient.utils;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;
public class AppHelper {

    public static void LogCat(String Message) {

        Log.e("ms", Message);
    }

    public static void LogStack(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        Log.e("ms", sw.toString());
    }

    public static void LogCat(Throwable Message) {

        Log.e("ms", " Throwable " + Message.getMessage());
    }

    public static void LogCat(Throwable Message, String line) {

        Log.e("ms", line + ", Throwable " + Message.getMessage() + Message.toString());
    }

    private static ProgressDialog mDialog;
    private static Snackbar snackbar;



    public static void showDialogSimple(Context mContext, String message) {
        mDialog = new ProgressDialog(mContext);
        mDialog.setMessage(message);
        mDialog.setIndeterminate(true);
        mDialog.show();
    }
    public static void showDialog(Context mContext, String message) {
        mDialog = new ProgressDialog(mContext);
        mDialog.setMessage(message);
        mDialog.setIndeterminate(true);
        mDialog.setCancelable(true);
        mDialog.setCancelable(true);
        mDialog.show();
    }
    public static void hideDialog() {
        if (mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

}
