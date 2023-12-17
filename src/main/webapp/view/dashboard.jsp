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

        <title>Feizh | Dashboard</title>
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
                </ul>
            </nav>
        </div>

        <section class="section is-main-section">

            <div class="tile is-ancestor">
                <c:forEach var="wallet" items="${Singleton.walletDAO.getRecentUpdates()}">
                    <div class="tile is-parent">
                        <div class="card tile is-child">
                            <div class="card-content">
                                <div class="level is-mobile">
                                    <div class="level-item">
                                        <div class="is-widget-label">
                                            <h3 class="subtitle is-spaced">
                                                ${wallet.name}
                                            </h3>
                                            <h1 class="title VND">
                                                ${wallet.balance}
                                            </h1>
                                        </div>
                                    </div>
                                    <div class="level-item has-widget-icon">
                                        <div class="is-widget-icon">
                                            <span class="icon has-text-primary is-large">
                                                <i class="mdi mdi-wallet mdi-48px"></i>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <div class="is-flex py-6">
                <div class="select">
                    <select id="duration">
                        <option value="today">Today</option>
                        <option value="yesterday">Yesterday</option>
                        <option value="1week">1 week ago</option>
                        <option value="1month">1 month ago</option>
                        <option value="3months">3 months ago</option>
                        <option value="6months">6 months ago</option>
                        <option value="1year">1 year ago</option>
                    </select>
                </div>
            </div>

            <div class="columns">
                <div class="column">
                    <div class="card">
                        <header class="card-header">
                            <p class="card-header-title">
                                <span class="icon"><i class="mdi mdi-finance"></i></span>
                                Total Expenses
                            </p>
                        </header>
                        <div class="card-content">
                            <div class="chart-area">
                                <div style="height: 100%;">
                                    <div>
                                        <canvas id="total-expenses"></canvas>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="column">
                    <div class="card">
                        <header class="card-header">
                            <p class="card-header-title">
                                <span class="icon"><i class="mdi mdi-finance"></i></span>
                                Total Incomes
                            </p>
                        </header>
                        <div class="card-content">
                            <div class="chart-area">
                                <div style="height: 100%;">
                                    <div>
                                        <canvas id="total-incomes"></canvas>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="columns">
                <div class="column">
                    <div class="card">
                        <header class="card-header">
                            <p class="card-header-title">
                                <span class="icon"><i class="mdi mdi-finance"></i></span>
                                Expense By Category
                            </p>
                        </header>
                        <div class="card-content">
                            <div class="chart-area">
                                <div style="height: 100%;">
                                    <div>
                                        <canvas id="expense-by-category"></canvas>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="column">
                    <div class="card">
                        <header class="card-header">
                            <p class="card-header-title">
                                <span class="icon"><i class="mdi mdi-finance"></i></span>
                                Income By Category
                            </p>
                        </header>
                        <div class="card-content">
                            <div class="chart-area">
                                <div style="height: 100%;">
                                    <canvas id="income-by-category"></canvas>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </section>


        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://cdn.datatables.net/1.13.8/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.13.8/js/dataTables.bulma.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://cdn.jsdelivr.net/npm/chart.js@4.4.1/dist/chart.umd.min.js"></script>
        <script src="<%=request.getContextPath()%>/assets/js/main.js"></script>
        <script src="<%=request.getContextPath()%>/assets/js/dashboard.js"></script>
    </body>
</html>
