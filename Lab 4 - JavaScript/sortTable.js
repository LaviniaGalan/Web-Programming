var wasSorted = [0, 0, 0]
var sortingFunction;

function swapContent(cells, a, b) {
    var aux = cells[a].innerHTML;
    cells[a].innerHTML = cells[b].innerHTML;
    cells[b].innerHTML = aux;
}

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
    let allCells = table.getElementsByTagName("td");
    let numberOfColumns = table.rows[0].cells.length;
    
    setSortFunction(columnIndex);
        
    let stillSwapping = true, i
    do {
        stillSwapping = false;
        for (i = columnIndex; i < allCells.length - numberOfColumns; i += numberOfColumns) {
            if (sortingFunction(allCells[i].innerHTML.toLowerCase(),  allCells[i + numberOfColumns].innerHTML.toLowerCase())) {
                swapContent(allCells, i, i + numberOfColumns);
                stillSwapping = true;
            }
        }

    } while (stillSwapping)
}
