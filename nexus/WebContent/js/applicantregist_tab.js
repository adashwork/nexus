$(function(){
	$('#tab2').hide();
	$('#tab3').hide();

	$('#btn1,a').on('click',function(){
		$('#tab1').show();
		$('#tab2').hide();
		$('#tab3').hide();
	});

	$('#btn2,a').on('click',function(){
		$('#tab1').hide();
		$('#tab2').show();
		$('#tab3').hide();
	});

	$('#btn3,a').on('click',function(){
		$('#tab1').hide();
		$('#tab2').hide();
		$('#tab3').show();
	});
});