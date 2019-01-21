package teotw.com.shareapplication.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;

import teotw.com.shareapplication.R;
import teotw.com.shareapplication.ui.dialog.ReleaseDialog;

public class MainActivity extends AppCompatActivity {
    public static int width, height;

    private ReleaseDialog releaseDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics displayMetrics = getDisplayMetrics();
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;

        findViewById(R.id.hello).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogss();
                    }
                });
    }

    private void showDialogss() {
        releaseDialog = new ReleaseDialog(MainActivity.this);
        ReleaseDialog.OnClickPointListener2 onClickPointListener = new ReleaseDialog.OnClickPointListener2() {

            @Override
            public void OnClickPoint(int clickPoint) {
                switch (clickPoint) {
                    case 1:
                        Toast.makeText(MainActivity.this, "wx",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(MainActivity.this, "qq",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(MainActivity.this, "circle",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(MainActivity.this, "qzone",
                                Toast.LENGTH_SHORT).show();
                        break;
                    default:
//                        releaseDialog.dismiss();
                        break;
                }
            }
        };
        releaseDialog.setOnClickPointListener(onClickPointListener);
        releaseDialog.show();
    }

    private DisplayMetrics getDisplayMetrics() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }
}
