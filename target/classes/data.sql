use batch;

drop table if exists product;

CREATE TABLE `product` (
   `pid` int DEFAULT NULL,
   `pcode` varchar(50) DEFAULT NULL,
   `pcost` double DEFAULT NULL,
   `gst` double DEFAULT NULL,
   `disc` double DEFAULT NULL
);

