$(function(){
    //상품금액
    let itemPrice = 0;
    let iIndex = $('input[name="orderitemPrice"]').length;

    for(let i=0;i<iIndex;i++){
        itemPrice += parseInt($('input[name="orderitemPrice"]').eq(i).val())
    }

    let itemPriceFormat = itemPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    $('#orderitemPrice').html(itemPriceFormat+'원');

    //할인금액
    let disPrice = 0;
    let dIndex = $('input[name="orderdisPrice"]').length;

    for(let i=0;i<dIndex;i++){
        disPrice += parseInt($('input[name="orderdisPrice"]').eq(i).val())
    }

    let disPriceFormat = disPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    $('#orderdisPrice').html(disPriceFormat+'원');

    let totalPrice = 0;
    totalPrice = (itemPrice - disPrice - $('#orderusePointData').val()).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    $('#ordertotalPrice').html(totalPrice+'원');
})


