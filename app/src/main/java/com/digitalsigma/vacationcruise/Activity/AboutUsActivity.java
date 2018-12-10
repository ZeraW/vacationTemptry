package com.digitalsigma.vacationcruise.Activity;

import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import com.digitalsigma.vacationcruise.R;

public class AboutUsActivity extends BaseActivity {
    ImageView imgAboutUs;
    TextView txtAboutUs, txtAboutUsDesc, txtVisionTitle, txtVisionDesc, txtMissionTitle, txtMissionDesc;
    int language;


    String enAboutUs = "Digital Sigma ";
    String enAboutUsDesc = "Provides the complete technology and marketing for any startup and existing company with a multi-product. Get the best tools for building your online business, digitalsigma.io comes with an outline to let you enjoy listing variants of each product.";

    String arAboutUs = "دچيتال سيجما";
    String arAboutUsDesc = "توفر التكنولوجيا الكاملة والتسويق لأي شركة ناشئة وشركة موجودة مع منتجات متعددة. لتحصل على أفضل الأدوات لبناء أعمالك على الإنترنت ، يأتي دجيتال سيجما مع مخطط لتمكنك من التمتع بخدمات ذات جودة عالية .";


    String arVisionTitle = "رؤيتنا";
    String enVisionTitle = "Our Vision";

    String arVisionDesc =
            "<font color=\"#000\"><strong> ۱| الفعالية </strong></font> <br>" +
            "نحن نهدف إلى تحقيق أهدافنا بأكثر الطرق فعالية.<br><br>" +
                    "<font color=\"#000\"><strong>  ۲| الأحترام </strong></font> <br>" +
            "نتعامل مع عملائنا وزملائنا وشركائنا وموردينا باحترام.<br><br>" +
                    "<font color=\"#000\"><strong> ۳| الجودة </strong></font> <br>" +
            "نحن نسعى الجودة على الكمية. نحن فخورون بعملنا.<br><br>" +
                    "<font color=\"#000\"><strong> ٤| التطور </strong></font> <br>" +
            "نحن مصممون على التعلم والتطوير باستمرار. نقوم بتحليل أنفسنا باستمرار ، ومنظمتنا والعالم من حولنا.<br><br>";


    String enVisionDesc = "<font color=\"#000\"><strong> 01 | Efficiency </strong></font> <br>" +
            "We aim to achieve our goals in the most efficient way.<br><br>" +
            "<font color=\"#000\"><strong> 02 | Respect </strong></font> <br>" +
            "We treat our clients, colleagues, partners, and suppliers with respect.<br><br>" +
            "<font color=\"#000\"><strong> 03 | Quality </strong></font> <br>" +
            "We seek quality over quantity. We are proud of our work.<br><br>" +
            "<font color=\"#000\"><strong> 04 | Evolve </strong></font><br>" +
            "We are determined to learn and develop constantly. we perpetually analyze and improve ourselves, our organization and the world around us.<br><br>";

    String arMissionTitle = "مهمتنا";
    String enMissionTitle = "Our Mission";

    String arMissionDesc = "لتقديم الجودة في كل مرة والشعور بالفخر بعملنا. نحن نسعى دائمًا لتحسين أنفسنا ولدينا الشجاعة لتجربة أشياء جديدة. فنحن نضيف قيمة من خلال تحويل أفكارك إلى حلول فعالة على الويب والجوّال. ";
    String enMissionDesc = "To deliver quality every time and to feel proud of our work. We always strive to improve ourselves and have the courage to try new things. We add value by turning your ideas into efficient web and mobile solutions.";

    String aboutUs, aboutUsDesc, visionTitle, VisionDesc, MissionTitle, MissionDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        Initializing();
        language = getIdLANG();

        if (language == 1) {
            setTitle("عن الشركة");
            visionTitle = arVisionTitle;
            VisionDesc = arVisionDesc;
            MissionTitle = arMissionTitle;
            MissionDesc = arMissionDesc;
            aboutUs = arAboutUs;
            aboutUsDesc = arAboutUsDesc;

        } else {
            setTitle("About Us");
            visionTitle = enVisionTitle;
            VisionDesc = enVisionDesc;
            MissionTitle = enMissionTitle;
            MissionDesc = enMissionDesc;
            aboutUs = enAboutUs;
            aboutUsDesc = enAboutUsDesc;
        }


        Picasso.with(this).load(R.drawable.logo2).fit().centerInside().into(imgAboutUs);

        txtAboutUs.setText(aboutUs);
        txtAboutUsDesc.setText(aboutUsDesc);
        txtVisionTitle.setText(visionTitle);
        txtVisionDesc.setText(Html.fromHtml(VisionDesc));
        txtMissionTitle.setText(MissionTitle);
        txtMissionDesc.setText(MissionDesc);



    }

    private void Initializing() {
        imgAboutUs = findViewById(R.id.aboutUSimg);
        txtVisionTitle = findViewById(R.id.aboutUSVisionTitle);
        txtVisionDesc = findViewById(R.id.aboutUSVisionDesc);
        txtMissionTitle = findViewById(R.id.aboutUSMissionTitle);
        txtMissionDesc = findViewById(R.id.aboutUSMissionDesc);
        txtAboutUs = findViewById(R.id.aboutUsGeneralTitle);
        txtAboutUsDesc = findViewById(R.id.aboutUsGeneralTitleDesc);
    }
}
