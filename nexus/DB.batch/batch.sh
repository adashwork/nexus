#!/bin/sh

start=`date +%y%m%d%H%M%S`
echo バッチが実行されました
# ユーザー名/データベースが未指定の場合終了
if [ $# -lt 1 ]
then
    echo 引数の数が足りていません。 > error_`date +%y%m%d%H%M%S`.txt
    exit 1
fi

# このスクリプトのディレクトリの絶対パスを取得
DIR=$(cd $(dirname $0); pwd)
DBUSER="taya"
PASS="pgJav@1807"
DBNAME="taya"

CSVPATH=$1
if [ -d ${CSVPATH} ]; then
  echo ファイルが見つかりません。 > error_`date +%y%m%d%H%M%S`.txt
  exit 1;
fi

#nkfでcsvの文字コードをUTF-8に変換
nkf -wd --overwrite $CSVPATH

# MySQLをバッチモードで実行
CMD_MYSQL="mysql -u${DBUSER} -p${PASS} ${DBNAME}"

# ヒアドキュメントでSQL文を一括で実行
#   1. hwkyujinテーブルのクリア
#   2. CSVから必要な項目をインポート
#   3. hwkyujinテーブルからkyujinテーブルに項目を登録更新
$CMD_MYSQL <<EOF 2> error_`date +%y%m%d%H%M%S`.txt

-- DBを設定
use taya;

TRUNCATE table hwkyujin;

-- 文字コードをUTF-8する
SET character_set_database=utf8;

-- ハローワーク求人テーブルにcsvからデータを登録
LOAD DATA LOCAL INFILE '${CSVPATH}'
INTO TABLE hwkyujin
FIELDS
  TERMINATED BY ','
  OPTIONALLY ENCLOSED BY '"'
LINES
  TERMINATED BY '\n'
IGNORE 1 LINES
  (@field1,@field2,@field3,@field4,@field5,@field6,@field7,@field8,@field9,@field10,@field11,@field12,@field13,@field14,@field15,@field16,@field17,@field18,@field19,@field20,@field21,@field22,@field23,@field24,@field25,@field26,@field27,@field28,@field29,@field30,@field31,@field32,@field33,@field34,@field35,@field36,@field37,@field38,@field39,@field40,@field41,@field42,@field43,@field44,@field45,@field46,@field47,@field48,@field49,@field50,@field51,@field52,@field53,@field54,@field55,@field56,@field57,@field58,@field59,@field60,@field61,@field62,@field63,@field64,@field65,@field66,@field67,@field68,@field69,@field70,@field71,@field72,@field73,@field74,@field75,@field76,@field77,@field78,@field79,@field80,@field81,@field82,@field83,@field84,@field85,@field86,@field87,@field88,@field89,@field90,@field91,@field92,@field93,@field94,@field95,@field96,@field97,@field98,@field99,@field100,@field101,@field102,@field103,@field104,@field105,@field106,@field107,@field108,@field109,@field110,@field111,@field112,@field113,@field114,@field115,@field116,@field117,@field118,@field119,@field120,@field121,@field122,@field123,@field124,@field125,@field126,@field127,@field128,@field129,@field130,@field131,@field132,@field133,@field134,@field135,@field136,@field137,@field138,@field139,@field140,@field141,@field142,@field143,@field144,@field145,@field146,@field147,@field148,@field149,@field150,@field151,@field152,@field153,@field154,@field155,@field156,@field157,@field158,@field159,@field160,@field161,@field162,@field163,@field164,@field165,@field166,@field167,@field168,@field169,@field170,@field171,@field172,@field173,@field174,@field175,@field176,@field177,@field178,@field179,@field180,@field181,@field182,@field183,@field184,@field185,@field186,@field187,@field188,@field189,@field190,@field191,@field192,@field193,@field194,@field195,@field196,@field197,@field198,@field199,@field200,@field201,@field202,@field203,@field204,@field205,@field206,@field207,@field208,@field209,@field210,@field211,@field212,@field213,@field214,@field215,@field216,@field217,@field218,@field219,@field220,@field221,@field222,@field223,@field224,@field225,@field226,@field227,@field228,@field229,@field230,@field231,@field232,@field233,@field234,@field235,@field236,@field237,@field238,@field239,@field240,@field241,@field242,@field243,@field244,@field245,@field246,@field247,@field248,@field249,@field250,@field251,@field252,@field253,@field254,@field255,@field256,@field257,@field258,@field259,@field260,@field261,@field262,@field263,@field264,@field265,@field266,@field267,@field268,@field269,@field270,@field271,@field272,@field273,@field274,@field275,@field276,@field277,@field278,@field279,@field280,@field281,@field282,@field283,@field284,@field285,@field286,@field287,@field288,@field289,@field290,@field291,@field292,@field293,@field294,@field295,@field296,@field297,@field298,@field299,@field300
)
SET
no=@field1,
receptiondt=IF(STRCMP('0000-00-00',@field2),null,@fiel2),
perioddt=IF(STRCMP('0000-00-00',@field4),null,@field4),
companyno=@field11,
addresscd=substring(@field14 from 1 for 2),
jobsmallcd1=substring(@field20 from 1 for 3),
jobsmallcd2=substring(@field21 from 1 for 3),
jobsmallcd3=substring(@field22 from 1 for 3),
joblargecd1=substring(@field23 from 1 for 3),
joblargecd2=substring(@field24 from 1 for 3),
joblargecd3=substring(@field25 from 1 for 3),
jobcategorysmallcd=@field26,
jobcategorylargecd=@field27,
companykana=@field29,
companyname=@field30,
companypostal=@field31,
companyplace=@field32,
companyurl=@field33,
postal=@field40,
address=@field41,
nearstation=@field42,
job=@field43,
hakencd=@field44,
detail=@field46,
koyoukeitaicd=@field47,
koyoukikan=@field50,
koyoukikankaishi=IF(STRCMP('0000-00-00',@field52),null,@field52),
koyoukikanowari=IF(STRCMP('0000-00-00',@field52),null,@field52),
education=@field60,
experience=@field63,
license=@field66,
agemin=@field68,
agemax=@field69,
salarymin=@field78,
salarymax=@field79,
salaryformcd=@field99,
begintime=@field157,
endtime=@field158,
establishdt=@field202,
capital=@field205,
companyfeature=@field209,
tantouyakushoku=@field280,
tantoukana=@field283,
tantou=@field284;

-- 求人テーブルにハローワーク求人テーブルの項目を登録更新
INSERT INTO kyujin
  SELECT * FROM hwkyujin
   ON DUPLICATE KEY UPDATE
        kyujin.no = hwkyujin.no;
        

EOF

end=`date +%y%m%d%H%M%S`
echo 実行時間:$((end-start))


