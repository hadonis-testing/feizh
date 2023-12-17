let totalExpensesChart;
let totalIncomesChart;
let expenseByCategoryChart;
let incomeByCategoryChart;

$(document).ready(function () {
    setupChart();
});

$("#duration").change(function () {
    setupChart();
});

function setupChart() {
    let selectedValue = $("#duration").val();

    fetch("dashboard?duration=" + selectedValue, { method: 'post' }).then(response => response.json()).then(data => {
        setupChartTotalExpenses(data['total-expenses']);
        setupChartTotalIncomes(data['total-incomes']);

        setupChartExpenseByCategory(data['expense-by-category']);
        setupChartIncomeByCategory(data['income-by-category']);
    }).catch(error => console.error('Error fetching data:', error));
}

function setupChartTotalExpenses(data) {
    if (totalExpensesChart) {
        totalExpensesChart.destroy();
    }

    let ctx = document.getElementById('total-expenses').getContext('2d');

    totalExpensesChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: data.labels,
            datasets: [{
                label: 'Total Expenses',
                data: data.dataset,
                borderWidth: 1
            }]
        },
        options: {
            locale: 'vi-VN',
            scales: {
                y: {
                    beginAtZero: true,
                    ticks: {
                        callback: function (value, index, values) {
                            return formatCurrencyVND(value);
                        }
                    }
                }
            }
        }
    });
}

function setupChartTotalIncomes(data) {
    if (totalIncomesChart) {
        totalIncomesChart.destroy();
    }

    let ctx = document.getElementById('total-incomes').getContext('2d');

    totalIncomesChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: data.labels,
            datasets: [{
                label: 'Total Incomes',
                data: data.dataset,
                borderWidth: 1
            }]
        },
        options: {
            locale: 'vi-VN',
            scales: {
                y: {
                    beginAtZero: true,
                    ticks: {
                        callback: function (value, index, values) {
                            return formatCurrencyVND(value);
                        }
                    }
                }
            }
        }
    });
}

function setupChartExpenseByCategory(data) {
    if (expenseByCategoryChart) {
        expenseByCategoryChart.destroy();
    }

    let ctx = document.getElementById('expense-by-category').getContext('2d');

    expenseByCategoryChart = new Chart(ctx, {
        type: 'pie',
        data: {
            labels: data.labels,
            datasets: [{
                data: data.dataset,
                borderWidth: 1
            }]
        }
    });
}

function setupChartIncomeByCategory(data) {
    if (incomeByCategoryChart) {
        incomeByCategoryChart.destroy();
    }

    let ctx = document.getElementById('income-by-category').getContext('2d');

    incomeByCategoryChart = new Chart(ctx, {
        type: 'pie',
        data: {
            labels: data.labels,
            datasets: [{
                data: data.dataset,
                borderWidth: 1
            }]
        }
    });
}