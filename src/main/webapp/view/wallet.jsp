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
        <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/main.css">

        <title>Feizh | Wallet Management</title>
    </head>

    <body>
        <%@include file="common/nav.jsp" %>
        <%@include file="common/aside.jsp" %>

        <div class="container">
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
                                <i class="mdi mdi-wallet" aria-hidden="true"></i>
                            </span>
                            <span>Wallet</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>

        <section class="section is-main-section">

            <table class="table is-fullwidth is-bordered" id="management-table">
                <thead>
                    <tr>
                        <th class="has-text-centered is-vcentered">ID</th>
                        <th class="has-text-centered is-vcentered">Name</th>
                        <th class="has-text-centered is-vcentered">Type</th>
                        <th class="has-text-centered is-vcentered">Balance</th>
                        <th class="has-text-centered is-vcentered">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="wallet" items="${Singleton.walletDAO.getAll()}">
                        <tr>
                            <td data-label="ID" class="has-text-centered is-vcentered">
                                ${wallet.ID}
                            </td>
                            <td data-label="Name" class="has-text-centered is-vcentered">
                                ${wallet.name}
                            </td>
                            <td data-label="Type" class="is-capitalized has-text-centered is-vcentered">
                                ${wallet.type.toString()}
                            </td>
                            <td data-label="Balance" class="VND has-text-centered is-vcentered">
                                ${wallet.balance}
                            </td>
                            <td>
                                <div class="buttons is-centered is-vcentered">
                                    <button class="button is-danger is-outlined"type="button" onclick="deleteWallet(${wallet.ID})">
                                        <span class="icon">
                                            <i class="mdi mdi-trash-can"></i>
                                        </span>
                                    </button>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://cdn.datatables.net/1.13.8/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.13.8/js/dataTables.bulma.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="<%=request.getContextPath()%>/assets/js/main.js"></script>
        <script src="<%=request.getContextPath()%>/assets/js/wallet.js"></script>
    </body>
</html>
