/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

window.onload = function () {
    var errorMessage = "${ERROR_MESSAGE}";
    var successMessage = "${SUCCESS_MESSAGE}";
    if (errorMessage) {
        alert(errorMessage);
    } else if (successMessage) {
        alert(successMessage);
    }
    document.querySelectorAll('.menu a').forEach(x => {
        var path = x.getAttribute('href');
        if(path.includes(location.pathname)) {
            x.setAttribute('class', 'active');
        }
    });
};