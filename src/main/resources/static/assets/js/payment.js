

function requestPay() {

  // let payCheck = $("#payCheck").val();
   let payCheck = $(':radio[name="credit"]:checked').val();
   if(payCheck===""){
       payCheck.val("card")
   }
    if(payCheck==="simple"){
        alert("간편결제 선택후 이용가능합니다.")
        return
    }
/*    $(document).ready(function () {
        $('#payCheck').val("card");

     /*   $('input[type="radio"]').on('click', (function () {

            if ($("input[name='credit']:checked").val() === 'card') {
                $("#payCheck").val("card");
            } else if ($("input[name='credit']:checked").val() === 'bank') {
                $("#payCheck").val("bank");
            } else if ($("input[name='credit']:checked").val() === 'phone') {
                $("#payCheck").val("phone");
            } else {
                $("#payCheck").val("kakao");
            }
        }));
    });*/
    alert(payCheck)

    if(payCheck==="card"){
        pgName="kcp";
        payMethod="card";
    }else if(payCheck==="bank"){
        pgName="kcp";
        payMethod="vbank";
    }else if(payCheck==="bank"){
        pgName="kcp";
        payMethod="phone";
    }else if(payCheck==="kakao"){
        pgName="kakaopay.TC0ONETIME";
        payMethod="card";
    }else if(payCheck==="toss"){
        pgName="tosspay";
        payMethod="card";
    }else if(payCheck==="payco"){
        pgName="payco";
        payMethod="card";
    }

    payment();
}
let pgName="";
let payMethod="";

function payment(){

    //아임포트 결제연동
    var IMP = window.IMP;
    IMP.init("imp73716258");


    // IMP.request_pay(param, callback) 결제창 호출
    IMP.request_pay({  // param(결제에대한 정보)
         /**pg:"kcp",
         pay_method:'vbank',**/
        /* pg: "tosspay",
         pay_method:'card',*/
        pg: pgName,
        pay_method:payMethod,
        merchant_uid:'cozyHouse12',
        name: $('#itemName').html() ,
        amount: $('#pointTotalPrice').html(),
        buyer_email: $('#customerEmail').html() ,
        buyer_name: $('#customerName').val(),
        buyer_tel: $('#customerTel').html() ,
        buyer_addr: $('#deliverAddr1').html(),
        buyer_postcode: $('#deliverZipCode').html(),
    },  function (rsp) { // callback함수
        if (rsp.success) {
            // 결제 성공 시 로직,
            let imp_uid = rsp.imp_id;
            let merchant_uid = rsp.merchant_uid;
            let paid_amount = rsp.paid_amount;
            let apply_num = rsp.apply_num;



            let data = {imp_uid:imp_uid,
                merchant_uid:merchant_uid,
                paid_amount:paid_amount,
                apply_num:apply_num
            };

            $.ajax({
                url: "/success_order",
                data:data,
                success: function(data){
                    let msg = "결제가 완료되었습니다.\n";
                    msg += "고유ID: " + imp_uid;
                    msg += "\n상점거래ID : " + merchant_uid;
                    msg += "\n결제금액 : " + paid_amount;
                    msg += "\n 카드승인번호 : " + apply_num;
                    alert(msg);
                    location.href="/success_order";
                }
            })// end ajax


        } else {
            // 결제 실패 시 로직,
            let msg = "결제에 실패하였습니다./n"
            msg = "에러내용: " + rsp.error_msg;

            alert(msg);
        }
    });
}
