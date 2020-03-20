select ID, IME_RADNIKA from DNEVNI_UCINAK where (datum >= '2017-01-1') and (datum <= '2020-12-31');

/*
	export as MySQL script i pokreni jar pa ucitaj novu u novu bazu
*/

CREATE TABLE `dnevni_ucinak` (
  `id` bigint(20) NOT NULL,
  `ime_radnika` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
