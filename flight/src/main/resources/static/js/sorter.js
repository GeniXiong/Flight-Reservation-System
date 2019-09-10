var tog = true;
function toggleSortNumber(tableName, rowNumber){
  tog ? sortNumber(tableName, rowNumber) : sortNumberReverse(tableName, rowNumber);
}
function sortNumber(tableName, rowNumber) {
    var table, rows, switching, i, x, y, shouldSwitch;
    table = document.getElementById(tableName);
    switching = true;
    /* Make a loop that will continue until
    no switching has been done: */
    while (switching) {
      // Start by saying: no switching is done:
      switching = false;
      rows = table.rows;
      /* Loop through all table rows (except the
      first, which contains table headers): */
      for (i = 1; i < (rows.length - 2); i++) {
        // Start by saying there should be no switching:
        shouldSwitch = false;
        /* Get the two elements you want to compare,
        one from current row and one from the next: */
        x = rows[i].getElementsByTagName("td")[rowNumber];
        y = rows[i + 1].getElementsByTagName("td")[rowNumber];
        // Check if the two rows should switch place:
        if (Number(x.innerText) > Number(y.innerText)) {
          // If so, mark as a switch and break the loop:
          shouldSwitch = true;
          break;
        }
      }
      if (shouldSwitch) {
        /* If a switch has been marked, make the switch
        and mark that a switch has been done: */
        rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
        switching = true;
      }
    }
    tog = false;
  }
function sortNumberReverse(tableName, rowNumber) {
  var table, rows, switching, i, x, y, shouldSwitch;
    table = document.getElementById(tableName);
    switching = true;
    while (switching) {
      switching = false;
      rows = table.rows;
      for (i = 1; i < (rows.length - 2); i++) {
        shouldSwitch = false;
        /* Get the two elements you want to compare,
        one from current row and one from the next: */
        x = rows[i].getElementsByTagName("td")[rowNumber];
        y = rows[i + 1].getElementsByTagName("td")[rowNumber];
        // Check if the two rows should switch place:
        if (Number(x.innerText) < Number(y.innerText)) {
          shouldSwitch = true;
          break;
        }
      }
      if (shouldSwitch) {
        rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
        switching = true;
      }
    }
    tog = true;
}

function toggleSortTime(tableName, rowNumber, whatTime){
  tog ? sortTime(tableName, rowNumber) : sortTimeReverse(tableName, rowNumber);
}
function sortTime(tableName, rowNumber, whatTime) {
    var xDate, table, rows, switching, i, x, y, shouldSwitch;
    table = document.getElementById(tableName);
    switching = true;
    while (switching) {
      switching = false;
      rows = table.rows;
      for (i = 1; i < (rows.length - 2); i++) {
        shouldSwitch = false;
        x = rows[i].getElementsByTagName("td")[rowNumber];
        xDate = new Date(parseDate(x, whatTime));
        y = rows[i + 1].getElementsByTagName("td")[rowNumber];
        yDate = new Date(parseDate(y, whatTime));
        if (xDate > yDate) {
          shouldSwitch = true;
          break;
        }
      }
      if (shouldSwitch) {
        rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
        switching = true;
      }
    }
    tog = false;
  }
function sortTimeReverse(tableName, rowNumber, whatTime) {
  var table, rows, switching, i, x, y, shouldSwitch;
    table = document.getElementById(tableName);
    switching = true;
    while (switching) {
      switching = false;
      rows = table.rows;
      for (i = 1; i < (rows.length - 2); i++) {
        shouldSwitch = false;
        x = rows[i].getElementsByTagName("td")[rowNumber];
        xDate = new Date(parseDate(x, whatTime));
        y = rows[i + 1].getElementsByTagName("td")[rowNumber];
        yDate = new Date(parseDate(y, whatTime));
        if (xDate < yDate) {
          shouldSwitch = true;
          break;
        }
      }
      if (shouldSwitch) {
        rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
        switching = true;
      }
    }
    tog = true;
}

function parseDate(x, departOrArrive){
  var len, dateString;
  if (departOrArrive == "departDate"){
    console.log(x.getElementsByTagName("div")[0].getElementsByTagName("span")[0]);
    dateString = x.getElementsByTagName("div")[0].getElementsByTagName("span")[0].innerHTML;
  }
  else{
    len = x.getElementsByTagName("div").length;
    dateString = x.getElementsByTagName("div")[len - 1].getElementsByTagName("span")[1].innerHTML;
  }
  return dateString.replace(" ", "T");
}