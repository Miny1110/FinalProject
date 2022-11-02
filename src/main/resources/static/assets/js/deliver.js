

function getDeliver() {
    let checkIndex = $('input[name="deliverCheck"]:checked').index('input[name="deliverCheck"]');

    let dn = $('input[id="dname"]').eq(checkIndex).val();
    let dr = $('input[id="raddr"]').eq(checkIndex).val();
    let dd = $('input[id="daddr"]').eq(checkIndex).val();
    let dj = $('input[id="jaddr"]').eq(checkIndex).val();
    let dz = $('input[id="zipCode"]').eq(checkIndex).val();
    let dt = $('input[id="tel"]').eq(checkIndex).val();


    if (checkIndex != null) {
        $("#deliverName").val(dn)
        $("#deliverTel").val(dt);
        $("#deliverRAddr").val(dr);
        $("#deliverJAddr").val(dj);
        $("#deliverDAddr").val(dd);
        $("#deliverZipCode").val(dz);

    }
}