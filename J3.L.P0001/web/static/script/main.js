/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

window.onload = function () {
    var errorMessage = "${ERROR_MESSAGE}";
    var successMessage = "${SUCCESS_MESSAGE}";
    var modalContent = document.getElementById("modal-content");
    var modal = document.getElementById("modal");
    if (errorMessage) {
        modalContent.innerHTML = errorMessage;
        modal.style.display = "block";
    } else if (successMessage) {
        modalContent.innerHTML = successMessage;
        modal.style.display = "block";
    }
};

function closeModal() {
    var modalContent = document.getElementById("modal-content");
    modalContent.innerHTML = "";
    var modal = document.getElementById("modal");
    modal.style.display = "none";
}