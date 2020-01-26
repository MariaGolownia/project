$(document).ready(function() {
    $('#locationName').on('change', function() {

        if (document.getElementById('locationName').selectedIndex == 1) {
            $('#loc1').attr('src', "./loc_img/loc1.jpg");
        } else
        {
            $('#loc1').attr('src', "./loc_img/loc2.jpg");
        }
    });
});