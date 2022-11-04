function addDeliver(){
let msg="배송지 등록 이동시 주문정보가 초기화 됩니다.\n"
    msg+= "이동하시겠습니까?"
    let result=confirm(msg);

if(result){
    location.href="/customer/address";
}else{
    return true;
}
}