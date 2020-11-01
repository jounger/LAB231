/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


window.addEventListener("load", function(e){
    var type = document.querySelectorAll('input[name="search-type"]');
    for (var i = 0; i < type.length; i++) {
        type[i].addEventListener("change", function (event) {
            var item = event.target.value;
              var returnDate = document.getElementById("reservation-code");
              returnDate.style.opacity = item === "all-booking" ? 0 : 1;
        });
    }
});