package gmsproduction.com.gmstemplate3.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import gmsproduction.com.gmstemplate3.R;

public class AboutUsActivity extends BaseActivity {
    ImageView imgAboutUs;
    TextView txtVisionTitle, txtVisionDesc, txtMissionTitle, txtMissionDesc;
    int language ;

    String arVisionTitle = "رؤيتنا";
    String enVisionTitle = "About Us";

    String arVisionDesc = " لذلك نعمل في مجالات متعددة منها :-\\r\\n\\r\\n-\\tتطبيقات الهاتف و مواقع إلكترونية.\\r\\n-\\tانتاج و تطوير و تسويق المحتوى الرقمي على العديد من مواقع بيع المحتوى الرقمي على الإنترنت.\\r\\n-\\tتقوم الشركة بالتعامل مع شركات الإتصالات لتقديم المحتوى الرقمي بكافة الصور الممكنة.\\r\\n-\\tخدمات القيمة المضافة و DCB \\r\\n-\\tبالاضافة إلى دور GMS Group   فى تطوير تكنولوجيا التعليم وتقديم خدمات بافضل المنهجيات للمعرفة والابتكار من اجل خلق جيل جديد من المتعلمين تعليما ابداعي عن طريق توفير حلول ذكية من خلال نظام متكامل مع التطبيقات الهاتف و التعليم عن بعد مع إقامة منشئات تعليمية و عمل دورات تدريبية متقدمة ....\n" +
            "        ";
    String enVisionDesc ="We are a Foundation that Invest in Information Technology , Production , Software , Digital Content and Online Business.";

    String arMissionTitle = "مهمتنا";
    String enMissionTitle = "Our Mission";

    String arMissionDesc = " مجموعة من الشركات مجالها الاستثمار فى تكنولوجيا المعلومات , الانتاج , و تطبيقات الهاتف و المحتوى الرقمى\\r\\nبالإضافة إلى التجارة الإلكترونية و برمجيات و شبكات و تسويق خدمات الأنترنت\\r\\nفهدف مجموعتنا بناء وتوفير مستوى عالي من خدمات المحتوى الرقمي      ";
    String enMissionDesc = "Build and Deliver High Level of Digital Content and Services So, We Merge Our Experiences with Technology" ;

    String visionTitle,VisionDesc,MissionTitle,MissionDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        Initializing();
        language = getIdLANG();

        if(language==1){
            setTitle("عن الشركة");
            visionTitle = arVisionTitle;
            VisionDesc = arVisionDesc;
            MissionTitle = arMissionTitle;
            MissionDesc = arMissionDesc;

        }else{
            setTitle("About Us");
            visionTitle = enVisionTitle;
            VisionDesc = enVisionDesc;
            MissionTitle = enMissionTitle;
            MissionDesc = enMissionDesc;
        }



        Picasso.with(this).load(R.drawable.gms_group1).fit().centerInside().into(imgAboutUs);

        txtVisionTitle.setText(visionTitle);
        txtVisionDesc.setText(VisionDesc);
        txtMissionTitle.setText(MissionTitle);
        txtMissionDesc.setText(MissionDesc);


    }

    private void Initializing() {
        imgAboutUs = findViewById(R.id.aboutUSimg);
        txtVisionTitle = findViewById(R.id.aboutUSVisionTitle);
        txtVisionDesc = findViewById(R.id.aboutUSVisionDesc);
        txtMissionTitle = findViewById(R.id.aboutUSMissionTitle);
        txtMissionDesc = findViewById(R.id.aboutUSMissionDesc);
    }
}
