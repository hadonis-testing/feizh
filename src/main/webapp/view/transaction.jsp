<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="io.hardingadonis.feizh.utils.Singleton"%>

<!DOCTYPE html>
<html lang="en" class="has-aside-left has-aside-mobile-transition has-navbar-fixed-top has-aside-expanded">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="dns-prefetch" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css?family=Nunito" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.13.8/css/dataTables.bulma.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/MaterialDesign-Webfont/7.3.67/css/materialdesignicons.min.css">
        <link rel="stylesheet"href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/main.css">

        <title>Feizh | Transaction Management</title>
    </head>

    <body>
        <%@include file="common/nav.jsp" %>
        <%@include file="common/aside.jsp" %>

        <div class="container has-text-centered">
            <nav class="breadcrumb is-centered has-arrow-separator is-large" aria-label="breadcrumbs">
                <ul>
                    <li>
                        <a href="dashboard">
                            <span class="icon is-small">
                                <i class="mdi mdi-monitor-dashboard" aria-hidden="true"></i>
                            </span>
                            <span>Management</span>
                        </a>
                    </li>
                    <li>
                        <a href="wallet" aria-current="page">
                            <span class="icon is-small">
                                <i class="mdi mdi-cash-multiple" aria-hidden="true"></i>
                            </span>
                            <span>Transaction</span>
                        </a>
                    </li>
                </ul>
            </nav>

            <button class="button is-primary" id="add-new-transaction" onclick="openModal()">Add New Transaction</button>
        </div>

        <section class="section is-main-section">

            <table class="table is-fullwidth is-bordered" id="transaction-management-table">
                <thead>
                    <tr>
                        <th class="has-text-centered is-vcentered">ID</th>
                        <th class="has-text-centered is-vcentered">Source Wallet</th>
                        <th class="has-text-centered is-vcentered">Amount</th>
                        <th class="has-text-centered is-vcentered">Description</th>
                        <th class="has-text-centered is-vcentered">Type</th>
                        <th class="has-text-centered is-vcentered">Create At</th>
                        <th class="has-text-centered is-vcentered">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="transaction" items="${Singleton.transactionDAO.getAll()}">
                        <tr id="transaction-${transaction.ID}">
                            <td data-label="ID" class="has-text-centered is-vcentered">
                                ${transaction.ID}
                            </td>
                            <td data-label="Source Wallet" class="has-text-centered is-vcentered">
                                ${Singleton.walletDAO.get(transaction.sourceWalletID).name}
                            </td>
                            <td data-label="Amount" class="VND has-text-centered is-vcentered">
                                ${transaction.amount}
                            </td>
                            <td data-label="Description" class="has-text-centered is-vcentered">
                                ${transaction.description}
                            </td>
                            <td data-label="Type" class="is-capitalized has-text-centered is-vcentered">
                                ${transaction.type.toString()}
                            </td>
                            <td data-label="Type" class="has-text-centered is-vcentered">
                                ${transaction.createAt}
                            </td>
                            <td>
                                <div class="buttons is-centered is-vcentered">
                                    <button class="button is-info is-outlined"type="button" onclick="viewDetail(${transaction.ID})">
                                        <span class="icon">
                                            <i class="mdi mdi-information"></i>
                                        </span>
                                    </button>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>

        <div id="detail-income-modal" class="modal animate__animated">
            <div class="modal-background"></div>
            <div class="modal-card">
                <header class="modal-card-head">
                    <p class="modal-card-title">Detail Income Transaction</p>
                    <button class="delete" aria-label="close" onclick="closeModal()"></button>
                </header>
                <section class="modal-card-body">
                    <div class="field">
                        <label class="label">ID</label>
                        <div class="control">
                            <input class="input" type="text" id="income-id" autocomplete="off" disabled>
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Source Wallet</label>
                        <div class="control">
                            <input class="input" type="text" id="income-source-wallet" autocomplete="off" disabled>
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Amount</label>
                        <div class="control">
                            <input class="input" type="text" id="income-amount" autocomplete="off" disabled>
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Description</label>
                        <div class="control">
                            <input class="input" type="text" id="income-description" autocomplete="off" disabled>
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Type</label>
                        <div class="control">
                            <input class="input" type="text" id="income-type" autocomplete="off" disabled>
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Create At</label>
                        <div class="control">
                            <input class="input" type="text" id="income-create-at" autocomplete="off" disabled>
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Category</label>
                        <div class="control">
                            <input class="input" type="text" id="income-category" autocomplete="off" disabled>
                        </div>
                    </div>

                </section>
            </div>
        </div>

        <div id="detail-expense-modal" class="modal animate__animated">
            <div class="modal-background"></div>
            <div class="modal-card">
                <header class="modal-card-head">
                    <p class="modal-card-title">Detail Expense Transaction</p>
                    <button class="delete" aria-label="close" onclick="closeModal()"></button>
                </header>
                <section class="modal-card-body">
                    <div class="field">
                        <label class="label">ID</label>
                        <div class="control">
                            <input class="input" type="text" id="expense-id" autocomplete="off" disabled>
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Source Wallet</label>
                        <div class="control">
                            <input class="input" type="text" id="expense-source-wallet" autocomplete="off" disabled>
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Amount</label>
                        <div class="control">
                            <input class="input" type="text" id="expense-amount" autocomplete="off" disabled>
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Description</label>
                        <div class="control">
                            <input class="input" type="text" id="expense-description" autocomplete="off" disabled>
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Type</label>
                        <div class="control">
                            <input class="input" type="text" id="expense-type" autocomplete="off" disabled>
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Create At</label>
                        <div class="control">
                            <input class="input" type="text" id="expense-create-at" autocomplete="off" disabled>
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Category</label>
                        <div class="control">
                            <input class="input" type="text" id="expense-category" autocomplete="off" disabled>
                        </div>
                    </div>

                </section>
            </div>
        </div>

        <div id="detail-transfer-modal" class="modal animate__animated">
            <div class="modal-background"></div>
            <div class="modal-card">
                <header class="modal-card-head">
                    <p class="modal-card-title">Detail Transfer Transaction</p>
                    <button class="delete" aria-label="close" onclick="closeModal()"></button>
                </header>
                <section class="modal-card-body">
                    <div class="field">
                        <label class="label">ID</label>
                        <div class="control">
                            <input class="input" type="text" id="transfer-id" autocomplete="off" disabled>
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Source Wallet</label>
                        <div class="control">
                            <input class="input" type="text" id="transfer-source-wallet" autocomplete="off" disabled>
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Amount</label>
                        <div class="control">
                            <input class="input" type="text" id="transfer-amount" autocomplete="off" disabled>
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Description</label>
                        <div class="control">
                            <input class="input" type="text" id="transfer-description" autocomplete="off" disabled>
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Type</label>
                        <div class="control">
                            <input class="input" type="text" id="transfer-type" autocomplete="off" disabled>
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Create At</label>
                        <div class="control">
                            <input class="input" type="text" id="transfer-create-at" autocomplete="off" disabled>
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Target Wallet</label>
                        <div class="control">
                            <input class="input" type="text" id="transfer-target-wallet" autocomplete="off" disabled>
                        </div>
                    </div>

                </section>
            </div>
        </div>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://cdn.datatables.net/1.13.8/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.13.8/js/dataTables.bulma.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="<%=request.getContextPath()%>/assets/js/main.js"></script>
        <script src="<%=request.getContextPath()%>/assets/js/transaction.js"></script>
    </body>
</html>
