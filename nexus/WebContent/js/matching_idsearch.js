function disp(url){
   window.open(url,'','width=750,height=*,resizeable=yes,scrollbars=yes');
}

// '/nexus/web/matching-jobseekerid-search'
// '/nexus/web/matching-companyid-search'

var parantExistanceFlag = true;

//企業ＩＤ
function clickEnterCompany(company){

    //ウィンドウオブジェクトが存在していない時警告してフラグをfalseに
    if ( !window.opener || !Object.keys(window.opener).length ) {
        window.alert('親画面が存在しません')
        parantExistanceFlag = false
    }

    //親画面に値を挿入
    if(parantExistanceFlag){
       window.opener.$('#company_id').val(company.next().val());
       window.opener.$('#company_name').val(company.next().next().val());
    }
    window.close();
}

$(function() {

	$('[name=id_name_company]').on('click',function(){
		clickEnterCompany($(this));
    });

	$('#company_sb').on('click',function(){
        disp('/nexus/web/matching-companyid-search');
    });

  });

//求人ＩＤ
function clickEnterKyujin(kyujin){

    //ウィンドウオブジェクトが存在していない時警告してフラグをfalseに
    if ( !window.opener || !Object.keys(window.opener).length ) {
        window.alert('親画面が存在しません')
        parantExistanceFlag = false
    }

    //親画面に値を挿入
    if(parantExistanceFlag){
       window.opener.$('#kyujin_id').val(kyujin.next().val());
       window.opener.$('#kyujin_job').val(kyujin.next().next().val());
    }
    window.close();
}

$(function() {

	$('[name=id_name_kyujin]').on('click',function(){
		clickEnterKyujin($(this));
    });

	$('#kyujin_sb').on('click',function(){
        disp('/nexus/web/matching-kyujinid-search');
    });

  });


//求職者ＩＤ
function clickEnter(obj){

    //ウィンドウオブジェクトが存在していない時警告してフラグをfalseに
    if ( !window.opener || !Object.keys(window.opener).length ) {
        window.alert('親画面が存在しません')
        parantExistanceFlag = false
    }

    //親画面に値を挿入
    if(parantExistanceFlag){
       window.opener.$('#jobseeker_id').val(obj.next().val());
       window.opener.$('#jobseeker_name').val(obj.next().next().val());
    }
    window.close();
}

$(function() {

	$('[name=id_name_input]').on('click',function(){
		clickEnter($(this));
    });

	$('#jobseeker_sb').on('click',function(){
        disp('/nexus/web/matching-jobseekerid-search');
    });

  });