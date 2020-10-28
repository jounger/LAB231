/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


window.onload = function () {
    var flightType = document.querySelectorAll('input[name="flight-type"]');
    for (var i = 0; i < flightType.length; i++) {
        flightType[i].addEventListener("change", function (event) {
            var item = event.target.value;
            console.log(item)
            document.getElementById("return-date").setAttribute("class", item === "one-way" ? "remove" : "");
        });
    }
};