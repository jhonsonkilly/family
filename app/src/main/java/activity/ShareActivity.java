package activity;

import android.Manifest;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.ShareBoardConfig;

import config.LoginHelper;
import family.live.R;
import model.UserModel;

/**
 * Created by mac on 18/1/27.
 */

public class ShareActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_share);
        findViewById(R.id.share_button).setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.share_button:


                ShareAction action = new ShareAction(ShareActivity.this);

                UMWeb web = new UMWeb("http://tz.tensdo.com/register/index?cid=" + UserModel.getUid());
                web.setTitle("都市民工");//标题
                web.setThumb(new UMImage(ShareActivity.this, R.drawable.ic_launcher));  //缩略图
                web.setDescription("欢迎加入都市民工家装服务平台！美好生活从此添加新色彩！");//描述
                action.setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                        .withMedia(web)
                        .setCallback(umShareListener)
                        .share();
                action.open();



                break;
        }
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);
            Toast.makeText(ShareActivity.this, "分享成功", Toast.LENGTH_LONG).show();


        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(ShareActivity.this, "分享失败", Toast.LENGTH_LONG).show();

        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(ShareActivity.this, "分享取消", Toast.LENGTH_LONG).show();
        }
    };
}
