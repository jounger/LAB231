/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


window.addEventListener("load", function(e){
    var type = document.querySelectorAll('input[name="flight-type"]');
    var returnDate = document.getElementById("return-date");
    // if one way type is checked then hidding returnDate row
    returnDate.style.display = document.querySelector('input[value="one-way"]').checked ? "none" : "table-row";
    // add event listener
    for (var i = 0; i < type.length; i++) {
        type[i].addEventListener("change", function (event) {
            var item = event.target.value;
              returnDate.style.display = item === "one-way" ? "none" : "table-row";
        });
    }
});