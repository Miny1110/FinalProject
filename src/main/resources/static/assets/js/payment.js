let header = $("meta[name='_csrf_header']").attr("content");
let token = $("meta[name='_csrf']").attr("content");

function requestPay() {


/*   let f = document.orderForm*/
   //let payment = $("#payment").val();
   let payment = $(':radio[name="credit"]:checked').val();
   if(payment===""){
       payment.val("card")
   }
    if(payment==="simple"){
        alert("간편결제 선택후 이용가능합니다.")
        return
    }

    if(payment==="bank"){
        pgName="html5_inicis";
        payMethod="vbank";
        payName="가상계좌";
        payState="결제대기"
    }else if(payment==="card"){
        pgName="html5_inicis";
        payMethod="card";
        payName="신용카드";
        payState="주문완료"
    }else if(payment==="bank"){
        pgName="kcp";
        payMethod="phone";
        payName="휴대폰 결제";
        payState="주문완료"
    }else if(payment==="kakao"){
        pgName="kakaopay.TC0ONETIME";
        payMethod="card";
        payName="카카오페이";
        payState="주문완료"
    }else if(payment==="toss"){
        pgName="tosspay";
        payMethod="card";
        payName="토스";
        payState="주문완료"
    }else if(payment==="payco"){
        pgName="payco";
        payMethod="card";
        payName="페이코";
        payState="주문완료"
    }

    payOrder();

}
let pgName="";
let payMethod="";
let payName="";
let payState="";

function payOrder(){



    //아임포트 결제연동
    var IMP = window.IMP;
    IMP.init("imp73716258");
    let itemNum=$('#itemNum').val();
    let itemQty=$('#itemQty').val();



    // IMP.request_pay(param, callback) 결제창 호출
    IMP.request_pay({  // param(결제에대한 정보)
        pg: pgName,
        pay_method:payMethod,
        merchant_uid: 'cozy'+new Date().getTime(), //고유 order
        name: $('#itemName').html() , //item 이름
        amount: $('#pointTotalPrice').val(), //총 가격
        buyer_email: $('#customerEmail').val() ,//구매자 메일
        buyer_name: $('#customerName').val(), //구매자 이름
        buyer_tel: $('#customerTel').html() ,
        //form 안쓰기에 저장 필요


    },  function (rsp) { // 결제완료시
        if (rsp.success) {  // 결제 성공 시 로직,
            //form 안써서 한번에 넘기며 저장할거
            let itemNum =$('#itemNum').val();
            let qty=$('#itemQty').val();
            let name =rsp.buyer_name;

            let merchant_uid = rsp.merchant_uid;
            let paid_amount = rsp.paid_amount;
            let apply_num = rsp.apply_num;
            //db저장용
            let email=rsp.buyer_email;

            let msg='결제가 완료되었습니다.';
            msg += '구매자:' +name;
            msg += "\n주문번호 : " + merchant_uid;
            msg += "\n결제금액 : " + paid_amount;



            alert(msg)
           

            $.ajax({
                method:"POST",
                url: "/order_ok",
                contentType:'application/json',
             /*   traditional :true, 배열*/
                data:JSON.stringify({
                    orderNum:merchant_uid,
                    odNum: new Date().getTime(),
                    itemNum:parseInt($('#itemNum').val()),
                    itemQty:parseInt($('#itemQty').val()),
                    deliverName:$('#deliverName').val(),
                    deliverRAddr:$('#deliverRAddr').val(),
                    deliverJAddr:$('#deliverJAddr').val(),
                    orderState:payState,
                    payment:payName,
                    deliverDAddr:$('#deliverDAddr').val(),
                    deliverZipCode:$('#deliverZipCode').val(),
                    deliverTel:$('#deliverTel').val(),
                    deliverCost:$('#deliverCost').val(),
                    usePoint:$('#use').val(),
                    itemSize:$('#itemSize').val(),
                    itemColor:$('#itemColor').val()
                }), beforeSend: function (jqXHR) {
                    jqXHR.setRequestHeader(header, token);
                },
                success: function(data){

                    let msg = "결제가 완료되었습니다.\n";
                    alert(msg);

                    location.href="/success_order";
                },error: function (){
                    alert("결제가 정상적으로 진행되지 않았습니다.")
                }

            })

     } else {
         // 결제 실패 시 로직,
         let msg = "결제에 실패하였습니다./n"
         msg = "에러내용: " + rsp.error_msg;

         alert(msg);
     }
 });
}


