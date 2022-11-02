function orderPay(){

    let f=document.order;
    let size =$("#size").val();
    let color =$("#color").val();


    if(!size||!color){
        alert("옵션 선택후 이용 가능합니다.");
        return false;
    }
    f.action="/order";
    f.submit();
    return true;
}