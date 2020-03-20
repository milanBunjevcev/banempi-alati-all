 select BRRN,
        BROJ,
        OPERACIJA as NAZIV_OPERACIJE,
        NORMA,
        NAZIV_ARTIKLA,
        KAT_BROJ as KATALOSKI_BROJ,
        PROIZVEDENO as proizvedeno_kolicina,
        RAD_NA_VREME as utroseno_vreme,
        NAPOMENA,
        ID,
        CASE
WHEN pogon = 'mašinsko' THEN 1
WHEN pogon = 'mašinsko (cilindri)' THEN 2
WHEN pogon = 'montaa' THEN 3
WHEN pogon = 'montaa (cilindri)' THEN 4
WHEN pogon = 'livnica' THEN 5
WHEN pogon = 'glaèara' THEN 6
WHEN pogon = 'galvanizacija' THEN 7
WHEN pogon = 'farbara' THEN 8
WHEN pogon = 'krojenje' THEN 9
WHEN pogon = 'alatnica' THEN 10
WHEN pogon = 'odravanje' THEN 11
WHEN pogon = 'uprava' THEN 12
WHEN pogon = 'ostalo' THEN 13
WHEN pogon = 'glaèara i farbara' THEN 14
             ELSE pogon
        END as location_id,
        DATUM as datum_ucinka,
        '2017-01-1' as vreme_unosa_ucinka
 from DNEVNI_UCINAK
 where (datum >= '2017-01-1') and
       (datum <= '2020-12-31');
	   
	   /*
		nakon toga export as MySQL insert scriptu i ubaciti u novu bazu
	   */