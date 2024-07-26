package com.example.trannguyenphat.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class QuanLyThuVienDataBase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "qltv.db";
    private static final int DATABASE_VESION = 1;

    public QuanLyThuVienDataBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VESION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        create_TbUser(db);
        create_TbLoaiSach(db);
        create_TbDocGia(db);
        create_TbSach(db);
        create_TbPhieuMuon(db);
        create_TbChiTietPhieuMuon(db);
        
       add_firstData(db);
    }

    private void add_firstData(SQLiteDatabase db) {
        try{
            String insertUser = "INSERT INTO users (ten_nguoi_dung, mat_khau, vai_tro) VALUES " +
                    "('admin', 'admin123', 'admin'), " +
                    "('coctra122', '123456', 'user'), " +
                    "('coctra123', '123456', 'user'), " +
                    "('coctra124', '123456', 'user'), " +
                    "('coctra125', '123456', 'user'), " +
                    "('coctra126', '123456', 'user'), " +
                    "('coctra127', '123456', 'user'), " +
                    "('coctra128', '123456', 'user'), " +
                    "('coctra129', '123456', 'user'), " +
                    "('coctra130', '123456', 'user'), " +
                    "('coctra131', '123456', 'user'), " +
                    "('coctra132', '123456', 'user')";
            db.execSQL(insertUser);

            String insertDocGia = "INSERT INTO docgia (ten_doc_gia, ngay_sinh, email, so_dien_thoai, gioi_tinh, dia_chi, ten_nguoi_dung) VALUES " +
                    "('Trần Nguyên Phát', strftime('%s','2004-07-27'), '', '1234567890', 'nam', '124 Nguyễn Oanh, GV, Tp.Hồ Chí Minh', 'coctra123'), " +
                    "('Nguyễn Huyền Yến', strftime('%s','2000-12-08'), 'email1@example.com', '1234567890', 'Nữ', '327 Quang Trung, GV, Tp.Hồ Chí Minh', 'coctra128')," +
                    "('Lê Văn', strftime('%s','2005-01-01'), 'email2@example.com', '1234567890', 'Nam','124 Nguyễn Oanh, GV, Tp.Hồ Chí Minh','coctra129'), " +
                    "('Người 3', strftime('%s','1999-05-27'),'email3@example.com','1234567890','Nam','124 Nguyễn Oanh, GV, Tp.Hồ Chí Minh','coctra130'), " +
                    "('Người 4', strftime('%s','1998-12-28'),'email4@example.com','1234567890','Nữ','124 Nguyễn Oanh, GV, Tp.Hồ Chí Minh','coctra131'), " +
                    "('Người 5', strftime('%s','2003-07-08'),'email5@example.com','1234567890','Nam','124 Nguyễn Oanh, GV, Tp.Hồ Chí Minh','coctra132');";
            db.execSQL(insertDocGia);

            String insertLoaiSach = "INSERT INTO loaisach (ten_loai_sach) VALUES ('Tiểu Thuyết'),('Truyện Ngắn'),('Du Ký'),('Trinh Thám'),('Kinh Tế'),('Ngoại Ngữ'),('Giáo Khoa - Tham Khảo')";
            db.execSQL(insertLoaiSach);

            String insertSachs = "INSERT INTO sachs (id_sach, ten_sach, tac_gia, nha_xuat_ban, loai_sach, nam_xuat_ban, trang_thai, image, ghi_chu) VALUES " +
                    "(NULL, 'Cây Cam Ngọt Của Tôi', 'José Mauro de Vasconcelos', 'NXB Hội Nhà Văn', 1, 2020, 'Sẵn có', 'btt_98263692847', 'Mở đầu bằng những thanh âm trong sáng và kết thúc lắng lại trong những nốt trầm hoài niệm, Cây cam ngọt của tôi khiến ta nhận ra vẻ đẹp thực sự của cuộc sống đến từ những điều giản dị như bông hoa trắng của cái cây sau nhà, và rằng cuộc đời thật khốn khổ nếu thiếu đi lòng yêu thương và niềm trắc ẩn. Cuốn sách kinh điển này bởi thế không ngừng khiến trái tim người đọc khắp thế giới thổn thức, kể từ khi ra mắt lần đầu năm 1968 tại Brazil.')," +
                    "(NULL, 'Ngày Xưa Có Một Chuyện Tình', 'Nguyễn Nhật Ánh', 'NXB Trẻ', 1, 2019, 'Sẵn có', 'btt_195509129716', NULL)," +
                    "(NULL, 'Tôi Là Bêtô', 'Nguyễn Nhật Ánh', 'NXB Trẻ', 1, 2023, 'Sẵn có', 'btt_4352023043531', NULL)," +
                    "(NULL, 'Lén Nhặt Chuyện Đời', 'Mộc Trầm', 'NXB Thế Giới', 1, 2022, 'Sẵn có', 'btt_97860436515911', NULL)," +
                    "(NULL, 'Tôi Thấy Hoa Vàng Trên Cỏ Xanh', 'Nguyễn Nhật Ánh', 'NXB Trẻ', 1, 2023, 'Sẵn có', 'btt_192215509122716', NULL)," +
                    "(NULL, 'Thiên Nga Đen', 'Nassim Nicholas Taleb', 'NXB Thế Giới', 5, 2020, 'Sẵn có', 'bkt_195509127686', NULL)," +
                    "(NULL, 'MBA Bằng Hình - The Usual MBA', 'Jason Barron , MBA', 'NXB Công Thương', 5, 2023,'Sẵn có','bkt_8935235238978', NULL),"+
                    "(NULL, 'Chính Sách Tiền Tệ Thế Kỷ 21','Ben S. Bernanke','NXB Thế Giới',5,'2023','Sẵn có','bkt_9783047187518', NULL),"+
                    "(NULL, 'Kế Toán Vỉa Hè','Darrell Mullis,Judith Orloff','NXB Thế Giới',5,'2023','Sẵn có','bkt_9786047787616', NULL)," +
                    "(NULL, 'Bài Tập Ngữ Pháp Tiếng Hàn - Trình Độ Nâng Cao', 'MaNguyễn Hoàng Thảo Ly', 'NXB Hồng Đức', 6, 2012, 'Sẵn có', 'bnn_195509121386', NULL)," +
                    "(NULL, '2500 Từ Vựng Cần Thiết Cho Kỳ Thi Năng Lực Nhật Ngữ N2', 'Arc Academy', 'NXB Trẻ', 6, 2020, 'Sẵn có', 'bnn_1955091266081', NULL)," +
                    "(NULL, 'Ngữ Pháp Tiếng Pháp Căn Bản', 'Nguyễn Thức Thành Tín', 'Đại Học Sư Phạm', 6, 2022,'Đã mượn', 'bnn_8935072956561', NULL)," +
                    "(NULL, 'Cambridge IELTS 17 Academic With Answers', 'Cambridge', 'Cambridge University', 6, 2022, 'Sẵn có', 'bnn_9781009320948', NULL)," +
                    "(NULL, 'Giáo Trình Chuẩn HSK 1', 'Khương Lệ Bình', 'NXB Tổng Hợp Thành Phố Hồ Chí Minh', 6, 2023, 'Không còn', 'bnn_9786043775662', NULL)," +
                    "(NULL, 'Tiếng Anh Cho NgườI Bắt Đầu', 'Trang Anh', 'Hồng Đức', 6, 2023, 'Sẵn có', 'bnn_9786043987102', NULL)," +
                    "(NULL, 'N1 - 3000 Từ Vựng Cần Thiết Cho Kỳ Thi Năng Lực Nhật Ngữ', 'Arc Academy', 'NXB Trẻ', 6, 2020, 'Sẵn có', 'bnn_195509126612', NULL)," +
                    "(NULL, 'Khám Phá Giáo Dục Steam', 'Nhiều Tác Giả', 'NXB Đại Học Sư Phạm Tp.HCM', 7, 2020, 'Sẵn có', 'btk_1955091452421', NULL)," +
                    "(NULL, 'Tài Liệu Dạy - Học Hóa Học 9 - Tập 2', 'Trương Công Luận', 'NXB Giáo Dục Việt Nam', 7, 2020, 'Sẵn có', 'btk_9786040361240', NULL)";
            db.execSQL(insertSachs);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void create_TbChiTietPhieuMuon(SQLiteDatabase db) {
        String TABLE = "chitietphieumuon";
        String COL_CHITIETPHIEUMUON_IDCHITIETPHIEUMUON = "id_chi_tiet_phieu_muon";
        String COL_CHITIETPHIEUMUON_IDPHIEUMUON = "id_phieu_muon";
        String COL_CHITIETPHIEUMUON_IDSACH = "id_sach";
        String COL_CHITIETPHIEUMUON_NGAYMUON = "ngay_muon";
        String COL_CHITIETPHIEUMUON_HANTRA = "han_tra";
        String COL_CHITIETPHIEUMUON_TRANGTHAI = "trang_thai";

        String scriptTbChiTietPhieuMuon = "CREATE TABLE " + TABLE + "(" +
                COL_CHITIETPHIEUMUON_IDCHITIETPHIEUMUON + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                COL_CHITIETPHIEUMUON_IDPHIEUMUON + " INTEGER NOT NULL, " +
                COL_CHITIETPHIEUMUON_IDSACH + " INTEGER NOT NULL, " +
                COL_CHITIETPHIEUMUON_NGAYMUON + " INTEGER NOT NULL, " +
                COL_CHITIETPHIEUMUON_HANTRA + " INTEGER NOT NULL, " +
                COL_CHITIETPHIEUMUON_TRANGTHAI + " TEXT, " +
                "FOREIGN KEY(" + COL_CHITIETPHIEUMUON_IDPHIEUMUON + ") REFERENCES phieumuon(" + COL_CHITIETPHIEUMUON_IDPHIEUMUON + "), " +
                "FOREIGN KEY(" + COL_CHITIETPHIEUMUON_IDSACH + ") REFERENCES sachs(" + COL_CHITIETPHIEUMUON_IDSACH + "))";
        db.execSQL(scriptTbChiTietPhieuMuon);
    }

    private void create_TbPhieuMuon(SQLiteDatabase db) {
        String TABLE = "phieumuon";
        String COL_PHIEUMUON_IDPHIEUMUON = "id_phieu_muon";
        String COL_PHIEUMUON_IDDOCGIA = "id_doc_gia";
        String COL_PHIEUMUON_NGAYLAP = "ngay_lap";

        String scriptTbPhieuMuon = "CREATE TABLE " + TABLE + "(" +
                COL_PHIEUMUON_IDPHIEUMUON + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                COL_PHIEUMUON_IDDOCGIA + " INTEGER NOT NULL, " +
                COL_PHIEUMUON_NGAYLAP + " INTEGER NOT NULL," +
                "FOREIGN KEY(" + COL_PHIEUMUON_IDDOCGIA + ") REFERENCES docgia(" + COL_PHIEUMUON_IDDOCGIA + "))";
        db.execSQL(scriptTbPhieuMuon);
    }

    private void create_TbSach(SQLiteDatabase db) {
        String TABLE = "sachs";
        String COL_SACHS_IDSACH = "id_sach";
        String COL_SACHS_TENSACH = "ten_sach";
        String COL_SACHS_TACGIA = "tac_gia";
        String COL_SACHS_NHAXB = "nha_xuat_ban";
        String COL_SACHS_LOAISACH = "loai_sach";
        String COL_SACHS_NAMXB = "nam_xuat_ban";
        String COL_SACHS_TRANGTHAI = "trang_thai";
        String COL_SACHS_IMAGE = "image";
        String COL_SACHS_GHICHU = "ghi_chu";

        String scriptTbSach = "CREATE TABLE " + TABLE + "(" +
                COL_SACHS_IDSACH + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                COL_SACHS_TENSACH + " TEXT NOT NULL, " +
                COL_SACHS_TACGIA + " TEXT NOT NULL, " +
                COL_SACHS_NHAXB + " TEXT NOT NULL, " +
                COL_SACHS_LOAISACH + " INTEGER NOT NULL, " +
                COL_SACHS_NAMXB + " INTEGER NOT NULL, " +
                COL_SACHS_TRANGTHAI + " TEXT, " +
                COL_SACHS_IMAGE + " TEXT, " +
                COL_SACHS_GHICHU + " TEXT, " +
                "FOREIGN KEY(" + COL_SACHS_LOAISACH + ") REFERENCES loaisach(id_loai_sach))";
        db.execSQL(scriptTbSach);
    }

    private void create_TbDocGia(SQLiteDatabase db) {
        String TABLE = "docgia";
        String COL_DOCGIA_IDDOCGIA = "id_doc_gia";
        String COL_DOCGIA_TENDOCGIA = "ten_doc_gia";
        String COL_DOCGIA_NGAYSINH = "ngay_sinh";
        String COL_DOCGIA_EMAIL = "email";
        String COL_DOCGIA_SODIENTHOAI = "so_dien_thoai";
        String COL_DOCGIA_GIOITINH = "gioi_tinh";
        String COL_DOCGIA_DIACHI = "dia_chi";
        String COL_DOCGIA_TENNGUOIDUNG = "ten_nguoi_dung";

        String scriptTbDocGia = "CREATE TABLE " + TABLE + "(" +
                COL_DOCGIA_IDDOCGIA + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                COL_DOCGIA_TENDOCGIA + " TEXT, " +
                COL_DOCGIA_NGAYSINH + " INTEGER, " +
                COL_DOCGIA_EMAIL + " TEXT, " +
                COL_DOCGIA_SODIENTHOAI + " INTEGER, " +
                COL_DOCGIA_GIOITINH + " TEXT, " +
                COL_DOCGIA_DIACHI + " TEXT, " +
                COL_DOCGIA_TENNGUOIDUNG + " TEXT, " +
                "FOREIGN KEY(" + COL_DOCGIA_TENNGUOIDUNG + ") REFERENCES users(" + COL_DOCGIA_TENNGUOIDUNG + "))";
        db.execSQL(scriptTbDocGia);
    }

    private void create_TbLoaiSach(SQLiteDatabase db) {
        String TABLE = "loaisach";
        String COL_LOAISACH_IDLOAISACH = "id_loai_sach";
        String COL_LOAISACH_TENLOAISACH = "ten_loai_sach";

        String scriptTbLoaiSach = "CREATE TABLE " + TABLE + "(" +
                COL_LOAISACH_IDLOAISACH + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                COL_LOAISACH_TENLOAISACH + " TEXT NOT NULL)";
        db.execSQL(scriptTbLoaiSach);
    }

    private void create_TbUser(SQLiteDatabase db) {
        String TABLE = "users";
        String COL_USERS_TENNGUOIDUNG = "ten_nguoi_dung";
        String COL_USERS_MATKHAU = "mat_khau";
        String COL_USERS_VAITRO = "vai_tro";

        String scriptTbUsers = "CREATE TABLE " + TABLE + "(" +
                COL_USERS_TENNGUOIDUNG + " TEXT PRIMARY KEY NOT NULL, " +
                COL_USERS_MATKHAU + " TEXT NOT NULL, " +
                COL_USERS_VAITRO + " TEXT)";
        db.execSQL(scriptTbUsers);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion){
            db.execSQL("DROP TABLE IF EXISTS chitietphieumuon");
            db.execSQL("DROP TABLE IF EXISTS phieumuon");
            db.execSQL("DROP TABLE IF EXISTS sachs");
            db.execSQL("DROP TABLE IF EXISTS docgia");
            db.execSQL("DROP TABLE IF EXISTS loaisach");
            db.execSQL("DROP TABLE IF EXISTS users");
            onCreate(db);
        }
    }
}
