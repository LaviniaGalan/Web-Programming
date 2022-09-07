var wasSorted = [0, 0, 0]
var sortingFunction;

function sortAcendingly(a, b) {
    return a > b
}

function sortDescendingly(a, b) {
    return a < b
}

function setSortFunction(columnIndex) {
    if (wasSorted[columnIndex] == 0) {
        sortingFunction = sortAcendingly;
        wasSorted[columnIndex] = 1;
    }
    else {
        sortingFunction = sortDescendingly;
        wasSorted[columnIndex] = 0;
    }
}

function sort(columnIndex) {
    let table = document.querySelector("#myTable");
    
    setSortFunction(columnIndex);
        
    let stillSwapping = true, i
    do {
        stillSwapping = false;
        let rows = table.rows;
	    for (i = 1; i < (rows.length - 1); i++) {
            currentCell = rows[i].getElementsByTagName("td")[columnIndex];
   	        nextCell = rows[i + 1].getElementsByTagName("td")[columnIndex];
	        if (sortingFunction(currentCell.innerHTML.toLowerCase(), nextCell.innerHTML.toLowerCase())){
                rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                stillSwapping = true;
            }
        }
    } while (stillSwapping)
}
