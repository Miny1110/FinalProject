$(function(){
    //상품금액
    let itemPrice = 0;
    let iIndex = $('input[name="itemPrice"]').length;

    for(let i=0;i<iIndex;i++){
        itemPrice += parseInt($('input[name="itemPrice"]').eq(i).val())
    }

    let itemPriceFormat = itemPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    $('#itemPrice').html(itemPriceFormat+'원');

    //할인금액
    let disPrice = 0;
    let dIndex = $('input[name="disPrice"]').length;

    for(let i=0;i<dIndex;i++){
        disPrice += parseInt($('input[name="disPrice"]').eq(i).val())
    }

    let disPriceFormat = disPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    $('#disPrice').html(disPriceFormat+'원');

    let totalPrice = 0;
    totalPrice = (itemPrice - disPrice - $('#usePointData').val()).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    $('#totalPrice').html(totalPrice+'원');
})


