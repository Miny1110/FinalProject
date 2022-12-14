let header = $("meta[name='_csrf_header']").attr("content");
let token = $("meta[name='_csrf']").attr("content");

function cart(){
            let customerEmail = $("#customerEmails").val();
            let iNum = $("#num").val();
            let itemNumChk = $("#itemNumChk").val();
            let size =$("#size").val();
            let sizeChk =$("#sizeChk").val();
            let color =$("#color").val();
            let colorChk =$("#colorChk").val();

            if(!customerEmail){
                alert("로그인 후 이용 가능합니다.")
                return false;
            }

            if(!size || !color){
                alert("옵션 선택후 이용 가능합니다.")
                return false;
            }

            if(iNum===itemNumChk&&size===sizeChk&&color===colorChk){
                let msg ="이미 장바구니에 들어있는 상품입니다.";
                msg += "장바구니에 추가하시겠습니까?";
                let result=confirm(msg);

                if(result){
                    updateCart()
                }else{
                return true;
                }
            }else{
                addCart()
            }

     }
     function updateCart(){
         $.ajax({
             url: "cartUpdate",
             type:"POST",
             contentType:'application/json',
             dataType: "TEXT",
             data:JSON.stringify({
                 itemNum:parseInt($('#num').val()),
                 itemQty:parseInt($('#itemQty').val()),
                 itemSize:$('#size').val(),
                 itemColor:$('#color').val(),
             }),beforeSend: function (jqXHR) {
                 jqXHR.setRequestHeader(header, token);
             },success: function() {
                 let msg = "장바구니에 추가되었습니다.\n";
                 msg += "장바구니로 이동하시겠습니까?\n";
                 let result=confirm(msg);

                 if(result){
                     location.href="cart";
                     return true;
                 }
                 else {
                     location.href="/itemDetail?itemNum="+data.itemNum;
                 }

             },error: function( request, status, error ){

                 alert("장바구니에 추가하지 못했습니다.");
             }
         })
     }
    function addCart(){
        $.ajax({
            url: "cart_ok",
            type:"POST",
            contentType:'application/json',
            dataType: "TEXT",
            data:JSON.stringify({
                itemNum:parseInt($('#num').val()),
                itemQty:parseInt($('#itemQty').val()),
                itemSize:$('#size').val(),
                itemColor:$('#color').val(),
            }),beforeSend: function (jqXHR) {
                jqXHR.setRequestHeader(header, token);
            },success: function() {
                let msg = "장바구니에 추가되었습니다.\n";
                msg += "장바구니로 이동하시겠습니까?\n";
                let result=confirm(msg);

                if(result){
                    location.href="cart";
                    return true;
                }
                else {
                    location.href="/itemDetail?itemNum="+data.itemNum;
                }

            },error: function( request, status, error ){

                alert("장바구니에 추가하지 못했습니다.");
            }
        })
    }

