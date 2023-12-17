<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="io.hardingadonis.feizh.utils.Singleton"%>
<%@page import="io.hardingadonis.feizh.model.detail.CategoryType"%>

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

        </div>

        <section class="section is-main-section">
            <div class="container">
                <form action="add-transaction" method="post" id="add-transaction-form">
                    <div class="field">
                        <label class="label">Source Wallet</label>
                        <div class="control">
                            <div class="select">
                                <select name="source-wallet" required>
                                    <c:forEach items="${Singleton.walletDAO.getAll()}" var="wallet">
                                        <option value="${wallet.ID}">${wallet.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="field">
                        <label class="label">Amount</label>
                        <div class="control">
                            <input class="input" type="number" name="amount" placeholder="Amount" autocomplete="off" required>
                        </div>
                    </div>

                    <div class="field">
                        <label class="label">Description</label>
                        <div class="control">
                            <textarea class="textarea" type="text" name="description" placeholder="Description" id="description" autocomplete="off" required></textarea>
                        </div>
                    </div>

                    <c:if test="${param.type eq 'income'}">
                        <div class="field">
                            <label class="label" id="type">Type</label>
                            <div class="control">
                                <div class="select">
                                    <select name="type" required>
                                        <option value="income">Income</option>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="field">
                            <label class="label" id="category">Category</label>
                            <div class="control">
                                <div class="select">
                                    <select name="category" required>
                                        <c:forEach items="${Singleton.categoryDAO.getAll(CategoryType.INCOME)}" var="category">
                                            <option value="${category.ID}">${category.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <c:if test="${param.type eq 'expense'}">
                        <div class="field">
                            <label class="label" id="type">Type</label>
                            <div class="control">
                                <div class="select">
                                    <select name="type" required>
                                        <option value="expense">Expense</option>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="field">
                            <label class="label" id="category">Category</label>
                            <div class="control">
                                <div class="select">
                                    <select name="category" required>
                                        <c:forEach items="${Singleton.categoryDAO.getAll(CategoryType.EXPENSE)}" var="category">
                                            <option value="${category.ID}">${category.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <c:if test="${param.type eq 'transfer'}">
                        <div class="field">
                            <label class="label" id="type">Type</label>
                            <div class="control">
                                <div class="select">
                                    <select name="type" required>
                                        <option value="transfer">Transfer</option>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="field">
                            <label class="label" id="category">Target Wallet</label>
                            <div class="control">
                                <div class="select">
                                    <select name="target-wallet" required>
                                        <c:forEach items="${Singleton.walletDAO.getAll()}" var="wallet">
                                            <option value="${wallet.ID}">${wallet.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <button class="button is-primary" type="submit">Submit</button>
                </form>
            </div>
        </section>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://cdn.datatables.net/1.13.8/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.13.8/js/dataTables.bulma.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="<%=request.getContextPath()%>/assets/js/main.js"></script>
        <script src="<%=request.getContextPath()%>/assets/js/add-transaction.js"></script>
    </body>
</html>
