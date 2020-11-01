/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

window.addEventListener("load", function(e){
    var stopDate = new Date("${quiz.dateStop}").getTime();
    var countdown = setInterval(function () {
        var now = new Date().getTime();

        // calc distance between now and count down date
        var timeLeft = stopDate - now;

        // specify days, hours, minutes, and seconds by time left
        var oneSec = 1000;
        var oneMin = 60 * oneSec;
        var oneHour = 60 * oneMin;
        var oneDay = 24 * oneHour;
        var days = Math.floor(timeLeft / oneDay);
        var hours = Math.floor((timeLeft % oneDay) / oneHour);
        var minutes = Math.floor((timeLeft % oneHour) / oneMin);
        var seconds = Math.floor((timeLeft % oneMin) / oneSec);

        var clock = "";

        // format date
        if (days > 0) {
            clock += days + ":";
        }
        if (hours > 0) {
            clock += hours + ":";
        }
        if (minutes > 0) {
            clock += minutes + ":";
        }
        if (seconds > 0) {
            clock += seconds;
        }

        // set to timer
        var timer = document.getElementById("timer");
        timer.innerHTML = clock;

        if (timeLeft <= 0) {
            clearInterval(countdown);
            // auto submit
            document.getElementById("submit").click();
        }

    }, 1000);
});