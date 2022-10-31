$(function (){
    function send() {
        var color =$('#color').val();
    }
    $('#optionColor li>a').on('click',function (){
        $('#dropdownMenuButton').text($(this).text());
        var color =$(this).attr('value');

        $('#color').val(color);
    });
});