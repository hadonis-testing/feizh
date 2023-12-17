$(document).ready(function () {
    $('#transaction-management-table').DataTable({ "order": [[0, 'desc']] });
});

function viewDetail(ID) {
    let type = $("#transaction-" + ID).find(".is-capitalized").text().trim();

    switch (type) {
        case "income":
            viewDetailIncome(ID);
            break;
        case "expense":
            viewDetailExpense(ID);
            break;
        case "transfer":
            viewDetailTransfer(ID);
            break;
        default:
            break;
    }
}

function viewDetailIncome(ID) {
    let transaction = $("#transaction-" + ID);
    const url = "transaction?action=detail&id=" + ID + "&type=income";

    $.ajax({
        url: url,
        type: "POST",
        dataType: "json",
        success: function (data) {
            if (data.status === "success") {
                let category = data.category;

                let rowData = transaction.find("td").map(function () {
                    return $(this).text().trim();
                }).get();

                var transactionData = {
                    id: rowData[0],
                    sourceWallet: rowData[1],
                    amount: rowData[2],
                    description: rowData[3],
                    type: capitalizeFirstLetter(rowData[4]),
                    createAt: rowData[5],
                    category: category
                };

                let modal = document.getElementById('detail-income-modal');

                modal.classList.add('is-active', 'animate__bounceIn');

                $("#income-id").val(transactionData.id);
                $("#income-source-wallet").val(transactionData.sourceWallet);
                $("#income-amount").val(transactionData.amount);
                $("#income-description").val(transactionData.description);
                $("#income-type").val(transactionData.type);
                $("#income-create-at").val(transactionData.createAt);
                $("#income-category").val(transactionData.category);
            }
        },
    });
}

function viewDetailExpense(ID) {
    let transaction = $("#transaction-" + ID);
    const url = "transaction?action=detail&id=" + ID + "&type=expense";

    $.ajax({
        url: url,
        type: "POST",
        dataType: "json",
        success: function (data) {
            if (data.status === "success") {
                let category = data.category;

                let rowData = transaction.find("td").map(function () {
                    return $(this).text().trim();
                }).get();

                var transactionData = {
                    id: rowData[0],
                    sourceWallet: rowData[1],
                    amount: rowData[2],
                    description: rowData[3],
                    type: capitalizeFirstLetter(rowData[4]),
                    createAt: rowData[5],
                    category: category
                };

                let modal = document.getElementById('detail-expense-modal');

                modal.classList.add('is-active', 'animate__bounceIn');

                $("#expense-id").val(transactionData.id);
                $("#expense-source-wallet").val(transactionData.sourceWallet);
                $("#expense-amount").val(transactionData.amount);
                $("#expense-description").val(transactionData.description);
                $("#expense-type").val(transactionData.type);
                $("#expense-create-at").val(transactionData.createAt);
                $("#expense-category").val(transactionData.category);
            }
        },
    });
}

function viewDetailTransfer(ID) {
    let transaction = $("#transaction-" + ID);
    const url = "transaction?action=detail&id=" + ID + "&type=transfer";

    $.ajax({
        url: url,
        type: "POST",
        dataType: "json",
        success: function (data) {
            if (data.status === "success") {
                let wallet = data.wallet;

                let rowData = transaction.find("td").map(function () {
                    return $(this).text().trim();
                }).get();

                var transactionData = {
                    id: rowData[0],
                    sourceWallet: rowData[1],
                    amount: rowData[2],
                    description: rowData[3],
                    type: capitalizeFirstLetter(rowData[4]),
                    createAt: rowData[5],
                    wallet: wallet
                };

                let modal = document.getElementById('detail-transfer-modal');

                modal.classList.add('is-active', 'animate__bounceIn');

                $("#transfer-id").val(transactionData.id);
                $("#transfer-source-wallet").val(transactionData.sourceWallet);
                $("#transfer-amount").val(transactionData.amount);
                $("#transfer-description").val(transactionData.description);
                $("#transfer-type").val(transactionData.type);
                $("#transfer-create-at").val(transactionData.createAt);
                $("#transfer-target-wallet").val(transactionData.wallet);
            }
        },
    });
}

function closeModal() {
    $('#detail-income-modal, #detail-expense-modal, #detail-transfer-modal').removeClass('is-active');
}

function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}