use batch;

drop table if exists product;

CREATE TABLE `product` (
   `pid` int DEFAULT NULL,
   `pcode` varchar(50) DEFAULT NULL,
   `pcost` double DEFAULT NULL,
   `gst` double DEFAULT NULL,
   `disc` double DEFAULT NULL
);

drop table if exists student;

CREATE TABLE `student` (
       `stdId` int DEFAULT NULL,
       `stdName` varchar(50) DEFAULT NULL,
       `stdFee` double DEFAULT NULL
);

INSERT INTO student (stdId, stdName, stdFee) VALUES (1, 'Alice Johnson', 5000.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (2, 'Bob Smith', 4500.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (3, 'Charlie Brown', 4700.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (4, 'David Williams', 4900.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (5, 'Emily Davis', 5100.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (6, 'Fiona Martinez', 5300.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (7, 'George Wilson', 5500.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (8, 'Hannah Clark', 5700.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (9, 'Ian Lewis', 5900.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (10, 'Jack Lee', 6100.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (11, 'Karen Walker', 6300.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (12, 'Liam Hall', 6500.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (13, 'Mia Young', 6700.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (14, 'Noah Allen', 6900.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (15, 'Olivia King', 7100.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (16, 'Paul Wright', 7300.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (17, 'Quinn Scott', 7500.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (18, 'Rachel Green', 7700.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (19, 'Sam Adams', 7900.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (20, 'Tina Baker', 8100.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (21, 'Ursula Cooper', 8300.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (22, 'Victor Murphy', 8500.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (23, 'Wendy Rogers', 8700.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (24, 'Xander Perry', 8900.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (25, 'Yvonne Cook', 9100.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (26, 'Zachary Nelson', 9300.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (27, 'Alice Walker', 9500.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (28, 'Bob Robinson', 9700.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (29, 'Charlie Carter', 9900.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (30, 'David Mitchell', 10100.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (31, 'Emily Evans', 10300.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (32, 'Fiona Murphy', 10500.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (33, 'George Roberts', 10700.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (34, 'Hannah Lopez', 10900.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (35, 'Ian Martinez', 11100.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (36, 'Jack Anderson', 11300.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (37, 'Karen Lewis', 11500.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (38, 'Liam Walker', 11700.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (39, 'Mia Taylor', 11900.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (40, 'Noah White', 12100.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (41, 'Olivia Harris', 12300.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (42, 'Paul Martin', 12500.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (43, 'Quinn Thompson', 12700.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (44, 'Rachel Lee', 12900.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (45, 'Sam Wilson', 13100.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (46, 'Tina White', 13300.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (47, 'Ursula Harris', 13500.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (48, 'Victor Brown', 13700.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (49, 'Wendy Jones', 13900.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (50, 'Xander Garcia', 14100.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (51, 'Yvonne Martinez', 14300.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (52, 'Zachary Davis', 14500.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (53, 'Alice Johnson', 14700.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (54, 'Bob Lee', 14900.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (55, 'Charlie Wilson', 15100.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (56, 'David Brown', 15300.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (57, 'Emily Clark', 15500.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (58, 'Fiona Walker', 15700.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (59, 'George Harris', 15900.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (60, 'Hannah Scott', 16100.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (61, 'Ian Taylor', 16300.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (62, 'Jack Johnson', 16500.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (63, 'Karen Anderson', 16700.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (64, 'Liam Garcia', 16900.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (65, 'Mia Martinez', 17100.00);
INSERT INTO student (stdId, stdName, stdFee) VALUES (66, 'Noah Smith', 17300);