$(function (){
    function send() {
        var size =$('#size').val();
    }
    $('#optionSize li>a').on('click',function (){
        $('#dropdownMenuButton1').text($(this).text());
        var size =$(this).attr('value');

        $('#size').val(size);
    });
});