var roundTrip = document.getElementById("roundtrip");
var oneTrip = document.getElementById("one-way");
var returnItem = document.querySelector(".returnDisplay");

roundTrip.addEventListener("click", function(){
    returnItem.style.display = "block";
})

oneTrip.addEventListener("click", function(){
    returnItem.style.display = "none";
})