package com.example.wanusa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

import com.example.wanusa.QuizContract.QuestionsTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Azhar Rivaldi on 10/07/2019.
 */

public class QuizDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "quizzes.db";
    private static final int DB_VERSION = 8;

    public static final String CATEGORY_MTK = "matematika";
    public static final String CATEGORY_IPA = "ipa";
    public static final String CATEGORY_IPS = "ips";
    public static final String CATEGORY_AGAMA = "agama";
    public static final String CATEGORY_OLAHRAGA = "olahraga";
    public static final String CATEGORY_PROGRAM = "pemrograman";

    private final String CREATE_TABLE_QUERY = "CREATE TABLE " + QuizContract.QuestionsTable.TABLE_NAME +
            "(" +
            QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            QuestionsTable.COLUMN_QUESTION + " TEXT, " +
            QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
            QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
            QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
            QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
            QuestionsTable.COLUMN_ANSWER + " TEXT, " +
            QuestionsTable.COLUMN_CATEGORY + " TEXT" +
            ")";

    private final String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME;

    private SQLiteDatabase db;
    private List<Question> mQuestionList;

    private Bundle categoryIntentBundle;

    public QuizDBHelper(Context context, Bundle bundle) {
        super(context, DB_NAME, null, DB_VERSION);
        this.categoryIntentBundle = bundle;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        db.execSQL(CREATE_TABLE_QUERY);

        setUpQuestions();
        insertQuestions();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_QUERY);
        onCreate(db);
    }

    private void setUpQuestions() {
        mQuestionList = new ArrayList<>();

        //questions for category Tari
        mQuestionList.add(new Question("Unsur utama dalam sebuah seni tari adalah....", "bicara", "gerak", "duduk", "pakaian", "gerak", CATEGORY_AGAMA));
        mQuestionList.add(new Question("Berikut ini yang termasuk gerak dasar suatu tari, kecuali....", "gerak kaki", "gerak lambung", "gerak leher", "gerak perut", "gerak leher", CATEGORY_AGAMA));
        mQuestionList.add(new Question("Kreasi seni yang memiliki gerakan tari yang lincah dan gesit tapi kurang ekspresif, merupakan seni tari dari daerah....", "Jawa Timur", "sumatra", "jawa tengah", "sunda", "Jawa Timur", CATEGORY_AGAMA));
        mQuestionList.add(new Question("Gerakan yang menonjol seni tari dari Timur adalah....", "kepala", "kaki", "perut", "tangan", "kepala", CATEGORY_AGAMA));
        mQuestionList.add(new Question("Gerakan yang mengesankan bentuk badan membesar adalah....", "gerakan badan", "gerakan bahu", "gerakan tangan", "gerakan lambung", "gerakan lambung", CATEGORY_AGAMA));

        //questions for category Budaya
        mQuestionList.add(new Question("salah satu cara menghargai keberagaman agama yang ada di Indonesia adalah …", "bermain dengan teman yang seagama saja", "bermain dengan teman yang berasal dari suku yang sama", "bermain dengan teman yang berbeda agama saja", "bermain dengan teman tanpa membeda-bedakan agama", "bermain dengan teman tanpa membeda-bedakan agama", CATEGORY_IPS));
        mQuestionList.add(new Question("Contoh sikap toleransi dalam menghadapi perbedaan yaitu …", "Hanya mempelajari budaya sendiri", "Bersikap tinggi hati terhadap budaya lain", "Menghargai pendapat tanpa memandang perbedaan suku", "mendengarkan dengan dengan suku yang sama", "Menghargai pendapat tanpa memandang perbedaan suku", CATEGORY_IPS));
        mQuestionList.add(new Question("Kebudayaan yang dimiliki suatu masyarakat di suatu tempat berbeda dengan kebudayaan yang dimiliki oleh masyarakat ditempat lain. hal ini menyebabkan timbulnya …", "perbedaan kebudayaan", "keunikan kebudayaan", "keanekaragaman kebudayaan", "lingkungan", "keanekaragaman kebudayaan", CATEGORY_IPS));
        mQuestionList.add(new Question("Walau memiliki banyak keberagaman dan perbedaan, namun bangsa Indonesia tetap bersatu seperti dalam semboyan …", "Bhineka Tunggal Ika", "Tut Wuri Handayani", "Ing Ngarsa Sung Tuladha", "Negara kertagama", "Bhineka Tunggal Ika", CATEGORY_IPS));
        mQuestionList.add(new Question("Di era globalisasi masyarakat dapat membeli berbagai barang yang beraneka ragam sesuai dengan tingkatan ekonominya. Tingkat ekonomi masyarakat antara lain dipengaruhi oleh faktor-faktor berikut ini, kecuali.... ", "Jenis kelamin yang dimiliki", "Keterampilan yang dimiliki", "Tingkatan pendidikan", "Sikap dalam bekerja", "Jenis kelamin yang dimiliki", CATEGORY_IPS));

        //questions for category sejarah
        mQuestionList.add(new Question("Makna harfiah sejarah sebagai sesuatu yang telah terjadi, terdapat pada kata....", "syajarotun", "history", "kronik", "geschicht", "geschicht", CATEGORY_IPA));
        mQuestionList.add(new Question("Sejarah berasal dari bahasa Arab, yaitu syajarotun yang berarti....", "pohon/kayu", "Batang", "Daun", "Akar", "pohon/kayu", CATEGORY_IPA));
        mQuestionList.add(new Question("Sejarah mempunyai arti “terjadi” dari kata “geschieden” yang berasal dari bahasa....", "Swiss", "Spanyol", "Italia", "Belanda", "Belanda", CATEGORY_IPA));
        mQuestionList.add(new Question("Sejarah adalah catatan tentang masyarakat umat manusia atau peradaban dunia, tentang perubahan-perubahan yang terjadi pada watak masyarakat, dikemukakan oleh...", "Nugroho Nutosusanto", "Patick Gardiner", "Ibnu Khaldun", "Soekarno", "Ibnu Khaldun", CATEGORY_IPA));
        mQuestionList.add(new Question("Sejarah sebagai ilmu karena memiliki ciri-ciri berikut, kecuali....", "metode", "rasional", "sistematis", "subjektif", "subjektif", CATEGORY_IPA));

        //questions for category Cerita Rakyat
        mQuestionList.add(new Question("Hikayat yang hanya berunsur Islam dan berasal dari tradisi sastra Arab-Persia. Pernyataan tersebut merupakan pengertian dari..", "Hikayat berunsur Hindu-Islam", "HIkayat berunsur Hindu", "Hikayat Pattani", "Hikayat Malim Dewa", "Hikayat Malim Dewa", CATEGORY_PROGRAM));
        mQuestionList.add(new Question("Jika dilihat berdasarkan isinya, dalam sastra Melayu (hikayat) dapat dikelompokan dalam tiga jenis. Salah satu contoh hikayat biografi adalah..", "Hikayat Sultan Ibrahim bin Adam", "Hikayat Raja-Raja Passai", "Hikayat Pattani", "Hikayat Hang Tuah", "Hikayat Sultan Ibrahim bin Adam", CATEGORY_PROGRAM));
        mQuestionList.add(new Question("Dalam karya sastra kita mengenali dua unsur, yaitu unsur intrinsik dan unsur ekstrinsik. Unsur intrinsik adalah...", "Unsur yang membentuk karya sastra dari luar sastra itu sendiri", "Unsur kebahasaan", "Unsur yang menyusun sebuah karya sastra dari dalam yang mewujudkan struktur suatu karya sastra", "Unsur pembangun dalam hikayat", "Unsur yang menyusun sebuah karya sastra dari dalam yang mewujudkan struktur suatu karya sastra", CATEGORY_PROGRAM));
        mQuestionList.add(new Question("Berikut ini termasuk unsur intrinsik dalam hikayat, kecuali...", "Tema", "Tokoh dan penokohan", "Alur dan pengaluran", "Latar dan peralatan", "Alur dan pengaluran", CATEGORY_PROGRAM));
        mQuestionList.add(new Question("Pengarang membawa pembaca ke dunia khayal atau imajinasi yang serba indah. Pernyataan tersebut merupakan salah satu ciri hikayat yang berarti...", "Menggunakan bahasa klise", "Magis", "Bersifat pralogis", "Anonim", "Magis", CATEGORY_PROGRAM));

        //questions for category Lagu Daerah
        mQuestionList.add(new Question("Berikut ini yang termasuk dalam lagu daerah dari Betawi, kecuali....", "Keroncong Kemayoran", "Kicir-Kicir", "Butet", "Ondel-Ondel", "Butet", CATEGORY_OLAHRAGA));
        mQuestionList.add(new Question("Lagu Injit - Injit Semut berasal dari....", "Jawa", "jakarta", "sulawesi", "Jambi", "Jambi", CATEGORY_OLAHRAGA));
        mQuestionList.add(new Question("Salah satu lagi daerah dari Jawa Timur adalah...", "Dayung Sampan", "Rek Ayo Rek", "Jali-Jali", "Apuse", "Rek Ayo Rek", CATEGORY_OLAHRAGA));
        mQuestionList.add(new Question("Lagu Butet berasal dari....", "Sumatra Utara", "Kalimantan Barat", "Sulawesi Tenggara", "Aceh", "Sumatra Utara", CATEGORY_OLAHRAGA));
        mQuestionList.add(new Question("Lagu dari Aceh yang terkenal adalah....", "Potong Bebek Angsa", "Burung Kakak Tua", "Bungong Jumpa", "Batanghari", "Bungong Jumpa", CATEGORY_OLAHRAGA));

        //questions for category pancasila
        mQuestionList.add(new Question("Rumusan Pancasiia yang resmi dan sah terdapa dalam...", "Pembukaan UUD 1945", "Piagam Jakarta", "Keputusan Presiden", "Ketetapan MPR", "Pembukaan UUD 1945", CATEGORY_MTK));
        mQuestionList.add(new Question("Pada sidang BPUPKi tanggai 29 Mei 1945-1 Juni 1945, Muhammad Yamin mengajukan usul dasar negara seperti berikut, kecuali...", "perikebangsaan", "periketuhana", "Perikeadilan", "perikemanusiaan", "Perikeadilan", CATEGORY_MTK));
        mQuestionList.add(new Question("Di bawah ini merupakan semboyan Jepang dalam rangka menarik simpati bangsa indonesia ...", "Jepang Pelindung Asia", "Jepang Cahaya Asia", "Jepang Pemimpin Asia", "Jawaban a b c benar", "Jawaban a b c benar", CATEGORY_MTK));
        mQuestionList.add(new Question("Hasil sidang PPKI tanggal 18 Agustus 1945, antara lain menetapkan bahwa untuk sementara waktu, presiden akan dibantu oleh..", "MPRS dan DPAS", "Komite Nasional", "DPRS", "Pemuda Nasionai", "Komite Nasional", CATEGORY_MTK));
        mQuestionList.add(new Question("Dalam sidang l BPUPKi, tiga Orang yang mendapatkan kesempatan untuk mengemukakan pendapat mengenai dasar Negara Indonesia yang akan dibentuk adalah..", "Muhammad Yamin, Soepardjo, dan Soekarno", "Muhammad Yamin, Supomo, dan Soekarno", "Muhammad Natsir, Supomo, dan Soekarno", "Muhammad Natsir, Moh Hatta, dan Soekarno", "Muhammad Yamin, Supomo, dan Soekarno", CATEGORY_MTK));
    }

    private void insertQuestions() {
        for(Question q : mQuestionList) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(QuestionsTable.COLUMN_QUESTION, q.getmQuestion());
            contentValues.put(QuestionsTable.COLUMN_OPTION1, q.getmOption1());
            contentValues.put(QuestionsTable.COLUMN_OPTION2, q.getmOption2());
            contentValues.put(QuestionsTable.COLUMN_OPTION3, q.getmOption3());
            contentValues.put(QuestionsTable.COLUMN_OPTION4, q.getmOption4());
            contentValues.put(QuestionsTable.COLUMN_ANSWER, q.getmAnswer());
            contentValues.put(QuestionsTable.COLUMN_CATEGORY, q.getmCategory());
            db.insert(QuestionsTable.TABLE_NAME, null, contentValues);
        }
    }

    public ArrayList<Question> getAllQuestions(String categoryID) {
        Log.d("TAG", "Getting all questions for : " + categoryID);
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        String SELECT_TABLE_QUERY = "SELECT * FROM " + QuestionsTable.TABLE_NAME + " WHERE " + QuestionsTable.COLUMN_CATEGORY + " = \"" + categoryID + "\"";
        Cursor cursor = db.rawQuery(SELECT_TABLE_QUERY, null);
        if(cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setmQuestion(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setmOption1(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setmOption2(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setmOption3(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setmOption4(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setmAnswer(cursor.getString(cursor.getColumnIndex(QuestionsTable.COLUMN_ANSWER)));
                questionList.add(question);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return questionList;
    }
}
