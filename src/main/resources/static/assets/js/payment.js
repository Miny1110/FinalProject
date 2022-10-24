

$(document).click(function requestPay() {
    alert("여기는 오니?")

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

    if(payment==="card"){
        pgName="html5_inicis";
        payMethod="card";
        payName="신용카드";
    }else if(payment==="bank"){
        pgName="html5_inicis";
        payMethod="vbank";
        payName="가상계좌";
    }else if(payment==="bank"){
        pgName="kcp";
        payMethod="phone";
        payName="휴대폰 결제";
    }else if(payment==="kakao"){
        pgName="kakaopay.TC0ONETIME";
        payMethod="card";
        payName="카카오페이";
    }else if(payment==="toss"){
        pgName="tosspay";
        payMethod="card";
        payName="토스";
    }else if(payment==="payco"){
        pgName="payco";
        payMethod="card";
        payName="페이코";
    }

    payOrder();

})
let pgName="";
let payMethod="";
let payName="";

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
        amount: $('#pointTotalPrice').html(), //총 가격
        buyer_email: $('#customerEmail').val() ,//구매자 메일
        buyer_name: $('#customerName').val(), //구매자 이름
        buyer_tel: $('#customerTel').html() ,
        payName: payName,
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
            let form = $('#orderForm')[0];

            let data =new FormData(form);

            let msg='결제가 완료되었습니다.';
            msg += '구매자:' +name;
            msg += "\n상점거래ID : " + merchant_uid;
            msg += "\n결제금액 : " + paid_amount;


            alert(msg)

            $.ajax({
                type:"Post",
                url: "/order_ok",
                dataType:'json',
                data:data{
                    'orderNum':merchant_uid,
                    "itemNum":$('#itemNum').val(),
                    "itemQty":$('#itemQty').val(),
                    "deliverName":$('#deliverName').val(),
                    "deliverRAddr":$('#deliverRAddr').val(),
                    "deliverJAddr":$('#deliverJAddr').val(),
                    "deliverDAddr":$('#deliverDAddr').val(),
                    "deliverZipCode":$('#deliverZipCode').val(),
                    "deliverTel":$('#deliverTel').val(),
                    "usePoint":$('#use').val(),
                },
                success: function(data){
                    let msg = "결제가 완료되었습니다.\n";

                    alert(msg);

                    location.href=url;
                },error: function (){
                    alert('결제를 실패하였습니다.')
                }

            })

                 /*   $.ajax({
                        url: "order_ok",
                        type:"POST",
                        data:{


                        },
                      success: function(data){
                            let msg = "결제가 완료되었습니다.\n";
                            msg += "구매자 : " + name;
                            msg += "\n결제금액 : " + paid_amount;
                            msg += "itemNum: " + itemNum;
                            alert(msg);

                            location.href=url;
                        },
         })*/




     } else {
         // 결제 실패 시 로직,
         let msg = "결제에 실패하였습니다./n"
         msg = "에러내용: " + rsp.error_msg;

         alert(msg);
     }
 });
}
