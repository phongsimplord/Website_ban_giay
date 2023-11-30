drop database website_ban_giay
go
create database website_ban_giay
go
use website_ban_giay
go
create table thuong_hieu (
	id uniqueidentifier primary key default newid(),
	ten_url varchar(20) default null,
	ma varchar(20) unique,
	ten nvarchar(50) default null,
	trangthai int null
)
create table gioi_tinh (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ten nvarchar(50) default null,
	trangthai int null
)
create table de_giay (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ten nvarchar(50) default null,
	trangthai int null
)
create table xuat_xu (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ten nvarchar(50) default null,
	trangthai int null
)
create table kieu_dang (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ten nvarchar(50) default null,
	trangthai int null
)
create table chat_lieu (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ten nvarchar(50) default null,
	trangthai int null
)
create table mau_sac (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ten nvarchar(50) default null,
	trangthai int null
)
create table giay (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ten nvarchar(50) default null,
	id_thuong_hieu uniqueidentifier,
	id_gioi_tinh uniqueidentifier,
	id_chat_lieu uniqueidentifier,
	id_de_giay uniqueidentifier,
	id_mau_sac uniqueidentifier,
	id_xuat_xu uniqueidentifier,
	id_kieu_dang uniqueidentifier,
	mota nvarchar(255) null,
	gianhap decimal,
	giaban decimal,
	trangthai int null,
	gia_sau_khuyen_mai decimal,
	ngay_nhap date,
	do_hot int null,
	foreign key (id_thuong_hieu) references thuong_hieu(id),
	foreign key (id_gioi_tinh) references gioi_tinh(id),
	foreign key (id_chat_lieu) references chat_lieu(id),
	foreign key (id_de_giay) references de_giay(id),
	foreign key (id_xuat_xu) references xuat_xu(id),
	foreign key (id_kieu_dang) references kieu_dang(id),
	foreign key (id_mau_sac) references mau_sac(id)
)
create table anh_giay (
	id uniqueidentifier primary key default newid(),
	ten_url varchar(20) unique,
	id_giay uniqueidentifier,
	trangthai int null,
	foreign key (id_giay) references giay(id)
)
create table kich_co (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ten nvarchar(50) default null,
	trangthai int null
)
create table giay_chi_tiet (
	id uniqueidentifier primary key default newid(),
	id_giay uniqueidentifier,
	id_kich_co uniqueidentifier,
	so_luong_ton int default null,
	trangthai int null,
	foreign key (id_giay) references giay(id),
	foreign key (id_kich_co) references kich_co(id)
)
create table chuc_vu (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ten nvarchar(50) default null,
	trangthai int null
)
create table nhan_vien (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ho_ten nvarchar(50) default null,
	ngay_sinh date null,
	dia_chi nvarchar(255) null,
	xa nvarchar(250) default null,
	huyen nvarchar(50) default null,
	thanh_pho nvarchar(255) null,
	anh varchar(20) null,
	sdt nvarchar(15) null,
	email nvarchar(255) null,
	id_chuc_vu uniqueidentifier,
	mat_khau nvarchar(255) null,
	ngay_vao_lam date null,
	ngay_nghi_viec date null,
	trangthai int null,
	foreign key (id_chuc_vu) references chuc_vu(id),
)
create table chuong_tring_giam_gia_san_pham (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ten nvarchar(50) default null,
	phan_tram_giam int,
	ngay_bat_dau date,
	ngay_ket_thuc date,
	id_nhan_vien_create uniqueidentifier,
	id_nhan_vien_update uniqueidentifier,
	trangthai int null,
	foreign key (id_nhan_vien_create) references nhan_vien(id),
	foreign key (id_nhan_vien_update) references nhan_vien(id)
)
create table chuong_trinh_giam_gia_chi_tiet_san_pham (
	id uniqueidentifier primary key default newid(),
	id_giay uniqueidentifier,
	id_chuong_trinh_giam_gia uniqueidentifier,
	so_tien_da_giam decimal,
	trangthai int null,
	foreign key (id_giay) references giay(id),
	foreign key (id_chuong_trinh_giam_gia) references chuong_tring_giam_gia_san_pham(id)
)
create table khach_hang (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	avatar varchar(20) null,
	ho_ten nvarchar(50) default null,
	ngay_sinh date null,
	sdt nvarchar(15) null,
	email nvarchar(255) null,
	mat_khau nvarchar(255) null,
	trangthai int null,
)
create table danh_gia(
	id uniqueidentifier primary key default newid(),
	id_giay uniqueidentifier not null,
	sao int,
	ten_nguoi_danh_gia nvarchar(50),
	noi_dung nvarchar(max),
	trang_thai int,
	foreign key (id_giay) references giay(id)
)
create table dia_chi (
	id uniqueidentifier primary key default newid(),
	ma nvarchar(50) default null,
	id_khach_hang uniqueidentifier,
	ten_dia_chi nvarchar(250) default null,
	ten_nguoi_nhan nvarchar(250) default null,
	sdt_nguoi_nhan nvarchar(250) default null,
	xa nvarchar(250) default null,
	huyen nvarchar(50) default null,
	thanh_pho nvarchar(50) default null,
	trangthai int null,
	foreign key (id_khach_hang) references khach_hang(id)
)
create table gio_hang (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	id_khach_hang uniqueidentifier null,
	ngay_tao date null,
	ngay_cap_nhap date null,
	ghi_chu nvarchar(255) null,
	trangthai int null,
	foreign key (id_khach_hang) references khach_hang(id)
)
create table gio_hang_chi_tiet (
	id uniqueidentifier primary key default newid(),
	id_gio_hang uniqueidentifier null,
	id_giay_chi_tiet uniqueidentifier null,
	so_luong int null,
	ghi_chu nvarchar(255) null,
	trangthai int null,
	foreign key (id_gio_hang) references gio_hang(id),
	foreign key (id_giay_chi_tiet) references giay_chi_tiet(id)
)
create table hoa_don (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ngay_tao date null,
	ngay_thanh_toan date null,
	id_nhan_vien uniqueidentifier,
	id_khach_hang uniqueidentifier,
	mo_ta nvarchar(255) null,
	ten_nguoi_nhan nvarchar(255) null,
	sdt_nguoi_nhan nvarchar(255) null,
	dia_chi nvarchar(255) null,
	tong_tien decimal,
	hinh_thuc_mua int,
	hinh_thuc_thanh_toan int,
	so_tien_giam money,
	phi_ship money,
	trangthai int null,
	foreign key (id_nhan_vien) references nhan_vien(id),
	foreign key (id_khach_hang) references khach_hang(id)
)
create table hoa_don_chi_tiet (
	id uniqueidentifier primary key default newid(),
	id_hoa_don uniqueidentifier,
	id_giay_chi_tiet uniqueidentifier,
	so_luong int,
	gia_nhap decimal,
	don_gia decimal,
	trangthai int null,
	foreign key (id_hoa_don) references hoa_don(id),
	foreign key (id_giay_chi_tiet) references giay_chi_tiet(id)
)
create table chuong_trinh_giam_gia_hoa_don (
	id uniqueidentifier primary key default newid(),
	ma varchar(20) unique,
	ten nvarchar(50) default null,
	dieu_kien decimal default null, --gia hoa don nho nhat de duoc phep sd giam gia vd :100000vnd
	so_tien_giam_max decimal  default null,
	ngay_bat_dau date  default null,
	ngay_ket_thuc date  default null,
	phan_tram_giam int  default null,
	so_luong int  default null,
	trangthai int null
)
create table chuong_trinh_giam_gia_chi_tiet_hoa_don (
	id uniqueidentifier primary key default newid(),
	id_hoa_don uniqueidentifier unique,
	id_chuong_trinh_giam_gia_hoa_don uniqueidentifier,
	tong_tien decimal  default null,
	so_tien_da_giam decimal  default null,
	tong_tien_thanh_toan decimal  default null,
	trangthai int null,
	foreign key (id_hoa_don) references hoa_don(id),
	foreign key (id_chuong_trinh_giam_gia_hoa_don) references chuong_trinh_giam_gia_hoa_don(id)
)
create table san_pham_yeu_thich(
	id uniqueidentifier primary key default newid(),
	id_khach_hang uniqueidentifier,
	ngay_tao date null,
		trangthai int null,
	foreign key (id_khach_hang) references khach_hang(id)
)
create table san_pham_yeu_thich_chi_tiet(
	id uniqueidentifier primary key default newid(),
	id_san_pham_yeu_thich uniqueidentifier,
	id_giay uniqueidentifier,
	ngay_tao date null,
	trangthai int null,
	foreign key (id_san_pham_yeu_thich) references san_pham_yeu_thich(id),
		foreign key (id_giay) references giay(id)
)
INSERT INTO website_ban_giay.dbo.chuc_vu (id,ma,ten,trangthai) VALUES
	 (N'7B52F69D-5508-4745-926F-A22F9907E523',N'CV02',N'USER',1),
	 (N'F09E9132-7F9E-48BD-A30C-E58C9E16717E',N'CV01',N'ADMIN',1);
INSERT INTO website_ban_giay.dbo.chat_lieu (id,ma,ten,trangthai) VALUES
	 (N'2637F26C-A204-42C1-AC7F-12B8FC937C9A',N'CL7',N'Da tổng hợp',1),
	 (N'BE5186A7-5389-4FD3-B7AF-2BC0DB126295',N'CL11',N'Vải lưới',1),
	 (N'CC292B68-7435-4206-96CC-38AD77F2ABBC',N'CL6',N'Polyester',1),
	 (N'81189F90-E2C9-481F-9DAB-5D71CBE35E0B',N'CL8',N'Canvas',1),
	 (N'40C6E20A-77FE-4194-837D-749CD2C09EED',N'CL2',N'Cao su',1),
	 (N'A93C9F0B-23C4-4F85-BF73-7F735B7E730C',N'CL9',N'Da bò',1),
	 (N'DB7162EA-A549-44AF-AB96-93D83031F7F1',N'CL5',N'Vải dệt',1),
	 (N'6E43A2A1-006F-43EB-B242-99908DA9C1FE',N'CL4',N'Vải cao cấp',1),
	 (N'5D450BC9-283A-452D-AA09-9C0DC9E65CCF',N'CL3',N'Da nhân tạo',1),
	 (N'D46378E2-9894-405B-8082-CEEEF95345B2',N'CL1',N'Da cao cấp',1),
	 (N'35130268-9650-4FAB-9D9E-A28158D5BCE7',N'CL10',N'Da lộn',1);
INSERT INTO website_ban_giay.dbo.de_giay (id,ma,ten,trangthai) VALUES
	 (N'4B9E5750-A2CF-4A8F-AE41-027E0FDF88EF',N'DG5',N'Đế kếp',1),
	 (N'265BD4E5-0F66-491C-80A3-27C4514F481F',N'DG7',N'Đế giày Christy Wedge',1),
	 (N'D2732979-A06B-4CB0-9061-329B411586F3',N'DG4',N'Đế phíp',1),
	 (N'D0767B24-FA85-4D04-BEA6-3562B9997EF2',N'DG3',N'Đế da',1),
	 (N'8C8121CB-DA54-47AD-AE66-81295B27E40E',N'DG2',N'Đế Pu',1),
	 (N'075DE902-E04D-4495-9ACA-A8C237BEA5D7',N'DG6',N'Đế Commando',1),
	 (N'6C26B653-B861-439F-AEEE-AD31E40EAE7D',N'DG9',N'Đế giày Cork Nitrile',1),
	 (N'061EC5B1-B1BF-433D-B0CA-CE800DFAF3E9',N'DG8',N'Đế giày Rubber',1),
	 (N'6DF06D0D-5366-420E-ABEE-E06B16E193DD',N'DG10',N'Đế giày Crepe',1),
	 (N'67EB0BD0-732B-4882-9913-FC86F05F733D',N'DG1',N'Đế cao su',1);
INSERT INTO website_ban_giay.dbo.kich_co (id,ma,ten,trangthai) VALUES
	 (N'09FD4A50-B9F7-4292-8A89-0DB59C0936A3',N'KC4',N'35',1),
	 (N'0627D97B-F97D-40E1-9FA6-15E914D5C594',N'KC3',N'30',1),
	 (N'F72D2327-32E8-4AE4-9E21-2C72F847FE50',N'KC7',N'38',1),
	 (N'C4C4B808-00C8-48FB-B043-2CC3240A71B3',N'KC6',N'37',1),
	 (N'3DC52F6D-C256-4D92-8A1A-3C0FEBFCC56A',N'KC10',N'41',1),
	 (N'5DA656B5-827E-450D-B5BD-3E02CC1DB251',N'KC5',N'36',1),
	 (N'B32D3113-2DC3-4697-A410-4A4FD4FE59A1',N'KC1',N'20',1),
	 (N'E296B3A2-3A8B-4321-B9B4-4B668F661164',N'KC2',N'25',1),
	 (N'6A438602-1AAD-4431-BE21-62BAFBC2D838',N'KC11',N'42',1),
	  (N'6E878EFE-1924-424C-B578-844DCC22A1B8',N'KC9',N'40',1),
	 (N'C76F964D-6C71-4774-93D0-9143964315B4',N'KC12',N'43',1),
	 (N'7C55C58F-70C6-4FD8-9CD2-E2BB49794D53',N'KC13',N'44',1),
	 (N'9AE7954E-5C93-4DF6-A431-6786C0B2CED6',N'KC8',N'39',1);
	INSERT INTO website_ban_giay.dbo.kieu_dang (id,ma,ten,trangthai) VALUES
	 (N'5D0BC152-D2BD-4D80-91DE-1820E24B6FF6',N'KD6',N'Giày thể thao',1),
	 (N'C03F6C3D-084D-4E22-8C17-20EDCF6C15CB',N'KD1',N'Giày Lười',1),
	 (N'5E6B2DBE-916D-4AA5-A9E0-305F3BF66CF7',N'KD10',N'Giày Slip On',1),
	 (N'8DCB4009-0545-4505-85FE-4190D1FCFD02',N'KD5',N'Giày bít mũi',1),
	 (N'06D5B281-9078-4AD5-AE5A-43D26F732976',N'KD4',N'Sneakers',1),
	 (N'4F492D14-468E-4A03-AC38-72E72BD165F4',N'KD8',N'Giày tây',1),
	 (N'79B2042D-9875-4BEE-A2E7-747BFF370D3A',N'KD7',N'Giày chạy bộ',1),
	 (N'C89F9C86-9C06-4F10-82DD-9C0198DEC178',N'KD2',N'Giày cao gót',1),
	 (N'89E541B6-D7B1-4EFC-9E0A-9C1FA04F7B43',N'KD3',N'Sandals',1),
	 (N'33777C09-7AB4-4065-872A-E45D6B449B6F',N'KD9',N'Giày bóng rổ',1);
	 INSERT INTO website_ban_giay.dbo.mau_sac (id,ma,ten,trangthai) VALUES
	 (N'5FF95118-AF6A-484C-94BA-064C3BB96FDC',N'MS2',N'Trắng',1),
	 (N'AE8450F7-4FCE-4AE9-89C9-0C0B7E8B777F',N'MS9',N'Vàng Hồng',1),
	 (N'74D19F0C-FECD-4CB6-9EE9-1E7BCB784F10',N'MS5',N'Bạc',1),
	 (N'567E8057-5837-45A1-A76F-3FE524E62EAE',N'MS6',N'Xám',1),
	 (N'8A4D64B5-42C6-4E91-8C77-4266246B2B52',N'MS11',N'Xanh Blue',1),
	 (N'BB9B5A33-457D-4479-A673-7A4018D80DDD',N'MS7',N'Hồng',1),
	 (N'310D7290-3B6B-4ACD-9AE6-8940E11A3C90',N'MS3',N'Nâu',1),
	 (N'A12276C1-86FD-4863-87E6-8E843601D359',N'MS8',N'Đỏ gạch',1),
	 (N'83F02A8D-13BE-4295-A8CA-A1F02D9FF39A',N'MS10',N'Đen trắng',1),
	 (N'51711A46-897D-4F59-A4DD-AB9EF99515FC',N'MS1',N'Đen',1),
	 (N'79E20A76-3E91-4C53-BD41-ECFEF4245FAD',N'MS4',N'Xanh navy',1);
	 INSERT INTO website_ban_giay.dbo.nhan_vien (id,ma,ho_ten,ngay_sinh,dia_chi,xa,huyen,thanh_pho,sdt,email,id_chuc_vu,mat_khau,ngay_vao_lam,ngay_nghi_viec,trangthai) VALUES
	 (N'9D8372B8-AAA4-464A-A938-3C6927210010',N'NV01',N'Nguyễn Văn Đạt','2003-12-12',N'Cấn Hữu-Quốc Oai-Hà Nội',NULL,NULL,NULL,N'0385090080',N'danhntnv1@gmail.com',N'7B52F69D-5508-4745-926F-A22F9907E523',N'$2a$10$6xUrgawUAXvPCohIWbL9e.lD8LKI1.ZyeZyo8hKRzQqlqVJ3CpBtq','2020-12-12',NULL,1),
	 (N'5D89F310-6D41-41EE-B5B8-57F5B6DB7A9A',N'NV02',N'Hoàng Đại Ka','2003-09-09',N'Cấn Hữu-Quốc Oai-Hà Nội',NULL,NULL,NULL,N'0385090080',N'danhntnv2@gmail.com',N'F09E9132-7F9E-48BD-A30C-E58C9E16717E',N'$2a$10$6xUrgawUAXvPCohIWbL9e.lD8LKI1.ZyeZyo8hKRzQqlqVJ3CpBtq','2023-09-12',NULL,1);
	 INSERT INTO website_ban_giay.dbo.thuong_hieu (id,ma,ten,trangthai,ten_url) VALUES
	 (N'F6F425A0-AD73-421F-B76B-09787A50808F',N'TH07',N'Gucci',1,N'logo1.png'),
	 (N'C41E486F-1EB2-40FD-9D2A-1F8F12298488',N'TH08',N'Calvin Klein',1,N'logo2.png'),
	 (N'EC0054D9-433D-4E85-8190-4582CDEBF593',N'TH03',N'MLB',1,N'logo3.png'),
	 (N'4EDE9E53-D595-4893-BDDF-46CCA4147D45',N'TH09',N'YSL',1,N'logo4.png'),
	 (N'5A6426F1-A954-4F40-ACE4-47646087F8BB',N'TH04',N'Lacoste',1,N'logo5.png'),
	 (N'12FB3324-EBA7-4F9F-995D-675CAD5097F3',N'TH02',N'Versace',1,N'logo6.png'),
	 (N'18364555-4334-4232-997D-8880592056AD',N'TH10',N'Nike',1,N'logo7.png'),
	 (N'BD4301E2-A17E-4F4D-AAEC-A44534AA7FF5',N'TH12',N'Puma',1,N'logo8.png'),
	 (N'9F92ABD8-69FB-4AC0-9D81-A77C5D525252',N'TH05',N'Adidas',1,N'logo1.png'),
	  (N'26E76171-C2CD-4766-97B8-E814FFA80EA6',N'TH06',N'Chanel',1,N'logo3.png'),
	 (N'B83D14A1-57D9-4966-A111-E913E7574FD4',N'TH01',N'Dior',1,N'logo4.png'),
	 (N'C3685B9F-8E3E-462B-975E-CC54FDF6DC9E',N'TH11',N'Balenciaga',1,N'logo2.png');
	INSERT INTO website_ban_giay.dbo.xuat_xu (id,ma,ten,trangthai) VALUES
	 (N'2D3C1855-497D-47C0-80F1-26500C8F1514',N'XS3',N'Ý',1),
	 (N'0345F695-5836-4185-A2D6-3005156DD0E3',N'XS7',N'Nhật Bản',1),
	 (N'EAB91FA5-29B9-4DFE-908E-39C48F11ED65',N'XS8',N'Việt Nam',1),
	 (N'01BCCF59-5947-4D84-A465-3D1662853418',N'XS5',N'Đức',1),
	 (N'344B6975-429D-4F20-9287-3D9C95938000',N'XS2',N'Pháp',1),
	 (N'938FAA96-3ED2-4385-9A93-52250376C599',N'XS9',N'Tây Ban Nha',1),
	 (N'46F694DF-1D74-4973-8421-9AFAD92CC9AF',N'XS6',N'Anh',1),
	 (N'3E32910B-3FA6-43F1-9218-AA83ECBB55DE',N'XS10',N'Thụy Sỹ',1),
	 (N'7F30834B-812F-49FA-AA3E-B55B043D7B43',N'XS4',N'Hàn Quốc',1),
	 (N'5232FDEF-6D86-471B-B921-EDEDCDED72EB',N'XS1',N'Mỹ',1);
	 INSERT INTO website_ban_giay.dbo.gioi_tinh (id,ma,ten,trangthai) VALUES
	 (N'4EC08B9F-D257-7A49-A72D-0D6BD38033B4',N'GT2',N'Nữ',1),
	 (N'D523EBBA-D211-0345-8EE8-15ECD456DBD9',N'GT3',N'Unisex',1),
	 (N'48BC5FB5-819D-5748-9CE6-310AE8CF4579',N'GT1',N'Nam',1),
	 (N'C352570B-C9BA-6B43-B579-A24833969874',N'GT4',N'Trẻ em',1);
	 INSERT INTO website_ban_giay.dbo.giay (id,ma,ten,id_thuong_hieu,id_gioi_tinh,id_chat_lieu,id_de_giay,id_mau_sac,id_xuat_xu,id_kieu_dang,mota,gianhap,giaban,trangthai,gia_sau_khuyen_mai,ngay_nhap,do_hot) VALUES
	 (N'597A9673-6929-D342-96DC-0A4C47E3CC7B',N'SP06',N'Giày Thể Thao MLB Chunky Liner Basic LA',N'EC0054D9-433D-4E85-8190-4582CDEBF593',N'D523EBBA-D211-0345-8EE8-15ECD456DBD9',N'D46378E2-9894-405B-8082-CEEEF95345B2',N'8C8121CB-DA54-47AD-AE66-81295B27E40E',N'79E20A76-3E91-4C53-BD41-ECFEF4245FAD',N'7F30834B-812F-49FA-AA3E-B55B043D7B43',N'5D0BC152-D2BD-4D80-91DE-1820E24B6FF6',N' là mẫu dành cho cả nam và nữ đến từ Hàn Quốc đang được săn đón trên thị trường. Sở hữu thiết kế đẹp mắt cùng sự phối màu trẻ trung ',3500000,3850000,1,3080000,'2023-11-06',1),
	 (N'3F71490B-511F-7B44-84C9-3FE4413B3188',N'SP04',N'Giày Thể Thao MLB Chunky Classic P LA Dodger',N'EC0054D9-433D-4E85-8190-4582CDEBF593',N'D523EBBA-D211-0345-8EE8-15ECD456DBD9',N'D46378E2-9894-405B-8082-CEEEF95345B2',N'D2732979-A06B-4CB0-9061-329B411586F3',N'5FF95118-AF6A-484C-94BA-064C3BB96FDC',N'7F30834B-812F-49FA-AA3E-B55B043D7B43',N'5D0BC152-D2BD-4D80-91DE-1820E24B6FF6',N' là mẫu dành cho cả nam và nữ đến từ Hàn Quốc đang làm mưa làm gió thời gian qua trên thị trường. Sở hữu thiết kế đẹp mắt cùng chất liệu cao cấp ',3000000,3500000,1,2800000,'2023-11-06',1),
	 (N'CB626472-C91C-EC4A-9369-7B0942E94EDD',N'SP03',N'Giày Cao Gót Nữ Dior J’adior Slingback Pump',N'B83D14A1-57D9-4966-A111-E913E7574FD4',N'4EC08B9F-D257-7A49-A72D-0D6BD38033B4',N'6E43A2A1-006F-43EB-B242-99908DA9C1FE',N'4B9E5750-A2CF-4A8F-AE41-027E0FDF88EF',N'AE8450F7-4FCE-4AE9-89C9-0C0B7E8B777F',N'344B6975-429D-4F20-9287-3D9C95938000',N'C89F9C86-9C06-4F10-82DD-9C0198DEC178',N' là đôi giày thời trang đến từ thương hiệu  của Pháp. Mang thiết kế cổ điển, đôi giày không chỉ nâng niu gót ngọc, tôn lên vẻ nữ tính, uyển chuyển mà còn lại mang lại cảm giác thoải mái, êm ái khi mang.',20000000,25900000,1,25900000,'2023-11-06',1),
	 (N'FC4C744B-AEDE-B645-B118-7EA9140B8249',N'SP02',N'Giày Sneaker Dior Walk''nDior',N'B83D14A1-57D9-4966-A111-E913E7574FD4',N'D523EBBA-D211-0345-8EE8-15ECD456DBD9',N'81189F90-E2C9-481F-9DAB-5D71CBE35E0B',N'8C8121CB-DA54-47AD-AE66-81295B27E40E',N'51711A46-897D-4F59-A4DD-AB9EF99515FC',N'344B6975-429D-4F20-9287-3D9C95938000',N'5D0BC152-D2BD-4D80-91DE-1820E24B6FF6',N' là đôi giày thời trang đến từ thương hiệu Dior nổi tiếng. Với thiết kế đơn giản, kiểu dáng hiện đại trẻ trung, đôi giày mang đến sự trải nghiệm tuyệt vời nhất cho khách hàng khi đi lên chân.',20000000,22730000,1,22730000,'2023-11-06',1),
	 (N'C00D80A0-1ADB-7845-9F3B-9AC42C2E2D1C',N'SP01',N'Giày Dior Homme B06 Leather',N'B83D14A1-57D9-4966-A111-E913E7574FD4',N'48BC5FB5-819D-5748-9CE6-310AE8CF4579',N'BE5186A7-5389-4FD3-B7AF-2BC0DB126295',N'075DE902-E04D-4495-9ACA-A8C237BEA5D7',N'51711A46-897D-4F59-A4DD-AB9EF99515FC',N'344B6975-429D-4F20-9287-3D9C95938000',N'5E6B2DBE-916D-4AA5-A9E0-305F3BF66CF7',N'có thiết kế năng động, hiện đại. Đặc biệt với kiểu dáng ôm chân vừa vặn mang đến sự trải nghiệm tốt nhất cho đôi chân của bạn.',10000000,15000000,1,15000000,'2023-11-06',1),
	 (N'6049B327-E697-D74D-A3AB-BB1F7543B139',N'SP05',N'Giày Thể Thao MLB Chunky Liner Diamond',N'EC0054D9-433D-4E85-8190-4582CDEBF593',N'D523EBBA-D211-0345-8EE8-15ECD456DBD9',N'D46378E2-9894-405B-8082-CEEEF95345B2',N'D2732979-A06B-4CB0-9061-329B411586F3',N'BB9B5A33-457D-4479-A673-7A4018D80DDD',N'7F30834B-812F-49FA-AA3E-B55B043D7B43',N'5D0BC152-D2BD-4D80-91DE-1820E24B6FF6',N'  là mẫu dành cho cả nam và nữ đến từ Hàn Quốc. Sở hữu thiết kế đẹp mắt cùng chất liệu cao cấp, mẫu giày cho người dùng thêm năng động và thoải mái khi đi lên chân.',3000000,3500000,1,3500000,'2023-11-06',1);
	INSERT INTO website_ban_giay.dbo.giay_chi_tiet (id,id_giay,id_kich_co,so_luong_ton,trangthai) VALUES
	 (N'E4F458CF-98D0-F847-BB59-07AE707F25BD',N'597A9673-6929-D342-96DC-0A4C47E3CC7B',N'3DC52F6D-C256-4D92-8A1A-3C0FEBFCC56A',20,1),
	 (N'0F034CF9-8D35-1846-BBFB-087BE7AB5DC0',N'FC4C744B-AEDE-B645-B118-7EA9140B8249',N'C4C4B808-00C8-48FB-B043-2CC3240A71B3',20,1),
	 (N'C83BF587-5499-8B49-9C26-219862C52458',N'CB626472-C91C-EC4A-9369-7B0942E94EDD',N'F72D2327-32E8-4AE4-9E21-2C72F847FE50',20,1),
	 (N'DFC08E8A-41C7-F54F-AD6D-21EAE092B6E4',N'CB626472-C91C-EC4A-9369-7B0942E94EDD',N'09FD4A50-B9F7-4292-8A89-0DB59C0936A3',20,1),
	 (N'85C526BB-61D2-DB4F-AD77-235CEA165A83',N'6049B327-E697-D74D-A3AB-BB1F7543B139',N'F72D2327-32E8-4AE4-9E21-2C72F847FE50',20,1),
	 (N'AF29B897-08AF-AA4D-A399-2F244B81733F',N'FC4C744B-AEDE-B645-B118-7EA9140B8249',N'F72D2327-32E8-4AE4-9E21-2C72F847FE50',20,1),
	 (N'7F33B9B3-FC9A-1245-A86A-3BE043ADCE87',N'C00D80A0-1ADB-7845-9F3B-9AC42C2E2D1C',N'F72D2327-32E8-4AE4-9E21-2C72F847FE50',10,1),
	 (N'DF231E0D-F81D-024F-86B5-3C7E689C294E',N'597A9673-6929-D342-96DC-0A4C47E3CC7B',N'6A438602-1AAD-4431-BE21-62BAFBC2D838',20,1),
	 (N'88C34317-3049-8D4E-801C-48870B23F314',N'597A9673-6929-D342-96DC-0A4C47E3CC7B',N'F72D2327-32E8-4AE4-9E21-2C72F847FE50',20,1),
	 (N'3144F5F3-9D37-0C43-8BD9-7897D58D3C38',N'6049B327-E697-D74D-A3AB-BB1F7543B139',N'9AE7954E-5C93-4DF6-A431-6786C0B2CED6',20,1);
	 INSERT INTO website_ban_giay.dbo.khach_hang (id,ma,avatar,ho_ten,ngay_sinh,sdt,email,mat_khau,trangthai) VALUES
	 (N'BB772ACE-91FF-4CBE-83DE-EA5E80FA3843',N'KH01','mlb1_1.jpg',N'Nguyễn Danh','2003-09-09',N'0385090080',N'danhnt@gmail.com',N'$2a$10$6xUrgawUAXvPCohIWbL9e.lD8LKI1.ZyeZyo8hKRzQqlqVJ3CpBtq',1),
	 (N'019427F5-1E52-402F-B5DC-EC17AD5F139A',N'KH02','mlb1_1.jpg',N'Nguyễn Đại','2003-08-08',N'0387090080',N'danhng@gmail.com',N'$2a$10$6xUrgawUAXvPCohIWbL9e.lD8LKI1.ZyeZyo8hKRzQqlqVJ3CpBtq',1),
	 (N'22B427F5-1E52-402F-B5DC-EC17AD5F139A',N'KH03','mlb1_1.jpg',N'Đào Gia Phong','2003-09-16',N'0385370656',N'phong@gmail.com',N'$2a$10$6xUrgawUAXvPCohIWbL9e.lD8LKI1.ZyeZyo8hKRzQqlqVJ3CpBtq',1);
	 INSERT INTO website_ban_giay.dbo.dia_chi (id,ma,id_khach_hang,ten_dia_chi,ten_nguoi_nhan,sdt_nguoi_nhan,xa,huyen,thanh_pho,trangthai) VALUES
	 (N'FD4A021B-4231-419C-9ADE-3F4FB6E86DE1',N'DC05',N'019427F5-1E52-402F-B5DC-EC17AD5F139A',N'Xóm 1 Triều Đông',N'Nguyễn Thành Danh',N'0385090080',N'Xã Tân Minh',N'Huyện Thường Tín',N'Thành phố Hà Nội',0),
	 (N'8B5AC57C-583E-4D62-88B4-558EF2FE3181',N'DC01',N'BB772ACE-91FF-4CBE-83DE-EA5E80FA3843',N'Xóm 1 Triều Đông',N'Nguyễn Thành Danh',N'0385090080',N'Xã Tân Minh',N'Huyện Thường Tín',N'Thành phố Hà Nội',1),
	 (N'1B1D2309-170B-46CA-B4A8-60C8E78D7962',N'DC02',N'BB772ACE-91FF-4CBE-83DE-EA5E80FA3843',N'Xóm 1 Triều Đông',N'Nguyễn Thành Danh',N'0385090080',N'Xã Tân Minh',N'Huyện Thường Tín',N'Thành phố Hà Nội',0),
	 (N'DB77537F-1E64-4850-B464-D570CF525128',N'DC04',N'019427F5-1E52-402F-B5DC-EC17AD5F139A',N'Xóm 1 Triều Đông',N'Nguyễn Thành Danh',N'0385090080',N'Xã Tân Minh',N'Huyện Thường Tín',N'Thành phố Hà Nội',1),
	 (N'FFA06F58-24F6-47E5-81BF-E30725A85957',N'DC03',N'BB772ACE-91FF-4CBE-83DE-EA5E80FA3843',N'Xóm 1 Triều Đông',N'Nguyễn Thành Danh',N'0385090080',N'Xã Tân Minh',N'Huyện Thường Tín',N'Thành phố Hà Nội',0);
	 INSERT INTO website_ban_giay.dbo.gio_hang (id,ma,id_khach_hang,ngay_tao,ngay_cap_nhap,ghi_chu,trangthai) VALUES
	 (N'D40A87D4-F3FF-4F92-8394-F793F75B1639',N'GH01',N'BB772ACE-91FF-4CBE-83DE-EA5E80FA3843','2023-11-06','2023-11-06',N'1',1);
	 INSERT INTO website_ban_giay.dbo.gio_hang_chi_tiet (id,id_gio_hang,id_giay_chi_tiet,so_luong,ghi_chu,trangthai) VALUES
	 (N'3A2E4DA9-49E0-4A2C-9CD7-1531F08C007C',N'D40A87D4-F3FF-4F92-8394-F793F75B1639',N'E4F458CF-98D0-F847-BB59-07AE707F25BD',3,N'1',1),
	 (N'B897AC00-69F7-4E49-8595-89124CC42E45',N'D40A87D4-F3FF-4F92-8394-F793F75B1639',N'0F034CF9-8D35-1846-BBFB-087BE7AB5DC0',5,N'1',1);
	 INSERT INTO website_ban_giay.dbo.san_pham_yeu_thich (id,id_khach_hang,ngay_tao,trangthai) VALUES
	 (N'72AE816C-5FE7-6B42-8971-4FD24759445C',N'22B427F5-1E52-402F-B5DC-EC17AD5F139A','2023-11-10',1);
	 INSERT INTO website_ban_giay.dbo.san_pham_yeu_thich_chi_tiet (id,id_san_pham_yeu_thich,id_giay,ngay_tao,trangthai) VALUES
	 (N'FC1C9BA0-01E9-B044-9E51-8CEEE4EB96C5',N'72AE816C-5FE7-6B42-8971-4FD24759445C',N'C00D80A0-1ADB-7845-9F3B-9AC42C2E2D1C','2023-11-10',1);
	 INSERT INTO website_ban_giay.dbo.chuong_tring_giam_gia_san_pham (id,ma,ten,phan_tram_giam,ngay_bat_dau,ngay_ket_thuc,id_nhan_vien_create,id_nhan_vien_update,trangthai) VALUES
	 (N'F00C57E5-B909-4CE4-B252-2FB5DB12F181',N'GGSP02',N'Mừng 8-3',20,'2023-11-06','2023-11-09',N'9D8372B8-AAA4-464A-A938-3C6927210010',N'5D89F310-6D41-41EE-B5B8-57F5B6DB7A9A',1),
	 (N'952D1932-0BB0-4259-BAD8-5E373E0BC427',N'GGSP01',N'Mừng Quốc Khánh',10,'2023-11-06','2023-11-09',N'9D8372B8-AAA4-464A-A938-3C6927210010',N'5D89F310-6D41-41EE-B5B8-57F5B6DB7A9A',1),
	 (N'74E890CD-8E00-4094-BCDA-9EEDC589BE4A',N'GGSP04',N'Mừng 20-11',13,'2023-11-06','2023-11-09',N'5D89F310-6D41-41EE-B5B8-57F5B6DB7A9A',N'5D89F310-6D41-41EE-B5B8-57F5B6DB7A9A',1),
	 (N'CD909688-7796-4AFD-9943-A1410ACD9999',N'GGSP03',N'Mừng Tết',15,'2023-11-06','2023-11-09',N'9D8372B8-AAA4-464A-A938-3C6927210010',N'5D89F310-6D41-41EE-B5B8-57F5B6DB7A9A',1),
	 (N'B8CFDF48-7F88-40BA-A227-CC72E40A0294',N'GGSP05',N'Mừng 9-11',17,'2023-11-06','2023-11-09',N'5D89F310-6D41-41EE-B5B8-57F5B6DB7A9A',N'5D89F310-6D41-41EE-B5B8-57F5B6DB7A9A',1);
	 INSERT INTO website_ban_giay.dbo.chuong_trinh_giam_gia_chi_tiet_san_pham (id,id_giay,id_chuong_trinh_giam_gia,so_tien_da_giam,trangthai) VALUES
	 (N'EE7B7F80-A961-B849-B9E9-3367396CDFE9',N'3F71490B-511F-7B44-84C9-3FE4413B3188',N'F00C57E5-B909-4CE4-B252-2FB5DB12F181',700000,1),
	 (N'F5E49D2F-AEBC-4248-A444-7A6F5028BFF8',N'597A9673-6929-D342-96DC-0A4C47E3CC7B',N'F00C57E5-B909-4CE4-B252-2FB5DB12F181',770000,1);
	 INSERT INTO website_ban_giay.dbo.chuong_trinh_giam_gia_hoa_don (id,ma,ten,dieu_kien,so_tien_giam_max,ngay_bat_dau,ngay_ket_thuc,phan_tram_giam,so_luong,trangthai) VALUES
	 (N'FA64ACA7-FA74-444B-BA3C-240BB4557FFC',N'GGHD02',N'Giảm 9%',2000000,200000,'2023-11-06','2023-11-09',9,20,0),
	 (N'A05DB32F-15A7-4684-9CFF-59700BC5404B',N'GGHD05',N'Giảm 3%',2000000,200000,'2023-11-06','2023-11-09',3,20,0),
	 (N'1E6AF3A2-918F-4C8D-86EB-8685ECD53A57',N'GGHD01',N'Giảm 10%',5000000,500000,'2023-11-06','2023-11-09',10,20,0),
	 (N'B8F5B69E-14AF-45AC-82AC-A6802430C7ED',N'GGHD04',N'Giảm 8%',1000000,200000,'2023-11-06','2023-11-09',8,20,0),
	 (N'0B4A4DC3-8FF0-41AC-85E3-AB76A3238611',N'GGHD03',N'Giảm 5%',1000000,100000,'2023-11-06','2023-11-09',5,20,0);
	 INSERT INTO website_ban_giay.dbo.anh_giay (id,ten_url,id_giay,trangthai) VALUES
	 (N'43DCC593-656A-314B-A21E-05E51546954C',N'mlb1_1.jpg',N'3F71490B-511F-7B44-84C9-3FE4413B3188',NULL),
	 (N'65C03615-C32C-5245-98EF-0DFE264E5C94',N'dior2_3.jpg',N'FC4C744B-AEDE-B645-B118-7EA9140B8249',NULL),
	 (N'6D68BCC8-42B5-C44A-A634-17DC20B321CC',N'mlb3_2.jpg',N'597A9673-6929-D342-96DC-0A4C47E3CC7B',NULL),
	 (N'5B39A0ED-D628-8D42-B83C-55F36C53C056',N'mlb1_3.jpg',N'3F71490B-511F-7B44-84C9-3FE4413B3188',NULL),
	 (N'323DB015-A59B-2747-A0B8-5CAF63A7CA33',N'mlb2_2.jpg',N'6049B327-E697-D74D-A3AB-BB1F7543B139',NULL),
	 (N'589122EF-B8DA-F143-AD48-6EA13101E1FF',N'mlb2_3.jpg',N'6049B327-E697-D74D-A3AB-BB1F7543B139',NULL),
	 (N'31B71E1B-BDA5-1B4D-926F-70AB72E920DF',N'mlb3_1.jpg',N'597A9673-6929-D342-96DC-0A4C47E3CC7B',NULL),
	 (N'A7A18237-871B-384D-BB01-7203994E8779',N'mlb3_3.jpg',N'597A9673-6929-D342-96DC-0A4C47E3CC7B',NULL),
	 (N'6A2F5F0D-014F-7C4C-A713-8B49C4CFCF7A',N'mlb1.jpg',N'3F71490B-511F-7B44-84C9-3FE4413B3188',NULL),
	 (N'5AB5446E-14D4-1745-9803-9E7757DEF5FF',N'dior3_1.jpg',N'CB626472-C91C-EC4A-9369-7B0942E94EDD',NULL),
	 (N'BEDDD94F-B5C2-0143-9368-9F1672A0348A',N'dior1_3.jpg',N'C00D80A0-1ADB-7845-9F3B-9AC42C2E2D1C',NULL),
	 (N'143CB0E4-72EB-7C43-B5D0-A37E5EF143CD',N'mlb2_1.jpg',N'6049B327-E697-D74D-A3AB-BB1F7543B139',NULL),
	 (N'4E6EF2AF-65FF-7D42-80EA-A60306D2EE75',N'dior3_3.jpg',N'CB626472-C91C-EC4A-9369-7B0942E94EDD',NULL),
	 (N'C9554A1E-F5BC-2844-A548-A8EB28CA0D5D',N'dior3.jpg',N'CB626472-C91C-EC4A-9369-7B0942E94EDD',NULL),
	 (N'217B614B-AE43-4741-BC2D-AB7FBF4803C4',N'dior1_1.jpg',N'C00D80A0-1ADB-7845-9F3B-9AC42C2E2D1C',NULL),
	 (N'1819A128-4CCE-E547-B31C-B460D21726AA',N'dior2.jpg',N'FC4C744B-AEDE-B645-B118-7EA9140B8249',NULL),
	 (N'3CB23777-6AD9-8843-8624-C204B14BE97A',N'dior3_2.jpg',N'CB626472-C91C-EC4A-9369-7B0942E94EDD',NULL),
	 (N'2836ACFB-3814-544C-80F0-C826CC3E04B9',N'mlb1_2.jpg',N'3F71490B-511F-7B44-84C9-3FE4413B3188',NULL),
	 (N'D9905F7E-A0C2-6D46-8D31-CB06DF808DB6',N'dior1.jpg',N'C00D80A0-1ADB-7845-9F3B-9AC42C2E2D1C',NULL),
	 (N'398446A0-E7EB-A74D-9055-E6934FEBBEC2',N'mlb2.jpg',N'6049B327-E697-D74D-A3AB-BB1F7543B139',NULL),
	 (N'90244A28-4E7E-3240-A9C6-E93776D779F3',N'dior2_2.jpg',N'FC4C744B-AEDE-B645-B118-7EA9140B8249',NULL),
	 (N'C071A3C4-AAA8-1D42-88D2-F536B093862C',N'dior2_1.jpg',N'FC4C744B-AEDE-B645-B118-7EA9140B8249',NULL),
	 (N'482E73CC-0327-374C-AF21-F7DFE4F38F32',N'dior1_2.jpg',N'C00D80A0-1ADB-7845-9F3B-9AC42C2E2D1C',NULL),
	 (N'845D57C3-0F44-BB47-982C-F7FBD8C5B0E7',N'mlb3.jpg',N'597A9673-6929-D342-96DC-0A4C47E3CC7B',NULL);

	 select * from khach_hang
	 select * from dia_chi
	 select * from hoa_don
	 select * from hoa_don_chi_tiet

	 delete from chuong_trinh_giam_gia_chi_tiet_hoa_don
	 delete from chuong_trinh_giam_gia_hoa_don
	 delete from hoa_don_chi_tiet
	 delete from hoa_don
